package pojo;

public class BatsmanStikeRate implements Comparable<BatsmanStikeRate> {
    private String batsman;
    private double strikeRate;

    public double getStrikeRate() {
        return strikeRate;
    }

    public String getBatsman() {
        return batsman;
    }

    public BatsmanStikeRate(String batsman, double strikeRate) {
        this.strikeRate = strikeRate;
        this.batsman = batsman;
    }

    @Override
    public int compareTo(BatsmanStikeRate other) {
        if(this.getStrikeRate() > other.getStrikeRate())
            return -1;
        else if (this.getStrikeRate() < other.getStrikeRate()) {
            return 1;
        }
        return 0;
    }
}
