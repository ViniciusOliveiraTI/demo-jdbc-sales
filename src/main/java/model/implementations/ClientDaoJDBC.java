package model.implementations;

import db.DbException;
import model.dao.ClientDao;
import model.entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoJDBC implements ClientDao {

    private final Connection conn;

    public ClientDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Client client) {

        String sql = """
                INSERT INTO client (name, email, contact, address)
                VALUES (?, ?, ?, ?)
                """;

        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, client.getName());
            st.setString(2, client.getEmail());
            st.setString(3, client.getContact());
            st.setString(4, client.getAddress());

            int rows = st.executeUpdate();

            if (rows == 0) {
                throw new DbException("Unexpected error. No rows affected");
            }

            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    client.setId(rs.getInt(1));
                }
            }

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public void update(Client client) {

        String sql = """
                UPDATE client
                SET name = ?, email = ?, contact = ?, address = ?
                WHERE id = ?
                """;

        try (PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, client.getName());
            st.setString(2, client.getEmail());
            st.setString(3, client.getContact());
            st.setString(4, client.getAddress());
            st.setInt(5, client.getId());

            st.executeUpdate();

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public void deleteById(Integer id) {

        String sql = """
                DELETE FROM client WHERE id = ?
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
    public Client findById(Integer id) {

        String sql = """
                SELECT * FROM client
                WHERE id = ?
                """;

        try (PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {

                if (rs.next()) {

                    return instantiateClient(rs);

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
    public List<Client> findAll() {

        String sql = """
                SELECT * FROM client
                """;

        try (PreparedStatement st = conn.prepareStatement(sql)) {

            List<Client> clients = new ArrayList<>();

            try (ResultSet rs = st.executeQuery()) {

                while (rs.next()) {
                    clients.add(instantiateClient(rs));
                }

                return clients;

            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    private Client instantiateClient(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String contact = rs.getString("contact");
        String address = rs.getString("address");

        return new Client(id, name, email, contact, address);

    }

}
