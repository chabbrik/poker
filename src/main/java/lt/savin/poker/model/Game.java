package lt.savin.poker.model;

import lt.savin.poker.HandEvaluator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static lt.savin.poker.PokerApplication.HAND_SIZE;

public class Game {
    List<Card> player1Hand;
    List<Card> player2Hand;

    public Game(String gameString) {
        List<String> cardCodes = Arrays.asList(gameString.split("\s"));
        setPlayer1Hand(cardCodes.subList(0, HAND_SIZE));
        setPlayer2Hand(cardCodes.subList(HAND_SIZE, cardCodes.size()));
    }

    private List<Card> convertStringToPlayerHand(List<String> cardCodes) {
        return cardCodes.stream().map(Card::new).collect(Collectors.toList());
    }

    public List<Card> getPlayer1Hand() {
        return player1Hand;
    }

    public void setPlayer1Hand(List<String> StringPlayer1Cards) {
        this.player1Hand = convertStringToPlayerHand(StringPlayer1Cards);
    }

    public List<Card> getPlayer2Hand() {
        return player2Hand;
    }

    public void setPlayer2Hand(List<String> StringPlayer2Cards) {
        this.player2Hand = convertStringToPlayerHand(StringPlayer2Cards);
    }

    public void declareGameOutcome() {
        HandEvaluator handEvaluatorP1 = new HandEvaluator(player1Hand);
        HandEvaluator handEvaluatorP2 = new HandEvaluator(player2Hand);
    }
}
