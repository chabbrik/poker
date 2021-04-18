package lt.savin.poker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private final int PLAYER1_WINNER = 1;
    String HighCardP1  = "TC QD 5D AC 9C 7C 5H 8D TD KS";
    String PairP1      = "5C QD 5D AC 9C 7C 5H 8D TD KS";
    String TwoPairsP1  = "5C AD 5D AC 9C 7C 5H 8D TD KS";
    String ThreeKindP1 = "5C AD 5D AC 9C 7C 5H 8D TD KS";
    String StraightP1  = "5C 6C 7D 9C 8C 7C 5H 8D TD KS";

    String TwoPairsP1Tie  = "5C AD 5D AC 9C 7C 5H 7D TD 5S";

    @Test
    void TestPlayer1WinsHighestCard() {
        Game game = new Game(HighCardP1);
        assertEquals(PLAYER1_WINNER, game.declareGameOutcome());
    }

    @Test
    void TestPlayer1WinsPair() {
        Game game = new Game(PairP1);
        assertEquals(PLAYER1_WINNER, game.declareGameOutcome());
    }

    @Test
    void TestPlayer1WinsTwoPairs() {
        Game game = new Game(TwoPairsP1);
        assertEquals(PLAYER1_WINNER, game.declareGameOutcome());
    }

    @Test
    void TestPlayer1WinsThreeKind() {
        Game game = new Game(ThreeKindP1);
        assertEquals(PLAYER1_WINNER, game.declareGameOutcome());
    }

    @Test
    void TestPlayer1WinsStraight() {
        Game game = new Game(StraightP1);
        assertEquals(PLAYER1_WINNER, game.declareGameOutcome());
    }

    @Test
    void TestPlayer1WinsTwoPairsTie() {
        Game game = new Game(TwoPairsP1Tie);
        assertEquals(PLAYER1_WINNER, game.declareGameOutcome());
    }
}