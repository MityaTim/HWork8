package HWork8;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static int MAP_SIZE = 3, BUF_MAP_SIZE = 3;
    static int WIN_LENGTH = 3, BUF_WIN_LENGTH = 3;
    static char [][] map;
    static Random random = new Random();
    static JButton[][] buttonArr;// = new JButton[][];
    static Container container;
    static JButton gameResult = new JButton();
    static JButton restartButton = new JButton();
    static JButton sizeLeftBut = new JButton();
    static JButton sizeValueBut = new JButton();
    static JButton sizeRightBut = new JButton();

    static JButton winSizeLeftBut = new JButton();
    static JButton winSizeValueBut = new JButton();
    static JButton winSizeRightBut = new JButton();

    // constants for drawline component
    static int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
    static int bx1 = 0, by1 = 0, bx2 = 0, by2 = 0;
    static boolean draw = false;

    public static void main(String[] args) {
        MyWindow window = new MyWindow();

        window.createWindow();
        window.buttonArrayListen();
        window.restartListen();
        window.changeDimensionsListen();

        Graphics g = window.getGraphics();

        // перерисовываем линию по новым координатам
        while (true){
            if (draw == true){ // draw - это флаг статичный, он меняется внутри window методов
                x1 = bx1;
                x2 = bx2;
                y1 = by1;
                y2 = by2;
                window.paint(g);
                draw = false; // в конце отрисовки меняем назад
            }
        }



    }
}
