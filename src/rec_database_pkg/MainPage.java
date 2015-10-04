package rec_database_pkg;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends Page {
    
    JLabel label;
    
    JButton parksBtn;
    JButton baseballBtn;
    JButton basketballBtn;
    JButton tennisBtn;
    
    ActionListener parksListener;
    ActionListener baseballListener;
    ActionListener basketballListener;
    ActionListener tennisListener;
    
    public MainPage(GUIState stateMachine, int w, int h, int x, int y) {
        super("Parks and Recreational Database", stateMachine, w, h, x, y);
        
        label = new JLabel("Welcome. Please select what you are looking for.");
        parksBtn = new JButton("Parks");
        baseballBtn = new JButton("Baseball Fields");
        basketballBtn = new JButton("Basketball Courts");
        tennisBtn = new JButton("Tennis Courts");
        
        addComponent(label, 0, 0, 2, 1, GridBagConstraints.BOTH, 0, 0);
        addComponent(parksBtn, 0, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
        addComponent(baseballBtn, 1, 1, 1, 1, GridBagConstraints.BOTH, 0, 0);
        addComponent(basketballBtn, 0, 2, 1, 1, GridBagConstraints.BOTH, 0, 0);
        addComponent(tennisBtn, 1, 2, 1, 1, GridBagConstraints.BOTH, 0, 0);
        
        parksListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.changePage(new LocationParkPage("Parks", parent, "parks", width, height, locX, locY));
            }};
        baseballListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.changePage(new LocationBaseballPage("Baseball Fields", parent, "baseball_fields", width, height, locX, locY));
            }};
        basketballListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.changePage(new LocationBasketballPage("Basketball Courts", parent, "basketball_courts", width, height, locX, locY));
            }};
        tennisListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.changePage(new LocationTennisPage("Tennis Courts", parent, "tennis_courts", width, height, locX, locY));
            }};
            
        enableListeners();
    }
    
    public void enableListeners() {
        parksBtn.addActionListener(parksListener);
        baseballBtn.addActionListener(baseballListener);
        basketballBtn.addActionListener(basketballListener);
        tennisBtn.addActionListener(tennisListener);
    }
    
}
