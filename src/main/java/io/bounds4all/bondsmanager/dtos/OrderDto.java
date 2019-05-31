package io.bounds4all.bondsmanager.dtos;

import io.bounds4all.bondsmanager.model.Emission;
import io.bounds4all.bondsmanager.model.Order;
import lombok.Data;

@Data
public class OrderDto {
    private Emission emission;
    private int amount;
    private int value;
    private int monthsLenght;
    private double coupon;
    private Order order;
}
