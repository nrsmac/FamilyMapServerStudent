package GenerateData;

import Model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GeneratePeopleTest {
    GeneratePeople generatePeople;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        generatePeople = new GeneratePeople(4, "nrsmac");
        ArrayList<Person> persons = generatePeople.getPersons();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void generatePersons() {

    }
}