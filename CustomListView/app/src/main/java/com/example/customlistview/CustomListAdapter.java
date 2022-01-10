package com.example.customlistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private List<Country> listCountry;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context context, List<Country> listCountry) {
        this.listCountry = listCountry;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return listCountry.size();
    }

    @Override
    public Object getItem(int i) {
        return listCountry.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.custom_list_layout, null);
            holder = new Viewholder();
            holder.flagView = (ImageView) view.findViewById(R.id.image_flag);
            holder.nameView = (TextView) view.findViewById(R.id.textView_name);
            holder.populationView = (TextView) view.findViewById(R.id.textView_population);
            view.setTag(holder);
        }else {
            holder = (Viewholder) view.getTag();
        }
        Country country = this.listCountry.get(i);
        holder.nameView.setText(country.getName());
        holder.populationView.setText("Population: " + country.getPopulation());
        int imageId = this.getMimapResId(country.getFlag());
        holder.flagView.setImageResource(imageId);
        return view;
    }

    public int getMimapResId(String resName) {
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(resName, "mipmap", pkgName);
        Log.i("CustomListView", "Res name: " + resName + "==> Res ID = " + resID);
        return resID;
    }

    static class  Viewholder{
        ImageView flagView;
        TextView nameView;
        TextView populationView;
    }
}
