package CagGenerators.benchmark;

import carGenerators.NewCarGeneratorRunner;
import carGenerators.OldCarNumberGenerator;
import org.openjdk.jmh.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CarGeneratorBenchmark {

    @State(Scope.Thread)
    public static class MyState{
        public NewCarGeneratorRunner newRunner = new NewCarGeneratorRunner();
        public OldCarNumberGenerator oldGenerator = new OldCarNumberGenerator();
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public static void testNewGenerator(MyState state) throws FileNotFoundException {
        state.newRunner.getNumbers();
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public static void testOldGenerator(MyState state) throws IOException {
        state.oldGenerator.createNumbers();
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

}