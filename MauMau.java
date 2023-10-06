import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MauMau {
    private Player player;
    private Player computer;
    private List<Card> drawPile;
    private List<Card> discardPile;

    public MauMau() {
        player = new Player("Spieler");
        computer = new Player("Computer");
        drawPile = new ArrayList<>();
        discardPile = new ArrayList<>();
        initializeDeck();
        Collections.shuffle(drawPile);
    }

    private void initializeDeck() {

        for (Enum.Suit suit : Enum.Suit.values()) {
            for (Enum.Rank rank : Enum.Rank.values()) {
                Card card = new Card(suit, rank);
                drawPile.add(card);
            }
        }
    }

    public void SpielAnfang() {
        for (int i = 0; i < 5; i++) {

            player.drawCard(drawCard());
            computer.drawCard(drawCard());
        }
        discardPile.add(drawCard());
    }

    private Card drawCard() {
        if (drawPile.isEmpty()) {
            Collections.shuffle(discardPile);
            drawPile.addAll(discardPile);
            discardPile.clear();
        }
        return drawPile.remove(0);
    }

    public void SpielerDran() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Du bist dran! Sie können diese Aktionen durchführen:");
        System.out.println("put: Eine Karte spielen");
        System.out.println("take: Eine neue Karte ziehen");
        System.out.println("show: Ihre Handkarten anzeigen");
        System.out.println("look: Die oberste Karte auf dem Stapel anzeigen");
        System.out.print("Ihre Aktion eintragen:\n");

        String aktion = scanner.nextLine();

        switch (aktion) {
            case "put":
                playerPlayCard();
                break;
            case "take":
                playerDrawCard();
                break;
            case "show":
                playerShowHand();
                SpielerDran();
                break;
            case "look":
                lookAtTopCard();
                SpielerDran();
                break;
            default:
                System.out.println("Sie haben eine ungültige Aktion eingetragen. Bitte nochmal versuchen");
                SpielerDran();
                break;
        }
    }

    private void playerPlayCard() {
        System.out.println("Welche Karte möchten Sie spielen? Geben Sie den Index an:");
        playerShowHand();
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();

        if (index >= 0 && index < player.showHand().size()) {
            Card selectedCard = player.showHand().get(index);
            if (isValidMove(selectedCard)) {
                player.playCard(selectedCard, discardPile);
            } else {
                System.out.println("Ungültiger Zug! Versuchen Sie es erneut.");
                playerPlayCard();
            }
        } else {
            System.out.println("Ungültiger Index! Versuchen Sie es erneut.");
            playerPlayCard();
        }
    }

    private boolean isValidMove(Card card) {
        Card topCard = discardPile.get(discardPile.size() - 1);
        return card.getRank().equals(topCard.getRank()) || card.getSuit().equals(topCard.getSuit());
    }

    private void playerDrawCard() {
        Card topCard = drawPile.get(drawPile.size() - 1);
        player.drawCard(topCard);
    }

    private void playerShowHand() {
        System.out.println("Ihre Hand: " + player.showHand());
    }

    private void lookAtTopCard() {
        int index = discardPile.size() - 1;
        System.out.println("Die oberste Karte auf dem Stapel ist: " + discardPile.get(index));
    }

    public void ComputerDran() {
        System.out.println("Der Computer ist dran...");
        Card computerCard = computer.showHand().get(0);

        if (isValidMove(computerCard)) {
            computer.playCard(computerCard, discardPile);
            System.out.println("Computer hat " + computerCard.toString() + " gespielt.\n");
        } else {
            Card topCard = drawPile.get(drawPile.size() - 1);
            computer.drawCard(topCard);
            System.out.println("Der Computer hat eine Karte gezogen.\n");
        }
    }

    public boolean IstSpielfertig() {
        return player.showHand().isEmpty() || computer.showHand().isEmpty();
    }

    public static void main(String[] args) {
        MauMau spiel = new MauMau();
        spiel.SpielAnfang();

        while (!spiel.IstSpielfertig()) {
            spiel.SpielerDran();
            if (spiel.IstSpielfertig()) {
                System.out.println("Der Spieler hat gewonnen!");
                break;
            }

            spiel.ComputerDran();
            if (spiel.IstSpielfertig()) {
                System.out.println("Der Computer hat gewonnen!");
                break;
            }
        }
    }
}
