package io.bounds4all.bondsmanager.controllers;

import io.bounds4all.bondsmanager.dtos.OrderDto;
import io.bounds4all.bondsmanager.model.Bond;
import io.bounds4all.bondsmanager.model.User;
import io.bounds4all.bondsmanager.services.OrderService;
import io.bounds4all.bondsmanager.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api( description = "COntroller to manage Orders")
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    @ApiOperation(value = "Put a new order of bonds. ",
            notes = "The order is confirmed immediately. Parameters to be included: amount, emission(id), monthsLenght",
            response = OrderDto.class
    )
    @RequestMapping(method= RequestMethod.POST, value="/api/order/", headers = "Authorization")
    public OrderDto makeOrder(@RequestBody OrderDto order, @RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findByToken(token);

        OrderDto response = orderService.putOrder(order, user);
        return response;
    }

    @ApiOperation(value = "Get all the bonds for the User.")
    @RequestMapping(method= RequestMethod.GET, value="/api/order/getList", headers = "Authorization")
    public List <OrderDto> getOrders (@RequestHeader("Authorization") String token) {

        User user = userService.findByToken(token);

        List <OrderDto> response = orderService.getAllOrdersForUser(user);
        return response;
    }
    @ApiOperation(value = "Vhange the term for a List of bonds and recalculate the coupons." )
    @RequestMapping(method= RequestMethod.PUT, value="/api/order/changeBondTerm", headers = "Authorization")
    public List<Bond> changeBondTerm(@RequestHeader("Authorization") String token, @RequestBody OrderDto order) throws Exception {

        User user = userService.findByToken(token);
        List<Bond> response = orderService.changeBondTerm(order, user);
        return response;
    }

}
