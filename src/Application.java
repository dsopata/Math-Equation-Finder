import java.util.concurrent.TimeUnit;

public class Application implements ApplicationInterface {

    private ExperimentalDataAccessIntereface experimentalDataAccessIntereface;
    private ResultInterface resultInterface;

    public Application() {
        experimentalDataAccessIntereface = new ExperimentalDataAccess();
        resultInterface = new Result();
    }

    @Override
    public ResultInterface execute(ExperimentalDataAccessIntereface dataAccess, long maxWorkTime, TimeUnit timeUnit) {
        return null;
    }



}
