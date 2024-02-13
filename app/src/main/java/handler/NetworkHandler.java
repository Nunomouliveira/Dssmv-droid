package handler;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetworkHandler {

    public static String getDataInStringFromUrl(String url) throws IOException {
        InputStream is = NetworkHandler.openGetHttpConnection(url);
        String data ="";
        if(is != null) {
            data = NetworkHandler.readString(is);
        }
        return data;
    }

    public static String addDataInStringFromUrl(String url, String body) throws IOException {
        InputStream is = NetworkHandler.openPostHttpConnection(url, body);
        String data ="";
        if(is != null) {
            data = NetworkHandler.readString(is);
        }
        return data;
    }

    public static String updateDataInStringFromUrl(String url, String body) throws IOException {
        InputStream is = NetworkHandler.openPutHttpConnection(url, body);
        String data ="";
        if(is != null) {
            data = NetworkHandler.readString(is);
        }
        return data;
    }

    public static boolean deleteDataInStringFromUrl(String url){
        NetworkHandler.openDeleteHttpConnection(url);
        return true;
    }

    public static InputStream openGetHttpConnection(String urlStr) {
        InputStream in = null;
        HttpURLConnection httpConn = null;
        int resCode = -1;
        try {
            URL url = new URL(urlStr);
            URLConnection urlConn = url.openConnection();

            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }

            httpConn = (HttpURLConnection) urlConn;
            httpConn.setConnectTimeout(3000);
            httpConn.setRequestMethod("GET");
            httpConn.setDoInput(true);
            httpConn.connect();
            resCode = httpConn.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }

    public static InputStream openPostHttpConnection(String urlStr, String body) {
        InputStream in = null;
        HttpURLConnection httpConn = null;
        int resCode = -1;
        try {
            URL url = new URL(urlStr);
            URLConnection urlConn = url.openConnection();

            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }

            httpConn = (HttpURLConnection) urlConn;
            httpConn.setConnectTimeout(3000);
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setDoOutput(true);
            writeBody(httpConn.getOutputStream(), body);
            httpConn.connect();
            resCode = httpConn.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }else{
                throw new RuntimeException("rescode not 200. VALUE:"+resCode + ". URL:" + urlStr +". With body:"+body);
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }

    public static InputStream openPutHttpConnection(String urlStr, String body) {
        InputStream in = null;
        HttpURLConnection httpConn = null;
        int resCode = -1;
        try {
            URL url = new URL(urlStr);
            URLConnection urlConn = url.openConnection();

            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }

            httpConn = (HttpURLConnection) urlConn;
            httpConn.setConnectTimeout(3000);
            httpConn.setRequestMethod("PUT");
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setDoOutput(true);
            writeBody(httpConn.getOutputStream(), body);
            httpConn.connect();
            resCode = httpConn.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }else{
                throw new RuntimeException("rescode not 200. VALUE:"+resCode + ". URL:" + urlStr +". With body:"+body);
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }

    private static void writeBody(OutputStream writer, String body){
        try {
            byte[] dataBytes = body.getBytes("UTF-8");
            writer.write(dataBytes);
            writer.flush();
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static InputStream openDeleteHttpConnection(String urlStr) {
        InputStream in = null;
        HttpURLConnection httpConn = null;
        int resCode = -1;
        try {
            URL url = new URL(urlStr);
            URLConnection urlConn = url.openConnection();

            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }

            httpConn = (HttpURLConnection) urlConn;
            httpConn.setConnectTimeout(3000);
            httpConn.setRequestMethod("DELETE");
            httpConn.setDoInput(true);
            httpConn.connect();
            resCode = httpConn.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_ACCEPTED) {
                in = httpConn.getInputStream();
            }else{
                throw new RuntimeException("rescode not 202. VALUE:"+resCode);
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }

    public static String readString(InputStream is) throws IOException {
        char[] buf = new char[2048];
        Reader r = new InputStreamReader(is, "UTF-8");
        StringBuilder s = new StringBuilder();
        while (true) {
            int n = r.read(buf);
            if (n < 0)
                break;
            s.append(buf, 0, n);
        }
        return s.toString();
    }

}
