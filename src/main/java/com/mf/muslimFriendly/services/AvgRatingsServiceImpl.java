package com.mf.muslimFriendly.services;

import com.mf.muslimFriendly.model.AvgRatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;


@Service
public class AvgRatingsServiceImpl implements AvgRatingsService {

    private static final String AVG_RATING_BY_SCHOOL_ID = "select avg(dressing),"
            + " avg(muslim_presence),"
            + " avg(staff_muslim_presence)"
            + " from rating where school_id = ?";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public AvgRatingDTO getAvgRatingsBySchool(Long schoolId) {
        return jdbcTemplate.queryForObject(
                AVG_RATING_BY_SCHOOL_ID,
                new Object[]{schoolId},
                new RowMapper<AvgRatingDTO>() {
                    @Override
                    public AvgRatingDTO mapRow(ResultSet rs, int i) throws SQLException {
                        AvgRatingDTO avgRatingDTO = new AvgRatingDTO();
                        avgRatingDTO.setDressing(rs.getDouble(1));
                        avgRatingDTO.setMuslimPresence(rs.getDouble(2));
                        avgRatingDTO.setStaffMuslimPresence(rs.getDouble(3));
                        return avgRatingDTO;
                    }
                });
    }

}
