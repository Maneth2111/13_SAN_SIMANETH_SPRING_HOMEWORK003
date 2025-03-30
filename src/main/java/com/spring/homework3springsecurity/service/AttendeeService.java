package com.spring.homework3springsecurity.service;

import com.spring.homework3springsecurity.model.enity.Attendee;
import com.spring.homework3springsecurity.model.request.AttendeeRequest;

import java.util.List;

public interface AttendeeService {
    List<Attendee> getALlAttendees(Integer page,Integer size);

    Attendee getAttendeeById(Integer attendeeId);

    Attendee addAttendee(AttendeeRequest request);

    Attendee updateAttendee(Integer attendeeId, AttendeeRequest request);

    void deleteAttendeeById(Integer attendeeId);
}
