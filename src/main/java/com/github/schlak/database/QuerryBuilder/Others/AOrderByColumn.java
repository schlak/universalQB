package com.github.schlak.database.QuerryBuilder.Others;

import com.github.schlak.database.DBOrderByStrategy;

/**
 * Created by Jonas Schlak on 15.10.2016.
 */
public abstract class AOrderByColumn {

    /**
     * The Column.
     */
    protected ADBColumn column;
    /**
     * The Db order by strategy.
     */
    protected DBOrderByStrategy dbOrderByStrategy;

    /**
     * Instantiates a new {@link AOrderByColumn} object.
     */
    public AOrderByColumn() {
        this.dbOrderByStrategy = DBOrderByStrategy.ASC;
    }

    /**
     * Set the {@link ADBColumn}.
     *
     * @param iDBColumn the {@link ADBColumn}
     * @return the {@link AOrderByColumn}
     */
    public abstract AOrderByColumn setColumn(ADBColumn iDBColumn);

    /**
     * Set the {@link DBOrderByStrategy}.
     *
     * @param orderBYStrategy the {@link DBOrderByStrategy}
     * @return the {@link DBOrderByStrategy}
     */
    public abstract AOrderByColumn setDBOrderBYStrategy(DBOrderByStrategy orderBYStrategy);

    /**
     * Returns the order by string.
     *
     * @return the order by string
     */
    public abstract String getOrderByString();
}
