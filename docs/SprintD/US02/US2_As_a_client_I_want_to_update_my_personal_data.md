# US 02 - As a client, I want to update my personal data

## 1. Requirements Engineering

### 1.1. User Story Description

*As a client, I want to update my personal data.*

### 1.2. Customer Specifications and Clarifications 

**From the client clarifications:**

> **Question:** Should the Client type in the attribute he/she wants to update or should he choose from a list?
> 
> **Answer:** In this sprint each team should develop a graphical user interface for US2. The application should show the current user data and the client can update any attribute.
>
> **Question:** To update the attribute, should the Client type the previous data?
> 
> **Answer:** In this sprint each team should develop a graphical user interface for US2. The application should show the current user data and the client can update any attribute.
>
> **Question:** What is the Data that the Client can update?
> 
> **Answer:** In this sprint each team should develop a graphical user interface for US2. The application should show the current user data and the client can update any attribute.
> 
> **Question** In US02 when the client changes the password what should the rules be for this new password? (for example, a digit limit)
> 
> **Answer:** The password should be randomly generated. It should have ten alphanumeric characters
> 
> **Question** Should/Can we show the current data held in the client's account? If so, we think that showing certain information(Password, for exemple) could violate some security purposes. How should we approach that situation?
>
> **Answer:** "The client can only update his: name, address, phone number and sex attributes. The other attributes can not be updated. There are some restrictions that forced me to make this decision."
> 
> **Question** When we update the client's information, should we send an email informing that the client's data has been changed? If so, what should this email contain?
> 
> **Answer:** Yes. The e-mail message should only inform the client that his personal data has been updated.
> 
> 
### 1.3. Acceptance Criteria
The client can only update his: name, address, phone number and sex attributes. The other attributes can not be updated.
The e-mail message should only inform the client that his personal data has been updated.
Develop a graphical user interface for US2
The application should show the current user data and the client can update any attribute.


***The client's information is updated***
### 1.4. Found out Dependencies

**From Sprint A:**

There is a dependency to:
* **"US3: As a receptionist of the laboratory, I want to register a client."** Since the client needs to be registered in the system in order to update his information

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
- Output Data
  - All client's data
  - (In)Success of the operation
  
### 1.6. System Sequence Diagram (SSD)

*Insert here a SSD depicting the envisioned Actor-System interactions and throughout which data is inputted and outputted to fulfill the requirement. All interactions must be numbered.*

![US2_SSD](US2_SSD.jpg)


### 1.7 Other Relevant Remarks

* The US should not have to be repeated very often since 

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 
*In this section, it is suggested to present an excerpt of the domain model that is seen as relevant to fulfill this requirement.* 

![US2_DM](US2_DM.jpg)


## 3. Design - User Story Realization 

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for... | Answer  | Justification (with patterns)  |
|:-------------  |:--------------------- |:------------|:---------------------------- |
| **Step/Msg 1:** Asks to change personal data |  interacting with the actor? | UpdateClientDataUI | **Pure Fabrication:** there is no reason to assign this responsibility to any existing class in the Domain Model |
|                                                        |  coordinating the US? | UpdateClientDataController | Controller |
| **Step/Msg 2:** requests input | n/a | | |
| **Step/Msg 3:** inputs data to be changed | knowing ClientStore? | Company | **IE:** ClientStore is initialized in Company |
|                                             |  knowing all the existent clients? | ClientStore | **IE:** knows its own clients |
| **Step/Msg 4:** Shows data and requests confirmation | n/a | | |
| **Step/Msg 5:** Confirms | 
| **Step/Msg 6:** Saves |  informing operation success? | UpdateClientDataUI | **IE:** responsible for user interaction |


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Company
 * Client

Other software classes (i.e. Pure Fabrication) identified: 
 * ClientUpdateUI  
 * UpdateClientDataController
 * ClientStore

## 3.2. Sequence Diagram (SD)

![US2_SD](US2_SD.jpg)
  

* SD_AddTestResult(parameterCode, result, metric)


## 3.3. Class Diagram (CD)

*In this section, it is suggested to present an UML static view representing the main domain related software classes that are involved in fulfilling the requirement as well as and their relations, attributes and methods.*

![US2_CD](US2_CD.jpg)

# 4. Tests

### ClientStore Test

**Test 1:**

    public void getPhoneNumberTest() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "ze@email.com", "Zé");

        String expected = "12345678910";
        String actual = client.getPhoneNumber();

        Assert.assertEquals(expected, actual);

    }

**Test 2:**
  
    public void getEmailTest() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "ze@email.com", "Zé");

        String expected = "ze@email.com";
        String actual = client.getEmail();

        Assert.assertEquals(expected, actual);

    }
**Test 3:**
  
    public void getTinTest() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "ze@email.com", "Zé");

        String expected = "1234567891";
        String actual = client.getTinNumber();

        Assert.assertEquals(expected, actual);

    }

**Test 4:**

    public void getCc() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "ze@email.com", "Zé");

        String expected = "1234567890123456";
        String actual = client.getCc();

        Assert.assertEquals(expected, actual);

    }

**Test 5:**
    
    public void setCc() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "ze@email.com", "Zé");
        String cc = "0034567890123456";
        client.setCc(cc);

        Assert.assertEquals(cc, client.getCc());
    }

