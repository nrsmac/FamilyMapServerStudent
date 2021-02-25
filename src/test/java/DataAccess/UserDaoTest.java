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
        User compareTest = uDao.find(bestUser.getUser_id());
        assertNotNull(compareTest);
        assertEquals(bestUser, compareTest);
    }

    @Test
    void retrieveUsers() {
        //TODO Implement
    }

    @Test
    void find() {
        //TODO Implement
    }

    @Test
    void clearUsers() {
        //TODO Implement
    }
}