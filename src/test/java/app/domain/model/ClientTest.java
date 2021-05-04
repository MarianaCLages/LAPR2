package app.domain.model;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class ClientTest {
    @Test
    public void CreateValidClient() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910","1234567890123456","1234567891","1234567891",date,'M',"email@gamil.com","Zé");

    }
    @Test
    public void CreateValidClientWithoutSex() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910","1234567890123456","1234567891","1234567891",date,"email@gamil.com","Zé");

    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidPhoneNumberTooBig() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("123456789104","1234567890123456","1234567891","1234567891",date,"email@gamil.com","Zé");

    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidPhoneNumberTooShort() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("1234567890","1234567890123456","1234567891","1234567891",date,"email@gamil.com","Zé");

    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidCcTooBig() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910","12345678901234564","1234567891","1234567891",date,"email@gamil.com","Zé");

    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidCcTooShort() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910","123456789012345","1234567891","1234567891",date,"email@gamil.com","Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidNhsTooShort() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910","1234567890123456","123456789","1234567891",date,"email@gamil.com","Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidNhsTooBig() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910","1234567890123456","12345678915","1234567891",date,"email@gamil.com","Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidTinTooBig() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910","1234567890123456","1234567891","12345678918",date,'M',"email@gamil.com","Zé");

    }

    public void CreateClientInvalidTinTooSmall() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910","1234567890123456","1234567891","123456789",date,'M',"email@gamil.com","Zé");

    }


    @Test (expected = IllegalArgumentException.class)
    public void DateAfterToday() throws ParseException {

        String strDate = "25-06-2022";

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        Date date = df.parse(strDate);

        Client client = new Client("12345678910","1234567890123456","1234567891","1234567891",date,'m',"email@gamil.com","Zé");
    }

    @Test (expected = IllegalArgumentException.class)
    public void TooOldToday() throws ParseException {
        String strDate = "25-06-1850";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        Client client = new Client("12345678910","1234567890123456","1234567891","1234567891",date,"email@gamil.com","Zé");
    }

    @Test (expected = IllegalArgumentException.class)
    public void CreateClientInvalidSex() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910","1234567890123456","1234567891","1234567891",date,'J',"email@gamil.com","Zé");
    }

    @Test (expected = IllegalArgumentException.class)
    public void CreateClientInvalidEmail() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910","1234567890123456","1234567891","1234567891",date,'M',"emailgamil.com","Zé");
    }

    @Test (expected = IllegalArgumentException.class)
    public void CreateClientInvalidName() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910","1234567890123456","1234567891","1234567891",date,'M',"emailgamil.com","Zé4");
    }


}