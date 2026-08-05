package Controller;

import Features.Feature;
import Utils.Display;
import Utils.Utils;

import java.util.Scanner;

public class FeatureController {
    static Feature feature;
    public static void start(String deliveryCsv, String matchesCsv) {
        feature = new Feature(deliveryCsv, matchesCsv);

        startInput();
    }

    private static void startInput(){
        Scanner sc = new Scanner(System.in);

        while (true) {
            Display.menuSection();
            Display.displayFeatures();
            int input = sc.nextInt();

            if (input == 0)
                break;

            callFeature(input);
            Display.line();
        }

        sc.close();
    }

    private static void callFeature(int input) {
        switch (input) {
            case 1:
                feature.matchesPerYear();
                break;
            case 2:
                feature.matchesWonAllTeams();
                break;
            case 3:
                feature.extraRunsConceded();
                break;
            case 4:
                feature.topEconomicalBowlers();
                break;
            case 5:
                feature.topStrikeRate();
                break;
            case 6:
                feature.highestRunScoringBatsmen(true);
                break;
            case 7:
                feature.topWicketTakingBowler(true);
                break;
            case 8:
                feature.topWicketTakingBowler(false);
                break;
            case 9:
                feature.highestRunScoringBatsmen(false);
                break;
            case 10:
                feature.highestStrikeRateVenueAgainstTeam();
                break;
            default:
                System.out.println("Invalid feature.");
        }
    }
}
