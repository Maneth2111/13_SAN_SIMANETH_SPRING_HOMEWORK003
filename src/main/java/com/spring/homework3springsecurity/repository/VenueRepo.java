package com.spring.homework3springsecurity.repository;


import com.spring.homework3springsecurity.model.enity.Venue;
import com.spring.homework3springsecurity.model.request.VenuesRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepo {

    @Select("""
    SELECT * FROM venues
    offset  #{size} * (#{page}-1)
    limit   #{size}
    """)
    @Results(id = "venues",value ={
            @Result(property = "venueId",column = "venue_id"),
            @Result(property = "venueName",column = "venue_name")
    })
    List<Venue> getAllVenues(Integer page,Integer size);


    @Select("""
    SELECT * FROM venues
    WHERE venue_id=#{venueId}
    """)
    @ResultMap("venues")
    Venue getVenueById(Integer id);

    @Select("""
    INSERT INTO venues (venue_name, location)
    VALUES (#{venueName}, #{location})
    RETURNING *
    """)
    @ResultMap("venues")
    Venue addNewVenue(VenuesRequest request);


    // UPDATE A VENUE
    @Update("""
    UPDATE venues
    SET venue_name = #{venueName},
        location = #{location}
    WHERE venue_id = #{venueId}
    RETURNING *
    """)
    @ResultMap("venues")
    void updateVenue(@Param("venueId") Integer venueId, @Param("venueName") String venueName, @Param("location") String location);


    @Delete("""
    DELETE FROM venues
    WHERE venue_id = #{venueId}
    """)
    @ResultMap("venues")
    void deleteVenueById(Integer venueId);
}
