package controller;

import features.Feature;
import utils.Display;
import utils.Utils;

public class FeatureController {
    static Feature feature;
    public static void start(String deliveryCsv, String matchesCsv) {
        feature = new Feature(deliveryCsv, matchesCsv);

        while (true) {
            Display.menuSection();
            Display.displayFeatures();
            int input = Utils.intInput("");

            if (input == 0)
                break;

            callFeature(input);
            Display.line();
        }
    }

    public static void callFeature(int input) {
        switch (input) {
            case 1 -> feature.matchesPerYear();
            case 2 -> feature.matchesWonAllTeams();
            case 3 -> feature.extraRunsConceded();
            case 4 -> feature.topEconomicalBowlers();
            case 5 -> feature.topStrikeRate();
            case 6 -> feature.highestRunScoringBatsmen(true);
            case 7 -> feature.topWicketTakingBowler(true);
            case 8 -> feature.topWicketTakingBowler(false);
            case 9 -> feature.highestRunScoringBatsmen(false);
            case 10 -> feature.highestStrikeRateVenueAgainstTeam();
            default -> System.out.println("Invalid feature.");
        }
    }
}
