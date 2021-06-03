package com.mf.muslimFriendly.services;

import com.mf.muslimFriendly.exception.ResourceNotFoundException;
import com.mf.muslimFriendly.model.Rating;
import com.mf.muslimFriendly.model.School;
import com.mf.muslimFriendly.repository.RatingRepository;
import com.mf.muslimFriendly.repository.SchoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.Set;

@Slf4j
@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final SchoolRepository schoolRepository;

    public RatingServiceImpl(RatingRepository ratingRepository,
                             SchoolRepository schoolRepository) {
        this.ratingRepository = ratingRepository;
        this.schoolRepository = schoolRepository;
    }

    @Override
    public Set<Rating> getRatingsBySchool(Long schoolId) {
        return ratingRepository.findBySchoolId(schoolId);
    }

    @Override
    public Rating createRating(Long schoolId, Rating rating) throws ResourceNotFoundException {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ResourceNotFoundException("School not found with the Id: " + schoolId));
        rating.setSchool(school);
        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(Long schoolId, Long ratingId, Rating _rating) throws ResourceNotFoundException {
        if (!schoolRepository.existsById(schoolId)) {
            throw new ResourceNotFoundException("School not found with the Id: " + schoolId);
        }

        return ratingRepository.findById(ratingId).map(rating -> {
            rating.setSchool(_rating.getSchool());
            rating.setDressing(_rating.getDressing());
            rating.setMuslimPresence(_rating.getMuslimPresence());
            rating.setLeadership(_rating.getLeadership());
            rating.setBeardFriendliness(_rating.getBeardFriendliness());
            rating.setHijabFriendliness(_rating.getHijabFriendliness());
            rating.setHalalFoodOptions(_rating.getHalalFoodOptions());
            rating.setMuslimPresence(_rating.getMuslimPresence());
            rating.setWuduArea(_rating.getWuduArea());
            rating.setDormFriendliness(_rating.getDormFriendliness());
            return ratingRepository.save(rating);
        }).orElseThrow(() -> new ResolutionException("Rating not found with the Id: " + ratingId));
    }

    @Override
    public Rating deleteRating(Long ratingId, Long schoolId) throws ResourceNotFoundException {
        return ratingRepository.findByIdAndSchoolId(schoolId, ratingId).map(rating -> {
            ratingRepository.delete(rating);
            return rating;
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Rating not found with Id: " + ratingId + " and school Id: " + schoolId
        ));
    }

}
