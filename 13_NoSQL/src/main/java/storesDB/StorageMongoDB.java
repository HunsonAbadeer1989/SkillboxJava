package storesDB;


import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;

import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.BsonField;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StorageMongoDB {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> groceries;
    private MongoCollection<Document> stores;
    private Logger loggerInfo;
    private Logger loggerError;
    private static Marker CORRECT_INPUT = MarkerManager.getMarker("INPUT_CORRECT");
    private static Marker WRONG_INPUT = MarkerManager.getMarker("WRONG_INPUT");

    public StorageMongoDB(String address, Integer port) {
        this.mongoClient = MongoClients.create("mongodb://" + address + ":" + port);
        this.mongoDatabase = mongoClient.getDatabase("groceryStores");
        this.groceries = mongoDatabase.getCollection("products");
        this.stores = mongoDatabase.getCollection("stores");
        groceries.drop();
        stores.drop();
        loggerInfo = LogManager.getLogger("InfoLog");
        loggerError = LogManager.getLogger("ErrorLog");
    }

    public MongoCollection<Document> getGroceries() {
        return groceries;
    }

    public MongoCollection<Document> getStores() {
        return stores;
    }

    public boolean isExist(MongoCollection<Document> collection, Object value) {
        FindIterable<Document> doc = collection.find(new Document("name", value));
        return doc.first() != null;
    }

    public void addStore(String storeName) {
        if (isExist(stores, storeName)) {
            loggerError.error(WRONG_INPUT, "Store " + storeName + " is already exist! \n");
        } else {
            stores.insertOne(new Document("name", storeName).append("groceries", new ArrayList<>()));
            loggerInfo.info(CORRECT_INPUT, "Store " + storeName + " was added \n");
        }
    }

    public void addProduct(String productName, int price) {
        if (isExist(groceries, productName)) {
            loggerError.error(WRONG_INPUT, "Product " + productName + " is already exist! \n");

        } else {
            groceries.insertOne(new Document("name", productName).append("price", price));
            loggerInfo.info(CORRECT_INPUT, "Product " + productName + " was added \n");
        }
    }

    public void putProductInStore(String productName, String storeName) {
        if (isExist(stores, storeName) && isExist(groceries, productName)) {
            stores.updateOne(eq("name", storeName),
                    new Document("$addToSet", new Document("groceries", productName)));
            loggerInfo.info(CORRECT_INPUT, "Product " + productName + " was add to store " + storeName + "\n");

        } else {
            System.out.printf("Product %s or store %s isn't exist! \n", productName, storeName);
            loggerError.error(WRONG_INPUT, "Product " + productName + " or store " + storeName + " isn't exist! \n");
        }
    }

    public void getProductsStatistic() {
        try {
            long productsCount = groceries.countDocuments();
            Double averagePrice = groceries.aggregate(Arrays.asList(Aggregates.group("_id",
                    new BsonField("averagePrice",
                            new BsonDocument("$avg",
                                    new BsonString("$price"))))))
                    .first().getDouble("averagePrice");
            String lowerPriceProduct = groceries.find().sort(new Document("price", 1))
                    .first().get("name").toString();
            int lowerPrice = groceries.find().sort(new Document("price", 1))
                    .first().getInteger("price");
            String maxPriceProduct = groceries.find().sort(new Document("price", -1))
                    .first().get("name").toString();
            int maxPrice = groceries.find().sort(new Document("price", -1))
                    .first().getInteger("price");
            long priceGt100 = groceries.countDocuments(gt("price", 100));

            System.out.printf("Product counts %s.\n", productsCount);
            System.out.printf("Average price of product is %s rubles.\n", averagePrice);
            System.out.printf("Product of lowest price  %s is %s rubles. \n", lowerPriceProduct, lowerPrice);
            System.out.printf("Highest price of product %s is %s rubles. \n", maxPriceProduct, maxPrice);
            System.out.printf("Products count with price greater than 100: %s. \n", priceGt100);

        } catch (Exception e) {
            loggerError.error(e.getMessage(), e);
        }
    }

    public boolean createGroceryAndStores() {
        addStore("GLOBUS");

        addProduct("EGGS", 90);
        addProduct("BREAD", 50);
        addProduct("CHEESE", 100);
        addProduct("SAUSAGE", 150);
        addProduct("CIGARETTES", 250);
        addProduct("CHOCOLATE", 120);
        addProduct("NAPKINS", 60);
        addProduct("VODKA", 200);
        addProduct("BUTTER", 90);
        addProduct("MILK", 80);

        putProductInStore("EGGS", "GLOBUS");
        putProductInStore("CHEESE", "GLOBUS");
        putProductInStore("CHOCOLATE", "GLOBUS");
        putProductInStore("BUTTER", "GLOBUS");
        putProductInStore("MILK", "GLOBUS");

        return true;
    }

    public String getStoreName(String name) {
        return stores.find().first().get("name", name);
    }

    public String getProductName(String name) {
        return groceries.find().first().get("name", name);
    }

    public Document getProductInStore(String storeName, String productName) {
        return stores.find(and(eq("name", storeName), eq("groceries", productName)))
                .projection(fields(excludeId())).first();
    }
}
