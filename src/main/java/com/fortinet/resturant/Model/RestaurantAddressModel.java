package com.fortinet.resturant.Model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantAddressModel {
    private Integer countryCode;
    private String city;
    private String address;
    private String locality;
    private Float longitude;
    private Float latitude;

}
