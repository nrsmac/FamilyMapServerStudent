package Services;

import DataAccess.*;
import GenerateData.GeneratePeople;
import Handlers.Json;
import Model.AuthToken;
import Model.Event;
import Model.User;
import Request.LoadRequest;
import Request.RegisterRequest;
import Response.RegisterResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterServiceTest {

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
    void tearDown() {
    }

    @Test
    void newRegistrant() throws DataAccessException {
        RegisterRequest req = new RegisterRequest("nrsmac", "Nrsmac12","noahrschill@gmail.com","Noah","Schill","m");
        RegisterService service = new RegisterService(req);
        RegisterResponse response = service.getResponse();
        assertNotNull(response);
        assertTrue(response.isSuccess());

        db = new Database();
        uDao = new UserDao(db.getConnection());
        PersonDao pDao = new PersonDao(db.getConnection());
        aDao = new AuthTokenDao(db.getConnection());

        assertNotNull(uDao.find(response.getPersonID()));
        assertNotNull(pDao.find(response.getPersonID()));
        assertNotNull(aDao.findByUsername(response.getUsername()));

        db.closeConnection(true);

    }

    @Test
    void registrantAlreadyExists() {

    }
}
