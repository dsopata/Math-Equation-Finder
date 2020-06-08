import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Result implements ResultInterface {
    @Override
    public String getFormula(String tableName) {
        try {
            Field field = GeneticAlgorithm.class.getDeclaredField(tableName);
            field.setAccessible(true);
            String[] bestResultArray = (String[]) field.get(GeneticAlgorithm.class);
            String bestResult = Arrays.toString(bestResultArray);
            System.out.println("Best result: " + bestResult);
            System.out.println(1);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
