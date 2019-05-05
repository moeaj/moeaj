package org.moeaj.core.solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.moeaj.util.RandomUtils;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.util.binarySet.BinarySet;

import com.google.common.base.Preconditions;

/**
 * Class representing a Binary Problem
 *
 * @author Thiago Ferreira
 */
public class MBinarySolution extends MSolution<BinarySet> implements BinarySolution {

    private static final long serialVersionUID = 2540148824243860928L;
    
    /** The number of bits per variable */
	protected List<Integer> bitsPerVariable;

	/**
	 * Constructor
	 * 
	 * @param numberOfObjectives the number of objectives
	 * @param numberOfVariables the number of variables
	 * @param bitsPerVariable the number of bit per variable
	 */
	public MBinarySolution(int numberOfObjectives, int numberOfVariables, List<Integer> bitsPerVariable) {
        super(numberOfObjectives, numberOfVariables);
        
        Preconditions.checkNotNull(bitsPerVariable, "The number of bits per variable should not be null");
        Preconditions.checkArgument(bitsPerVariable.size() == numberOfVariables, "The bits per variable should have the same number of variables");
        
        this.bitsPerVariable = bitsPerVariable;
        
        initialize();
    }
	
	/**
	 * Constructor. The default number of bits per variable is 10
	 * 
	 * @param numberOfObjectives the number of objectives
	 * @param numberOfVariables the number of variables
	 */
	public MBinarySolution(int numberOfObjectives, int numberOfVariables) {
        this(
            numberOfObjectives,
            numberOfVariables,
            Collections.nCopies(numberOfVariables, 10)
        );
    }
	
    /**
     * Copy constructor
     * 
     * @param solution to be copied
     */
    public MBinarySolution(MBinarySolution solution) {
        super(solution);

        setBitsPerVariable(new ArrayList<>(solution.getBitsPerVariable()));
        
        for (int i = 0; i < solution.getNumberOfVariables(); i++) {
            setVariableValue(i, (BinarySet) solution.getVariableValue(i).clone());
        }        
    }

    @Override
    public int getNumberOfBits(int index) {
    	
    	Preconditions.checkArgument(index >= 0, "The variable index should be >= 0");
        Preconditions.checkArgument(index < getNumberOfVariables(), "The variable index should be less than the number of variables");

        return getVariableValue(index).getBinarySetLength() ;
    }

    @Override
    public int getTotalNumberOfBits() {

        int sum = 0;

        for (int i = 0; i < getNumberOfVariables(); i++) {
            sum += getNumberOfBits(i);
        }

        return sum;
    }

    public void setBitsPerVariable(List<Integer> bitsPerVariable) {
    	
    	Preconditions.checkNotNull(bitsPerVariable, "The number of bits per variable should not be null");
    	Preconditions.checkArgument(bitsPerVariable.size() == getNumberOfVariables(), "The bits per variable should have the same number of variables");
        
        this.bitsPerVariable = bitsPerVariable;
    }

    public List<Integer> getBitsPerVariable() {
        return bitsPerVariable;
    }

    @Override
    public MBinarySolution copy() {
        return new MBinarySolution(this);
    }

    @Override
    public void initialize() {

        for (int i = 0; i < getNumberOfVariables(); i++) {
            setVariableValue(i, createNewBitSet(bitsPerVariable.get(i)));
        }
    }

    protected BinarySet createNewBitSet(int numberOfBits) {

        BinarySet bitSet = new BinarySet(numberOfBits);

        for (int i = 0; i < numberOfBits; i++) {

            if (RandomUtils.randDouble() < 0.5) {
                bitSet.set(i);
            } else {
                bitSet.clear(i);
            }
        }

        return bitSet;
    }
}
