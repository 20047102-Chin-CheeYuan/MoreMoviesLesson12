package sg.edu.rp.c346.id20047102.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class showMovies extends AppCompatActivity {

    ListView lvMovies;
    Button btnPG13;
    CustomAdapter caMovie;
    ArrayList<Movie> filteredList;
    ArrayList<Movie> sortPG13List;
    Boolean state = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movies);

        lvMovies = findViewById(R.id.lvMovies);
        btnPG13 = findViewById(R.id.btnPG13);

        filteredList = new ArrayList<Movie>();
        sortPG13List = new ArrayList<Movie>();

        caMovie = new CustomAdapter(this, R.layout.row, filteredList);
        lvMovies.setAdapter(caMovie);

        DBHelper dbh = new DBHelper(showMovies.this);
        sortPG13List.clear();
        sortPG13List.addAll(dbh.getAllMovies());
        filteredList.addAll(dbh.getAllMovies());
        caMovie.notifyDataSetChanged();

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

        btnPG13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = !state;
                filteredList.clear();
                if (state) {
                    btnPG13.setText("DISPLAY ALL MOVIES");
                    for (Movie element : sortPG13List
                    ) {
                        if (element.getmRatings().equals("PG13")) {
                            filteredList.add(element);
                        }
                    }
                } else {
                    btnPG13.setText("SHOW ALL PG-13 MOVIES");
                    filteredList.addAll(sortPG13List);
                }

                caMovie.notifyDataSetChanged();
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