package com.example.exam.service;

import com.example.exam.exception.BadRequest;
import com.example.exam.model.Doctor;
import com.example.exam.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@Repository
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;



    public Doctor getDoctor(Integer id) {return getEntity(id);
    }

    public boolean create(Doctor doctor) {
        doctor.setCreatedAt(LocalDateTime.now());
        doctor.setStatus(true);
        Doctor doctor1;
        doctorRepository.save(doctor);
        return true;
    }


    public boolean updateDoctor(Integer id, Doctor doctor) {
        Doctor doctor1 = getDoctor(id);
        doctor1.setCreatedAt(doctor.getCreatedAt());
        doctor1.setDeletedAt(doctor.getDeletedAt());
        doctorRepository.save(doctor1);
        return true;
    }

    public boolean delete(Integer id) {
        Doctor doctor = getEntity(id);
        doctor.setDeletedAt(LocalDateTime.now());
        doctorRepository.save(doctor);
        return true;
    }

    public Doctor getEntity(Integer id) {
        Optional<Doctor> optional;
        optional= doctorRepository.findById(id);
        if (optional.isEmpty()){
            throw new BadRequest("Doctor not found");
        }
        return optional.get();
    }
//    public String deleteDoctor(Integer id){
//        getEntity(id);
//        doctorRepository.deleteById(id);
//        return "Doctor Deleted";
//    }
}
