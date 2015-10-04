package rec_database_pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.*;

public abstract class ResultsPage extends Page {
    
    String table;
    
    ArrayList<String> selections;
    
    ArrayList<String> sqlColumns;
    ArrayList<String> tblColumns;
    
    String currSort;
    String currSortDir;

    JButton backBtn;
    JButton restartBtn;
    JComboBox sortCol;
    JRadioButton sortAsc;
    JRadioButton sortDesc;
    
    DefaultTableModel tableModel;
    ResultsTable results;
    JScrollPane scrollPane;
    
    ActionListener backListener;
    ActionListener restartListener;
    ActionListener sortColListener;
    ActionListener sortRadioListener;
    
    public ResultsPage(String title, GUIState stateMachine, String t, ArrayList<String> s, int w, int h, int x, int y) {
        super(title, stateMachine, w, h, x, y);
        
        selections = s;
        sqlColumns = new ArrayList<String>();
        tblColumns = new ArrayList<String>();
        
        currSort = "name";
        currSortDir = "Asc";

        sqlColumns.add("name");
        sqlColumns.add("address");
        sqlColumns.add("neighbourhood");

        tblColumns.add("Park Name");
        tblColumns.add("Address");
        tblColumns.add("Neighbourhood");
        
        backBtn = new JButton("Back");
        addComponent(backBtn, 0, 3, 1, 1, GridBagConstraints.BOTH, 0, 0);
        restartBtn = new JButton("Restart");
        addComponent(restartBtn, 5, 3, 1, 1, GridBagConstraints.BOTH, 0, 0);
        
        ButtonGroup sortGrp = new ButtonGroup();
        sortAsc = new JRadioButton("Asc", true);
        sortDesc = new JRadioButton("Desc");
        sortGrp.add(sortAsc);
        sortGrp.add(sortDesc);
        addComponent(sortAsc, 0, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
        addComponent(sortDesc, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);

        addComponent(new JLabel("   Filters:      "), 2, 0, 1, 1, GridBagConstraints.BOTH, 0, 0);

        restartListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.changePage(new MainPage(parent, width, height, locX, locY));
            }};

        sortColListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                int option = (int)cb.getSelectedIndex();
                currSort = sqlColumns.get(option);
                updateTable();
            }};

        sortRadioListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currSortDir = e.getActionCommand();
                updateTable();
            }};
    }
    
    public void updateTable() {
       String[][] rows = getRows(); 
       String[] cols = getCols();
       tableModel.setDataVector(rows, cols);
    }
    
    public String[][] getRows() {
        int numCols = tblColumns.size();
        int numRows = 0;
        try {
            ResultSet rs = parent.query("select count(park_id) from " + table + getNeighbourhoodFilter() + getFilterString());
            rs.next();
            numRows = rs.getInt("count(park_id)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[][] rows = new String[numRows][numCols];

        String sqlString = "select ";
        boolean first = true;
        for (String s: sqlColumns) {
            if (first) first = false;
            else sqlString += ", ";
            sqlString += s;
        }
        sqlString += " from " + table + getNeighbourhoodFilter() + getFilterString() + getSortString();
            
        try {
            ResultSet rs = parent.query(sqlString);

            int row = 0;
            while (rs.next()) {
                for (int col=0; col<numCols; col++) {
                    String value = rs.getString(sqlColumns.get(col));
                    rows[row][col] = value;
                }
                row++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rows;
    }
    
    public String[] getCols() {
        int numCols = tblColumns.size();
        String[] arr = new String[numCols];
        int count = 0;
        for (String s: tblColumns) {
            arr[count++] = s;
        }
        return arr;
    }
    
    public void enableListeners() {
        backBtn.addActionListener(backListener);
        restartBtn.addActionListener(restartListener);
        sortCol.addActionListener(sortColListener);
        sortAsc.addActionListener(sortRadioListener);
        sortDesc.addActionListener(sortRadioListener);
    }
    
    public String getNeighbourhoodFilter() {
        String sqlString = " where (";
        boolean first = true;
        for (String s: selections) {
            if (first) first = false;
            else sqlString += " OR ";
            sqlString += "neighbourhood = '" + s + "'";
        }
        return sqlString + ")";
    }
    
    public String getSortString() {
        return " order by " + currSort + " " + currSortDir;
    }
    
    public void buildSortMenu(String[] colArray) {
        JLabel label = new JLabel("   Sort by");
        addComponent(label, 0, 0, 1, 1, GridBagConstraints.BOTH, 0, 0);

        sortCol = new JComboBox(colArray);
        addComponent(sortCol, 1, 0, 1, 1, GridBagConstraints.BOTH, 0, 0);
    }
    
    public void buildTable() {
        String[][] rows = getRows();
        String[] cols = getCols();
        
        buildSortMenu(cols);
        
        tableModel = new DefaultTableModel(rows, cols);
        results = new ResultsTable(tableModel);

        scrollPane = new JScrollPane(results); 
        results.setFillsViewportHeight(true);
        addComponent(scrollPane, 0, 2, 6, 1, GridBagConstraints.BOTH, 1, 1);
    }

    public abstract String getFilterString();
    
}
