package hackpsu.kevinzh.androidtest;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Kevin on 3/28/2015.
 */
// Uses AsyncTask to create a task away from the main UI thread. This task takes a
// URL string and uses it to create an HttpUrlConnection. Once the connection
// has been established, the AsyncTask downloads the contents of the webpage as
// an InputStream. Finally, the InputStream is converted into a string, which is
// displayed in the UI by the AsyncTask's onPostExecute method.
public class DownloadWebpageTask extends AsyncTask<String, Void, String> {
    DownloadWebpageTask(TextView target){
        super();
        this.target = target;
    }

    private TextView target;
    private String text = "OOPS WEB CODE FAILED";
    @Override
    protected String doInBackground(String... urls) {

        // params comes from the execute() call: params[0] is the url.
        try {
            Log.d("~!@#$%^&*DEBUG*&^%$#@!~", "The url is " + urls[0]);
            text = downloadUrl(urls[0]);
            return text;
        } catch (IOException e) {
            return e.toString();
        }
    }
    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {

        if(text == null) {
            text = "[OOPS I'M NULL]";
        }

        Log.d("~!@#$%^&*DEBUG*&^%$#@!~", "The result is " + result);
        Log.d("~!@#$%^&*DEBUG*&^%$#@!~", "The text is " + text);

        target.setText(text.toCharArray(),0,text.length());

        super.onPostExecute(result);
    }



    // Given a URL, establishes an HttpUrlConnection and retrieves
// the web page content as a InputStream, which it returns as
// a string.
    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("~!@#$%^&*DEBUG*&^%$#@!~", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-16");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}