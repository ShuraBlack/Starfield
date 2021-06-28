import javax.swing.*;
import java.awt.*;

public class StarfieldFrame extends JFrame {

    public StarfieldFrame() {
        this.add(new ControllPanel());
        this.setTitle("Starfield");
        this.setIconImage(new IconLoader().getImage());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();

        //device.setFullScreenWindow(this);
    }
}
