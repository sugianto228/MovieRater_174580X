package sg.edu.nyp.movierater

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_movie.*
import sg.edu.nyp.movierater.MainActivity.Companion.currentMovie
import sg.edu.nyp.movierater.MainActivity.Companion.myMovieList

class EditMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie)

        //Set Value
        editMovieName.setText(myMovieList[currentMovie].Title)
        editMovieDesc.setText(myMovieList[currentMovie].Overview)
        when (myMovieList[currentMovie].Language){
            "English" -> {
                editMovieLangEng.performClick()
            }
            "Chinese" -> {
                editmovieLangChi.performClick()
            }
            "Malay" -> {
                editMovieLangMal.performClick()
            }
            "Tamil" -> {
                editMovieLangTam.performClick()
            }
        }
        editMovieDate.setText(myMovieList[currentMovie].Date)
        if (myMovieList[currentMovie].NotSuitable){
            editMovieSuitable.performClick()
        }
        if (myMovieList[currentMovie].SViolance){
            editMovieSuitableVio.performClick()
        }
        if (myMovieList[currentMovie].SLang){
            editMovieSuitableLang.performClick()
        }
    }

    fun editMovieSuitableClick(v: View){
        if (editMovieSuitable.isChecked){
            editMovieSuitableBox.visibility = View.VISIBLE
        } else {
            editMovieSuitableBox.visibility = View.GONE
            editMovieSuitableVio.isChecked = false
            editMovieSuitableLang.isChecked = false
        }
    }

    // Create Option Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_movie, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Select Option Menu Item
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.editMovieSave){
            // Validation
            var name = editMovieName.text.toString()
            var desc = editMovieDesc.text.toString()
            var date = editMovieDate.text.toString()
            var radioBtn: RadioButton = findViewById(editMovieLang.checkedRadioButtonId)
            var checkRadioBtn = radioBtn.text.toString()
            var result = true

            if (name.isEmpty()){
                editMovieName.setError("Field empty")
                result = false
            }
            if (desc.isEmpty()){
                editMovieDesc.setError("Field empty")
                result = false
            }
            if (date.isEmpty()){
                editMovieDate.setError("Field empty")
                result = false
            }

            if (result){
                myMovieList[currentMovie].updateData(name, desc, checkRadioBtn, date, editMovieSuitable.isChecked, editMovieSuitableVio.isChecked, editMovieSuitableLang.isChecked)
                Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show()

                var viewMovieIntent = Intent(this, ViewMovie::class.java)
                startActivity(viewMovieIntent)
            }

        } else if (item?.itemId == R.id.editMovieCancel){
            super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
