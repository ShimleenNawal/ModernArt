package paintings;
import players.*;
import java.util.Scanner;

public class FixedPriceAuctionPainting extends Painting {
    public FixedPriceAuctionPainting(int artist_id) {
        super(artist_id);
    }

    @Override
    public String getType() {
        return "Fixed Price Auction";
    }

    public void auction(Player[] players) {
        System.out.println(getOwner().getName() + ", please fix a price for the auction");
        if (getOwner() instanceof ComputerPlayer || getOwner() instanceof AFKPlayer) {
            currentBidder = getOwner();
            currentBid = getOwner().bid(currentBid, this);
            System.out.println("The fixed price is " + currentBid);
        } else {
            Scanner in = new Scanner(System.in);
            do {
                try {
                    System.out.println(getOwner());
                    System.out.print("Enter your bid (enter 0 = forfeit): ");
                    int bidValue = in.nextInt();
                    if (bidValue >= 0 && bidValue <= getOwner().getMoney()) {
                        currentBidder = getOwner();
                        currentBid = bidValue;
                        System.out.println("The fixed price is " + currentBid);
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    in.nextLine();
                }
            } while (true);
        }

        int paintingOwner = 0;
        for (int a = 0; a < players.length; a++) {
            if (players[a].getName().equals(getOwner().getName()))
                paintingOwner = a;
        }

        for (int a = paintingOwner + 1; a < players.length; a++) {
            int bidValue = players[a].bid(currentBid, this);
            if (bidValue >= currentBid) {
                currentBidder = players[a];
                break;
            } else {
                System.out.println(players[a].getName() + " pass.");
            }
        }

        if (currentBidder == getOwner())
            for (int a = 0; a < paintingOwner; a++) {
                int bidValue = players[a].bid(currentBid, this);
                if (bidValue >= currentBid) {
                    currentBidder = players[a];
                    break;
                } else {
                    System.out.println(players[a].getName() + " pass.");
                }
            }

        sold();
    }
}
