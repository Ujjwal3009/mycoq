package com.myboq.manifest.model;

import java.util.List;

/**
 * Node sstructure for
 * name: auth-core
 * type: SHARED
 * version: 1.0.0
 * dependencies: []
 */
public class Node {
    private  final String name;
    private final NodeType type;

    public Version getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public NodeType getType() {
        return type;
    }

    public List<Dependency> getDependencyList() {
        return dependencyList;
    }

    private final Version version;
    private final List<Dependency> dependencyList;

    public Node(String _name , NodeType _type , Version _version , List<Dependency> _dependency){
        this.name = _name;
        this.type = _type;
        this.version = _version;
        this.dependencyList = _dependency;
    }

}
