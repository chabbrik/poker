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
            case 'T': this.value = 10;
            case 'J': this.value = 11;
            case 'Q': this.value = 12;
            case 'K': this.value = 13;
            case 'A': this.value = 14;
        }
    }
}
