import java.time.LocalDateTime;
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
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(dataAccess.getNumberOfExperimentalPoints(), dataAccess.getNumberOfIndependentVariables());
        geneticAlgorithm.generatePopulation(50, dataAccess);
        //2. start algorytmu

        //3. iteracja algorytmu:
        //krzyzowanie
        //mutowanie
        //selekcja

        int iterationIndex = 0;
        while (LocalDateTime.now().isBefore(finishTime)) {
            resultInterface.getFormula("dasdas");
           // System.out.println("Iteration: " + iterationIndex++);
        }

        return resultInterface;
    }
}



