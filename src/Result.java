import org.lsmp.djep.xjep.XJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Arrays;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class Result implements ResultInterface {

    private final boolean useJsLib = false;
    private final boolean useXjep = true;

    @Override
    public String getFormula(String tableName) {
        try {
            Field field = GeneticAlgorithm.class.getDeclaredField(tableName);
            field.setAccessible(true);
            String[] bestResultArray = (String[]) field.get(GeneticAlgorithm.class);
            String bestResult = Arrays.toString(bestResultArray);

            if(useJsLib) {
                return jsLibSimplify(bestResult);
            }

            if(useXjep) {
                return xJepSimplify(bestResult);
            }

            return bestResult;
        } catch (NoSuchFieldException | IllegalAccessException | ScriptException | FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }



    private String xJepSimplify(String bestResult) throws ParseException {
        XJep xjep = new XJep();
        xjep.setImplicitMul(true);
        xjep.setAllowUndeclared(true);

        Node node = xjep.parse(bestResult);
        Node simp = xjep.simplify(node);

        return simp.toString();
    }

    private String jsLibSimplify(String bestResult) throws ScriptException, FileNotFoundException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new FileReader("lib/math.js"));

        return engine.eval("math.simplify(\'" + bestResult + "\')").toString();
    }
}
