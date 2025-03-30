package com.spring.homework3springsecurity.repository;


import com.spring.homework3springsecurity.model.enity.Event;
import com.spring.homework3springsecurity.model.request.EventRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventRepo {
    @Select("""
    SELECT * FROM events
    offset  #{size} * (#{page}-1)
    limit   #{size}
    """)
    @Results(id = "events", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venues", column = "venue_id",
                    many = @Many(select = "com.spring.homework3springsecurity.repository.VenueRepo.getVenueById")),
            @Result(property = "attendees", column = "event_id",
                    many = @Many(select = "com.spring.homework3springsecurity.repository.AttendeeRepo.getAttendeeById"))
    })

    List<Event> getAllEvents(Integer page, Integer size);
    @Select("""
    INSERT INTO events (event_name, event_date, venue_id)
    VALUES (#{eventName}, #{eventDate}, #{venueId})
    RETURNING event_id
    """)
    @ResultMap("events")
    Event addNewEvent(EventRequest request);

    @Insert("""
    INSERT INTO event_attendee (event_id, attendee_id)
    VALUES (#{eventId}, #{attendeeId})
    """)
    void addEventAttendee(@Param("eventId") Integer eventId, @Param("attendeeId") Integer attendeeId);

    @Select("""
    SELECT * FROM events
    WHERE event_id = #{eventId}
    """)
    @ResultMap("events")
    Event getEventById(@Param("eventId") Integer eventId);

    @Delete("""
    DELETE FROM event_attendee WHERE event_id = #{eventId}
    """)
    void deleteEventAttendees(@Param("eventId") Integer eventId);


    @Update("""
    UPDATE events
    SET event_name = #{request.eventName}, 
        event_date = #{request.eventDate}, 
        venue_id = #{request.venueId}
    WHERE event_id = #{eventId}
    """)
    void updateEvent(@Param("eventId") Integer eventId, @Param("request") EventRequest request);

}


