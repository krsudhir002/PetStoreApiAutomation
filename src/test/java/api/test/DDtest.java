package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoint.UserEndPoints;
import api.payload.User;
import api.utilities.dataProviders;
import io.restassured.response.Response;

public class DDtest {

@Test(priority=1, dataProvider="Data", dataProviderClass=dataProviders.class )
public void testPostuser(String userID, String userName,String fname,String lname,String useremail,String pwd, String ph ){

	User userPayload=new User();
	
	userPayload.setId(Integer.parseInt(userID));
	
	userPayload.setUsername(userName);
	userPayload.setFirstname (fname);
	
	userPayload.setLastname(lname); 
	userPayload.setEmail (useremail);
	
	userPayload.setPassword (pwd);
	
	userPayload.setPhone (ph);
	
	Response response=UserEndPoints.createUser(userPayload); 
	Assert.assertEquals(response.getStatusCode(),200);

}


@Test(priority=2,dataProvider="UserNames",dataProviderClass=dataProviders.class)
void testDeleteuser(String userName) {
	Response response=UserEndPoints.deleteUser(userName); 
	Assert.assertEquals(response.getStatusCode(),200);
}


}








