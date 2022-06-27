package hisLite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Patient {
    
    private int patientId;
    private String surname;
    private String name;
    private Long peselNumber;
    private String dateOfBirth;

    //tworze ekstensje
    private static List<Patient> patientList = new ArrayList<>();

    //arraylist dla wizyty
    private static List<Visit> visits = new ArrayList<>();

    //arraylist dla lekarze
    private List<Doctor> doctors = new ArrayList<>();

    //konstruktor
    public Patient(int patientId, String surname, String name, Long peselNumber, String dateOfBirth) {
        this.patientId = patientId;
        this.surname = surname;
        this.name = name;
        this.peselNumber = peselNumber;
        this.dateOfBirth = dateOfBirth;

        patientList.add(this);

    }

    public int getPatientId() {
        return patientId;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }


    public static List<Visit> getVisits() {
        return visits;
    }
    
    public static List<Patient> getPatientList() {
        return patientList;
    }

    public void addVisit(Visit wizyta) {
        if (!visits.contains(wizyta)) {
            visits.add(wizyta);
        }
    }

    @Override
    public String toString() {
        return "Patients{" +
                "patient Id=" + patientId +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", pesel number='" + peselNumber + '\'' +
                ", date of birth='" + dateOfBirth + '\'' +
                '}';
    }

    //metoda na wczytanie pacjentow z pliku
    public static List<Patient> loadPatients() throws IOException {
        List<Patient> loadedPatient = new ArrayList<>();
        File patientsFile = new File("src/hisLite/patientsDatabase.txt");

        FileReader sl = new FileReader(patientsFile);
        BufferedReader br = new BufferedReader(sl);

        br.readLine();
        String line = "";

        while ((line = br.readLine()) != null) {

            String[] args = line.split("\\t");

            int patientId = Integer.parseInt(args[0]);
            String surname = args[1];
            String name = args[2];
            Long peselNumber = Long.parseLong(args[3]);
            String dateOfBirth = args[4];

            new Patient(patientId, surname, name, peselNumber, dateOfBirth);

        }
        return loadedPatient;
    }

    public static Patient findPatientsId(int patientId) {
        Patient foundPatient = null;
        for (Patient patient : patientList) {
            if (patient.getPatientId() == patientId) {
                foundPatient = patient;
            }
        }
        return foundPatient;
    }
    
    public static Patient findThePatientWithMostVisits(List<Patient> list) {
        Patient patientWhichLikesGoingToTheDoctor = list.get(0);
        for (Patient pacjent : list) {
            if (pacjent.getVisits().size() > patientWhichLikesGoingToTheDoctor.getVisits().size()) {
                patientWhichLikesGoingToTheDoctor = pacjent;
            }
        }
        return patientWhichLikesGoingToTheDoctor;
    }

}
