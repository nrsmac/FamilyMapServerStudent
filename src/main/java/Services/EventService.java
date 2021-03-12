package Services;

import DataAccess.*;
import Model.AuthToken;
import Model.Event;
import Request.EventRequest;
import Response.EventResponse;

import java.sql.Connection;
import java.util.ArrayList;

public class EventService {
    EventResponse response;

    public EventService(EventRequest request) throws DataAccessException {
        this.response = null;
        Database db = new Database();
        try{
            Connection conn = db.getConnection();
            UserDao pDao = new UserDao(conn);
            EventDao eDao = new EventDao(conn);
            AuthTokenDao aDao = new AuthTokenDao(conn);

            String authKey = request.getAuthtoken();
                AuthToken authToken = aDao.findByAuthtoken(authKey);
                String username = authToken.getAssociatedUsername();

                if(request.getEventID() == null){
                    ArrayList<Event> events = eDao.findByUser(username);
                    if(events != null){
                        this.response = new EventResponse(events, true);
                    } else {
                        this.response = new EventResponse("Error: No events associated with users",false );
                    }
                } else {
                    Event event = eDao.findByEventId(request.getEventID());
                    if(event != null && event.getAssociatedUsername().equals(username)){
                        this.response = new EventResponse(
                                event.getAssociatedUsername(),
                                event.getEventID(),
                                event.getPersonID(),
                                event.getLatitude(),
                                event.getLongitude(),
                                event.getCountry(),
                                event.getCity(),
                                event.getEventType(),
                                event.getYear(),
                                true
                        );

                    } else {
                        this.response = new EventResponse("Error: Event not found", false);

                    }
                }
            db.closeConnection(false);
        } catch (DataAccessException e) {
            e.printStackTrace();
            this.response = new EventResponse("Error: Issue getting events.",false);
            db.closeConnection(false);
        }
    }

    public EventResponse getResponse() {
        return this.response;
    }
}
