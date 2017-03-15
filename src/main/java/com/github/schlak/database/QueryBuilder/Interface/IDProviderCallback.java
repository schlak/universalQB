package com.github.schlak.database.QueryBuilder.Interface;

import com.github.schlak.database.Manager.IDProvider;

/**
 * Created by jschl on 28.01.2017.
 */
public interface IDProviderCallback {

    /**
     * Returns a instance of the {@link IDProvider}.
     *
     * @return the {@link IDProvider}
     */
    public IDProvider getProvider();

}
