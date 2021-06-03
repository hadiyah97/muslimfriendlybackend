package com.mf.muslimFriendly.services;

import com.mf.muslimFriendly.exception.ResourceNotFoundException;
import com.mf.muslimFriendly.model.Rating;

import java.util.Set;

public interface RatingService {

    Set<Rating> getRatingsBySchool(Long schoolId);

    Rating createRating(Long schoolId, Rating rating) throws ResourceNotFoundException;

    Rating updateRating(Long schoolId, Long ratingId, Rating rating) throws ResourceNotFoundException;

    Rating deleteRating(Long schoolId, Long ratingId) throws ResourceNotFoundException;

}
