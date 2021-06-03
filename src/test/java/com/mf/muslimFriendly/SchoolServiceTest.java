package com.mf.muslimFriendly;

import com.mf.muslimFriendly.exception.ResourceNotFoundException;
import com.mf.muslimFriendly.model.Rating;
import com.mf.muslimFriendly.model.School;
import com.mf.muslimFriendly.model.SchoolRatingDTO;
import com.mf.muslimFriendly.repository.RatingRepository;
import com.mf.muslimFriendly.repository.SchoolRepository;
import com.mf.muslimFriendly.services.SchoolServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SchoolServiceTest {

    @Mock
    public SchoolRepository schoolRepository;

    @Mock
    public RatingRepository ratingRepository;

    @InjectMocks
    public SchoolServiceImpl schoolService;

    private School testSchool;

    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(this);
        testSchool = new School("School Name", "School Description", true, true, false);
    }

    @Test
    public void testGetAllSchools() {
        List<School> listOfSchools = new ArrayList<>();
        listOfSchools.add(testSchool);
        when(schoolRepository.findAll()).thenReturn(listOfSchools);
        List<SchoolRatingDTO> schools = schoolService.getSchools();
        Assert.assertEquals(schools.get(0).getName(), testSchool.getName());
    }

    @Test
    public void testGetSchoolById() throws ResourceNotFoundException {
        when(schoolRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(testSchool));
        when(ratingRepository.getAvgRating(any(Long.class))).thenReturn(1L);
        Assert.assertEquals(schoolService.getSchoolById("1").getName(), testSchool.getName());
    }

}
