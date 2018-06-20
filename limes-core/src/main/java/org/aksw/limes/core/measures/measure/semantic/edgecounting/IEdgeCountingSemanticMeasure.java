package org.aksw.limes.core.measures.measure.semantic.edgecounting;

import java.util.List;

import org.aksw.limes.core.measures.measure.semantic.ISemanticMeasure;

import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;

public interface IEdgeCountingSemanticMeasure extends ISemanticMeasure {
    
    
    /**
     * Calculates the semantic similarity of two synsets.
     * 
     * @param synsets1
     * @param synsets2
     * @return semantic similarity of two synsets
     */
    public double getSimilarity(ISynset synset1, List<List<ISynsetID>> synset1Tree, ISynset synset2, List<List<ISynsetID>> synset2Tree);

    
}
