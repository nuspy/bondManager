package io.bounds4all.bondsmanager.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class BondModificationRequestDto {

    @ApiModelProperty(value = "The new lenght in months of term")
    private int monthsLenght;
    @ApiModelProperty(value = "The List of Bonds to modify")
    private List<Long> bondToModify;
}
