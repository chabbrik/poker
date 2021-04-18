package lt.savin.poker.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Card {
    private static final Logger logger = LoggerFactory.getLogger(Card.class);

    Rank rank;
    Suit suit;

    public Card (String code) {
        setRank(code.charAt(0));
        setSuit(code.charAt(1));
    }

    @Override
    public String toString() {
        return rank + " " + suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    private void setSuit(char code) {
        switch (code) {
            case 'S' -> suit = Suit.SPADE;
            case 'C' -> suit = Suit.CLUB;
            case 'D' -> suit = Suit.DIAMOND;
            case 'H' -> suit = Suit.HEART;
            default -> logger.error("Unrecognised character: " + code);
        }
    }

    private void setRank(char code) {
        switch (code) {
            case '2' -> rank = Rank.TWO;
            case '3' -> rank = Rank.THREE;
            case '4' -> rank = Rank.FOUR;
            case '5' -> rank = Rank.FIVE;
            case '6' -> rank = Rank.SIX;
            case '7' -> rank = Rank.SEVEN;
            case '8' -> rank = Rank.EIGHT;
            case '9' -> rank = Rank.NINE;
            case 'T' -> rank = Rank.TEN;
            case 'J' -> rank = Rank.JACK;
            case 'Q' -> rank = Rank.QUEEN;
            case 'K' -> rank = Rank.KING;
            case 'A' -> rank = Rank.ACE;
            default -> logger.error("Unrecognised character: " + code);
        }
    }
}
