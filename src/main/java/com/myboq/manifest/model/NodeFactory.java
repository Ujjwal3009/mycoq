package com.myboq.manifest.model;

import java.util.List;
import java.util.stream.Collectors;

public class NodeFactory {

    public static Node fromManifest(NodeManifest mf){

        Version v = Version.parse(mf.getVersion());

        List<String> rawDeps;
        if (mf.getType() == NodeType.COMPOSITE) {
            rawDeps = mf.getIncludes();         // ⬅️ composite uses includes
        } else {
            rawDeps = mf.getDependencies();     // ⬅️ others use dependencies
        }

        //Creating List of dependency from manifest and version
        List<Dependency> deps = rawDeps.stream()
                .map(Dependency :: parse)
                .collect(Collectors.toList());


        return new Node(mf.getName() , mf.getType(), v, deps);
    }
}
