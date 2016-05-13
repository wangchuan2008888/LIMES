package org.aksw.limes.core.measures.mapper.temporal.simpleTemporal;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.aksw.limes.core.io.cache.Cache;
import org.aksw.limes.core.io.cache.Instance;
import org.aksw.limes.core.io.mapping.Mapping;
import org.aksw.limes.core.io.mapping.MemoryMapping;

public class PredecessorMapper extends TemporalMapper {

    /**
     * Maps a set of source instances to their predecessor target instances. The
     * mapping contains n-to-m relations. Each source instance takes as
     * predecessors the set of target instances with the highest begin date
     * that is lower than the begin date of the source instance.
     * 
     * @author kleanthi
     */
    @Override
    public Mapping getMapping(Cache source, Cache target, String sourceVar, String targetVar, String expression,
	    double threshold) {

	Mapping m = new MemoryMapping();

	TreeMap<String, Set<Instance>> sources = this.orderByBeginDate(source, expression);
	TreeMap<String, Set<Instance>> targets = this.orderByBeginDate(target, expression);

	for (Map.Entry<String, Set<Instance>> sourceEntry : sources.entrySet()) {
	    String epochSource = sourceEntry.getKey();

	    String lowerEpoch = targets.lowerKey(epochSource);
	    if (lowerEpoch != null) {
		Set<Instance> sourceInstances = sourceEntry.getValue();
		Set<Instance> targetInstances = targets.get(lowerEpoch);
		for (Instance i : sourceInstances) {
		    for (Instance j : targetInstances) {
			m.add(i.getUri(), j.getUri(), 1);
		    }
		}
	    }
	}

	return m;

    }

    @Override
    public String getName() {
	return "predecessor";
    }

    public double getRuntimeApproximation(int sourceSize, int targetSize, double theta, Language language) {
	return 1000d;
    }

    public double getMappingSizeApproximation(int sourceSize, int targetSize, double theta, Language language) {
	return 1000d;
    }

}