package rec_database_pkg;

import javax.swing.JTable;
import javax.swing.table.*;

public class ResultsTable extends JTable {

    public ResultsTable(Object[][] data, Object[] columns) {
        super(data, columns);
    }
    
    public ResultsTable(DefaultTableModel t) {
       super(t); 
    }

    public boolean isCellEditable(int row, int column){  
        return false;  
    }
}
