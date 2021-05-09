# US 03 - To register a Client

## 1. Requirements Engineering

### 1.1. User Story Description

As a receptionist of the laboratory, I want to register a client.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> "The receptionist needs the client’s citizen card number, National Healthcare Service (NHS) number, birth date, sex, Tax Identification number (TIF), phone number, e-mail and name, to register a client."

**From the client clarifications:**

> **Question:** To register a Client which is the format of each attribute?
>
> **Answer:**
>
> Citizen Card: 16 digit number
>
> NHS: 10 digit number
>
> TIN: 10 digit number
>
> Date of Birth: DD/MM/YY
>
> Sex: Male/Female
>
> Phone number: 11 digit number
>
> **Link:** https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7153#p9314
>

-

> **Question:** The receptionist needs mandatorily of all client data described on the project description?
>
> **Answer:** No, the receptionist doesn't need to input the client's sex, it is optional while all the other fields are required.
>
> **Link:** https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7153#p9314
>

-

> **Question:** After being registered by the receptionist, should the client receive some kind of confirmation e-mail in order to finish their registration?
>
> **Answer:** The client only receives an e-mail informing that the registration was successful and that they can start to use the system. The e-mail includes the client's password.
>
> **Link:** https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7736#p10081
>

-

> **Question:** Relative to registering a new user, how should the system respond in the event of creating a new user with the same attributes of an already existing user?
>
> **Answer:** This should be treated as an error. A subset of the attributes of any client are unique.
>
> **Link:** https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7482#p9736
>

-

> **Question:** During the registration of a customer, if they give a phone number already used by another customer, should the registration continue normally or should it be cancelled?
>
> **Answer:** The e-mail address and phone number should be unique for each user. The system should present a message informing about the error and asking for a different phone number.
>
> **Link:** https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7615


> **Question:** What should be the maximum length of the String with the name of the Client?
>
> **Answer:** A string with no more than 35 characters.
>
> **Link:** https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7945#p10383

> **Question:** Is there any restrictions to the client age?
>
> **Answer:** A client should not have more than 150 years of age.
>
> **Link:** https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7945#p10383


> **Question:** What should be the maximum length of the String with the name of the Client?
>
> **Answer:** A string with no more than 35 characters.
>
> **Link:** https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7945#p10383

### 1.3. Acceptance Criteria

* **AC1:** The client must become a system user. The "auth" component available on the repository must be reused (
  without modifications).
* **AC2:** To input the client's sex is optional while all the other fields are mandatory.
* **AC3:** The e-mail address and phone number should be unique for each user
* **AC4:** Citizen Card number is a 16-digit number.
* **AC5:** NHS number is a 10 digit number.
* **AC6:** Date of birth has DD/MM/YY as format.
* **AC7:** Phone number is an 11-digit number.
* **AC8:** Age must be inferior to 150 years
* **AC9:** TIN is a 10-digit number
* **AC10** Name must have maximum of 35 characters

### 1.4. Found out Dependencies

* There is a dependency to "US7- Register a new employee" since there must be a receptionist employed to register the
  client.


### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * Name
    * Citizen Card number
    * National Healthcare Service number
    * Tax Identification Number
    * Phone number
    * E-mail
    * Birthdate

* Selected data:
    * Sex

### 1.6. System Sequence Diagram (SSD)

*Insert here a SSD depicting the envisioned Actor-System interactions and throughout which data is inputted and
outputted to fulfill the requirement. All interactions must be numbered.*

![US3_SSD](US3_SSD.svg)

### 1.7 Other Relevant Remarks

*Use this section to capture other relevant information that is related with this US such as (i) special requirements
; (ii) data and/or technology variations; (iii) how often this US is held.*

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt

*In this section, it is suggested to present an excerpt of the domain model that is seen as relevant to fulfill this
requirement.*

![US3_MD](US3_MD.svg)

### 2.2. Other Remarks

