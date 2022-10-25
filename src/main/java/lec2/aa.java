package lec2;

import java.util.Arrays;
import java.util.List;

public class aa {
    public static void main(String[] args) {
        List<String> agents = Arrays.asList("Agent1", "Agent2", "Agent3");
        String myAgent = ("Agent1");
        for (String agent : agents) {
            if (!myAgent.equals(agent)) {
                System.out.println("dada");

            } else {
                System.out.println("netnet");
            }
        }
    }
}
