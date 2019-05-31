package io.bounds4all.bondsmanager.controllers;

import io.bounds4all.bondsmanager.dtos.OrderDto;
import io.bounds4all.bondsmanager.model.Emission;
import io.bounds4all.bondsmanager.repositories.EmissionRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Api( description = "Controller to manage Emissions of Bonds.")
@RestController()
public class EmissionController {

    @Autowired
    EmissionRepository emissionRepository;

    @ApiOperation(value = "Get all the Emissions of Bonds. "
    )
    @RequestMapping(method= RequestMethod.GET, value="/api/emission")
    public List<Emission> getAllEmissions() {
        return emissionRepository.findAll();
    }

    @ApiOperation(value = "Shows an emission of Bond. ",
            notes = "Parameters to be included: Emssion(id)",
            response = OrderDto.class
    )
    @RequestMapping(method=RequestMethod.GET, value="/api/emission/{id}")
    public Optional<Emission> show(@PathVariable long id) {
        return emissionRepository.findById(id);
    }



}
