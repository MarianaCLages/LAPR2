package app.domain.stores;

import app.controller.App;
import app.domain.model.Client;
import app.domain.model.Company;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ClientStoreTest {

    @Test
    public void CreateValidClientStore() {
        ClientStore store = new ClientStore();
        Assert.assertNotNull(store);
    }

    @Test
    public void CreateNullClientStore() {
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
    public void CreateClientNotNull1() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.CreateClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'u', "email@gamil.com", "Zé");
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

    @Test
    public void existTestTrue() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.CreateClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
        store.saveClient();

        Assert.assertTrue(store.exists("1234567891"));

    }

    @Test
    public void existTestFalse() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.CreateClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
        store.saveClient();

        Assert.assertFalse(store.exists("1234567791"));

    }

    @Test
    public void existTestFalse2() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.CreateClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
        Assert.assertFalse(store.exists("1234567791"));

    }

    @Test
    public void orderClientListByTin() {
        ClientStore store = new ClientStore();
        List<Client> array = store.returnClientList();

        List<Client> orderedListTin = new ArrayList<>(array);
        orderedListTin.sort(Comparator.comparing(Client::getTinNumber));

        List<Client> actual = store.orderClientListByTin();

        Assert.assertEquals(actual, orderedListTin);
    }


    @Test
    public void orderClientListByName() {
        ClientStore store = new ClientStore();
        List<Client> array = store.returnClientList();

        List<Client> orderedListName = new ArrayList<>(array);
        orderedListName.sort(Comparator.comparing(Client::getName));

        List<Client> actual = store.orderClientListByName();

        Assert.assertEquals(actual, orderedListName);
    }

}