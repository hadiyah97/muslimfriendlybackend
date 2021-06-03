package com.mf.muslimFriendly.services;

import com.mf.muslimFriendly.model.AvgRatingDTO;

public interface AvgRatingsService {
    AvgRatingDTO getAvgRatingsBySchool(Long schoolId);
}
