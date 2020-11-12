package com.assessment.priceengine.dto;


import lombok.*;

import java.util.HashMap;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private int itemId;
    private String itemName;
    private int unitsPerCarton;
    private double pricePerCarton;
    private HashMap<Integer,Double> unitPrices;

}
