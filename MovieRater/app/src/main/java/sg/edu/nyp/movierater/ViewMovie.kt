package sg.edu.nyp.movierater

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_view_movie.*

class ViewMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_movie)

        registerForContextMenu(viewMovieNoReviews)
    }

    // Create Context Menu
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (v?.id == R.id.viewMovieNoReviews){
            menu?.add(1, 1001, 1, "Add Review")
        }
    }

    val SET_RATE_MOVIE = 111
    // Select Context Menu Item
    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == 1001){
            var rateMovieIntent = Intent(this, RateMovie::class.java)
            rateMovieIntent.putExtra("movieName", viewMovieTitle.text.toString())
            startActivityForResult(rateMovieIntent, SET_RATE_MOVIE)
        }
        return super.onContextItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SET_RATE_MOVIE){
            if (resultCode == RESULT_OK){
                var comment = data?.getStringExtra(RateMovie.COMMENT)
                var starsNum = data?.getStringExtra(RateMovie.STARS)

                viewMovieStar.rating = starsNum!!.toFloat()
                viewMovieStar.visibility = View.VISIBLE
                viewMovieReviews.text = comment
                viewMovieReviews.visibility = View.VISIBLE
                viewMovieNoReviews.visibility = View.GONE
            }
        }
}
}
