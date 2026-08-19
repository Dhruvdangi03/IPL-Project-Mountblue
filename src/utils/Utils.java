package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    static Scanner sc = new Scanner(System.in);

    public static int intInput(String message){
        if(!message.isEmpty())
            System.out.println(message);

        int input = sc.nextInt();
        sc.nextLine();
        return input;
    }

    public static String stringInput(String message){
        System.out.println(message);
        return sc.nextLine();
    }

    public static List<String> split(String str) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        boolean insideQuotes = false;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '"') {
                insideQuotes = !insideQuotes;
                continue;
            }

            if (ch == ',' && !insideQuotes) {
                result.add(current.toString());
                current.setLength(0);
            } else {
                current.append(ch);
            }
        }

        result.add(current.toString());

        return result;
    }
}
