package xor;

import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class House implements Serializable {
    private long id;
    private String name;
    private LocalDate dateOfSale;
    private HouseAddress houseAddress;
    private Set<Integer> apartments;
    private static Set<House> houses = new HashSet<>();

    private List<Employee> employees = new ArrayList<>();


    public House(long id, String name, LocalDate dateOfSale, HouseAddress houseAddress, Set<Integer> apartments) {
        setId(id);
        setName(name);
        setDateOfSale(dateOfSale);
        setHouseAddress(houseAddress);
        setApartments(apartments);
    }


    public void addEmployee(Employee employee) throws Exception {
        if( employee != null && !employees.contains(employee)){
            if(!employee.isGardenNotSet()){
                throw new Exception("House can't be installed if Garden is already set.");
            }
            employees.add(employee);
            employee.setHouse(this);
        }
    }

    public void removeEmployee(Employee employee) throws Exception {
        if (employees.contains(employee)){
            employees.remove(employee);
            employee.removeHouse();
        }
    }


    //Getters
    public long  getId() {
        return id;
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfSale() {
        return dateOfSale;
    }

    public HouseAddress getHouseAddress() {
        return houseAddress;
    }

    public Set<Integer> getApartments() {
        return Collections.unmodifiableSet(apartments);
    }

    public static Set<House> getHouses() {
        return Collections.unmodifiableSet(houses);
    }


    public void setId(long id) throws IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("Id can not be negative value.");
        }
        boolean alreadyExists = houses.stream().anyMatch(house -> house.getId() == id);
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

    public void setDateOfSale(LocalDate dateOfSale) throws IllegalArgumentException {
        if (dateOfSale == null) {
            throw new IllegalArgumentException("Sale date cannot be null");
        }
        if (dateOfSale.isAfter(LocalDate.now())) {
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
        boolean isNegative = apartments.stream().anyMatch(a -> a <= 0);
        if (isNegative) {
            throw new IllegalArgumentException("Apartments set cannot have non positive values");
        }
        this.apartments = new HashSet<>(apartments);
    }


    public String getShortInfo(){
        return String.format(
                "House : is = %s, name = %s, dateOfSale = %s, houseAddress = %s, apartments = %s",
                id,
                name,
                dateOfSale.toString(),
                houseAddress,
                apartments
        );
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getShortInfo().concat("\nEmployees in House:"));
        for (Employee e : employees) {
            stringBuilder.append(e.getShortInfo()).append(' ');
        }
        return stringBuilder.toString();
    }
}
