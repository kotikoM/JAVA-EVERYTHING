import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuffixingApp {


    private static final Logger logger = Logger.getLogger(SuffixingApp.class.getName());


    private static Properties loadConfig(String configFile) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(configFile);
        properties.load(inputStream);

        return properties;
    }


    private static boolean isValidMode(String mode) {
        return mode.equalsIgnoreCase("copy") ||
                mode.equalsIgnoreCase("move") ||
                mode.equalsIgnoreCase("suffix");
    }


    public static void main(String[] args) {

        //check if array is empty or null
        if (args == null || args.length < 1) {
            logger.log(Level.SEVERE, "NO CONFIG FILE");
            return;
        }

        String configFile = args[0];

        try {

            Properties config = loadConfig(configFile);

            String mode = config.getProperty("mode");
            String suffix = config.getProperty("suffix");
            String files = config.getProperty("files");


            //check mode for null or non-valid
            if (mode == null || !isValidMode(mode)) {
                logger.log(Level.SEVERE, "Mode is not recognized: " + mode);
                return;
            }


            //check suffix
            if (suffix == null) {
                logger.log(Level.SEVERE, "No suffix is configured");
                return;
            }


            //check files for null or empty
            if (files == null || files.isEmpty()) {
                logger.log(Level.WARNING, "No files are configured to be copied/moved");
                return;
            }



            String[] filePaths = files.split(":");
            for (String filePath : filePaths) {
                File file = new File(filePath);
                //check files existence
                if (!file.exists()) {
                    logger.log(Level.SEVERE, "No such file: " + filePath.replace("\\", "/"));
                    continue;
                }



                if (mode.equalsIgnoreCase("copy")) {
                    copyFile(file, suffix);
                } else if (mode.equalsIgnoreCase("move")) {
                    moveFile(file, suffix);
                } else if (mode.equalsIgnoreCase("suffix")) {
                    addSuffix(file, suffix);
                }


            }

        }
        catch (Exception e ){
            logger.log(Level.WARNING, e.getMessage());
        }

    }


    private static void copyFile(File file, String suffix) throws IOException {
        String newFileName = addSuffixToFileName(file.getName(), suffix);
        Path destinationPath = file.toPath().resolveSibling(newFileName);
        Files.copy(file.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

        logger.log(Level.INFO, file.getPath().replace("\\", "/") + " -> " +
                destinationPath.toString().replace("\\", "/"));
    }


    private static void moveFile(File file, String suffix) throws IOException {
        String newFileName = addSuffixToFileName(file.getName(), suffix);
        Path destinationPath = file.toPath().resolveSibling(newFileName);
        Files.move(file.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

        logger.log(Level.INFO, file.getPath().replace("\\", "/") + " => " +
                destinationPath.toString().replace("\\", "/"));
    }


    private static void addSuffix(File file, String suffix) throws IOException {
        String newFileName = addSuffixToFileName(file.getName(), suffix);
        Path destinationPath = file.toPath().resolveSibling(newFileName);
        Files.move(file.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

        logger.log(Level.INFO, file.getPath().replace("\\", "/") + " => " +
                destinationPath.toString().replace("\\", "/"));
    }


    private static String addSuffixToFileName(String fileName, String suffix) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1) {
            String name = fileName.substring(0, dotIndex);
            String extension = fileName.substring(dotIndex);
            return name + suffix + extension;
        }

        return fileName + suffix;
    }
}
