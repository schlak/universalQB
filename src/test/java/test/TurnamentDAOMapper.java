package test;

/**
 * Created by jonas on 07.03.17.
 */
public class TurnamentDAOMapper extends DAOMapper {


    @Override
    public void save(Object object) {
        createBuilder.setTable(object.getClass().getName());


    }
}
