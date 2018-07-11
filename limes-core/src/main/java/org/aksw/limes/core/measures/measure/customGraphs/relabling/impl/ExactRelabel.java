package org.aksw.limes.core.measures.measure.customGraphs.relabling.impl;

import org.aksw.limes.core.measures.measure.customGraphs.relabling.IGraphRelabel;
import org.aksw.limes.core.measures.measure.customGraphs.relabling.ILabel;
import org.aksw.limes.core.measures.measure.customGraphs.relabling.ILabelCollector;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class to relabel exact edges, every lateral to one label
 *
 * @author Cedric Richter
 */
public class ExactRelabel implements IGraphRelabel {

    private boolean ignoreEdge;

    public ExactRelabel(boolean ignoreEdge){
        this.ignoreEdge = ignoreEdge;
    }

    public ExactRelabel(){
        this(false);
    }

    @Override
    public ILabelCollector getPriorLabelCollector() {
        return null;
    }

    @Override
    public String relabel(ILabel label) {
        if(ignoreEdge && label.getType() == ILabel.LabelType.EDGE){
            return null;
        }
        return label.getContent();
    }

    @Override
    public Map<ILabel, String> relabel(Set<ILabel> labels) {
        Map<ILabel, String> mapping = new HashMap<>();

        for(ILabel label: labels){
            mapping.put(label, relabel(label));
        }

        return mapping;
    }
}