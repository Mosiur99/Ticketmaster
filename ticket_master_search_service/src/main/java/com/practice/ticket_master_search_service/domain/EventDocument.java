package com.practice.ticket_master_search_service.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "event", createIndex = false)
public class EventDocument {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String name;
}
