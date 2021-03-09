package GenerateData;

import Model.Event;
import Model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GenerateEventsTest {
    GenerateEvents generatedEvents;
    GeneratePeople people;

    @BeforeEach
    void setUp() {
        people = new GeneratePeople(20,"nrsmac");
        generatedEvents = new GenerateEvents(people.getPersons());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void generateEvents() {
        ArrayList<Event> events = generatedEvents.getEvents();
        assertNotNull(events);
    }
}