package com.example.star.myapplication;

import android.app.Activity;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class UploadReviewHelper extends AsyncTask<String, String, String> {
    final String serverURL = "http://52.79.216.222";
    public String storename;
    private String grade;
    private String content;
    private String nickname;


    final String upLoadServerUri = serverURL + "/review";
    Activity activity;

    public UploadReviewHelper(Activity activity, String grade,String storename,String content,String nickname) {
        this.grade = grade;
        this.storename = storename;
        this.content = content;
        this.nickname = nickname;
        this.activity = activity;
    }


    @Override
    protected String doInBackground(String... filepath) {

        String fileName = filepath[0];

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(fileName);

        try {

            // open a URL connection to the Servlet
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            URL url = new URL(upLoadServerUri);

            // Open a HTTP  connection to  the URL
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); // Allow Inputs
            conn.setDoOutput(true); // Allow Outputs
            conn.setUseCaches(false); // Don't use a Cached Copy
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("ENCTYPE", "multipart/form-data");
            conn.setRequestProperty("Accept-Charset","euc-kr");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+ boundary);
            conn.setRequestProperty("images", fileName);

            dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"grade\"\r\n\r\n");
            dos.write(grade.getBytes(Charset.forName("UTF-8")));
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"storename\"\r\n\r\n");
            dos.write(storename.getBytes(Charset.forName("UTF-8")));
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"content\"\r\n\r\n");
            dos.write(content.getBytes(Charset.forName("UTF-8")));
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"nickname\"\r\n\r\n");
            dos.write(nickname.getBytes(Charset.forName("UTF-8")));
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"images\";filename=\""
                    + fileName + "\"" + lineEnd);
            dos.writeBytes(lineEnd);

            // create a buffer of  maximum size
            bytesAvailable = fileInputStream.available();

            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            // read file and write it into form...
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {

                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            }

            // send multipart form data necesssary after file data...
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            // Responses from the server (code and message)
            int serverResponseCode = conn.getResponseCode();
            String serverResponseMessage = conn.getResponseMessage();

            Log.i("uploadFile", "HTTP Response is : "
                    + serverResponseMessage + ": " + serverResponseCode);

            StringBuilder response;
            if (serverResponseCode == 200) {
                BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream(), "cp949"));
                response = new StringBuilder();
                String strLine = null;
                while ((strLine = input.readLine()) != null)
                    response.append(strLine);

                input.close();

                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();
                return response.toString();
            }

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            Log.e("uploadFile", "error: " + ex.getMessage(), ex);
            return "MalformedURLException Exception : check script url.";
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("uploadFile", "Exception : " + e.getMessage(), e);
            return "Got Exception : see logcat ";
        }
        return null;
    }

}