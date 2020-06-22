import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.junit.jupiter.api.Test;
import storesDB.StorageMongoDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Projections.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Testcontainers
public class StorageMongoDBTest {

    private StorageMongoDB underTest;

    @Container
    public GenericContainer mongodb = new GenericContainer("mongo:4.0").withExposedPorts(27017);

    @BeforeEach
    public void setUp(){
        mongodb.start();
        String address = mongodb.getHost();
        Integer port = mongodb.getFirstMappedPort();
        underTest = new StorageMongoDB(address, port);

    }

    @Test
    public void addStoreTest(){
        underTest.addStore("TESLA");
        String actual = (String) underTest.getStores().find().first().get("name");

        assertEquals( "TESLA", actual);
    }

    @Test
    public void addProductTest(){
        underTest.addProduct("CAR", 10000);
        String actual = (String) Objects.requireNonNull(underTest.getGroceries().find().first()).get("name");

        assertEquals("CAR", actual);
    }

    @Test
    public void putProductInStoreTest(){
        underTest.addStore("TESLA");
        underTest.addProduct("CAR", 10000);
        underTest.putProductInStore("CAR", "TESLA");
        List<String> groceriesList = List.of("CAR");
        Document expected = new Document("name", "TESLA").append("groceries", groceriesList);
        Document actual = Objects.requireNonNull(underTest.getStores().find().projection(fields(excludeId())).first() );

        assertEquals(expected , actual);
    }

}

