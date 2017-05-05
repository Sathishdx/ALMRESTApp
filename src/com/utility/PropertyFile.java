package com.utility;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * Reading the property file
 */
public class PropertyFile {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getAuthenticateURI() {
        return authenticateURI;
    }

    public void setAuthenticateURI(String authenticateURI) {
        this.authenticateURI = authenticateURI;
    }

    public String getLoginURIs() {
        return loginURIs;
    }

    public void setLoginURIs(String loginURIs) {
        this.loginURIs = loginURIs;
    }

    public String getLogoutURI() {
        return logoutURI;
    }

    public void setLogoutURI(String logoutURI) {
        this.logoutURI = logoutURI;
    }

    private String username;
    private String password;
    private String server;
    private String authenticateURI;
    private String loginURIs;
    private String logoutURI;

    public PropertyFile() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("com.resources.alm");
        Enumeration<String> keys = resourceBundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (key.equalsIgnoreCase("username")) {
                setUsername(resourceBundle.getString(key));
            } else if (key.equalsIgnoreCase("password")) {
                setPassword(resourceBundle.getString(key));
            } else if (key.equalsIgnoreCase("server")) {
                setServer(resourceBundle.getString(key));
            } else if (key.equalsIgnoreCase("authenticateURI")) {
                setAuthenticateURI(resourceBundle.getString(key));
            } else if (key.equalsIgnoreCase("loginURI")) {
                setLoginURIs(resourceBundle.getString(key));
            } else if (key.equalsIgnoreCase("logoutURI")) {
                setLogoutURI(resourceBundle.getString(key));
            }
        }
    }
}
