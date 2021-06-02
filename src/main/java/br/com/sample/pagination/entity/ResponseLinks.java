package br.com.sample.pagination.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class ResponseLinks implements Serializable {
    private String self;
    private String first;
    private String prev;
    private String next;
    private String last;
}