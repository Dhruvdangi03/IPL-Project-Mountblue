package DataExtraction;

import Models.Match;
import Utils.Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatchesExtraction {
    public static List<Match> dataExtract(){
        List<Match> matches = new ArrayList<>();
        try{
            Scanner sc = new Scanner(new File("matches.csv"));
            sc.nextLine();
            Utils utils = new Utils();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                List<String> values = utils.split(line);

                Match match = makeMatch(values);
                matches.add(match);
            }

            sc.close();
        } catch (Exception e) {
            System.out.println("Matches");
            System.out.println(e.getMessage());
        }

        return matches;
    }

    private static Match makeMatch(List<String> values) {
        Match match = new Match();

        match.setMatchId(Integer.parseInt(values.get(0)));
        match.setSeason(Integer.parseInt(values.get(1)));
        match.setCity(values.get(2));
        match.setTeam1(values.get(4));
        match.setTeam2(values.get(5));
        match.setTossWinner(values.get(6));
        match.setTossDecision(values.get(7));
        match.setResult(values.get(8));
        match.setDlApplied(Integer.parseInt(values.get(9)));
        match.setWinner(values.get(10));
        match.setWinByRuns(Integer.parseInt(values.get(11)));
        match.setWinByWickets(Integer.parseInt(values.get(12)));
        match.setPlayerOfMatch(values.get(13));
        match.setVenue(values.get(14));
        match.setUmpire1(values.get(15));
        match.setUmpire2(values.get(16));
        match.setUmpire3(values.get(17));

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            match.setDate(sdf.parse(values.get(3)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return match;
    }
}
