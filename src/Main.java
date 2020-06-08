import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        ExperimentalDataAccessIntereface experimentalDataAccessIntereface = new ExperimentalDataAccess();
        ResultInterface resultInterface = new Result();
        ExperimentalDataAccess dataAccess = new ExperimentalDataAccess();

        double[][] independendValues = {
                {0.826308427,0.548696398,0.114552399},
                {0.409941856,0.633549051,0.679030999},
                {0.77503771,0.464139151,0.318191276},
                {0.542992751,0.786261082,0.612430244},
                {0.930874852,0.889142952,0.497110534},
                {0.656316154,0.741171471,0.905906239},
                {0.044077344,0.035014353,0.816611597},
                {0.533417202,0.597669309,0.52062325},
                {0.91389357,0.263380645,0.911569968},
                {0.81404986,0.504900081,0.59458966},
                {0.517995031,0.760751663,0.257085852},
                {0.834557426,0.885083982,0.091525607},
                {0.735834714,0.792303347,0.800208189},
                {0.30268205,0.863996842,0.970508288},
                {0.212188653,0.859310914,0.425617627},
                {0.838560792,0.605625728,0.384592021},
                {0.746159759,0.048584572,0.812725331},
                {0.959455594,0.579922688,0.766372293},
                {0.969192941,0.070758029,0.164136979},
                {0.159039165,0.5980799,0.357829429},
                {0.178336227,0.971512757,0.435408032}
        };

        double[] dependendValues = {
                1.524901931,
                2.224470747,
                1.699417301,
                2.251171104,
                2.357183707,
                2.926437776,
                2.264362329,
                1.986978454,
                2.69679372,
                2.179386658,
                1.669834104,
                1.751691531,
                2.75780318,
                2.896827026,
                1.71150628,
                1.919407662,
                2.287023003,
                2.626832087,
                1.236710703,
                1.524939303,
                1.717932594
        };
        dataAccess.setIndependentVariables(independendValues);
        dataAccess.setDependentVariable(dependendValues);


        Application application = new Application();
        application.execute(dataAccess, 3l, TimeUnit.HOURS);

    }
}
