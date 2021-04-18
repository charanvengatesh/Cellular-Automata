

public class Cellular1D {

  private int iterations;
  private int rule;

  private int[] original;
  private int[] cells;

  public Cellular1D(int size, int rule) {
    this.iterations = 0;
    this.original = new int[size];
    for (int i = 0; i < size; i++) {
      this.original[i] = (int) Math.round(Math.random());
    }
    this.cells = this.original;
    this.rule = rule;
  }

  public Cellular1D(int[] original, int rule) {
    this.iterations = 0;
    this.original = original;
    this.cells = original;
    this.rule = rule;
  }

  private String getBinString(int decimal) throws Exception {

    if (decimal < 256 && decimal > 0) {
      String str = Integer.toBinaryString(decimal);
      str = ("00000000" + str).substring(str.length());
      return str;
    } else {
      throw new Exception("Decimal has to be between 1 and 255");
    }

  }

  public void step() throws Exception {

    iterations++;

    int tempArr[] = new int[this.cells.length];

    for (int i = 0; i < this.cells.length; i++)
      tempArr[i] = this.cells[i];

    String ruleBin = getBinString(rule);

    for (int i = 0; i < tempArr.length; i++) {

      int leftCell = 0;
      int middleCell = 0;
      int rightCell = 0;

      if (i == 0) {
        leftCell = tempArr[tempArr.length - 1];
      } else {
        leftCell = tempArr[i - 1];
      }

      if (i == tempArr.length - 1) {
        rightCell = tempArr[0];
      } else {
        rightCell = tempArr[i + 1];
      }

      middleCell = tempArr[i];

      if (leftCell == 1 && middleCell == 1 && rightCell == 1) {
        this.cells[i] = Integer.parseInt(ruleBin.substring(0, 1));
      } else if (leftCell == 1 && middleCell == 1 && rightCell == 0) {
        this.cells[i] = Integer.parseInt(ruleBin.substring(1, 2));
      } else if (leftCell == 1 && middleCell == 0 && rightCell == 1) {
        this.cells[i] = Integer.parseInt(ruleBin.substring(2, 3));
      } else if (leftCell == 1 && middleCell == 0 && rightCell == 0) {
        this.cells[i] = Integer.parseInt(ruleBin.substring(3, 4));
      } else if (leftCell == 0 && middleCell == 1 && rightCell == 1) {
        this.cells[i] = Integer.parseInt(ruleBin.substring(4, 5));
      } else if (leftCell == 0 && middleCell == 1 && rightCell == 0) {
        this.cells[i] = Integer.parseInt(ruleBin.substring(5, 6));
      } else if (leftCell == 0 && middleCell == 0 && rightCell == 1) {
        this.cells[i] = Integer.parseInt(ruleBin.substring(6, 7));
      } else if (leftCell == 0 && middleCell == 0 && rightCell == 0) {
        this.cells[i] = Integer.parseInt(ruleBin.substring(7, 8));
      }
    }

  }

  @Override
  public String toString() {
    String s = "";

    for (int i = 0; i < this.cells.length; i++) {
      s += this.cells[i];
    }
    return s;
  }

  
  public int[] getCells() {
    return this.cells;
  }

  public int getIteration() {
    return this.iterations;
  }


}
