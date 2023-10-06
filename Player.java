import java.util.ArrayList;
import java.util.List;

class Player {
    private final String name;
    private final List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    // Funktion drawCard(): Nimmt eine Karte auf
    public void drawCard(Card card) {
        hand.add(card);
    }

    // Funktion showHand(): Gibt die Hand des Spielers zurück
    public List<Card> showHand() {
        return hand;
    }

    // Funktion playCard(): Legt eine Karte an der angegebenen Position auf der Hand
    // ab
    public void playCard(int index, List<Card> discardPile) {
        if (index >= 0 && index < hand.size()) {
            Card playedCard = hand.remove(index);
            discardPile.add(playedCard);
        } else {
            System.out.println("Ungültige Indexposition.");
        }
    }

    // Überladene Funktion playCard(): Legt eine Karte ab, wenn sie auf die oberste
    // Karte passt
    public void playCard(Card topCard, List<Card> discardPile) {
        for (Card card : hand) {
            if (canPlayCard(card, topCard)) {
                hand.remove(card);
                discardPile.add(card);
                return;
            }
        }
        System.out.println("Keine passende Karte gefunden.");
    }

    // Hilfsfunktion canPlayCard(): Überprüft, ob die Karte auf die oberste Karte
    // passt
    private boolean canPlayCard(Card card, Card topCard) {
        return card.getRank().equals(topCard.getRank()) || card.getSuit().equals(topCard.getSuit());
    }
}
