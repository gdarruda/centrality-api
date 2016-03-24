import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        File file = new File("edges");

        Graph graph = new Graph();

        Gson gson = new Gson();

        try
        {

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine())
            {
                Integer fromNode = scanner.nextInt();
                Integer toNode = scanner.nextInt();
                graph.addEdge(fromNode, toNode, false);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        CentralityCalculator centralityCalculator = new CentralityCalculator(graph);
        ArrayList<CentralityCalculator.CentralityNode> rankedCentrality = centralityCalculator.getRankedCentrality();

        System.out.println(gson.toJson(rankedCentrality));
    }
}
