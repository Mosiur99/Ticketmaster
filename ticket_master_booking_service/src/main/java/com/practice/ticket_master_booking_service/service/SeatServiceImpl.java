package com.practice.ticket_master_booking_service.service;

import com.practice.ticket_master_booking_service.domain.Seat;
import com.practice.ticket_master_booking_service.enumeration.SeatSection;
import com.practice.ticket_master_booking_service.model.Response.ActionResponse;
import com.practice.ticket_master_booking_service.repository.SeatRepository;
import com.practice.ticket_master_booking_service.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class SeatServiceImpl implements SeatService{

    private static final String SEAT_KEY_PREFIX = "seat:venue:";

    private final RedisUtil redisUtil;
    private final SeatRepository seatRepository;

    @Autowired
    public SeatServiceImpl(RedisUtil redisUtil,
                           SeatRepository seatRepository) {
        this.redisUtil = redisUtil;
        this.seatRepository = seatRepository;
    }

    @Override
    public Map<Object, Object> getSeatMap(Long eventId) {
        return redisUtil.getSeatMap(eventId);
    }

    @Override
    public ActionResponse setSeatMapInRedis(Long venueId) {
        if (Objects.isNull(venueId)) {
            return new ActionResponse(false, "Please provide a venue");
        }

        List<Seat> seats = seatRepository.getSeatsByVenueId(venueId);

        Map<String, Map<String, String>> sectionMap = new HashMap<>();

        for (Seat seat : seats) {

            String section = seat.getSection().toString();

            String key = buildKey(venueId, section);
            String field = seat.getRowName() + "-" + seat.getSeatNumber();
            String seatStatus = "AVAILABLE";

            sectionMap
                    .computeIfAbsent(key, k -> new HashMap<>())
                    .put(field, seatStatus);
        }

        for (Map.Entry<String, Map<String, String>> entry : sectionMap.entrySet()) {
            redisUtil.setSeatMap(entry.getKey(), entry.getValue());
        }

        return new ActionResponse(true, "Success");
    }

    private String buildKey(Long venueId, String section) {
        return SEAT_KEY_PREFIX + venueId + ":section:" + section;
    }

    @Override
    public boolean reserveSeats(Long venueId, SeatSection section, List<String> seatFields) {
        String key = buildKey(venueId, section.toString());
        return redisUtil.reserveSeats(key, seatFields);
    }
}
