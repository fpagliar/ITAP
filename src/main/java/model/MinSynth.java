package model;


public class MinSynth implements SynthesisFunction {

	public double apply(double[] values) {
		double result = values[0];
		for (double d : values)
			if(result > d)
			result = d;
		return result;
	}
}
