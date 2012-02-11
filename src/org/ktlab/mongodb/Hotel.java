package org.ktlab.mongodb;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;


@Entity
public class Hotel {
  @Id private ObjectId id;

  private String name;
  private int stars;
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setStars(int stars) {
    this.stars = stars;
  }
  
  public ObjectId getId() {
    return this.id;
  }
  
  public int getStars() {
    return this.stars;
  }

  public String getName() {
    return this.name;
  }
}
