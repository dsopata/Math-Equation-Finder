package oramus.zti.uj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hypercube {
	private final List<Range> ranges = new ArrayList<Range>();

	public void addRange( Range range ) {
		ranges.add(range);
	}
	
	public List<Range> get() {
		return Collections.unmodifiableList(ranges);
	}
	
	public int getDimensions() {
		return ranges.size();
	}

	@Override
	public String toString() {
		return ranges.toString();
	}
}
