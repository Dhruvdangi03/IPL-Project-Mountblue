package Controller;

import DataExtraction.DeliveriesExtraction;
import DataExtraction.MatchesExtraction;
import Features.Feature;
import Models.Delivery;
import Models.Match;
import Utils.Display;

import java.util.List;
import java.util.Scanner;

public class FeatureController {

    private final List<Match> matches;
    private final List<Delivery> deliveries;
    private final Feature feature = new Feature();

    public FeatureController() {
        this.matches = MatchesExtraction.dataExtract();
        this.deliveries = DeliveriesExtraction.dataExtract();
    }

    public void start() {
        startInput();
    }

    private void startInput(){
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

    private void callFeature(int input) {
        switch (input) {
            case 1:
                feature.matchesPerYear(matches);
                break;
            case 2:
                feature.matchesWonAllTeams(matches);
                break;
            case 3:
                feature.extraRunsConceded(matches, deliveries);
                break;
            case 4:
                feature.topEconomicalBowlers(matches, deliveries);
                break;
            case 5:
                feature.topWicketTakingBowler(matches, deliveries);
                break;
            default:
                System.out.println("Invalid feature.");
        }
    }
}
