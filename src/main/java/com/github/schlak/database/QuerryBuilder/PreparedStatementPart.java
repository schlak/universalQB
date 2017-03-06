package com.schlak.Database.QuerryBuilder;

import com.schlak.Database.ConditionLinkType;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by jschl on 14.01.2017.
 */
public class PreparedStatementPart {

    /**
     * The queue contains the parameters that are going to be bound to the string.
     */
    public Queue<String> queue = new ArrayDeque<>();
    /**
     * The String contains the part of the statement containing question marks instead of the values.
     */
    public String string = "";

    /**
     * The method is used to add a part of the string connected by an {@link ConditionLinkType}.
     *
     * @param preparedStatementPart the prepared statement part
     * @param conditionLinkType     the condition link type
     */
    @Deprecated
    public void add(PreparedStatementPart preparedStatementPart, ConditionLinkType conditionLinkType) {
        this.queue.addAll(preparedStatementPart.queue);

        if (string.equals("")) {
            string = preparedStatementPart.string;
        } else {
            string = string.concat(" " + conditionLinkType.toString() + " ");
            string = string.concat("(" + preparedStatementPart.string + ")");
        }

    }

}
