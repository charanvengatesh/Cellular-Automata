
import java.awt.*;
import java.util.Scanner;

public class App {

    public static final int RES = 100;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    public static void main(String[] args) throws Exception {

        DrawingPanel panel = new DrawingPanel(WIDTH, HEIGHT);
        Graphics2D g = panel.getGraphics();

        // elementary(126, 50, panel, g);
        cellular2D("345/2/4", 60, panel, g);

    }

    public static void elementary(int rule, int timeMilli, DrawingPanel panel, Graphics2D g) throws Exception {

        int[] originalCells = new int[RES];
        originalCells[RES / 2] = 1;

        Cellular1D cells = new Cellular1D(originalCells, rule);

        for (int j = 0; j < RES; j++) {
            for (int i = 0; i < cells.getCells().length; i++) {
                if (cells.getCells()[i] == 0) {
                    g.setColor(Color.white);
                } else {
                    g.setColor(Color.black);
                }
                g.fillRect(i * (WIDTH / RES), j * (HEIGHT / RES), WIDTH / RES, WIDTH / RES);
            }
            Thread.sleep(timeMilli);

            cells.step();
        }
    }

    public static void cellular2D(String rule, int timeMilli, DrawingPanel panel, Graphics2D g) throws Exception {

        int[][] originalCells = new int[RES][RES];
        originalCells[RES / 2 - 1][RES / 2 - 1] = 1;
        originalCells[RES / 2][RES / 2 - 1] = 1;
        originalCells[RES / 2 + 1][RES / 2] = 1;
        originalCells[RES / 2][RES / 2] = 1;
        originalCells[RES / 2 + 1][RES / 2 + 1] = 1;

        Cellular2D cells = new Cellular2D(RES, RES, rule);

        for (int u = 0; u < 500; u++) {
            for (int j = 0; j < RES; j++) {
                for (int i = 0; i < cells.getCells().length; i++) {
                    // if (cells.getCells()[j][i] == 1) {
                    // g.setColor(new Color(255, 0, 0));
                    // } else if (cells.getCells()[j][i] == 2) {
                    // g.setColor(new Color(255, 63, 0));
                    // } else if (cells.getCells()[j][i] == 3) {
                    // g.setColor(new Color(255, 127, 0));
                    // } else if (cells.getCells()[j][i] == 4) {
                    // g.setColor(new Color(255, 191, 0));
                    // } else if (cells.getCells()[j][i] == 5) {
                    // g.setColor(new Color(255, 255, 0));
                    // } else {
                    // g.setColor(Color.black);
                    // }
                    Color col = Color.black;
                    for (int k = 1; k < (Integer.parseInt(rule.split("/")[2])); k++) {
                        if (k == cells.getCells()[j][i]) {
                            col = new Color(255, (k - 1) * (255 / (Integer.parseInt(rule.split("/")[2]) - 2)), 0);
                        }
                    }

                    g.setColor(col);

                    g.fillRect(i * (WIDTH / RES), j * (HEIGHT / RES), WIDTH / RES, WIDTH / RES);
                }
            }

            Thread.sleep(timeMilli);
            cells.step();
        }
    }

}
