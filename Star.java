import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.concurrent.ThreadLocalRandom;

public class Star {
    private float x;
    private float y;
    private float z;

    private int red;
    private int green;
    private int blue;

    private float speed = 15.0f;
    private int colorPlus = 5;

    private float pz;

    public Star() {
        x = ThreadLocalRandom.current().nextInt(-ControllPanel.SCREEN_WIDTH/2,ControllPanel.SCREEN_WIDTH/2);
        y = ThreadLocalRandom.current().nextInt(-ControllPanel.SCREEN_HEIGHT/2,ControllPanel.SCREEN_HEIGHT/2);
        z = ThreadLocalRandom.current().nextInt(ControllPanel.SCREEN_WIDTH);

        pz = z;
        red = 0;
        green = 0;
        blue = 0;
    }

    public void update () {
        z = z - speed;
        if (z < 1) {
            z = ThreadLocalRandom.current().nextInt(ControllPanel.SCREEN_WIDTH);
            x = ThreadLocalRandom.current().nextInt(-ControllPanel.SCREEN_WIDTH/2,ControllPanel.SCREEN_WIDTH/2);
            y = ThreadLocalRandom.current().nextInt(-ControllPanel.SCREEN_HEIGHT/2,ControllPanel.SCREEN_HEIGHT/2);

            pz = z;
            red = 0;
            green = 0;
            blue = 0;
        }
    }

    public void show (Graphics2D g) {
        float sx = map(x/z, 0,1,0,ControllPanel.SCREEN_WIDTH);
        float sy = map(y/z, 0,1,0,ControllPanel.SCREEN_HEIGHT);

        float r = map(z, 0, ControllPanel.SCREEN_WIDTH/2, 8, 0);

        recolor();
        g.setColor(new Color(red,green,blue));

        Ellipse2D circle = new Ellipse2D.Float();
        circle.setFrame(sx,sy,r,r);
        //g.fill(circle);

        float px = map(x / pz, 0, 1, 0, ControllPanel.SCREEN_WIDTH/2);
        float py = map(y / pz, 0, 1, 0, ControllPanel.SCREEN_HEIGHT/2);


        Line2D line = new Line2D.Float(px*1.5f,py*1.5f,sx+(r/2),sy+(r/2));

        g.draw(line);
        pz = z;
    }

    private void recolor () {
        if (red <= 240) {
            red = red + colorPlus;
            green = green + colorPlus;
            blue = blue + colorPlus;
        }
    }

    static public float map(float value,
                            float istart,
                            float istop,
                            float ostart,
                            float ostop) {
        return (ostart + (ostop - ostart) * ((value - istart) / (istop - istart)));
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setColorPlus(int colorPlus) {
        this.colorPlus = colorPlus;
    }
}
