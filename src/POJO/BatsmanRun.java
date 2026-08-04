package POJO;

public class BatsmanRun implements Comparable<BatsmanRun> {
    private String batsman;
    private double runRate;

    public double getRunRate() {
        return runRate;
    }

    public String getBatsman() {
        return batsman;
    }

    public BatsmanRun(String batsman, double runRate) {
        this.runRate = runRate;
        this.batsman = batsman;
    }

    @Override
    public int compareTo(BatsmanRun other) {
        if(this.getRunRate() > other.getRunRate())
            return -1;
        else if (this.getRunRate() < other.getRunRate()) {
            return 1;
        }
        return 0;
    }
}
