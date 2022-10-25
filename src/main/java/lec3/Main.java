package lec3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
////        List<String> sts = new ArrayList<>();
//        Manger m1 = new Manger();
//        Manger m2 = new Manger();
//        Employee e = new Employee();
//
//        List<Manger> l = new ArrayList<>();
//        l.add(m1);
//        l.add(m2);
//
//        for (Manger o : l) {
//            o.name();
//        }
//
//        Pair<String> p = new Pair<>("Name", "LastName");
//        String first = p.getFirst();
//        String second = p.getSecond();
//
//        Pair<Manger> m = new Pair<>(new Manger(), new Manger());
//        Manger first1 = m.getFirst();
//
//        List<String> strs = List.of("First", "Second", "Third");
//        System.out.println(getRandomItem(strs));
//
//        List<Integer> ints = List.of(1,2,3,4,5,67);
//        System.out.println(getRandomItem2(ints));
//
//        List<Double> dbs = List.of(1.0,2.0,3.0,4.0,5.0,67.0);
//        System.out.println(getRandomItem2(dbs));
//
//        Cleaner c = new Cleaner();
//        Employee e = new Employee();
//        Manger m = new Manger();
//
//        print(e);
//        print(m);
//        print(c);

        int a = 2, b = 0;

        double res = dev(a, b);
        System.out.println(res);

    }
    public static void open(String fName)  {
        File locatedFile = new File(fName);
        try {
            FileReader fileReader = new FileReader(locatedFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static double dev(int nom, int den ){
        try {
            return nom / den;
        } catch (ArithmeticException e){
//            e.printStackTrace();
//            return 0;
            throw new RuntimeException("some errors occured during work "+e.getMessage());
        }
    }
    public static <T> T getRandomItem(List<T> col){
        int n = new Random().nextInt(col.size());
        return col.get(n);
    }

    public static <T extends Number> T getRandomItem2(List<T> col){
        int n = new Random().nextInt(col.size());
        return col.get(n);
    }

    public static <T extends Worker & Departable> void print(T worker){
        System.out.println(worker.getName() +" "+worker.getLasName()+worker.getDepartment());
    }
 }
