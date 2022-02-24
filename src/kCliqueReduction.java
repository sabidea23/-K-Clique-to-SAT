import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class kCliqueReduction {
    public static String addParenthesis(String str) {
        return "(" + str + ")";
    }

    public static String makeLiteral(String index1, String index2) {
        return "x" + index1 + index2;
    }

    public static String makeLiteralNeg(String index1, String index2) {
    return "~x" + index1 + index2;
    }

    public static String isVertex(int n, int k) {
        String exp = "";
        for (int j = 1; j <= k; j++) {
            String clause = "";
            for (int i = 1; i <= n; i++) {
                clause = clause.concat(makeLiteral (String.valueOf(i),
                        String.valueOf(j)));
                if (i < n)
                    clause = clause.concat("V");
            }
            clause = addParenthesis (clause);
            clause = clause.concat("^");
            exp = exp.concat (clause);
        }
        return exp;
    }

    public static String differentVertex(int n, int k) {
        String exp = "";
        for (int i = 1; i <= k; i++) {
            for (int j = i + 1; j <= k; j++) {
                    String clause = "";
                    for (int h = 1; h <= n; h++) {
                        String str = "";
                        str = str.concat (makeLiteralNeg (String.valueOf (h), String.valueOf (i)));
                        str = str.concat ("V");
                        str = str.concat (makeLiteralNeg (String.valueOf (h), String.valueOf (j)));
                        str = addParenthesis (str);
                        str = str.concat ("^");
                        clause = clause.concat (str);
                    }
                    exp = exp.concat (clause);
            }
        }
        return exp;
    }

    public static boolean isEdge(Graph graph, int index1 , int index2) {
        return graph.getG()[index1 - 1][index2 - 1] == 1;
    }

    public static String isClique(int n, int k, Graph graph) {
        String exp = "";
        for (int r = 1; r <= k; r++) {
            for (int s = r + 1; s <= k; s++) {
                String clause1 = "";
                for (int i = 1; i <= n; i++) {
                    String clause2 = "";
                    for (int j = 1; j <= n; j++) {
                        String str = "";
                        if (!isEdge (graph, i, j)) {
                            str = str.concat (makeLiteralNeg (String.valueOf (i), String.valueOf (r)));
                            str = str.concat ("V");
                            str = str.concat (makeLiteralNeg (String.valueOf (j), String.valueOf (s)));
                            str = addParenthesis (str);

                            clause2 = clause2.concat (str);
                            clause2 = clause2.concat ("^");
                        }
                    }
                    clause1 = clause1.concat (clause2);
                }

                exp = exp.concat (clause1);
            }
        }
        return exp;
    }

    public static void main (String[] args) {
        File inFile = new File (args[0]);
        BufferedReader br = null;

        String nStr, kStr,mStr;
        int n, k, m;
        try {
            String sCurrentLine;
            br = new BufferedReader (new FileReader (inFile));
            kStr = br.readLine();
            nStr = br.readLine ();
            mStr = br.readLine ();
            k = Integer.parseInt (kStr);
            n = Integer.parseInt (nStr);
            m = Integer.parseInt (mStr);
            Graph obj = new Graph(n);

            while ((sCurrentLine = br.readLine()) != null) {
                String []nodes = sCurrentLine.split(" ");
                int node1 = Integer.parseInt(nodes[0]);
                int node2 = Integer.parseInt(nodes[1]);
                obj.addEdge(node1 - 1, node2 - 1);
            }

            String res = isVertex(n,k) + differentVertex(n, k) + isClique(n, k, obj);
            StringBuffer str = new StringBuffer(res);
            str.deleteCharAt(str.length()-1);
            System.out.println(str);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
