import graph.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;


/**
 * @author T.Whiter
 * @Date 2020/2/17 9:22
 * @Version 1.0
 */
public class Testdemo implements Serializable {
    public static void main(String[] args) throws IOException {
        graphTest();

    }

    public static void BitTreeTest() throws IOException {
        BitTree <Integer> integerBitTree = new BitTree<>();


        FileOutputStream fileOutputStream = new FileOutputStream("r.txt");
        PrintStream printStream = new PrintStream(fileOutputStream);

        System.setOut(printStream);
        ArrayList<Integer> integers = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            integers.add(i);
        }
        integerBitTree.buildTree(integers);

        ArrayList<Vertex<Integer>> DFSList = integerBitTree.dfs();
        ArrayList<Vertex<Integer>> BFSList = integerBitTree.bfs();
        ArrayList<TreeVertex<Integer>> PreList = integerBitTree.preOrderVisit();
        ArrayList<TreeVertex<Integer>> InOList = integerBitTree.inOrderVisit();
        ArrayList<TreeVertex<Integer>> PostList = integerBitTree.postOrderVisit();



        System.out.println("DFS----------------------------------------------");
        for (var i:DFSList)
            System.out.println(i.getLabel());

        System.out.println("BFS----------------------------------------------");
        for (var i:BFSList)
            System.out.println(i.getLabel());

        System.out.println("Pre----------------------------------------------");
        for (var i:PreList)
            System.out.println(i.getLabel());

        System.out.println("In----------------------------------------------");
        for (var i:InOList)
            System.out.println(i.getLabel());

        System.out.println("Post----------------------------------------------");
        for (var i:PostList)
            System.out.println(i.getLabel());

        printStream.close();
        fileOutputStream.close();
        System.out.close();
    }

    public static void graphTest() throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("r.txt");
        PrintStream printStream = new PrintStream(fileOutputStream);

        System.setOut(printStream);


        ArrayList<Edge<Integer>> edgeArrayList = new ArrayList<>();

        edgeArrayList.add(new Edge<>(1,2,1));
        edgeArrayList.add(new Edge<>(3,4,1));
        edgeArrayList.add(new Edge<>(4,2,1));
//        edgeArrayList.add(new Edge<>(4,5,1));
//        edgeArrayList.add(new Edge<>(5,6,1));


        Graph<Integer> graph = new Graph<>(edgeArrayList);


        System.out.println("BFS------------------------");

        for (var e : graph.bfs())
            System.out.println(e.getLabel());

        System.out.println("DFS-------------------------");
        for (var e : graph.dfs())
            System.out.println(e.getLabel());

        System.out.println("shortDis----------------");
        for (var e:graph.getShortDis())
            System.out.println(e.getFromLabel() + " " + e.getToLabel() + " " + e.getWeight());
    }
}
