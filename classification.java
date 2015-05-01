/*
This file shows how to use weka clasification?
*/

import java.util.ArrayList;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.net.estimate.SimpleEstimator;
import weka.classifiers.bayes.net.search.global.K2;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class classificationWeka {
	public static void main(String args[]) throws Exception {

		DataSource source = new DataSource("out.csv");
		Instances data = source.getDataSet();
		if (data.classIndex() == -1) {
			data.setClassIndex(data.numAttributes() - 1);
		}

		// Classifier
		BayesNet bnet = new BayesNet();
		bnet.buildClassifier(data);
		bnet.buildStructure();
		bnet.setDebug(false);
		bnet.setUseADTree(false);

		SimpleEstimator bestimator = new SimpleEstimator();
		bestimator.setAlpha(0.5);
		bnet.setEstimator(bestimator);

		K2 bsearch = new K2();
		bnet.setSearchAlgorithm(bsearch);
		ArrayList<LearningAlgos> classifiers = new ArrayList<LearningAlgos>();
		Classifier bclassifier = new BayesNet();
		bclassifier.buildClassifier(data);
		classifiers.add(new LearningAlgos("Bayes Net", bclassifier, data));

		System.out.println(bclassifier.toString());

		Evaluation bevaluation = new Evaluation(data);

		bevaluation.crossValidateModel(bnet, data, 10, new Random(1));

		System.out.println(bevaluation.toSummaryString(
				"------------Summary-----------\n", true));

		System.out.println(bevaluation.fMeasure(1) + " "
				+ bevaluation.precision(1) + " " + bevaluation.recall(1));
				
		double[][] cMatrix = bevaluation.confusionMatrix();
		System.out.println("Confusion Matrix");
		
		for (int row_i = 0; row_i < cMatrix.length; row_i++) {
			for (int col_i = 0; col_i < cMatrix.length; col_i++) {
				System.out.print(cMatrix[row_i][col_i]);
				System.out.print("|");
			}
			System.out.println();
		}

	}

}
