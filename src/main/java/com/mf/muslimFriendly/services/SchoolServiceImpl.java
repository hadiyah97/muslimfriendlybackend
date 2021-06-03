package com.mf.muslimFriendly.services;

import com.mf.muslimFriendly.exception.ResourceNotFoundException;
import com.mf.muslimFriendly.model.AvgRatingDTO;
import com.mf.muslimFriendly.model.School;
import com.mf.muslimFriendly.model.SchoolRatingDTO;
import com.mf.muslimFriendly.repository.RatingRepository;
import com.mf.muslimFriendly.repository.SchoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    private AvgRatingsServiceImpl avgRatingsServiceImpl;

    public SchoolServiceImpl(SchoolRepository schoolRepository,
                             RatingRepository ratingRepository) {
        this.schoolRepository = schoolRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<SchoolRatingDTO> getSchools() {
        Set<School> setOfSchools = new HashSet<>();
        schoolRepository.findAll().iterator().forEachRemaining(setOfSchools::add);
        return setOfSchools.stream().map(this::convertToSchoolRatingDTO).collect(Collectors.toList());
    }

    @Override
    public SchoolRatingDTO getSchoolById(String schoolId) throws ResourceNotFoundException {
        School school = schoolRepository.findById(Long.valueOf(schoolId))
                .orElseThrow(() -> new ResourceNotFoundException("School not found with the Id: " + schoolId));
        return convertToSchoolRatingDTO(school);
    }

    @Override
    public SchoolRatingDTO createSchool(School school) {
        try {
            schoolRepository.save(new School(school.getName(),
                    school.getDescription(),
                    school.isHalal(),
                    school.isPrayerSpace(),
                    school.isWuduArea()));
        } catch (Exception e) {
            log.info("Unable to create school: {}", school.getName());
        }
        return convertToSchoolRatingDTO(school);
    }

    @Override
    public School updateSchool(String schoolId, School school) throws ResourceNotFoundException {
        School _school = schoolRepository.findById(Long.valueOf(schoolId))
                .orElseThrow(() -> new ResourceNotFoundException("School not found with the Id: " + schoolId));
        _school.setName(school.getName());
        _school.setDescription(school.getDescription());
        _school.setHalal(school.isHalal());
        _school.setPrayerSpace(school.isPrayerSpace());
        _school.setWuduArea(school.isWuduArea());
        return schoolRepository.save(_school);
    }

    @Override
    public void deleteSchool(String schoolId) throws ResourceNotFoundException {
        School school = schoolRepository.findById(Long.valueOf(schoolId))
                .orElseThrow(() -> new ResourceNotFoundException("School not found with the Id: " + schoolId));
        schoolRepository.delete(school);
    }

    private SchoolRatingDTO convertToSchoolRatingDTO(School school) {
        SchoolRatingDTO schoolRatingDTO = new SchoolRatingDTO();
        schoolRatingDTO.setSchoolId(school.getId());
        schoolRatingDTO.setName(school.getName());
        schoolRatingDTO.setDescription(school.getDescription());
        schoolRatingDTO.setHalal(school.isHalal());
        schoolRatingDTO.setPrayerSpace(school.isPrayerSpace());
        schoolRatingDTO.setPrayerSpace(school.isWuduArea());
        AvgRatingDTO avgRatingDTO = avgRatingsServiceImpl.getAvgRatingsBySchool(school.getId());
        schoolRatingDTO.setAvgRatings(avgRatingDTO);
        return schoolRatingDTO;
    }
}
