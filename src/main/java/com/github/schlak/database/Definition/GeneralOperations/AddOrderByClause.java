package com.github.schlak.database.Definition.GeneralOperations;

import com.github.schlak.database.Definition.GeneralObjects.OrderByDefinition;

/**
 * Created by jonas on 15.03.17.
 */
public interface AddOrderByClause {

    void orderBy(OrderByDefinition orderByColumn);
}
