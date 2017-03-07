package com.github.schlak.database.QueryBuilder.Others;

import com.github.schlak.database.ConditionLinkType;
import com.github.schlak.database.QueryBuilder.PreparedStatementPart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class AConditionStack {

    /**
     * The {@link AConditionStack} list.
     */
    protected List<AConditionStack> conditionStackList;
    /**
     * The {@link AValueAllocation} list.
     */
    protected List<AValueAllocation> valueConditionList;
    /**
     * The {@link ConditionLinkType} type.
     */
    protected ConditionLinkType conditinonLinkType;

    /**
     * Instantiates a new {@link AConditionStack}.
     */
    public AConditionStack(){
        this.conditionStackList = new ArrayList<>();
        this.valueConditionList = new ArrayList<>();
        this.conditinonLinkType = ConditionLinkType.AND;
    }

    /**
     * Add {@link AValueAllocation} to the {@link AConditionStack}.
     *
     * @param valueCondition the {@link AValueAllocation}
     * @return the {@link AConditionStack}
     */
    public abstract AConditionStack addCondition(AValueAllocation valueCondition);

    /**
     * Add {@link AValueAllocation} to the {@link AConditionStack}.
     *
     * @param conditionStack the {@link AConditionStack}
     * @return the {@link AConditionStack}
     */
    public abstract AConditionStack addCondition(AConditionStack conditionStack);

    /**
     * Set the {@link ConditionLinkType}.
     *
     * @param conditionLinkType the {@link ConditionLinkType}
     * @return the {@link ConditionLinkType}
     */
    public abstract AConditionStack setConditionLinkType(ConditionLinkType conditionLinkType);

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
