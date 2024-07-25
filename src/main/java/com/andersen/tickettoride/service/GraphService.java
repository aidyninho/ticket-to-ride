package com.andersen.tickettoride.service;

import com.andersen.tickettoride.exception.RouteAlreadyExistsException;
import com.andersen.tickettoride.model.City;
import com.andersen.tickettoride.model.Route;
import lombok.extern.slf4j.Slf4j;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GraphService {

    private final SimpleWeightedGraph<City, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

    public void createGraphFromRoutes(List<Route> routes) {
        routes.forEach(this::addRouteToGraph);
        log.info("Graph of size " + routes.size() + " was successfully created.");
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
        if (edge == null) {
            throw new RouteAlreadyExistsException();
        }
        graph.setEdgeWeight(edge, route.getSegments());
        log.info("Route from " + route.getSourceCity() + " to " + route.getDestinationCity() + " was added to the graph.");
    }

    public void updateRouteInGraph(Route route) {
        DefaultWeightedEdge edge = graph.getEdge(route.getSourceCity(), route.getDestinationCity());
        graph.setEdgeWeight(edge, route.getSegments());
        log.info(route.getSourceCity() + " to " + route.getDestinationCity()
                 + " - segments changed from " + graph.getEdgeWeight(edge) + " to " + route.getSegments() + ".");
    }

    public void deleteRouteInGraph(Route route) {
        DefaultWeightedEdge edge = graph.getEdge(route.getSourceCity(), route.getDestinationCity());
        graph.removeEdge(edge);
        log.warn("Route from " + route.getSourceCity() + " to " + route.getDestinationCity() + " was deleted.");
    }

    public boolean isEmpty() {
        return graph.edgeSet().isEmpty();
    }
}
