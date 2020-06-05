package oramus.zti.uj;

import java.util.ArrayList;
import java.util.List;

public class Apparatus {
	private final ExperimentResponseInterface experiment;
	private final List<NoiseInterface> noise;
	
	public Apparatus( ExperimentResponseInterface ei ) {
		this.experiment = ei;
		noise = new ArrayList<NoiseInterface>();
	}
	
	public void addNoiseGenerator( NoiseInterface noise ) {
		this.noise.add(noise);
	}
	
	public ExperimentalPoint response( Point independentVariables ) {
		double v = experiment.get(independentVariables);
		
		for ( NoiseInterface n : noise ) {
			v = n.get(independentVariables, v);
		}
		
		return new ExperimentalPoint(independentVariables, v );
	}
}
