package ohtu;

import java.util.HashMap;

public class TennisGame {

    private String playerOneName;
    private String playerTwoName;

    private int limitForDeuce;
    String[] possibleGameStates = new String[4];

    HashMap<String, Integer> playersAndScores;

    public TennisGame(String player1Name, String player2Name) {
        this.playerOneName = player1Name;
        this.playerTwoName = player2Name;

        playersAndScores = new HashMap();
        playersAndScores.put(player1Name, 0);
        playersAndScores.put(player2Name, 0);

        possibleGameStates[0] = "Love";
        possibleGameStates[1] = "Fifteen";
        possibleGameStates[2] = "Thirty";
        possibleGameStates[3] = "Forty";

        limitForDeuce = 4;

    }

    public void wonPoint(String playerName) {
        int currentScore = playersAndScores.get(playerName);
        playersAndScores.put(playerName, currentScore + 1);
      
    }

    public String getPlayerScore(String playerName) {
        int points = playersAndScores.get(playerName);

        if (points >= limitForDeuce) {
            return "Deuce";
        }

        return possibleGameStates[points];

    }

    public boolean gameIsAtDeuce() {
        int playerOneScore = playersAndScores.get(playerOneName);
        int playerTwoScore = playersAndScores.get(playerTwoName);

        if (playerOneScore >= limitForDeuce || playerTwoScore >= limitForDeuce) {
            return true;
        }

        return false;
    }

    public boolean gameIsEven() {
        int playerOneScore = playersAndScores.get(playerOneName);
        int playerTwoScore = playersAndScores.get(playerTwoName);
        if (playerOneScore == playerTwoScore) {
            return true;
        }
        return false;

    }

    public String getResultAtDeuce() {
        int playerOneScore = playersAndScores.get(playerOneName);
        int playerTwoScore = playersAndScores.get(playerTwoName);

        int scoreDifference = playerOneScore - playerTwoScore;
        if (scoreDifference == 1) {
            return "Advantage player1";
        }
        if (scoreDifference == -1) {
            return "Advantage player2";
        }
        if (scoreDifference >= 2) {
            return "Win for player1";
        }
        return "Win for player2";

    }

    public String getScore() {

        String playerOneScore = getPlayerScore(this.playerOneName);
        String playerTwoScore = getPlayerScore(this.playerTwoName);
        if (gameIsEven()) {
            if (!gameIsAtDeuce()) {
                return playerOneScore + "-All";
            }
            return "Deuce";
        }

        if (gameIsAtDeuce()) {
            return getResultAtDeuce();
        }

        return playerOneScore + "-" + playerTwoScore;

    }
}
