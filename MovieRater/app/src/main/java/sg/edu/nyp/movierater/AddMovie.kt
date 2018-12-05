package sg.edu.nyp.movierater

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_movie.*
import sg.edu.nyp.movierater.MainActivity.Companion.currentMovie
import sg.edu.nyp.movierater.MainActivity.Companion.myMovieList

class AddMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)
    }

    fun addMovieSuitableClick(v: View){
        if (addMovieSuitable.isChecked){
            addMovieSuitableBox.visibility = View.VISIBLE
        } else {
            addMovieSuitableBox.visibility = View.GONE
            addMovieSuitableVio.isChecked = false
            addMovieSuitableLang.isChecked = false
        }
    }

    // Create Option Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_movie, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Select Option Menu Item
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.addMovieAdd){
            var name = addMovieName.text.toString()
            var desc = addMovieDesc.text.toString()
            var date = addMovieDate.text.toString()
            var radioBtn: RadioButton = findViewById(addMovieLang.checkedRadioButtonId)
            var checkRadioBtn = radioBtn.text.toString()
            var result = true

            if (name.isEmpty()){
                addMovieName.setError("Field empty")
                result = false
            }
            if (desc.isEmpty()){
                addMovieDesc.setError("Field empty")
                result = false
            }
            if (date.isEmpty()){
                addMovieDate.setError("Field empty")
                result = false
            }

            var text = ""
            if (result){
                text += "Title = $name"
                text += "\nOverview = $desc"
                text += "\nRelease date = $date"
                text += "\nLanguage = $checkRadioBtn"
                text += "\nSuitable for children below 13 = "
                if (addMovieSuitable.isChecked){
                    text += "No\nReason:"
                    if (addMovieSuitableVio.isChecked)
                        text += "\nViolence"
                    if (addMovieSuitableLang.isChecked)
                        text += "\nLanguage"
                } else {
                    text += "Yes"
                }

                Toast.makeText(this, text, Toast.LENGTH_LONG).show()

                var anyName = Movies(name, desc, checkRadioBtn, date, addMovieSuitable.isChecked, addMovieSuitableVio.isChecked, addMovieSuitableLang.isChecked)
                myMovieList.add(anyName)
                currentMovie = myMovieList.indexOf(anyName)

                var viewMovieIntent = Intent(this, ViewMovie::class.java)
                startActivity(viewMovieIntent)
            }

        } else if (item?.itemId == R.id.addMovieClear){
            addMovieName.text = null
            addMovieDesc.text = null
            addMovieLang.check(R.id.addMovieLangEng)
            addMovieDate.text = null
            if (addMovieSuitable.isChecked){
                addMovieSuitable.performClick()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
