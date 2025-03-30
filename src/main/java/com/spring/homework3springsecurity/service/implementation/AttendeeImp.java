package com.spring.homework3springsecurity.service.implementation;


import com.spring.homework3springsecurity.model.enity.Attendee;
import com.spring.homework3springsecurity.model.request.AttendeeRequest;
import com.spring.homework3springsecurity.repository.AttendeeRepo;
import com.spring.homework3springsecurity.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeImp implements AttendeeService {
    private final AttendeeRepo attendeeRepo;

    public AttendeeImp(AttendeeRepo attendeeRepo) {
        this.attendeeRepo = attendeeRepo;
    }

    @Override
    public List<Attendee> getALlAttendees(Integer page, Integer size) {
        return attendeeRepo.getAllAttendees(page,size);
    }

    @Override
    public Attendee getAttendeeById(Integer attendeeId) {
        return attendeeRepo.getAttendeeById(attendeeId);
    }

    @Override
    public Attendee addAttendee(AttendeeRequest request) {
        return attendeeRepo.addAttendee(request);
    }

    @Override
    public Attendee updateAttendee(Integer attendeeId, AttendeeRequest request) {
        Attendee attendee = attendeeRepo.getAttendeeById(attendeeId);
        attendee.setAttendeeName(request.getAttendeeName());
        attendee.setEmail(request.getEmail());
        attendeeRepo.updateAttendee(attendeeId, request.getAttendeeName(),request.getEmail());
        return attendee;
    }

    @Override
    public void deleteAttendeeById(Integer attendeeId) {
        attendeeRepo.deleteAttendeeById(attendeeId);

    }
}
