package com.andersen.tickettoride.service;

import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.model.Route;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraphService {

    private final SimpleWeightedGraph<City, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

    public void createGraphFromRoutes(List<Route> routes) {
        routes.forEach(this::addRouteToGraph);
    }

    public double findShortestPathBetweenCities(City sourceCity, City destinationCity) {
        DijkstraShortestPath<City, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(graph);

        GraphPath<City, DefaultWeightedEdge> path = dijkstraShortestPath.getPath(sourceCity, destinationCity);
        return path.getWeight();
    }

    public void addRouteToGraph(Route route) {
        graph.addVertex(route.getSourceCity());
        graph.addVertex(route.getDestinationCity());
        DefaultWeightedEdge edge = graph.addEdge(route.getSourceCity(), route.getDestinationCity());
        graph.setEdgeWeight(edge, route.getSegments());
    }

    public void updateRouteInGraph(Route route) {
        DefaultWeightedEdge edge = graph.getEdge(route.getSourceCity(), route.getDestinationCity());
        graph.setEdgeWeight(edge, route.getSegments());
    }

    public void deleteRouteInGraph(Route route) {
        DefaultWeightedEdge edge = graph.getEdge(route.getSourceCity(), route.getDestinationCity());
        graph.removeEdge(edge);
    }

    public boolean isEmpty() {
        return graph.edgeSet().isEmpty();
    }
}
