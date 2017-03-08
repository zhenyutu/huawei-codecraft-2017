package com.cacheserverdeploy.deploy;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by tuzhenyu on 17-3-8.
 * @author tuzhenyu
 */
public class FIleInput {

    private static final String CHARSET_NAME = "UTF-8";
    private Scanner scanner;

    public FIleInput(File file){

        if (file == null) throw new NullPointerException("argument is null");
        try {
            scanner = new Scanner(file, CHARSET_NAME);
            scanner.useDelimiter(System.getProperty("line.separator"));
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + file);
        }
    }

}
