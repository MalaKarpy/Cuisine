import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;

public class CuisineTest {



  @Test
  public void cuisine_instantiatesCorrectly_true() {
    Cuisine myCuisine = new Cuisine("Mexican", (4));
    assertEquals(true, myCuisine instanceof Cuisine);
  }

  @Test
  public void cuisine_returnsType() {
    Cuisine myCuisine = new Cuisine("Mexican", (4));
    assertEquals("Mexican", myCuisine.getType());
  }

  @Test
  public void cuisine_returnsCuisineId() {
    Cuisine myCuisine = new Cuisine("Mexican", (4));
    assertEquals(5, myCuisine.getCuisineId());
  }

  // @Test
  // public void cuisineArray_emptyAtFirst() {
  //   assertEquals(Cuisine.all().size(), 0);
  // }

}
