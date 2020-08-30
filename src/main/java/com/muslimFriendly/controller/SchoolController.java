package com.muslimFriendly.controller;

import com.muslimFriendly.model.School;
import com.muslimFriendly.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SchoolController {

    @Autowired
    SchoolRepository schoolRepository;

    @GetMapping("/schools")
    public ResponseEntity<List<School>> getAllSchools(@RequestParam(required = false) String name) {
        try {
            List<School> schools = new ArrayList<School>();
            if(name == null) {
                schoolRepository.findAll().forEach(schools::add);
            } else {
                schoolRepository.findByName(name).forEach(schools::add);
            }
            if(schools.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(schools, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/schools/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable("id") String id) {
        Optional<School> schoolData = schoolRepository.findById(id);
        if(schoolData.isPresent()) {
            return new ResponseEntity<>(schoolData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/schools")
    public ResponseEntity<School> createSchool(@RequestBody School school) {
        try {
            School _school = schoolRepository.save(new School(school.getName(), school.getDescription()));
            return new ResponseEntity<>(_school, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/schools/{id}")
    public ResponseEntity<School> updateSchool(@PathVariable("id") String id, @RequestBody School school) {
        // @TODO
        return null;
    }

    @DeleteMapping("/schools/{id}")
    public ResponseEntity<HttpStatus> deleteSchool(@PathVariable("id") String id) {
        // @TODO
        return null;
    }

}
