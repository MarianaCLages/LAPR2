# US 4 -  To register a test to be performed to a registered client
## 1. Requirements Engineering


### 1.1. User Story Description

As a receptionist of the laboratory, I intend to register a test to be performed to a
registered client.

### 1.2. Customer Specifications and Clarifications 
**From the specifications document:**
> "Typically, the client arrives at one of the clinical analysis laboratories with a lab order prescribed by
   a doctor. Once there, a receptionist asks the client’s citizen card number, the lab order (which
   contains the type of test and parameters to be measured), and registers in the application the test to
   be performed to that client."
>
**From the client clarifications:**

> **Question:** 
>  
> **Answer:**
-
> **Question:** 
>  
> **Answer:**

### 1.3. Acceptance Criteria
- **AC1:** The receptionist must select the parameters to be analysed from
           all possible parameters in accordance with the test type.

- **AC2:** Citizen Card number is a 16-digit number.


### 1.4. Found out Dependencies
* There is a dependency to "US3- To register a client." since the client must be registered in the application to register their test.


### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * citizen card number
	* type of test
	
* Selected data:
    * parameters 
	

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

*Insert here a SSD depicting the envisioned Actor-System interactions and throughout which data is inputted and outputted to fulfill the requirement. All interactions must be numbered.*

![USXX-SSD](USXX-SSD.svg)


### 1.7 Other Relevant Remarks

*Use this section to capture other relevant information that is related with this US such as (i) special requirements ; (ii) data and/or technology variations; (iii) how often this US is held.* 


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 
*In this section, it is suggested to present an excerpt of the domain model that is seen as relevant to fulfill this requirement.* 

![USXX-MD](USXX-MD.svg)

### 2.2. Other Remarks

*Use this section to capture some aditional notes/remarks that must be taken into consideration into the design activity. In some case, it might be usefull to add other analysis artifacts (e.g. activity or state diagrams).* 



## 3. Design - User Story Realization 

### 3.1. Rationale

**The rationale grounds on the SSD interactions and the identified input/output data.**

| Interaction ID | Question: Which class is responsible for... | Answer  | Justification (with patterns)  |
|:-------------  |:--------------------- |:------------|:---------------------------- |
| Step 1: wants to register a test|	... instantiating a new Test?| TestStore |Creator R1/2: we look to decrease the responsibilities assign to the the Company class in order to go accordingly to GRASP|
| |... interacting with the actor?|CreateTestUI|Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model|
| |... coordinating the US?|CreateTestController|Controller| 
| Step 2: types the requested data|... create the TestStore?| Company |IE: Company knows all lists of objects in the system|                              |
| | ...saving the inputted data? | Test | IE: object created has its own data.
| | ..local validation?| Test|IE: knows its own data|
|Step 3: shows a list of parameters and asks to select| ... knowing the parameters to show? | ParameterStore | IE: knows all parameters|
| Step 4: selects parameters| ...saving the selected parameter?| Test|IE: test created in step 1 is classified in parameters.|
| | ..local validation?| Test|IE: knows its own data|
| Step 5: confirms data| ...global validation?| TestStore | IE: knows all tests|
| | ... saving the created test? | TestStore |IE: owns all its tests|            

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Company
 * Test
 * Parameter

Other software classes (i.e. Pure Fabrication) identified: 
 * CreateTestUI  
 * CreateTestController
 * TestStore
 * ParameterStore

## 3.2. Sequence Diagram (SD)

*In this section, it is suggested to present an UML dynamic view stating the sequence of domain related software objects' interactions that allows to fulfill the requirement.* 

![USXX-SD](USXX-SD.svg)

## 3.3. Class Diagram (CD)

*In this section, it is suggested to present an UML static view representing the main domain related software classes that are involved in fulfilling the requirement as well as and their relations, attributes and methods.*

![USXX-CD](USXX-CD.svg)

# 4. Tests 
*In this section, it is suggested to systematize how the tests were designed to allow a correct measurement of requirements fulfilling.* 

**_DO NOT COPY ALL DEVELOPED TESTS HERE_**

**Test 1:** Check that it is not possible to create an instance of the Example class with null values. 

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Exemplo instance = new Exemplo(null, null);
	}

*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)

*In this section, it is suggested to provide, if necessary, some evidence that the construction/implementation is in accordance with the previously carried out design. Furthermore, it is recommeded to mention/describe the existence of other relevant (e.g. configuration) files and highlight relevant commits.*

*It is also recommended to organize this content by subsections.* 

# 6. Integration and Demo 

*In this section, it is suggested to describe the efforts made to integrate this functionality with the other features of the system.*


# 7. Observations

*In this section, it is suggested to present a critical perspective on the developed work, pointing, for example, to other alternatives and or future related work.*





