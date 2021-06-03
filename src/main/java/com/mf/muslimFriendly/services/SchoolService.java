package com.mf.muslimFriendly.services;

import com.mf.muslimFriendly.exception.ResourceNotFoundException;
import com.mf.muslimFriendly.model.School;
import com.mf.muslimFriendly.model.SchoolRatingDTO;

import java.util.List;

public interface SchoolService {

    List<SchoolRatingDTO> getSchools();

    SchoolRatingDTO getSchoolById(String schoolId) throws ResourceNotFoundException;

    SchoolRatingDTO createSchool(School school);

    School updateSchool(String schoolId, School school) throws ResourceNotFoundException;

    void deleteSchool(String schoolId) throws ResourceNotFoundException;
}
