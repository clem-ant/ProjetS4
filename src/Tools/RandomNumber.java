package Tools;
import java.util.Random;

/**
 * @author Clément PAYET
 * The type Random number.
 */
public class RandomNumber {
    /**
     * Get random number in range int.
     *
     * @param min the min
     * @param max the max
     * @return the int
     */
    public static int getRandomNumberInRange(int min, int max){
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
