package com.fortinet.resturant.Domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantDomain {
    private String id;
    private String name;
    private String city;
    private String address;
    private float rating;
    private int votes;
    private String cuisine;
    private float costForTwo;
    private String currency;


}
