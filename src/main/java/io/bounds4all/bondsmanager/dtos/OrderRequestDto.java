package io.bounds4all.bondsmanager.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderRequestDto {
    @ApiModelProperty(value = "The emission of bonds")
    private long emission;
    @ApiModelProperty(value = "The amount of bounds for order")
    private int amount;
    @ApiModelProperty(value = "The amount of order")
    private int value;
    @ApiModelProperty(value = "The lenght in months of term")
    private int monthsLenght;
}
