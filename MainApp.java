/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.projekthd;

import java.lang.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import pl.polsl.projekthd.*;
/**
 *
 * @author patam
 */
public class MainApp {

    public static void main(String[] args) {
        Model m = ModelFactory.createDefaultModel();
        try {
            // read csv:
            ClassLoader cl = MainApp.class.getClassLoader();
            System.out.println(cl.getResourceAsStream("Example.csv"));
            InputStream is = MainApp.class.getResourceAsStream("Example.csv");
            if (is != null) {
                RDFDataMgr.read(m, is, "http://example.org", Lang.CSV);
                m.write(System.out, "ttl");
                // save as XML:
                Path p = Files.createTempFile("temp-", ".xml");
                System.out.println("Save to " + p);
                OutputStream out = Files.newOutputStream(p);
                RDFDataMgr.write(out, m, Lang.RDFXML);
                // print file to console:
                Files.lines(p).forEach(System.out::println);
            } else {
                System.out.println("Stream is empty!");
            }
        } catch (IOException e) {
        }
    }
}
