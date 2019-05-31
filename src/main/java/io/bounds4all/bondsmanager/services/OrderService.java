package io.bounds4all.bondsmanager.services;

import io.bounds4all.bondsmanager.dtos.OrderDto;
import io.bounds4all.bondsmanager.model.*;
import io.bounds4all.bondsmanager.repositories.BondRepository;
import io.bounds4all.bondsmanager.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OfferService offerService;

    @Autowired
    OrderConditionCheck orderConditionCheck;

    @Autowired
    BondRepository bondRepository;

    @Autowired
    OrderRepository orderRepository;

    public OrderDto putOrder(OrderDto order, User user) throws Exception {
        Emission emission = offerService.checkOffer(order);
        if (!orderConditionCheck.dailyLimitCheck(order, emission, user)) {
            throw new Exception("The order exceed the maximum daily amount of bond");
        }
        if (!orderConditionCheck.timeCheck(order, emission)) {
            throw new Exception("The operation amount exceed limit for nightly orders");
        }

        Pageable orderAmount = new PageRequest(0, order.getAmount());
        Page<Bond> bondsToAssign = bondRepository.findByEmissionAndOrderNotNull(emission, orderAmount);

        if (bondsToAssign.getSize() != order.getAmount()){
            throw new Exception("Another order has been placed and the amout of available bond has diminuished");
        }

        OrderDto responseOrderDto = offerService.calculateInitialCoupon(order, emission);
        Order newOrder = new Order();
        List<Bond> purchasedBonds = new ArrayList<>();
        for (Bond b : bondsToAssign){
            b.setOrder(newOrder);
            BondHistory bondHistory = new BondHistory();
            List<BondHistory>bondHistories = new ArrayList<>();
            bondHistories.add(bondHistory);
            bondHistory.setCoupon(responseOrderDto.getCoupon());
            b.setBondHistory(bondHistories);
            purchasedBonds.add(b);
        }

        newOrder.setUser(user);
        newOrder.setPurchaseDateTime(LocalDateTime.now());
        newOrder.setBonds(purchasedBonds);

       newOrder =  orderRepository.save(newOrder);

        responseOrderDto.setOrder(newOrder);

        return responseOrderDto;
    }

    public List<OrderDto> getAllOrdersForUser(User user) {

        List<Order> oldOrders = orderRepository.findByUser(user);
        List<OrderDto> result = new ArrayList<>();
        oldOrders.stream().forEach(o -> {
            OrderDto newOrderDto = new OrderDto();
            newOrderDto.setOrder(o);
            result.add(newOrderDto);
        });
        return result;
    }
}
