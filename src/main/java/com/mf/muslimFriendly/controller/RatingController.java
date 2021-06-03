package com.mf.muslimFriendly.controller;

import com.mf.muslimFriendly.exception.ResourceNotFoundException;
import com.mf.muslimFriendly.model.Rating;
import com.mf.muslimFriendly.services.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/schools/{schoolId}/ratings")
    public Rating createRating(@PathVariable(value = "schoolId") Long schoolId,
                               @Valid @RequestBody Rating rating) throws ResourceNotFoundException {
        return ratingService.createRating(schoolId, rating);
    }

    @PutMapping("/schools/{schoolId}/ratings/{ratingId}")
    public Rating updateRating(@PathVariable(value = "schoolId") Long schoolId,
                               @PathVariable(value = "ratingId") Long ratingId,
                               @Valid @RequestBody Rating rating) throws ResourceNotFoundException {
        return ratingService.updateRating(schoolId, ratingId, rating);
    }

    @DeleteMapping("/schools/{schoolId}/rating/{ratingId}")
    public Rating deleteRating(@PathVariable(value = "schoolId") Long schoolId,
                               @PathVariable(value = "ratingId") Long ratingId) throws ResourceNotFoundException {
        return ratingService.deleteRating(schoolId, ratingId);
    }
}
