package com.myboq.manifest.model;

public class Version {
    private final int major;
    private final int minor;

    public int getPatch() {
        return patch;
    }

    public int getMinor() {
        return minor;
    }

    public int getMajor() {
        return major;
    }

    private final int patch;

    public Version(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public static Version parse(String s) {
        String[] parts = s.split("\\.");
        int major = Integer.parseInt(parts[0]);
        int minor = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
        int patch = parts.length > 2 ? Integer.parseInt(parts[2]) : 0;
        return new Version(major, minor, patch);
    }

    @Override
    public String toString() {
        return major + "." + minor + "." + patch;
    }


}