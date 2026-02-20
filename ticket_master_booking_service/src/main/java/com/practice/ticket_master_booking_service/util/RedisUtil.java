package com.practice.ticket_master_booking_service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
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

        return redisTemplate.opsForZSet()
                .rangeByScore("event:" + eventId + ":reserved", 0, now);
    }

    public void removeReservation(Long eventId, String seat) {

        redisTemplate.opsForZSet()
                .remove("event:" + eventId + ":reserved", seat);
    }
}
