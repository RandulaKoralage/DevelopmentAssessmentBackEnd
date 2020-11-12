package com.assessment.priceengine.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "item")
public class Item implements Serializable{
    @Id
    @Column(name="itemId", nullable = false)
    @GeneratedValue
    private int itemId;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "unitsPerCarton")
    private int unitsPerCarton;

    @Column(name = "pricePerCarton")
    private double pricePerCarton;
}
