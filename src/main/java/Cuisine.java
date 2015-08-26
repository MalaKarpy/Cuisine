import java.util.List;
import org.sql2o.*;

public class Cuisine {
  private String type;
  private int cuisineId;

  public Cuisine(String type, int cuisineId) {
  this.type = type;
  this.cuisineId = cuisineId;

}
  public String getType() {
    return type;
  }

  public int getCuisineId() {
    return cuisineId;
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
    String sql = "SELECT id, name FROM Cuisine";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Cuisine.class);
    }
  }


//   public List<Restaurants> getRestaurants() {
//       try(Connection con = DB.sql2o.open()) {
//         String sql = "SELECT * FROM Restaurants where cuisineId=:id";
//         return con.createQuery(sql)
//           .addParameter("id", this.id)
//           .executeAndFetch(Restaurants.class);
//       }
// }
}
