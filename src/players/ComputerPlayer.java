package players;
import java.util.concurrent.ThreadLocalRandom;
import paintings.Painting;

public class ComputerPlayer extends Player {
    private int[][] scoreboard;
    public ComputerPlayer(int money, int[][] scoreboard) {
        super(money, "Computer " + totalPlayers++);
        this.scoreboard = scoreboard;
    }

    @Override
    public Painting playPainting() {
        return handPaintings.remove(0);
    }
//        if (handPaintings.isEmpty()) {
//            return null;
//        }

//        int playIndex = ThreadLocalRandom.current().nextInt(handPaintings.size());  //Select random painting
//        return handPaintings.remove(playIndex); //Remove and return
//    }

    @Override
    public int bid(int currentBid, Painting p) {
        int potentialValue = 30;
        int artist_id = p.getArtistId();
        int endOfRound = 0;  //count of ended rounds

        for (int x = 0; x < scoreboard.length; x++) {
            for (int y = 0; y < scoreboard[x].length; y++) {
                if (scoreboard[x][y] != 0) {
                    endOfRound += 1;
                    break;
                }
            }
        }

        for (int x = 0; x < endOfRound; x++) {
            potentialValue += scoreboard[x][artist_id];
        }
        if (p.getOwner() == this)
            potentialValue = potentialValue / 2;
        if (this.getMoney() >= potentialValue)
            return ThreadLocalRandom.current().nextInt(0, potentialValue + 1);
        else
            return ThreadLocalRandom.current().nextInt(0, this.getMoney() + 1);
    }
}
