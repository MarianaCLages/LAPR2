package app.domain.model;

import app.domain.model.ClientStore;
import app.domain.shared.Constants;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientStoreTest {

    @Test
    public void CreateValidClientStore() {
        ClientStore store = new ClientStore();
        Assert.assertNotNull(store);
    }

    @Test
    public void CreateNulllientStore() {
        ClientStore store = null;
        Assert.assertNull(store);
    }

    @Test
    public void CreateClientNotNull() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.CreateClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
        Assert.assertNotNull(store.getClient());
    }

    @Test
    public void ValidateValidClient() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.CreateClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");

        Assert.assertTrue(store.validateClient(store.getClient()));
    }

    @Test
    public void ValidateClientAlreadyExists() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.CreateClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
        store.saveClient();
        Assert.assertFalse(store.validateClient(store.getClient()));
    }

    @Test
    public void ValidateClientAlreadyExistEmail() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.CreateClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
        store.saveClient();
        store.CreateClient("12385678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");


        Assert.assertFalse(store.validateClient(store.getClient()));
    }

    @Test
    public void ValidateClientAlreadyExistPhoneNumber() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.CreateClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
        store.saveClient();
        store.CreateClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "emil@gamil.com", "Zé");
        Assert.assertFalse(store.validateClient(store.getClient()));
    }

    @Test
    public void TryingToSaveInvalidClient() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.CreateClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
        store.saveClient();
        store.CreateClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "emil@gamil.com", "Zé");

        Assert.assertFalse(store.saveClient());
    }

    @Test
    public void saveClient() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.CreateClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");

        Assert.assertTrue(store.saveClient());
    }

}