*Use this section to capture some aditional notes/remarks that must be taken into consideration into the design
activity. In some case, it might be usefull to add other analysis artifacts (e.g. activity or state diagrams).*

## 3. Design - User Story Realization

### 3.1. Rationale

**The rationale grounds on the SSD interactions and the identified input/output data.**

| Interaction ID                                                                                                                         | Question: Which class is responsible for...                              | Answer                   | Justification (with patterns)                                                                                 |
| :-------------                                                                                                                         | :---------------------                                                   | :------------            | :----------------------------                                                                                 |
| Step 1: asks to register a new client                                                                                                  | ... asking to register a new client?                                     | RegisterClientUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                                                                                                                                        | ... coordinating the US?                                                 | RegisterClientController | Controller                                                                                                    |
|                                                                                                                                        | ... instantiating a new Client?                                          | ClientStore              | Creator </br> LC/HC                                                                                           |
| Step 2: requests client's registration data (citizen card number, NHS number, birth date, sex, TIF number, phone number, e-mail, name) | ... requesting the client's data?                                        | RegisterClientUI         | IE: the interface interacts with the actor.                                                                   |
| Step 3: types data                                                                                                                     | ... creating the ClientStore?                                            | Company                  | IE: Company knows all lists of objects in the system                                                          |
|                                                                                                                                        | ... saving the input data                                                | Client                   | IE: the object has its own data                                                                               |
| Step 4: shows data and requests confirmation                                                                                           | ... showing and requesting the confirmation of the data?                 | RegisterClientUI         |                                                                                                               |
|                                                                                                                                        | ... validating the data locally (e.g.: mandatory vs.non-mandatory data)? | Client                   | IE: knows its own data.                                                                                       |
|                                                                                                                                        | ... validating the data globally (e.g.: duplicated)?                     | ClientStore              | IE: knows/has all the Client objects                                                                          |
| Step 5: confirms data                                                                                                                  | ... confirming the data?                                                 | RegisterClientUI         | IE: the interface interacts with the actor.                                                                   |
|                                                                                                                                        | ... adding the user with role?                                           | Client                   | IE: owns all the data that is needed in order to create a new user                                            |
|                                                                                                                                        | ... creating the user password                                           | Client                   | IE: knows his own data and generates its own data                                                             |
|                                                                                                                                        | ... sending the confirmation email                                       | Client                   | IE: owns all the data that will be sent                                                                       |
| Step 6: informs operation success                                                                                                      | ... informing operation success?                                         | RegisterClientUI         | IE: the interface interacts with the actor.                                                                   |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Company
* Client

Other software classes (i.e. Pure Fabrication) identified:

* RegisterClientUI
* RegisterClientController
* ClientStore
* RoleStore

## 3.2. Sequence Diagram (SD)

![US3_SD](US3_SD.svg)

## 3.3. Class Diagram (CD)

![US3_CD](US3_CD.svg)

# 4. Tests

*In this section, it is suggested to systematize how the tests were designed to allow a correct measurement of
requirements fulfilling.*

**Test 1:** Check that it is not possible to create an instance of the Client class with null values.

	    @Test(expected = IllegalArgumentException.class)
    public void ClientNull() throws ParseException {
        Client client = new Client(null, null, null, null, null, ' ', null, null);
    }

**Test 2:** Check that it is possible to create an instance of the Client without sex parameter (AC2)

```
    @Test
    public void CreateValidClientWithoutSex() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, "email@gamil.com", "Zé");
    }
```

**Test 3:** Check that it is possible to create an instance of the Client with sex parameter (AC2)

```
    @Test
    public void CreateValidClient() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
    }
    
```

**Test 4:** Check that it is not possible to create an instance of the Client with a repeated email address (AC3)

```
    @Test
    public void ValidateClientAlreadyExistEmail() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.CreateClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
        store.saveClient();
        store.CreateClient("12385678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");


        Assert.assertFalse(store.ValidateClient(store.getClient()));
    }
```

