package com.schlak.Database.QuerryBuilder.Interface;

import com.schlak.Database.QuerryBuilder.Manager.IDProvider;

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
