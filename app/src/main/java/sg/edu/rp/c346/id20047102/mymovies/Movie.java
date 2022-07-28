package sg.edu.rp.c346.id20047102.mymovies;

import java.io.Serializable;

public class Movie implements Serializable {
    private int mId;
    private String mTitle;
    private String mGenre;
    private int mYear;
    private String mRatings;

    public Movie(int mId, String mTitle, String mGenre, int mYear, String mRatings) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mGenre = mGenre;
        this.mYear = mYear;
        this.mRatings = mRatings;

    }

    public int getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmGenre() {
        return mGenre;
    }

    public void setmGenre(String mGenre) {
        this.mGenre = mGenre;
    }

    public String getmYear() {
        return String.valueOf(mYear);
    }

    public void setmYear(int mYear) {
        this.mYear = mYear;
    }

    public String getmRatings() {
        return mRatings;
    }

    public void setmRatings(String mRatings) {
        this.mRatings = mRatings;
    }
}

