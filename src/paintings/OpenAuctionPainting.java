package paintings;
import players.*;

public class OpenAuctionPainting extends Painting {

    public OpenAuctionPainting(int artist_id) {
        super(artist_id);
    }

    @Override
    public String getType() {
        return "Open Auction";
    }

    public void auction(Player[] players) {
        for (int a = 0; a < players.length; a++) {
            int bidValue = players[a].bid(currentBid, this);

            while (bidValue > currentBid) {
                System.out.println(players[a].getName() + " bids " + bidValue);
                currentBid = bidValue;
                currentBidder = players[a];

                for (int b = a + 1; b < players.length; b++) {
                    bidValue = players[b].bid(currentBid, this);
                    if (bidValue > currentBid) {
                        a = b;
                        break;
                    }
                }

                if (bidValue > currentBid) {
                    continue;
                }

                for (int b = 0; b < a; b++) {
                    bidValue = players[b].bid(currentBid, this);
                    if (bidValue > currentBid) {
                        a = b;
                        break;
                    }
                }
            }

            if (currentBid > 0) {
                break;
            }
        }

        sold();
    }
}
