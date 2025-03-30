package com.spring.homework3springsecurity.service;

import com.spring.homework3springsecurity.model.enity.Event;
import com.spring.homework3springsecurity.model.request.EventRequest;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(Integer page, Integer size);

    Event createNewEvent(EventRequest request);

    Event getEventById(Integer eventId);

    Event updateEventById(Integer eventId,EventRequest request);

    void deleteEventById(Integer eventId);
}
