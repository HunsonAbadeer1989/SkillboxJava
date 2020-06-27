package CagGenerators.benchmark;

import carGenerators.NewCarGeneratorRunner;
import carGenerators.OldCarNumberGenerator;
import org.openjdk.jmh.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CarGeneratorBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public static void testNewGenerator(NewCarGeneratorRunner state) throws FileNotFoundException {
        state.getNumbers();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public static void testOldGenerator(OldCarNumberGenerator state) throws IOException {
        state.createNumbers();
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

}
