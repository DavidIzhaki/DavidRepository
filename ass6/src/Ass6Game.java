import Animations.AnimationRunner;
import GamePack.GameFlow;
import Levels.LevelInformation;
import Levels.LevelOne;
import Levels.LevelThree;
import Levels.LevelTwo;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 5 game.
 */
public class Ass6Game {
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("David Game!", FRAME_WIDTH, FRAME_HEIGHT);
        AnimationRunner animation = new AnimationRunner(gui, 60, new Sleeper());
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        List<LevelInformation> levelInformationList = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].length() == 1) {
                if (Integer.parseInt(args[i]) == 1) {
                    levelInformationList.add(new LevelOne());
                }
                if (Integer.parseInt(args[i]) == 2) {
                    levelInformationList.add(new LevelTwo());
                }
                if (Integer.parseInt(args[i]) == 3) {
                    levelInformationList.add(new LevelThree());
                }
            }
        }
        if (levelInformationList.size() == 0) {
            levelInformationList.add(new LevelOne());
            levelInformationList.add(new LevelTwo());
            levelInformationList.add(new LevelThree());
        }
        GameFlow gameFlow = new GameFlow(animation, keyboardSensor);
        gameFlow.runLevels(levelInformationList);

    }
}
