package com.andersen.tickettoride.service;

import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.model.Route;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.springframework.stereotype.Service;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.List;

@Service
public class GraphService {

    private final SimpleWeightedGraph<City, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

    private void createGraphFromRoutes(List<Route> routes) {
        routes.forEach(route -> {
            graph.addVertex(route.getSourceCity());
            graph.addVertex(route.getDestinationCity());
            DefaultWeightedEdge edge = graph.addEdge(route.getSourceCity(), route.getDestinationCity());
            graph.setEdgeWeight(edge, route.getSegments());
        });
    }

    public double findShortestPathBetweenCities(City sourceCity, City destinationCity) {
        DijkstraShortestPath<City, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(graph);

        GraphPath<City, DefaultWeightedEdge> path = dijkstraShortestPath.getPath(sourceCity, destinationCity);
        return path.getWeight();
    }
}
