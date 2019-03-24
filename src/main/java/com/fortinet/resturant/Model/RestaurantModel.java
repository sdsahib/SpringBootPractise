package com.fortinet.resturant.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection="resturant")
public class RestaurantModel {
    @Id
    private String restaurantId;

    private RestaurantAddressModel restaurantAddress;
    private String restaurantName;
    private List<RestaurantCuisines> cuisines;
    private Float averageCostOfTwo;
    private String currency;
    private Boolean hasTable;
    private Boolean hasOnline;
    private Float rating;
    private String ratingColor;
    private String ratingText;
    private Integer votes;

    public String getCuisineString(){
        String toreturn ="";
        for(int i=0;i<cuisines.size();i++){
            if(i!=cuisines.size()-1)
                toreturn = toreturn + cuisines.get(i).getCuisineName() +",";
            else{
                toreturn = toreturn + cuisines.get(i).getCuisineName();
            }
        }
        return  toreturn;
    }


}
