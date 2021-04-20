package lt.savin.poker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private final int PLAYER1_WINNER = 1;
    private final int PLAYER2_WINNER = 2;
    String HighCardP1       = "TC QD 5D AC 9C 7C 5H 8D TD KS";
    String PairP1           = "5C QD 5D AC 9C 7C 5H 8D TD KS";
    String TwoPairsP1       = "5C AD 5D AC 9C 7C 5H 8D TD KS";
    String ThreeKindP1      = "4C 4D 4D AC 9C 3C 3H 3D TD KS";
    String StraightP1       = "5C 6C 7D 9C 8C 7C 5H 8D TD KS";
    String FlushP2          = "5C 6C 7D 9C 8C 2H TH AH JH KH";
    String FullHouseP2      = "5C 6C 7D 9C 8C 2C 2H 2D 3D 3S";
    String FourKindP2       = "5C 6C 7D 9C 8C JC JD JH TD JS";
    String StraightFlushP2  = "7C 5H 8D TD KS 5C 6C 7C 9C 8C";
    String RoyalFlushP2     = "5C 6C 7D 9C 8C AC KC JC QC TC";

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
    void TestPlayer2WinsFlush() {
        Game game = new Game(FlushP2);
        assertEquals(PLAYER2_WINNER, game.declareGameOutcome());
    }

    @Test
    void TestPlayer2WinsFullHouse() {
        Game game = new Game(FullHouseP2);
        assertEquals(PLAYER2_WINNER, game.declareGameOutcome());
    }

    @Test
    void TestPlayer2WinsFourKind() {
        Game game = new Game(FourKindP2);
        assertEquals(PLAYER2_WINNER, game.declareGameOutcome());
    }

    @Test
    void TestPlayer2WinsStraightFlush() {
        Game game = new Game(StraightFlushP2);
        assertEquals(PLAYER2_WINNER, game.declareGameOutcome());
    }

    @Test
    void TestPlayer2WinsRoyalFlushP2() {
        Game game = new Game(RoyalFlushP2);
        assertEquals(PLAYER2_WINNER, game.declareGameOutcome());
    }
}