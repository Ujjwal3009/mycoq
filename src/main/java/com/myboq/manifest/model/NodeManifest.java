package com.myboq.manifest.model;

import java.util.List;

public class NodeManifest {
    private String name;
    private NodeType type;

    public List<String> getIncludes() {
        return includes;
    }

    public void setIncludes(List<String> includes) {
        this.includes = includes;
    }

    private List<String> includes;

    public void setVersion(String version) {
        this.version = version;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    private String version;
    private List<String> dependencies;

    public String getVersion() {
        return version;
    }

    public NodeType getType() {
        return type;
    }

    public List<String> getDependencies() {
        return dependencies;
    }




    public String getName() {
        return name;
    }
}
