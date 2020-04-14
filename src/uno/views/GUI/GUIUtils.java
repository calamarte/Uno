package uno.views.GUI;

import uno.Carta;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUIUtils {


    public static JLabel getGUICard(Carta carta){
        try {
            BufferedImage picture = ImageIO.read(new File("resources/x4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
