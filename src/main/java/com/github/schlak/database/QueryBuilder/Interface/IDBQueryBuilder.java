package com.github.schlak.database.QueryBuilder.Interface;

import com.github.schlak.database.QueryBuilder.*;
import com.github.schlak.database.QueryBuilder.Manager.IDProvider;
import com.github.schlak.database.QueryBuilder.Others.*;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public interface IDBQueryBuilder {

    /**
     * Returns a new {@link ADBSelectBuilder}.
     *
     * @return the {@link ADBSelectBuilder}
     */
    ADBSelectBuilder getSelectBuilder();

    /**
     * Returns a new {@link ADBUpdateBuilder}.
     *
     * @return the {@link ADBUpdateBuilder}
     */
    ADBUpdateBuilder getUpdateBuilder();

    /**
     * Returns a new {@link ADBInsertBuilder}.
     *
     * @return the {@link ADBInsertBuilder}
     */
    ADBInsertBuilder getInsertBuilder();

    /**
     * Returns a new {@link ADBDeleteBuilder}.
     *
     * @return the {@link ADBDeleteBuilder}
     */
    ADBDeleteBuilder getDeleteBuilder();

    /**
     * Returns a new {@link ADBCreateBuilder}.
     *
     * @return the {@link ADBCreateBuilder}
     */
    ADBCreateBuilder getCreateBuilder();

    /**
     * Returns a new {@link AConditionStack}.
     *
     * @return the new {@link AConditionStack}
     */
    AConditionStack getNewConditionStackInstance();

    /**
     * Returns a new db column instance.
     *
     * @return the new {@link ADBColumn}
     */
    ADBColumn getNewDBColumnInstance();

    /**
     * Returns a new {@link AJoinCondition}.
     *
     * @return the new {@link AJoinCondition}
     */
    AJoinCondition getNewJoinConditionInstance();

    /**
     * Returns a new {@link AOrderByColumn}.
     *
     * @return the new {@link AOrderByColumn}
     */
    AOrderByColumn getNewOrderByColumnInstance();

    /**
     * Returns a new {@link ATableJoinInformation}.
     *
     * @return the new {@link ATableJoinInformation}
     */
    ATableJoinInformation getNewTableJoinInformationInstance();

    /**
     * Returns a new {@link AValueAllocation}.
     *
     * @return the new value allocation instance
     */
    AValueAllocation getNewValueAllocationInstance();

    /**
     * Returns a new {@link IDProvider}.
     *
     * @return the {@link IDProvider}
     */
    IDProvider getIdProvider();
}
