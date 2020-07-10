package com.example.cocktailtime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CocktailLightAdapter extends RecyclerView.Adapter<CocktailLightAdapter.CocktailLightAdapterVH> implements Filterable {

    private List<CocktailResponseLight> cocktailResponseList;
    private List<CocktailResponseLight> cocktailResponseListFull;
    private Context context;
    private CocktailLightAdapter.ClickedItem clickedItem;



    public CocktailLightAdapter(CocktailLightAdapter.ClickedItem clickedItem) {
        this.clickedItem = clickedItem;

    }

    public void setData(List<CocktailResponseLight> cocktailResponseList){
        this.cocktailResponseList = cocktailResponseList;
        cocktailResponseListFull = new ArrayList<>(cocktailResponseList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CocktailLightAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new CocktailLightAdapter.CocktailLightAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_users, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull CocktailLightAdapter.CocktailLightAdapterVH holder, int position) {
        CocktailResponseLight cocktailResponseLight = cocktailResponseList.get(position);

        String naam = cocktailResponseLight.getNaam();
        int sterkte = cocktailResponseLight.getSterkte();
        String image_location = cocktailResponseLight.getImage_location();



        holder.naam.setText(naam);
        holder.sterkte.setText("Sterkte: " + String.valueOf(sterkte) + "%");

        holder.imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedItem.ClickedUser(cocktailResponseLight);
            }
        });

    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CocktailResponseLight> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(cocktailResponseListFull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(CocktailResponseLight item: cocktailResponseListFull){
                    if(item.getNaam().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cocktailResponseList.clear();
            cocktailResponseList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };



    public interface ClickedItem{
        public void ClickedUser(CocktailResponseLight cocktailResponseLight);
    }

    @Override
    public int getItemCount() {
        return cocktailResponseList.size();
    }

    public class CocktailLightAdapterVH extends RecyclerView.ViewHolder {

        TextView naam;
        TextView sterkte;
        ImageView imageMore;
        ImageView image_location;



        public CocktailLightAdapterVH(@NonNull View itemView) {
            super(itemView);

            naam = itemView.findViewById(R.id.naam);
            sterkte = itemView.findViewById(R.id.sterkte);
            imageMore = itemView.findViewById(R.id.imageMore);
            image_location = itemView.findViewById(R.id.image_location);


        }
    }
}
