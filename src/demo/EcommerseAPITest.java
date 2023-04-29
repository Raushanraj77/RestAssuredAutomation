package demo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.OrderDetail;
import pojo.Orders;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EcommerseAPITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	RequestSpecification res = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.setContentType(ContentType.JSON).build();
	
	LoginRequest loginRequest = new LoginRequest();
	loginRequest.setUserEmail("for2testji@gmail.com");
	loginRequest.setUserPassword("India@123");
	
	RequestSpecification reqLogin = given().log().all().spec(res).body(loginRequest);
	LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().log().all().extract().response().as(LoginResponse.class);
	
	System.out.println(loginResponse.getToken());
	System.out.println(loginResponse.getUserId());
	String token = loginResponse.getToken();
	String userId = loginResponse.getUserId();
	
	//Add Product
	
	RequestSpecification addProductAuthReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
	.addHeader("authorization", token).build();
	
	RequestSpecification reqAddProduct = given().log().all().spec(addProductAuthReq).param("productName", "Televis")
	.param("productAddedBy", userId).param("productCategory", "Electronics")
	.param("productSubCategory", "Elec").param("productPrice", "10323")
	.param("productDescription", "TSeries").param("productFor", "Raja")
	.multiPart("productImage", new File("//Users//maa//Documents//Java_RestAssured//tv.jpg"));
	
	String addProductRes= reqAddProduct.when().post("/api/ecom/product/add-product")
	.then().log().all().extract().response().asString();
	
	JsonPath js = new JsonPath(addProductRes);
	String productOrderId = js.get("productId");
	
	//Create Order
	
	RequestSpecification createOrderAuthReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("authorization", token).setContentType(ContentType.JSON).build();
	
	OrderDetail orderDetail = new OrderDetail();
	orderDetail.setCountry("India");
	orderDetail.setProductOrderId(productOrderId);
	
	List<OrderDetail> orderDetailList = new ArrayList<OrderDetail> ();
	orderDetailList.add(orderDetail);
	
	Orders orders = new Orders();
	orders.setOrders(orderDetailList);
	
	RequestSpecification createOrderReq = given().log().all().spec(createOrderAuthReq).body(orders);
	String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
	System.out.println(responseAddOrder);
	
	//Delete Product
	
	RequestSpecification deleteOrderAuthReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("authorization", token).setContentType(ContentType.JSON).build();
	RequestSpecification deleteProdReq = given().log().all().spec(deleteOrderAuthReq).pathParam("productID", productOrderId);
	String deleteProdResponse = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productID}").then().log().all().extract().response().asString();
	
	JsonPath js1 = new JsonPath(addProductRes);
	String deleteproductId = js1.get("message");
	System.out.println(deleteproductId);
	}

}
