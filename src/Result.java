//import org.lsmp.djep.xjep.XJep;
//import org.nfunk.jep.Node;
//import org.nfunk.jep.ParseException;

import java.lang.reflect.Field;

public class Result implements ResultInterface {

    @Override
    public String getFormula(String tableName) {
        try {
            Field field = GeneticAlgorithm.class.getDeclaredField(tableName);
            field.setAccessible(true);
            String[] bestResultArray = (String[]) field.get(GeneticAlgorithm.class);
            String bestResult = "";

            for(String bestResultPart : bestResultArray) {
                bestResult += bestResultPart;
                bestResult += " ";
            }

            return bestResult;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }



//    private String xJepSimplify(String bestResult) throws ParseException {
////        XJep xjep = new XJep();
////        xjep.setImplicitMul(true);
////        xjep.setAllowUndeclared(true);
////
////        Node node = xjep.parse(bestResult);
////        Node simp = xjep.simplify(node);
////
////        return simp.toString();
//    }

//    private String jsLibSimplify(String bestResult) throws ScriptException, FileNotFoundException {
//        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
//        engine.eval(new FileReader("lib/math.js"));
//
//        return engine.eval("math.simplify(\'" + bestResult + "\')").toString();
//    }
}
