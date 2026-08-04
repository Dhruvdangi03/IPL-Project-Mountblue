package DataExtraction;

import Models.Delivery;
import Utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveriesExtraction {
    public static List<Delivery> dataExtract(){
        List<Delivery> deliveries = new ArrayList<>();
        try{
            Scanner sc = new Scanner(new File("deliveries.csv"));
            sc.nextLine();
            Utils utils = new Utils();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                List<String> values = utils.split(line);

                Delivery delivery = makeDelivery(values);
                deliveries.add(delivery);
            }

            sc.close();
        } catch (Exception e) {
            System.out.println("Deliveries");
            System.out.println(e.getMessage());
        }

        return deliveries;
    }

    private static Delivery makeDelivery(List<String> values) {
        Delivery delivery = new Delivery();

        delivery.setMatchId(Integer.parseInt(values.get(0)));
        delivery.setInning(Integer.parseInt(values.get(1)));
        delivery.setBattingTeam(values.get(2));
        delivery.setBowlingTeam(values.get(3));
        delivery.setOver(Integer.parseInt(values.get(4)));
        delivery.setBall(Integer.parseInt(values.get(5)));
        delivery.setBatsman(values.get(6));
        delivery.setNonStriker(values.get(7));
        delivery.setBowler(values.get(8));
        delivery.setIsSuperOver(Integer.parseInt(values.get(9)));
        delivery.setWideRuns(Integer.parseInt(values.get(10)));
        delivery.setByeRuns(Integer.parseInt(values.get(11)));
        delivery.setLegByeRuns(Integer.parseInt(values.get(12)));
        delivery.setNoBallRuns(Integer.parseInt(values.get(13)));
        delivery.setPenaltyRuns(Integer.parseInt(values.get(14)));
        delivery.setBatsmanRuns(Integer.parseInt(values.get(15)));
        delivery.setExtraRuns(Integer.parseInt(values.get(16)));
        delivery.setTotalRuns(Integer.parseInt(values.get(17)));
        delivery.setPlayerDismissed(values.get(18));
        delivery.setDismissalKing(values.get(19));
        delivery.setFielder(values.get(20));

        return delivery;
    }
}
