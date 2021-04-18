package lt.savin.poker;

import lt.savin.poker.model.Card;
import lt.savin.poker.model.Combo;
import lt.savin.poker.model.RankFrequency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static lt.savin.poker.PokerApplication.HAND_SIZE;

public class Game {
    private static final Logger logger = LoggerFactory.getLogger(HandEvaluator.class);

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

    private void setPlayer1Hand(List<String> StringPlayer1Cards) {
        this.player1Hand = convertStringToPlayerHand(StringPlayer1Cards);
    }

    private void setPlayer2Hand(List<String> StringPlayer2Cards) {
        this.player2Hand = convertStringToPlayerHand(StringPlayer2Cards);
    }

    private int compareCombos() {
        HandEvaluator handEvaluatorP1 = new HandEvaluator(player1Hand);
        HandEvaluator handEvaluatorP2 = new HandEvaluator(player2Hand);
        Combo p1Combo = handEvaluatorP1.getWinningCombination();
        Combo p2Combo = handEvaluatorP2.getWinningCombination();

        logger.info("Hands: P1: {} P2: {}", player1Hand, player2Hand);
        if (p1Combo.getWeight() == p2Combo.getWeight()) {
            logger.info("Breaking a tie: P1: {} P2: {}", p1Combo, p2Combo);
            return breakTie(handEvaluatorP1, handEvaluatorP2);
        } else if (p1Combo.getWeight() > p2Combo.getWeight()){
            return 1;
        } else {
            return 2;
        }
    }

    private int breakTie(HandEvaluator handEvaluatorP1, HandEvaluator handEvaluatorP2) {
        if (handEvaluatorP1.getWinningCombination() == Combo.HIGH_CARD) {
            if (handEvaluatorP1.getHighestCard() == handEvaluatorP2.getHighestCard()) {
                return 0;
            } else if (handEvaluatorP1.getHighestCard() > handEvaluatorP2.getHighestCard()) {
                return 1;
            } else {
                return 2;
            }
        } else {
            List<RankFrequency> freqL1 = handEvaluatorP1.getFrequencyList();
            List<RankFrequency> freqL2 = handEvaluatorP2.getFrequencyList();

            for (int i = 0; i < Math.min(freqL1.size(), freqL2.size()); i++) {
                if (freqL1.get(i).getRank().getValue() > freqL2.get(i).getRank().getValue()) {
                    return 1;
                } else if (freqL1.get(i).getRank().getValue() < freqL2.get(i).getRank().getValue()) {
                    return 2;
                }
            }
        }
        return 0;
    }

    public void declareGameOutcome() {
        int winner = compareCombos();
        logger.info("Player {} wins", winner);
    }
}
