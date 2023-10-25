import java.util.Arrays;

public class WarGame {
    private Deck deck;
    private Deck[] players;
    private int moves;

    public WarGame(int nrOfPlayers){
        deck = new Deck();
        deck.shuffle();

        players = new Deck[nrOfPlayers];
        for(int i=0; i<nrOfPlayers; i++){
            players[i] = new Deck();
        }

        while(deck != null){
            for(int i=0; i<nrOfPlayers; i++){
                PlayingCard card = deck.removeCard();
                if(card != null){
                    players[i].addCard(card);
                }
            }
        }
    }

    public void play(){
        while (true) {
            moves++;
            PlayingCard[] topCards = new PlayingCard[players.length];
            for (int i = 0; i < players.length; i++) {
                PlayingCard topCard = players[i].removeCard();
                topCards[i] = topCard;
            }

            // Find the player with the highest-ranking card
            int winningPlayer = -1;
            PlayingCard highestCard = null;
            for (int i = 0; i < topCards.length; i++) {
                if (topCards[i] != null) {
                    if (highestCard == null || topCards[i].getRank().compareTo(highestCard.getRank()) > 0) {
                        highestCard = topCards[i];
                        winningPlayer = i;
                    }
                }
            }

            // Distribute the cards to the winning player
            for (int i = 0; i < topCards.length; i++) {
                if (i != winningPlayer && topCards[i] != null) {
                    players[winningPlayer].addCard(topCards[i]);
                }
            }

            boolean gameFinished = true;
            for (int i = 0; i < players.length; i++) {
                if (players[i] != null) {
                    gameFinished = false;
                    break;
                }
            }

            if (gameFinished) {
                break;
            }
        }

        System.out.println("Game finished in " + moves + " moves.");
    }
    public static void main(String[] args) {

        int numPlayers = 4;
        int numGames = 10;
        int[] moves = new int[numGames];

        for (int i = 0; i < numGames; i++) {
            WarGame warGame = new WarGame(numPlayers);
            warGame.play();
            moves[i] = warGame.getMoves();
        }

        int minMoves = Arrays.stream(moves).min().orElse(0);
        int maxMoves = Arrays.stream(moves).max().orElse(0);
        double meanMoves = Arrays.stream(moves).average().orElse(0);

        System.out.println("Minimum Moves: " + minMoves);
        System.out.println("Maximum Moves: " + maxMoves);
        System.out.println("Mean Moves: " + meanMoves);
    }

    public int getMoves() {
        return moves;
    }

}