package com.example.exam.controller;

import com.example.exam.model.Patient;
import com.example.exam.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.validation.Valid;
import java.util.List;

public class PatientController {
    private PatientService patientService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id) {
        Patient result = patientService.get(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Patient patient) {
        boolean result = patientService.createPatient(patient);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody @Valid Patient patient) {
       Patient result = patientService.update(id, patient);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        String  result = patientService.delete(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(@RequestParam("s") Integer size,
                                    @RequestParam("p") Integer page) {
        List<Patient> result = patientService.getall();
        return ResponseEntity.ok(result);
    }
}
