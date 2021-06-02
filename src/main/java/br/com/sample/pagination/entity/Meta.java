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
public class Meta implements Serializable {
    private Long totalRecords;
    private Integer totalPages;
    private String requestDateTime;
}