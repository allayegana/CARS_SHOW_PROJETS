package com.blit.us.carshow.Entity;


import com.blit.us.carshow.Enum.CarshopEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Cars")
@Entity
public class Carshop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brand;
    private String model;
    private String color;
    private String registerNumber;
    private int year;
    private int price;
    @Enumerated(EnumType.STRING)
    @Column(name = "CAR_STATUS")
    @JsonProperty("CAR_STATUS")
    private CarshopEnum carshopEnum;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner")
    private OwnerCar owner;

}
