package com.spring.homework3springsecurity.service;

import com.spring.homework3springsecurity.model.enity.Venue;
import com.spring.homework3springsecurity.model.request.VenuesRequest;

import java.util.List;

public interface VenueService {

    List<Venue> getAllVenues(Integer page, Integer size);

    Venue getVenuesById(Integer id);

    Venue addNewVenue(VenuesRequest request);

    Venue updateVenue(Integer venueId, VenuesRequest request);

    void deleteVenueById(Integer venueId);
}
