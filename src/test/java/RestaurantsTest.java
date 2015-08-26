import org.junit.*;
import static org.junit.Assert.*;

public class RestaurantsTest {

  @Test
  public void restaurant_instants_true() {
    Restaurants myRestaurants = new Restaurants(8, "Olive Garden", "italian", 9);
    assertEquals(true, myRestaurants instanceof Restaurants);
  }

  @Test
  public void restaurant_returnsrestaurantId() {
    Restaurants myRestaurants = new Restaurants(6, "Pho Gia", "Vietnamese", 9);
    assertEquals(6, myRestaurants.getRestaurantId());
  }

  @Test
  public void restaurant_returnsName() {
    Restaurants myRestaurants = new Restaurants(4, "Pokez", "Mexican", 4);
    assertEquals("Pokez", myRestaurants.getName());
  }

  @Test
  public void restaurant_returnsType() {
    Restaurants myRestaurants = new Restaurants(3, "Pokez", "Mexican", 6);
    assertEquals("Mexican", myRestaurants.getType());
  }

  @Test
  public void restaurant_returnsCuisineId() {
    Restaurants myRestaurants = new Restaurants(5, "Pho Gia", "Vietnamese", 9);
    assertEquals(9, myRestaurants.getCuisineId());
  }

}
