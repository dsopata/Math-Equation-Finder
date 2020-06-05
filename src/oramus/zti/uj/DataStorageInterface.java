package oramus.zti.uj;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataStorageInterface {
	public void save(ExperimentalDataCollection collection, String filename) throws FileNotFoundException;

	public ExperimentalDataCollection load(String filename) throws FileNotFoundException, IOException;
}
