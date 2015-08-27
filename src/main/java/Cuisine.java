import java.util.List;
import org.sql2o.*;

public class Cuisine {
  private String type;
  private int id;

  public String getType() {
    return type;
  }

  public int getCuisineId() {
    return id;
  }

  public Cuisine(String type) {
  this.type = type;
  //this.id = id;

}

  @Override
    public boolean equals(Object otherCuisine){
      if (!(otherCuisine instanceof Cuisine)) {
        return false;
      } else {
        Cuisine newCuisine = (Cuisine) otherCuisine;
        return this.getType().equals(newCuisine.getType());
      }
    }


  public static Cuisine find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Cuisine where id=:id";
      Cuisine Cuisine = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Cuisine.class);
      return Cuisine;
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

  public static List<Cuisine> all() {
    String sql = "SELECT id, type FROM Cuisine";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Cuisine.class);
    }
  }


  public List<Restaurants> getRestaurants() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM Restaurants where cuisineId=:id";
        return con.createQuery(sql)
          .addParameter("id", this.id)
          .executeAndFetch(Restaurants.class);
      }
}
   public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO cuisine(type) VALUES (:type)";
        this.id = (int) con.createQuery(sql, true)
          .addParameter("type", this.type)
          .executeUpdate()
          .getKey();
      }
    }

}
