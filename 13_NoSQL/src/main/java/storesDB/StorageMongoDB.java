package storesDB;

import com.mongodb.client.*;

import static com.mongodb.client.model.Filters.*;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.BsonField;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

public class StorageMongoDB {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> groceries;
    private MongoCollection<Document> stores;

    public StorageMongoDB() {
        this.mongoClient = MongoClients.create("mongodb://localhost:27017");
        this.mongoDatabase = mongoClient.getDatabase("groceryStores");
        this.groceries = mongoDatabase.getCollection("products");
        this.stores = mongoDatabase.getCollection("stores");
        groceries.drop();
        stores.drop();
    }

    private boolean isExist(MongoCollection<Document> collection, Object value) {
        FindIterable<Document> doc = collection.find(new Document("name", value));
        return doc.first() != null;
    }

    public void addStore(String storeName) {
        if (isExist(stores, storeName)) {
            System.out.printf("Store %s is already exist! \n", storeName);
        } else {
            stores.insertOne(new Document("name", storeName).append("groceries", new ArrayList<>()));
            System.out.printf("Store %s was added \n", storeName);
        }
    }

    public void addProduct(String productName, int price) {
        if (isExist(groceries, productName)) {
            System.out.printf("Product %s is already exist! \n", productName);
        } else {
            groceries.insertOne(new Document("name", productName).append("price", price));
            System.out.printf("Product %s was added \n", productName);
        }
    }

    public void putProductInStore(String productName, String storeName) {
        if (isExist(stores, storeName) && isExist(groceries, productName)) {
            stores.updateOne(eq("name", storeName),
                    new Document("$addToSet", new Document("groceries", productName)));
            System.out.printf("Product %s was add to store %s \n", productName, storeName);

        } else {
            System.out.printf("Product %s or store %s isn't exist! \n", productName, storeName);
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
            e.printStackTrace();
        }
    }

    public void createGroceryAndStores() {
        addStore("GLOBUS");

        addProduct("EGGS", 90);
        addProduct("BREAD", 50);
        addProduct("CHEESE", 100);
        addProduct("SAUSAGE", 150);
        addProduct("MILK", 80);
        addProduct("CHOCOLATE", 120);
        addProduct("BUTTER", 90);
        addProduct("NAPKINS", 60);
        addProduct("VODKA", 200);
        addProduct("CIGARETTES", 250);

        putProductInStore( "EGGS" ,"GLOBUS");
        putProductInStore( "CHEESE" ,"GLOBUS");
        putProductInStore( "CHOCOLATE" ,"GLOBUS");
        putProductInStore( "BUTTER" ,"GLOBUS");
        putProductInStore( "MILK" ,"GLOBUS");
    }
}
