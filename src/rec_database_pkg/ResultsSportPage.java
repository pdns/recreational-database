package rec_database_pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class ResultsSportPage extends ResultsPage {

    public ResultsSportPage(String title, GUIState stateMachine, String t, ArrayList<String> s, int w, int h, int x, int y) {
        super(title, stateMachine, t, s, w, h, x, y);
        
        sqlColumns.add("num_fields");
        tblColumns.add("Number of Fields");
        
        table = t + " natural join parks";

    }

}
