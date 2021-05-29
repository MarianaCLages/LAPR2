# OO Analysis

The construction process of the domain model is based on the client specifications, especially the nouns (for _
concepts_) and verbs (for _relations_) used.

## Rationale to identify domain conceptual classes

To identify domain conceptual classes, start by making a list of candidate conceptual classes inspired by the list of
categories suggested in the book "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and
Iterative Development".

### _Conceptual Class Category List_

**Business Transactions**

- Test

---

**Transaction Line Items**

- Sample

---

**Product/Service related to a Transaction or Transaction Line Item**


---

**Roles of People or Organizations**

- Client
- Receptionist
- Medical Lab Technician
- Specialist Doctor
- Administrator
- Clinical Chemistry Technologist
- Laboratory Coordinator
- User

---

**Places**

- Clinical Analysis Laboratory
- Chemical Laboratory
- Company's Headquarters

---

**Noteworthy Events**

- Chemical Analysis

---

**Physical Objects**

- blood sample
- swab sample

---

**Descriptions of Things**

-Parameter

- Type of Test
- Type of Sample

--- 

**Organizations**

- Many Labs

---

**Other External/Collaborating Systems**

- Barcode External API
- AutomaticValidationModule

---


**Documents mentioned/used to perform some work/**

- Lab Order
- Notification
- Organization Role
- Results
- Report

---

### **Rationale to identify associations between conceptual classes**

| Concept (A)                     | Association                 | Concept (B)                                                                                                                                    |
| ------------------------------- | :-------------------------: | ---------------------------------------------------------------------------------------------------------------------------------------------: |
| Administrator                   | create                      | Parameter                                                                                                                                      |
| Administrator                   | choose for Parameter        | ParameterCategory                                                                                                                              |
| Administrator                   | register a new              | ClinicalAnalysisLaboratory                                                                                                                     |
| Administrator                   | register                    | Employee                                                                                                                                       |
| Administrator                   | creates                     | Parameter                                                                                                                                      |
| Administrator                   | creates                     | TypeTest                                                                                                                                       |
| Administrator                   | creates                     | ParameterCategory                                                                                                                              |
| Administrator                   | is a                        | Employee                                                                                                                                       |
| AutomaticValidationModule       | validate                    | Results                                                                                                                                        |
| ChemicalAnalysis                | take place in               | ChemicalLaboratory                                                                                                                             |
| ChemicalLaboratory              | is located in               | CompanyHeadquarters                                                                                                                            |
| Client                          | is a                        | User                                                                                                                                           |
| Client                          | to execute the test needs a | LabOrder                                                                                                                                       |
| ClinicalAnalysisLaboratory      | operates                    | TypeTest                                                                                                                                       |
| ClinicalChemistryTechnologist   | works on                    | ChemicalLaboratory                                                                                                                             |
| ClinicalChemistryTechnologist   | records                     | Results                                                                                                                                        |
| ClinicalChemistryTechnologist   | analyse                     | Sample                                                                                                                                         |
| Company                         | knows all the               | OrganizationRole                                                                                                                               |
| Company                         | sends to NHS                | Covid19Report                                                                                                                                  |
| Company                         | employs                     | Administrator                                                                                                                                  |
| Company                         | employs                     | Administrator                                                                                                                                  |
| Company                         | knows                       | Client                                                                                                                                         |
| Company                         | owns                        | ClinicalAnalysisLaboratory                                                                                                                     |
| Company                         | employs                     | Employee                                                                                                                                       |
| Company                         | adopts                      | ParameterCategory                                                                                                                              |
| Company                         | employs                     | Receptionist                                                                                                                                   |
| Company                         | knows                       | User                                                                                                                                           |
| Company                         | owns                        | ChemicalLaboratory                                                                                                                             |
| Company                         | owns                        | CompanyHeadquarters                                                                                                                            |
| Employee                        | is characterized by a       | OrganizationRole                                                                                                                               |
| Employee                        | can be a                    | Administrator                                                                                                                                  |
| Employee                        | can be a                    | ClinicalChemistryTechnologist                                                                                                                  |
| Employee                        | can be a                    | LaboratoryCoordinator                                                                                                                          |
| Employee                        | can be a                    | MedicalLabTechnician                                                                                                                           |
| Employee                        | can be a                    | Receptionist                                                                                                                                   |
| Employee                        | can be a                    | SpecialistDoctor                                                                                                                               |
| Employee                        | can be a                    | SpecialistDoctor                                                                                                                               |
| Employee                        | is a                        | User                                                                                                                                           |
| LaboratoryCoordinator           | verifies                    | Report                                                                                                                                         |
| LaboratoryCoordinator           | uses                        | AutomaticValidationModule                                                                                                                      |
| MedicalLabTechnician            | collects and records        | Sample                                                                                                                                         |
| MedicalLabTechnician            | works on                    | ClinicalAnalysisLaboratory                                                                                                                     |
| Notification                    | delivered to                | Client                                                                                                                                         |
| Parameter                       | has a                       | ParameterCategory                                                                                                                              |
| Receptionist                    | works on                    | ClinicalAnalysisLaboratory                                                                                                                     |
| Receptionist                    | is a                        | User                                                                                                                                           |
| Receptionist                    | register in application     | Client                                                                                                                                         |
| Receptionist                    | register in application     | Test                                                                                                                                           |
| Report                          | is sent by                  | Notification                                                                                                                                   |
| Results                         | is sent by                  | Notification                                                                                                                                   |
| Sample                          | is identified using         | BarcodeExternalAPI                                                                                                                             |
| Sample                          | has a                       | TypeSample                                                                                                                                     |
| Sample                          | used in                     | Test                                                                                                                                           |
| SpecialistDoctor                | writes                      | Report                                                                                                                                         |
| SpecialistDoctor                | analyse                     | Results                                                                                                                                        |
| Test                            | as a                        | TypeTest                                                                                                                                       |
| Test                            | has                         | Parameter                                                                                                                                      |
| TypeSample                      | has                         | Parameter                                                                                                                                      |

## Domain Model

![DM.svg](DM.svg)