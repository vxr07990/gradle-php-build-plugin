package org.swissphpfriends.gradle.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * StreamGobbler helper class to output a ProcessBuilder stream to srdout/stderr
 */
final public class StreamGobbler extends Thread
{
    InputStream inputStream;

    String type;

    public StreamGobbler(InputStream inputStream, String type)
    {
        this.inputStream = inputStream;
        this.type = type;
    }

    @Override
    public void run()
    {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line = null;
            while ((line = br.readLine()) != null)
                System.out.println(type + "> " + line);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}