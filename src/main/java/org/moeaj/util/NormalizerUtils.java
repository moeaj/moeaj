package org.moeaj.util;

import com.google.common.primitives.Doubles;

public class NormalizerUtils {

	public static double normalize(double value, double a, double b, double min, double max) {
		return a + (((value - min) * (b - a)) / (max - min));
	}

	public static double normalize(double value, double min, double max) {
		return normalize(value, 0.0, 1.0, min, max);
	}
	
	/**
	 * This method normalizes an array between [0:1] values given 
	 * the minimum and maximum values
	 * 
	 * @param array an array to be normalized
	 * @param min the minimum value
	 * @param max the maximum value
	 * @return an normalized array
	 */
	public static double[] normalize(double[] array, double min, double max) {

		double[] normalized = new double[array.length];

		for (int i = 0; i < array.length; i++) {
			normalized[i] = normalize(array[i], min, max);
		}

		return normalized;
	}
	
	/**
	 * This method normalizes an array between [0:1] values. 
	 * To do that, we capture the minimum and maximum values in the given
	 * array
	 * 
	 * @param array an array to be normalized
	 * @return an normalized array
	 */
	public static double[] normalize(double[] array) {
		return normalize(array, Doubles.min(array), Doubles.max(array));
	}
}
