package app.domain.stores;

import app.domain.model.Client;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        store.createClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
        Assert.assertNotNull(store.getClient());
    }

    @Test
    public void CreateClientNotNull1() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.createClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'u', "email@gamil.com", "Zé");
        Assert.assertNotNull(store.getClient());
    }

    @Test
    public void ValidateValidClient() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.createClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");

        Assert.assertTrue(store.validateClient(store.getClient()));
    }

    @Test
    public void ValidateClientAlreadyExists() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.createClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
        store.saveClient();
        Assert.assertFalse(store.validateClient(store.getClient()));
    }

    @Test
    public void ValidateClientAlreadyExistEmail() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.createClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
        store.saveClient();
        store.createClient("12385678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");


        Assert.assertFalse(store.validateClient(store.getClient()));
    }

    @Test
    public void ValidateClientAlreadyExistPhoneNumber() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.createClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
        store.saveClient();
        store.createClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "emil@gamil.com", "Zé");
        Assert.assertFalse(store.validateClient(store.getClient()));
    }

    @Test
    public void TryingToSaveInvalidClient() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.createClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
        store.saveClient();
        store.createClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "emil@gamil.com", "Zé");

        Assert.assertFalse(store.saveClient());
    }

    @Test
    public void saveClient() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.createClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");

        Assert.assertTrue(store.saveClient());
    }

    @Test
    public void existTestTrue() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.createClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
        store.saveClient();

        Assert.assertTrue(store.exists("1234567891"));

    }

    @Test
    public void existTestFalse() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.createClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
        store.saveClient();

        Assert.assertFalse(store.exists("1234567791"));

    }

    @Test
    public void existTestFalse2() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.createClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
        Assert.assertFalse(store.exists("1234567791"));

    }

    @Test
    public void orderClientListByTin() throws ParseException {
        String dateStr = "21/07/2002";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        ClientStore clientStore = new ClientStore();
        List<Client> clientList = clientStore.getClientList();
        List<Client> orderedListTIN = new ArrayList<>(clientList);

        clientStore.createClient("12345678901", "1234567890123456", "1234567890", "1234567890", date, 'M', "ze@ze.com", "Zé");
        clientStore.saveClient();

        clientStore.createClient( "12345678903", "1234567891123456", "1234567891", "1234567891", date, 'M', "alberto@alberto.com", "Alberto");
        clientStore.saveClient();

        clientStore.createClient("12345678902", "1234565891123456", "1234567893", "2234567890", date, 'F', "maria@maria.com", "Maria");
        clientStore.saveClient();

        Client client1 = new Client("12345678901", "1234567890123456", "1234567890", "1234567890", date, 'M', "ze@ze.com", "Zé");
        Client client2 = new Client("12345678903", "1234567891123456", "1234567891", "1234567891", date, 'M', "alberto@alberto.com", "Alberto");
        Client client3 = new Client("12345678902", "1234565891123456", "1234567893", "2234567890", date, 'F', "maria@maria.com", "Maria");

        orderedListTIN.add(client1);
        orderedListTIN.add(client2);
        orderedListTIN.add(client3);

        Assert.assertEquals(orderedListTIN.get(0).getTinNumber(), clientStore.sortClientListByTin().get(0).getTinNumber());
        Assert.assertEquals(orderedListTIN.get(1).getTinNumber(), clientStore.sortClientListByTin().get(1).getTinNumber());
        Assert.assertEquals(orderedListTIN.get(2).getTinNumber(), clientStore.sortClientListByTin().get(2).getTinNumber());
    }


    @Test
    public void orderClientListByName() throws ParseException {
        String dateStr = "21/07/2002";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(dateStr);

        ClientStore clientStore = new ClientStore();
        List<Client> clientList = clientStore.getClientList();
        List<Client> orderedListName = new ArrayList<>(clientList);

        clientStore.createClient("12345678901", "1234567890123456", "1234567890", "1234567890", date, 'M', "ze@ze.com", "Zé");
        clientStore.saveClient();

        clientStore.createClient( "12345678903", "1234567891123456", "1234567891", "1234567891", date, 'M', "alberto@alberto.com", "Alberto");
        clientStore.saveClient();

        clientStore.createClient("12345678902", "1234565891123456", "1234567893", "2234567890", date, 'F', "maria@maria.com", "Maria");
        clientStore.saveClient();

        Client client1 = new Client("12345678903", "1234567891123456", "1234567891", "1234567891", date, 'M', "alberto@alberto.com", "Alberto");
        Client client2 = new Client("12345678902", "1234565891123456", "1234567893", "2234567890", date, 'F', "maria@maria.com", "Maria");
        Client client3 = new Client("12345678901", "1234567890123456", "1234567890", "1234567890", date, 'M', "ze@ze.com", "Zé");

        orderedListName.add(client1);
        orderedListName.add(client2);
        orderedListName.add(client3);

        Assert.assertEquals(orderedListName.get(0).getName(), clientStore.sortClientListByName().get(0).getName());
        Assert.assertEquals(orderedListName.get(1).getName(), clientStore.sortClientListByName().get(1).getName());
        Assert.assertEquals(orderedListName.get(2).getName(), clientStore.sortClientListByName().get(2).getName());
    }
}