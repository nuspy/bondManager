package io.bounds4all.bondsmanager.dtos;

import io.bounds4all.bondsmanager.model.Emission;
import io.bounds4all.bondsmanager.model.Order;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    @ApiModelProperty(value = "The emission of bonds")
    private Emission emission;
    @ApiModelProperty(value = "The amount of bounds for order")
    private int amount;
    @ApiModelProperty(value = "The amount of order")
    private int value;
    @ApiModelProperty(value = "The lenght in months of term")
    private int monthsLenght;
    @ApiModelProperty(value = "The coupon received")
    private double coupon;
    @ApiModelProperty(value = "The order involved")
    private Order order;
    @ApiModelProperty(value = "The List of Bonds to modify")
    private List<Long> bondToModify;
}
