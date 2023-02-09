import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SquashClub {
    private List<Match> matchList = new ArrayList<>();

    public StringBuffer displayPlayerPointsAtAGivenDate(LocalDate date){
        StringBuffer result = new StringBuffer();
        List<Match> matchsOfTheDate = matchList.stream().filter(match -> date.equals(match.getDate()) ).collect(Collectors.toList());

        for (Match match: matchsOfTheDate) {
            int totalGames = 0;
            result.append("Match:\n");
            Player player1 = match.getPlayer1();
            result.append("Player " + player1.getName() +" Score:\n");
            for (Integer gamePoints: match.pointsOfPlayer(player1)) {
                result.append(gamePoints);
                totalGames += gamePoints;
            }
            result.append("Match Points:");
            switch (player1.getZone()){
                case "A":
                    result.append(totalGames *2);
                    break;
                case "B":
                    result.append(totalGames);
                    break;
                case "C":
                    if (match.winner().equals(player1)){
                        result.append(totalGames);
                    }else{
                        result.append(0);
                    }
                    break;
            }

            Player player2 = match.getPlayer2();
            totalGames = 0;
            result.append("Player " + player2.getName() +" Score:\n");
            for (Integer gamePoints: match.pointsOfPlayer(player2)) {
                result.append(gamePoints);
                totalGames += gamePoints;
            }
            result.append("Match Points:");
            switch (player2.getZone()){
                case "A":
                    result.append(totalGames *2);
                    break;
                case "B":
                    result.append(totalGames);
                    break;
                case "C":
                    if (match.winner().equals(player2)){
                        result.append(totalGames);
                    }else{
                        result.append(0);
                    }
                    break;
            }
        }
        return result;
    }
}
