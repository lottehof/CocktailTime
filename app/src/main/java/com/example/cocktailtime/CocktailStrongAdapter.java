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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CocktailStrongAdapter extends RecyclerView.Adapter<CocktailStrongAdapter.CocktailStrongAdapterVH> implements Filterable {

    private List<CocktailResponseStrong> cocktailResponseList;
    private List<CocktailResponseStrong> cocktailResponseListFull;
    private Context context;
    private CocktailStrongAdapter.ClickedItem clickedItem;



    public CocktailStrongAdapter(CocktailStrongAdapter.ClickedItem clickedItem) {
        this.clickedItem = clickedItem;

    }

    public void setData(List<CocktailResponseStrong> cocktailResponseList){
        this.cocktailResponseList = cocktailResponseList;
        cocktailResponseListFull = new ArrayList<>(cocktailResponseList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CocktailStrongAdapter.CocktailStrongAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new CocktailStrongAdapter.CocktailStrongAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_users, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull CocktailStrongAdapter.CocktailStrongAdapterVH holder, int position) {
        CocktailResponseStrong cocktailResponse = cocktailResponseList.get(position);

        String naam = cocktailResponse.getNaam();
        int sterkte = cocktailResponse.getSterkte();
        String image = cocktailResponse.getImage_location();

        Picasso.with(context).load(image).into(holder.image_location);



        holder.naam.setText(naam);
        holder.sterkte.setText("Sterkte: " + String.valueOf(sterkte) + "%");

        holder.imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedItem.ClickedUser(cocktailResponse);
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
            List<CocktailResponseStrong> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(cocktailResponseListFull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(CocktailResponseStrong item: cocktailResponseListFull){
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
        public void ClickedUser(CocktailResponseStrong userResponse);
    }

    @Override
    public int getItemCount() {
        return cocktailResponseList.size();
    }

    public class CocktailStrongAdapterVH extends RecyclerView.ViewHolder {

        TextView naam;
        TextView sterkte;
        ImageView imageMore;
        ImageView image_location;



        public CocktailStrongAdapterVH(@NonNull View itemView) {
            super(itemView);

            naam = itemView.findViewById(R.id.naam);
            sterkte = itemView.findViewById(R.id.sterkte);
            imageMore = itemView.findViewById(R.id.imageMore);
            image_location = itemView.findViewById(R.id.image_location);


        }
    }
}
