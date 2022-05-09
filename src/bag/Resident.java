package bag;

import java.util.*;

public class Resident {
    private long id;
    private String name;
    private String surname;
    private int apartmentNumber;

    // class extent
    private static final Set<Resident> residents = new HashSet<>();

    private List<EntrancePoint> entrancePoints = new ArrayList<>();
    private House house;

    public Resident(long id, String name, String surname, int apartmentNumber) {
        setId(id);
        setName(name);
        setSurname(surname);
        setApartmentNumber(apartmentNumber);
        Resident.addResident(this);
    }


    public void addEntrancePoint(EntrancePoint entrancePoint){
        if (!(entrancePoint == null) && ! entrancePoints.contains(entrancePoint)){
            entrancePoints.add(entrancePoint);
            entrancePoint.setResident(this);
        }
    }

    public void removeEntrancePoint(EntrancePoint entrancePoint){
        if (entrancePoints.contains(entrancePoint)){
            entrancePoints.remove(entrancePoint);
            entrancePoint.removeResident(this);
        }
    }

    public EntrancePoint[] getEntrancePoint(){
        return entrancePoints.toArray(new EntrancePoint[0]);
    }

    public House getHouse() {
        return house;
    }



    private static void addResident(Resident resident) throws IllegalArgumentException {
        if (resident == null) {
            throw new IllegalArgumentException("bag.House cannot be null.");
        }

        boolean alreadyAdded = Resident.residents.stream().anyMatch(a -> a.getId() == resident.getId());
        if (alreadyAdded) {
            throw new IllegalArgumentException("bag.House with id " + resident.getId() + " has already been added.");
        }

        Resident.residents.add(resident);
    }

    private static void removeResident(Resident resident) throws IllegalArgumentException {
        if (resident == null) {
            throw new IllegalArgumentException("bag.Resident cannot be null.");
        }

        boolean removed = Resident.residents.remove(resident);
        if (!removed) {
            throw new IllegalArgumentException("bag.Resident with id " + resident.getId() + " was not removed.");
        }
    }


    public void setApartmentNumber(int apartmentNumber) {
        if (apartmentNumber < 0) {
            throw new IllegalArgumentException("Apartment Number cannot be negative value.");
        }
        this.apartmentNumber = apartmentNumber;
    }

    public void setId(long id) throws IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative value.");
        }

        this.id = id;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.trim().equals("")) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public void setSurname(String surname) throws IllegalArgumentException {
        if (surname == null || surname.trim().equals("")) {
            throw new IllegalArgumentException("Surname cannot be null or empty.");
        }
        this.surname = surname;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public static Set<Resident> getResidents() {
        return Collections.unmodifiableSet(residents);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Resident{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", apartmentNumber=" + apartmentNumber +
                ", house=" + house +
                '}';
    }
}
