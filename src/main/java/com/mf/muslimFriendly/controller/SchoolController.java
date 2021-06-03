package com.mf.muslimFriendly.controller;

import com.mf.muslimFriendly.exception.ResourceNotFoundException;
import com.mf.muslimFriendly.model.School;
import com.mf.muslimFriendly.model.SchoolRatingDTO;
import com.mf.muslimFriendly.services.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @RequestMapping("/ping")
    public String healthCheck() {
        log.info("Completing Health Check");
        return "Hello World!";
    }

    @GetMapping("/schools")
    @ResponseBody
    public List<SchoolRatingDTO> getSchools() {
        return schoolService.getSchools();
    }

    @GetMapping("/schools/{schoolId}")
    @ResponseBody
    public SchoolRatingDTO getSchoolById(@PathVariable("schoolId") String schoolId) throws ResourceNotFoundException {
        return schoolService.getSchoolById(schoolId);
    }

    @PutMapping("/schools/{schoolId}")
    public ResponseEntity<School> updateSchool(@PathVariable(value = "schoolId") String schoolId,
                                               @RequestBody School school) throws ResourceNotFoundException {
        School updatedSchool = schoolService.updateSchool(schoolId, school);
        return ResponseEntity.ok(updatedSchool);
    }

    @PostMapping("/schools")
    @ResponseBody
    public SchoolRatingDTO createSchool(@RequestBody School school) {
        return schoolService.createSchool(school);
    }

    @DeleteMapping("/schools/{schoolId}")
    public Map<String, Boolean> deleteSchool(@PathVariable(value = "schoolId") String schoolId) throws ResourceNotFoundException {
        schoolService.deleteSchool(schoolId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
