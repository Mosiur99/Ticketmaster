package com.practice.ticket_master_booking_service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Component
public class RedisUtil {

    private static final String MULTI_RESERVE_SCRIPT = """
        for i=1,#ARGV do
            local status = redis.call('HGET', KEYS[1], ARGV[i])
            if status ~= 'AVAILABLE' then
                return 0
            end
        end
    
        for i=1,#ARGV do
            redis.call('HSET', KEYS[1], ARGV[i], 'RESERVED')
        end
    
        return 1
    """;

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisUtil(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setSeatStatus(Long eventId, String seat, String status) {

        redisTemplate.opsForHash()
                .put("event:" + eventId + ":seats", seat, status);
    }

    public String getSeatStatus(Long eventId, String seat) {

        return (String) redisTemplate.opsForHash()
                .get("event:" + eventId + ":seats", seat);
    }

    public Map<Object, Object> getSeatMap(Long eventId) {

        return redisTemplate.opsForHash()
                .entries("event:" + eventId + ":seats");
    }

    public void addReservation(Long eventId, String seat, long expiry) {

        redisTemplate.opsForZSet()
                .add("event:" + eventId + ":reserved", seat, expiry);
    }

    public Set<Object> getExpiredReservations(Long eventId, long now) {

        return Collections.singleton(redisTemplate.opsForZSet()
                .rangeByScore("event:" + eventId + ":reserved", 0, now));
    }

    public void removeReservation(Long eventId, String seat) {

        redisTemplate.opsForZSet()
                .remove("event:" + eventId + ":reserved", seat);
    }

    public void setSeatMap(String key, Map<String, String> seatMap) {
        redisTemplate.opsForHash().putAll(key, seatMap);
    }

    public boolean reserveSeats(String key, List<String> seatFields) {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setScriptText(MULTI_RESERVE_SCRIPT);
        script.setResultType(Long.class);

        Long result = redisTemplate.execute(
                script,
                Collections.singletonList(key),
                seatFields.toArray()
        );

        return Objects.nonNull(result) && result == 1;
    }

    public void releaseSeats(String key, List<String> seatFields) {
        for (String field : seatFields) {
            redisTemplate.opsForHash().put(key, field, "AVAILABLE");
        }
    }

    public void createReservationTTL(Long bookingId,
                                     Long venueId,
                                     String section,
                                     List<String> seats) {

        String reservationKey = "reservation:" + bookingId;

        Map<String, String> data = new HashMap<>();
        data.put("venueId", venueId.toString());
        data.put("section", section);
        data.put("seats", String.join(",", seats));

        redisTemplate.opsForHash().putAll(reservationKey, data);

        redisTemplate.expire(reservationKey, Duration.ofMinutes(5));
    }

    public Map<String, String> getReservationData(Long bookingId) {
        String reservationKey = "reservation:" + bookingId;

        Map<Object, Object> raw = redisTemplate.opsForHash().entries(reservationKey);

        if (raw == null || raw.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, String> result = new HashMap<>();
        for (Map.Entry<Object, Object> entry : raw.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                result.put(entry.getKey().toString(), entry.getValue().toString());
            }
        }

        return result;
    }

    public void bookSeats(String key, List<String> seatFields) {
        for (String field : seatFields) {
            redisTemplate.opsForHash().put(key, field, "BOOKED");
        }
    }

    public void clearReservation(Long bookingId) {
        String reservationKey = "reservation:" + bookingId;
        redisTemplate.delete(reservationKey);
    }
}
