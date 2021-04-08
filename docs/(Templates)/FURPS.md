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


(fill in here )

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
The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more. 



(fill in here )


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