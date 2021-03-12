package Services;

import DataAccess.DataAccessException;
import Request.ClearRequest;
import Response.ClearResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClearServiceTest  {
    ClearRequest goodReq = new ClearRequest();

    @Test
    void testGoodRequest() throws DataAccessException{
        ClearService service = new ClearService(goodReq);
        ClearResponse response = service.getResponse();
        assertNotNull(response);
        assertEquals("Clear succeeded",response.getMessage());
        assertTrue(response.isSuccess());
    }
}