***Test 5*** Check that it is not possible to create an instance of the Client with a repeated phone number (AC3)

```
    @Test
    public void ValidateClientAlreadyExistPhoneNumber() throws ParseException {
        ClientStore store = new ClientStore();
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        store.CreateClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
        store.saveClient();
        store.CreateClient("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "emil@gamil.com", "Zé");
        Assert.assertFalse(store.ValidateClient(store.getClient()));
    }
```

***Test 6*** Check that it is not possible to create an instance of the Client with an invalid cc number (AC4)

```
@Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidCcTooShort() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "123456789012345", "1234567891", "1234567891", date, "email@gamil.com", "Zé");
    }

```

***Test 7*** Check that it is not possible to create an instance of the Client with an invalid nhs number (AC5)

```
    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidNhsTooBig() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "12345678915", "1234567891", date, "email@gamil.com", "Zé");
    }
```

***Test 8*** Check that it is not possible to create an instance of the Client with an invalid date of birth (AC6/AC8)

```
    @Test(expected = IllegalArgumentException.class)
    public void TooOldToday() throws ParseException {
        String strDate = "25-06-1850";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);
        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, "email@gamil.com", "Zé");
    }
```

***Test 9*** Check that it is not possible to create an instance of the Client with an invalid phone number (AC7)

```
    @Test(expected = IllegalArgumentException.class)
    public void PhoneNumberNotOnlyNumerical() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("1234567f910", "1234567890123456", "1234567891", "1234567891", date, 'M', "email@gamil.com", "Zé");
    }
```

***Test 10*** Check that it is not possible to create an instance of the Client with a Tin number (AC9)

```
    @Test(expected = IllegalArgumentException.class)
    public void TinNotOnlyNumerical() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567800123456", "1234567891", "123f567891", date, 'M', "email@gamil.com", "Zé");
    }
    
```

***Test 11*** Check that it is not possible to create an instance of the Client with an invalid name (AC10)

```
    @Test(expected = IllegalArgumentException.class)
    public void CreateClientInvalidNameTooBig() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "emailgamil.com", "José Manuel Avelino Faria Guimarães de Sousa Andrade de Melo");
    }
```

# 5. Construction (Implementation)

*In this section, it is suggested to provide, if necessary, some evidence that the construction/implementation is in
accordance with the previously carried out design. Furthermore, it is recommeded to mention/describe the existence of
other relevant (e.g. configuration) files and highlight relevant commits.*

