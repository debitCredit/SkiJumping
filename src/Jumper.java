import java.util.ArrayList;
import java.util.List;


public class Jumper {

    private String jumperName;
    private int score;
    private List<Integer> scores = new ArrayList<Integer>();
    private List<Integer> jumpLengths = new ArrayList<Integer>();


    public Jumper(String jumperName, int score){
        this.jumperName = jumperName;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getJumperName() {
        return jumperName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score){
        this.scores.add(score);
    }

    public int sumScores(){
        int totalScore = 0;
        for (Integer i : scores){
            totalScore += i;
        }
        return totalScore;
    }

    public void addJumpLength(int jumpLength){
        this.jumpLengths.add(jumpLength);
    }

    public String getJumpLengths(){
        String jumpLengthsResult = "";
        for (Integer i : jumpLengths){
            jumpLengthsResult += Integer.toString(i) + " m, ";
        }

        return jumpLengthsResult.substring(0, jumpLengthsResult.length()-2);
    }
}
