package sg.edu.rp.c346.id20047102.mymovies;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Movie> movieList;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects) {
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        movieList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.tvTitleRow);
        TextView tvGenre = rowView.findViewById(R.id.tvGenreRow);
        TextView tvYear = rowView.findViewById(R.id.tvYearRow);
        ImageView ivRating = rowView.findViewById(R.id.imageViewRating);

        // Obtain the Song information based on the position
        Movie currentMovie = movieList.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentMovie.getmTitle());
        tvGenre.setText(currentMovie.getmGenre());
        tvYear.setText(currentMovie.getmYear());

        String rating = currentMovie.getmRatings();

        if (rating.equalsIgnoreCase("G")) {
            ivRating.setImageResource(R.drawable.rating_g);
            tvTitle.setTextColor(Color.GREEN);
        } else if (rating.equalsIgnoreCase("PG")) {
            ivRating.setImageResource(R.drawable.rating_pg);
            tvTitle.setTextColor(Color.GREEN);
        } else if (rating.equalsIgnoreCase("PG13")) {
            ivRating.setImageResource(R.drawable.rating_pg13);
            tvTitle.setTextColor(Color.BLUE);
        } else if (rating.equalsIgnoreCase("NC16")) {
            ivRating.setImageResource(R.drawable.rating_nc16);
            tvTitle.setTextColor(Color.BLUE);
        } else if (rating.equalsIgnoreCase("M18")) {
            ivRating.setImageResource(R.drawable.rating_m18);
            tvTitle.setTextColor(Color.RED);
        } else {
            ivRating.setImageResource(R.drawable.rating_r21);
            tvTitle.setTextColor(Color.RED);
        }


        return rowView;
    }


}
