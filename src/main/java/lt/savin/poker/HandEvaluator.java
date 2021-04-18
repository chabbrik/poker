package lt.savin.poker;

import lt.savin.poker.model.*;

import java.util.*;
import java.util.stream.Collectors;

import static lt.savin.poker.PokerApplication.HAND_SIZE;

public class HandEvaluator {
    private static final int FLUSH_SIZE = 5;
    private static final int STRAIGHT_HAND_DISTANCE = 4;
    Map<Rank, Integer> ranksCounters = new HashMap<>();
    Map<Suit, Integer> suitCounters = new HashMap<>();
    List<Integer> rankList;
    List<RankFrequency> frequencyList;

    /* I am using object to have null type before calculation is done */
    Boolean isFlush = null;
    Boolean isStraight = null;

    private int calculateRankDistance(List<Integer> rankList) {
        return getHighestCard() - rankList.get(0);
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

        /* Looking if any suit repeats 5 times within a hand. */
        List<Map.Entry<Suit, Integer>> flush =
                suitCounters.entrySet().stream()
                        .filter(suit -> suit.getValue() == FLUSH_SIZE)
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
        return (frequencyList.get(0).getFrequency()  == 2 && frequencyList.get(1).getFrequency() != 2);
    }

    private boolean isTwoPairs() {
        return (frequencyList.get(0).getFrequency()  == 2 && frequencyList.get(1).getFrequency() == 2);
    }

    private boolean isThreeKind() {
        return (frequencyList.get(0).getFrequency()  == 3 && frequencyList.get(1).getFrequency() != 2);
    }

    private boolean isFullHouse() {
        return (frequencyList.get(0).getFrequency()  == 3 && frequencyList.get(1).getFrequency() == 2);
    }

    private boolean isFourKind() {
        return (frequencyList.get(0).getFrequency()  == 4);
    }

    private boolean isRoyalFlush() {
        return (isStraightFlush() && (getHighestCard() == Rank.ACE.getValue()));
    }

    private boolean isStraightFlush() {
        return isFlush() && isStraight();
    }

    public int getHighestCard() {
        return rankList.get(rankList.size() - 1);
    }

    public List<RankFrequency> getFrequencyList() {
        return frequencyList;
    }

    public HandEvaluator(List<Card> playerHand) {
        playerHand.forEach(card -> {
            ranksCounters.merge(card.getRank(), 1, Integer::sum);
            suitCounters.merge(card.getSuit(), 1, Integer::sum);
        });


        frequencyList = new ArrayList<>(ranksCounters.entrySet()).stream()
                .map(entry -> new RankFrequency(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        /* Reversing rank frequency list for easier management */
        frequencyList.sort(Comparator.comparingInt(RankFrequency::getFrequency).reversed());
    }
}