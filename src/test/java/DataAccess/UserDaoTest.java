package DataAccess;

import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    private Database db;
    private User bestUser;
    private UserDao uDao;

    @BeforeEach
    void setUp() throws DataAccessException {
        db = new Database();
        bestUser = new User("2659", "nrsmac", "Nrsmac12", "noahrschill@gmail.com", "Noah", "Schill", "m");
        Connection conn = db.getConnection();
        db.clearTables();
        uDao = new UserDao(conn);
    }

    @AfterEach
    void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    void insertPass() throws DataAccessException {
        uDao.insertUser(bestUser);
        User compareTest = uDao.find(bestUser.getPersonId());
        assertNotNull(compareTest);
        assertEquals(bestUser, compareTest);
    }

    @Test
    void insertFail() throws DataAccessException {
        uDao.insertUser(bestUser);
        assertThrows(DataAccessException.class, ()-> uDao.insertUser(bestUser));
    }


    @Test
    void findPass() throws DataAccessException {
        uDao.insertUser(bestUser);
        User test = uDao.find(bestUser.getPersonId());
        assertNotNull(test);
        assertEquals(bestUser, test);
    }

    @Test
    void findFail() throws DataAccessException {
        User userNotThere = new User("1234","s2cool","4821","shaun@shaun.com","Shaun","the Sheep","m");
        assertThrows(DataAccessException.class, ()-> uDao.find(userNotThere.getPersonId()));
    }

    @Test
    void getUserCount() throws DataAccessException{
        assertEquals(uDao.getUserCount(),0);
        User userNotThere = new User("1234","s2cool","4821","shaun@shaun.com","Shaun","the Sheep","m");
        uDao.insertUser(bestUser);
        uDao.insertUser(userNotThere);
        assertEquals(uDao.getUserCount(), 2);
    }

    @Test
    void clearUsers() throws DataAccessException {
        User user1 = new User("1234","s2cool","4821","shaun@shaun.com","Shaun","the Sheep","m");
        uDao.insertUser(bestUser);
        uDao.insertUser(user1);
        assertEquals(uDao.getUserCount(),2);
        uDao.clearUsers();
        assertEquals(uDao.getUserCount(),0);
    }
}