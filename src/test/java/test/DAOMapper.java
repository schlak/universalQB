package test;

import com.github.schlak.database.Definition.Statements.BasicCreate;

/**
 * Created by jonas on 07.03.17.
 */
public abstract class DAOMapper {

    protected BasicCreate createBuilder;

    public abstract void save(Object object);

    public void setTable(String string){
        createBuilder.setTable(string);
    }



}
