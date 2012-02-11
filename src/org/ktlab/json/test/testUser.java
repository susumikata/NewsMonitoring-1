package org.ktlab.json.test;

import java.util.Map;
import java.util.TreeMap;

import org.ktlab.json.JSONReader;
import org.ktlab.json.JSONWriter;

class User {
  private String name;
  private int age;
  private Map<String,Integer> point = new TreeMap<String,Integer>();
  
  public User() {    
  }
  
  public User(String _name, int _age, Map<String,Integer> _point) {
    this.setName(_name);
    this.setAge(_age);
    this.setPoint(_point);
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
  
  public void setAge(int age) {
    this.age = age;
  }
  
  public int getAge() {
    return age;
  }

  public void setPoint(Map<String,Integer> point) {
    this.point = point;
  }

  public Map<String,Integer> getPoint() {
    return point;
  }
}

public class testUser {
  public static void main(String... arg) throws Exception {
    //Create JSONWriter object with a new file test.json
	JSONWriter writer = new JSONWriter("test.json") ;
    Map<String, Integer> point = new TreeMap<String, Integer>();
    point.put("Toán", 5);
    point.put("Văn", 6);
    point.put("Anh", 10);
    User user1 = new User("Vũ", 29, point);
    User user2 = new User("Quỳnh", 26, point);
    User user3 = new User("Thanh", 25, point);
    writer.write(user1);
    writer.write(user2);
    writer.write(user3);
    writer.close();
    
    //Create JSONReader object to read file .json with json file's name
    JSONReader reader = new JSONReader("test.json");
    User user = null;
    while((user = reader.read(User.class)) != null) {
      System.out.println(user.getName());
    }
    reader.close();
  }
}
