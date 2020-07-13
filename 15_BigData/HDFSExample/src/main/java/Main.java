import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;

public class Main
{
    private static String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String HADOOP_CONTAINER_ID = "400537a7128a";
    private static final String HADOOP_PATH = "hdfs://" + HADOOP_CONTAINER_ID + ":8020/";

    public static void main(String[] args) throws Exception
    {
        String fileName = "testFile.txt";
        String dirName = "test";
        FileAccess fileAccess = new FileAccess(HADOOP_PATH);
        fileAccess.create(fileName);
        fileAccess.append(fileName, "Hello my dear friend!");
        System.out.println(fileAccess.read(fileName));
        fileAccess.delete(fileName);
        System.out.println(fileAccess.list(dirName));

//        Configuration configuration = new Configuration();
//        configuration.set("dfs.client.use.datanode.hostname", "true");
//        System.setProperty("HADOOP_USER_NAME", "root");
//
//        FileSystem hdfs = FileSystem.get(
//            new URI("hdfs://400537a7128a:8020"), configuration
//        );
//
//        Path file = new Path("hdfs://400537a7128a:8020/test/file.txt");
//
//        if (hdfs.exists(file)) {
//            hdfs.delete(file, true);
//        }
//
//        OutputStream os = hdfs.create(file);
//        BufferedWriter br = new BufferedWriter(
//            new OutputStreamWriter(os, "UTF-8")
//        );
//
//        for(int i = 0; i < 10_000_000; i++) {
//            br.write(getRandomWord() + " ");
//        }
//
//        br.flush();
//        br.close();
//        hdfs.close();
    }

    private static String getRandomWord()
    {
        StringBuilder builder = new StringBuilder();
        int length = 2 + (int) Math.round(10 * Math.random());
        int symbolsCount = symbols.length();
        for(int i = 0; i < length; i++) {
            builder.append(symbols.charAt((int) (symbolsCount * Math.random())));
        }
        return builder.toString();
    }
}
