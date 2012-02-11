package org.ktlab.crawler.data;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

public class URLEntryDB {
  String dbName = "URLEntry";
  Mongo mongo;
  Datastore ds;

  URLEntryDB() throws Exception {
    mongo = new Mongo();
    Morphia morphia = new Morphia();
    morphia.map(URLEntry.class);
    ds = morphia.createDatastore(mongo, dbName);
    ds.ensureIndexes();
    ds.ensureCaps();
  }

  public void drop() {
    mongo.dropDatabase(dbName);
  }

  public void save(URLEntry entry) {
    ds.save(entry);
  }

  public Datastore getDS() {
    return ds;
  }
}
