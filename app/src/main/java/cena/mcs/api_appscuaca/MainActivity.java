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

    private List<Weather> weatherList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CuacaAdapter adapter;
    String w, type, test;
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
//        rvWeather = findViewById(R.id.rvWeatherDay);

        recyclerView = findViewById(R.id.recyclerWeather);
        adapter = new CuacaAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        new WeatherTask().execute();
    }

    public String castWheater(int n){
        switch (n){
            case 0:
                w = "Clear Sky";
                test = "img_sun";
                break;
            case 1:
                w = "Mainly Clear";
                break;
            case 2:
                w = "Party Cloudy";
                break;
            case 3:
                w = "Overcast";
                break;
            case 45:
                w = "Fog";
                break;
            case 48:
                w = "Fog";
                break;
            case 51:
                w = "Drizzle";
                break;
            case 53:
                w = "Drizzle";
                break;
            case 55:
                w = "Drizzle";
                break;
            case 56:
                w = "Freezing Drizzle";
                type = "Light";
                break;
            case 57:
                w = "Freezing Drizzle";
                type = "Dense Intensity";
                break;
            case 61:
                w = "Rain";
                type = "Light";
                break;
            case 63:
                w = "Rain";
                type = "Moderate";
                break;
            case 65:
                w = "Rain";
                type = "Heavy Intensity";
                break;
            case 66:
                w = "Freezing Rain";
                type = "Light";
                break;
            case 67:
                w = "Freezing Rain";
                type = "Heavy Intensity";
                break;
            case 71:
                w = "Snow";
                type = "Slight";
                break;
            case 73:
                w = "Snow";
                type = "Moderate";
                break;
            case 75:
                w = "Snow";
                type = "Heavy Intensity";
                break;
            case 77:
                w = "Snow Grains";
                break;
            case 80:
                w = "Rain Showers";
                type = "Slight";
                break;
            case 81:
                w = "Rain Showers";
                type = "Moderate";
                break;
            case 82:
                w = "Rain Showers";
                type = "Violent";
                break;
            case 85:
                w = "Snow Showers";
                type = "Slight";
                break;
            case 86:
                w = "Snow Showers";
                type = "Heavy";
                break;
            case 95:
                w = "Thunderstorm";
                type = "Moderate";
                break;
            case 96:
                w = "Thunderstorm";
                type = "Slight";
                break;
            case 99:
                w = "Thunderstorm";
                type = "Heavy Hail";
                break;
        }
        return w;
    }

//    public void initData() {
//        adapter.setData(weatherList);
//        adapter.notifyDataSetChanged();
//    }

//    private class WeatherTask extends AsyncTask<Void, Void, List<Weather>> {
//        @Override
//        protected List<Weather> doInBackground(Void... voids) {
//            String url = "https://api.open-meteo.com/v1/forecast?latitude=-7.98&longitude=112.63&daily=weathercode&current_weather=true&timezone=auto";
//            try {
//                URL apiUrl = new URL(url);
//                HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
//                connection.setRequestMethod("GET");
//
//                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                StringBuilder stringBuilder = new StringBuilder();
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    stringBuilder.append(line);
//                }
//                reader.close();
//                connection.disconnect();
//
//                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
//                JSONObject daily = jsonObject.getJSONObject("daily");
//                JSONArray timeArray = daily.getJSONArray("time");
//                JSONArray weatherCodeArray = daily.getJSONArray("weathercode");
//
////                ArrayList<String> weatherDataList = new ArrayList<>();
//
//                for (int i = 0; i < timeArray.length(); i++) {
//                    String time = timeArray.getString(i);
//                    String weathercode = weatherCodeArray.getString(i);
////                    int weathercode = weatherCodeArray.getInt(i);
//                    castWheater(Integer.parseInt(weathercode));
//                    weatherList.add(new Weather(time, w, weathercode));
//                }
////                initData();
//
//                JSONObject today = jsonObject.getJSONObject("current_weather");
//                String temp = today.getString("weathercode");
//                int wCodeToday = Integer.parseInt(today.getString("weathercode"));
//                temperature.setText(today.getString("temperature"));
//                kondisi.setText(castWheater(Integer.parseInt(temp)));
//                windspeed.setText(today.getString("windspeed"));
//                imgWeather.setImageResource(Helper.getIcon(wCodeToday));
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return weatherList;
//        }
//
//        @Override
//        protected void onPostExecute(List<Weather> weather) {
//            super.onPostExecute(weatherList);
//            adapter.setData(weatherList);
//            adapter.notifyDataSetChanged();
//        }
//    }
//}

    private class WeatherTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder result = new StringBuilder();
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL("https://api.open-meteo.com/v1/forecast?latitude=-7.98&longitude=112.63&daily=weathercode&current_weather=true&timezone=auto");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line).append('\n');
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }

            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            try {
//                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONObject jsonObject = new JSONObject(result);
                JSONObject daily = jsonObject.getJSONObject("daily");
                JSONArray timeArray = daily.getJSONArray("time");
                JSONArray weatherCodeArray = daily.getJSONArray("weathercode");

//                ArrayList<String> weatherDataList = new ArrayList<>();

                for (int i = 0; i < timeArray.length(); i++) {
                    String time = timeArray.getString(i);
                    String weathercode = weatherCodeArray.getString(i);
                    castWheater(Integer.parseInt(weathercode));
                    weatherList.add(new Weather(time, w, weathercode));
                }
                initData();

//                JSONObject jsonObject = new JSONObject(result);
                JSONObject currentWeatherObject = jsonObject.getJSONObject("current_weather");

//                JSONObject daily = jsonObject.getJSONObject("daily");
//                JSONArray timeArray = daily.getJSONArray("time");
//                JSONArray weatherCodeArray = daily.getJSONArray("weathercode");

//                ArrayList<String> weatherDataList = new ArrayList<>();

//                for (int i = 0; i < timeArray.length(); i++) {
//                    String time = timeArray.getString(i);
//                    int weatherCode = weatherCodeArray.getInt(i);
//                    weatherDataList.add(time + ", " + weatherCode);
//                }

//                StringBuilder weatherData = new StringBuilder();
//                for (String data : weatherDataList) {
//                    weatherData.append(data).append("\n");
//                }
//
//                kondisi.setText(weatherData);

//                int weatherCode = currentWeatherObject.getInt("weathercode");
//                double temperature = currentWeatherObject.getDouble("temperature");
//                double windSpeed = currentWeatherObject.getDouble("windspeed");
//                double latitude = jsonObject.getDouble("latitude");
//                double longitude = jsonObject.getDouble("longitude");
//
//                String weatherInfo = "Kondisi Cuaca: " + kondisi + "\n"
//                        + "Temperature: " + temperature + "°C\n"
//                        + "Kecepatan Angin: " + windSpeed + " km/h\n"
//                        + "Koordinat: " + latitude + ", " + longitude;

                String temp = currentWeatherObject.getString("weathercode");
                int wCodeToday = Integer.parseInt(currentWeatherObject.getString("weathercode"));
                temperature.setText(currentWeatherObject.getString("temperature"));
                kondisi.setText(castWheater(Integer.parseInt(temp)));
                windspeed.setText(currentWeatherObject.getString("windspeed"));
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