# US 01 - view the results

## 1. Requirements Engineering

### 1.1. User Story Description

As a client, I want to access the application to view the results of the tests I have performed.
### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> The client tests must be shown ordered from the most
recent to the oldest one. 

> The test results are shown only after the client has
selected a test.

> The client receives a notification alerting that the results are already available in the central application and informing that he/she must access the application to view those results.

**From the client clarifications:**

> **Question:** In US01 which date should be used to arrange the tests in order?The date the test is done or the validation date?

> **Answer:** The test registration date.
> https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=8787#p11486


> **Question:** What are the data to show the customer? and in what way do we have to show? do you have any examples you can give us?

> **Answer:** I want to access the application to view the results of the tests I have performed. This includes the report made by the specialist doctor.
The client tests must be shown ordered from the most recent to the oldest one. The test results are shown only after the client has selected a test.
https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=9038#p11781
> 
### 1.3. Acceptance Criteria

* **AC1:** The client needs to be logged in the system.

* **AC2:** The client tests must be shown ordered from the most
  recent to the oldest one.

* **AC3:** The test results are shown only after the client has
  selected a test.

* **AC4:** The tests must be in Validated state.

### 1.4. Found out Dependencies

- The US5 has dependency with the US03 since it needs the registered clients in the system. It also has dependency in US04 because the client's test must be in the system too.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:


* Selected data:
    * Test

**Output Data:**

* Results

### 1.6. System Sequence Diagram (SSD)

![US1_SSD](US1_SSD.svg)

### 1.7 Other Relevant Remarks

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt

![US1_MD](US1_MD.svg)

### 2.2. Other Remarks

## 3. Design - User Story Realization

### 3.1. Rationale

| Interaction ID                                   | Question: Which class is responsible for...        | Answer                | Justification (with patterns)                                                     |
| :-------------                                   | :---------------------                             | :------------         | :----------------------------                                                     |
| Step 1: wants to view the results of a test      | ... interacting with the actor?                    | ViewResultsUI         | IE: the interface interacts with the actor.                                       |
|                                                  | ... coordinating the US?                           | ViewResultsController | Controller                                                                        |
| Step 2: shows test list associated to the client | ... having the test list?                          | TestStore             | IE: it knows and saves all the information related to the tests.                  |
|                                                  | ... knowing the users in the system?               | UserSession           | IE: it knows and saves all the information related to the users.                  |
|                                                  | ... having the client list?                        | ClientStore           | IE: it knows and saves all the information related to the clients.                |
|                                                  | ... organizing the tests of the respective client? | Test Store            | IE: it knows and saves all the information related to the tests.                  |
| Step 3: selects a test from the list             | ... selecting the test?                            | ViewResultsUI         | IE: the interface interacts with the actor.                                       |
| Step 4: show the results                         | ... asking for the results?                        | Test                  | IE: it knows and saves all the information related to the results.                |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Company
* Test
* Client

Other software classes (i.e. Pure Fabrication) identified:

* ViewResultsUI
* ViewResultsController
* User
* UserSession
* TestStore
* ClientStore

## 3.2. Sequence Diagram (SD)

![US1_SD](US1_SD.svg)

## 3.3. Class Diagram (CD)

![US1_CD](US1_CD.svg)

# 4. Tests

**Test 1: The client tests must be shown ordered from the most recent to the oldest one.**

````
@Test
    public void sortDateTest() {
        ParameterCategoryStore cat = new ParameterCategoryStore();
        ParameterCategory pc1 = new ParameterCategory("AH000", "Hemogram");
        cat.add(pc1);
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(pc1);
        List<Parameter> pa = new ArrayList<>();
        Parameter p1 = new Parameter("AH000", "Nome", "description", pc1);
        pa.add(p1);
        TestType testType = new TestType("BL000", "description", "sei lá", cat);
        TestStore store = new TestStore();
        app.domain.model.Test t1 = store.createTest("123456789187", "1234567890123456", testType, cat1, pa);
        app.domain.model.Test t2 = store.createTest("123456789185", "1234567890123456", testType, cat1, pa);
        app.domain.model.Test t3 = store.createTest("123456782187", "1234567890123455", testType, cat1, pa);

        store.addTest(t3);
        store.addTest(t2);
        store.addTest(t1);

        List<app.domain.model.Test> list = store.sortDate("1234567890123456");

        

        List<app.domain.model.Test> expected = new ArrayList<>();
        expected.add(t2);
        expected.add(t1);
        Assert.assertEquals(list,expected);
    }
   ````


