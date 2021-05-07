package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientControllerTest {
    Company company = App.getInstance().getCompany();

    @Test
    public void CreateClientControllerTest() {
        ClientController controller = new ClientController();
    }

    @Test
    public void CreateClientControllerWithParameterTest() {
        ClientController controller = new ClientController(company);
    }

    @Test
    public void CreateValidClientTest() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        ClientController controller = new ClientController();

        controller.createClient("1234567890123456", "1234567891", date, 'M', "1234567891", "12345678910", "email@gamil.com", "Zé");
    }

    @Test
    public void SaveClientTest() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        ClientController controller = new ClientController();

        controller.createClient("1234567890123456", "1234567891", date, 'M', "1234567891", "12345678910", "email@gamil.com", "Zé");

        Assert.assertTrue(controller.saveClient());
    }


    @Test
    public void GetClientTest() throws ParseException {
        ClientController controller = new ClientController();

        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        controller.createClient("1234567890123456", "1234567891", date, 'M', "1234567891", "12345678910", "ze@email.com", "Zé");

        String expected = "Client: phoneNumber= 12345678910, cc= 1234567890123456, nhs= 1234567891, tinNumber= 1234567891, birthDate= 25-06-1950, sex= Male, email= ze@email.com, name= Zé" ;
        String actual = (controller.getClient());

        Assert.assertEquals(expected,actual);

    }


}