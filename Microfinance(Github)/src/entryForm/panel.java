package entryForm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class panel extends JPanel {

    public panel() {
        setBackground(new Color(100, 0, 0));
        setPreferredSize(new Dimension(1052, 592));
    }

    // Resizes the JPanel while maintaining the same aspect ratio
    // of the monitor.
    public void updatePanelSize() {

//        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
//                .getDefaultScreenDevice();
//        float monitorWidth = gd.getDisplayMode().getWidth();
//        float monitorHeight = gd.getDisplayMode().getHeight();
//
//        // Aspect ratio of the monitor in decimal form.
//        float monitorRatio = monitorWidth / monitorHeight;

        JComponent parent = (JComponent) getParent();
        float width = parent.getWidth();
        float height = parent.getHeight();

//        width = Math.min(width, height * monitorRatio);
//        height = width / monitorRatio;

        // I am subtracting the width and height by their respective aspect ratio...
        int paddedWidth = (int) width - 36;
        int paddedHeight = (int) height - 76;
        setPreferredSize(new Dimension(paddedWidth, paddedHeight));

        int resultWidth = getWidth();
        int resultHeight = getHeight();
        if (paddedWidth != resultWidth && paddedHeight != resultHeight) {
            revalidate(); // preferred dimensions not applied, so force them
        }

        System.out.println("PreferredSize: " + paddedWidth + "x" + paddedHeight);
        System.out.println("PanelRes: " + resultWidth + "x" + resultHeight);
        System.out.println("PanelRatio: " + (float)resultWidth / resultHeight);
    }
}
