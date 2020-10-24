package choretracker;

import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import javax.swing.*;
import java.util.*;

@SuppressWarnings("checkstyle")
public class Chore {

    public JCheckBox checkbox;
    public JLabel costLbl;
    public int cost;
    public JButton rmvButton;
    public int choreCount;

    public Chore(){
    
    }

    public Chore(JCheckBox checkbox, JLabel costLbl, int cost, JButton rmvButton, int choreCount){
        this.checkbox = checkbox;
        this.costLbl = costLbl;
        this.cost = cost;
        this.rmvButton = rmvButton;
        this.choreCount = choreCount;
    }
    

    /**
     * @return JCheckBox return the checkbox
     */
    public JCheckBox getCheckbox() {
        return checkbox;
    }

    /**
     * @param checkbox the checkbox to set
     */
    public void setCheckbox(JCheckBox checkbox) {
        this.checkbox = checkbox;
    }

    /**
     * @return JLabel return the costLbl
     */
    public JLabel getCostLbl() {
        return costLbl;
    }

    /**
     * @param costLbl the costLbl to set
     */
    public void setCostLbl(JLabel costLbl) {
        this.costLbl = costLbl;
    }

    /**
     * @return int return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(int cost) {
        this.cost = cost;
    }


    /**
     * @return JButton return the rmvButton
     */
    public JButton getRmvButton() {
        return rmvButton;
    }

    /**
     * @param rmvButton the rmvButton to set
     */
    public void setRmvButton(JButton rmvButton) {
        this.rmvButton = rmvButton;
    }

}