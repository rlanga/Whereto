//package uk.ac.brunel.cs14rrl1.whereto;
//
///**
// * Created by cs14rrl1 on 10/03/2016.
// */
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import java.text.DecimalFormat;
//
//        import org.json.JSONException;
//        import org.json.JSONObject;
//
//        import uk.ac.brunel.cs14rrl1.whereto.Site;
//        import com.google.gson.Gson;
//
//        import android.app.Activity;
//        import android.content.BroadcastReceiver;
//        import android.content.Context;
//        import android.content.Intent;
//        import android.content.IntentFilter;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.ConnectivityManager;
//        import android.net.NetworkInfo;
//        import android.os.Bundle;
//import android.util.Base64;
//import android.util.Log;
//        import android.view.Gravity;
//        import android.view.View;
//        import android.view.inputmethod.InputMethodManager;
//        import android.widget.Button;
//        import android.widget.EditText;
//        import android.widget.ImageView;
//        import android.widget.TextView;
//        import android.widget.Toast;
//
//public class JSONSiteactivity extends Activity {
//
//    private MyRequestReceiver receiver;
//    private Button getInfo;
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sitetest);
//
//        //Register your receiver so that the Activity can be notified
//        //when the JSON response came back
//        IntentFilter filter = new IntentFilter(MyRequestReceiver.PROCESS_RESPONSE);
//        filter.addCategory(Intent.CATEGORY_DEFAULT);
//        receiver = new MyRequestReceiver();
//        registerReceiver(receiver, filter);
//
//        getSiteInformation();
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        Log.v("JSONActivity", "onDestory");
//
//        unregisterReceiver(receiver);
//        super.onDestroy();
//    }
//
//    private void getSiteInformation() {
//
//        boolean internet =  isNetworkAvailable(this);
//        if(internet){
//            String code = "2";
//
//                //pass the request to your service so that it can
//                //run outside the scope of the main UI thread
//                Intent msgIntent = new Intent(this, JSONRequest.class);
//                msgIntent.putExtra(JSONRequest.IN_MSG, "getSiteInfo");
//                msgIntent.putExtra("catId", code);
//                startService(msgIntent);
//
//        }
//    }
//
//    //parse and display JSON response
//    private void displaySiteInformation(String response){
//
//        JSONObject responseObj = null;
//
//        //get references to your views
//        TextView name = (TextView) findViewById(R.id.siteName);
//        ImageView siteimage = (ImageView) findViewById(R.id.siteImage);
//
//        try {
//            //create JSON object from JSON string
//            responseObj = new JSONObject(response);
//            //get the success property
//            boolean success = responseObj.getBoolean("success");
//            if(success){
//
//                Gson gson = new Gson();
//                //get the site information property
//                String siteInfo = responseObj.getString("siteInfo");
//                //create java object from the JSON object
//                Site site = gson.fromJson(siteInfo, Site.class);
//
//                //set values from your site java object
//                name.setText(site.getName());
//
////                byte[] decodedString = Base64.decode(site.getImage(), Base64.DEFAULT);
////                InputStream inputStream  = new ByteArrayInputStream(decodedString);
////                Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
////                siteimage.setImageBitmap(bitmap);
//            }
//            else {
//
//                name.setText(" ");
//                siteimage.setImageBitmap(null);
//
//            }
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    //check if you have internet connection
//    private boolean isNetworkAvailable(Context context) {
//        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivity != null) {
//            NetworkInfo[] info = connectivity.getAllNetworkInfo();
//            if (info != null) {
//                for (int i = 0; i < info.length; i++) {
//                    Log.w("INTERNET:",String.valueOf(i));
//                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
//                        Log.w("INTERNET:", "connected!");
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//
//    //broadcast receiver to receive messages sent from the JSON IntentService
//    public class MyRequestReceiver extends BroadcastReceiver{
//
//        public static final String PROCESS_RESPONSE = "uk.ac.brunel.cs14rrl1.whereto.intent.action.PROCESS_RESPONSE";
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//            String response = null;
//            String responseType = intent.getStringExtra(JSONRequest.IN_MSG);
//
//            if(responseType.trim().equalsIgnoreCase("getSiteInfo")){
//                response = intent.getStringExtra(JSONRequest.OUT_MSG);
//                displaySiteInformation(response);
//            }
//            else if(responseType.trim().equalsIgnoreCase("getSomethingElse")){
//                //you can choose to implement another transaction here
//            }
//
//        }
//    }
//}