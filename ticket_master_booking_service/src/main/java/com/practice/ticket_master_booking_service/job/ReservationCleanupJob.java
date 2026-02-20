package com.practice.ticket_master_booking_service.job;

import com.practice.ticket_master_booking_service.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ReservationCleanupJob {

    private final RedisUtil redisUtil;

    @Autowired
    public ReservationCleanupJob(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Scheduled(fixedRate = 30000)
    public void cleanup() {

        Long eventId = 1L;

        long now = System.currentTimeMillis();

        Set<Object> expired =
                redisUtil.getExpiredReservations(eventId, now);

        for (Object seat : expired) {

            redisUtil.setSeatStatus(eventId,
                    seat.toString(),
                    "AVAILABLE");

            redisUtil.removeReservation(eventId,
                    seat.toString());
        }
    }
}
