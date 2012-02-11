package org.ktlab.mongodb;

import java.net.UnknownHostException;
import java.util.List;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class testMongoDB {

  public static void main(String[] args) throws UnknownHostException, MongoException {
    long start = System.currentTimeMillis() ;
    Mongo mongo = new Mongo();
    mongo.dropDatabase("Hotel");
    
    Morphia morphia = new Morphia();
    morphia.map(Hotel.class);
    Datastore ds = morphia.createDatastore(mongo, "Hotel");
    ds.ensureIndexes();
    ds.ensureCaps();    
    String name = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    String content = "";
    for (int i = 0; i < 2; i++) content += name;
    for (int i = 0; i < 10000000; i++) {
      Hotel hotel = new Hotel();
      hotel.setName(i + content);
      hotel.setStars(4);
      ds.save(hotel);  
    }        
    
    /*List<Hotel> hotels = ds.find(Hotel.class).asList();
    for (Hotel ht:hotels){
      System.out.println(ht.getId());
      System.out.println(ht.getName());
      System.out.println(ht.getStars());
      System.out.println("=======================");
    }*/
    long time = System.currentTimeMillis() - start ;
    System.out.println("Analyze in " + time + "ms");
  }
}
