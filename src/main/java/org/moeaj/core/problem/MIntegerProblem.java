package org.moeaj.core.problem;

import org.moeaj.core.instance.MInstance;
import org.moeaj.core.objective.MObjective;
import org.moeaj.core.solution.MIntegerSolution;
import org.moeaj.core.solution.MSolution;
import org.uma.jmetal.problem.IntegerProblem;
import org.uma.jmetal.solution.IntegerSolution;

import com.google.common.base.Preconditions;

import java.util.List;

/**
 * Class representing a Integer Problem
 *
 * @author Thiago Ferreira
 */
public abstract class MIntegerProblem extends MProblem<IntegerSolution> implements IntegerProblem {

    private static final long serialVersionUID = 1139363470344941082L;

	private List<Integer> lowerBounds;

    private List<Integer> upperBounds;

    public MIntegerProblem(MInstance instance, List<MObjective> objectives) {
        super(instance, objectives);
    }

    @Override
    public Integer getUpperBound(int index) {
    	
    	Preconditions.checkArgument(index >= 0, "The variable index should be >= 0");
        Preconditions.checkArgument(index < getNumberOfVariables(), "The variable index should be less than the number of variables");
        
        return upperBounds.get(index);
    }

    @Override
    public Integer getLowerBound(int index) {
    	
    	Preconditions.checkArgument(index >= 0, "The variable index should be >= 0");
        Preconditions.checkArgument(index < getNumberOfVariables(), "The variable index should be less than the number of variables");
        
        return lowerBounds.get(index);
    }

    protected void setLowerBounds(List<Integer> lowerBounds) {
    	
    	Preconditions.checkNotNull(lowerBounds, "The lower bounds should not be null");
    	Preconditions.checkArgument(lowerBounds.size() == getNumberOfVariables(), "The lower bounds should not be equals to the number of variables");
    	
        this.lowerBounds = lowerBounds;
    }

    protected void setUpperBounds(List<Integer> upperBounds) {
    	
    	Preconditions.checkNotNull(upperBounds, "The upper bounds should not be null");
    	Preconditions.checkArgument(upperBounds.size() == getNumberOfVariables(), "The upper bounds should not be equals to the number of variables");
    	
        this.upperBounds = upperBounds;
    }

    protected List<Integer> getLowerBounds() {
        return lowerBounds;
    }

    protected List<Integer> getUpperBounds() {
        return upperBounds;
    }

    @Override
    public void evaluate(IntegerSolution solution) {
    	
    	Preconditions.checkNotNull(solution, "The solution should not be null");

        for (int i = 0; i < objectives.size(); i++) {
            solution.setObjective(i, objectives.get(i).evaluate(instance, (MSolution<?>) solution));
        }
    }

    @Override
    public MIntegerSolution createSolution() {

        return new MIntegerSolution(
            getNumberOfObjectives(),
            getNumberOfVariables(),
            getLowerBounds(),
            getUpperBounds()
        );
    }
}
