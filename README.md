# Centrality calculator

This tools calculates [closeness centrality](https://en.wikipedia.org/wiki/Centrality#Closeness_centrality) for unweighted non-oriented graphs using a simple [BFS](https://en.wikipedia.org/wiki/Breadth-first_search) algorithm.

# REST api

Three services are available for this tool:

- add-edge: POST service to add a new edge connecting two edges, the input JSON have two parameters

```
{
    "fromNode": "{{fromNode}}",
    "toNode": "{{toNode}}"
}
```

- /node-closeness/:node: GET service to retrieve closeness centrality of a specific node (:node)

Response example:

```
{
  "error": {
    "code": 0,
    "message": ""
  },
  "closeness": 0.0056179776
}
```
- /node-closeness/: GET service to retrieve an ordered list of edges by closeness centrality

Response example:

```
[
  {
    "node": 6,
    "centrality": 0.09090909
  },
  {
    "node": 1,
    "centrality": 0.11111111
  },
  {
    "node": 2,
    "centrality": 0.125
  },
  {
    "node": 3,
    "centrality": 0.125
  },
  {
    "node": 4,
    "centrality": 0.14285715
  },
  {
    "node": 5,
    "centrality": 0.14285715
  }
]
```

# Offline run

The Main class shows an example to run the algorithm using an input text file (edges) and calculate closeness centrality for all nodes. 
