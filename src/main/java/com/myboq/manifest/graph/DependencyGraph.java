package com.myboq.manifest.graph;

import com.myboq.manifest.model.Dependency;
import com.myboq.manifest.model.Node;

import java.util.*;

public class DependencyGraph {

    private final Map<String , Node> nodes = new LinkedHashMap<>();



    private final Map<String , List<String>> edges = new LinkedHashMap<>();

    public void addNode(Node node){
        nodes.put(node.getName(), node);
        edges.putIfAbsent(node.getName() , new ArrayList<>());
    }

    public void validateDependenciesExist() {
        for (Node node : nodes.values()) {
            for (Dependency dep : node.getDependencyList()) {
                if (!nodes.containsKey(dep.getName())) {
                    throw new IllegalStateException(
                            "Missing manifest for dependency '" + dep.getName() +
                                    "' required by node '" + node.getName() + "'"
                    );
                }
            }
        }
    }

    public void addEdgesFor(Node node) {
        for (Dependency d : node.getDependencyList()) {

            // ensure dependent node exists in graph
            edges.putIfAbsent(d.getName(), new ArrayList<>());

            // correct direction: dependency → node
            edges.get(d.getName()).add(node.getName());
        }
    }


    public List<String> topologicalOrder() {
        // calculate indegree
        validateDependenciesExist();
        Map<String, Integer> indegree = new HashMap<>();
        for (String node : edges.keySet()) {
            indegree.put(node, 0);
        }

        for (var entry : edges.entrySet()) {
            for (String dep : entry.getValue()) {
                indegree.put(dep, indegree.getOrDefault(dep, 0) + 1);
            }
        }

        //Lexigographioic order
        Queue<String> q = new PriorityQueue<>();
        for (var e : indegree.entrySet()) {
            if (e.getValue() == 0) {
                q.add(e.getKey());
            }
        }

        List<String> order = new ArrayList<>();
        while (!q.isEmpty()) {
            String n = q.poll();
            order.add(n);

            for (String dep : edges.getOrDefault(n, List.of())) {
                indegree.put(dep, indegree.get(dep) - 1);
                if (indegree.get(dep) == 0) {
                    q.add(dep);
                }
            }
        }

        // if we didn't process all nodes, there is a cycle
        if (order.size() != nodes.size()) {
            throw new IllegalStateException("Cycle detected in dependency graph");
        }

        return order;
    }


}
