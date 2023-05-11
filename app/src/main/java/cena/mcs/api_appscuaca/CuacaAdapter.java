package cena.mcs.api_appscuaca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CuacaAdapter extends RecyclerView.Adapter {
    List<Weather> result;
    Context context;

    public CuacaAdapter(Context context, List<Weather> result) {
        this.context = context;
        this.result = result;
    }

    public CuacaAdapter (Context context){
        this.context = context;
        this.result = new ArrayList<>();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView _day, _weather;
        ImageView _img;

        public MyViewHolder(View itemView) {
            super(itemView);

            _day = itemView.findViewById(R.id.day);
            _weather = itemView.findViewById(R.id.weather);
            _img = itemView.findViewById(R.id.imgDayWeather);

//            img = itemView.findViewById(R.id.pp);
//            img.setImageResource(R.drawable.ic_launcher_background);
        }
    }

    public void setData(List<Weather> result) {
        this.result = result;
    }

    public List<Weather> getData() {
        return result;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.card_cuaca,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder vh = (MyViewHolder) holder;
        Weather res = result.get(position);
//        LocalDate date = LocalDate.parse(res.day);
//        if (vh.getAdapterPosition() == 0){
//            vh._day.setText("Today");
//        } else if (vh.getAdapterPosition() == 1) {
//            vh._day.setText("Tomorrow");
//        } else {
//            String temp = String.valueOf(date.getDayOfWeek());
//            vh._day.setText(String.valueOf(dayFormat(temp)));
//        }
        vh._day.setText(res.day);
        vh._img.setImageResource(Helper.getIcon(Integer.parseInt(res.code)));
        vh._weather.setText(res.weather);
    }

    public String dayFormat(String n){
        String temp = n.substring(0,1) + n.substring(1).toLowerCase();
        return temp;
    }

    @Override
    public int getItemCount() {
        return result.size();
    }
}

