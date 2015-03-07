package com.stokmate.backend.external;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;

/**
 * Created by amdesai on 3/6/15.
 */
public class ExternalCallClient {
    public String fetch(String url) throws Exception {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            final HttpGet httpGet =
                    new HttpGet(url);

            response = httpclient.execute(httpGet);

            HttpEntity entity = response.getEntity();


            // Get the response
            BufferedReader rd = new BufferedReader
                    (new InputStreamReader(entity.getContent()));

            StringBuffer buffer = new StringBuffer("");
            String line;
            while ((line = rd.readLine()) != null) {
                buffer.append(line);
            }
            EntityUtils.consume(entity);
            return buffer.toString();
        } finally {
            if (response != null) {
                response.close();
            }
            if (httpclient != null) {
                httpclient.close();
            }
        }

    }

    public JSONObject fetchJSON(String url) throws Exception {
        JSONObject jsonObj = new JSONObject(fetch(url));
        return jsonObj;
    }

    public String encode(String data) throws Exception{
        return URLEncoder.encode(data,"UTF-8");
    }

}
