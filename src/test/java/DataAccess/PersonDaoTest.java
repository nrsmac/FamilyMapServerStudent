package DataAccess;

import Model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class PersonDaoTest {
    private Database db;
    private Person person1;
    private Person person2;
    private Person person3;
    private Person person4;
    private PersonDao pDao;

    @BeforeEach
    void setUp() throws DataAccessException {
        db = new Database();
        person1 = new Person("1111",
                "nrsmac",
                "Ann",
                "Newling",
                "f",
                "5432",
                "3345",
                "Isaac Hunt");
        person2 = new Person("2222",
                "nrsmac",
                "Gary",
                "Coleman",
                "m",
                null,
                null,
                null);
        person3 = new Person("3333",
                "fabs",
                "Men",
                "Mae",
                "n",
                null,
                null,
                null);
        person4 = new Person("4444",
                "fred",
                "mex",
                "leg",
                "f",
                null,
                null,
                null);
        Connection conn = db.getConnection();
        db.clearTables();
        pDao = new PersonDao(conn);
    }

    @AfterEach
    void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    void insertPerson() throws DataAccessException {
        pDao.insertPerson(person1);
        Person test = pDao.find(person1.getPersonID());
        assertNotNull(test);
        assertEquals(person1, test);
    }

    @Test
    void insertPersonFail() throws DataAccessException {
        pDao.insertPerson(person1);
        assertThrows(DataAccessException.class, ()-> pDao.insertPerson(person1));
    }

    @Test
    void find() throws DataAccessException {
        pDao.insertPerson(person1);
        Person test = pDao.find(person1.getPersonID());
        assertNotNull(test);
        assertEquals(person1, test);
    }

    @Test
    void findFail() throws DataAccessException {
        Person personNotThere = person4;
        assertThrows(DataAccessException.class, ()-> pDao.find(personNotThere.getPersonID()));
    }

    @Test
    void clearPersons() throws DataAccessException {
        pDao.insertPerson(person1);
        pDao.insertPerson(person2);
        assertEquals(pDao.getPersonCount(), 2);
        pDao.clearPersons();
        assertEquals(pDao.getPersonCount(),0);
    }

    @Test
    void getPersonCount() throws DataAccessException {
        assertEquals(pDao.getPersonCount(),0);
        Person personNotThere = person4;
        pDao.insertPerson(person1);
        pDao.insertPerson(person2);
        assertEquals(pDao.getPersonCount(), 2);
    }
}