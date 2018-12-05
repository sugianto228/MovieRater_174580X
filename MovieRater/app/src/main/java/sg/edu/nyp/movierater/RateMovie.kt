package sg.edu.nyp.movierater

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_rate_movie.*
import sg.edu.nyp.movierater.MainActivity.Companion.currentMovie
import sg.edu.nyp.movierater.MainActivity.Companion.myMovieList

class RateMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_movie)

        var movieName = intent.getStringExtra("movieName")
        rateMovieName.text = rateMovieName.text.toString() + movieName
    }

    // Create Option Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.rate_movie, menu)
        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        val COMMENT = "comment"
        val STARS = "stars"
    }

    // Select Option Menu Item
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.rateMovieSubmit){
            var output = Intent()
            output.putExtra(COMMENT, rateMovieComment.text.toString())
            output.putExtra(STARS, rateMovieStars.rating.toString())

            myMovieList[currentMovie].updateReviewData(rateMovieStars.rating, rateMovieComment.text.toString())

            setResult(Activity.RESULT_OK, output)

            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
