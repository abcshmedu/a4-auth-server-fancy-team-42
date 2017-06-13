package edu.hm.shareitauth.resources;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for AuthResource class.
 * @author Thomas Murschall
 *
 */
public class AuthRessourceTest {
    
    public static final String APP_URL = "/";
    public static final int PORT = 8082;
    public static final String WEBAPP_DIR = "./src/main/webapp/";
    
    private String uriBase = "http://localhost:8082/authenticate/auth";
    private String invalidToken = "{\"token\":\"ASDF\"}";
    private String validUserJSON = "{\"name\":\"markus\",\"password\":\"123456789\"}";
    private String invalidUserJSON = "{\"name\":\"hacker\",\"password\":\"987654321\"}";
    private String userInfo = "{\"admin\":true,\"language\":\"eng\",\"settings\":\"no-ads, sort after date\"}";
    private HttpURLConnection connection;
    
    /**
     * Initialization.
     * @throws Exception 
     */
    @BeforeClass
    public static void setup() throws Exception {
        Server jetty = new Server(PORT);
        jetty.setHandler(new WebAppContext(WEBAPP_DIR, APP_URL));
        jetty.start();
    }
    /**
     * Tests if we can get a token with valid credentials to log in.
     * @throws Exception 
     */
    @Test
    public void validTokenTest() throws Exception {
        connection = (HttpURLConnection) new URL(uriBase + "/user").openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        //connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.connect();
        OutputStream os = connection.getOutputStream();
        os.write(validUserJSON.getBytes());
        os.flush();
        connection.getResponseCode();


        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String token = in.readLine();
        connection.disconnect();
        
        
        connection = (HttpURLConnection) new URL(uriBase + "/valid").openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.connect();
        os = connection.getOutputStream();
        os.write(token.getBytes());
        os.flush();

        //System.out.print(connection.getResponseMessage());
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        assertTrue(userInfo.equals(in.readLine()));
        connection.disconnect();
    }
    
    /**
     * Tests if we get a token when inserting wrong credentials.
     * @throws Exception 
     */
    @Test
    public void invalidTokenTest() throws Exception {
        connection = (HttpURLConnection) new URL(uriBase + "/user").openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        //connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.connect();
        OutputStream os = connection.getOutputStream();
        os.write(invalidUserJSON.getBytes());
        os.flush();
        final int code = 400;
        assertTrue(code == connection.getResponseCode());
    }
    
    /**
     * 
     * @throws Exception 
     */
    @Test
    public void useInvalidTokenTest() throws Exception {
        connection = (HttpURLConnection) new URL(uriBase + "/valid").openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.connect();
        OutputStream os = connection.getOutputStream();
        os.write(invalidToken.getBytes());
        os.flush();
        
        
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = in.readLine();
        connection.disconnect();
        assertTrue("{\"message\":\"token is wrong.\"}".equals(response));
    }
    
}
