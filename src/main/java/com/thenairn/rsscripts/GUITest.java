package com.thenairn.rsscripts;

import com.thenairn.rsscripts.gui.LightGUI;
import com.thenairn.rsscripts.gui.component.LightImage;
import com.thenairn.rsscripts.gui.component.LightLabel;
import com.thenairn.rsscripts.gui.component.LightLabelButton;
import com.thenairn.rsscripts.gui.event.LightMouseEvent;
import com.thenairn.rsscripts.resource.Fonts;
import com.thenairn.rsscripts.utils.items.ItemAPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Thomas Nairn on 16/03/2016.
 */
public class GUITest extends JFrame implements ActionListener {

    LightGUI gui;

    Timer timer = new Timer(20, this);

    public GUITest() throws HeadlessException {
        this.setSize(300, 300);
        PCanvas canvas = new PCanvas();
        this.add(canvas);
        gui = new LightGUI(canvas);
        ItemAPI.init();
        LightImage imageComponent = new LightImage(100, 100, ItemAPI.get(45).getIcon());
        gui.add(imageComponent);
        String text = "the quick brown fox jumped over the lazy dog. the lonely fox chases after the one-eyed hound";
        gui.add(new LightLabel(20, 0, text));
        gui.add(new LightLabelButton(20, 30, "Hello Button", Fonts.RUNESCAPE_CHAT_BOLD, 12, 10,
                Color.BLACK, Color.WHITE,
                Color.WHITE, Color.BLACK) {
            @Override
            public boolean mouseClicked(LightMouseEvent event) {
                System.out.println("YOYOYOY");
                return false;
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        new GUITest().setVisible(true);
    }

    private class PCanvas extends JPanel {
        public PCanvas() {
            setDoubleBuffered(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            gui.onPaint((Graphics2D) g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            repaint();// this will call at every 1 second
        }
    }
}
