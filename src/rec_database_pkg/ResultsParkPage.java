package rec_database_pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ResultsParkPage extends ResultsPage {
    
    JComboBox dogReg;
    
    ActionListener dogRegListener;
    
    String currDogFilter;
    
    public ResultsParkPage(String title, GUIState stateMachine, String t, ArrayList<String> s, int w, int h, int x, int y) {
        super(title, stateMachine, t, s, w, h, x, y);

        table = t;
        
        currDogFilter = "Any";
        
        sqlColumns.add("dog_regulation");
        tblColumns.add("Dog Regulation");

        String[] dogRegOptions = {"Any", "Dogs Allowed", "Leash", "No Dogs", "Mixed"};
        dogReg = new JComboBox(dogRegOptions);
        addComponent(dogReg, 4, 0, 1, 1, GridBagConstraints.BOTH, 0, 0);
        addComponent(new JLabel("Dog regulation  "), 3, 0, 1, 1, GridBagConstraints.BOTH, 0, 0);

        backListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.changePage(new LocationParkPage("Parks", parent, table, width, height, locX, locY, selections));
            }};

        dogRegListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                currDogFilter = (String)cb.getSelectedItem();
                updateTable();
            }};
        
        buildTable();
        enableListeners();
    }
    
    public String getFilterString() {
       if (currDogFilter.equals("Any")) return "";
       return " and dog_regulation = '" + currDogFilter + "'"; 
    }
    
    public void enableListeners() {
        super.enableListeners();
        dogReg.addActionListener(dogRegListener);
    }

}
