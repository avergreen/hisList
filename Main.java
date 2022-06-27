package hisLite;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //stosuje metody, ktore wczytuja potrzebne dane z pliku do poszczegolnej listy w klasie
        Doctor.loadDoctors();
        Patient.loadPatients();
        Visit.loadVisits();

        System.out.println("Find the doctor with most visits");
        System.out.println(Doctor.findTheDoctorWithMostVisits(Doctor.getDoctorsList()));

        System.out.println("Find the patient with most visits");
        System.out.println(Patient.findThePatientWithMostVisits(Patient.getPatientList()));

        System.out.println("Find medical speciality which is most common in doctorsList");
        System.out.println(Doctor.mostWantedMedicalSpeciality(Doctor.getDoctorsList()));

        System.out.println("Find most common year in visitsDatabase");
        System.out.println(Visit.findMostCommonYearinVisitsDatabase(Visit.getListOfVisits()));

        System.out.println("Find five oldest doctors");
        System.out.println(Doctor.getFiveOldestDoctors(Doctor.getDoctorsList()));

    }

}
