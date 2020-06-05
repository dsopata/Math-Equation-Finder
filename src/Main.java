import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        ExperimentalDataAccessIntereface experimentalDataAccessIntereface = new ExperimentalDataAccess();
        ResultInterface resultInterface = new Result();
        ExperimentalDataAccess dataAccess = new ExperimentalDataAccess();

        Application application = new Application();
        application.execute(dataAccess, 3l, TimeUnit.SECONDS);

    }


}
