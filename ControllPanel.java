import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ControllPanel extends JPanel implements ActionListener {

    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT = 1080;

    private static final int DELAY = 16;
    private final Star[] stars = new Star[1000];

    private static float speed = 15.0f;
    private static int colorPlus = 5;

    Timer timer;

    public ControllPanel () {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addMouseMotionListener(new CustomMouseListener());
        this.addKeyListener(new CustomKeyListener());

        start();
    }

    public void start () {
        timer = new Timer(DELAY,this);
        timer.start();
        for (int i = 0 ; i < stars.length; i++) {
            stars[i] = new Star();
        }
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.translate(SCREEN_WIDTH/2,SCREEN_HEIGHT/2);
        draw(g);
    }

    public void draw (Graphics g) {
        float speedTmp = speed;
        for (int i = 0 ; i < stars.length; i++) {
            stars[i].setSpeed(speedTmp);
            stars[i].setColorPlus(colorPlus);
            stars[i].update();
            Graphics2D g2 = (Graphics2D) g;
            stars[i].show(g2);
        }
    }

    static public float map(float value,
                            float istart,
                            float istop,
                            float ostart,
                            float ostop) {
        return (ostart + (ostop - ostart) * ((value - istart) / (istop - istart)));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public static class CustomMouseListener extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            speed = map(e.getX(), 0, SCREEN_WIDTH, 0, 50);
        }
    }

    public static class CustomKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (colorPlus < 10) {
                    colorPlus++;
                }
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (colorPlus > 0) {
                    colorPlus--;
                }
            }
        }
    }
}
