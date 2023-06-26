package GamePack;

import Animations.Animation;
import Animations.AnimationRunner;
import Animations.EndScreen;
import Animations.KeyPressStoppableAnimation;
import Levels.LevelInformation;

import ProjectBasis.Counter;
import ProjectBasis.Point;
import Spies.ScoreTrackingListener;
import Sprites.Objects.ScoreIndicator;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {

    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;

    private Counter score;


    /**
     * Instantiates a new Game flow.
     *
     * @param ar the ar
     * @param ks the ks
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.score = new Counter();
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        int counter = levels.size();
        for (LevelInformation levelInfo : levels) {
            ScoreTrackingListener scoreTrackingListener =
                    new ScoreTrackingListener(this.score);
            ScoreIndicator scoreIndicator = new ScoreIndicator(this.score,
                    Color.white,
                    new ProjectBasis.Rectangle(new Point(0, 0),
                            800, 30));
            GameLevel level = new GameLevel(new SpriteCollection(),
                    new GameEnvironment(), this.keyboardSensor,
                    this.animationRunner, levelInfo, scoreTrackingListener);

            level.initialize();
            scoreIndicator.addToGame(level);

            while (level.getRunning()) {
                level.run();
                counter--;
            }

            if (level.remainingBalls() == 0) {
                this.animationRunner.closeGui();
            } else {
                if (counter == 0) {
                    Animation end =
                            new EndScreen(this.score,
                                    true);
                    this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                            KeyboardSensor.SPACE_KEY, end));
                    this.animationRunner.closeGui();
                }
            }

        }
    }
}