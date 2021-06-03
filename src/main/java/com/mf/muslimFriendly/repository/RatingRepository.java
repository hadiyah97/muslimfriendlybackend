package com.mf.muslimFriendly.repository;

import com.mf.muslimFriendly.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Set<Rating> findBySchoolId(Long schoolId);
    Optional<Rating> findByIdAndSchoolId(Long ratingId, Long schoolId);

    @Query(value = "select avg(r.hijab_friendliness) from rating r where r.school_id = :schoolId", nativeQuery = true)
    long getAvgRating(Long schoolId);

}
