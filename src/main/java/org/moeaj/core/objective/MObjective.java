package org.moeaj.core.objective;

import org.moeaj.core.instance.MInstance;
import org.moeaj.core.solution.MSolution;
import org.moeaj.util.ConverterUtils;

public abstract class MObjective {

	public boolean isMaximize() {
		return false;
	}
	
	public double getMinimumValue() {

		if (isMaximize()) {
			return -1.0;
		}

		return 0.0;
	}

	public double getMaximumValue() {

		if (isMaximize()) {
			return 0.0;
		}

		return 1.0;
	}
	
	public String getId() {
		return ConverterUtils.toId(getName());
	}
	
	public String toString() {
		return getId();
	}
	
	public double evaluate(MInstance instance, MSolution<?> solution) {

		double value = calculate(instance, solution);

		if (isMaximize()) {
			return -1.0 * value;
		}

		return value;
	}
	
	public abstract String getName();
	
	public abstract String getGroupName();
	
	public abstract double calculate(MInstance instance, MSolution<?> solution);
}
