import java.util.Arrays;
import java.util.List;

public class Deck {
    private PlayingCard[] cards;
    private int last_index; //the highest index
    public Deck(){
        cards = new PlayingCard[52];
        int i = 0; //index of cards
        for(Suit suit : Suit.values()){
            for(Rank rank : Rank.values()){
                cards[i] = new PlayingCard(rank, suit);
                i++;
            }
        }
    }

    public void addCard(PlayingCard c){
        if(last_index < 51){
            last_index++;
            cards[last_index] = c;
        }
    }

    public PlayingCard removeCard(){
        if(last_index > 0){
            PlayingCard card = cards[last_index];
            cards[last_index] = null;
            last_index--;
            return card;
        }else{
            return null;
        }
    }

    public void shuffle(){
        for(int i=0; i<52; i++){
            int j = (int)(Math.random() * (52 - i)) + i;
            PlayingCard aux = cards[i];
            cards[i] = cards[j];
            cards[j] = aux;
        }
    }

    public void sort(){
        Arrays.sort(cards, 0, last_index + 1, new CardComparator());
    }

}
