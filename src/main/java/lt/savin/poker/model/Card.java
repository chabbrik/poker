package lt.savin.poker.model;

public class Card {

    char rank;
    char suit;

    public Card (String code) {
        rank = code.charAt(0);
        suit = code.charAt(1);
    }

    @Override
    public String toString() {
        return ""+rank+suit;
    }
}
