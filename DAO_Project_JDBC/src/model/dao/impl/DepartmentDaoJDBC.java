package model.dao.impl;

import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC() {
    }

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
       PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO department (Name) "
                    + "values (?)");
            st.setString(1, obj.getName());
            int rowsAffected = st.executeUpdate();
            st.executeUpdate();
            if (rowsAffected == 0) {
                throw new DbException("Unexpected error! No Rows affected!");
            }
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {
       PreparedStatement st = null;
       
        try {
            st = conn.prepareStatement(
                    "UPDATE department "
                    + "SET Name = ?"
                    + "WHERE Id = ?");
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
    @Override
    public void deleteById(Integer id) {
       PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "DELETE FROM department WHERE id = ?");
            st.setInt(1, id);
            int rows = st.executeUpdate();
            if ( rows == 0){
                throw new DbException("ID does not exist!");
            }
            st.executeUpdate();
           
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT id, name FROM department WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Department dep = instantiateDepartment(rs);
                return dep;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);

        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department");
            rs = st.executeQuery();
            List<Department> depart = new ArrayList<>();
            while (rs.next()) {
               Department dp = instantiateDepartment(rs);
               depart.add(dp);
            }
            return depart;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);

        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }

}
