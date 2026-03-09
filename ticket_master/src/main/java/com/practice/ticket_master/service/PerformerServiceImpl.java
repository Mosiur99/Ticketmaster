package com.practice.ticket_master.service;

import com.practice.ticket_master.domain.Performer;
import com.practice.ticket_master.domain.Venue;
import com.practice.ticket_master.dto.request.PerformerCreateRequest;
import com.practice.ticket_master.dto.response.ActionResponse;
import com.practice.ticket_master.repository.PerformerRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PerformerServiceImpl implements PerformerService{

    private final PerformerRepository performerRepository;

    @Autowired
    public PerformerServiceImpl(PerformerRepository performerRepository) {
        this.performerRepository = performerRepository;
    }

    @Override
    public Optional<Performer> get(Long id) {
        return performerRepository.findById(id);
    }

    @Override
    @Transactional
    public ActionResponse create(PerformerCreateRequest request) {
        ActionResponse validationResult = validateRequest(request);
        if (!validationResult.getResult()) {
            return validationResult;
        }

        Performer performer = buildPerformer(request.getName());
        performerRepository.save(performer);
        return success("Created successfully");
    }

    private Performer buildPerformer(String name) {
        Performer performer = new Performer();
        performer.setName(name);
        return performer;
    }

    private ActionResponse validateRequest(PerformerCreateRequest request) {
        if (Objects.isNull(request)) {
            return failure("No request found");
        }

        if (StringUtils.isBlank(request.getName())) {
            return failure("Please provide performer name");
        }

        return success("Valid request");
    }

    private ActionResponse failure(String message) {
        return new ActionResponse(false, message);
    }

    private ActionResponse success(String message) {
        return new ActionResponse(true, message);
    }
}
