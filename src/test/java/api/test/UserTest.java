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
	
		logger.info("\n*******************  creating user *********************\n");
		
		Response response=(Response) UserEndPoints.createUser(payload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("\n\n*******************  user created  *********************\n");


		
	}
	@Test(priority=2)
	void testGettUser() throws IOException {
		logger.info("\n*******************  retrieving user *********************\n");
		
		Response response=(Response) UserEndPoints.getUser(this.payload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("\n\n*******************  user retrieved *********************\n");
		

		
	}
	
	@Test(priority=3)
	void testUpdatetUser() throws IOException {
		logger.info("\n*******************  updating user *********************\n");
		Response response=(Response) UserEndPoints.updateUser(payload,this.payload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("\n\n******************* user  updated  *********************\n");
		

		
	}
	
	@Test(priority=4)
	void testDeletetUser() throws IOException {
		logger.info("\n*******************  deleting user *********************");
		Response response=(Response) UserEndPoints.getUser(payload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("\n\n*******************  user deleted *********************");
		

		
	}
	
	

	

}
