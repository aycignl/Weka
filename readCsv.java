/*
In weka, we can use .arff file and also .csv file. 
Weka accepts .csv file.
This class shows how can we use .csv in weka?
*/

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class readCsv {
	public static void main(String args[]) throws Exception {
		DataSource source = new DataSource("out.csv");
		Instances data = source.getDataSet();
		if (data.classIndex() == -1) {
			data.setClassIndex(data.numAttributes() - 1);
		}
	}
}
