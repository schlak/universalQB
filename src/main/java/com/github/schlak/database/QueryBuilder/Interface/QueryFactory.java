package com.github.schlak.database.QueryBuilder.Interface;

import com.github.schlak.database.Definition.GeneralObjects.*;
import com.github.schlak.database.Definition.Statements.*;
import com.github.schlak.database.Manager.IDProvider;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public interface QueryFactory {

    /**
     * Returns a new {@link BasicSelectBuilder}.
     *
     * @return the {@link BasicSelectBuilder}
     */
    BasicSelectBuilder getSelectBuilder();

    /**
     * Returns a new {@link BasicUpdateBuilder}.
     *
     * @return the {@link BasicUpdateBuilder}
     */
    BasicUpdateBuilder getUpdateBuilder();

    /**
     * Returns a new {@link BasicInsertBuilder}.
     *
     * @return the {@link BasicInsertBuilder}
     */
    BasicInsertBuilder getInsertBuilder();

    /**
     * Returns a new {@link BasicDeleteBuilder}.
     *
     * @return the {@link BasicDeleteBuilder}
     */
    BasicDeleteBuilder getDeleteBuilder();

    /**
     * Returns a new {@link BasicCreateBuilder}.
     *
     * @return the {@link BasicCreateBuilder}
     */
    BasicCreateBuilder getCreateBuilder();

    /**
     * Returns a new {@link ConditionStack}.
     *
     * @return the new {@link ConditionStack}
     */
    ConditionStack getNewConditionStackInstance();

    /**
     * Returns a new db column instance.
     *
     * @return the new {@link Column}
     */
    Column getNewDBColumnInstance();

    /**
     * Returns a new {@link JoinCondition}.
     *
     * @return the new {@link JoinCondition}
     */
    JoinCondition getNewJoinConditionInstance();

    /**
     * Returns a new {@link OrderByDefinition}.
     *
     * @return the new {@link OrderByDefinition}
     */
    OrderByDefinition getNewOrderByColumnInstance();

    /**
     * Returns a new {@link TableJoinInformation}.
     *
     * @return the new {@link TableJoinInformation}
     */
    TableJoinInformation getNewTableJoinInformationInstance();

    /**
     * Returns a new {@link ValueAllocation}.
     *
     * @return the new value allocation instance
     */
    ValueAllocation getNewValueAllocationInstance();

    /**
     * Returns a new {@link IDProvider}.
     *
     * @return the {@link IDProvider}
     */
    IDProvider getIdProvider();
}
