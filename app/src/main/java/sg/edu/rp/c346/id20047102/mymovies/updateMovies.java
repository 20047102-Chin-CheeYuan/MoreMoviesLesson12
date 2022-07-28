package sg.edu.rp.c346.id20047102.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class updateMovies extends AppCompatActivity {

    Movie data;
    EditText etId, etTitle, etGenre, etYear;
    Spinner spinRating;
    Button btnUpdate, btnDelete, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_movies);

        etId = findViewById(R.id.editTextID);
        etTitle = findViewById(R.id.editTextUpdateTitle);
        etGenre = findViewById(R.id.editTextUpdateGenre);
        etYear = findViewById(R.id.editTextUpdateYear);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        spinRating = findViewById(R.id.spinnerUpdateRating);

        Intent i = getIntent();
        data = (Movie) i.getSerializableExtra("data");

        String id = String.valueOf(data.getmId());

        etId.setText(id);
        etTitle.setText(data.getmTitle());
        etGenre.setText(data.getmGenre());
        etYear.setText(data.getmYear());

        for (int x = 0; x < spinRating.getCount(); x++) {
            if (spinRating.getItemAtPosition(x).toString().equalsIgnoreCase(data.getmRatings())) {
                spinRating.setSelection(x);
            }
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbh = new DBHelper(updateMovies.this);
                data.setmTitle(etTitle.getText().toString());
                data.setmGenre(etGenre.getText().toString());
                data.setmYear(Integer.parseInt(etYear.getText().toString()));
                data.setmRatings(spinRating.getSelectedItem().toString());

                dbh.updateMovie(data);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(updateMovies.this);
                dbh.deleteMovie(data.getmId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}