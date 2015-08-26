import java.util.List;
import org.sql2o.*;




public class Restaurants {
  private int restaurantId;
  private String name;
  private String type;
  private int cuisineId;

  public Restaurants(int restaurantId, String name, String type, int cuisineId) {
    this.restaurantId = restaurantId;
    this.name = name;
    this.type = type;
    this.cuisineId = cuisineId;
  }

  public int getRestaurantId() {
    return restaurantId;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public int getCuisineId() {
    return cuisineId;
  }

}
