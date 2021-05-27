package application;

import db.DB;
import java.sql.Connection;
import java.sql.Statement;

public class Program {

    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        try{
            
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }


    }

    
       

}

