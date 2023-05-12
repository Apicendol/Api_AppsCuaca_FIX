package cena.mcs.api_appscuaca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CuacaAdapter extends RecyclerView.Adapter {
    List<Weather> result;
    Context context;

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

        }
    }

    public void setData(List<Weather> result) {
        this.result = result;
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
        vh._day.setText(DateSet.formattedDate(res.getDay()));
        vh._img.setImageResource(res.getIcon());
        vh._weather.setText(Helper.getCode(res.getCode()));
    }

    @Override
    public int getItemCount() {
        return result.size();
    }
}

