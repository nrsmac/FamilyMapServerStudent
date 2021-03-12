package Services;

import DataAccess.*;
import GenerateData.GeneratePeople;
import Handlers.Json;
import Model.AuthToken;
import Model.Event;
import Model.User;
import Request.LoadRequest;
import Request.PersonsRequest;
import Response.PersonsResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class PersonsServiceTest {
    LoadRequest goodReq;
    private Database db;
    private User user1;
    private User user2;
    private UserDao uDao;
    private GeneratePeople genPeople;
    private EventDao eDao;
    private AuthTokenDao aDao;
    private Event e1;
    private Event e2;
    private String auth;
    private String eventID;
    @BeforeEach
    void setUp() throws DataAccessException, IOException {
        db = new Database();
        uDao = new UserDao(db.getConnection());
        eDao = new EventDao(db.getConnection());
        aDao = new AuthTokenDao(db.getConnection());
        uDao.clearUsers();
        eDao.clearEvents();
        aDao.clearTokens();

        db.closeConnection(true);

        String json = Files.readString(Path.of("passoffFiles/LoadData.json"));
        LoadRequest loadRequest = Json.deserialize(json,LoadRequest.class);
        LoadService loadService = new LoadService(loadRequest);
        System.out.println(loadService.getResponse().isSuccess());

        db = new Database();
        aDao = new AuthTokenDao(db.getConnection());
        AuthToken token = aDao.findByUsername("sheila");
        auth = token.getAuthToken();
        db.closeConnection(true);
    }

    @AfterEach
    void tearDown() throws DataAccessException {

    }

    @Test
    void testValidAuthtokenOnly() throws DataAccessException {
        PersonsRequest req = new PersonsRequest(auth);
        PersonsService service = new PersonsService(req);
        PersonsResponse response = service.getResponse();

        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertNotEquals(0,response.getData().size());
    }

    @Test
    void testValidTokenAndId() throws DataAccessException {
        PersonsRequest req = new PersonsRequest(auth,"Ken_Rodham");
        PersonsService service = new PersonsService(req);
        PersonsResponse response = service.getResponse();

        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertEquals("Ken_Rodham",response.getPersonID());
    }

    @Test
    void testValidTokenAndInvalidId() throws DataAccessException {
        PersonsRequest req = new PersonsRequest(auth,"K");
        PersonsService service = new PersonsService(req);
        PersonsResponse response = service.getResponse();

        assertNotNull(response);
        assertFalse(response.isSuccess());
    }

    @Test
    void testInvalidToken() throws DataAccessException {
        PersonsRequest req = new PersonsRequest("");
        PersonsService service = new PersonsService(req);
        PersonsResponse response = service.getResponse();

        assertNotNull(response);
        assertFalse(response.isSuccess());
    }
}
