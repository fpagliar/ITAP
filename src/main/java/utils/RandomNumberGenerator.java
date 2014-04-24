package utils;

import net.sf.doodleproject.numerics4j.random.ExponentialRandomVariable;
import net.sf.doodleproject.numerics4j.random.NormalRandomVariable;
import net.sf.doodleproject.numerics4j.random.RNG;
import net.sf.doodleproject.numerics4j.random.RandomRNG;
import net.sf.doodleproject.numerics4j.random.RayleighRandomVariable;

public class RandomNumberGenerator {

	private static RNG rng = new RandomRNG(System.currentTimeMillis());

	public static double gaussian(double mean, double standardDeviation) {
		return NormalRandomVariable.nextRandomVariable(mean, standardDeviation,
				rng);
	}

	public static double rayleigh(double epsilon) {
		return RayleighRandomVariable.nextRandomVariable(epsilon, rng);
	}

	public static double exponential(double lambda) {
		return ExponentialRandomVariable.nextRandomVariable(1 / lambda, rng);
	}

}