package com.thenairn.rsscripts.lightlib.utils.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class MarchingAnts extends Observable {

    private BasicStroke stroke;

    private float dashPhase = 0f;
    private float dash[] = {5.0f, 5.0f};

    private ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            dashPhase += 9.0f;
            stroke = new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.5f, dash, dashPhase);
            setChanged();
            notifyObservers();
        }
    };

    public MarchingAnts() {
        stroke = new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 1.5f, dash, dashPhase);
    }

    private Timer timer = new Timer(40, listener);

    public BasicStroke getStroke() {
        return stroke;
    }

    public void startAnimation() {
        timer.start();
    }

    public void stopAnimation() {
        timer.stop();
    }

    /*
    //Declare
private MarchingAnts ants = new MarchingAnts();

...

//Start animation
ants.startAnimation();

...

//Add observers
ants.addObserver((obj, obs) -> repaint()); //repaint() method from a painting component

...

//Use stroke
g2d.setStroke(ants.getStroke());
     */

}