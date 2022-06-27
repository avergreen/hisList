package hisLite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Doctor {

    private int doctorId;
    private String surname;
    private String name;
    private String medicalSpeciality;
    private String dateOfBirth;
    private String nipNumber;
    private String peselNumber;
    private int yearOfBirth;

    private static List<Doctor> doctorsList = new ArrayList<>();

    private List<Visit> visits = new ArrayList<>();

    public Doctor(int doctorId, String surname, String name, String medicalSpeciality, String dateOfBirth, String nipNumber, String peselNumber) {
        this.doctorId = doctorId;
        this.surname = surname;
        this.name = name;
        this.medicalSpeciality = medicalSpeciality;
        this.dateOfBirth = dateOfBirth;
        this.nipNumber = nipNumber;
        this.peselNumber = peselNumber;
        this.yearOfBirth = Integer.parseInt(dateOfBirth.substring(0, 4));

        doctorsList.add(this);
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getSurname() {
        return surname;
    }

    public String getMedicalSpeciality() {
        return medicalSpeciality;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public static List<Doctor> getDoctorsList() {
        return doctorsList;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    @Override
    public String toString() {
        return "Doctors{" +
                "doctor Id=" + doctorId +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", medical speciality='" + medicalSpeciality + '\'' +
                ", date od birth='" + dateOfBirth + '\'' +
                ", nip number='" + nipNumber + '\'' +
                ", pesel number='" + peselNumber + '\'' +
                '}';
    }

    public static List<Doctor> loadDoctors() throws IOException {
        List<Doctor> loadedDoctors = new ArrayList<>();
        File doctorsFile = new File("src/hisLite/doctorsDatabase.txt");

        FileReader sl = new FileReader(doctorsFile);
        BufferedReader br = new BufferedReader(sl);

        br.readLine();
        String line = "";

        while ((line = br.readLine()) != null) {

            String[] args = line.split("\\t");

            int doctorId = Integer.parseInt(args[0]);
            String surname = args[1];
            String name = args[2];
            String medicalSpeciality = args[3];
            String dateOfBirth = args[4];
            String nipNumber = args[5];
            String peselNumber = args[6];

            new Doctor(doctorId, surname, name, medicalSpeciality, dateOfBirth, nipNumber, peselNumber);
        }
        return loadedDoctors;
    }

    public void addVisits(Visit wizyta) {
        if (!visits.contains(wizyta)) {
            visits.add(wizyta);
        }
    }

    public static Doctor findTheDoctorByHisIdNumber(int doctorId) {
        Doctor foundDoctor = null;
        for (Doctor doctor : doctorsList) {
            if (doctor.getDoctorId() == doctorId) {
                foundDoctor = doctor;
            }
        }
        return foundDoctor;
    }

    public static Doctor findTheDoctorWithMostVisits(List<Doctor> list) {
        Doctor mostWantedDoctor = list.get(0);
        for (Doctor doctor : list) {
            if (doctor.getVisits().size() > mostWantedDoctor.getVisits().size()) {
                mostWantedDoctor = doctor;
            }
        }
        return mostWantedDoctor;
    }

    public static String mostWantedMedicalSpeciality(List<Doctor> lista) {
        Map<String, Integer> medicalSpecialities = new HashMap<>();

        for (Doctor doctor : lista) {
            if (!medicalSpecialities.containsKey(doctor.getMedicalSpeciality())) {
                medicalSpecialities.put(doctor.getMedicalSpeciality(), 1);
            } else {
                int ilosc = medicalSpecialities.get(doctor.getMedicalSpeciality());
                medicalSpecialities.replace(doctor.getMedicalSpeciality(), ilosc + 1);
            }
        }
        return Collections.max(medicalSpecialities.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }

    public static List<Doctor> getFiveOldestDoctors(List<Doctor> doctorsList) {
        List<Doctor> tempList = new ArrayList<>(doctorsList);
        List<Doctor> oldestDoctorsList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Doctor doctor1 = tempList.get(0);

            for (Doctor doctor : tempList) {
                if (doctor.getYearOfBirth() < doctor1.getYearOfBirth()) {
                    doctor1 = doctor;
                }
            }
            if (!oldestDoctorsList.contains(doctor1)) {
                oldestDoctorsList.add(doctor1);
                tempList.remove(doctor1);
            }
        }
        return oldestDoctorsList;
    }
}




