package lt.savin.poker;

import lt.savin.poker.model.Card;
import lt.savin.poker.model.Combo;
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
    List<Long> rankList;
    List<Long> counters;

    /* I am using object to have null type before calculation is done */
    Boolean isFlush = null;
    Boolean isStraight = null;

    private int calculateRankDistance(List<Long> rankList) {
        return (int) (getHighestCard() - rankList.get(0));
    }

    private boolean isStraight() {
        /* Avoiding multiple calculation for the same hand */
        if (isStraight != null) {
            return isStraight;
        }

        rankList = new ArrayList<>(ranksCounters.keySet()).stream()
                .map(Rank::getValue)
                .sorted()
                .collect(Collectors.toList());

        isStraight = (calculateRankDistance(rankList) == STRAIGHT_HAND_DISTANCE && rankList.size() == HAND_SIZE);
        return isStraight;
        //TODO edge case, when ACE equals one
    }

    private boolean isFlush() {
        /* Avoiding multiple calculation for the same hand */
        if (isFlush != null) {
            return isFlush;
        }

        // Looking if any suit repeats 5 times within a hand.
        List<Map.Entry<Suit, Long>> flush =
                suitCounters.entrySet().stream()
                        .filter(suit -> suit.getValue().intValue() == FLUSH_SIZE)
                        .collect(Collectors.toList());
        isFlush = flush.size() > 0;
        return isFlush;
    }

    public Combo getWinningCombination() {
        if (isRoyalFlush()) {
            return Combo.ROYAL_FLUSH;
        }

        if (isStraightFlush()) {
            return Combo.STRAIGHT_FLUSH;
        }

        if (isFourKind()) {
            return Combo.FOUR_KIND;
        }

        if (isFullHouse()) {
            return Combo.FULL_HOUSE;
        }

        if (isFlush()) {
            return Combo.FLUSH;
        }

        if (isStraightFlush()) {
            return Combo.STRAIGHT_FLUSH;
        }

        if (isStraight()) {
            return Combo.STRAIGHT;
        }

        if (isThreeKind()) {
            return Combo.THREE_KIND;
        }

        if (isTwoPairs()) {
            return Combo.TWO_PAIRS;
        }

        if (isPair()) {
            return Combo.PAIR;
        }

        return Combo.HIGH_CARD;
    }

    private boolean isPair() {
        return (counters.get(0)  == 2 && counters.get(1) != 2);
    }

    private boolean isTwoPairs() {
        return (counters.get(0)  == 2 && counters.get(1) == 2);
    }

    private boolean isThreeKind() {
        return (counters.get(0)  == 3 && counters.get(1) != 2);
    }

    private boolean isFullHouse() {
        return (counters.get(0)  == 3 && counters.get(1) == 2);
    }

    private boolean isFourKind() {
        return (counters.get(0)  == 4);
    }

    private boolean isRoyalFlush() {
        return (isStraightFlush() && (getHighestCard() == Rank.ACE.getValue()));
    }

    private boolean isStraightFlush() {
        return isFlush() && isStraight();
    }

    private int getHighestCard() {
        return rankList.get(rankList.size()-1).intValue();
    }

    public HandEvaluator(List<Card> playerHand) {
        playerHand.forEach(card -> {
            ranksCounters.merge(card.getRank(), 1L, Long::sum);
            suitCounters.merge(card.getSuit(), 1L, Long::sum);
        });

        counters = new ArrayList<>(ranksCounters.values()).stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}