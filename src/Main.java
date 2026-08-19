import controller.FeatureController;

public class Main {
    public static void main(String[] args) {
        FeatureController.start("deliveries.csv", "matches.csv");
    }
}