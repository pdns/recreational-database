package rec_database_pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ResultsBaseballPage extends ResultsSportPage {
    
    String benchSel;
    String fenceSel;
    
    JComboBox benchFilter;
    JComboBox fenceFilter;
    
    ActionListener benchFilterListener;
    ActionListener fenceFilterListener;
    
    public ResultsBaseballPage(String title, GUIState stateMachine, String t, ArrayList<String> s, int w, int h, int x, int y) {
        super(title, stateMachine, t, s, w, h, x, y);
        
        sqlColumns.add("benches");
        sqlColumns.add("fences");
        tblColumns.add("Benches");
        tblColumns.add("Fences");

        benchSel = "Any";
        fenceSel = "Any";

        String[] options = {"Any", "Yes", "No"};
        benchFilter = new JComboBox(options);
        fenceFilter = new JComboBox(options);
        
        addComponent(new JLabel("Benches  "), 3, 0, 1, 1, GridBagConstraints.BOTH, 0, 0);
        addComponent(new JLabel("Fences  "), 3, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
        addComponent(benchFilter, 4, 0, 1, 1, GridBagConstraints.BOTH, 0, 0);
        addComponent(fenceFilter, 4, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
        
        backListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.changePage(new LocationBaseballPage("Baseball Fields", parent, "baseball_fields", width, height, locX, locY, selections));
            }};

        fenceFilterListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                 fenceSel = (String)cb.getSelectedItem();
                updateTable();
            }};

        benchFilterListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                 benchSel = (String)cb.getSelectedItem();
                updateTable();
            }};

        buildTable();
        enableListeners();
    }
            
    public void enableListeners() {
        super.enableListeners();
        fenceFilter.addActionListener(fenceFilterListener);
        benchFilter.addActionListener(benchFilterListener);
    }


    public String getFilterString() {
        String str = "";
        if (!benchSel.equals("Any")) str += " and benches = '" + benchSel + "'";
        if (!fenceSel.equals("Any")) str += " and fences = '" + fenceSel + "'";
        return str; 
    }
}