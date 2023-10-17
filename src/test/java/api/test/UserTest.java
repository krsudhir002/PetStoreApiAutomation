package api.test;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import api.endpoint.Routes;
import api.endpoint.UserEndPoints;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserTest {
	Faker faker;
	User payload;
	public Logger logger;
	
	@BeforeClass
	public void setUpData() {
		faker=new Faker();
		payload=new User();
		payload.setId(faker.idNumber().hashCode());
		payload.setUsername(faker.name().username());
		payload.setFirstname(faker.name().firstName());
		payload.setLastname(faker.name().lastName());
		payload.setEmail(faker.internet().emailAddress());
		payload.setPassword(faker.internet().password());
		payload.setPhone(faker.phoneNumber().cellPhone());
		
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	void testPostUser() throws IOException {
	
		logger.info("*******************  creating user *********************");
		
		Response response=(Response) UserEndPoints.createUser(payload);
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*******************  created user *********************");


		
	}
	@Test(priority=2)
	void testGettUser() throws IOException {
		logger.info("*******************  getting user *********************");
		
		Response response=(Response) UserEndPoints.getUser(this.payload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*******************  got user *********************");
		

		
	}
	
	@Test(priority=3)
	void testUpdatetUser() throws IOException {
		logger.info("*******************  updating user *********************");
		Response response=(Response) UserEndPoints.updateUser(payload,this.payload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*******************  updated user *********************");
		

		
	}
	
	@Test(priority=4)
	void testDeletetUser() throws IOException {
		logger.info("*******************  deleting *********************");
		Response response=(Response) UserEndPoints.getUser(payload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*******************  deleted *********************");
		

		
	}
	
	

	

}
