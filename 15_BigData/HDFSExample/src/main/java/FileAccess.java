import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.*;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileAccess {
    private Configuration configuration;
    private FileSystem hdfs;
    private String rootPath;

    /**
     * Initializes the class, using rootPath as "/" directory
     *
     * @param rootPath - the path to the root of HDFS,
     *                 for example, hdfs://localhost:32771
     */
    public FileAccess(String rootPath) throws URISyntaxException, IOException {
        configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        System.setProperty("HADOOP_USER_NAME", "root");
        this.rootPath = rootPath;
        hdfs = FileSystem.get(new URI(rootPath), configuration);

    }

    /**
     * Creates empty file or directory
     *
     * @param path
     */
    public void create(String path) throws IOException {
        Path pathFile = new Path(rootPath + "/" + path);
        if (hdfs.exists(pathFile)) {
            System.out.printf("Path %s already exists!\n", pathFile);
        } else {
            if (!path.contains(".")) {
                hdfs.mkdirs(pathFile);
            } else {
                hdfs.createNewFile(pathFile);
            }
        }
    }

    /**
     * Appends content to the file
     *
     * @param path
     * @param content
     */
    public void append(String path, String content) throws IOException {
        Path pathFile = new Path(rootPath + "/" + path);
        if (isFileAndExist(pathFile)) {
            try (
                    FSDataOutputStream file = hdfs.append(pathFile);
                    BufferedWriter writer = new BufferedWriter
                            (new OutputStreamWriter(file, "UTF-8"))) {

                writer.write(content);
                writer.flush();
            }

        } else {
            System.out.println("File isn't exist!");
        }
    }

    /**
     * Returns content of the file
     *
     * @param path
     * @return
     */
    public String read(String path) throws IOException {
        StringBuilder result = new StringBuilder();
        Path pathFile = new Path(rootPath + "/" + path);
        if (isFileAndExist(pathFile)) {
            FSDataInputStream open = hdfs.open(pathFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(open));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            open.close();
            reader.close();
        } else {
            result.append("This is not file or this file does not exist");
        }

        return result.toString();
    }

    /**
     * Deletes file or directory
     *
     * @param path
     */
    public void delete(String path) throws IOException {
        Path pathFile = new Path(rootPath + "/" + path);
        if (hdfs.exists(pathFile)) {
            hdfs.delete(pathFile, true);
        } else {
            System.out.println(path + " does not exist");
        }
    }

    /**
     * Checks, is the "path" is directory or file
     *
     * @param path
     * @return
     */
    public boolean isDirectory(String path) throws IOException {
        return hdfs.isDirectory(new Path(rootPath + "/" + path));
    }

    /**
     * Return the list of files and subdirectories on any directory
     *
     * @param path
     * @return
     */
    public List<String> list(String path) throws IOException {
        String prefix = "";
        if (!path.contains(rootPath)) {
            prefix = rootPath + "/";
        }
        Path pathFile = new Path(prefix + path);
        List<String> result = new ArrayList<>();
        FileStatus[] files = hdfs.listStatus(pathFile);

        if (hdfs.exists(pathFile)) {
            for (FileStatus file : files) {
                result.add(file.getPath().getName());
                if (file.isDirectory()) {
                    result.addAll(list(file.getPath().toString()));
                }
            }
        } else {
            System.out.println("Path is not found!");
        }

        return result;
    }

    public boolean isFileAndExist(Path path) throws IOException {
        return (hdfs.exists(path) && hdfs.isFile(path));
    }
}
