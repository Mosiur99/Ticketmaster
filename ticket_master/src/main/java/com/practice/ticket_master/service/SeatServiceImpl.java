package com.practice.ticket_master.service;

import com.practice.ticket_master.domain.Seat;
import com.practice.ticket_master.domain.Venue;
import com.practice.ticket_master.dto.request.SeatCreateRequest;
import com.practice.ticket_master.dto.request.SectionSeatRequest;
import com.practice.ticket_master.dto.response.ActionResponse;
import com.practice.ticket_master.enumeration.SeatSection;
import com.practice.ticket_master.repository.SeatRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {

    private static final int MAX_SEATS_PER_ROW = 10;

    private final VenueService venueService;
    private final SeatRepository seatRepository;

    @Autowired
    public SeatServiceImpl(VenueService venueService,
                           SeatRepository seatRepository) {
        this.venueService = venueService;
        this.seatRepository = seatRepository;
    }

    @Override
    @Transactional
    public ActionResponse create(SeatCreateRequest request) {
        ActionResponse validationResult = validateRequest(request);
        if (!validationResult.getResult()) {
            return validationResult;
        }

        Optional<Venue> venueOptional = venueService.get(request.getVenueId());
        if (venueOptional.isEmpty()) {
            return failure("Please provide a valid venue");
        }

        List<Seat> seats = buildSeats(request, venueOptional.get());
        seatRepository.saveAll(seats);
        return success("Created successfully");
    }

    private ActionResponse validateRequest(SeatCreateRequest request) {
        if (Objects.isNull(request)) {
            return failure("No request found");
        }

        if (Objects.isNull(request.getVenueId())) {
            return failure("Please provide a venue");
        }

        if (CollectionUtils.isEmpty(request.getSectionSeatRequests())) {
            return failure("Please provide a list of seat section");
        }

        return success("Valid request");
    }

    private ActionResponse failure(String message) {
        return new ActionResponse(false, message);
    }

    private ActionResponse success(String message) {
        return new ActionResponse(true, message);
    }

    private List<Seat> buildSeats(SeatCreateRequest request, Venue venue) {
        List<Seat> seats = new ArrayList<>();
        for (SectionSeatRequest sectionRequest : request.getSectionSeatRequests()) {
            SeatSection section = sectionRequest.getSection();
            int totalSeats = sectionRequest.getSeatCount();
            int rowIndex = 0;
            int seatNumberInRow = 1;
            for (int i = 1; i <= totalSeats; i++) {
                String rowName = generateRowName(rowIndex);
                seats.add(buildSeat(venue, section, rowName, seatNumberInRow));

                seatNumberInRow++;
                if (seatNumberInRow > MAX_SEATS_PER_ROW) {
                    seatNumberInRow = 1;
                    rowIndex++;
                }
            }
        }

        return seats;
    }

    private Seat buildSeat(Venue venue, SeatSection section, String rowName, int seatNumberInRow) {
        Seat seat = new Seat();
        seat.setVenue(venue);
        seat.setSection(section);
        seat.setRowName(rowName);
        seat.setSeatNumber(String.valueOf(seatNumberInRow));
        return seat;
    }

    private String generateRowName(int index) {
        if (index < 26) {
            return String.valueOf((char) ('A' + index));
        }

        int first = (index / 26) - 1;
        int second = index % 26;
        return String.valueOf((char) ('A' + first))
                + (char) ('A' + second);
    }
}
