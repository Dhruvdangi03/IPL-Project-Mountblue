package POJO;

public class BowlerWickets implements Comparable<BowlerWickets> {
    private int wickets;
    private String bowler;

    public BowlerWickets(String bowler, int wickets) {
        this.bowler = bowler;
        this.wickets = wickets;
    }

    public int getWickets() {
        return wickets;
    }

    public String getBowler() {
        return bowler;
    }

    @Override
    public int compareTo(BowlerWickets other) {
        if(this.getWickets() > other.getWickets())
            return -1;
        else if(this.getWickets() < other.getWickets())
            return 1;

        return 0;
    }
}
