package io.bounds4all.bondsmanager.services;

import io.bounds4all.bondsmanager.dtos.OrderRequestDto;
import io.bounds4all.bondsmanager.model.Bond;
import io.bounds4all.bondsmanager.model.Emission;
import io.bounds4all.bondsmanager.model.Order;
import io.bounds4all.bondsmanager.model.User;
import io.bounds4all.bondsmanager.repositories.BondRepository;
import io.bounds4all.bondsmanager.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class OrderConditionCheck {

    @Autowired
    BondRepository bondRepository;

    @Autowired
    OrderRepository orderRepository;

    boolean getAvailability(OrderRequestDto order, Emission emission) {
        List <Bond> bondOnEmission = bondRepository.findByEmission(emission);
        //int totalBoundsForEmission = emission.getEmittedBonds().size();
        //int assignedBounds = bondOnEmission.stream().mapToInt(x -> x.getBonds().size()).sum();
        int assignedBounds = bondRepository.findByEmissionAndOrderNotNull(emission).size();
        return order.getAmount() <= bondOnEmission.size() - assignedBounds;
    }

    public boolean minimalTermCheck(OrderRequestDto order, Emission emission) {
        return order.getMonthsLenght()>= emission.getMinTerm();
    }

    public boolean timeCheck(OrderRequestDto order, Emission emission) {
        LocalTime time = LocalTime.now();
        if (time.isAfter(LocalTime.of(20, 0)) && time.isBefore(LocalTime.of(6, 0))) {
            return !(order.getAmount() * emission.getUnitValue() > emission.getMaxUncontrolledPurchase());
        }
        return true;
    }

    public boolean dailyLimitCheck(OrderRequestDto order, Emission emission, User user) {
        List <Order> ordersPerUser = orderRepository.findByUser( user);
        int totalDailyOrderPerUser = 0;
        for (Order o : ordersPerUser){
            if(o.getPurchaseDateTime().toLocalDate().equals( LocalDate.now()) && o.getUser() == user ){

                totalDailyOrderPerUser = totalDailyOrderPerUser + o.getBonds().size();
            }
        }
        return (emission.getMaxUncontrolledPurchase()-totalDailyOrderPerUser>=order.getAmount());
    }
}
