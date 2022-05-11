package xorAndOrdered;

import java.time.LocalDate;

public class Employee {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    private House house = null;
    private Garden garden = null;

    public Employee(long id, String firstName, String lastName, LocalDate dateOfBirth, House house) throws Exception {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
        if (isGardenNotSet()) {
            setHouse(house);
        }
    }

    public Employee(long id, String firstName, String lastName, LocalDate dateOfBirth, Garden garden) throws Exception {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
        if (isHouseNotSet()) {
            setGarden(garden);
        }
    }

    public boolean isHouseNotSet() {
        return getHouse() == null;
    }

    public boolean isGardenNotSet() {
        return getGarden() == null;
    }

    protected void setHouse(House house) throws Exception {
        if (isGardenNotSet() && house != null) {
            this.house = house;
            house.addEmployee(this);
        } else {
            throw new Exception("House can't be set, because garden is already set.");
        }

    }

    public void replaceToGarden(Garden garden) throws Exception {
        if (isGardenNotSet() && this.house != null) {
            removeHouse();
        }
        setGarden(garden);
    }

    protected void setGarden(Garden garden) throws Exception {
        if (isHouseNotSet() && garden != null) {
            this.garden = garden;
            garden.addEmployee(this);
        } else {
            throw new Exception("Garden can't be set, because house is already set.");
        }
    }

    public void replaceToHouse(House house) throws Exception {
        if (isHouseNotSet() && this.garden !=null) {
            removeGarden();
            System.out.println("in remove");
        }
        setHouse(house);
    }

    protected void removeHouse() throws Exception {
        if (isGardenNotSet() && this.house != null) {
            House tmp = this.house;
            this.house = null;
            tmp.removeEmployee(this);
        }
    }

    protected void removeGarden() throws Exception {
        if (isHouseNotSet() && this.garden != null) {
            Garden tmp = this.garden;
            this.garden = null;
            tmp.removeEmployee(this);
        }
    }

    public void setId(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id can not be negative value.");
        }
        this.id = id;
    }

    public void setFirstName(String name) {
        if (name != null && name.trim().equals("")) {
            throw new IllegalArgumentException("First name cannot be empty.");
        }
        this.firstName = name;
    }

    public void setLastName(String lastName) {
        if (lastName != null && lastName.trim().equals("")) {
            throw new IllegalArgumentException("Last name cannot be empty.");
        }
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Birth date cannot be null");
        }
        if (dateOfBirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth cannot be set to date in future");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public House getHouse() {
        return house;
    }

    public Garden getGarden() {
        return garden;
    }

    public String getShortInfo() {
        return String.format(
                "\nEmployee: firstName = %s, lastName = %s, dateOfBirth = %s, house = %s, garden = %s",
                firstName,
                lastName,
                dateOfBirth.toString(),
                (house == null) ? "null" : house.getId(),
                (garden == null) ? "null" : garden.getId()
        );
    }
}
