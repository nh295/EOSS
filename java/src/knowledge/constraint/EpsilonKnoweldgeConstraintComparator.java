/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knowledge.constraint;

import org.moeaframework.core.Solution;
import org.moeaframework.core.comparator.EpsilonBoxDominanceComparator;

/**
 *
 * @author nozomihitomi
 */
public class EpsilonKnoweldgeConstraintComparator extends EpsilonBoxDominanceComparator {

    private static final long serialVersionUID = -9189000195762110959L;

    /**
     * The aggregate constraint comparator.
     */
    private final KnowledgeConstraintComparator comparator;

    public EpsilonKnoweldgeConstraintComparator(double epsilon, KnowledgeConstraintComparator kcc) {
        super(epsilon);
        this.comparator = kcc;
    }

    public EpsilonKnoweldgeConstraintComparator(double[] epsilons, KnowledgeConstraintComparator kcc) {
        super(epsilons);
        this.comparator = kcc;
    }

    @Override
    public int compare(Solution solution1, Solution solution2) {
        int flag = this.comparator.compare(solution1, solution2);

        if (flag != 0) {
            isSameBox = false;
            return flag;
        } else {
            return super.compare(solution1, solution2);
        }
    }

}
