package sg.edu.rp.c346.id20047102.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class showMovies extends AppCompatActivity {

    ListView lvMovies;
    TextView tvSearch;
    Button btnPG13;
    CustomAdapter caMovie;
    ArrayList<Movie> filteredList;
    ArrayList<Movie> sortPG13List;
    HashSet<String> spinnerSet = new HashSet<String>();
    Spinner spinOptRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movies);

        lvMovies = findViewById(R.id.lvMovies);
        tvSearch = findViewById(R.id.textViewGuide);
        spinOptRating = findViewById(R.id.spinOptRating);
        filteredList = new ArrayList<Movie>();
        sortPG13List = new ArrayList<Movie>();

        caMovie = new CustomAdapter(this, R.layout.row, filteredList);
        lvMovies.setAdapter(caMovie);

        DBHelper dbh = new DBHelper(showMovies.this);
        sortPG13List.clear();
        sortPG13List.addAll(dbh.getAllMovies());
        filteredList.addAll(dbh.getAllMovies());
        caMovie.notifyDataSetChanged();

        ArrayList<String> spinnerArray = new ArrayList<String>(spinnerSet);
        spinnerArray.add(0, "All Ratings");
        spinnerArray.add(1, "G");
        spinnerArray.add(2, "PG");
        spinnerArray.add(3, "PG13");
        spinnerArray.add(4, "NC16");
        spinnerArray.add(5, "M18");
        spinnerArray.add(6, "R21");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray
        );

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinOptRating.setAdapter(spinnerAdapter);

        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movie data = filteredList.get(position);
                Intent i = new Intent(showMovies.this,
                        updateMovies.class);
                i.putExtra("data", data);
                startActivity(i);

            }
        });


        spinOptRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filteredList.clear();
                if (position != 0) {
                    for (Movie element : sortPG13List
                    ) {
                        if (String.valueOf(element.getmRatings()).equals(spinnerArray.get(position))) {
                            tvSearch.setText("You have chosen the " + element.getmRatings() + " Rating!");
                            filteredList.add(element);
                        }
                    }
                } else {
                    tvSearch.setText("Choose the Rating that you desire below");
                    filteredList.addAll(sortPG13List);
                }

                caMovie.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(showMovies.this);
        sortPG13List.clear();
        filteredList.clear();
        sortPG13List.addAll(dbh.getAllMovies());
        filteredList.addAll(sortPG13List);
        caMovie.notifyDataSetChanged();
    }


}