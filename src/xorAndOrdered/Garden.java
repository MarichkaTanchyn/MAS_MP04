package xorAndOrdered;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Garden {
    private long id;
    private double gardenArea;
    private int numberOfTrees;

    private List<Employee> employees = new ArrayList<>();

    public Garden(long id,double gardenArea, int numberOfTrees) {
        setId(id);
        setGardenArea(gardenArea);
        setNumberOfTrees(numberOfTrees);
    }

    public void addEmployee(Employee employee) throws Exception {
        if (employee != null && !employees.contains(employee)) {
            if (!employee.isHouseNotSet()) {
                throw new Exception("Garden can't be installed if house is already set.");
            }
            employees.add(employee);
            employee.setGarden(this);
        }
    }

    public void removeEmployee(Employee employee) throws Exception {
        if (employees.contains(employee)) {
            employees.remove(employee);
            employee.removeGarden();
        }
    }

    public void setGardenArea(double gardenArea) {
        if (gardenArea <= 0) {
            throw new IllegalArgumentException("Garden area can't me smaller than 0");
        }
        this.gardenArea = gardenArea;
    }

    public void setNumberOfTrees(int numberOfTrees) {
        if (numberOfTrees < 0) {
            throw new IllegalArgumentException("Number of trees can't me smaller than 0");
        }
        this.numberOfTrees = numberOfTrees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id can not be negative value.");
        }
        this.id = id;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public double getGardenArea() {
        return gardenArea;
    }

    public int getNumberOfTrees() {
        return numberOfTrees;
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    public String getShortInfo(){
        return String.format(
                "Garden : id = %s, gardenArea = %s, numberOfTrees = %s",
                id,
                gardenArea,
                numberOfTrees
        );
    }

    @Override
    public String toString() {
        Employee[] employees = getEmployees().toArray(new Employee[0]);
        StringBuilder stringBuilder = new StringBuilder(getShortInfo().concat("\nEmployees in Garden:"));
        for(int i = 0; i < employees.length; i++){
            stringBuilder.append(employees[i].getShortInfo() + ' ');
        }
        return stringBuilder.toString();
    }
}
