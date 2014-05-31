package model;

public class ModuleSynth implements SynthesisFunction {

	public double apply(double[] values) {
		double result = 0;
		for (double d : values)
			result += Math.pow(d, 2);
		return Math.sqrt(result);
	}
}
