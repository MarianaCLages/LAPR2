# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._

### Auditing
- _"For any interval of time, for example one week (6 working days with 12 working hours per day), the difference between the number of new tests and the number of results available to the client during each half an hour period is computed. In that case, a list with 144 integers is obtained, where a positive integer means that in such half an hour more tests were processed than results were obtained, and a negative integer means the opposite. Now, the problem consists in determining what the contiguous subsequence of the initial sequence is, whose sum of their entries is maximum. This will show the time interval, in such week, when the company was less effective in responding. So, the application should implement a brute-force algorithm (an algorithm which examines each subsequence) to determine the contiguous subsequence with maximum sum, for any interval of time registered."_

### Localization
- _"The application must support the English language only"_


### Help
- _There should exist a user-manual_

### Security
- _"All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits."_ 



## Usability 

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._
- #####Accessibility
- _"Once the laboratory coordinator
 confirms that everything was done correctly, the client receives a notification alerting that the
 results are already available in the central application and informing that he/she must access the
 application to view those results."_
 
- _"The client receives the notification by SMS and e-mail."_
 
- _"... the results are also available in the central application where the medical lab technicians
     who collect the samples, the clinical chemistry technologist, the specialist doctor, and the laboratory
     coordinator can check them."_

- _"... the application must allow ordering the clients by TIF and by
  name."_    
  
-  _"... at least two sorting algorithms should be evaluated and
   documented in the application user manual"_
   
 
- #####Aesthetics
_"All the images/figures produced during the software development process should be recorded in
 SVG format. "_
 

- #####Consistency

_"The samples are sent daily to the chemical laboratory... the medical lab technician records the samples in the system ..."_

_"... the medical lab technician records the samples in the system,
     associating the samples with the client/test, and identifying each sample with a barcode that is
     automatically generated using an external API."_

_"... the application uses an external module that is responsible for doing an automatic validation using test reference
 values"_
 

## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._


### Accuracy

- _"the application uses an external module that is responsible for doing an automatic validation using test reference
values."_
  
### Throughput
- _"an internal code, an NHS code, a description that identifies the sample collection method, the date and time when the samples
were collected, the date and time of the chemical analysis, the date and time of the diagnosis made
by the specialist doctor, the date and time when the laboratory coordinator validated the test, and the
test type (whether it is blood test or Covid test)."_

## Supportability

####Auditability
- _"the application should implement a brute-force algorithm (an algorithm which examines each subsequence) to determine the contiguous subsequence with maximum sum, for any interval of time registered."_

- _"the company needs to: identify the number of Covid-19 tests performed, identify all positive results to Covid-19 tests, report the total number of Covid-19 cases per day, per week and per month of the year, and send the forecasts for these same time horizons (the number of Covid-19 cases for the following day, next week and next month)."_

- _"Only the specialist doctor is allowed to access all client data."_

####Localizability
- _"The application must support the English language only."_

####Scalability
- _"...  the company
      needs to: identify the number of Covid-19 tests performed, identify all positive results to Covid-19
      tests, report the total number of Covid-19 cases per day, per week and per month of the year, and
      send the forecasts for these same time horizons (the number of Covid-19 cases for the following
      day, next week and next month)."_
 


- ####Testability
- _" The unit tests should be implemented using the JUnit 4 framework."_


## +

###Design Constraints
- _"The application must be developed in Java language using the IntelliJ IDE or Netbeans"_
- "The application graphical interface is to be developed in JavaFX 11"
- _"During system development, the team must adopt best practices for identifying requirements
  and for OO software analysis and design"_
- _"The application should use object serialization to ensure data persistence between two runs of the
  application."_


### Implementation languages
- _Java language_



#### External systems

- _"the medical lab technician records the samples in the system,
  associating the samples with the client/test, and identifying each sample with a barcode that is
  automatically generated using an external API."_
- _"To facilitate and simplify the validation work performed by the specialist doctor, the application
  uses an external module that is responsible for doing an automatic validation using test reference
  values."_  

#### Interface formats

- _"All the images/figures produced during the software development process should be recorded in
SVG format."_

