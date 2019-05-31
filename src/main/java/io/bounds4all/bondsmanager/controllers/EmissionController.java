package io.bounds4all.bondsmanager.controllers;

import io.bounds4all.bondsmanager.model.Emission;
import io.bounds4all.bondsmanager.repositories.EmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController()
public class EmissionController {

    @Autowired
    EmissionRepository emissionRepository;

    @RequestMapping(method= RequestMethod.GET, value="/api/emission")
    public List<Emission> getAllEmissions() {
        return emissionRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.GET, value="/api/emission/{id}")
    public Optional<Emission> show(@PathVariable long id) {
        return emissionRepository.findById(id);
    }



}
