package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailTest {

    @Test
    public void ValidEmail(){
        Email mail = new Email("email@mail.com","password123");
    }



}