package net.amg.larder.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class ResourceFromClasspath {
    public static String contentsOf(String filename) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(fileFor(filename))) {
            return scanner.useDelimiter("\\A").next();
        }
    }

    public static File fileFor(String filename) {
        URL resource = ResourceFromClasspath.class.getClassLoader().getResource(filename);
        if (resource == null) {
            throw new RuntimeException("Could not find file: " + filename);
        }
        return new File(resource.getFile());
    }
}