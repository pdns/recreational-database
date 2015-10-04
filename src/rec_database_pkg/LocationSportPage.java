package rec_database_pkg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LocationSportPage extends LocationPage {

    public LocationSportPage(String title, GUIState stateMachine, String t, int w, int h, int x, int y) {
        super(title, stateMachine, t, w, h, x, y);

        prevSelections = new ArrayList<String>();

        getNeighbourhoodList("select neighbourhood, count(park_id) from parks natural join " + table + " group by neighbourhood");
    }

    public LocationSportPage(String title, GUIState stateMachine, String t, int w, int h, int x, int y, ArrayList<String> prevSel) {
        super(title, stateMachine, t, w, h, x, y);

        prevSelections = prevSel;

        getNeighbourhoodList("select neighbourhood, count(park_id) from parks natural join " + table + " group by neighbourhood");
    }

}
