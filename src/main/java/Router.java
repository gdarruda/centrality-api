import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import spark.Response;

import java.util.ArrayList;

import static spark.Spark.*;

public class Router {

    public static void main(String[] args) {

        get("/hello", (req, res) -> "Hello World");

        post("/add-edge", (request, response) -> {

            Gson gson = new Gson();
            Graph graph = Graph.getGraph();

            ExternalEdge edge = gson.fromJson(request.body(), ExternalEdge.class);
            graph.addEdge(edge.getFromNode(), edge.getToNode(), false);

            JsonObject responseBody = new JsonObject();
            responseBody.add("error", formatJSONError(0,""));

            response = responseOk(response);
            response.body(gson.toJson(responseBody));

            return response.body();

        });

        get("/node-closeness/:node", (request, response) -> {

            Integer node = Integer.parseInt(request.params(":node")) ;

            Graph graph = Graph.getGraph();
            JsonObject responseBody = new JsonObject();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            if (graph.containsKey(node))
            {
                Float closeness = graph.getCentrality(node);

                responseBody.add("error", formatJSONError(0,""));
                responseBody.addProperty("closeness", closeness);
            }
            else
            {
                responseBody.add("error", formatJSONError(1,"Invalid Edge"));
            }

            response = responseOk(response);
            response.body(gson.toJson(responseBody));

            return response.body();

        });

        get ("/node-closeness", (request, response) -> {

            Gson gson = new Gson();

            CentralityCalculator centralityCalculator = new CentralityCalculator(Graph.getGraph());
            ArrayList<CentralityCalculator.CentralityNode> rankedCentrality = centralityCalculator.getRankedCentrality();

            response = responseOk(response);
            response.body(gson.toJson(rankedCentrality));

            return  response.body();
        });
    }

    private static JsonObject formatJSONError(Integer errorCode, String errorMsg) {

        JsonObject error = new JsonObject();
        error.addProperty("code", errorCode);
        error.addProperty("message", errorMsg);

        return error;
    }

    private static Response responseOk(Response response) {

        response.status(200);
        response.type("application/json");

        return response;
    }
}