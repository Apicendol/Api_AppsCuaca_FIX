package cena.mcs.api_appscuaca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Weather> weatherList = new ArrayList<>();
    RecyclerView recyclerView;
    CuacaAdapter adapter;
    String w;
    TextView coor, kondisi, temperature, windspeed;
    ImageView imgWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coor = findViewById(R.id.coordinate);
        kondisi = findViewById(R.id.condition);
        temperature = findViewById(R.id.temperature);
        imgWeather = findViewById(R.id.weatherImg);
        windspeed = findViewById(R.id.wind);

        recyclerView = findViewById(R.id.recyclerWeather);
        adapter = new CuacaAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        new WeatherTask().execute();
    }

    private class WeatherTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder result = new StringBuilder();
            HttpURLConnection connection;
            BufferedReader reader;
            try {
                URL url = new URL("https://api.open-meteo.com/v1/forecast?latitude=-7.98&longitude=112.63&daily=weathercode&current_weather=true&timezone=auto");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line).append('\n');
                }
                reader.close();
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject daily = jsonObject.getJSONObject("daily");
                JSONArray timeArray = daily.getJSONArray("time");
                JSONArray weatherCodeArray = daily.getJSONArray("weathercode");

                for (int i = 0; i < timeArray.length(); i++) {
                    String time = timeArray.getString(i);
                    int code = weatherCodeArray.getInt(i);
                    int icon = Helper.getIcon(code);
                    Weather cuaca = new Weather(time, code, icon);
                    weatherList.add(cuaca);
                }
                initData();

                JSONObject currentWeatherObject = jsonObject.getJSONObject("current_weather");
                int temp = currentWeatherObject.getInt("weathercode");
                int wCodeToday = Integer.parseInt(currentWeatherObject.getString("weathercode"));
                temperature.setText("Temperatur : " + currentWeatherObject.getString("temperature") + " °C");
                kondisi.setText(Helper.getCode(temp));
                windspeed.setText("Kecepatan Angin : " + currentWeatherObject.getString("windspeed") + " KM/H");
                imgWeather.setImageResource(Helper.getIcon(wCodeToday));
                double latitude = jsonObject.getDouble("latitude");
                double longitude = jsonObject.getDouble("longitude");
                coor.setText(latitude + " " + longitude);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void initData() {
        adapter.setData(weatherList);
        adapter.notifyDataSetChanged();
    }
}