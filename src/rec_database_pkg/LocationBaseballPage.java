package rec_database_pkg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class LocationBaseballPage extends LocationSportPage {
    
    public LocationBaseballPage(String title, GUIState stateMachine, String t, int w, int h, int x, int y) {
        super(title, stateMachine, t, w, h, x ,y);

        nextListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> selections = getSelectionList();
                if (selections.size() < 1) {
                    JOptionPane.showMessageDialog(null, "Please select at least one neighbourhood."); 
                    return;
                }
                parent.changePage(new ResultsBaseballPage("Baseball Fields - Search Results", parent, table, selections, width, height, locX, locY));
            }};
            
        enableListeners();
    }

    public LocationBaseballPage(String title, GUIState stateMachine, String t, int w, int h, int x, int y, ArrayList<String> prevSel) {
        super(title, stateMachine, t, w, h, x ,y, prevSel);

        nextListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> selections = getSelectionList();
                if (selections.size() < 1) {
                    JOptionPane.showMessageDialog(null, "Please select at least one neighbourhood."); 
                    return;
                }
                parent.changePage(new ResultsBaseballPage("Baseball Fields - Search Results", parent, table, selections, width, height, locX, locY));
            }};
            
        enableListeners();
    }
}