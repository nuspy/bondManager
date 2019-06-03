package io.bounds4all.bondsmanager.services;

import io.bounds4all.bondsmanager.dtos.OrderDto;
import io.bounds4all.bondsmanager.dtos.OrderRequestDto;
import io.bounds4all.bondsmanager.model.Emission;
import io.bounds4all.bondsmanager.repositories.BondRepository;
import io.bounds4all.bondsmanager.repositories.EmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    @Autowired
    OrderConditionCheck orderConditionCheck;

    @Autowired
    EmissionRepository emissionRepository;

    @Autowired
    BondRepository bondRepository;

    public OrderDto calculateOffer(OrderRequestDto order) throws Exception {

        Emission emission = checkOffer(order);
        OrderDto responseOrderDto = calculateInitialCoupon(order, emission);
        return responseOrderDto;
    }

    public Emission checkOffer(OrderRequestDto order) throws Exception {
        Emission emission = emissionRepository.getOne(order.getEmission());
        if (!orderConditionCheck.getAvailability(order, emission)) {
            throw new Exception("Amount not available");
        }
        if (!orderConditionCheck.minimalTermCheck(order, emission)){
            throw new Exception("Term is too short.");
        }
        return emission;
    }

    public OrderDto calculateInitialCoupon(OrderRequestDto order, Emission emission) {
        OrderDto response = new OrderDto();
        Emission orderEmission = emissionRepository.getOne(order.getEmission());
        response.setAmount(order.getAmount());
        response.setEmission(orderEmission);
        response.setMonthsLenght(order.getMonthsLenght());
        response.setCoupon(calculateCoupon(order.getMonthsLenght(), emission));
        return response;
    }

    public double calculateCoupon(int monthLenght, Emission emission) {
        double coupon = emission.getDefaultCoupon();
        if (monthLenght > emission.getMinTerm()) {
            // out of specification: to make coherent with logic of already purchased bond term change
            coupon = coupon - ((monthLenght - (double) emission.getMinTerm()) / 10 / 12);
        }
        if (coupon < 0) {
            coupon = 0;
        }
        return coupon;
    }

    public int getAvailableBondQuantity(long emissionId) {
        Emission emission = emissionRepository.getOne(emissionId);
        return bondRepository.findByEmissionAndOrderNull(emission).size();
    }
}
