package features;

import dataextraction.DeliveriesExtraction;
import dataextraction.MatchesExtraction;
import models.Delivery;
import models.Match;
import pojo.*;
import utils.*;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Feature {
    private List<Match> matches;
    private List<Delivery> deliveries;

    public Feature(String deliveryCsv, String matchesCsv){
        MatchesExtraction matchesExtraction = new MatchesExtraction(matchesCsv);
        DeliveriesExtraction deliveriesExtraction = new DeliveriesExtraction(deliveryCsv);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<List<Match>> matchesFuture = executor.submit(matchesExtraction);
        Future<List<Delivery>> deliveriesFuture = executor.submit(deliveriesExtraction);

        try{
            this.matches = matchesFuture.get();
            this.deliveries = deliveriesFuture.get();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            executor.shutdown();
        }
    }

    public void displayData(String mode){
        switch (mode) {
            case "all" -> Display.printAll(matches, deliveries);
            case "matches" -> Display.printMatches(matches);
            case "delivery" -> Display.printDeliveries(deliveries);
        }
    }

    public void matchesPerYear(){
        TreeMap<Integer, Integer> perYear = new TreeMap<>();

        for(Match match: matches){
            int session = match.getSeason();
            perYear.put(session, perYear.getOrDefault(session, 0) +1);
        }

        Display.printMatchesPerYear(perYear);
    }

    public void matchesWonAllTeams(){
        HashMap<String, Integer> matchesWon = new HashMap<>();

        for(Match match: matches){
            matchesWon.put(match.getWinner(),
                    matchesWon.getOrDefault(match.getWinner(), 0) + 1);
        }

        Display.printMatchesWon(matchesWon);
    }

    public void extraRunsConceded(){
        int session = Utils.intInput("Enter the Session :");

        HashSet<Integer> matchSet = matchesBySession(session);
        HashMap<String, Integer> extraRunsPerTeam = new HashMap<>();

        for(Delivery delivery: deliveries){
            if(matchSet.contains(delivery.getMatchId())){
                if(delivery.getExtraRuns() > 0){
                    extraRunsPerTeam.put(delivery.getBowlingTeam(),
                            extraRunsPerTeam.getOrDefault(delivery.getBowlingTeam(), 0)
                                    + delivery.getExtraRuns());
                }
            }
        }

        Display.printExtraRuns(session, extraRunsPerTeam);
    }

    public void topEconomicalBowlers(){
        int bowlers = Utils.intInput("Enter the number of Bowlers :");
        int session = Utils.intInput("Enter the Session :");

        HashSet<Integer> matchSet = matchesBySession(session);
        HashMap<String, BallsAndRuns> bowlersAndRuns = new HashMap<>();
        List<BowlerEconomy> economicalBowlers = new ArrayList<>();

        for(Delivery delivery: deliveries){
            if(matchSet.contains(delivery.getMatchId())){
                bowlersAndRuns.putIfAbsent(delivery.getBowler(), new BallsAndRuns());
                BallsAndRuns stats = bowlersAndRuns.get(delivery.getBowler());
                stats.setRuns(stats.getRuns() +
                        (delivery.getTotalRuns() - delivery.getLegByeRuns() - delivery.getByeRuns()));
                stats.setTotalBalls(stats.getTotalBalls() + 1);

                if (delivery.getWideRuns() > 0) {
                    stats.setWideBalls(stats.getWideBalls() + 1);
                }
                if (delivery.getNoBallRuns() > 0) {
                    stats.setNoBalls(stats.getNoBalls() + 1);
                }
            }
        }

        for (Map.Entry<String, BallsAndRuns> entry : bowlersAndRuns.entrySet()){
            BallsAndRuns stats = entry.getValue();
            int legalBalls = stats.getTotalBalls() - stats.getWideBalls() - stats.getNoBalls();
            if (legalBalls < 6) {
                continue;
            }
            double overs = legalBalls / 6.0;
            double economy = stats.getRuns() / overs;

            economicalBowlers.add(new BowlerEconomy(economy, entry.getKey()));
        }
        economicalBowlers.sort(BowlerEconomy::compareTo);
        Display.printEconomicalBowlers(economicalBowlers, bowlers);
    }

    public void topStrikeRate(){
        int batsmen = Utils.intInput("Enter the number of Batsmen :");
        int session = Utils.intInput("Enter the Session :");

        HashSet<Integer> matchSet = matchesBySession(session);
        HashMap<String, int[]> batsmanAndRuns = new HashMap<>();
        List<BatsmanStrikeRate> batsmanStrikeRates = new ArrayList<>();

        for (Delivery delivery: deliveries){
            if(matchSet.contains(delivery.getMatchId())){
                if(delivery.getWideRuns() > 0 || delivery.getNoBallRuns() > 0)
                    continue;

                int[] ballsRuns = batsmanAndRuns.getOrDefault(delivery.getBatsman(), new int[]{0, 0});
                ballsRuns[0] += delivery.getBatsmanRuns();
                ballsRuns[1]++;
                batsmanAndRuns.put(delivery.getBatsman(), ballsRuns);
            }
        }

        for(Map.Entry<String, int[]> entry: batsmanAndRuns.entrySet()){
            int runs = entry.getValue()[0];
            int balls = entry.getValue()[1];

            double strikeRate = ((double) runs / balls) * 100;
            batsmanStrikeRates.add(new BatsmanStrikeRate(entry.getKey(), strikeRate));
        }

        batsmanStrikeRates.sort(BatsmanStrikeRate::compareTo);
        Display.printTopStrikeRate(batsmanStrikeRates, batsmen);
    }

    public void topWicketTakingBowler(boolean purpleCap){
        int bowlers = 0;
        if(!purpleCap)
            bowlers = Utils.intInput("Enter the number of Bowlers :");
        int session = Utils.intInput("Enter the Session :");

        HashSet<Integer> matchSet = matchesBySession(session);
        HashMap<String, Integer> bowlersAndWickets = new HashMap<>();
        List<BowlerWickets> wicketBowlers = new ArrayList<>();

        for (Delivery delivery: deliveries){
            if(matchSet.contains(delivery.getMatchId())){
                if(!delivery.getPlayerDismissed().isEmpty() && !delivery.getDismissalKing().equals("run out")){
                    bowlersAndWickets.put(delivery.getBowler(), bowlersAndWickets.getOrDefault(delivery.getBowler(), 0) +1);
                }
            }
        }

        for(Map.Entry<String, Integer> entry: bowlersAndWickets.entrySet()){
            wicketBowlers.add(new BowlerWickets(entry.getKey(), entry.getValue()));
        }

        wicketBowlers.sort(BowlerWickets::compareTo);
        if(purpleCap)
            Display.printPurpleCap(wicketBowlers, session);
        else
            Display.printWicketBowlers(wicketBowlers, bowlers);
    }

    public void highestRunScoringBatsmen(boolean orangeCap){
        int batsmen = 1;
        if(!orangeCap)
            batsmen = Utils.intInput("Enter the number of Batsmen :");
        int session = Utils.intInput("Enter the Session :");

        HashSet<Integer> matchSet = matchesBySession(session);
        HashMap<String, Integer> batsmanAndRuns = new HashMap<>();
        List<BatsmenRuns> batsmenRuns = new ArrayList<>();

        for (Delivery delivery: deliveries){
            if(matchSet.contains(delivery.getMatchId())){
                if(delivery.getWideRuns() > 0 || delivery.getNoBallRuns() > 0)
                    continue;

                batsmanAndRuns.put(delivery.getBatsman(),
                        batsmanAndRuns.getOrDefault(delivery.getBatsman(),0) + delivery.getBatsmanRuns());
            }
        }

        for(Map.Entry<String, Integer> entry: batsmanAndRuns.entrySet()){
            batsmenRuns.add(new BatsmenRuns(entry.getKey(), entry.getValue()));
        }

        batsmenRuns.sort(BatsmenRuns::compareTo);
        if(orangeCap)
            Display.printOrangeCap(batsmenRuns, session);
        else
            Display.printTopBatsmenRuns(batsmenRuns, batsmen);
    }

    public void highestStrikeRateVenueAgainstTeam(){
        int batsmen = Utils.intInput("Enter the number of Batsmen :");
        int session = Utils.intInput("Enter the Session :");
        String venue = Utils.stringInput("Enter the name of Venue :");
        String bowlingTeam = Utils.stringInput("Enter the name of the bowling team:");

        HashSet<Integer> matchSet = matchesBySessionAndVenue(session, venue);
        HashMap<String, int[]> batsmanAndRuns = new HashMap<>();
        List<BatsmanStrikeRate> batsmanStrikeRates = new ArrayList<>();

        for (Delivery delivery: deliveries){
            if(matchSet.contains(delivery.getMatchId())){
                if((!delivery.getBowlingTeam().equals(bowlingTeam)) || (delivery.getWideRuns() > 0 || delivery.getNoBallRuns() > 0))
                    continue;

                int[] ballsRuns = batsmanAndRuns.getOrDefault(delivery.getBatsman(), new int[]{0, 0});
                ballsRuns[0] += delivery.getBatsmanRuns();
                ballsRuns[1]++;
                batsmanAndRuns.put(delivery.getBatsman(), ballsRuns);
            }
        }

        for(Map.Entry<String, int[]> entry: batsmanAndRuns.entrySet()){
            int runs = entry.getValue()[0];
            int balls = entry.getValue()[1];

            double strikeRate = ((double) runs / balls) * 100;
            batsmanStrikeRates.add(new BatsmanStrikeRate(entry.getKey(), strikeRate));
        }

        batsmanStrikeRates.sort(BatsmanStrikeRate::compareTo);
        Display.printTopStrikeRateVenueAgainstTeam(batsmanStrikeRates, batsmen, bowlingTeam, venue);
    }

    private HashSet<Integer> matchesBySession(int session){
        HashSet<Integer> matchSet = new HashSet<>();
        for(Match match: matches){
            if(match.getSeason() == session){
                matchSet.add(match.getMatchId());
            }
        }

        if(matchSet.isEmpty())
            System.out.println("There were no Matches played in : " + session);

        return matchSet;
    }

    private HashSet<Integer> matchesBySessionAndVenue(int session, String venue){
        HashSet<Integer> matchSet = new HashSet<>();
        for(Match match: matches){
            if(match.getSeason() == session){
                if(match.getVenue().equals(venue) || venue.equals("all"))
                    matchSet.add(match.getMatchId());
            }
        }

        if(matchSet.isEmpty())
            System.out.println("There were no Matches played in : " + session + " in : " + venue);

        return matchSet;
    }
}
