package org.aksw.limes.core.evaluation.evaluationDataLoader.datasets;

import org.aksw.limes.core.evaluation.evaluationDataLoader.IDataSetIO;

/**
 * Class to Select AmazonGoogle Dataset for the evaluation.
 * Using this class you can perform a variety of functions on the selected dataset.
 * You can use the following functions like getName, getDataSetFolder, getConfigFile, getReferenceFile,
 * getSourceFile, getTargetFile, getSourceClass, getTargetClass, getEvaluationFilename etc.
 *
 * @author Cedric Richter
 *
 */

public class AmazonGoogleDataset extends BaseDataSet {

    /**
     * @return the NameOfDataSet
     */
    @Override
    public String getName() {
        return "amazongoogleproducts";
    }

    /**
     * @return the BaseFolder
     */
    @Override
    public String getDataSetFolder() {
        return this.getBaseFolder()+"Amazon-GoogleProducts/";
    }


    /**
     * @return the ConfigFile
     */
    @Override
    public String getConfigFile() {
        return "Amazon-GoogleProducts.xml";
    }


    /**
     * @return the ReferenceFile
     */
    @Override
    public String getReferenceFile() {
        return "Amzon_GoogleProducts_perfectMapping.csv";
    }


    /**
     * @return the SourceFile
     */
    @Override
    public String getSourceFile() {
        return "Amazon.csv";
    }

    /**
     * @return the TargetFile
     */
    @Override
    public String getTargetFile() {
        return "GoogleProducts.csv";
    }

    /**
     * @return the SourceClass
     */
    @Override
    public String getSourceClass() {
        return "amazon:product";
    }

    /**
     * @return the TargetClass
     */
    @Override
    public String getTargetClass() {
        return "google:product";
    }

    /**
     * @return the EvaluationFileName
     */
    @Override
    public String getEvaluationFilename() {
        return "Pseudo_eval_Amazon-GoogleProducts.csv";
    }

    @Override
    public String getOAEIType() {
        return null;
    }

    /**
     * @return a new Oracle
     */
    @Override
    public IDataSetIO getIO() {
        return new OracleIO();
    }
}