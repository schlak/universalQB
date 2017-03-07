package com.github.schlak.database.QueryBuilder.MysqlQueryBuilder.Others;

import com.github.schlak.database.ConditionLinkType;
import com.github.schlak.database.QueryBuilder.PreparedStatementPart;
import com.github.schlak.database.QueryBuilder.Others.AConditionStack;
import com.github.schlak.database.QueryBuilder.Others.AValueAllocation;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MysqlConditionStack extends AConditionStack {

    @Override
    public AConditionStack addCondition(AValueAllocation valueCondition) {
        this.valueConditionList.add(valueCondition);
        return this;
    }

    @Override
    public AConditionStack addCondition(AConditionStack conditionStack) {
        //TODO prevent giving it self as a condition stack
        if (conditionStack != this)
            this.conditionStackList.add(conditionStack);
        return this;
    }

    @Override
    public AConditionStack setConditionLinkType(ConditionLinkType conditionLinkType) {
        this.conditinonLinkType = conditionLinkType;
        return this;
    }

    @Override
    public String getConditionString() {
        String returnString = "";
        boolean isFirst = true;

        for (AValueAllocation valueAllocation :
                valueConditionList) {
            if (!isFirst) returnString = returnString.concat(" " + this.conditinonLinkType.toString() + " ");
            returnString = returnString.concat(valueAllocation.toString());
            isFirst = false;
        }

        if (valueConditionList.size() == 0) isFirst = true;


        for (AConditionStack conditionStack :
                conditionStackList) {
            if (!isFirst) returnString = returnString.concat(" " + this.conditinonLinkType.toString() + " ");
            returnString = returnString.concat(" ( " + conditionStack.toString() + " ) ");
            isFirst = false;
        }

        return returnString;
    }

    @Override
    public PreparedStatementPart getStatementPreparationBox() {
        PreparedStatementPart sPB = new PreparedStatementPart();
        boolean isFirst = true;

        for (AValueAllocation valueAllocation :
                valueConditionList) {
            if (!isFirst) sPB.string = sPB.string.concat(" " + this.conditinonLinkType.toString() + " ");
            sPB.string = sPB.string.concat(valueAllocation.getColumn() + " = ? ");
            sPB.queue.add(valueAllocation.getValue());
            isFirst = false;
        }

        for (AConditionStack conditionStack : conditionStackList) {
            sPB.add(conditionStack.getStatementPreparationBox(), conditinonLinkType);
        }

        return sPB;
    }


}
