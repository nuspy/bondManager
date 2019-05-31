package io.bounds4all.bondsmanager.controllers;

import io.bounds4all.bondsmanager.dtos.OrderDto;
import io.bounds4all.bondsmanager.model.Emission;
import io.bounds4all.bondsmanager.model.Order;
import io.bounds4all.bondsmanager.model.User;
import io.bounds4all.bondsmanager.services.OrderService;
import io.bounds4all.bondsmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;


    @RequestMapping(method= RequestMethod.POST, value="/api/order", headers = "Authorization")
    public OrderDto makeOrder(@RequestBody OrderDto order, @RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findByToken(token);

        OrderDto response = orderService.putOrder(order, user);
        return response;
    }

    @RequestMapping(method= RequestMethod.POST, value="/api/order", headers = "Authorization")
    public List <OrderDto> getOrders (@RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findByToken(token);

        List <OrderDto> response = orderService.getAllOrdersForUser(user);
        return response;
    }
}
