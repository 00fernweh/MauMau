import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Stack {
    private List<Card> cards;

    // Konstruktor für einen Kartenstapel mit 32 Karten
    public Stack() {
        this.cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                if (rank != Rank.Six && rank != Rank.Ten) {
                    Card card = new Card(suit, rank);
                    cards.add(card);
                }
            }
        }
        shuffle(); // Karten im Stapel mischen
    }

    // Konstruktor für einen leeren Ablagestapel
    public Stack(boolean isEmpty) {
        this.cards = new ArrayList<>();
    }

    // Eine zufällige Karte wird vom Stapel genommen und zurückgegeben
    public Card draw() {
        if (!isEmpty()) {
            Card drawnCard = cards.remove(cards.size() - 1);
            return drawnCard;
        }
        return null;
    }

    // Eine Karte wird auf den Stapel gelegt, wenn sie passend ist
    public boolean playCard(Card card) {
        if (!isEmpty() && card.isValidOnTop(cards.get(cards.size() - 1))) {
            cards.add(card);
            return true;
        }
        return false;
    }

    // Überprüfen, ob der Stapel leer ist
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    // Zeigt die oberste Karte des Stapels
    public Card show() {
        if (!isEmpty()) {
            return cards.get(cards.size() - 1);
        }
        return null;
    }

    // Stapel mischen
    private void shuffle() {
        Collections.shuffle(cards);
    }
}
