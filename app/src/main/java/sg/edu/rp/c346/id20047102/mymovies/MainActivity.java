package sg.edu.rp.c346.id20047102.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etGenre, etYear;
    Button btnInsert, btnShowList;
    Spinner spinRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.editTextTitle);
        etGenre = findViewById(R.id.editTextGenre);
        etYear = findViewById(R.id.editTextYear);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        spinRating = findViewById(R.id.spinnerRating);

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        showMovies.class);
                startActivity(i);

            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String genre = etGenre.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                String rating = spinRating.getSelectedItem().toString();

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertMovie(title, genre, year, rating);

                if (inserted_id != -1) {
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }

                etTitle.setText("");
                etGenre.setText("");
                etYear.setText("");
                spinRating.setSelection(0);
            }

        });

    }
}