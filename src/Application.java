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

        //GA THREAD
        Thread gaThread = new Thread() {
            @Override
            public void run() {
                //1. generowanie populacje
                GeneticAlgorithm geneticAlgorithm = GeneticAlgorithm.getInstance();
                geneticAlgorithm.prepareDataAccess(dataAccess);

                try {
                    geneticAlgorithm.generatePopulation();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //2. start algorytmu
                //3. iteracje algorytmu:
                int iterationIndex = 0;
                while (LocalDateTime.now().isBefore(finishTime)) {
                    geneticAlgorithm.nextGeneration(resultInterface);
                }
            }
        };
        try {
            gaThread.start();
            gaThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return resultInterface;
    }
}



