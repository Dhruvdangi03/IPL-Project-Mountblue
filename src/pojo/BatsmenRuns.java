package pojo;

public class BatsmenRuns implements Comparable<BatsmenRuns> {
    private String batsman;
    private int runs;

    public int getRuns() {
        return runs;
    }

    public String getBatsman() {
        return batsman;
    }

    public BatsmenRuns(String batsman, int runs) {
        this.runs = runs;
        this.batsman = batsman;
    }

    @Override
    public int compareTo(BatsmenRuns other) {
        if (this.getRuns() > other.getRuns())
            return -1;
        else if (this.getRuns() < other.getRuns()) {
            return 1;
        }
        return 0;
    }
}
