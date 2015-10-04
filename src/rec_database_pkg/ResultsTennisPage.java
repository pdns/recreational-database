package rec_database_pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ResultsTennisPage extends ResultsSportPage {

    String benchSel;

    JComboBox benchFilter;

    ActionListener benchFilterListener;
    
    public ResultsTennisPage(String title, GUIState stateMachine, String t, ArrayList<String> s, int w, int h, int x, int y) {
        super(title, stateMachine, t, s, w, h, x, y);

        sqlColumns.add("benches");
        tblColumns.add("Benches");

        benchSel = "Any";

        String[] options = {"Any", "Yes", "No"};
        benchFilter = new JComboBox(options);

        addComponent(new JLabel("Benches  "), 3, 0, 1, 1, GridBagConstraints.BOTH, 0, 0);
        addComponent(benchFilter, 4, 0, 1, 1, GridBagConstraints.BOTH, 0, 0);

        backListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.changePage(new LocationTennisPage("Tennis Courts", parent, "tennis_courts", width, height, locX, locY, selections));
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
        benchFilter.addActionListener(benchFilterListener);
    }

    public String getFilterString() {
       if (benchSel.equals("Any")) return "";
       return " and benches = '" + benchSel + "'"; 
    }
}