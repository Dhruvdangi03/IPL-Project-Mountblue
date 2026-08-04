package Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public List<String> split(String str) {
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

    public int intInput(String message){
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        return sc.nextInt();
    }
}
