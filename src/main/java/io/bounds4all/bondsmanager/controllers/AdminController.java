package io.bounds4all.bondsmanager.controllers;

import io.bounds4all.bondsmanager.model.Emission;
import io.bounds4all.bondsmanager.repositories.EmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
public class AdminController {

    final
    EmissionRepository emissionRepository;

    public AdminController(@Autowired EmissionRepository emissionRepository) {
        this.emissionRepository = emissionRepository;
    }

    //Admin APIs
    @RequestMapping(method = RequestMethod.POST, value = "/api/admin/emission", headers = "Authorization")
    public long save(/*@RequestHeader("Authorization") String token,*/ @RequestBody Emission emission) {
        emissionRepository.save(emission);
        return emission.getId();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/api/admin/emission/{id}", headers = "Authorization")
    public Emission update(@PathVariable long id, @RequestBody Emission emission) {
        Optional<Emission> existingEmission = emissionRepository.findById(id);
        if (emission.getEmissionDate() != null)
            existingEmission.get().setEmissionDate(emission.getEmissionDate());
        if (emission.getEmittedBonds() != null)
            existingEmission.get().setEmittedBonds(emission.getEmittedBonds());
        emissionRepository.save(existingEmission.get());
        return existingEmission.get();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/admin/emission/{id}", headers = "Authorization")
    public String delete(@PathVariable long id) {
        Optional<Emission> emission = emissionRepository.findById(id);
        emissionRepository.delete(emission.get());

        return "product deleted";
    }

}
