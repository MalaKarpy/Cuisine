import org.junit.*;
import static org.junit.Assert.*;

public class RestaurantsTest {

  @Test
  public void restaurant_instants_true() {
    Restaurants myRestaurants = new Restaurants("Olive Garden", 9);
    assertEquals(true, myRestaurants instanceof Restaurants);
  }

  @Test
  public void restaurant_returnsrestaurantId() {
    Restaurants myRestaurants = new Restaurants("Pho Gia", 0);
    assertEquals(0, myRestaurants.getRestaurantId());
  }

  @Test
  public void restaurant_returnsName() {
    Restaurants myRestaurants = new Restaurants("Pokez", 4);
    assertEquals("Pokez", myRestaurants.getName());
  }

  // @Test
  // public void restaurant_returnsType() {
  //   Restaurants myRestaurants = new Restaurants("Pokez", 6);
  //   assertEquals("Pokez", myRestaurants.getType());
  // }

  @Test
  public void restaurant_returnsCuisineId() {
    Restaurants myRestaurants = new Restaurants("Pho Gia", 9);
    assertEquals(9, myRestaurants.getCuisineId());
  }

}
