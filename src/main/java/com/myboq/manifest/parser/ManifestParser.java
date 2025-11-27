package com.myboq.manifest.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.myboq.manifest.model.NodeManifest;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class ManifestParser {

    private final ObjectMapper mapper;
    public ManifestParser() {
        this.mapper = new ObjectMapper(new YAMLFactory());

    }

    public NodeManifest parse(Path path){
        try{
            NodeManifest mf = mapper.readValue(path.toFile() , NodeManifest.class);
            normalizeAndValidate(mf);
            return mf;
        }
        catch (IOException e){
            throw new RuntimeException("Failed to parse manifest" + path , e );
        }

    }

    private void normalizeAndValidate(NodeManifest mf) {
        if (mf.getName() == null || mf.getName().isBlank()) {
            throw new IllegalArgumentException("Manifest missing 'name'");
        }
        if (mf.getType() == null) {
            throw new IllegalArgumentException("Manifest " + mf.getName() + " missing 'type'");
        }
        if (mf.getVersion() == null || mf.getVersion().isBlank()) {
            mf.setVersion("1.0.0"); // default
        }
        if (mf.getDependencies() == null) {
            mf.setDependencies(new ArrayList<>());
        }
        if (mf.getIncludes() == null) {
            mf.setIncludes(new ArrayList<>());
        }

    }
}
