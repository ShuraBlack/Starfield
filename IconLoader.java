import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class IconLoader {
    private Image image;

    public IconLoader () {
        try {
            BufferedImage icon = ImageIO.read(getClass().getResource("/icon.png"));
            image = icon;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getImage() {
        return image;
    }
}
