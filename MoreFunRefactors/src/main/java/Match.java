import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Match {
    private List<Integer> player1Points;
    private List<Integer> player2Points;
    private LocalDate date;
    private Player player1;
    private Player player2;

    public Player winner(){
        return player1Win() ? player1 : player2;
    }
    private boolean player1Win(){
        return player1Points.stream().reduce(0, Integer::sum)
                > player2Points.stream().reduce(0, Integer::sum);
    }
    public List<Integer> pointsOfPlayer(Player player){
        return player1.equals(player) ? player1Points : player2Points;
    }

}
