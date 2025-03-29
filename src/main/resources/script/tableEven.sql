CREATE DATABASE homework3;

CREATE TABLE venues (
                        venue_id SERIAL PRIMARY KEY,
                        venue_name VARCHAR(100) NOT NULL,
                        location TEXT NOT NULL
);

CREATE TABLE events (
                        event_id SERIAL PRIMARY KEY,
                        event_name VARCHAR(100) NOT NULL,
                        event_date DATE NOT NULL,
                        venue_id INT,
                        FOREIGN KEY (venue_id) REFERENCES venues(venue_id) ON DELETE SET NULL
);

CREATE TABLE attendees (
                           attendee_id SERIAL PRIMARY KEY,
                           attendee_name VARCHAR(100) NOT NULL,
                           email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE event_attendee (
                                id SERIAL PRIMARY KEY,
                                event_id INT NOT NULL,
                                attendee_id INT NOT NULL,
                                FOREIGN KEY (event_id) REFERENCES events(event_id) ON DELETE CASCADE,
                                FOREIGN KEY (attendee_id) REFERENCES attendees(attendee_id) ON DELETE CASCADE,
                                UNIQUE (event_id, attendee_id)
);