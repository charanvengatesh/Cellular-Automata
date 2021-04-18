
import java.awt.*;

public class App {

    public static final int RES = 500;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    public static void main(String[] args) throws Exception {

        DrawingPanel panel = new DrawingPanel(WIDTH, HEIGHT);
        Graphics2D g = panel.getGraphics();

        // elementary(126, 50, panel, g);
        cellular2D("23/3", 75, panel, g);
        

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

        Cellular2D cells = new Cellular2D(RES, RES, rule);

        for (int u = 0; u < 1000; u++) {
            for (int j = 0; j < RES; j++) {
                for (int i = 0; i < cells.getCells().length; i++) {
                    if (cells.getCells()[j][i] == 0) {
                        g.setColor(Color.white);
                    } else {
                        g.setColor(Color.black);
                    }
                    g.fillRect(i * (WIDTH / RES), j * (HEIGHT / RES), WIDTH / RES, WIDTH / RES);
                }
            }

            Thread.sleep(timeMilli);
            cells.step();
        }
    }


}
