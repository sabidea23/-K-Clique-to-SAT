import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class kCliqueBKT {

    static void isClique(ArrayList<Integer[]> combinations,
                         Graph graph) {
        for (Integer[] vertex : combinations) {
            int found = 1;

            for (int i = 0; i < vertex.length; i++) {
                for (int j = i + 1; j < vertex.length; j++) {
                    if (graph.getG()[vertex[i] - 1][vertex[j] -1] == 0) {
                        found = 0;
                        break;
                    }
                }
                if (found == 0)
                    break;
            }

            if (found == 1) {
                System.out.println("True");
                return;
            }
        }

        System.out.println("False");
    }


    static void combinationUtil(int arr[], int n, int r, int index,
                                Integer[] data, int i, ArrayList<Integer[]> list) {
        if (index == r) {
            Integer[] combination = new Integer[r];
            System.arraycopy (data, 0, combination, 0, r);
            list.add(combination);
            return;
        }

        if (i >= n)
            return;

        data[index] = arr[i];
        combinationUtil(arr, n, r, index+1, data, i+1, list);

        combinationUtil(arr, n, r, index, data, i+1, list);
    }

    static void putCombination(int[] arr, int n, int r,
                                 ArrayList<Integer[]> list) {
        Integer[] data = new Integer[r];
        combinationUtil(arr, n, r, 0, data, 0, list);
    }

    public static void main (String[] args) {
        File inFile = new File (args[0]);
        BufferedReader br = null;

        String nStr, kStr,mStr;
        int n, k, m;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(inFile));
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

            int []arr = new int[n]; 
            for (int i = 0; i < n; i++)
                arr[i] = i + 1;

            ArrayList<Integer[]> listCombinations = new ArrayList<>();
            putCombination(arr, n, k, listCombinations);

            isClique(listCombinations, obj);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
