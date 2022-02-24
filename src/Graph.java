
public class Graph {
    private int n;
    private int[][] g;

    Graph(int x) {
        this.n = x;
        this.g = new int[n][n];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                g[i][j] = 0;
            }
        }
    }

    public void displayAdjacencyMatrix() {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(" " + g[i][j]);
            }
            System.out.println();
        }
    }

    public void addEdge(int x, int y) {
        if ((x < 0) || (x >= n)) {
            System.out.printf("Vertex " + x
                    + " does not exist!");
        }
        if ((y < 0) || (y >= n)) {
            System.out.printf("Vertex " + y
                    + " does not exist!");
        }

        if (x == y) {
            System.out.println("Same Vertex!");
        }

        else {
            g[y][x] = 1;
            g[x][y] = 1;
        }
    }

    public int getN () {
        return n;
    }

    public void setN (int n) {
        this.n = n;
    }

    public int[][] getG () {
        return g;
    }

    public void setG (int[][] g) {
        this.g = g;
    }
}