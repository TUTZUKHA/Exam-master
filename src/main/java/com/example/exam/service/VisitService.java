package com.example.exam.service;

import com.example.exam.exception.BadRequest;
import com.example.exam.model.Doctor;
import com.example.exam.model.Visit;
import com.example.exam.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Component
public class VisitService {
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;

    public Visit get(Integer id) {
        Optional<Visit> optional = visitRepository.findById(id);
        if (optional.isEmpty()){
            throw new BadRequest("Visit not found");
        }

        return optional.get();
    }

    public Visit getEntity(Integer id) {
        Optional<Visit>optional;
        optional= visitRepository.findById(id);
        if (optional.isEmpty()){
            throw new BadRequest("Visit not found");
        }
        return optional.get();
    }

    public Visit create(Visit visit) {
        doctorService.getDoctor(visit.getDoctorId());
        patientService.getPatient(visit.getPatient());
        visit.setCreatedAt(LocalDateTime.now());
        visit.setStatus(true);
        visitRepository.save(visit);

        return visit;
    }

//    public boolean update(Integer id, Visit visit) {
//        Visit oldVisit1= getEntity(id);
//        oldVisit1.set
//        return false;
//    }
public boolean updateVisit(Integer id, Visit visit) {
   Visit visit1 = getVisit(id);
   visit1.setCreatedAt(visit.getCreatedAt());
    visit1.setDeletedAt(visit.getDeletedAt());
    visitRepository.save(visit);
    return true;
}

    public Visit getVisit(Integer id) {
        return getEntity(id);
    }


    public boolean delete(Integer id) {
       Visit visit = getEntity(id);
        visit.setDeletedAt(LocalDateTime.now());
        visitRepository.save(visit);
        return true;
    }


}
