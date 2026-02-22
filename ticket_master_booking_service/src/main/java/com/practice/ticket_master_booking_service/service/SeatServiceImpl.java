package com.practice.ticket_master_booking_service.service;

import com.practice.ticket_master_booking_service.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SeatServiceImpl implements SeatService{

    private final RedisUtil redisUtil;

    @Autowired
    public SeatServiceImpl(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public Map<Object, Object> getSeatMap(Long eventId) {
        return redisUtil.getSeatMap(eventId);
    }

    @Override
    public boolean reserveSeat(Long eventId, String seat) {

        String status = redisUtil.getSeatStatus(eventId, seat);

        if (!"AVAILABLE".equals(status)) {
            return false;
        }

        redisUtil.setSeatStatus(eventId, seat, "RESERVED");

        long expiry = System.currentTimeMillis() + 300000;

        redisUtil.addReservation(eventId, seat, expiry);

        return true;
    }
}
