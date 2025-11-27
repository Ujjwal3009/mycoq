package com.myboq;

import com.myboq.manifest.graph.DependencyGraph;
import com.myboq.manifest.model.Dependency;
import com.myboq.manifest.model.Node;
import com.myboq.manifest.model.NodeFactory;
import com.myboq.manifest.parser.ManifestParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        Path manifestDir = Path.of("src/main/resources/manifests");

        ManifestParser parser = new ManifestParser();

        List<Node> nodes = null;
        try {
            nodes = Files.list(manifestDir)
                    .filter(p -> p.toString().endsWith(".yaml"))
                    .sorted()
                    .map(parser:: parse)
                    .map(NodeFactory::fromManifest)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DependencyGraph graph = new DependencyGraph();
        for(Node n : nodes) graph.addNode(n);
        for(Node n : nodes ) graph.addEdgesFor(n);

        List<String> order = graph.topologicalOrder();
        System.out.println("Build/Run order: " + order);





        }
    }
