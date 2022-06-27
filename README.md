# hisList
Hospital Information System based on Classes and Mehods

Welcome to the hisLite (Hospital Information System). The program contains three classes with methods, which are implemented in Main Class

I've created three files which contains data inputs to three Classes - doctorsDatabase.txt, patientsDatabase.txt, visitsDatabase.txt.

File doctorsDatabase.txt contains information about idOfDoctor, surname, name, medicalSpeciality, dateOfBirth, peselNumber
  Example:
  23 Smith Monica pediatrist 1965-03-16 879-122-69-94 65031687654
  34 Simpson Scarlett nephrologist 1965-03-16 879-122-69-94 65031687654

File patientsDatabase.txt contains: idOfPatient, surname, name, peselNumber, dateOfBirth
  Example
  122 Lorenzo Stephanie 73050512356 1973-05-05
  124 Minardi Joe 88030422345 1988-03-04

File visitsDatabase.txt contains information about medical visits: idOfDoctor, idOfPatient, dateOfVisit
  Example:
  23 124 2006-12-13
  34 122 2007-02-20

From the information contained in Classes, I've created orders for the hisLite system:
         - find doctor with most visits,
         - find patient with most visits
         - find most common medialSpeciality among doctors,
         - find which year is the most common is visitsDatabase,
         - find five oldest doctors
