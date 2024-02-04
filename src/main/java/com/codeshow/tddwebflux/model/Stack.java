package com.codeshow.tddwebflux.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stack")
public record Stack (@Id String id, String componentes) {

}
