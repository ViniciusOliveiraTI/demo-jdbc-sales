package model.dao;

import db.DB;
import model.implementations.ClientDaoJDBC;

public class DaoFactory {

    public static ClientDao createClientDao() {
        return new ClientDaoJDBC(DB.getConnection());
    }

}
