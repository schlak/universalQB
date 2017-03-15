package com.github.schlak.database.Definition.GeneralOperations;

import com.github.schlak.database.Definition.GeneralObjects.TableJoinInformation;

/**
 * Created by jonas on 15.03.17.
 */
public interface AddJoinClause {

    void join(TableJoinInformation joinInformation);

}
