package bag;

import java.time.LocalDateTime;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        House house = new House(
                1,
                "house1",
                LocalDateTime.of(2000, 1, 1, 0, 0),
                new HouseAddress("Spokijna", 24),
                Set.of(1, 2, 3, 4,5,6,7,8,9,10,11));

        House house1 = new House(
                2,
                "house2",
                LocalDateTime.of(2000, 1, 1, 0, 0),
                new HouseAddress("Spokijna", 24),
                Set.of(1, 2, 3, 4, 5,6,7,8,9,10,11));

        Resident r1 = new Resident(1,"Max","Chleb",2);
        EntrancePoint e1 = new EntrancePoint(LocalDateTime.of(2000, 2, 1, 16, 33),1,house,r1);
        EntrancePoint e2 = new EntrancePoint(LocalDateTime.of(2000, 2, 1, 17, 33),1,house,r1);
        EntrancePoint e3 = new EntrancePoint(LocalDateTime.of(2000, 2, 1, 18, 33),1,house,r1);

        System.out.println(r1);
        System.out.println(house);

        r1.removeEntrancePoint(e1);
        house.removeEntrancePoint(e1);
        System.out.println(r1);
        System.out.println(house);
    }

}
