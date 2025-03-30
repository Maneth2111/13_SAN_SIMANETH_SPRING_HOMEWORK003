package com.spring.homework3springsecurity.repository;


import com.spring.homework3springsecurity.model.enity.Attendee;
import com.spring.homework3springsecurity.model.request.AttendeeRequest;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface AttendeeRepo {

    @Select("""
    SELECT * FROM attendees
    offset  #{size} * (#{page}-1)
    limit   #{size}
    """)
    @Results(id = "attendees",value = {
            @Result(property = "attendeeId",column = "attendee_id"),
            @Result(property = "attendeeName",column = "attendee_name")
    })
    List<Attendee> getAllAttendees(Integer page,Integer size);

    @Select("""
    SELECT * FROM attendees a
    JOIN event_attendee ea ON a.attendee_id = ea.attendee_id
    WHERE ea.event_id = #{eventId}

    """)
    @ResultMap("attendees")
    Attendee getAttendeeById(Integer attendeeId);

    @Select("""
    INSERT INTO attendees(attendee_name, email)
    VALUES (#{attendeeName},#{email})
    RETURNING *
    """)
    @ResultMap("attendees")
    Attendee addAttendee(AttendeeRequest request);

    @Update("""
    UPDATE attendees
    SET  attendee_name =#{attendeeName} ,
         email= #{email}
    where attendees.attendee_id = 1;
    """)
    void updateAttendee(@RequestParam Integer attendeeId,@RequestParam("attendeeName")String attendeeName,@RequestParam("email") String email);


    @Delete("""
    DELETE FROM attendees
    WHERE attendee_id = #{attendeeId}
    """)
    void deleteAttendeeById(Integer attendeeId);

    @Insert("""
    INSERT INTO event_attendee (event_id, attendee_id)
    VALUES (#{eventId}, #{attendeeId})
    """)
    void addEventAttendee(@Param("eventId") Integer eventId, @Param("attendeeId") Integer attendeeId);

}