## Class Client
```
package app.domain.model;

import app.domain.shared.Constants;
import auth.AuthFacade;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

public class Client {

    private String phoneNumber;
    private String cc;
    private String nhs;
    private String tinNumber;
    private Date birthDate;
    private String sex;
    private String email;
    private String name;


    public Client(String phoneNumber, String cc, String nhs, String tinNumber, Date birthDate, char sex, String email, String name) {
        checkPhoneNumber(phoneNumber);
        checkCc(cc);
        checkNhs(nhs);
        checkTin(tinNumber);
        checkBirthDate(birthDate, checkAge(birthDate));
        checkEmailRules(email);
        checkNameRules(name);
        this.phoneNumber = phoneNumber;
        this.cc = cc;
        this.nhs = nhs;
        this.tinNumber = tinNumber;
        this.birthDate = birthDate;

        if (sex == 'M') {
            this.sex = "Male";
        } else {
            if (sex == 'F') {
                this.sex = "Female";
            } else {
                throw new IllegalArgumentException("Sex must be 'M' or 'F' ");
            }
        }

        this.email = email;
        this.name = name;
    }

    public Client(String phoneNumber, String cc, String nhs, String tinNumber, Date birthDate, String email, String name) {
        checkPhoneNumber(phoneNumber);
        checkCc(cc);
        checkNhs(nhs);
        checkTin(tinNumber);
        checkBirthDate(birthDate, checkAge(birthDate));
        checkEmailRules(email);
        checkNameRules(name);
        this.phoneNumber = phoneNumber;
        this.cc = cc;
        this.nhs = nhs;
        this.tinNumber = tinNumber;
        this.birthDate = birthDate;
        this.email = email;
        this.name = name;
    }

    private boolean checkIfIsNumerical(String i) {
        return NumberUtils.isCreatable(i);
    }

    private void checkPhoneNumber(String phoneNumber) {
        if (!checkIfIsNumerical(phoneNumber)) {
            throw new IllegalArgumentException("Phone Number must be a number");
        }
        if (phoneNumber.length() != 11) {
            throw new IllegalArgumentException("Phone Number must have 11 digits");
        }
    }

    private void checkCc(String cc) {
        if (!checkIfIsNumerical(cc)) {
            throw new IllegalArgumentException("Citizen Card must be a number");
        }
        if (cc.length() != 16) {
            throw new IllegalArgumentException("Citizen Card must have 16 digits");
        }
    }

    private void checkNhs(String nhs) {
        if (!checkIfIsNumerical(nhs)) {
            throw new IllegalArgumentException("National Health System must be a number");
        }
        if (nhs.length() != 10) {
            throw new IllegalArgumentException("National Health System number must have 10 digits");
        }
    }

    private void checkTin(String tif) {
        if (!checkIfIsNumerical(tif)) {
            throw new IllegalArgumentException("Tax Identification Number must be a number");
        }
        if (tif.length() != 10) {
            throw new IllegalArgumentException("Tax Identification Number number must have 10 digits");
        }
    }

    private int checkAge(Date birthDate) {
        LocalDate date = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Transition from object Date to object LocalDate retrieved from https://www.baeldung.com/java-date-to-localdate-and-localdatetime
        return Period.between(date, LocalDate.now()).getYears(); // Inspired in https://stackoverflow.com/a/1116138
    }

    private void checkBirthDate(Date birthDate, int age) {
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth Date cannot be null");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Birth Date cannot be in the future");
        }
        if (age > 150) {
            throw new IllegalArgumentException("Age cannot be more than 150");
        }

    }

    /**
     * This methode checks if email is correct.
     * Retrieved by professor Paulo Maio code of template of the project.
     *
     * @param email
     * @author Paulo Maio <pam@isep.ipp.pt>
     */

    private void checkEmailRules(String email) {

        if (StringUtils.isBlank(email))
            throw new IllegalArgumentException("Code cannot be blank.");

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (!pat.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid Email.");
        }
    }

    private void checkNameRules(String name) {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");

        name = name.toLowerCase();
        name = Normalizer.normalize(name, Normalizer.Form.NFD);
        name = name.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        name = name.replaceAll(" ", "");
        char[] charArray = name.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (!(c >= 'a' && c <= 'z')) {
                throw new IllegalArgumentException("Name only accepts letters");
            }
        }

        if (name.length() >= 35) {
            throw new IllegalArgumentException("Name must have maximum of 35 characters");
        }
    }


    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(this.birthDate.getTime());

        return "Client: " + "phoneNumber= " + phoneNumber + ", cc= " + cc + ", nhs= " + nhs + ", tinNumber= " + tinNumber + ", birthDate= " + date + ", sex= " + sex + ", email= " + email + ", name= " + name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    private String getPassword() {
        int lenght = 10;

        return RandomStringUtils.randomAlphanumeric(lenght);
    }

    public boolean addUser(Company company) {
        boolean success = false;
        String password = getPassword();
        AuthFacade authFacade = company.getAuthFacade();
        success = authFacade.addUserWithRole(this.name, this.email, getPassword(), Constants.ROLE_CLIENT);
        if (success){
            Email mail = new Email(this.email,getPassword());

        }
        return success;
    }

}
```

