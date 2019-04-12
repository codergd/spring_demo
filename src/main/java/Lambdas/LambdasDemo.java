package Lambdas;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LambdasDemo {

    public static void main(String[] args){

        String[] atp = {"gaoda","faker","rookie"};
        List<String> players = Arrays.asList(atp);

        players.forEach((player) -> System.out.println(player+";"));
        players.forEach(System.out::println);

        new Thread(() -> System.out.println("hello world")).start();
        Runnable run1 = () -> System.out.println("hello world");
        run1.run();

        Collections.sort(players, (s1, s2) -> (s1.compareTo(s2)));
        players.forEach((player) -> System.out.println(player+";"));

    }
}
