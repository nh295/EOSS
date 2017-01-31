/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knowledge.constraint;

import aos.creditassigment.CreditFunctionInputType;
import aos.creditassigment.CreditFitnessFunctionType;
import aos.creditassigment.Credit;
import aos.creditassigment.CreditDefinedOn;
import aos.creditassignment.setcontribution.AbstractPopulationContribution;
import java.util.Collection;
import java.util.HashMap;
import org.moeaframework.core.Population;
import org.moeaframework.core.Solution;
import org.moeaframework.core.Variation;

/**
 * 
 *
 * @author Nozomi
 */
public class PopulationConsistency extends AbstractPopulationContribution {

    /**
     * Maps which operators are responsible for each constraint
     */
    private final HashMap<String, Variation> constraintOperatorMap;

    /**
     * Constructor to specify how to update the probability of applying each
     * operator based on the consistency of solutions in a population to a
     * constraint
     *
     * @param constraintOperatorMap
     */
    public PopulationConsistency(HashMap<String, Variation> constraintOperatorMap) {
        super();
        this.operatesOn = CreditDefinedOn.POPULATION;
        this.fitType = CreditFitnessFunctionType.Do;
        this.inputType = CreditFunctionInputType.CS;
        this.constraintOperatorMap = constraintOperatorMap;
    }

    @Override
    public String toString() {
        return "AdapativeConstraint";
    }

    /**
     *
     * @param population for this implementation it should be the current
     * population
     * @param operators
     * @param iteration
     * @return
     */
    @Override
    public HashMap<Variation, Credit> compute(Population population, Collection<Variation> operators, int iteration) {
        HashMap<Variation, Credit> out = new HashMap();
        for (String constraint : constraintOperatorMap.keySet()) {
            int consistentCount = 0;
            for (Solution s : population) {
                if((double)s.getAttribute(constraint) == 0){
                    consistentCount++;
                }
            }
            double probability = Math.min((double)consistentCount / (double)population.size(), 0.03);
            out.put(constraintOperatorMap.get(constraint), new Credit(iteration, probability));
        }
        return out;
    }
}