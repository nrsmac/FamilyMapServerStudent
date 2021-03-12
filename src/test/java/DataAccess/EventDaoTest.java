package DataAccess;

import GenerateData.GeneratePeople;
import Model.Event;
import Model.Person;
import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class EventDaoTest {
    private Database db;

    Event e1;
    Event e2;
    Event e3;

    User user1;
    User user2;

    private Person person1;
    private Person person2;

    private EventDao eDao;

    @BeforeEach
    void setUp() throws DataAccessException, FileNotFoundException {
        db = new Database();

        User user1 = new User("777",
                "nrsmac",
                "4821",
                "shaun@shaun.com",
                "Noah",
                "Schill",
                "m");

        User user2 = new User("888",
                "bella",
                "4821",
                "shaun@shaun.com",
                "Shaun",
                "the Sheep",
                "m");

        Person userPerson1 = new Person(user1.getPersonID(),
                user1.getUsername(),
                user1.getFirstName(),
                user1.getLastName(),
                user1.getGender());
        Person userPerson2 = new Person(user2.getPersonID(),
                user2.getUsername(),
                user2.getFirstName(),
                user2.getLastName(),
                user2.getGender());

        Connection conn = db.getConnection();

        UserDao uDao = new UserDao(conn);
        uDao.insertUser(user1);
        uDao.insertUser(user2);

        GeneratePeople genPeople = new GeneratePeople();
        HashMap<String, HashSet<?>> data = genPeople.generations(userPerson1, 4);
        HashSet<Event> events = (HashSet<Event>) data.get("events");

        eDao = new EventDao(conn);

        genPeople = new GeneratePeople();
        data = genPeople.generations(userPerson2, 4);
        events = (HashSet<Event>) data.get("events");
        Iterator<Event> it = events.iterator();
        e1 = it.next();
        e2 = it.next();

    }

    @AfterEach
    void tearDown() throws DataAccessException {
        db.clearTables();
        db.closeConnection(false);
    }

    @Test
    void insertEvent() throws DataAccessException {
        eDao.insertEvent(e1);
        Event test = eDao.findByEventId(e1.getEventID());
        assertNotNull(test);
        assertEquals(e1, test);
    }

    @Test
    void findByEventIdPass() throws DataAccessException {
        eDao.insertEvent(e1);
        eDao.insertEvent(e2);
        Event test1 = eDao.findByEventId(e1.getEventID());
        Event test2 = eDao.findByEventId(e2.getEventID());
        assertNotNull(test1);
        assertNotNull(test2);
        assertEquals(e1, test1);
        assertEquals(e2, test2);
        assertNotEquals(e1,test2);
    }
    @Test
    void findByEventIdFail() throws DataAccessException {
        eDao.insertEvent(e1);

        assertThrows(DataAccessException.class, () -> {
            eDao.insertEvent(e1);
        });
        assertThrows(DataAccessException.class, () -> {
            Event test2 = eDao.findByEventId(e2.getEventID());
        });
        Event test1 = eDao.findByEventId(e1.getEventID());
        assertNotNull(test1);
        assertEquals(e1, test1);
    }

    @Test
    void findByUserPass() throws DataAccessException {

        User user2 = new User("888",
                "bella",
                "4821",
                "shaun@shaun.com",
                "Shaun",
                "the Sheep",
                "m");
        db.clearTables();

        eDao.insertEvent(e1);
        eDao.insertEvent(e2);
        ArrayList<Event> bellaEvents = eDao.findByUser(user2.getUsername());
        assertNotNull(bellaEvents);
        assertEquals(2, bellaEvents.size());

    }

    @Test
    void findByUserFail() throws DataAccessException {
        User user1 = new User("777",
                "nrsmac",
                "4821",
                "shaun@shaun.com",
                "Noah",
                "Schill",
                "m");

        User user2 = new User("888",
                "bella",
                "4821",
                "shaun@shaun.com",
                "Shaun",
                "the Sheep",
                "m");
        db.clearTables();

        eDao.insertEvent(e1);
        eDao.insertEvent(e2);
        assertThrows(DataAccessException.class, () -> {
            ArrayList<Event> nrsmacEvents = eDao.findByUser("nrsmac");
        });
        ArrayList<Event> bellaEvents = eDao.findByUser(user2.getUsername());
        assertNotNull(bellaEvents);
        assertEquals(2,bellaEvents.size());

    }


    @Test
    void clearEvents() throws DataAccessException {
        eDao.insertEvent(e1);
        eDao.insertEvent(e2);
        assertEquals(2,eDao.getEventCount());
        eDao.clearEvents();
        assertEquals(0, eDao.getEventCount());
    }
}