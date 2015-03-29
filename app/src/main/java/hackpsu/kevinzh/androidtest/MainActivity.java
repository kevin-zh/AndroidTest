package hackpsu.kevinzh.androidtest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("--------------------NEW TEST-------------------------");

        TextView welcome = (TextView)findViewById(R.id.textView);
        welcome.setVisibility(View.VISIBLE);

        String message = "SOMETHING WENT WRONG";
        welcome.setText(message.toCharArray(),0,message.length());
//
        new DownloadWebpageTask(welcome).execute(getString(R.string.ticker_url));

        welcome.setText(message.toCharArray(),0,message.length());
        welcome.setTextSize(14);

//        AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
//        AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f ) ;
//        fadeIn.setDuration(2000);
//        fadeIn.setFillAfter(true);
//        fadeOut.setDuration(2000);
//        fadeOut.setFillAfter(true);
//        fadeOut.setStartOffset(4200+fadeIn.getStartOffset());
//
//        welcome.startAnimation(fadeIn);
//        welcome.startAnimation(fadeOut);



    }



    // And From your main() method or any other method



//    public String readJSON(String url) throws IOException {
//        StringBuilder builder = new StringBuilder();
//        URL warbsit = new URL(url);
//        URLConnection connection = warbsit.openConnection();
//        BufferedReader in = new BufferedReader(new InputStreamReader(
//                connection.getInputStream()));
//        String inputLine;
//        while ((inputLine = in.readLine()) != null)
//            builder.append(inputLine);
//
//        return builder.toString();
//    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
