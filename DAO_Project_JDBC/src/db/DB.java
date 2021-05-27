package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {

    private static Connection conn = null;
    
    public static Connection getConnection(){
        if(conn==null){
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Properties props = loadProperties();
            String url = props.getProperty("dburl");
           conn =  DriverManager.getConnection(url, props);
            }
            catch(SQLException e){
                throw new DbException(e.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }
    
    public static void closeConnection(){
        if(conn!= null){
            try{
            conn.close();
            }catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }
    
    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("C:\\Users\\breno\\Documents\\NetBeansProjects\\jdbc1\\src\\db\\db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch(IOException e){
            throw new DbException(e.getMessage());
        }
    }
    public static void closeStatement(Statement st){
            if(st!= null){
            try{
                    st.close();
            }catch (SQLException e){
                throw new DbException(e.getMessage());
            }
    }
    }
    public static void closeResultSet(ResultSet rs){
            if(rs!= null){
            try{
                    rs.close();
            }catch (SQLException e){
                throw new DbException(e.getMessage());
            }
    }
    }
            
}
