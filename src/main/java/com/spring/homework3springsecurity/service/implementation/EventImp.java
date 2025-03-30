package com.spring.homework3springsecurity.service.implementation;


import com.spring.homework3springsecurity.model.enity.Event;
import com.spring.homework3springsecurity.model.request.EventRequest;
import com.spring.homework3springsecurity.repository.AttendeeRepo;
import com.spring.homework3springsecurity.repository.EventRepo;
import com.spring.homework3springsecurity.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventImp implements EventService {

    private final EventRepo eventRepo;

    public EventImp(EventRepo eventRepo, AttendeeRepo attendeeRepo) {
        this.eventRepo = eventRepo;
        this.attendeeRepo = attendeeRepo;
    }
    private final AttendeeRepo attendeeRepo;

    @Override
    public List<Event> getAllEvents(Integer page, Integer size) {
        return eventRepo.getAllEvents(page,size);
    }

    @Override
    public Event createNewEvent(EventRequest request) {

        Integer eventId = eventRepo.addNewEvent(request).getEventId();

        for (Integer attendeeId : request.getAttendeesId()) {
            attendeeRepo.addEventAttendee(eventId, attendeeId);
        }
        return eventRepo.getEventById(eventId);
    }

    @Override
    public Event getEventById(Integer eventId) {
        if (eventRepo.getEventById(eventId) == null) {
            throw new RuntimeException("Event not found with ID: " + eventId);
        }
        return eventRepo.getEventById(eventId);
    }

    @Override
    public Event updateEventById(Integer eventId, EventRequest request) {
        Event event = eventRepo.getEventById(eventId);
        if (event == null) {
            throw new RuntimeException("Event not found with ID: " + eventId);
        }
        eventRepo.updateEvent(eventId, request);
        eventRepo.deleteEventAttendees(eventId);
        for (Integer attendeeId : request.getAttendeesId()) {
            eventRepo.addEventAttendee(eventId, attendeeId);
        }
        return eventRepo.getEventById(eventId);
    }

    @Override
    public void deleteEventById(Integer eventId) {
        eventRepo.deleteEventAttendees(eventId);
    }


}
