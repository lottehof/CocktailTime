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

public class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.CocktailAdapterVH> implements Filterable {

    private List<CocktailResponse> cocktailResponseList;
    private List<CocktailResponse> cocktailResponseListFull;
    private Context context;
    private ClickedItem clickedItem;



    public CocktailAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;

    }

    public void setData(List<CocktailResponse> cocktailResponseList){
        this.cocktailResponseList = cocktailResponseList;
        cocktailResponseListFull = new ArrayList<>(cocktailResponseList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CocktailAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new CocktailAdapter.CocktailAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_users, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull CocktailAdapterVH holder, int position) {
        CocktailResponse cocktailResponse = cocktailResponseList.get(position);

        String naam = cocktailResponse.getNaam();
        int sterkte = cocktailResponse.getSterkte();
        String image = cocktailResponse.getImage_location();





        holder.naam.setText(naam);
        holder.sterkte.setText("Sterkte: " + String.valueOf(sterkte) + "%");
        Picasso.with(context).load(image).into(holder.image_location);

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
            List<CocktailResponse> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(cocktailResponseListFull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(CocktailResponse item: cocktailResponseListFull){
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
        public void ClickedUser(CocktailResponse cocktailResponse);
    }

    @Override
    public int getItemCount() {
        return cocktailResponseList.size();
    }

    public class CocktailAdapterVH extends RecyclerView.ViewHolder {

        TextView naam;
        TextView sterkte;
        ImageView imageMore;
        ImageView image_location;



        public CocktailAdapterVH(@NonNull View itemView) {
            super(itemView);

            naam = itemView.findViewById(R.id.naam);
            sterkte = itemView.findViewById(R.id.sterkte);
            imageMore = itemView.findViewById(R.id.imageMore);
            image_location = itemView.findViewById(R.id.image_location);


        }
    }
}