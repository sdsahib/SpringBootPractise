package com.fortinet.resturant.Controller;

import com.fortinet.resturant.Domain.RestaurantDomain;
import com.fortinet.resturant.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin()
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/getList")
    Page<RestaurantDomain> get(Pageable pageable){
        Page<RestaurantDomain> toReturn = restaurantService.getRestaurant(pageable);
        return toReturn;

    }
}
