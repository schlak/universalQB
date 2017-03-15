package com.github.schlak.database.Definition.GeneralOperations;

import com.github.schlak.database.Definition.GeneralObjects.Column;

/**
 * Created by jonas on 15.03.17.
 */
public interface AddGroupByClause {

    void groupBy(Column column);
}
