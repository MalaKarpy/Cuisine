import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Restaurants {
  private int id;
  private String name;
  private int cuisineId;

  public int getRestaurantId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getCuisineId() {
    return cuisineId;
  }

  public Restaurants(String name, int cuisineId) {
    this.name = name;
    this.cuisineId = cuisineId;
  }

  @Override
    public boolean equals(Object otherRestaurants){
      if (!(otherRestaurants instanceof Restaurants)) {
        return false;
      } else {
        Restaurants newRestaurants = (Restaurants) otherRestaurants;
        return this.getName().equals(newRestaurants.getName()) &&
              this.getRestaurantId() == newRestaurants.getRestaurantId() &&
              this.getCuisineId() == newRestaurants.getCuisineId();
      }
    }


  public static Restaurants find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Restaurants where id=:id";
      Restaurants Restaurants = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Restaurants.class);
      return Restaurants;
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

  public static List<Restaurants> all() {
    String sql = "SELECT id, name, cuisineId FROM Restaurants";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurants.class);
    }
  }

  public void save() {
     try(Connection con = DB.sql2o.open()) {
       String sql = "INSERT INTO Restaurants(name, cuisineId) VALUES (:name, :cuisineId)";
       this.id = (int) con.createQuery(sql, true)
         .addParameter("name", name)
         .addParameter("cuisineId", cuisineId)
         .executeUpdate()
         .getKey();
     }
   }

   public void update(String name) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE Restaurants SET name = :name WHERE id = :id";
    con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("id", id)
      .executeUpdate();
    }
}

public void delete() {
  try(Connection con = DB.sql2o.open()) {
  String sql = "DELETE FROM Restaurants WHERE id = :id;";
  con.createQuery(sql)
    .addParameter("id", id)
    .executeUpdate();
  }
}
}
