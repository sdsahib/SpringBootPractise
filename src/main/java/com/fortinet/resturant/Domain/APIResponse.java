package com.fortinet.resturant.Domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class APIResponse {
    private int status;
    private List<Object> data;
}
