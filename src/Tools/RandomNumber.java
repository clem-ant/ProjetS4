package Tools;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
        return ThreadLocalRandom.current().nextInt(min,max);
    }

    public static long getRandomNumberInRange(long min, long max){
        return ThreadLocalRandom.current().nextLong(min, max);
    }


}
