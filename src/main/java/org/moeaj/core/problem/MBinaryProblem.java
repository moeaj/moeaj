package org.moeaj.core.problem;

import java.util.List;

import org.moeaj.core.instance.MInstance;
import org.moeaj.core.objective.MObjective;
import org.moeaj.core.solution.MBinarySolution;
import org.uma.jmetal.problem.BinaryProblem;
import org.uma.jmetal.solution.BinarySolution;

import com.google.common.base.Preconditions;

/**
 * Class representing a Binary Problem
 *
 * @author Thiago Ferreira
 */
public abstract class MBinaryProblem extends MProblem<BinarySolution> implements BinaryProblem {

	private static final long serialVersionUID = 997938360589911317L;

	protected List<Integer> bitsPerVariable;
	
	public MBinaryProblem(MInstance instance, List<MObjective> objectives) {
		super(instance, objectives);
	}
	
	@Override
	public int getNumberOfBits(int index) {
		
		Preconditions.checkArgument(index >= 0, "The variable index should be >= 0");
        Preconditions.checkArgument(index < getNumberOfVariables(), "The variable index should be less than the number of variables");
        
		return bitsPerVariable.get(index);
	}

	@Override
	public int getTotalNumberOfBits() {

		int count = 0;
		
		for (int i = 0; i < this.getNumberOfVariables(); i++) {
			count += this.getNumberOfBits(i);
		}

		return count;
	}

	public List<Integer> getBitsPerVariable() {
		return bitsPerVariable;
	}

	public void setBitsPerVariable(List<Integer> bitsPerVariable) {
		
		Preconditions.checkNotNull(bitsPerVariable, "The number of bits per variable should not be null");
    	Preconditions.checkArgument(bitsPerVariable.size() == getNumberOfVariables(), "The bits per variable should have the same number of variables");
        
    	this.bitsPerVariable = bitsPerVariable;
	}
	
	@Override
	public BinarySolution createSolution() {
		
		return new MBinarySolution(
			getNumberOfObjectives(), 
			getNumberOfVariables(),
			getBitsPerVariable()
		);
	}
}
