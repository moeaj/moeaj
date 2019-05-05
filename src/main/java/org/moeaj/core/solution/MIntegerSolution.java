package org.moeaj.core.solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.moeaj.util.RandomUtils;
import org.uma.jmetal.solution.IntegerSolution;

import com.google.common.base.Preconditions;

public class MIntegerSolution extends MSolution<Integer> implements IntegerSolution {

    private static final long serialVersionUID = 8332060242589849144L;

    /** The lower bounds */
	protected List<Integer> lowerBounds;

	/** The upper bounds */
    protected List<Integer> upperBounds;

    /**
     * Constructor
     *
     * @param numberOfObjectives the number of objectives
     * @param numberOfVariables the number of variables
     * @param lowerBounds the lower bounds
     * @param upperBounds the upper bounds
     */
    public MIntegerSolution(
            int numberOfObjectives,
            int numberOfVariables,
            List<Integer> lowerBounds,
            List<Integer> upperBounds) {
        super(
            numberOfObjectives,
            numberOfVariables
         );

        Preconditions.checkNotNull(lowerBounds, "The lower bounds should not be null");
        Preconditions.checkNotNull(upperBounds, "The upper bounds should not be null");
        Preconditions.checkArgument(lowerBounds.size() == numberOfVariables, "The lower bounds should have the same number of variables");
        Preconditions.checkArgument(upperBounds.size() == numberOfVariables, "The upper bounds should have the same number of variables");

        this.lowerBounds = lowerBounds;
        this.upperBounds = upperBounds;

        initialize();
    }

    /**
     * Constructor. The default lower and upper bounds are 0 and 10 respectively
     * 
     * @param numberOfObjectives
     * @param numberOfVariables
     */
    public MIntegerSolution(int numberOfObjectives, int numberOfVariables) {
        this(
            numberOfObjectives,
            numberOfVariables,
            Collections.nCopies(numberOfVariables, 0),
            Collections.nCopies(numberOfVariables, 10)
        );
    }

    /**
     * Copy constructor
     */
	public MIntegerSolution(MIntegerSolution solution) {
		super(solution);

		setLowerBounds(new ArrayList<>(solution.getLowerBounds()));
		setUpperBounds(new ArrayList<>(solution.getUpperBounds()));

		for (int i = 0; i < solution.getNumberOfVariables(); i++) {
			setVariableValue(i, new Integer(solution.getVariableValue(i)));
		}
	}

    public void setLowerBounds(List<Integer> lowerBounds) {

        Preconditions.checkNotNull(lowerBounds, "The lower bounds should not be null");
        Preconditions.checkArgument(lowerBounds.size() == getNumberOfVariables(), "The lower bounds should not be equals to the number of variables");

        this.lowerBounds = lowerBounds;
    }

    public List<Integer> getUpperBounds() {
        return upperBounds;
    }
    
    public void setUpperBounds(List<Integer> upperBounds) {

        Preconditions.checkNotNull(upperBounds, "The upper bounds should not be null");
        Preconditions.checkArgument(lowerBounds.size() == getNumberOfVariables(), "The lower bounds should not be equals to the number of variables");

        this.upperBounds = upperBounds;
    }

    public List<Integer> getLowerBounds() {
        return lowerBounds;
    }
    
    public void setLowerBound(int index, Integer value) {

        Preconditions.checkArgument(index >= 0, "The variable index should be >= 0");
        Preconditions.checkArgument(index < getNumberOfVariables(), "The variable index should be less than the number of variables");

        this.lowerBounds.set(index, value);
    }

    public void setUpperBound(int index, Integer value) {

        Preconditions.checkArgument(index >= 0, "The variable index should be >= 0");
        Preconditions.checkArgument(index < getNumberOfVariables(), "The variable index should be less than the number of variables");

        this.upperBounds.set(index, value);
    }
    
    @Override
    public Integer getLowerBound(int index) {

        Preconditions.checkArgument(index >= 0, "The variable index should be >= 0");
        Preconditions.checkArgument(index < getNumberOfVariables(), "The variable index should be less than the number of variables");

        return lowerBounds.get(index);
    }

    @Override
    public Integer getUpperBound(int index) {

        Preconditions.checkArgument(index >= 0, "The variable index should be >= 0");
        Preconditions.checkArgument(index < getNumberOfVariables(), "The variable index should be less than the number of variables");

        return upperBounds.get(index);
    }

    @Override
    public MIntegerSolution copy() {
        return new MIntegerSolution(this);
    }

    @Override
    public void initialize() {

        for (int i = 0; i < getNumberOfVariables(); i++) {
            setVariableValue(i, RandomUtils.randInt(getLowerBound(i), getUpperBound(i)));
        }
    }
}
