package statCreator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.scene.control.Label;

public class DiceRoller {
	
	public static void getDiceTotal(Label label) {
        int diceTotal = 0;

        Random rng = new Random();
        ArrayList<Integer> dice = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            dice.add(rng.nextInt(6) + 1);
        }

        Collections.sort(dice);
        dice.remove(0);

        for (int i = 0; i < 3; i++) {
            diceTotal += dice.get(i);
        }
        
        label.setText(String.valueOf(diceTotal));

    }

}
