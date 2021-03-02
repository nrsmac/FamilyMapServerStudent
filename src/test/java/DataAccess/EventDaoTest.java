package DataAccess;

import GenerateData.GenerateEvents;
import GenerateData.GeneratePeople;
import Model.Event;
import Model.Person;
import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EventDaoTest {
    private Database db;

    User user1;
    User user2;

    private Person person1;
    private Person person2;

    private Event e1;
    private Event e2;
    private Event e3;

    private EventDao eDao;

    @BeforeEach
    void setUp() throws DataAccessException, FileNotFoundException {
        db = new Database();

        User user1 = new User("777",
                "nrsmac",
                "4821",
                "shaun@shaun.com",
                "Shaun",
                "the Sheep",
                "m");
        User user2 = new User("888",
                "bella",
                "4821",
                "shaun@shaun.com",
                "Shaun",
                "the Sheep",
                "m");
        User user3 = new User("999",
                "bella",
                "4821",
                "shaun@shaun.com",
                "Shaun",
                "the Sheep",
                "m");


        GeneratePeople people = new GeneratePeople(20);
        GenerateEvents events = new GenerateEvents(people.getPersons());

        e1 = events.getEvents().get(0);
        e2 = events.getEvents().get(20);
        e3 = events.getEvents().get(30);

        Connection conn = db.getConnection();
        db.clearTables();
        UserDao uDao = new UserDao(conn);
        uDao.insertUser(user1);
        uDao.insertUser(user2);
        uDao.insertUser(user3);

        PersonDao pDao = new PersonDao(conn);
        for(Person p : people.getPersons()){
            pDao.insertPerson(p);
        }

        eDao = new EventDao(conn);
    }

    @AfterEach
    void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    void insertEvent() throws DataAccessException {
        eDao.insertEvent(e1);
        Event test = eDao.findByEventId(e1.getEvent_id());
        assertNotNull(test);
        assertEquals(e1, test);
    }

    @Test
    void findByEventId() throws DataAccessException {
        eDao.insertEvent(e1);
        eDao.insertEvent(e2);
        Event test1 = eDao.findByEventId(e1.getEvent_id());
        Event test2 = eDao.findByEventId(e2.getEvent_id());
        assertNotNull(test1);
        assertNotNull(test2);
        assertEquals(e1, test1);
        assertEquals(e2, test2);
    }

    @Test
    void findByUser() {
    }

    @Test
    void findByPersonId() {
    }

    @Test
    void clearEvents() {
    }

    @Test
    void getEventCount() {
    }
}