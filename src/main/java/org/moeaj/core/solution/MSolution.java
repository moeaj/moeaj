package org.moeaj.core.solution;

import com.google.common.base.Preconditions;
import org.uma.jmetal.solution.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing a Solution
 *
 * @author Thiago Ferreira
 */
public abstract class MSolution <T> implements Solution<T> {

    private static final long serialVersionUID = 3115245004876220948L;

	/** The objectives values */
    protected double[] objectives;

    /** The variables values */
    protected List<T> variables;

    /** The attributes values */
    protected Map<Object, Object> attributes;

    /**
     * Constructor
     *
     * @param numberOfObjectives the number of objectives
     * @param numberOfVariables the number of variables
     */
    public MSolution(int numberOfObjectives, int numberOfVariables) {

        Preconditions.checkArgument(numberOfObjectives >= 1, "The number of objectives should be >= 1");
        Preconditions.checkArgument(numberOfVariables >= 1, "The number of variables should be >= 1");

        this.objectives = new double[numberOfObjectives];
        this.variables = new ArrayList<>(numberOfVariables);

        for (int i = 0; i < getNumberOfObjectives(); i++) {
            objectives[i] = 0.0;
        }

        for (int i = 0; i < numberOfVariables; i++) {
            variables.add(i, null);
        }

        this.attributes = new HashMap<>();
    }
    
    /**
     * Copy Constructor
     * 
     * @param solution to be copied
     */
	public MSolution(MSolution<T> solution) {
		this(solution.getNumberOfObjectives(), solution.getNumberOfVariables());

		for (int i = 0; i < solution.getNumberOfObjectives(); i++) {
			setObjective(i, solution.getObjective(i));
		}

		setAttributes(new HashMap<>(solution.getAttributes()));
	}

    public List<T> getVariables() {
        return variables;
    }

    public void setVariables(List<T> variables) {

        Preconditions.checkNotNull(variables, "The variables should not be null");
        Preconditions.checkArgument(!variables.isEmpty(), "The variables should not be empty");

        this.variables = variables;
    }

    public int getNumberOfObjectives() {
        return objectives.length;
    }

    public int getNumberOfVariables() {
        return variables.size();
    }

    public void setObjectives(double[] objectives) {

        Preconditions.checkNotNull(objectives, "The objectives should not be null");
        Preconditions.checkArgument(objectives.length >= 1, "The number of objectives should be >= 1");

        this.objectives = objectives;
    }

    @Override
    public double[] getObjectives() {
        return objectives;
    }

    @Override
    public String getVariableValueString(int index) {

        Preconditions.checkArgument(index >= 0, "The variable index should be >= 0");
        Preconditions.checkArgument(index < getNumberOfVariables(), "The variable index should be less than the number of variables");

        return getVariableValue(index).toString();
    }

    @Override
    public T getVariableValue(int index) {

        Preconditions.checkArgument(index >= 0, "The variable index should be >= 0");
        Preconditions.checkArgument(index < getNumberOfVariables(), "The variable index should be less than the number of variables");

        return variables.get(index);
    }

    @Override
    public void setVariableValue(int index, T value){

        Preconditions.checkArgument(index >= 0, "The variable index should be >= 0");
        Preconditions.checkArgument(index < getNumberOfVariables(), "The variable index should be less than the number of variables");

        variables.set(index, value);
    }

    @Override
    public void setObjective(int index, double value) {

        Preconditions.checkArgument(index >= 0, "The index should be >= 0");
        Preconditions.checkArgument(index < getNumberOfObjectives(), "The index should be less than the number of objectives");

        this.objectives[index] = value;
    }

    @Override
    public double getObjective(int index) {

        Preconditions.checkArgument(index >= 0, "The index should be >= 0");
        Preconditions.checkArgument(index < getNumberOfObjectives(), "The index should be less than the number of objectives");

        return objectives[index];
    }

    @Override
    public void setAttribute(Object id, Object value) {
        this.attributes.put(id, value) ;
    }

    @Override
    public Object getAttribute(Object key) {

        Preconditions.checkNotNull(key, "The key should not be null");

        return attributes.get(key) ;
    }

    public Map<Object, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<Object, Object> attributes) {

        Preconditions.checkNotNull(attributes, "The attributes should not be null");

        this.attributes = attributes;
    }

    @Override
    public String toString() {
        
    	StringBuilder builder = new StringBuilder();
    	
    	builder.append(Arrays.toString(getObjectives()));
    	builder.append(getVariables());
    	
    	return builder.toString();
    }
    
    public abstract void initialize();
}
