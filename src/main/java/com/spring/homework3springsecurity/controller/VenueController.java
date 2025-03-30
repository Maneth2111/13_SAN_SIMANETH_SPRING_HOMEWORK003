package com.spring.homework3springsecurity.controller;

import com.spring.homework3springsecurity.model.enity.Venue;
import com.spring.homework3springsecurity.model.request.VenuesRequest;
import com.spring.homework3springsecurity.model.response.ApiResponse;
import com.spring.homework3springsecurity.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }


//    GET ALL VENUE

    @GetMapping
    @Operation(summary = "get all venues")
    public ResponseEntity<ApiResponse<List<Venue>>>  getAllVenues(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer size) {
        List<Venue> venues = venueService.getAllVenues(page,size);
        ApiResponse<List<Venue>> response = new ApiResponse<>("get All venues",venues,"Successfully",LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//  GET ALL VENUE BY ID

    @GetMapping("/{venueId}")
    @Operation(summary = "get venue by ID")
    public ResponseEntity<ApiResponse<Venue>> getVenuesById(@PathVariable Integer venueId) {
        Venue venues = venueService.getVenuesById(venueId);
        ApiResponse<Venue> response = new ApiResponse<>(
                "Get venues by id",
                venues,
                "Successfully",
                LocalDateTime.now()

        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    ADD NEW VENUES

    @PostMapping
    @Operation(summary = "Add a venue")
    public ResponseEntity<ApiResponse<Venue>> addNewVenue(@Valid @RequestBody VenuesRequest request) {
        Venue venues = venueService.addNewVenue(request);
        ApiResponse<Venue> response = new ApiResponse<>(
                "Add new Venue",
                venues,
                "Add successfully",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    UPDATE VENUE

    @PutMapping("/{venueId}")
    @Operation(summary = "Update a venue")
    public ResponseEntity<ApiResponse<Venue>> updateVenue(@PathVariable Integer venueId,@RequestBody VenuesRequest request) {
        Venue venues = venueService.updateVenue(venueId,request);
        ApiResponse<Venue> response = new ApiResponse<>(
                "update successfully",
                venues,
                "successfully",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    DELETE A VENUE

    @DeleteMapping("/{venueId}")
    @Operation(summary = "Delete a venue by ID")
    public ResponseEntity<ApiResponse<Venue>> deleteVenue(@PathVariable Integer venueId) {
        venueService.deleteVenueById(venueId);
        ApiResponse<Venue> response = new ApiResponse<>(
                "delete successfully",
                null,
                "successfully",
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
