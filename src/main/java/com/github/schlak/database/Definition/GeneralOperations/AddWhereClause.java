package com.github.schlak.database.Definition.GeneralOperations;

import com.github.schlak.database.Definition.GeneralObjects.ConditionStack;
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;

/**
 * Created by jonas on 15.03.17.
 */
public interface AddWhereClause {

    void where(ConditionStack conditionStack);

    void where(ValueAllocation valueAllocation);

}
