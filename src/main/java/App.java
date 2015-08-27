import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import org.sql2o.*;
import java.util.*;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("cuisine/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    get("/cuisine", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // get("cuisine/:id/restaurants/new", (request,response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
    //   List<Restaurants> restaurants = cuisine.getRestaurants();
    //   model.put("cuisine", cuisine);
    //   model.put("restaurants", restaurants);
    //   model.put("template", "templates/index.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    get("cuisines/:id", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
      List<Restaurants> restaurants = cuisine.getRestaurants();
      model.put("cuisine", cuisine);
      model.put("restaurants", restaurants);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/cuisine", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String input = request.queryParams("name");
      Cuisine newCuisine = new Cuisine(input);
      newCuisine.save();
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

    post("/restaurants", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Cuisine cuisine = Cuisine.find(Integer.parseInt(request.queryParams("cuisineId")));
      int cuisineId = cuisine.getCuisineId();
      System.out.println(cuisineId);

      String name = request.queryParams("name");
      Restaurants newRestaurants = new Restaurants(name, cuisineId);
      newRestaurants.save();
      List<Restaurants> restaurants = cuisine.getRestaurants();
      model.put("cuisine", cuisine);
      model.put("cuisines", Cuisine.all());
      model.put("restaurants", restaurants);
      model.put("template", "templates/restaurants.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/restaurants", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Restaurants> restaurants = Restaurants.all();
      model.put("restaurants", restaurants);
      model.put("restaurants", Cuisine.all());
      model.put("template", "templates/restaurants.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/restaurants/:id", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Restaurants restaurants = Restaurants.find(id);
      model.put("restaurants", restaurants);
      model.put("template", "templates/restaurants.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // post("/cuisine", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   Cuisine thisCuisine = Cuisine.find(Integer.parseInt(request.queryParams("cuisineId")));
    //   int id = Integer.parseInt(request.params("id"));
    //   Cuisine cuisine = Cuisine.find(id);
    //   model.put("cuisine", cuisine);
    //   model.put("cuisine", Cuisine.all());
    //   model.put("template", "templates/index.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    // post ("/restaurants/:id", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   Restaurants restaurants = Restaurants.find(Integer.parseInt(request.params("cuisineId")));
    //   Cuisine cuisine = Cuisine.find(restaurants.getCuisineId());
    //   String name = request.queryParams("name");
    //   // restaurants.update("name");
    //   model.put("template", "templates/restaurants.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
  //
  //   post("/restaurants/:id", (request, response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //   Restaurants restaurants = Restaurants.find(Integer.parseInt(request.params("cuisineId")));
  //   Cuisine cuisine = Cuisine.find(restaurants.getCuisineId());
  //   String name = request.queryParams("name");
  //   restaurants.update("name");
  //   model.put("template", "templates/restaurants.vtl");
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());

    post("/restaurants/:id/:cuisineId/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Restaurants restaurants = Restaurants.find(Integer.parseInt(request.params("cuisineId")));
      restaurants.delete();
      model.put("cuisines", Cuisine.all());
      model.put("restaurants", Restaurants.all());
      // response.redirect("/index.vtl");
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
