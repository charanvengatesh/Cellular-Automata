
public class Cellular2D {

  private int iterations;
  private String rule;

  private int[][] original;
  private int[][] cells;

  public Cellular2D(int width, int height, String rule) {
    this.iterations = 0;
    this.original = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        this.original[i][j] = (int) Math.round(Math.random());
      }
    }
    this.cells = this.original;
    this.rule = rule;
  }

  public Cellular2D(int[][] original, String rule) {
    this.iterations = 0;
    this.original = original;
    this.cells = original;
    this.rule = rule;
  }


  public void step() throws Exception {

    iterations++;

    int tempArr[][] = new int[this.cells.length][this.cells[0].length];

    for (int i = 0; i < this.cells.length; i++) {
      for (int j = 0; j < this.cells[i].length; j++) {
        tempArr[i][j] = this.cells[i][j];
      }
    }

    for (int i = 0; i < tempArr.length; i++) {
      for (int j = 0; j < tempArr[0].length; j++) {
        int state = tempArr[i][j];
        int neighbors = getNeighbors(tempArr, j, i);

        if (state == 0 && check(bornRule(), neighbors)) {
          this.cells[i][j] = 1;
        } else if (state == 1 && check(aliveRule(), neighbors)) {
          this.cells[i][j] = state;
        } else {
          this.cells[i][j] = 0;
        }
      }
    }

  }

  public boolean check(int[] arr, int toCheckValue){
    for (int element : arr) {
      if (element == toCheckValue) {
        return true;
      }
    }
    return false;
  }

  public int getNeighbors(int[][] arr, int y, int x) {
    int sum = 0;
    for (int i = -1; i < 2; i++) {
      for (int j = -1; j < 2; j++) {
        int col = (x + i + arr.length) % arr.length;
        int row = (y + j + arr[0].length) % arr[0].length;
        sum += arr[col][row];
      }
    }
    sum -= arr[x][y];
    return sum;

  }

  @Override
  public String toString() {
    String s = "";

    for (int i = 0; i < this.cells.length; i++) {
      for (int j = 0; j < this.cells[i].length; j++) {
        s += this.cells[i][j];
      }
      s += "\n";
    }
    return s;
  }

  public int[][] getCells() {
    return this.cells;
  }

  public int getIteration() {
    return this.iterations;
  }

  public int[] aliveRule(){
    
    String[] alive = rule.split("/");

    int[] intArray = new int[alive[0].length()];

    for (int i = 0; i < alive[0].length(); i++) {
      intArray[i] = Character.digit(alive[0].charAt(i), 10);
    }

    return intArray;
  }

  public int[] bornRule() {

    String[] alive = rule.split("/");

    int[] intArray = new int[alive[1].length()];

    for (int i = 0; i < alive[1].length(); i++) {
      intArray[i] = Character.digit(alive[1].charAt(i), 10);
    }

    return intArray;
  }

}
