# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._



(fill in here)



## Usability 

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._
- #####Accessibility 
######(The ease with which different facets of the system are exercised.)

"Once the laboratory coordinator
 confirms that everything was done correctly, the client receives a notification alerting that the
 results are already available in the central application and informing that he/she must access the
 application to view those results."
 
 "The client receives the notification by SMS and e-mail."
 
 "... the results are also available in the central application where the medical lab technicians
     who collect the samples, the clinical chemistry technologist, the specialist doctor, and the laboratory
     coordinator can check them."

"... the application must allow ordering the clients by TIF and by
  name."    
  
  "... at least two sorting algorithms should be evaluated and
   documented in the application user manual"
  
  
  
  
  
  
  
 
- #####Aesthetics
######(The aesthetic quality of the user interface.)
"All the images/figures produced during the software development process should be recorded in
 SVG format. "
 

- #####Consistency
######(The consistent use of mechanisms employed in the user interface. This applies both within the system, and with other systems.)

"The samples are sent daily to the chemical laboratory... the medical lab technician records the samples in the system ..."

"... the medical lab technician records the samples in the system,
     associating the samples with the client/test, and identifying each sample with a barcode that is
     automatically generated using an external API."

"the application uses an external module that is responsible for doing an automatic validation using test reference
 values"
 

## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

Requirement
- Reliability refers to the consistency of a measure.

Accuracy

- "the application
uses an external module that is responsible for doing an automatic validation using test reference
values."

Availability




Recoverability

- "The implemented algorithm should be analysed in terms of its worst-case time complexity, and it should be compared to a provided benchmark algorithm."


## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._


Throughput
- "an internal code, an NHS code, a description that identifies the sample collection method, the date and time when the samples
were collected, the date and time of the chemical analysis, the date and time of the diagnosis made
by the specialist doctor, the date and time when the laboratory coordinator validated the test, and the
test type (whether it is blood test or Covid test)."

## Supportability
<<<<<<< Updated upstream
The supportability requirements gathers several characteristics, such as:
=======

_The supportability requirements gathers several characteristics, such as:
>>>>>>> Stashed changes
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more. 

- ####Adaptability
######(The ease with which the system is adapted to new environments.)
"Many Labs is a company that operates in the English market..."

"... Many Labs has exclusivity for Covid-19 tests throughout the territory, which means that no other company can perform this type of testing."

- ####Auditability
######(The ease with which the system provides audit trails of its execution.)
"the application should implement a brute-force algorithm (an algorithm which examines each subsequence) to determine the contiguous subsequence with maximum sum, for any interval of time registered."

"the company needs to: identify the number of Covid-19 tests performed, identify all positive results to Covid-19 tests, report the total number of Covid-19 cases per day, per week and per month of the year, and send the forecasts for these same time horizons (the number of Covid-19 cases for the following day, next week and next month)."

"Only the specialist doctor is allowed to access all client data."
(fill in here )
- ####Localizability
######(The level to which the system supports multiple human languages.)
"The application must support the English language only."

- ####Scalability
######(The ease with which the system can scale in terms of data volumes and users.)
"The complexity analysis must be accompanied by the observation of the execution time of the algorithms for inputs of variable size in order to observe the asymptotic behaviour."
 "...  the company
      needs to: identify the number of Covid-19 tests performed, identify all positive results to Covid-19
      tests, report the total number of Covid-19 cases per day, per week and per month of the year, and
      send the forecasts for these same time horizons (the number of Covid-19 cases for the following
      day, next week and next month)."
"The accuracy of the prediction models should be analysed and
 documented in the application user manual..."
 


- ####Testability
######(The ease with which the system is tested.)
" The unit tests should be implemented using the JUnit 4 framework."


## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._
  

(fill in here )


### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

Implementation languages

- Java language



### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._


External systems

- "the medical lab technician records the samples in the system,
  associating the samples with the client/test, and identifying each sample with a barcode that is
  automatically generated using an external API."
- "To facilitate and simplify the validation work performed by the specialist doctor, the application
  uses an external module that is responsible for doing an automatic validation using test reference
  values."  

Interface formats

- "All the images/figures produced during the software development process should be recorded in
SVG format."

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

(fill in here )