# 5. Construction (Implementation)

## Class ViewResultsUI

```

package app.ui.gui.client;

import app.controller.*;
import app.domain.shared.Constants;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import app.controller.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewResultsUI implements Initializable{

    private final SceneController sceneController = SceneController.getInstance();
    private final ViewResultsController vrc = new ViewResultsController();
    private final App app = sceneController.getApp();

    @FXML
    private ListView<String> ListView1;

    @FXML
    private Label label1;

    @FXML
    private Button button1;


    public void returnToMenu(ActionEvent event) {
        sceneController.switchMenu(event, Constants.CLIENT_UI);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label1.setText(null);



    }
    public void confirm() {
        button1.setDisable(true);
        vrc.sortedDateList();
        ListView1.getItems().addAll(vrc.getTestSortedList());
        selectedList();
        vrc.getTestSortedList().removeAll(vrc.getTestSortedList());

    }
    private void selectedList() {
        ListView1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String test) {
                label1.setText(vrc.getDiagnosis(test));
            }
        });
    }

}


```

## Class ViewResultsController

```` 
package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.stores.ClientStore;
import app.domain.stores.TestStore;

import java.util.List;

public class ViewResultsController {

    private Company company;
    private TestStore store;
    private ClientStore cstore;

    public ViewResultsController(Company company) {

        this.company = company;
        cstore = App.getInstance().getCompany().getClientList();
        store = App.getInstance().getCompany().getTestList();
    }

    public ViewResultsController() {
        this(App.getInstance().getCompany());
    }

    public void sortedDateList() {
        Client client = cstore.getClientByEmail(App.getInstance().getCurrentUserSession().getUserId().toString());
        store.sortDate(client.getTinNumber());
    }

    public String getDiagnosis(String test) {
        String[] testSplitted = test.split(",");
        String[] idTestSplitted = testSplitted[1].split("=");
        Test t = store.getTestByCode(idTestSplitted[1]);

        return t.getResults();
    }

    public List<String> getTestSortedList(){

        return store.getTestSortedListString();
    }
}

````

## Class TestStore
````
    public List<Test> sortDate(String clientTin) {
        Comparator<Test> comparator1 = (o1, o2) -> {
            LocalDateTime d1 = o1.getDate();
            Date date1 = Date.from(d1.atZone(ZoneId.systemDefault()).toInstant());
            LocalDateTime d2 = o2.getDate();
            Date date2 = Date.from(d2.atZone(ZoneId.systemDefault()).toInstant());

            if (date1.before(date2)) {
                return 1;
            } else if (date1.after(date2)) {
                return -1;
            } else {
                return 0;
            }

        };
        List<Test> testList2 = getTestByTin(clientTin);
        Collections.sort(testList2, comparator1);

        for (Test test : testList2) {
            testSortedListString.add(test.toString() + "\n");
        }
        return testList2;
    }
    public Test getTestByCode(String testId) {
        for (Test t1 : this.array) {
            if (t1.getTestNhsNumber().equals(testId)) {
                return t1;
            }
        }
        return null;
    }
    public List<String> getTestSortedListString() {

        return testSortedListString;
    }
````

## Class ClientStore
````
    public Client getClientByEmail(String email) {
        for (Client c1 : this.array) {
            if (c1.getEmail().equals(email)) {
                return c1;
            }
        }
        return null;
    }
````

## Class Test
````
    public String getResults() {
        StringBuilder listString = new StringBuilder();

        for (TestParameter s : this.testParam) {
            listString.append(s.getTestParameterResult()).append("\n");
        }
        return String.valueOf(listString);

    }
````

## Class Client
````
    public String getTinNumber() {
        return tinNumber;
    }

````

# 6. Integration and Demo

- A new option was added in the Client Menu

# 7. Observations




