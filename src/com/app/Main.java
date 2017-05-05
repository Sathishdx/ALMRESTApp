package com.app;

import com.infrastructure.AlmRestConnector;
import com.utility.PropertyFile;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {

        AlmRestConnector connect = new AlmRestConnector();
        try {
            PropertyFile propertyFile = new PropertyFile();
            //Authentication
            CloseableHttpResponse responseGet = connect.httpGet("https://"+propertyFile.getServer()+""+propertyFile.getAuthenticateURI());
            System.out.println(responseGet.getStatusLine().getStatusCode());
            responseGet = connect.login("https://"+propertyFile.getServer()+""+propertyFile.getLoginURIs(), propertyFile.getUsername(), propertyFile.getPassword());
            System.out.println(responseGet.getStatusLine().getStatusCode());
            /*
            Perform read/write operations
            */
            //Logout and clear the sesssion
            responseGet = connect.logout("https://"+propertyFile.getServer()+""+propertyFile.getLogoutURI());
            System.out.println(responseGet.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
