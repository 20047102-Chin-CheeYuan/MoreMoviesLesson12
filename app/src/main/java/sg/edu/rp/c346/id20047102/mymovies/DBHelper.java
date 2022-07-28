package sg.edu.rp.c346.id20047102.mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyMovies.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "Movies";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATING = "rating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createMovieTableSql = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_GENRE + " TEXT,"
                + COLUMN_YEAR + " INTEGER,"
                + COLUMN_RATING + " TEXT)";

        db.execSQL(createMovieTableSql);
        Log.i("info", "created tables");

        //Dummy records, to be inserted when the database is created
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, "Titanic");
        values.put(COLUMN_GENRE, "Romance");
        values.put(COLUMN_YEAR, "2003");
        values.put(COLUMN_RATING, "PG");
        db.insert(TABLE_NAME, null, values);
        Log.i("info", "dummy records inserted");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> movies = new ArrayList<Movie>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        Cursor cursor = db.query(TABLE_NAME, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int mID = cursor.getInt(0);
                String mTitle = cursor.getString(1);
                String mGenre = cursor.getString(2);
                int mYear = cursor.getInt(3);
                String mRating = cursor.getString(4);
                Movie movie = new Movie(mID, mTitle, mGenre, mYear, mRating);
                movies.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movies;
    }

    public long insertMovie(String title, String genre, int year, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_GENRE, genre);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RATING, rating);
        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1) {
            Log.d("DBHelper", "Insert failed");
        }
        db.close();
        Log.d("SQL Insert", "ID:" + result); //id returned, should not be -1
        return result;
    }

    public int updateMovie(Movie data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getmTitle());
        values.put(COLUMN_GENRE, data.getmGenre());
        values.put(COLUMN_YEAR, data.getmYear());
        values.put(COLUMN_RATING, data.getmRatings());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getmId())};
        int result = db.update(TABLE_NAME, values, condition, args);
        if (result < 1) {
            Log.d("DBHelper", "Update failed");
        }
        db.close();
        return result;
    }

    public int deleteMovie(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_NAME, condition, args);
        if (result < 1) {
            Log.d("DBHelper", "Update failed");
        }
        db.close();
        return result;
    }
}