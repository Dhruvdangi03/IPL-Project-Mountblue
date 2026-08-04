package Utils;

import POJO.BowlerEconomy;
import Models.Delivery;
import Models.Match;
import POJO.BowlerWickets;

import java.util.List;
import java.util.Map;

public class Display {
    public static void printALl(List<Match> matches, List<Delivery> deliveries){
        for (Match match: matches)
            System.out.println(match.toString());

        for (Delivery delivery: deliveries)
            System.out.println(delivery.toString());
    }

    public static void printMatches(List<Match> matches){
        for (Match match: matches)
            System.out.println(match.toString());
    }

    public static void printDeliveries(List<Delivery> deliveries){
        for (Delivery delivery: deliveries)
            System.out.println(delivery.toString());
    }

    public static void displayFeatures(){
        System.out.println("Enter 1 for Matches Per Year.");
        System.out.println("Enter 2 for Matches Won by all Teams all over IPL");
        System.out.println("Enter 3 for Extra Runs Conceded per Team");
        System.out.println("Enter 4 for Top Economical Bowlers");
        System.out.println("Enter 5 for Leading Wicket Takers");

        System.out.println("Enter feature (or 0 for exit): ");
    }

    public static void menuSection() {
        System.out.println("---------- MENU ----------------------------------------------------");
    }

    private static void simpleLine(){
        System.out.println("-------------------------------------------------------------------");
    }

    public static void line() {
        System.out.println("====================================================================");
    }

    public static void printTitle(String title) {
        System.out.println("\n====================================================================");
        System.out.println(title);
        System.out.println("====================================================================");
    }

    public static void printMatchesPerYear(Map<Integer, Integer> matchesPerYear) {
        printTitle("Matches Played Per Season");

        System.out.printf("%-10s | %-10s%n", "Season", "Matches");
        simpleLine();

        for (Map.Entry<Integer, Integer> entry : matchesPerYear.entrySet()) {
            System.out.printf("%-10d | %-10d%n",
                    entry.getKey(),
                    entry.getValue());
        }
    }

    public static void printMatchesWon(Map<String, Integer> matchesWon) {
        printTitle("Matches Won By Teams");

        System.out.printf("%-35s | %-10s%n", "Team", "Wins");
        simpleLine();

        for (Map.Entry<String, Integer> entry : matchesWon.entrySet()) {
            if (!entry.getKey().isEmpty()) {
                System.out.printf("%-35s | %-10d%n",
                        entry.getKey(),
                        entry.getValue());
            }
        }
    }

    public static void printExtraRuns(int season, Map<String, Integer> extraRuns) {
        printTitle("Extra Runs Conceded - " + season);

        System.out.printf("%-35s | %-10s%n", "Team", "Extra Runs");
        simpleLine();

        for (Map.Entry<String, Integer> entry : extraRuns.entrySet()) {
            System.out.printf("%-35s | %-10d%n",
                    entry.getKey(),
                    entry.getValue());
        }
    }

    public static void printEconomicalBowlers(List<BowlerEconomy> economicalBowlers, int limit) {

        printTitle("Top " + limit + " Economical Bowlers");

        System.out.printf("%-5s | %-30s | %-10s%n",
                "Rank", "Bowler", "Economy");
        simpleLine();

        int rank = 1;
        for (BowlerEconomy bowler: economicalBowlers) {
            if (rank > limit)
                break;

            System.out.printf("%-5d | %-30s | %-10f%n",
                    rank++,
                    bowler.getBowler(),
                    bowler.getEconomy());
        }
    }

    public static void printWicketBowlers(List<BowlerWickets> wicketBowlers, int limit) {
        printTitle("Top " + limit + " Leading Wicket Takers");

        System.out.printf("%-5s | %-30s | %-10s%n",
                "Rank", "Bowler", "Wickets");
        simpleLine();

        int rank = 1;
        for (BowlerWickets bowler: wicketBowlers) {
            if (rank > limit)
                break;

            System.out.printf("%-5d | %-30s | %-10d%n",
                    rank++,
                    bowler.getBowler(),
                    bowler.getWickets());
        }
    }
}
