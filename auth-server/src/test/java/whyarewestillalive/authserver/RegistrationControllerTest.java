package whyarewestillalive.authserver;

import org.junit.Before;
import org.junit.Test;
import whyarewestillalive.authserver.controllers.RegistrationController;
import whyarewestillalive.authserver.entities.User;
import whyarewestillalive.authserver.responses.JsonResponse;
import whyarewestillalive.authserver.responses.ResponseType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RegistrationControllerTest {

    RegistrationController mockRC;
    User newUser = new User();
    User extistingUser = new User();
    User notUser= new User();


    @Before
    public void init() {
        // creating a mock for the DataAccess class
        mockRC = mock(RegistrationController.class);

        newUser.setName("mmarcell264");
        newUser.setPassword("12345");

        extistingUser.setName("mmarcell264");
        extistingUser.setPassword("12345");

        notUser.setName("asdasd");
        notUser.setPassword("12345");
    }


    @Test
    public void UserRegistrationSuccessTest() {

        when(mockRC.register(newUser)).thenReturn(new JsonResponse(ResponseType.SUCCESS, "User registered successfully."));
        JsonResponse jsonResponse =mockRC.register(newUser);
        boolean status=false;

        if (jsonResponse.status.toString().equals("SUCCESS")) status=true;

        assertTrue(status);

        verify(mockRC, times(1)).register(newUser);

    }

    @Test
    public void UserRegistrationErrorTest() {

        when(mockRC.register( extistingUser)).thenReturn(new JsonResponse(ResponseType.ERROR, "User already exist!"));
        JsonResponse jsonResponse = mockRC.register( extistingUser);
        boolean status=true;
        if (jsonResponse.status.toString().equals("ERROR")) status=false;

        assertFalse(status);

        verify(mockRC, times(1)).register( extistingUser);

    }

    @Test
    public void UserDeleteSuccessTest() {

        when(mockRC.delete(newUser)).thenReturn(new JsonResponse(ResponseType.SUCCESS, "User delete is unsuccessful."));
        JsonResponse jsonResponse = mockRC.delete(newUser);
        boolean status=false;
        if (jsonResponse.status.toString().equals("SUCCESS")) status=true;

        assertTrue(status);

        verify(mockRC, times(1)).delete(newUser);

    }

    @Test
    public void UserDeleteErrorTest() {

        when(mockRC.delete(notUser)).thenReturn(new JsonResponse(ResponseType.ERROR, "User delete is successful!"));
        JsonResponse jsonResponse = mockRC.delete(notUser);
        boolean status=true;
        if (jsonResponse.status.toString().equals("ERROR")) status=false;

        assertFalse(status);

        verify(mockRC, times(1)).delete(notUser);

    }


}
