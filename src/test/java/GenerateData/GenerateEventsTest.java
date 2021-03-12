package GenerateData;

import Model.Event;
import Model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class GenerateEventsTest {
    GenerateEvents generatedEvents;
    GeneratePeople people;

    @BeforeEach
    void setUp() {
        people = new GeneratePeople();
        generatedEvents = new GenerateEvents();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void generateEvents() {
//        TreeSet<Event> events = people.getEvents();
//        assertNotNull(events);
    }
}