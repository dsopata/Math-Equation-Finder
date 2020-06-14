//import org.lsmp.djep.xjep.XJep;
//import org.nfunk.jep.Node;
//import org.nfunk.jep.ParseException;

public class Result implements ResultInterface {

    private static final String NAME_ALIAS = "_x";
    private String bestResult = "";

    @Override
    public String getFormula(String tableName) {
        return bestResult.replace(NAME_ALIAS, tableName);
    }

    public void setBestResult(String bestResult) {
        this.bestResult = bestResult;
    }
}
