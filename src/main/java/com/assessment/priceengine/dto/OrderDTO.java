package com.assessment.priceengine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class OrderDTO implements Serializable {
    private int itemId;
    private boolean isUnit;
    private int qty;
}
