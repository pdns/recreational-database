package rec_database_pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class LocationPage extends Page {
    
    String table;
    ArrayList<String> nbrhdList;
    ArrayList<JCheckBox> options;
    
    ArrayList<String> prevSelections;

    JButton backBtn;
    JButton nextBtn;
    
    ActionListener backListener;
    ActionListener nextListener;
    
    public LocationPage(String title, GUIState stateMachine, String t, int w, int h, int x, int y) {
        super(title, stateMachine, w, h, x, y);
        
        table = t;
        nbrhdList = new ArrayList<String>();
        options = new ArrayList<JCheckBox>();
        
        backBtn = new JButton("Back");
        nextBtn = new JButton("Search");
        
        addComponent(backBtn, 0, 2, 1, 1, GridBagConstraints.BOTH, 0, 0);
        addComponent(nextBtn, 2, 2, 1, 1, GridBagConstraints.BOTH, 0, 0);
        JLabel newLabel = new JLabel("Please select the neighbourhoods to search through.");
        newLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(newLabel, 0, 0, 3, 1, GridBagConstraints.BOTH, 1, 0);
        
        backListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.changePage(new MainPage(parent, width, height, locX, locY));
            }};
    }
    
    public void enableListeners() {
        backBtn.addActionListener(backListener);
        nextBtn.addActionListener(nextListener);
    }
    
    public void getNeighbourhoodList(String query) {
        try {
            ResultSet results = parent.query(query);
            while (results.next()) {
                String nName = results.getString("neighbourhood");
                String nCount = results.getString("count(park_id)");
                nbrhdList.add(nName);
                JCheckBox newBox = new JCheckBox(nName + " (" + nCount + ")", prevSelections.contains(nName));
                options.add(newBox);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        displayMenu();
    }
    
    public void displayMenu() {
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        panel.setLayout(layout);
        int count = 0;
        for (JCheckBox b: options) {
            int col = count%3;
            int row = count++/3;
            constraints.gridx = col;
            constraints.gridy = row;

            layout.setConstraints(b, constraints);
            panel.add(b);
        }
        addComponent(panel, 0, 1, 3, 1, GridBagConstraints.BOTH, 1, 1);
    }
    
    public ArrayList<String> getSelectionList() {
        ArrayList<String> selections = new ArrayList<String>();
        int count = 0;
        for (JCheckBox b: options) {
           if (b.isSelected()) {
              selections.add(nbrhdList.get(count));
           }
           count++;
        }
        return selections;
    }

}
