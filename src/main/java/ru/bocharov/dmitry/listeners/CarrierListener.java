package ru.bocharov.dmitry.listeners;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import org.apache.commons.io.FilenameUtils;
import ru.bocharov.dmitry.editor.ManageFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class CarrierListener extends AbstractListener {


    public CarrierListener(ManageFolder manageFolder) {
        super(manageFolder);

    }

    private boolean checkXml(Path folder) {
        File file = new File(String.valueOf(folder));
        int counterXml = 0;
        try {
            DirectoryStream<Path> dirstr = Files.newDirectoryStream(Paths.get(String.valueOf(folder)));

            if (file.isDirectory()) {
                for (File myFile : file.listFiles()) {
                    if (myFile.isFile()) {
                        System.out.println(myFile);
                    }
                }
            }

            for (Path entry : dirstr
            ) {

                BasicFileAttributes attributes = Files.readAttributes(entry, BasicFileAttributes.class);

                if (!attributes.isDirectory() && FilenameUtils.getExtension(entry.toString()).equals("xml")) {
                    counterXml++;
                    logger.info("xml: " + entry);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        logger.info("quantity xml: " + counterXml);
        if (counterXml != 0) return true;

        return false;
    }

    @Override
    public void update(final Path folder) throws IOException {
        if (checkXml(folder)) {

            try {
                CamelContext context = new DefaultCamelContext();
                context.addRoutes(new RouteBuilder() {
                    public void configure() {
                        from("file:" + folder + "?delete=true&?include=.*.xml")
                                .to("file:" + folderOut);
                    }
                });
                context.start();
                Thread.sleep(1000);
                context.stop();
                logger.info("xml moved");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }
}

