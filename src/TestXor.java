import xorAndOrdered.Employee;
import xorAndOrdered.Garden;
import xorAndOrdered.House;
import xorAndOrdered.HouseAddress;

import java.time.LocalDate;
import java.util.Set;

public class TestXor {
    public static void main(String[] args) throws Exception {

        House house = new House(
                1,
                "house1",
                LocalDate.of(2000, 1, 1),
                new HouseAddress("Spokijna", 24),
                Set.of(1, 2, 3, 4));

        Garden garden = new Garden(1,12.5, 2);

        Employee e1 = new Employee(1, "Miron", "Chlebow", LocalDate.of(2002, 9, 24),house);
        Employee e2 = new Employee(2, "Max", "Chlebow", LocalDate.of(2002, 9, 24), garden);



        System.out.println(house);
        System.out.println(garden);
        System.out.println();
        e2.replaceToHouse(house);

        System.out.println(house);
        System.out.println(garden);

    }
}
