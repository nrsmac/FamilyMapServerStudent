package Services;

import DataAccess.*;
import GenerateData.GeneratePeople;
import Handlers.Json;
import Model.Event;
import Model.User;
import Request.EventRequest;
import Request.LoadRequest;
import Response.EventResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;


public class EventServiceTest {



    EventRequest goodReq;
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
        uDao.clearUsers();
        eDao.clearEvents();
        aDao = new AuthTokenDao(db.getConnection());

        aDao.clearTokens();
        db.closeConnection(true);

        String json = Files.readString(Path.of("passoffFiles/LoadData.json"));
        LoadRequest loadRequest = Json.deserialize(json,LoadRequest.class);
        LoadService loadService = new LoadService(loadRequest);
        System.out.println(loadService.getResponse().isSuccess());

        db = new Database();
        aDao = new AuthTokenDao(db.getConnection());
        auth = aDao.findByUsername("sheila").getAuthToken();
        db.closeConnection(true);




    }

    @AfterEach
    void tearDown() throws DataAccessException {
    }


    @Test
    void testWithGoodAuthRequest() throws DataAccessException {
        goodReq = new EventRequest(auth);
        EventService service = new EventService(goodReq);
        EventResponse response = service.getResponse();
        assertNotNull(response);
        assertTrue(response.isSuccess());
    }

    @Test
    void testWithGoodIdRequest() throws DataAccessException {
        goodReq = new EventRequest(auth,eventID);
        EventService service = new EventService(goodReq);
        EventResponse response = service.getResponse();
        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertNotEquals(0,response.getData().size());
    }

    @Test
    void testWithBadIdRequest() throws DataAccessException {
        goodReq = new EventRequest(auth,"");
        EventService service = new EventService(goodReq);
        EventResponse response = service.getResponse();
        assertNotNull(response);
        assertFalse(response.isSuccess());
    }

    @Test
    void testWithBadAuthRequest() throws DataAccessException {
        goodReq = new EventRequest("");
        EventService service = new EventService(goodReq);
        EventResponse response = service.getResponse();
        assertNotNull(response);
        assertFalse(response.isSuccess());

    }


}
