package Features;

import Models.Delivery;
import Models.Match;
import POJO.*;
import Utils.Display;

import java.util.*;

public class Feature {
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of Bowlers :");
        int bowlers = sc.nextInt();
        System.out.println("Enter the Session :");
        int session = sc.nextInt();

        HashSet<Integer> matchSet = matchesBySession(session, matches);
        HashMap<String, BallsAndRuns> bowlersAndRuns = new HashMap<>();
        List<Pairs> economicalBowlers = new ArrayList<>();

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

            economicalBowlers.add(new Pairs(economy, entry.getKey()));
        }
        economicalBowlers.sort(Pairs::compareTo);
        Display.printEconomicalBowlers(economicalBowlers, bowlers);
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
