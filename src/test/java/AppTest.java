import org.fluentlenium.adapter.FluentTest;
import java.util.ArrayList;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.sql2o.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Restaurants");
  }

  // @Test
  // public void cuisineIsCreatedAndDisplayedTest() {
  //   goTo("http://localhost:4567/");
  //   Cuisine myCuisine = new Cuisine("Mexican", 4);
  //   click("a", withText("Add A New Cuisine"));
  //   fill("#type").with("Mexican");
  //   myCuisine.save();
  //   submit(".btn");
  //   assertThat(pageSource()).contains("Mexican");
  // }

  // @Test
  // public void categoryIsCreatedAndDisplayedTest() {
  //   goTo("http://localhost:4567/");
  //   click("a", withText("Add a New Category"));
  //   fill("#name").with("fun");
  //   submit(".btn");
  //   assertThat(pageSource()).contains("Categories");
  // }
  //
  // @Test
  // public void taskIScreatedTest(){
  //   goTo("http://localhost:4567/");
  //   click("a", withText("Add a New Category"));
  //   fill("#name").with("fun");
  //   submit(".btn");
  //   System.out.println(pageSource());
  //   click("a", withText("fun"));
  //   click("a", withText("Add another task to fun"));
  //   fill("#description").with("cool stuff");
  //   submit(".btn");
  //   assertThat(pageSource()).contains("cool stuff");
  // }
}
