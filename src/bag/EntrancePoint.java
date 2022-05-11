package bag;

import java.time.LocalDateTime;

public class EntrancePoint {
    private LocalDateTime date;
    private int id;

    House house;
    Resident resident;

    public EntrancePoint(LocalDateTime date, int id, House house, Resident resident) {
        setId(id);
        setDate(date);
        setHouse(house);
        setResident(resident);
    }


    public void setDate(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (date.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot define date in future");
        }
        this.date = date;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id can not be negative value.");
        }
        this.id = id;
    }

    public void setHouse(House house) {
        if ((getHouse() == null) && house != null) {
            this.house = house;
            house.addEntrancePoint(this);
        }
    }

    public void removeHouse(House houseToRemove){
        if (getHouse() != null && this.getHouse() == houseToRemove) {
            this.house = null;

            houseToRemove.removeEntrancePoint(this);
        }
    }

    public void setResident(Resident newResident) {
        if ((getResident() == null) && newResident != null) {
            this.resident = newResident;
            newResident.addEntrancePoint(this);
        }
    }
    public void removeResident(Resident residentToRemove){
        if (getResident() != null && this.getResident() == residentToRemove) {
            this.resident = null;

            residentToRemove.removeEntrancePoint(this);
        }
    }
    public LocalDateTime getDate() {
        return date;
    }

    public int getid() {
        return id;
    }

    public House getHouse() {
        return house;
    }

    public Resident getResident() {
        return resident;
    }

    @Override
    public String toString() {
        return "EntrancePoint{" +
                "date=" + date +
                ", id='" + id + '\'' +
                ", house=" + house +
                ", resident=" + resident +
                '}';
    }
}
