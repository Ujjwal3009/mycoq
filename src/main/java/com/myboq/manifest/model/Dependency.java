package com.myboq.manifest.model;

public class Dependency {
    private final String name;

    public Version getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    private final Version version;

    public Dependency(String name, Version version) {
        this.name = name;
        this.version = version;
    }

    public static Dependency parse(String raw) {
        String[] parts = raw.split("@");
        String name = parts[0];
        Version version = Version.parse(parts[1]);
        return new Dependency(name, version);
    }

    // getters
}