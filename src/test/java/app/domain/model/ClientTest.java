package app.domain.model;

import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ClientTest {
    @Test
    public void CreateValidClient() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
    }

    @Test
    public void CreateValidClientFemale() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'F', "email@gamil.com", "Zé");
    }


    @Test
    public void CreateValidClientWithoutSex() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, "email@gamil.com", "Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidPhoneNumberTooBig() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("123456789104", "1234567890123456", "1234567891", "1234567891", date, "email@gamil.com", "Zé");

    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidPhoneWithSex() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("123456789104", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");

    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidPhoneNumberTooShort() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("1234567890", "1234567890123456", "1234567891", "1234567891", date, "email@gamil.com", "Zé");

    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidCcTooBig() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "12345678901234564", "1234567891", "1234567891", date, "email@gamil.com", "Zé");

    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidCcTooShort() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "123456789012345", "1234567891", "1234567891", date, "email@gamil.com", "Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidCcWithSex() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "123456789012345", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
    }


    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidNhsTooShort() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "123456789", "1234567891", date, "email@gamil.com", "Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidNhsWithSex() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "123456789", "1234567891", date, 'M', "email@gamil.com", "Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidNhsTooBig() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "12345678915", "1234567891", date, "email@gamil.com", "Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidTinTooBig() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "12345678918", date, 'M', "email@gamil.com", "Zé");

    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidTinTooSmall() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "123456789", date, 'M', "email@gamil.com", "Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidTinWithoutSex() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "123456789", date, "email@gamil.com", "Zé");
    }


    @Test(expected = IllegalArgumentException.class)
    public void DateAfterToday() throws ParseException {

        String strDate = "25-06-2022";

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'm', "email@gamil.com", "Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void TooOldToday() throws ParseException {
        String strDate = "25-06-1850";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, "email@gamil.com", "Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void TooOldTodayWithSex() throws ParseException {
        String strDate = "25-06-1850";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidSex() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'J', "email@gamil.com", "Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidEmailWithSex() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "emailgamil.com", "Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidEmail() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, "emailgamil.com", "Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidNameNumberWithSex() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "emailgamil.com", "Zé4");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidNameNumberWithoutSex() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, "emailgamil.com", "Zé4");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidNameTooBig() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "emailgamil.com", "José Manuel Avelino Faria Guimarães de Sousa Andrade de Melo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void PhoneNumberNotOnlyNumerical() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("1234567f910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CcNotOnlyNumerical() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "12345678d0123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void TinNotOnlyNumerical() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567800123456", "1234567891", "123f567891", date, 'M', "email@gamil.com", "Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void NhsNotOnlyNumerical() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567800123456", "123456f891", "1234567891", date, 'M', "email@gamil.com", "Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void BirthDateNull() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567800123456", "123456f891", "1234567891", null, 'M', "email@gamil.com", "Zé");
    }


    @Test(expected = IllegalArgumentException.class)
    public void BirthDateToday() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        Client client = new Client("12345678910", "1234567800123456", "123456f891", "1234567891", date, 'M', "email@gamil.com", "Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void BirthDate150YearsAgo() throws ParseException {
        String strDate = "25-06-1871";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567800123456", "123456f891", "1234567891", date, 'M', "email@gamil.com", "Zé");
    }

    @Test(expected = IllegalArgumentException.class)
    public void EmailNull() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', null, "Zé");
    }

    @Test
    public void toStringTest() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "ze@email.com", "Zé");

        String expected = "Client: phoneNumber= 12345678910, cc= 1234567890123456, nhs= 1234567891, tinNumber= 1234567891, birthDate= 25-06-1950, sex= Male, email= ze@email.com, name= Zé" ;
        String actual = client.toString();

        Assert.assertEquals(expected,actual);

    }

    @Test(expected = IllegalArgumentException.class)
    public void ClientNull() throws ParseException {
        Client client = new Client(null, null, null, null, null, ' ', null, null);
    }

    @Test
    public void ClientName35Char() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "ze@email.com", "Zoseeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
    }

    @Test
    public void addUserSendEmail() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "ze@email.com", "Manuel");

    }
}

