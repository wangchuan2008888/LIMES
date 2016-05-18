package org.aksw.limes.core.measures.mapper.temporal.allenAlgebra.complex;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

import org.aksw.limes.core.io.cache.Cache;
import org.aksw.limes.core.io.mapping.Mapping;
import org.aksw.limes.core.io.mapping.MemoryMapping;
import org.aksw.limes.core.measures.mapper.temporal.allenAlgebra.AllenAlgebraMapper;
import org.aksw.limes.core.measures.mapper.temporal.allenAlgebra.atomic.BeginBegin;
import org.aksw.limes.core.measures.mapper.temporal.allenAlgebra.atomic.EndEnd;

import java.util.Map;
import java.util.Set;

public class DuringMapper extends AllenAlgebraMapper {
    public DuringMapper() {
	// EE1 \ (BB0 U BB1)
	this.getRequiredAtomicRelations().add(7);
	this.getRequiredAtomicRelations().add(0);
	this.getRequiredAtomicRelations().add(1);
    }

    @Override
    public String getName() {
	return "During";
    }

    @Override
    public Mapping getMapping(ArrayList<TreeMap<String, Set<String>>> maps) {
	Mapping m = new MemoryMapping();
	TreeMap<String, Set<String>> mapEE1 = maps.get(0);
	TreeMap<String, Set<String>> mapBB0 = maps.get(1);
	TreeMap<String, Set<String>> mapBB1 = maps.get(2);

	for (Map.Entry<String, Set<String>> entryEE1 : mapEE1.entrySet()) {

	    String instanceEE1 = entryEE1.getKey();
	    Set<String> setEE1 = entryEE1.getValue();

	    Set<String> setBB0 = mapBB0.get(instanceEE1);
	    Set<String> setBB1 = mapBB1.get(instanceEE1);
	    if (setBB0 == null)
		setBB0 = new TreeSet<String>();
	    if (setBB1 == null)
		setBB1 = new TreeSet<String>();

	    Set<String> union = AllenAlgebraMapper.union(setBB0, setBB1);
	    Set<String> difference = AllenAlgebraMapper.difference(setEE1, union);

	    if (!difference.isEmpty()) {
		for (String targetInstanceUri : difference) {
		    m.add(instanceEE1, targetInstanceUri, 1);
		}

	    }

	}
	return m;

    }

    @Override
    public Mapping getMapping(Cache source, Cache target, String sourceVar, String targetVar, String expression,
	    double threshold) {
	ArrayList<TreeMap<String, Set<String>>> maps = new ArrayList<TreeMap<String, Set<String>>>();
	EndEnd ee = new EndEnd();
	BeginBegin bb = new BeginBegin();
	// EE1 \ (BB0 U BB1)
	maps.add(ee.getPredecessorEvents(source, target, expression));
	
	maps.add(bb.getConcurrentEvents(source, target, expression));
	maps.add(bb.getPredecessorEvents(source, target, expression));

	Mapping m = getMapping(maps);
	return m;
    }

    public double getRuntimeApproximation(int sourceSize, int targetSize, double theta, Language language) {
	return 1000d;
    }

    public double getMappingSizeApproximation(int sourceSize, int targetSize, double theta, Language language) {
	return 1000d;
    }
}