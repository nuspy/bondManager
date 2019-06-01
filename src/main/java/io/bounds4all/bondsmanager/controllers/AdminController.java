package io.bounds4all.bondsmanager.controllers;

import io.bounds4all.bondsmanager.model.Emission;
import io.bounds4all.bondsmanager.repositories.EmissionRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(description = "Controller fot admin Ops")
@RestController()
public class AdminController {

    final
    EmissionRepository emissionRepository;

    public AdminController(@Autowired EmissionRepository emissionRepository) {
        this.emissionRepository = emissionRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/admin/emission", headers = "Authorization")
    public ResponseEntity<?> save(/*@RequestHeader("Authorization") String token,*/ @RequestBody Emission emission) {
        try {
            emissionRepository.save(emission);
            return ResponseEntity.ok(emission.getId());
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/api/admin/emission/{id}", headers = "Authorization")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody Emission emission) {
        Optional<Emission> existingEmission = emissionRepository.findById(id);
        if (emission.getEmissionDate() != null)
            existingEmission.get().setEmissionDate(emission.getEmissionDate());
        if (emission.getEmittedBonds() != null)
            existingEmission.get().setEmittedBonds(emission.getEmittedBonds());
        try {
            emissionRepository.save(existingEmission.get());
            return ResponseEntity.ok(existingEmission.get());
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/admin/emission/{id}", headers = "Authorization")
    public ResponseEntity<?> delete(@PathVariable long id) {
        Optional<Emission> emission = emissionRepository.findById(id);
        try {
            emissionRepository.delete(emission.get());
            return ResponseEntity.ok("product deleted");
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
