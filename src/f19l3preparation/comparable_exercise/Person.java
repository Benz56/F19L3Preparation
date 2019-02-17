/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package f19l3preparation.comparable_exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author erso
 */
public class Person implements Comparable<Person> {

    private final String fName;
    private final String lName;
    private final GregorianCalendar birthDay;
    private final double heigth;

    public Person(String fName, String lName, int bYear, int bMonth, int bDate, double heigth) {
        this.fName = fName;
        this.lName = lName;
        this.birthDay = new GregorianCalendar(bYear, bMonth, bDate);
        this.heigth = heigth;
    }

    @Override
    public String toString() {
        return "fName=" + fName + ", lName=" + lName + ", birthDay=" + birthDay.getTime() + ", height_" + heigth + '\n';
    }

    //Opgave 1A:
    // Der skal sorteres på efternavn. Hvis ens, skal der sorteres på fornavn.
    // Hvis det stadig er ens sorteres på fødselsdag.
    @Override
    public int compareTo(Person o) {
        return lName.equals(o.lName) ? fName.equals(o.fName) ? birthDay.compareTo(o.birthDay) : fName.compareTo(o.fName) : lName.compareTo(o.lName);
    }

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("A", "BB", 1980, 3, 17, 1.87));
        list.add(new Person("B", "BB", 1980, 3, 8, 1.86));
        list.add(new Person("A", "AA", 1980, 3, 9, 1.67));
        list.add(new Person("A", "BB", 1980, 3, 10, 1.67));
        list.add(new Person("A", "BB", 1980, 3, 1, 1.66));
        list.add(new Person("A", "CC", 1980, 3, 1, 1.65));

        System.out.println(list);

        Collections.sort(list);
        System.out.println("\nsorted:\n" + list);

        Comparator<Person> comp = (o1, o2) -> {
            return o1.heigth != o2.heigth ? o1.heigth < o2.heigth ? -1 : 1 : 0;
        };

        Collections.sort(list, comp);
        System.out.println("\nsorted:\n" + list);
    }
}
