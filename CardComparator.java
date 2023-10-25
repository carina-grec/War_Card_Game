import java.util.Comparator;

  class CardComparator implements Comparator<PlayingCard> {
     @Override
     public int compare(PlayingCard c1, PlayingCard c2) {
         int suitComparison = c1.getSuit().compareTo(c2.getSuit());
         if (suitComparison == 0) {
             return c1.getRank().compareTo(c2.getRank());
         }
         return suitComparison;
     }
}
