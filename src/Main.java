import Controller.FeatureController;
import DataExtraction.DeliveriesExtraction;
import DataExtraction.MatchesExtraction;
import Models.Delivery;
import Models.Match;
import Utils.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FeatureController controller = new FeatureController();
        controller.start();
    }
}