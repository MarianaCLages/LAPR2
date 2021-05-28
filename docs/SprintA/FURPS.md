# Supplementary Specification (FURPS+)

## Functionality

### Localization

- _"The application must support the English language only"_

### Help

- _There should exist a user-manual._

### Security

- _"All those who wish to use the application must be authenticated with a password holding seven alphanumeric
  characters, including three capital letters and two digits."_

## Usability

##### Accessibility

- _"Once the laboratory coordinator confirms that everything was done correctly, the client receives a notification
  alerting that the results are already available in the central application and informing that he/she must access the
  application to view those results."_

- _"The client receives the notification by SMS and e-mail."_

- _"... the results are also available in the central application where the medical lab technicians who collect the
  samples, the clinical chemistry technologist, the specialist doctor, and the laboratory coordinator can check them."_

- _"... the application must allow ordering the clients by TIF and by name."_
- _"... at least two sorting algorithms should be evaluated and documented in the application user manual"_

##### Aesthetics

- _"The user interface must be simple, intuitive and consistent"_

##### Consistency

- _"The samples are sent daily to the chemical laboratory... the medical lab technician records the samples in the
  system ..."_

- _"... the medical lab technician records the samples in the system, associating the samples with the client/test, and
  identifying each sample with a barcode that is automatically generated using an external API."_

- _"... the application uses an external module that is responsible for doing an automatic validation using test
  reference values"_

## Reliability

### Accuracy

- _"... the application uses an external module that is responsible for doing an automatic validation using test
  reference values."_

### Throughput

- _"... an internal code, an NHS code, a description that identifies the sample collection method, the date and time
  when the samples were collected, the date and time of the chemical analysis, the date and time of the diagnosis made
  by the specialist doctor, the date and time when the laboratory coordinator validated the test, and the test type (
  whether it is blood test or Covid test)."_

### Availability

- _"The system should not fail more than 5 days in one year"_

## Performance

### Response time

- _"Any interface between a user and the system shall have a maximum response time of 3 seconds"_

### Start-up time

- _"The system should start up in less than 10 seconds "_

## Supportability

#### Auditability

- _"... the application should implement a brute-force algorithm (an algorithm which examines each subsequence) to
  determine the contiguous subsequence with maximum sum, for any interval of time registered."_

- _"... the company needs to: identify the number of Covid-19 tests performed, identify all positive results to Covid-19
  tests, report the total number of Covid-19 cases per day, per week and per month of the year, and send the forecasts
  for these same time horizons (the number of Covid-19 cases for the following day, next week and next month)."_

- _"Only the specialist doctor is allowed to access all client data."_

#### Localizability

- _"The application must support the English language only."_

#### Scalability

- _"... the company needs to: identify the number of Covid-19 tests performed, identify all positive results to Covid-19
  tests, report the total number of Covid-19 cases per day, per week and per month of the year, and send the forecasts
  for these same time horizons (the number of Covid-19 cases for the following day, next week and next month)."_

#### Testability

- _"The unit tests should be implemented using the JUnit 4 framework."_

## +

### Design Constraints

- _"The application must be developed in Java language using the IntelliJ IDE or Netbeans"_
- "The application graphical interface is to be developed in JavaFX 11"
- _"During system development, the team must adopt best practices for identifying requirements and for OO software
  analysis and design"_
- _"The application should use object serialization to ensure data persistence between two runs of the application."_
- _"The JaCoCo plugin should be used to generate the coverage report."_

### Implementation languages

- _Java language_

### Interface Constraints

#### Platform support

- _"The application should run on all platforms for which there exists a Java Virtual Machine"_

#### Resource limits

- _"The application will be deployed to a machine with 8GB of RAM."_

#### External systems

- _"... the medical lab technician records the samples in the system, associating the samples with the client/test, and
  identifying each sample with a barcode that is automatically generated using an external API."_
- _"To facilitate and simplify the validation work performed by the specialist doctor, the application uses an external
  module that is responsible for doing an automatic validation using test reference values."_

#### Interface formats

- _"The user interface must be simple, intuitive and consistent."_
