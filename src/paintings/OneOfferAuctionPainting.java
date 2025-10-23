package paintings;
import players.*;

public class OneOfferAuctionPainting extends Painting {

    public OneOfferAuctionPainting(int artist_id) {
        super(artist_id);
    }

    @Override
    public String getType() {
        return "One Offer Auction";
    }

    public void auction(Player[] players) {
        int paintingOwner = 0;
        for (int a = 0; a < players.length; a++) {   //Identifying the owner
            if (players[a].getName().equals(getOwner().getName())) {
                paintingOwner = a;
            }
        }

        for (int a = paintingOwner + 1; a < players.length; a++) {     //Comparing from the next player after owner to last player
            int bidValue = players[a].bid(currentBid, this);
            System.out.println(players[a].getName() + " bids " + bidValue);
            if (bidValue > currentBid) {
                currentBidder = players[a];
                currentBid = bidValue;
            }
        }

        for (int a = 0; a < paintingOwner + 1; a++) {              //Loops through all the players including the paintingOwner to compare
            if (a == paintingOwner && currentBid == 0) {
                break;
            }

            int bidValue = players[a].bid(currentBid, this);
            System.out.println(players[a].getName() + " bids " + bidValue);

            if (bidValue > currentBid) {
                currentBidder = players[a];
                currentBid = bidValue;
            }
        }

        sold();
    }
}