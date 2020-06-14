import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Application implements ApplicationInterface {

    public Application() {

    }

    @Override
    public ResultInterface execute(ExperimentalDataAccessIntereface dataAccess, long maxWorkTime, TimeUnit timeUnit) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime finishTime = now.plus(maxWorkTime, timeUnit.toChronoUnit());
        ResultInterface resultInterface = new Result();

        //1. generowanie populacje
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(dataAccess);
        geneticAlgorithm.generatePopulation();
        //2. start algorytmu

        //3. iteracje algorytmu:
        int iterationIndex = 0;
        while (LocalDateTime.now().isBefore(finishTime)) {
            geneticAlgorithm.nextGeneration(resultInterface);
        }

        return resultInterface;
    }
}



