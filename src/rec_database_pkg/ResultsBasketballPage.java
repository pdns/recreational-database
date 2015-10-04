package rec_database_pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ResultsBasketballPage extends ResultsSportPage {
    
    String sizeSel;
    
    JComboBox sizeFilter;
    
    ActionListener sizeFilterListener;
    
    public ResultsBasketballPage(String title, GUIState stateMachine, String t, ArrayList<String> s, int w, int h, int x, int y) {
        super(title, stateMachine, t, s, w, h, x, y);

        sqlColumns.add("court_size");
        tblColumns.add("Size");
        
        sizeSel = "Any";
        
        String[] options = {"Any", "Full", "Half"};
        sizeFilter = new JComboBox(options);
        addComponent(new JLabel("Court Size "), 3, 0, 1, 1, GridBagConstraints.BOTH, 0, 0);
        addComponent(sizeFilter, 4, 0, 1, 1, GridBagConstraints.BOTH, 0, 0);

        backListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.changePage(new LocationBasketballPage("Basketball Courts", parent, "basketball_courts", width, height, locX, locY, selections));
            }};

        sizeFilterListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                sizeSel = (String)cb.getSelectedItem();
                updateTable();
            }};

        buildTable();
        enableListeners();
    }
            
    public void enableListeners() {
        super.enableListeners();
        sizeFilter.addActionListener(sizeFilterListener);
    }

    public String getFilterString() {
       if (sizeSel.equals("Any")) return "";
       return " and court_size = '" + sizeSel + "'"; 
    }
}
