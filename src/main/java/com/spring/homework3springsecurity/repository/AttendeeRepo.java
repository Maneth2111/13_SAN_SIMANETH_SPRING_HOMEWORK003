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
    """)
    @Results(id = "attendees",value = {
            @Result(property = "attendeeId",column = "attendee_id"),
            @Result(property = "attendeeName",column = "attendee_name")
    })
    List<Attendee> getAllAttendees();

    @Select("""
    SELECT * FROM attendees
    WHERE attendee_id = #{attendeeId} 
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
}
