package AttributeUnique;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class House implements Serializable {
    private long id; // unique
    private String name;
    private LocalDateTime dateOfSale;
    private HouseAddress houseAddress;
    private Set<Integer> apartments;
    private static long minimumApartmentsNumber = 10;
    private static final Map<Long,House> houses = new HashMap<>(); // map for unique


    public House(long id, String name, LocalDateTime dateOfSale, HouseAddress houseAddress, Set<Integer> apartments) {
        setId(id);
        setName(name);
        setDateOfSale(dateOfSale);
        setHouseAddress(houseAddress);
        setApartments(apartments);
        addHouse(this);
    }

    private static void addHouse(House house) throws IllegalArgumentException {
        if (house == null) {
            throw new IllegalArgumentException("House cannot be null");
        }
        boolean alreadyAdded = House.getHouses().entrySet().stream().anyMatch(p -> p.getKey() == house.getId());
        if (alreadyAdded) {
            throw new IllegalArgumentException("House with id " + house.getId() + " has already been added");
        }
        House.houses.put(house.getId(),house);
    }

    private static void removeHouse(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("House cannot be null");
        }
        boolean alreadyDeleted = House.getHouses().entrySet().stream().noneMatch(p -> p.getKey().equals(id));
        if (alreadyDeleted) {
            throw new IllegalArgumentException("House with id " + id + " has already been deleted");
        }
        House.houses.remove(id);
    }


    public void addApartment(int apNumber) throws IllegalArgumentException {
        if (apNumber <= 0) {
            throw new IllegalArgumentException("Apartment number cannot be negative or 0");
        }
        boolean alreadyExists = this.getApartments().stream().anyMatch(a -> a == apNumber);
        if (alreadyExists) {
            throw new IllegalArgumentException("Apartment number you entered already exists.");
        }
        this.apartments.add(apNumber);
    }

    public void removeApartment(int apNumber) throws IllegalArgumentException {
        if (apNumber <= 0) {
            throw new IllegalArgumentException("Apartment number cannot be negative or 0");
        }
        if (this.getApartments().size() <= getMinimumApartmentsNumber()) {
            throw new IllegalArgumentException("Number of apartment must be greater than " + getMinimumApartmentsNumber());
        }
        boolean removed = this.getApartments().remove(apNumber);
        if (!removed) {
            throw new IllegalArgumentException("Cannot remove apartment which was not added to the set");
        }
    }


    //Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateOfSale() {
        return dateOfSale;
    }

    public HouseAddress getHouseAddress() {
        return houseAddress;
    }

    public Set<Integer> getApartments() {
        return Collections.unmodifiableSet(apartments);
    }

    public static long getMinimumApartmentsNumber() {
        return minimumApartmentsNumber;
    }

    public static Map<Long,House> getHouses() {
        return Collections.unmodifiableMap(houses);
    }

    //atribute pochodny
    public long getOperatingTime() {
        return ChronoUnit.YEARS.between(LocalDateTime.now(), this.dateOfSale);
    }

    //Setters

    public void setId(long id) throws IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("Id can not be negative value.");
        }
        boolean alreadyExists = getHouses().entrySet().stream().anyMatch(p -> p.getKey() == id);
        if (alreadyExists) {
            throw new IllegalArgumentException("Id you entered already exists.");
        }
        this.id = id;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name != null && name.trim().equals("")) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public void setDateOfSale(LocalDateTime dateOfSale) throws IllegalArgumentException {
        if (dateOfSale == null) {
            throw new IllegalArgumentException("Sale date cannot be null");
        }
        if (dateOfSale.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Date of sale cannot be set to date in future");
        }
        this.dateOfSale = dateOfSale;
    }

    public void setHouseAddress(HouseAddress houseAddress) throws IllegalArgumentException {
        if (houseAddress == null) {
            throw new IllegalArgumentException("House address cannot be null.");
        }
        this.houseAddress = houseAddress;
    }

    public void setApartments(Set<Integer> apartments) throws IllegalArgumentException {
        if (apartments == null || apartments.size() <= 0) {
            throw new IllegalArgumentException("Apartments set cannot be null");
        }
        if (apartments.size() < getMinimumApartmentsNumber()) {
            throw new IllegalArgumentException("In House must be more than " + getMinimumApartmentsNumber() + " apartments.");
        }
        boolean isNegative = apartments.stream().anyMatch(a -> a <= 0);
        if (isNegative) {
            throw new IllegalArgumentException("Apartments set cannot have non positive values");
        }
        this.apartments = new TreeSet<>(apartments);
    }

    public static void setMinimumApartmentsNumber(long minimumApartmentsNumber) {
        if (minimumApartmentsNumber <= 0) {
            throw new IllegalArgumentException("Minimum apartments number must be greater than 0");
        }
        Set<Long> toDelete = House.getHouses()
                .entrySet()
                .stream()
                .filter(p -> p.getValue().getApartments().size() < getMinimumApartmentsNumber())
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        for (Long id: toDelete) {
            House.removeHouse(id);
        }
        House.minimumApartmentsNumber = minimumApartmentsNumber;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfSale=" + dateOfSale.toLocalDate().toString() +
                ", houseAddress=" + houseAddress +
                ", apartments=" + apartments +
                '}';
    }


}
