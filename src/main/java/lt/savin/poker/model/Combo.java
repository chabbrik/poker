package lt.savin.poker.model;

public enum Combo {
    ROYAL_FLUSH(10),
    STRAIGHT_FLUSH(9),
    FOUR_KIND(8),
    FULL_HOUSE(7),
    FLUSH(6),
    STRAIGHT(5),
    THREE_KIND(4),
    TWO_PAIRS(3),
    PAIR(2),
    HIGH_CARD(1);

    int weight;

    Combo(int weight) {
        this.weight = weight;
    }

}
