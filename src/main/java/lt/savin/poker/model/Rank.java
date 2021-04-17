package lt.savin.poker.model;

public enum Rank {
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('T'),
    JACK('J'),
    QUEEN('Q'),
    KING('K'),
    ACE('A');

    public long getValue() {
        return value;
    }

    long value;

    Rank(char code) {
        switch (code) {
            case '2' -> this.value = 2;
            case '3' -> this.value = 3;
            case '4' -> this.value = 4;
            case '5' -> this.value = 5;
            case '6' -> this.value = 6;
            case '7' -> this.value = 7;
            case '8' -> this.value = 8;
            case '9' -> this.value = 9;
            case 'T' -> this.value = 10;
            case 'J' -> this.value = 11;
            case 'Q' -> this.value = 12;
            case 'K' -> this.value = 13;
            case 'A' -> this.value = 14;
        }
    }
}
