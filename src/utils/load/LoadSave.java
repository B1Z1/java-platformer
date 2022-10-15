package utils.load;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";

    public static BufferedImage getSpriteAtlas(String fileName) {
        BufferedImage image = null;
        InputStream inputStream = LoadSave.class.getResourceAsStream("/" + fileName);

        try {
            image = ImageIO.read(inputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return image;
    }
}
