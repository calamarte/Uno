package uno.views.GUI;

import uno.Carta;
import uno.players.Player;
import uno.views.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GUI implements View {

    private JFrame window;

    public GUI(){
        setUpWindow();
    }

    @Override
    public void start() {

    }

    @Override
    public void setUpPlayers() {

    }

    @Override
    public void stop() {

    }

    @Override
    public Carta choose(Player player, Carta stackCard, Carta.Color gameColor) {
//        try {
//
//            window.add(new JLabel(new ImageIcon(picture)));
//            window.revalidate();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return null;
    }

    @Override
    public Carta.Color chooseColor(Player player) {
        return null;
    }

    @Override
    public void rob(Player target, Carta carta) {

    }

    @Override
    public void rob(Player target, List<Carta> cartas) {

    }

    @Override
    public void jump(Player target) {

    }

    @Override
    public void changeDirection(Player who) {

    }

    @Override
    public void win(Player winner) {

    }


    private void reset(){

    }

    private void setUpWindow(){
        window = new JFrame("UNO");
        window.setLayout(new FlowLayout());
        window.setSize(300, 300);
        window.setVisible(true);
    }
}
