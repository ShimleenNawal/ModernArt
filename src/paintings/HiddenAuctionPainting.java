package paintings;
import players.*;
//import java.util.Scanner;

public class HiddenAuctionPainting extends Painting {

    public HiddenAuctionPainting(int artist_id) {
        super(artist_id);
    }

    @Override
    public String getType() {
        return "Hidden Auction";
    }

    public void auction(Player[] players) {
//        int[] bidValues = new int[players.length];
//        int highestBid = 0;
//        int highestBidder = -1;
//
//        for (int b = 0; b < players.length; b++) {
//            bidValues[b] = players[b].bid(currentBid, this);
//        }
//
//        for (int b = 0; b < bidValues.length; b++) {
//            if (bidValues[b] > highestBid) {
//                highestBid = bidValues[b];
//                highestBidder = b;
//            } else if (bidValues[b] == highestBid) {
//                if (highestBidder == -1 || b < highestBidder) {
//                    highestBidder = b;
//                }
//            }
//        }
//
//        if (highestBid > 0) {
//            setOwner(players[highestBidder]);
//        } else {
//            Player auctioneer = players[0];
//            setOwner(auctioneer);
//        }
//
//        sold();
//    }
        int[] bidValue = new int[players.length];
        for (int a = 0; a < players.length; a++) {
            bidValue[a] = players[a].bid(currentBid, this);
            if (bidValue[a] > currentBid) {
                currentBidder = players[a];
                currentBid = bidValue[a];
            }
        }

        int paintingOwner = 0;
        for (int a = 0; a < players.length; a++) {
            if (players[a].getName().equals(getOwner().getName()))
                paintingOwner = a;
        }
        for (int a = paintingOwner; a < players.length; a++) {
            System.out.println(players[a].getName() + " bids " + bidValue[a]);
        }
        for (int a = 0; a < paintingOwner; a++) {
            System.out.println(players[a].getName() + " bids " + bidValue[a]);
        }

        sold();
    }
}