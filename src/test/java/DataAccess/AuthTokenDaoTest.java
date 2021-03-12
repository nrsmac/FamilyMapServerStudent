package DataAccess;

import Model.AuthToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTokenDaoTest {
    private Database db;
    private AuthToken bestToken;
    private AuthTokenDao aDao;

    @BeforeEach
    void setUp() throws DataAccessException {
        db = new Database();
        bestToken = new AuthToken("nrsmac","123");
        Connection conn = db.getConnection();
        db.clearTables();
        aDao = new AuthTokenDao(conn);
    }

    @AfterEach
    void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    void add() throws DataAccessException {
        aDao.add(bestToken);
        AuthToken compareTest = aDao.findByAuthtoken(bestToken.getAuthToken());
        assertNotNull(compareTest);
        assertEquals(bestToken,compareTest);
    }

    @Test
    void findByUsername() throws DataAccessException {
        aDao.add(bestToken);
        AuthToken compareTest = aDao.findByUsername(bestToken.getAssociatedUsername());
        assertNotNull(compareTest);
        assertEquals(bestToken,compareTest);
    }

    @Test
    void findByAuthtoken() throws DataAccessException {
        aDao.add(bestToken);
        AuthToken compareTest = aDao.findByAuthtoken(bestToken.getAuthToken());
        assertNotNull(compareTest);
        assertEquals(bestToken,compareTest);
    }

    @Test
    void clearTokens() throws DataAccessException {
        aDao.add(bestToken);
        aDao.clearTokens();
        assertThrows(DataAccessException.class, ()-> aDao.findByUsername(bestToken.getAssociatedUsername()));
        assertThrows(DataAccessException.class, ()-> aDao.findByAuthtoken(bestToken.getAuthToken()));
    }

    @Test
    void addFail() throws DataAccessException {
        aDao.add(bestToken);
        assertThrows(DataAccessException.class, ()-> aDao.add(bestToken));
    }

    @Test
    void findByUsernameFail() {
        assertThrows(DataAccessException.class, ()-> aDao.findByUsername("abc"));
    }

    @Test
    void findByAuthtokenFail() {
        assertThrows(DataAccessException.class, ()-> aDao.findByUsername("abc"));
    }
}
