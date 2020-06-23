package com.java.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConditionVo {
    private Integer typeid;
    private String author;
    private Double min_price;
    private Double max_price;
}
