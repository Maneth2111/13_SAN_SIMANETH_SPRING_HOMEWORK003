package com.spring.homework3springsecurity.service.implementation;


import com.spring.homework3springsecurity.model.enity.Venue;
import com.spring.homework3springsecurity.model.request.VenuesRequest;
import com.spring.homework3springsecurity.repository.VenueRepo;
import com.spring.homework3springsecurity.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueImp implements VenueService {
    private  final VenueRepo venueRepo;

    public VenueImp(VenueRepo venueRepo) {
        this.venueRepo = venueRepo;
    }


    @Override
    public List<Venue> getAllVenues(Integer page, Integer size) {
        return venueRepo.getAllVenues(page,size);
    }

    @Override
    public Venue getVenuesById(Integer id) {

        return venueRepo.getVenueById(id);
    }

    @Override
    public Venue addNewVenue(VenuesRequest request) {
        return venueRepo.addNewVenue(request);
    }

    @Override
    public Venue updateVenue(Integer venueId, VenuesRequest request) {
        Venue venue = venueRepo.getVenueById(venueId);
        venue.setVenueName(request.getVenueName());
        venue.setLocation(request.getLocation());

        venueRepo.updateVenue(venueId, request.getVenueName(), request.getLocation());

        return venue;
    }

    @Override
    public void deleteVenueById(Integer venueId) {
         venueRepo.deleteVenueById(venueId);
    }
}
