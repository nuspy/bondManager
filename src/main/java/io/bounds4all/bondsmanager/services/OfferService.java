package io.bounds4all.bondsmanager.services;

import io.bounds4all.bondsmanager.dtos.OrderDto;
import io.bounds4all.bondsmanager.model.Emission;
import io.bounds4all.bondsmanager.repositories.EmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    @Autowired
    OrderConditionCheck orderConditionCheck;

    @Autowired
    EmissionRepository emissionRepository;

    public OrderDto calculateOffer(OrderDto order) throws Exception {

        Emission emission = emissionRepository.getOne(order.getEmission().getId());
        if (!orderConditionCheck.getAvailability(order, emission)) {
            throw new Exception("Amount not available");
        }
        if (!orderConditionCheck.minimalTermCheck(order, emission)){
            throw new Exception("Term is too short.");
        }
        OrderDto responseOrderDto = calculateInitialCoupon(order, emission);
        return responseOrderDto;
    }

    public OrderDto calculateInitialCoupon(OrderDto order, Emission emission) {
        OrderDto response = new OrderDto();
        response.setAmount(order.getAmount());
        response.setEmission(order.getEmission());
        response.setMonthsLenght(order.getMonthsLenght());
        response.setCoupon(calculateCoupon(order, emission));
        return response;
    }

    private double calculateCoupon(OrderDto order, Emission emission) {
        double coupon = emission.getDefaultCoupon();
        if (order.getMonthsLenght()>emission.getMinTerm()){
            // out of specification: to make coherent with logic of already purchased bond term change
            coupon = coupon - ((order.getMonthsLenght() - emission.getMinTerm())/12*10);
        }
        if (coupon < 0) {
            coupon = 0;
        }
        return coupon;
    }
}