## Class ClientStore
```
package app.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientStore {
    private final List<Client> array;
    private Client client;

    public ClientStore() {
        this.array = new ArrayList<>();
    }
    public void CreateClient(String phoneNumber, String cc, String nhs, String tinNumber, Date birthDate, char sex, String email, String name) {
        if (sex == 'M' || sex == 'F') {
            this.client = new Client(phoneNumber, cc, nhs, tinNumber, birthDate, sex, email, name);
        } else {
            this.client = new Client(phoneNumber, cc, nhs, tinNumber, birthDate, email, name);
        }
    }
    public boolean ValidateClient(Client client) {
        return client != null && !contains(client) && !exists(client);
    }
    private boolean exists(Client client) {
        for (Client c : array) {
            return c.getEmail().equals(client.getEmail()) || c.getPhoneNumber().equals(client.getPhoneNumber());
        }
        return false;
    }
    public boolean saveClient() {
        if (ValidateClient(this.client)) {
            array.add(this.client);
            return true;
        } else {
            return false;
        }
    }
    public boolean contains(Client client) {
        return this.array.contains(client);
    }
    public boolean addUser(Company company) {
        return client.addUser(company);
    }
    public Client getClient() {
        return client;
    }
}

```

## Class ClientController
````
package app.controller;

import app.domain.model.Client;
import app.domain.model.ClientStore;
import app.domain.model.Company;

import java.util.Date;

public class ClientController {

    private Company company;
    private ClientStore store;

    public ClientController() {

        this(App.getInstance().getCompany());
    }

    public ClientController(Company company) {
        this.company = company;

    }
    public void createClient(String cc, String nhs, Date birhDate, char sex, String tifNumber, String phoneNumber,String email,String name) {
        store = company.getClientList();
        store.CreateClient(phoneNumber, cc, nhs,tifNumber, birhDate, sex,  email, name);
    }

    public Client getClient(){
        return store.getClient();
    }

    public boolean saveClient() {
        boolean saved = false;

        saved = store.saveClient();
        saved = store.addUser(this.company);

        return saved;
    }

}
````

### Class CreateClientUI
```
package app.ui.console;

import app.controller.ClientController;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateClientUI implements Runnable {
    private ClientController ctrl;
    private List<String> sexes;

    public CreateClientUI() {
        this.ctrl = new ClientController();
        this.sexes = new ArrayList<String>();
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        sexes.add("Male");
        sexes.add("Female");
        sexes.add("Other");
        boolean cont = true;
        do {
            boolean exception = false;
            do {
                try {

                    String cc = Utils.readLineFromConsole("Please enter the cc of the new Client");
                    String name = Utils.readLineFromConsole("Please enter the name of the new Client");
                    String nhs = Utils.readLineFromConsole("Please enter the nhs number of the new Client");
                    Date birthDate = Utils.readDateFromConsole("Please enter the birth date Client");
                    int sexIndex = Utils.showAndSelectIndex(sexes, "Please choose your sex");
                    char sex = ' ';
                    if (sexIndex == 0) {
                        sex = 'M';
                    } else if (sexIndex == 1) {
                        sex = 'S';
                    }
                    String tif = Utils.readLineFromConsole("Please enter the TIF number of the new Client");
                    String phoneNumber = Utils.readLineFromConsole("Please enter the phone number of the new Client");
                    String email = Utils.readLineFromConsole("Please enter the email of the new Client");
                    ctrl.createClient(cc, nhs, birthDate, sex, tif, phoneNumber, email, name);
                    exception = false;
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("An error occurred during the creation during the creation of the Parameter please try again");
                    exception = true;
                }
            } while (exception);

            cont = Utils.confirm("The following Client was created do you want to save? (s/n) \n" + ctrl.getClient().toString());
            if (cont){
                ctrl.saveClient();
            }


        } while (!cont);

    }
}
```

# 6. Integration and Demo

- A new menu was added for the actor Receptionist
- 2 new User roles were added: Client and Receptionist

# 7. Observations

We are passing a lot of information from UI layer to Domain layer, in the next sprints we might need to use data transfer objects to pass this information

