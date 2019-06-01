package io.bounds4all.bondsmanager.controllers;

import io.bounds4all.bondsmanager.dtos.OrderDto;
import io.bounds4all.bondsmanager.services.OfferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api( description = "COntroller to manage Offers")
@RestController
public class OfferController {

    @Autowired
    OfferService offerService;

    @ApiOperation(value = "Requires the calucaltion for an order of given paramenter. Parameters to be included: amount, emission(id), monthsLenght",
            notes = "The order is confirmed immediately.",
            response = OrderDto.class
    )
    @RequestMapping(method= RequestMethod.POST, value="/api/offer")
    public ResponseEntity<?> getOffer(@RequestBody OrderDto order) throws Exception {
        try {
            OrderDto response = offerService.calculateOffer(order);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get the amount of bonds available. Parameters to be included: emission(id)" )
    @RequestMapping(method= RequestMethod.GET, value="/api/offer")
    public ResponseEntity<?> getAvailableBondQuantity(long emissionId) {
        try {
            return ResponseEntity.ok(offerService.getAvailableBondQuantity(emissionId));
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
