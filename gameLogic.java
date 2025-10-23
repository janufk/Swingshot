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

   public Boolean savePenalty(int targetSelect, int powerValue)
   {
    boolean saved;
    if (goalkeeperAnimation.dive == targetSelect)
    {
        this.notScored();
        saved = true;
    }

    else if(powerValue > 90)
    {
        this.notScored();
        saved = false;
    }


    else
    {
        this.scored();
        saved = false;
    }

    return saved;


   }


}
