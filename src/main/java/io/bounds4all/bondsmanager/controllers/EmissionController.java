package io.bounds4all.bondsmanager.controllers;

import io.bounds4all.bondsmanager.model.Emission;
import io.bounds4all.bondsmanager.repositories.EmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmissionController {

    @Autowired
    EmissionRepository emissionRepository;

    @RequestMapping(method= RequestMethod.GET, value="/api/emission")
    public Iterable<Emission> product() {
        return emissionRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/api/emission")
    public long save(@RequestBody Emission emission) {
        emissionRepository.save(emission);
        return emission.getId();
    }

    @RequestMapping(method=RequestMethod.GET, value="/api/emission/{id}")
    public Optional<Emission> show(@PathVariable long id) {
        return emissionRepository.findById(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/api/products/{id}")
    public Emission update(@PathVariable long id, @RequestBody Emission emission) {
        Optional<Emission> existingEmission = emissionRepository.findById(id);
        if(emission.getEmissionDate() != null)
            existingEmission.get().setEmissionDate(emission.getEmissionDate());
        if(emission.getEmittedBonds() != null)
            existingEmission.get().setEmittedBonds(emission.getEmittedBonds());
        emissionRepository.save(existingEmission.get());
        return existingEmission.get();
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/api/products/{id}")
    public String delete(@PathVariable long id) {
        Optional<Emission> emission = emissionRepository.findById(id);
        emissionRepository.delete(emission.get());

        return "product deleted";
    }

}
