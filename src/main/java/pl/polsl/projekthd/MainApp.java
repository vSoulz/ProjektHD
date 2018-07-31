/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.projekthd;

import java.io.File;
import java.io.FileInputStream;
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
            File fs = new File("src/main/resources/Example.csv");
            InputStream is = new FileInputStream(fs);
            if (is != null) {
                RDFDataMgr.read(m, is, "http://example.org", Lang.CSV);
                m.write(System.out, "ttl");
                String path = new String("."+File.separator+"output.xml");
                System.out.println("Save to " + path);
                File outputFile = new File(path);
                OutputStream out = Files.newOutputStream(outputFile.toPath());
                RDFDataMgr.write(out, m, Lang.RDFXML);
                Files.lines(outputFile.toPath()).forEach(System.out::println);
            } else {
                System.out.println("Stream is empty!");
            }
        } catch (IOException e) {
        }
    }
}
