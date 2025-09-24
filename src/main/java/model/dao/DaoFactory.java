package model.dao;

import db.DB;
import model.implementations.ClientDaoJDBC;
import model.implementations.ProductDaoJDBC;

public class DaoFactory {

    public static ClientDao createClientDao() {
        return new ClientDaoJDBC(DB.getConnection());
    }

    public static ProductDao createProductDao() {
        return new ProductDaoJDBC(DB.getConnection());
    }

}
