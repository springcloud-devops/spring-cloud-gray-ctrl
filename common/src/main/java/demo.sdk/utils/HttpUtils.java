package demo.sdk.utils;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by :Guozhihua
 * Date： 2017/12/8.
 */
public class HttpUtils {
    private static final RequestConfig defaultRequestConfig = RequestConfig.custom()
            .setSocketTimeout(5000)
            .setConnectTimeout(5000)
            .setConnectionRequestTimeout(5000)
            .build();

    /**
     * 发送Post form
     *
     * @throws IOException
     * @throws RuntimeException
     */
    public static String postForm(String url, Map<String, String> params) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(defaultRequestConfig);
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;
        List<BasicNameValuePair> pairList = new ArrayList<>();
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                pairList.add(new BasicNameValuePair(key, params.get(key)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
        }
        HttpResponse resp = client.execute(httpPost);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he, "UTF-8");
        } else {
            throw new IOException("请求失败" + resp);
        }
        client.close();
        return respContent;
    }

    /**
     * JSON
     *
     * @throws IOException
     * @throws RuntimeException
     */
    public static String postJson(String url, Map<String, String> params) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(defaultRequestConfig);
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;
        if (params != null && !params.isEmpty()) {
            JSONObject jsonParam = new JSONObject();
            for (String key : params.keySet()) {
                jsonParam.put(key, params.get(key));
            }
            StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");//解决中文乱码问题
            httpPost.setEntity(entity);
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
        }
        HttpResponse resp = client.execute(httpPost);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();

            respContent = EntityUtils.toString(he, "UTF-8");
        } else {
            throw new IOException("请求失败" + resp);
        }
        client.close();
        return respContent;
    }


    /**
     * get form
     *
     * @throws IOException
     * @throws RuntimeException
     */
    public static String getFrom(String url, Map<String, String> params) throws IOException {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                url =addParamsUrl(key,params.get(key),url);
            }
        }
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(defaultRequestConfig);
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;
        HttpResponse resp = client.execute(httpGet);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he, "UTF-8");
        } else {
            throw new IOException("请求失败" + resp);
        }
        client.close();
        return respContent;
    }
    public static String addParamsUrl(String paramsName, String paramVal, String url) {
        String[] arrSplit = url.split("[?]");
        if (!url.contains("?") || arrSplit.length == 1) {
            url += "?" + paramsName + "=" + paramVal;
        } else if (url.contains("?") && arrSplit.length > 1) {
            url += "&" + paramsName + "=" + paramVal;
        } else {
            url = null;
        }
        return url;
    }
}
