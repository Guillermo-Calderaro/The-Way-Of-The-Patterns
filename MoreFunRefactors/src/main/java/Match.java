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

    public void display(String result){
        int totalGames = 0;
        result += "Match:\n";
        Player player1 = this.getPlayer1();
        result += "Player " + player1.getName() +" Score:\n";
        for (Integer gamePoints: this.getPlayer1Points()) {
            result += gamePoints;
            totalGames += gamePoints;
        }
        result += "Match Points:";
        switch (player1.getZone()){
            case "A":
                result += totalGames *2;
                break;
            case "B":
                result += totalGames;
                break;
            case "C":
                if (this.winner().equals(player1)){
                    result += totalGames;
                }else{
                    result += 0;
                }
                break;
        }

        Player player2 = this.getPlayer2();
        totalGames = 0;
        result += "Player " + player2.getName() +" Score:\n";
        for (Integer gamePoints: this.getPlayer1Points()) {
            result += gamePoints;
            totalGames += gamePoints;
        }
        result += "Match Points:";
        switch (player2.getZone()){
            case "A":
                result += totalGames *2;
                break;
            case "B":
                result += totalGames;
                break;
            case "C":
                if (this.winner().equals(player2)){
                    result += totalGames;
                }else{
                    result += 0;
                }
                break;
        }
    }
}
