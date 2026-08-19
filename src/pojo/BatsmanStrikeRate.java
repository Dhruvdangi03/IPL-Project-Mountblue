package pojo;

public class BatsmanStrikeRate implements Comparable<BatsmanStrikeRate> {
    private String batsman;
    private double strikeRate;

    public double getStrikeRate() {
        return strikeRate;
    }

    public String getBatsman() {
        return batsman;
    }

    public BatsmanStrikeRate(String batsman, double strikeRate) {
        this.strikeRate = strikeRate;
        this.batsman = batsman;
    }

    @Override
    public int compareTo(BatsmanStrikeRate other) {
        if(this.getStrikeRate() > other.getStrikeRate())
            return -1;
        else if (this.getStrikeRate() < other.getStrikeRate()) {
            return 1;
        }
        return 0;
    }
}
