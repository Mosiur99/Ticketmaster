package com.practice.ticket_master_search_service.repository;

import com.practice.ticket_master_search_service.domain.EventDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EventSearchRepository extends ElasticsearchRepository<EventDocument, Long> {

    List<EventDocument> findByName(String name);
}
