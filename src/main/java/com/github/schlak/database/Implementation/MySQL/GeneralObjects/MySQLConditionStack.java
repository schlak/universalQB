package com.github.schlak.database.Implementation.MySQL.GeneralObjects;

import com.github.schlak.database.Definition.GeneralObjects.ConditionStack;
import com.github.schlak.database.Definition.GeneralObjects.PreparedStatementPart;
import com.github.schlak.database.Definition.GeneralObjects.ValueAllocation;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public class MySQLConditionStack extends ConditionStack {

    @Override
    public String getConditionString() {
        String returnString = "";
        boolean isFirst = true;

        for (ValueAllocation valueAllocation :
                valueConditionList) {
            if (!isFirst) returnString = returnString.concat(" " + this.conditionLinkType.toString() + " ");
            returnString = returnString.concat(valueAllocation.toString());
            isFirst = false;
        }

        if (valueConditionList.size() == 0) isFirst = true;


        for (ConditionStack conditionStack :
                conditionStackList) {
            if (!isFirst) returnString = returnString.concat(" " + this.conditionLinkType.toString() + " ");
            returnString = returnString.concat(" ( " + conditionStack.toString() + " ) ");
            isFirst = false;
        }

        return returnString;
    }

    @Override
    public PreparedStatementPart getStatementPreparationBox() {
        PreparedStatementPart sPB = new PreparedStatementPart();
        boolean isFirst = true;

        for (ValueAllocation valueAllocation :
                valueConditionList) {
            if (!isFirst) sPB.string = sPB.string.concat(" " + this.conditionLinkType.toString() + " ");
            sPB.string = sPB.string.concat(valueAllocation.getColumn() + " = ? ");
            sPB.queue.add(valueAllocation.getValue());
            isFirst = false;
        }

        for (ConditionStack conditionStack : conditionStackList) {
            sPB.add(conditionStack.getStatementPreparationBox(), conditionLinkType);
        }

        return sPB;
    }


}
