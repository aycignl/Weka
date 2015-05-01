/*
This file shows how to use .arff file in weka.
*/

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class readCsv {
	public static void main(String args[]) throws Exception {
		 DataSource source = new DataSource("test.arff");
		 Instances data = source.getDataSet();
		
		 data.setClassIndex(data.numAttributes() - 1);
		
		}
	}

