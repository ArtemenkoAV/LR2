package lec2;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HashMapTest {

    public static void main(String[] args) {
//        Map<Student, Double> students = new HashMap<>();
//
//        Student jack = new Student("Jack", "m12", 3);
//        Student kate = new Student("Kate", "m13", 4);
//        Student flash = new Student("Flash", "m1", 2);
//        students.put(jack, 4.5);
//        students.put(kate, 3.5);
//        students.put(flash, 0.5);
//
//        Student jack2 = new Student("Jack", "m12", 3);
//        boolean b = students.containsKey(jack2);
//        System.out.println(b);

//        System.out.println(students.containsKey(kate));
//        kate.setCourse(5);
//        System.out.println(students.containsKey(kate));

//        Set<Student> set = new HashSet<>();
//
//        set.add(kate);
//        set.add(kate);
//        set.add(kate);
//        set.add(kate);
//
//        set.add(null);
//        System.out.println(set.size());
//
        List<Student> studentList = List.of(
                new Student("Jack", "m12", 2, 3.0),
                new Student("Kate", "m13", 4, 5.0),
                new Student("Rayan", "m13", 2, 4.5),
                new Student("Max", "m12", 4, 4.0),
                new Student("Alex", "m14", 4, 2.0)
                );

        // map -> ключ - курс, значние - средняя оценка у студентов
        // map -> ключ - курс, значение количество студентов

        int count =0;
        double average = 0;
        for (Student student : studentList) {
            if (student.getCourse() == 2){
                count ++;
                average += student.getMark();
            }
        }
        average = average / count;

//        long count2 = studentList.stream().filter((e) -> e.getCourse() == 2).count();

        double av =studentList.stream()
                .filter(el -> el.getCourse() == 2)
                .mapToDouble(el -> el.getMark())
                .average().orElse(0);

        List<String> names = studentList.stream()
                .filter(el -> el.getCourse() == 4 && el.getMark() >= 3.5)
                .map(el -> el.getName())
                .collect(Collectors.toList());

        Map<Integer, List<Student>> collect = studentList.stream()
                .collect(Collectors.groupingBy(el -> el.getCourse(), Collectors.toList()));

        Map<Integer, List<Student>> collect1 = studentList.stream()
                .collect(Collectors.groupingBy(el -> el.getCourse()));

//        studentList.stream()
//                .filter(el -> el.getCourse() == 4 && el.getMark() >= 4)
//                .map(el -> new Professor(el.getName(), "phd"))
//                .collect(Collectors.toList())


        System.out.println(names);
//
//        System.out.println(av);
    }
}
