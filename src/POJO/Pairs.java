package POJO;

public class Pairs implements Comparable<Pairs> {
    private double economy;
    private String bowler;

    public Pairs(double economy, String bowler){
        this.economy = economy;
        this.bowler = bowler;
    }

    public double getEconomy() {
        return economy;
    }

    public String getBowler() {
        return bowler;
    }

    @Override
    public int compareTo(Pairs other) {
        if(this.getEconomy() > other.getEconomy())
            return 1;
        else if(this.getEconomy() < other.getEconomy())
            return -1;

        return 0;
    }
}
