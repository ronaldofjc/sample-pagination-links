package br.com.sample.pagination.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<T> data;
    private ResponseLinks links;
    private Meta meta;
}