import java.util.Comparator;

public class JumperComparatorByScore implements Comparator<Jumper> {

    @Override
    public int compare(Jumper j1, Jumper j2) {
        return j1.getScore() - j2.getScore();
    }
}
