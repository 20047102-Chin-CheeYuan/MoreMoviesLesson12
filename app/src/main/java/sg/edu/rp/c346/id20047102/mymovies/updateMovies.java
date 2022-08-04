package sg.edu.rp.c346.id20047102.mymovies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
                // Create the Dialog Builder
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(updateMovies.this);

                // Set the dialog details
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to save the changes");
                myBuilder.setCancelable(false);

                DBHelper dbh = new DBHelper(updateMovies.this);

                myBuilder.setNegativeButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        data.setmTitle(etTitle.getText().toString());
                        data.setmGenre(etGenre.getText().toString());
                        data.setmYear(Integer.parseInt(etYear.getText().toString()));
                        data.setmRatings(spinRating.getSelectedItem().toString());
                        Toast.makeText(updateMovies.this, "Movie updated successfully",
                                Toast.LENGTH_SHORT).show();
                        dbh.updateMovie(data);
                        dbh.close();
                        finish();
                    }
                });
                myBuilder.setPositiveButton("CANCEL", null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the Dialog Builder
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(updateMovies.this);

                // Set the dialog details
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the movie " + etTitle.getText().toString());
                myBuilder.setCancelable(false);

                DBHelper dbh = new DBHelper(updateMovies.this);

                myBuilder.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dbh.deleteMovie(data.getmId());
                        Toast.makeText(updateMovies.this, "The movie " + etTitle.getText().toString() + " has been deleted successfully",
                                Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

                myBuilder.setPositiveButton("CANCEL", null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the Dialog Builder
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(updateMovies.this);

                // Set the dialog details
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes");
                myBuilder.setCancelable(false);

                myBuilder.setNegativeButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(updateMovies.this, "Changes have been discarded",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

                myBuilder.setPositiveButton("DO NOT DISCARD", null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }
}