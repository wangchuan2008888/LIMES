package org.aksw.limes.core.measures.measure.bags;

import com.google.common.collect.Multiset;

/**
 * The Dice index, is a statistic used for comparing the similarity of two samples,
 * another name for it is 'F-Measure'. This is another similarity measure for bags.
 *
 *
 *
 * @author Cedric Richter
 */
public class DiceBagMeasure extends ABagMeasure {
    @Override
    public <T> double getSimilarity(Multiset<T> A, Multiset<T> B) {
        double J = new JaccardBagMeasure().getSimilarity(A, B);
        return 2*J / (1 + J);
    }

    @Override
    public double getRuntimeApproximation(double mappingSize) {
        return mappingSize;
    }

    @Override
    public String getName() {
        return "dice";
    }
}