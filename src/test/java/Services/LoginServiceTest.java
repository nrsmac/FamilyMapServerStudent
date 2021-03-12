package Services;

import DataAccess.*;
import GenerateData.GeneratePeople;
import Handlers.Json;
import Model.Event;
import Model.User;
import Request.LoadRequest;
import Request.LoginRequest;
import Response.LoginResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {
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

    }

    @AfterEach
    void tearDown() throws DataAccessException {
    }

    @Test
    void testValidLogin() throws IOException, DataAccessException {
        LoginRequest req = new LoginRequest("sheila","parker");
        LoginService service = new LoginService(req);
        LoginResponse resp = service.getResponse();

        assertNotNull(resp);
        assertTrue(resp.isSuccess());
    }
    @Test
    void testUserNotFound() throws IOException, DataAccessException {
        LoginRequest req = new LoginRequest("noah","parker");
        LoginService service = new LoginService(req);
        LoginResponse resp = service.getResponse();

        assertNotNull(resp);
        assertFalse(resp.isSuccess());

    }
    @Test
    void testBadPassword() throws IOException, DataAccessException {
        LoginRequest req = new LoginRequest("sheila","n");
        LoginService service = new LoginService(req);
        LoginResponse resp = service.getResponse();

        assertNotNull(resp);
        assertFalse(resp.isSuccess());
    }
}
