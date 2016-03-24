package uk.ac.brunel.cs14rrl1.whereto;

        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Base64;
        import android.util.Log;
        import android.view.View;
        import android.widget.ProgressBar;
        import android.widget.Toast;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;
        import java.io.BufferedReader;
        import java.io.ByteArrayInputStream;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private static final String TAG = "RecyclerViewExample";
    private List<Site> siteList;
    private RecyclerView mRecyclerView;
    private RVAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_activity);

        // Initialize recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RVAdapter(RecyclerViewActivity.this, siteList);
        mRecyclerView.setAdapter(adapter);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        // Downloading data from below url
        final String url = "http://134.83.83.25:47314/sites?catId=2";
        new AsyncHttpTask().execute(url);
    }

    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult(response.toString());
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Download complete. Let us update UI
            progressBar.setVisibility(View.GONE);

            if (result == 1) {

                adapter = new RVAdapter(RecyclerViewActivity.this, siteList);
                mRecyclerView.setAdapter(adapter);


                   adapter.notifyDataSetChanged();

            } else {
                Toast.makeText(RecyclerViewActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray sites = response.optJSONArray("siteInfo");
            siteList = new ArrayList<>();

            for (int i = 0; i < sites.length(); i++) {
                JSONObject post = sites.optJSONObject(i);
                Site item = new Site();
                item.setName(post.optString("name"));
                item.setLat(post.optDouble("lat"));
                item.setLong(post.optDouble("lng"));

                byte[] decodedString = Base64.decode(post.optString("image"), Base64.DEFAULT);
                InputStream inputStream  = new ByteArrayInputStream(decodedString);
                Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
                item.setImage(bitmap);

                siteList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}