**Test 6:**

    public void setNhs() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "ze@email.com", "Zé");
        String nhs = "0234567891";
        client.setNhs(nhs);

        Assert.assertEquals(nhs, client.getNhs());
    }

**Test 7:**

    public void setBirthDate() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "ze@email.com", "Zé");
        String birthDateString = "25-06-2000";
        Date birthDate = df.parse(birthDateString);
        client.setBirthDate(birthDate);

        Assert.assertEquals(birthDateString, df.format(client.getBirthDate()));
    }

**Test 8:**

    public void setSex() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "ze@email.com", "Zé");
        String sex = "F";

        client.setSex(sex);

        Assert.assertEquals(sex, client.getSex());
    }

**Test 9:**

    public void setTinNumber() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "ze@email.com", "Zé");
        String tin = "1234167891";

        client.setTinNumber(tin);

        Assert.assertEquals(tin, client.getTinNumber());
    }


**Test 10:**

    public void setPhoneNumber() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "ze@email.com", "Zé");
        String phoneNumber = "12345674110";

        client.setPhoneNumber(phoneNumber);

        Assert.assertEquals(phoneNumber, client.getPhoneNumber());
    }

**Test 11:**

    public void setEmail() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, 'M', "ze@email.com", "Zé");
        String email = "kikal@email.com";

        client.setEmail(email);

        Assert.assertEquals(email, client.getEmail());
    }

**Test 12:**

    public void addUser() throws ParseException {
        String strDate = "25-06-1950";
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = df.parse(strDate);

        Client client = new Client("12345678910", "1234567890123456", "1234567891", "1234567891", date, "email@gamil.com", "Zé");

        client.addUser(App.getInstance().getCompany());

    }

	

*It is also recommended organizing this content by subsections.* 

# 5. Construction (Implementation)

### Class ClientUpdateUI

    public class ClientUpdateUI {


    private UpdateClientDataController clientDataController = new UpdateClientDataController();

    private final SceneController sceneController = SceneController.getInstance();
    private final App app = sceneController.getApp();


    @FXML
    private TextArea informationArea;

    @FXML
    private TextField attributeField;

    @FXML
    private TextField newField;


    @FXML
    void showInfo(ActionEvent actionEvent) {
        informationArea.setText(clientDataController.getClient());
    }

    @FXML
    void updateInfo(ActionEvent actionEvent) {
        int i = Integer.parseInt(attributeField.getText());
        if (i > 0 && i <= 8) {
            String info = newField.getText();
            try {
                clientDataController.changeData(i, info);
                Alert updated = new Alert(Alert.AlertType.INFORMATION, "Your personal information was updated\n" +
                        "For security reasons restart the login session");
                updated.showAndWait();
            } catch (IllegalArgumentException | ParseException e) {
                Alert invalid = new Alert(Alert.AlertType.WARNING, "The information is invalid");
                invalid.showAndWait();
            }
        } else {
            Alert canceled = new Alert(Alert.AlertType.WARNING, "Invalid attribute number");
            canceled.showAndWait();
        }
        showInfo(null);
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        app.doLogout();
        sceneController.switchMenu(event, Constants.MAIN_SCREEN_UI);
    }
}


### Class UpdateClientDataController

    /**
     * The company that knows the tests
     */
    private final Company company = App.getInstance().getCompany();

    /**
     * Client who do the test
     */
    private final Client client;

    /**
     * The client store to get the client
     */
    private final ClientStore clientStore = company.getClientList();

    public UpdateClientDataController() {
        String email = App.getInstance().getCurrentUserSession().getUserId().toString();
        this.client = company.getClientList().getClient();
    }

    public String getClient(){
        return client.toString();
    }


    /**
     * Changes an attribute
     *
     * @param i The attribute to be changed
     * @param data The new attribute
     */
    public void changeData(int i, String data) throws ParseException {
        switch (i) {
            case 1:
                this.client.setPhoneNumber(data);
                break;

            case 2:
                this.client.setCc(data);
                break;

            case 3:
                this.client.setNhs(data);
                break;

            case 4:
                this.client.setTinNumber(data);
                break;

            case 5:
                Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(data);
                this.client.setBirthDate(date1);
                break;

            case 6:
                this.client.setSex(data);
                break;


            case 7:
                this.client.setEmail(data);
                break;

            case 8:
                this.client.setName(data);
                break;

            default:
                break;
        }

    }

    public void updateClientData(String cc, String nhs, Date birthDate, char sex, String tinNumber, String phoneNumber, String email, String name) {
        this.client.setCc(cc);
        this.client.setNhs(nhs);
        this.client.setBirthDate(birthDate);
        this.client.setSex(String.valueOf(sex));
        this.client.setTinNumber(tinNumber);
        this.client.setPhoneNumber(phoneNumber);
        this.client.setEmail(email);
        this.client.setName(name);

        clientStore.setClient(this.client);
        clientStore.saveClient();
        company.saveCompany();
    }





# 6. Integration and Demo

* Added ClientInformationUI
* Added ClientInformationController
* Added coverage and mutation tests
* A new option on the Client menu options was added: Change my personal information


# 7. Observations