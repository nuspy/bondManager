package io.bounds4all.bondsmanager.controllers;

import io.bounds4all.bondsmanager.dtos.OrderDto;
import io.bounds4all.bondsmanager.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferController {

    @Autowired
    OfferService offerService;

    @RequestMapping(method= RequestMethod.POST, value="/api/offer")
    public OrderDto getOffer(@RequestBody OrderDto order) throws Exception {
        OrderDto response = offerService.calculateOffer(order);
        return response;
    }

    @RequestMapping(method= RequestMethod.POST, value="/api/offer")
    public int getAvailableBondQuantity(long emissionId){
        return offerService.getAvailableBondQuantity(emissionId);
    }
}
