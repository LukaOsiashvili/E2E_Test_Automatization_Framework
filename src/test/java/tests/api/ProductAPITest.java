package tests.api;

import api.client.ProductClient;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

@Epic("API Testing")
@Feature("Product Endpoints")
public class ProductAPITest {

    private final ProductClient productClient = new ProductClient();

    @Test(priority = 1, description = "API Test Case 7: Get All Products List")
    @Description("GET /productsList return 200 with full products list including all fields")
    public void testGetAllProductsList() {
        Response GET_AllProducts_Response = productClient.getAllProducts();

        Assert.assertEquals(GET_AllProducts_Response.getStatusCode(), 200);
        Assert.assertEquals(GET_AllProducts_Response.jsonPath().getInt("responseCode"), 200);

        List<Map<String, Object>> products = GET_AllProducts_Response.jsonPath().getList("products");
        Assert.assertNotNull(products);
        Assert.assertFalse(products.isEmpty(), "Products List should not be empty");

        Map<String, Object> firstProduct = products.getFirst();
        Assert.assertNotNull(firstProduct.get("id"), "Product should have id");
        Assert.assertNotNull(firstProduct.get("name"), "Product should have name");
        Assert.assertNotNull(firstProduct.get("price"), "Product should have price");
        Assert.assertNotNull(firstProduct.get("brand"), "Product should have brand");
        Assert.assertNotNull(firstProduct.get("category"), "Product should have category");
        Assert.assertNotNull(GET_AllProducts_Response.jsonPath().getString("products[0].category.category"),
                "Product should have category name");

        Assert.assertEquals(GET_AllProducts_Response.jsonPath().getString("products[0].name"), "Blue Top");
        Assert.assertEquals(GET_AllProducts_Response.jsonPath().getString("products[0].price"), "Rs. 500");
        Assert.assertEquals(GET_AllProducts_Response.jsonPath().getString("products[0].brand"), "Polo");
        Assert.assertEquals(GET_AllProducts_Response.jsonPath().getString("products[0].category.category"), "Tops");
        Assert.assertEquals(GET_AllProducts_Response.jsonPath().getString("products[0].category.usertype.usertype"), "Women");
    }

    @Test(priority = 2, description = "API Test Case 8: POST To All Products List")
    @Description("POST /productsList return 405 method not supported")
    public void testPostToAllProductsList() {
        Response POST_AllProducts_Response = productClient.postToProductsList();

        Assert.assertEquals(POST_AllProducts_Response.getStatusCode(), 200);
        Assert.assertEquals(POST_AllProducts_Response.jsonPath().getInt("responseCode"), 405);
        Assert.assertEquals(POST_AllProducts_Response.jsonPath().getString("message"),
                "This request method is not supported.");
    }

    @Test(priority = 3, description = "API Test Case 9: POST To Search Product")
    @Description("POST /searchProduct with 'top' returns matching products")
    public void testSearchProduct() {
        int matches = 0;
        double matchPercentage = 0;
        String searchTerm = "top";

        Response searchResponse = productClient.searchProduct(searchTerm);

        Assert.assertEquals(searchResponse.getStatusCode(), 200);
        Assert.assertEquals(searchResponse.jsonPath().getInt("responseCode"), 200);

        List<Map<String, Object>> products = searchResponse.jsonPath().getList("products");
        Assert.assertNotNull(products);
        Assert.assertFalse(products.isEmpty(), "Search results should not be empty");

        for (Map<String, Object> product : products) {
            String name = (String) product.get("name");
            if(name.toLowerCase().contains(searchTerm)) {
                matches = matches + 1;
            }
        }

        matchPercentage = (double) matches / products.size() * 100;

        Assert.assertTrue((matches > 0 && matchPercentage > 50), "At least majority of products should have search term in the name: " + searchTerm);
    }

    @Test(priority = 4, description = "API Test Case 10: POST To Search Product without parameter")
    @Description("POST /searchProduct without search_product param, returns 400")
    public void testSearchProductWithoutParam(){
        Response searchResponse = productClient.searchProductWithoutParam();

        Assert.assertEquals(searchResponse.getStatusCode(), 200);
        Assert.assertEquals(searchResponse.jsonPath().getInt("responseCode"), 400);
        Assert.assertEquals(searchResponse.jsonPath().getString("message"), "Bad request, search_product parameter is missing in POST request.");
    }
}
