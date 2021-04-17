package lt.savin.poker;

import lt.savin.poker.model.Card;
import lt.savin.poker.model.Rank;
import lt.savin.poker.model.Suit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HandEvaluator {
    private static final int FLUSH_SIZE = 5;
    Map<Rank, Long> ranksCounters = new HashMap<>();
    Map<Suit, Long> suitCounters = new HashMap<>();

    private void isFlushHand() {
        List<Map.Entry<Suit, Long>> straight =
                suitCounters.entrySet().stream()
                        .filter(suit -> suit.getValue().intValue() == FLUSH_SIZE)
                        .collect(Collectors.toList());

        if (straight.size() > 0) {
            System.out.println(straight.get(0));
        }
    }

    public HandEvaluator(List<Card> playerHand) {
        playerHand.forEach(card -> {
            ranksCounters.merge(card.getRank(), 1L, Long::sum);
            suitCounters.merge(card.getSuit(), 1L, Long::sum);
        });

        isFlushHand();
    }
}