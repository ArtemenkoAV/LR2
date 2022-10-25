package pr2;

import java.util.Arrays;
import java.util.List;

public class ListMain {
    public static void main(String[] args) {
        List<String> list = new MyPlainArrList<>();
//        list.add("hello");
        for(int i=0;i < 10; i++){
            list.add(""+i);
        }
        list.add("world");
        System.out.println(list.get(1));
        System.out.println(list.get(10));
        System.out.println(list.size());

        list.remove(8);
        System.out.println(list.get(9));
        System.out.println(list.size());

        System.out.println(Arrays.toString(((MyPlainArrList<String>) list).data));
        boolean res = list.remove("world");
        System.out.println(res);

        System.out.println(Arrays.toString(((MyPlainArrList<String>) list).data));
        res = list.remove("4");
        System.out.println(Arrays.toString(((MyPlainArrList<String>) list).data));
        res = list.remove("10");
        System.out.println(Arrays.toString(((MyPlainArrList<String>) list).data));

        System.out.println("List contains 5 " + list.contains("5"));
        System.out.println("List contains 10 " + list.contains("10"));

        for (String s : list) {
            System.out.println(s);
        }


    }
}
