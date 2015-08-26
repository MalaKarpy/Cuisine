import java.util.List;
import org.sql2o.*;




public class Restaurants {
  private int id;
  private String name;
  private int cuisineId;

  public Restaurants(String name, int cuisineId) {
    this.name = name;
    this.cuisineId = cuisineId;
  }

  public int getRestaurantId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
    public boolean equals(Object otherRestaurant){
      if (!(otherRestaurant instanceof Restaurant)) {
        return false;
      } else {
        Restaurant newRestaurant = (Restaurant) otherRestaurant;
        return this.getName().equals(newRestaurant.getName());
      }
    }

  public int getCuisineId() {
    return cuisineId;
  }

  public static Restaurants find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Restaurant where id=:id";
      Restaurants Restaurants = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Restaurants.class);
      return Restaurants;
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

  public static List<Restaurants> all() {
    String sql = "SELECT id, name FROM Restaurant";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurants.class);
    }
  }

  public void save() {
     try(Connection con = DB.sql2o.open()) {
       String sql = "INSERT INTO restaurant(name) VALUES (:name)";
       this.id = (int) con.createQuery(sql, true)
         .addParameter("name", this.name)
         .executeUpdate()
         .getKey();
     }
   }

}
