package rec_database_pkg;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;

public class Page extends JFrame implements ComponentListener {
    
    GUIState parent;
    
    GridBagLayout layout;
    GridBagConstraints constraints;
    
    int width;
    int height;
    int locX;
    int locY;
    
    public Page(String title, GUIState stateMachine, int w, int h, int x, int y) {
        super(title);
        
        parent = stateMachine;
        
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        getContentPane().setLayout(layout);
        
        width = w;
        height = h;
        locX = x;
        locY = y;
        
        this.addComponentListener(this);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width,height);
        setLocation(locX,locY);
    }
    
    protected void addComponent(Container component, int gridx, int gridy, int gridw, int gridh, int f, int weightx, int weighty) {
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridw;
        constraints.gridheight = gridh;
        constraints.fill = f;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        layout.setConstraints(component, constraints);
        getContentPane().add(component);
    }
    
    public void componentMoved(ComponentEvent e) {
        locX = this.getX();
        locY = this.getY();
    }

    public void componentResized(ComponentEvent e) {
        width = this.getWidth();
        height = this.getHeight();
    }

    public void componentShown(ComponentEvent e) {} 
    public void componentHidden(ComponentEvent e) {}
}
