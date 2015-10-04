package rec_database_pkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;        

public class GUIState {
    
    private Page currPage;
    
    Connection database;
    
    public GUIState() {
        currPage = new MainPage(this, 600, 400, 0, 0);
        currPage.setVisible(true);
        
        try {
            Class.forName("org.sqlite.JDBC");
            database = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void changePage(Page newPage) {
        currPage.dispose();
        currPage = newPage;
        currPage.setVisible(true);
    }
    
    public ResultSet query(String sql_cmd) {
        ResultSet rs = null;
        try {
            Statement stat = database.createStatement();
            rs = stat.executeQuery(sql_cmd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

}
