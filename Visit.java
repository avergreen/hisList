package hisLite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Visit {

    private String dateOfVisit;
    private int yearOfVisit;

    private Patient patient;
    private Doctor doctor;

    private static List<Visit> listOfVisits = new ArrayList<>();

    public Visit(int idLekarza, int idPacjenta, String dateOfVisit) {
        this.doctor = Doctor.findTheDoctorByHisIdNumber(idLekarza);
        Doctor.findTheDoctorByHisIdNumber(idLekarza).addVisits(this);
        this.patient = Patient.findPatientsId(idPacjenta);
        Patient.findPatientsId(idPacjenta).addVisit(this);
        this.dateOfVisit = dateOfVisit;
        this.yearOfVisit = Integer.parseInt(dateOfVisit.substring(0, 4));

        listOfVisits.add(this);
    }

    public Patient getPatient() {
        return patient;
    }

    public int getYearOfVisit() {
        return yearOfVisit;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public static List<Visit> getListOfVisits() {
        return listOfVisits;
    }

    public String toString() {
        return "medicalVisits{" + "doctor's id='" + getDoctor().getDoctorId() + "', surname='" + getDoctor().getSurname() + '\'' + ", patient=" + getPatient().getName() + " " + getPatient().getSurname() + '\'' + ", date of medical visit='" + dateOfVisit + '\'' + '}';

    }

    public static List<Visit> loadVisits() throws IOException {
        List<Visit> loadedVisits = new ArrayList<>();
        File visitsFile = new File("src/hisLite/visitsDatabase.txt");

        FileReader sl = new FileReader(visitsFile);
        BufferedReader br = new BufferedReader(sl);

        br.readLine();
        String line = "";

        while((line = br.readLine()) != null) {

            String[] args = line.split("\\t");

            if (args[0].length() > 1) {
                try {
                    int idLekarza = Integer.parseInt(args[0]);
                    int idPacjenta = Integer.parseInt(args[1]);
                    String dateOfVisit = args[2];

                    new Visit(idLekarza, idPacjenta, dateOfVisit);
                } catch (Exception exception) {
                    System.out.println("Check if id number of doctor or patient loaded from visitsDatabase is correct, it might not be on the doctors of patients database : " + exception);
                }
            }

        }
        return loadedVisits;
    }
    
    public static int findMostCommonYearinVisitsDatabase(List<Visit> list) {
        Map<Integer, Integer> howCommonIsTheYearInVisitsDatabase = new HashMap<>();

        for(Visit visit : list) {
            if (!howCommonIsTheYearInVisitsDatabase.containsKey(visit.getYearOfVisit())) {
                howCommonIsTheYearInVisitsDatabase.put(visit.getYearOfVisit(), 1);
            } else {
                int quantityOfPresentYear = howCommonIsTheYearInVisitsDatabase.get(visit.getYearOfVisit());
                howCommonIsTheYearInVisitsDatabase.replace(visit.getYearOfVisit(), quantityOfPresentYear + 1);
            }
        }
        return Collections.max(howCommonIsTheYearInVisitsDatabase.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }

}






















