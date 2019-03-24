package com.fortinet.resturant.Service;

import com.fortinet.resturant.Domain.RestaurantDomain;
import com.fortinet.resturant.Model.RestaurantModel;
import com.fortinet.resturant.Repository.RestaurantModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantModelRepository restaurantModelRepository;
    public Page<RestaurantDomain> getRestaurant(Pageable pageable) {
        Page<RestaurantDomain> toReturn ;
        Page<RestaurantModel> temp = restaurantModelRepository.findAll(pageable);

        List<RestaurantModel> tempList = temp.getContent();
        List<RestaurantDomain> toReturnList = new ArrayList<>();
        RestaurantDomain tempDomain ;
        for (RestaurantModel a: tempList) {
            tempDomain = new RestaurantDomain();
            tempDomain.setId(a.getRestaurantId());
            tempDomain.setName(a.getRestaurantName());
            tempDomain.setCity(a.getRestaurantAddress().getCity());
            tempDomain.setAddress(a.getRestaurantAddress().getAddress());
            tempDomain.setRating(a.getRating());
            tempDomain.setVotes(a.getVotes());
            tempDomain.setCuisine(a.getCuisineString());
            tempDomain.setCostForTwo(a.getAverageCostOfTwo());
            tempDomain.setCurrency(a.getCurrency());
            toReturnList.add(tempDomain);
        }
        toReturn = new PageImpl<>(toReturnList);
        return toReturn;
    }
}
