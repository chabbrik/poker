package lt.savin.poker;

import lt.savin.poker.model.Card;
import lt.savin.poker.model.Rank;
import lt.savin.poker.model.Suit;

import java.util.*;
import java.util.stream.Collectors;

import static lt.savin.poker.PokerApplication.HAND_SIZE;

public class HandEvaluator {
    private static final int FLUSH_SIZE = 5;
    private static final int STRAIGHT_HAND_DISTANCE = 4;
    Map<Rank, Long> ranksCounters = new HashMap<>();
    Map<Suit, Long> suitCounters = new HashMap<>();

    private int calculateRankDistance(List<Long> rankList) {
        return (int) (rankList.get(rankList.size()-1) - rankList.get(0));
    }

    private boolean isStraightHand() {
        List<Long> rankList = new ArrayList<>(ranksCounters.keySet()).stream()
                .map(Rank::getValue)
                .sorted()
                .collect(Collectors.toList());

        return (calculateRankDistance(rankList) == STRAIGHT_HAND_DISTANCE && rankList.size() == HAND_SIZE);
    }

    private boolean isFlushHand() {
        // Looking if any suit repeats 5 times within a hand.
        List<Map.Entry<Suit, Long>> flush =
                suitCounters.entrySet().stream()
                        .filter(suit -> suit.getValue().intValue() == FLUSH_SIZE)
                        .collect(Collectors.toList());

        return flush.size() > 0;
    }

    public HandEvaluator(List<Card> playerHand) {
        playerHand.forEach(card -> {
            ranksCounters.merge(card.getRank(), 1L, Long::sum);
            suitCounters.merge(card.getSuit(), 1L, Long::sum);
        });

        isFlushHand();
        isStraightHand();
        //TODO edge case, when ACE equals one
    }
}