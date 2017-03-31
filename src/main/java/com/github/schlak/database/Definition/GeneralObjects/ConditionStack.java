package com.github.schlak.database.Definition.GeneralObjects;

import com.github.schlak.database.Definition.Cleanable;
import com.github.schlak.database.Definition.FixedValues.ConditionLinkType;
import com.github.schlak.database.ObjectRecycler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class ConditionStack implements Cleanable {


    protected List<ConditionStack> conditionStackList;
    protected List<ValueAllocation> valueConditionList;
    protected ConditionLinkType conditionLinkType;

    /**
     * Instantiates a new {@link ConditionStack}.
     */
    public ConditionStack() {
        this.clean();
    }

    /**
     * Add {@link ValueAllocation} to the {@link ConditionStack}.
     *
     * @param valueCondition the {@link ValueAllocation}
     * @return the {@link ConditionStack}
     */
    public ConditionStack addCondition(ValueAllocation valueCondition) {
        valueConditionList.add(valueCondition);
        return this;
    }

    /**
     * Add {@link ValueAllocation} to the {@link ConditionStack}.
     *
     * @param conditionStack the {@link ConditionStack}
     * @return the {@link ConditionStack}
     */
    public ConditionStack addCondition(ConditionStack conditionStack) {
        this.conditionStackList.add(conditionStack);
        return this;
    }

    /**
     * Set the {@link ConditionLinkType}.
     *
     * @param conditionLinkType the {@link ConditionLinkType}
     * @return the {@link ConditionLinkType}
     */
    public ConditionStack setConditionLinkType(ConditionLinkType conditionLinkType) {
        this.conditionLinkType = conditionLinkType;
        return this;
    }

    /**
     * Retruns the condition string.
     *
     * @return the condition string
     */
    public abstract String getConditionString();

    /**
     * Returns the {@link PreparedStatementPart}.
     *
     * @return the {@link PreparedStatementPart}
     */
    public abstract PreparedStatementPart getStatementPreparationBox();

    @Override
    public void clean() {

        if (conditionStackList != null){
            conditionStackList.forEach(ObjectRecycler::returnInstance);
            conditionStackList.clear();
        }else {
            conditionStackList = new ArrayList<>();
        }

        if (valueConditionList != null){
            valueConditionList.forEach(ObjectRecycler::returnInstance);
            valueConditionList.clear();
        }else{
            valueConditionList = new ArrayList<>();
        }

        this.setConditionLinkType(ConditionLinkType.AND);
    }
}
