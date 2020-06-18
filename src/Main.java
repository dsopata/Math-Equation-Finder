import java.io.*;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final String TABLE_NAME = "x";
    private static final String DATA_FILE_NAME = "data.csv";

    public static void main(String[] args) throws IOException {
        ExperimentalDataAccess dataAccess = new ExperimentalDataAccess();
        setDataAccessFromFile(dataAccess);

        Application application = new Application();
        System.out.println(application.execute(dataAccess, 30l, TimeUnit.SECONDS).getFormula(TABLE_NAME));
    }

    private static void setDataAccessFromFile(ExperimentalDataAccess dataAccess) throws IOException {
        String fName = DATA_FILE_NAME;
        String thisLine;
        FileInputStream fis = new FileInputStream(fName);
        DataInputStream myInput = new DataInputStream(fis);

        int dataExmaples = countLines(fName) - 1;

        int i=0;
        myInput.readLine();
        double[][] independendValues = new double[dataExmaples][3];
        double[] dependendValues = new double[dataExmaples];
        while ((thisLine = myInput.readLine()) != null) {
            String line = thisLine.replace(",", ".");
            String strar[] = line.split(";");
            independendValues[i] = new double[]{ Double.valueOf(strar[0]), Double.valueOf(strar[1]), Double.valueOf(strar[2]) };
            dependendValues[i] = Double.valueOf(strar[3]);
            i++;
        }

        dataAccess.setIndependentVariables(independendValues);
        dataAccess.setDependentVariable(dependendValues);
    }


    public static int countLines(String input) throws IOException {
        try (InputStream is = new FileInputStream(input)) {
            int count = 1;
            for (int aChar = 0; aChar != -1;aChar = is.read())
                count += aChar == '\n' ? 1 : 0;
            return count;
        }
    }
}
