package app.domain.model;

import org.junit.Test;

import static app.domain.model.Email.sendPasswordNotification;

public class EmailTest {

    @Test
    public void sendPasswordNotificationValid() {
        sendPasswordNotification("name", "String email", "String password");
    }

}