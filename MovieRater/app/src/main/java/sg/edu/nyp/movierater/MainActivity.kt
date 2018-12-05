package sg.edu.nyp.movierater

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        var myMovieList = arrayListOf<Movies>()
        var currentMovie: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (myMovieList.isEmpty()){
            mainActivityRelativeLayout.visibility = View.VISIBLE
        } else {
            var mainLinearLayout = findViewById<LinearLayout>(R.id.mainActivityLinearLayout)
            for (i in myMovieList.indices){
                var linearLayout = LinearLayout(this)
                linearLayout.setLayoutParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
                linearLayout.orientation = LinearLayout.HORIZONTAL

                var imageView = ImageView(this)
                imageView.setImageResource(R.drawable.camera)
                imageView.setLayoutParams(ViewGroup.LayoutParams(150, 150))
                linearLayout?.addView(imageView)

                var textView = TextView(this)
                textView.setLayoutParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
                textView.gravity = Gravity.CENTER
                textView.setText(myMovieList[i].Title)
                textView.setTextSize(17.toFloat())
                textView.setMinHeight(150)
                textView.setId(i)
                linearLayout?.addView(textView)
                registerForContextMenu(textView)

                mainLinearLayout?.addView(linearLayout)

                var view = View(this)
                view.setLayoutParams(ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 3))
                view.setBackgroundColor(Color.parseColor("#CACBCA"))

                mainLinearLayout?.addView(view)
            }
        }
        registerForContextMenu(mainActivityText)
    }

    // Create Context Menu
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (myMovieList.isNotEmpty()){
            for (i in myMovieList.indices){
                if (v?.id == i){
                    menu?.add(1, 999,1,"Edit")
                }
            }
        } else {
            if (v?.id == R.id.mainActivityText){
                menu?.add(1, 1001, 1, "Add")
            }
        }
    }

    // Select Context Menu Item
    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == 1001){
            var addMovieIntent = Intent(this, AddMovie::class.java)
            startActivity(addMovieIntent)
        } else if (item?.itemId == 999){
            // GO TO EDIT MOVIE PAGE!!! LEFT THIS NOT DONE
        }
        return super.onContextItemSelected(item)
    }

    // Create Option Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (myMovieList.isNotEmpty()){
            menuInflater.inflate(R.menu.main, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    // Select Option Menu Item
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.mainActivityAdd){
            var addMovieIntent = Intent(this, AddMovie::class.java)
            startActivity(addMovieIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}
