/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode2016.io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Goncalo
 */
public class Writer {

    public static void write(ArrayList<String> commands) {
        try (PrintWriter writer = new PrintWriter("output.txt", "UTF-8")) {
            commands.stream().forEach((command) -> {
                writer.println(command);
            });
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
