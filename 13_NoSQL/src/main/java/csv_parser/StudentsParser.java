package csv_parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

public class StudentsParser {

    public static void main(String[] args) {
        String csvFile = "src/main/resources/mongo.csv";
        String line = "";

        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");

        MongoCollection<Document> mongoCollection = database.getCollection("Students");
        mongoCollection.drop();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                Pattern PATTERN = Pattern.compile("(\\w+\\s\\w+),(\\d+),([\"\\w*,?]*)");
                Matcher matcher = PATTERN.matcher(line);
                if (matcher.find()) {
                    Document studentDocument = new Document();
                    studentDocument.append("name", matcher.group(1));
                    studentDocument.append("age", Integer.parseInt(matcher.group(2)));
                    studentDocument.append("courses", matcher.group(3));
                    mongoCollection.insertOne(studentDocument);
                }
            }
            
            System.out.printf("Total count of students: %s \n", mongoCollection.countDocuments());

            System.out.printf("%s students grater then 40 years old \n",
                    mongoCollection.countDocuments(Filters.gt("age", 40)));

            System.out.printf("Youngest student is %s \n",
                    mongoCollection.find().sort(Sorts.ascending("age")).first().get("name"));

            System.out.printf("Oldest student %s, his courses %s \n",
                    mongoCollection.find().sort(Sorts.descending("age")).first().get("name"),
                    mongoCollection.find().sort(Sorts.descending("age")).first().get("courses"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
