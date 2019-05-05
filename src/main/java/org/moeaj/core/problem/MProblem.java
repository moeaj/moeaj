package org.moeaj.core.problem;

import com.google.common.base.Preconditions;

import org.moeaj.core.instance.MInstance;
import org.moeaj.core.objective.MObjective;
import org.moeaj.util.ConverterUtils;
import org.uma.jmetal.problem.Problem;

import java.util.List;

/**
 * Class representing a Problem
 *
 * @author Thiago Ferreira
 */
public abstract class MProblem<S> implements Problem<S> {

	private static final long serialVersionUID = 5481821202257469141L;

	/** The instance file */
	protected MInstance instance;

	/** The list of objectives */
	protected List<MObjective> objectives;

	/** The number of variables */
	protected int numberOfVariables;

	/** The number of constraints */
	protected int numberOfConstraints;

	/**
	 * Constructor
	 * 
	 * @param instance   the instance file
	 * @param objectives the list of objectives
	 */
	public MProblem(MInstance instance, List<MObjective> objectives) {

		Preconditions.checkNotNull(instance, "The instance should not be null");
		Preconditions.checkNotNull(objectives, "The objectives should not be null");
		Preconditions.checkArgument(!objectives.isEmpty(), "The objectives should not be empty");

		this.instance = instance;
		this.objectives = objectives;
	}

	@Override
	public int getNumberOfVariables() {
		return numberOfVariables;
	}

	public void setNumberOfVariables(int numberOfVariables) {
		
		Preconditions.checkArgument(numberOfVariables >= 1, "The number of variables should not be >= 0");
		
		this.numberOfVariables = numberOfVariables;
	}

	@Override
	public int getNumberOfObjectives() {
		return objectives.size();
	}

	@Override
	public int getNumberOfConstraints() {
		return numberOfConstraints;
	}

	public void setNumberOfConstraints(int numberOfConstraints) {
		
		Preconditions.checkArgument(numberOfConstraints >= 0, "The number of constraints should not be >= 0");
    	
		this.numberOfConstraints = numberOfConstraints;
	}

	public String getId() {
		return ConverterUtils.toId(getName());
	}

	public abstract String getName();
}
