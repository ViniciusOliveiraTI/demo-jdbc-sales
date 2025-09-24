package model.implementations;

import db.DbException;
import model.dao.ProductDao;
import model.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC implements ProductDao {

    private final Connection conn;

    public ProductDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Product product) {

        String sql = """
                INSERT INTO product (name, price, stock)
                VALUES (?, ?, ?)
                """;

        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, product.getName());
            st.setDouble(2, product.getPrice());
            st.setInt(3, product.getStock());

            int rows = st.executeUpdate();

            if (rows == 0) {
                throw new DbException("Unexpected error. No rows affected");
            }

            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    product.setId(rs.getInt(1));
                }
            }

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public void update(Product product) {

        String sql = """
                UPDATE product
                SET name = ?, price = ?, stock = ?
                WHERE id = ?
                """;

        try (PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, product.getName());
            st.setDouble(2, product.getPrice());
            st.setInt(3, product.getStock());
            st.setInt(4, product.getId());

            st.executeUpdate();

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public void deleteById(Integer id) {

        String sql = """
                DELETE FROM product WHERE id = ?
                """;

        try (PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);

            st.executeUpdate();

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public Product findById(Integer id) {

        String sql = """
                SELECT * FROM product
                WHERE id = ?
                """;

        try (PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {

                if (rs.next()) {

                    return instantiateProduct(rs);

                }

                return null;

            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public List<Product> findAll() {

        String sql = """
                SELECT * FROM product
                """;

        try (PreparedStatement st = conn.prepareStatement(sql)) {

            List<Product> products = new ArrayList<>();

            try (ResultSet rs = st.executeQuery()) {

                while (rs.next()) {
                    products.add(instantiateProduct(rs));
                }

                return products;

            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    private static Product instantiateProduct(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        String name = rs.getString("name");
        double price = rs.getDouble("price");
        int stock = rs.getInt("stock");

        return new Product(id, name, price, stock);

    }

}
