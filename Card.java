
public class Card {
    private final Enum.Suit suit;
    private final Enum.Rank rank;

    public Card(Enum.Suit suit, Enum.Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Enum.Suit getSuit() {
        return suit;
    }

    public Enum.Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " " + suit;
    }
}
