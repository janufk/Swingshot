import javax.swing.*;
import java.awt.*;


public class gameLogic {

    public int score = 0;

    public int scored()
    {
        score += 10;
        return score;
    }

    public int notScored()
    {
        score -= 10;
        return score;
    }

    public int getScore()
    {
        return score;
    }


}
