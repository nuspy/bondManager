package io.bounds4all.bondsmanager.services;

import io.bounds4all.bondsmanager.dtos.OrderDto;
import io.bounds4all.bondsmanager.model.Emission;
import io.bounds4all.bondsmanager.model.Order;
import io.bounds4all.bondsmanager.model.User;
import io.bounds4all.bondsmanager.repositories.EmissionRepository;
import io.bounds4all.bondsmanager.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class OrderConditionCheck {

    @Autowired
    OrderRepository orderRepository;

    boolean getAvailability(OrderDto order, Emission emission) {
        List <Order> ordersOnEmission = orderRepository.findByEmission(emission);
        int totalBoundsForEmission = emission.getEmittedBonds().size();
        int assignedBounds = ordersOnEmission.stream().mapToInt(x -> x.getBonds().size()).sum();
        return order.getAmount() < totalBoundsForEmission - assignedBounds;
    }

    public boolean minimalTermCheck(OrderDto order, Emission emission) {
        return order.getMonthsLenght()> emission.getMinTerm();
    }

    public boolean timeCheck(OrderDto order, Emission emission)  {
        LocalTime time = LocalTime.now();
        if (time.isAfter(LocalTime.of(20,00)) && time.isBefore(LocalTime.of(06, 00))) {
            if (order.getAmount()* emission.getUnitValue() > emission.getMaxUncontrolledPurchase()){
                return false;
            }
        }
        return true;
    }
    public boolean dailyLimitCheck(OrderDto order, Emission emission, User user)  {
        List <Order> ordersOnEmissionPerUser = orderRepository.findByEmissionAndUser(emission, user);
        int totalDailyOrderPerUser = 0;
        for (Order o : ordersOnEmissionPerUser){
            if(o.getPurchaseDateTime().toLocalDate() == LocalDate.now()){
                totalDailyOrderPerUser = totalDailyOrderPerUser + o.getBonds().size();
            }
        }
        return (emission.getMaxUncontrolledPurchase()-totalDailyOrderPerUser>=order.getAmount());
    }
}
