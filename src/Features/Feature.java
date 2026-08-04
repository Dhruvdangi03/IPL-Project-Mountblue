package Features;

import Models.Delivery;
import Models.Match;
import POJO.*;
import Utils.*;

import java.util.*;

public class Feature {
    Utils utils = new Utils();
    public void matchesPerYear(List<Match> matches){
        TreeMap<Integer, Integer> perYear = new TreeMap<>();

        for(Match match: matches){
            int session = match.getSeason();
            perYear.put(session, perYear.getOrDefault(session, 0) +1);
        }

        Display.printMatchesPerYear(perYear);
    }

    public void matchesWonAllTeams(List<Match> matches){
        HashMap<String, Integer> matchesWon = new HashMap<>();

        for(Match match: matches){
            matchesWon.put(match.getWinner(), matchesWon.getOrDefault(match.getWinner(), 0) +1);
        }

        Display.printMatchesWon(matchesWon);
    }

    public void extraRunsConceded(List<Match> matches, List<Delivery> deliveries){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Session :");
        int session = sc.nextInt();

        HashSet<Integer> matchSet = matchesBySession(session, matches);
        HashMap<String, Integer> extraRunsPerTeam = new HashMap<>();

        for(Delivery delivery: deliveries){
            if(matchSet.contains(delivery.getMatchId())){
                if(delivery.getExtraRuns() > 0){
                    extraRunsPerTeam.put(delivery.getBowlingTeam(), extraRunsPerTeam.getOrDefault(delivery.getBowlingTeam(), 0) +1);
                }
            }
        }

        Display.printExtraRuns(session, extraRunsPerTeam);
    }

    public void topEconomicalBowlers(List<Match> matches, List<Delivery> deliveries){
        int bowlers = utils.intInput("Enter the number of Bowlers :");
        int session = utils.intInput("Enter the Session :");

        HashSet<Integer> matchSet = matchesBySession(session, matches);
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
            if (legalBalls == 0) {
                continue;
            }
            double overs = legalBalls / 6.0;
            double economy = stats.getRuns() / overs;

            economicalBowlers.add(new BowlerEconomy(economy, entry.getKey()));
        }
        economicalBowlers.sort(BowlerEconomy::compareTo);
        Display.printEconomicalBowlers(economicalBowlers, bowlers);
    }

    public void topWicketTakingBowler(List<Match> matches, List<Delivery> deliveries){
        int bowlers = utils.intInput("Enter the number of Bowlers :");
        int session = utils.intInput("Enter the Session :");

        HashSet<Integer> matchSet = matchesBySession(session, matches);
        HashMap<String, Integer> bowlersAndWickets = new HashMap<>();
        List<BowlerWickets> wicketBowlers = new ArrayList<>();

        for (Delivery delivery: deliveries){
            if(matchSet.contains(delivery.getMatchId())){
                if(!delivery.getPlayerDismissed().isEmpty()){
                    bowlersAndWickets.put(delivery.getBowler(), bowlersAndWickets.getOrDefault(delivery.getBowler(), 0) +1);
                }
            }
        }

        for(Map.Entry<String, Integer> entry: bowlersAndWickets.entrySet()){
            wicketBowlers.add(new BowlerWickets(entry.getKey(), entry.getValue()));
        }

        wicketBowlers.sort(BowlerWickets::compareTo);
        Display.printWicketBowlers(wicketBowlers, bowlers);
    }

    private HashSet<Integer> matchesBySession(int session, List<Match> matches){
        HashSet<Integer> matchSet = new HashSet<>();
        for(Match match: matches){
            if(match.getSeason() == session){
                matchSet.add(match.getMatchId());
            }
        }

        if(matchSet.isEmpty())
            System.out.println("There were no Matches played in :" + session);

        return matchSet;
    }
}
