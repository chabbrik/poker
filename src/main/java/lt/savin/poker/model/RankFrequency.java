package lt.savin.poker.model;

public class RankFrequency {
    Rank rank;
    int frequency;

    public Rank getRank() {
        return rank;
    }

    public int getFrequency() {
        return frequency;
    }

    public RankFrequency(Rank key, Long value) {
        this.rank = key;
        this.frequency = value.intValue();
    }
}
