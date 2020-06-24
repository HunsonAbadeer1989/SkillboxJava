import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class Loader {
    public static final int COUNT_PROCESSORS = Runtime.getRuntime().availableProcessors();
    public static final int REGIONS = 100;

    public static void main(String[] args) throws FileNotFoundException {
        long start = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        Set<Future<?>> poolSet = new HashSet<>();

        int temp = REGIONS/COUNT_PROCESSORS + 1;
        for(int i = 1, from = 1, to = temp; i <= COUNT_PROCESSORS; i++, from += to){
            if(to * i > REGIONS){
                to = REGIONS;
                poolSet.add(pool.submit(new CarNumberGenerator(i, from, to)));
            }
            else {
                poolSet.add(pool.submit(new CarNumberGenerator(i, from, to * i)));
            }
        }

        poolSet.forEach(fs -> {
            try {
                fs.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }
}
