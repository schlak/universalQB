package com.github.schlak.database.Definition.GeneralObjects;

import com.github.schlak.database.Definition.FixedValues.ConditionLinkType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class ConditionStack {

    /**
     * The {@link ConditionStack} list.
     */
    protected List<ConditionStack> conditionStackList;
    /**
     * The {@link ValueAllocation} list.
     */
    protected List<ValueAllocation> valueConditionList;
    /**
     * The {@link ConditionLinkType} type.
     */
    protected ConditionLinkType conditinonLinkType;

    /**
     * Instantiates a new {@link ConditionStack}.
     */
    public ConditionStack() {
        this.conditionStackList = new ArrayList<>();
        this.valueConditionList = new ArrayList<>();
        this.conditinonLinkType = ConditionLinkType.AND;
    }

    /**
     * Add {@link ValueAllocation} to the {@link ConditionStack}.
     *
     * @param valueCondition the {@link ValueAllocation}
     * @return the {@link ConditionStack}
     */
    public abstract ConditionStack addCondition(ValueAllocation valueCondition);

    /**
     * Add {@link ValueAllocation} to the {@link ConditionStack}.
     *
     * @param conditionStack the {@link ConditionStack}
     * @return the {@link ConditionStack}
     */
    public abstract ConditionStack addCondition(ConditionStack conditionStack);

    /**
     * Set the {@link ConditionLinkType}.
     *
     * @param conditionLinkType the {@link ConditionLinkType}
     * @return the {@link ConditionLinkType}
     */
    public abstract ConditionStack setConditionLinkType(ConditionLinkType conditionLinkType);

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
}
