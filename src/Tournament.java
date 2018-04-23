import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Tournament {

    private int roundNumber = 1;
    final private int MIN_JUMP = 60;
    final private int MAX_JUMP = 120;
    final private int MIN_JUDGE_VOTE = 10;
    final private int MAX_JUDGE_VOTE = 20;

    private List<Jumper> jumpers = new ArrayList<Jumper>();

    public void start(Scanner reader) {
        startTournament(reader);
        System.out.println();
        startJumping(reader);
        System.out.println();
        results();
    }

    public void startTournament(Scanner reader) {

        System.out.println("Kumpula ski jumping week\n");

        System.out.println("Write the names of the participants one at a time; " +
                "an empty string brings you to the jumping phase.\n");

        while (true) {

            System.out.println("  Participant name:");
            String input = reader.nextLine();
            if (input.equals("")) {
                System.out.println("The tournament begins!");
                break;
            }
            Jumper jumper = new Jumper(input, 0);
            this.jumpers.add(jumper);
        }
    }


    public void startJumping(Scanner reader) {

        while (true) {
            System.out.println("Write \"jump\" to jump; otherwise you quit:");
            System.out.println();
            String input = reader.nextLine();
            if (input.equals("jump")) {
                System.out.println();
                System.out.println("Round " + roundNumber);
                System.out.println();
                jumpingOrder();
                System.out.println();
                roundResults();
                roundNumber++;
            } else {
                break;
            }
        }
    }

    public void jumpingOrder() {
        System.out.println("Jumping order:");
        int starting = 1;
        Collections.sort(jumpers, new JumperComparatorByScore());
        //Collections.reverse(jumpers);
        for (Jumper j : jumpers) {
            System.out.println("  " + starting + ". " + j.getJumperName() + " (" + j.getScore() + " points)");
            starting++;
        }
    }

    public void roundResults(){
        System.out.println("Results of round " + roundNumber);
        for (Jumper j : jumpers){
            int jumpLength = ThreadLocalRandom.current().nextInt(MIN_JUMP, MAX_JUMP + 1);
            System.out.println("  " + j.getJumperName());
            System.out.println("    length: " + jumpLength);
            j.setScore(j.getScore() + jumpLength);
            j.addJumpLength(jumpLength);

            List<Integer> votes = new ArrayList<Integer>();
            votes.add(ThreadLocalRandom.current().nextInt(MIN_JUDGE_VOTE, MAX_JUDGE_VOTE + 1));
            votes.add(ThreadLocalRandom.current().nextInt(MIN_JUDGE_VOTE, MAX_JUDGE_VOTE + 1));
            votes.add(ThreadLocalRandom.current().nextInt(MIN_JUDGE_VOTE, MAX_JUDGE_VOTE + 1));
            votes.add(ThreadLocalRandom.current().nextInt(MIN_JUDGE_VOTE, MAX_JUDGE_VOTE + 1));
            votes.add(ThreadLocalRandom.current().nextInt(MIN_JUDGE_VOTE, MAX_JUDGE_VOTE + 1));

            System.out.println("    judge votes: " + votes);

            Collections.sort(votes);
            votes.remove(0);
            votes.remove(votes.size()-1);

            for (Integer i : votes){
                j.setScore(j.getScore() + i);
            }
        }

    }
    public void results(){
        int starting = 1;
        Collections.sort(jumpers, new JumperComparatorByScore());
        Collections.reverse(jumpers);

        System.out.println("Thanks!");
        System.out.println();
        System.out.println("Tournament results:");
        System.out.println("Position    Name");

        for (Jumper j : jumpers){
            System.out.println(starting + "           " + j.getJumperName() + " (" + j.getScore() + " points)");
            System.out.println("            jump lengths: " + j.getJumpLengths());
            starting++;
        }



    }


}
