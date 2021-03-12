package GenerateData;

import Model.Person;
import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GeneratePeopleTest {
    GeneratePeople generatePeople;
    TreeSet<Person> persons;
    TreeSet<Model.Event> events;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        User user1 = new User("777",
                "nrsmac",
                "4821",
                "shaun@shaun.com",
                "Noah",
                "Schill",
                "m");

        Person userPerson1 = new Person(user1.getPersonID(),user1.getUsername(),user1.getFirstName(),user1.getLastName(),user1.getGender());

        generatePeople = new GeneratePeople();
//        generatePeople.generations(userPerson1,4);
//
//        persons = generatePeople.getPersons();
//        events = generatePeople.getEvents();
    }


    @AfterEach
    void tearDown() {

    }

    @Test
    void generatePersons() {
        assertNotNull(this.persons);
        assertNotNull(this.events);
    }
}