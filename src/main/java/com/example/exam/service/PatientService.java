package com.example.exam.service;

import com.example.exam.exception.BadRequest;
import com.example.exam.model.Patient;
import com.example.exam.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;


    public Patient get(Integer id) {
        Optional<Patient> optional = patientRepository.findById(id);
        if (optional.isEmpty()){
            throw new BadRequest("Patient not found");
        }
        return optional.get();
    }

    public boolean createPatient(Patient patient) {
        patient.setCreatedAt(LocalDateTime.now());
        patient.setStatus(true);
        Patient patient1;
        patientRepository.save(patient);
        return true;
    }

    public Patient update(Integer id, Patient patient) {
        Patient oldPatient = get(id);
        oldPatient.setName(patient.getName());
        oldPatient.setSurname(patient.getSurname());
        oldPatient.setBirthday(patient.getBirthday());
        oldPatient.setAge(patient.getId());
        patientRepository.save(oldPatient);
        return oldPatient;
    }


    public String delete(Integer id){
        Patient patient = get(id);
        patientRepository.delete(patient);
        return "Patient's deleted";
    }

    public Patient getEntity(Integer id){
        Optional<Patient> optional;
        optional= patientRepository.findById(id);
        if (optional.isEmpty()){
            throw new BadRequest("Patient not found");
        }
        return optional.get();
    }
    public List<Patient> page(Integer size, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Patient> patientPage = patientRepository.findAll(pageRequest);
        List<Patient> patientList = new LinkedList<>();
        for (Patient u : patientPage) {
            patientList.add( u);
        }
        return patientList;
    }


    public List<Patient> getall() {
        List<Patient> getall=patientRepository.findAll();
        return getall;
    }

    public void getPatient(Patient patient) {

    }
}
