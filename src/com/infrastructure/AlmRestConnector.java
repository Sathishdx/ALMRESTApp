package com.infrastructure;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Rest connector to intialize cookies and perform operations.
 */
public class AlmRestConnector {
    protected BasicCookieStore cookieStore;
    protected CloseableHttpClient httpclient;

    protected BasicCookieStore setCookie() {
        cookieStore = new BasicCookieStore();
        httpclient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();
        return cookieStore;
    }

    public BasicCookieStore getCookie() {
        return this.cookieStore;
    }

    private HttpEntity entityConsume(CloseableHttpResponse response) {

        HttpEntity entity = response.getEntity();
        try {
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public CloseableHttpResponse httpGet(String uri) throws IOException {
        CloseableHttpResponse responseGet = null;
        this.setCookie();
        try {
            HttpGet httpget = new HttpGet(uri);
            responseGet = httpclient.execute(httpget);
            entityConsume(responseGet);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseGet;
    }

    public CloseableHttpResponse login(String uri, String username, String password) throws IOException {
        CloseableHttpResponse responsePost = null;
        try {
            HttpUriRequest login = RequestBuilder.post().setUri(new URI(uri)).addParameter("j_username", username).addParameter("j_password", password).build();
            responsePost = httpclient.execute(login);
            entityConsume(responsePost);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responsePost;
    }

    public CloseableHttpResponse logout(String uri) {
        CloseableHttpResponse responseLogout = null;
        try {
            HttpGet httpget = new HttpGet(uri);
            responseLogout = httpclient.execute(httpget);
            entityConsume(responseLogout);
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseLogout;
    }
}
