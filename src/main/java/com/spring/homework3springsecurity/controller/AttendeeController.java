package com.spring.homework3springsecurity.controller;

import com.spring.homework3springsecurity.model.enity.Attendee;
import com.spring.homework3springsecurity.model.request.AttendeeRequest;
import com.spring.homework3springsecurity.model.response.ApiResponse;
import com.spring.homework3springsecurity.service.AttendeeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

//    GET ALL ATTENDEES

    @GetMapping
    @Operation(summary = "Get all Attendees")
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendees(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer size) {
        List<Attendee> attendees = attendeeService.getALlAttendees(page,size);
        ApiResponse <List<Attendee>> response = new ApiResponse<>(
                "Get all attendees",
                attendees,
                "Successfully",
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }

//    GET A ATTENDEE BY ID

    @GetMapping("/{attendeeId}")
    @Operation(summary = "Get a attendee by ID")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable Integer attendeeId){
        Attendee attendees = attendeeService.getAttendeeById(attendeeId);
        ApiResponse<Attendee> response = new ApiResponse<>(
                "Get a attendee by ID",
                attendees,
                "Successfully",
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);

    }
//    ADD AN ATTENDEE

    @PostMapping()
    @Operation(summary = "Add an Attendee")
    public ResponseEntity<ApiResponse<Attendee>> addAttendee(@Valid @RequestBody AttendeeRequest request){
        Attendee attendees = attendeeService.addAttendee(request);
        ApiResponse<Attendee> response = new ApiResponse<>(
                "Add an Attendee",
                attendees,
                "Successfully",
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }

//    UPDATE AN ATTENDEE

    @PutMapping("/{attendeeId}")
    @Operation(summary = "Update an Attendee")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendee(@PathVariable Integer attendeeId,@RequestBody AttendeeRequest request){
        Attendee attendees =attendeeService.updateAttendee(attendeeId,request);
        ApiResponse<Attendee> response = new ApiResponse<>(
                "Update an Attendee",
                attendees,
                "Successfully",
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }

//    DELETE AN ATTENDEE

    @DeleteMapping("/{attendeeId}")
    @Operation(summary = "Delete an Attendee")
    public ResponseEntity<ApiResponse<Attendee>> deleteAttendee(@PathVariable Integer attendeeId){
        attendeeService.deleteAttendeeById(attendeeId);
        ApiResponse<Attendee> response = new ApiResponse<>(
                "delete successfully",
                null,
                "successfully",
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
