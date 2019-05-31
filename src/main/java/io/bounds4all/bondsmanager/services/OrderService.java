package io.bounds4all.bondsmanager.services;

import io.bounds4all.bondsmanager.dtos.OrderDto;
import io.bounds4all.bondsmanager.model.*;
import io.bounds4all.bondsmanager.repositories.BondHistoryRepository;
import io.bounds4all.bondsmanager.repositories.BondRepository;
import io.bounds4all.bondsmanager.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
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

    @Autowired
    BondHistoryRepository bondHistoryRepository;

    public OrderDto putOrder(OrderDto order, User user) throws Exception {
        Emission emission = offerService.checkOffer(order);
        if (!orderConditionCheck.dailyLimitCheck(order, emission, user)) {
            throw new Exception("The order exceed the maximum daily amount of bond");
        }
        if (!orderConditionCheck.timeCheck(order, emission)) {
            throw new Exception("The operation amount exceed limit for nightly orders");
        }

        Pageable orderAmount = PageRequest.of(0, order.getAmount());
        Page<Bond> bondsToAssign = bondRepository.findByEmissionAndOrderNull(emission, orderAmount);

        if (bondsToAssign.getSize() != order.getAmount()){
            throw new Exception("Another order has been placed and the amout of available bond has diminuished");
        }

        OrderDto responseOrderDto = offerService.calculateInitialCoupon(order, emission);
        Order newOrder = new Order();
        List<Bond> purchasedBonds = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, order.getMonthsLenght());

        for (Bond b : bondsToAssign){
            b.setOrder(newOrder);
            BondHistory bondHistory = new BondHistory();
            List<BondHistory>bondHistories = new ArrayList<>();
            bondHistories.add(bondHistory);
            bondHistory.setCoupon(responseOrderDto.getCoupon());
            bondHistory.setTermEndDate(LocalDateTime.now());
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

    public Bond changeBondTerm(OrderDto order, User user) throws Exception {

        Bond bond = bondRepository.getOne(order.getOrder().getId());
        if (!bond.getOrder().getUser().equals(user)){
            throw new Exception("Bond associated to another user.");
        }

        BondHistory lastBond = getBondHistory(bond);

        BondHistory newBondHistory = new BondHistory();
        newBondHistory.setBond(lastBond.getBond());
        newBondHistory.setInsertDate(Calendar.getInstance().getTime());
        newBondHistory.setCoupon(offerService.calculateInitialCoupon(order, lastBond.getBond().getEmission()).getCoupon());
        newBondHistory.setTermEndDate(lastBond.getBond().getOrder().getPurchaseDateTime().plusMonths(order.getMonthsLenght()));

        bondHistoryRepository.save(newBondHistory);
        assert (bond.getBondHistory().contains(newBondHistory));
        return bondRepository.getOne(order.getOrder().getId());

    }

    private BondHistory getBondHistory(Bond bond) {
        List<BondHistory> bondHistory = bond.getBondHistory();
        bondHistory.sort(new Comparator<BondHistory>() {
            @Override
            public int compare(BondHistory first, BondHistory second) {
                return first.getInsertDate().compareTo(second.getInsertDate());
            }
        });
        return bondHistory.get(bondHistory.size()-1);
    }
}
