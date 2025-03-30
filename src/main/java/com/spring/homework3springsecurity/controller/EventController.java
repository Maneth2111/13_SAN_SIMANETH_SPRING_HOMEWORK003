package com.spring.homework3springsecurity.controller;

import com.spring.homework3springsecurity.model.enity.Attendee;
import com.spring.homework3springsecurity.model.enity.Event;
import com.spring.homework3springsecurity.model.request.AttendeeRequest;
import com.spring.homework3springsecurity.model.request.EventRequest;
import com.spring.homework3springsecurity.model.response.ApiResponse;
import com.spring.homework3springsecurity.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

//    GET ALL EVENTS
    @GetMapping
    @Operation(summary = "Get All Event")
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size) {
        List<Event> events = eventService.getAllEvents(page,size);
        ApiResponse <List<Event>> response = new ApiResponse<>(
                "Get All Events",
                events,
                "Get all successfully",
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }

//    ADD NEW EVENT

    @PostMapping
    @Operation(summary = "Add New Event")
    public ResponseEntity<ApiResponse<Event>> createNewEvent(@Valid @RequestBody EventRequest  request){
        Event events = eventService.createNewEvent(request);
        ApiResponse<Event> response = new ApiResponse<>(
                "Add an Attendee",
                events,
                "Successfully",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    GET A EVENT BY ID
    @GetMapping("/{eventId}")
    @Operation(summary = "Get an Event By ID")
    public ResponseEntity<ApiResponse<Event>> getEventById(@PathVariable Integer eventId){
        Event events = eventService.getEventById(eventId);
        ApiResponse<Event> response = new ApiResponse<>(
                "Add an Attendee",
                events,
                "Successfully",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

//    UPDATE EVENT
    @PutMapping("/{eventId}")
    @Operation(summary = "Update an Event")
    public ResponseEntity<ApiResponse<Event>> updateEvent(@PathVariable Integer eventId, @RequestBody EventRequest request){
        Event events = eventService.updateEventById(eventId,request);
        ApiResponse<Event> response = new ApiResponse<>(
                "Add an Attendee",
                events,
                "Successfully",
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }

//    DELETE AN EVENT

    @DeleteMapping("/{eventId}")
    @Operation(summary = "Delete an Event by ID")
    public ResponseEntity<ApiResponse<Event>> deleteEventById(@PathVariable Integer eventId){
        eventService.deleteEventById(eventId);
        ApiResponse<Event> response = new ApiResponse<>(
                "Delete an Event",
                null,
                "Deleted Successfully",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
