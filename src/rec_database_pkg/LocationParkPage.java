package rec_database_pkg;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LocationParkPage extends LocationPage {
    
    public LocationParkPage(String title, GUIState stateMachine, String t, int w, int h, int x, int y) {
        super(title, stateMachine, t, w, h, x, y);
        
        prevSelections = new ArrayList<String>();

        getNeighbourhoodList("select neighbourhood, count(park_id) from " + table + " group by neighbourhood");

        nextListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> selections = getSelectionList();
                if (selections.size() < 1) {
                    JOptionPane.showMessageDialog(null, "Please select at least one neighbourhood."); 
                    return;
                }
                parent.changePage(new ResultsParkPage("Parks - Search Results", parent, table, selections, width, height, locX, locY));
            }};

        enableListeners();
    }

    public LocationParkPage(String title, GUIState stateMachine, String t, int w, int h, int x, int y, ArrayList<String> prevSel) {
        super(title, stateMachine, t, w, h, x, y);
        
        prevSelections = prevSel;

        getNeighbourhoodList("select neighbourhood, count(park_id) from " + table + " group by neighbourhood");

        nextListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> selections = getSelectionList();
                if (selections.size() < 1) {
                    JOptionPane.showMessageDialog(null, "Please select at least one neighbourhood."); 
                    return;
                }
                parent.changePage(new ResultsParkPage("Parks - Search Results", parent, table, selections, width, height, locX, locY));
            }};

        enableListeners();
    }
}
