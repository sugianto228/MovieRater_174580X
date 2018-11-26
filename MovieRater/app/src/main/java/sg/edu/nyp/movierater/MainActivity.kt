package sg.edu.nyp.movierater

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.landing_page.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_movie.*
import kotlinx.android.synthetic.main.rate_movie.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_page)

        registerForContextMenu(tvLandingPageText)
    }

    // Create Context Menu
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (v?.id == R.id.tvLandingPageText){
            menu?.add(1, 1001, 1, "Add")
        }
    }

    // When Context Menu item selected
    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == 1001){
            setContentView(R.layout.activity_main)
        }
        return super.onContextItemSelected(item)
    }

    fun cBMasterClicked(v: View){
        if (cBMaster.isChecked){
            cBSwitch.visibility = View.VISIBLE
        } else {
            cBSwitch.visibility = View.INVISIBLE
            cBViolence.isChecked = false
            cBLanguage.isChecked = false
        }
    }

    fun btnAddMovie(v: View){
        var name = eTName.text.toString()
        var desc = eTDesc.text.toString()
        var date = eTDate.text.toString()
        var checkRadioBtn:RadioButton = findViewById(rGMaster.checkedRadioButtonId)
        var result = true

        if(name.isEmpty()){
            eTName.setError("Field empty")
            result = false
        }
        if(desc.isEmpty()){
            eTDesc.setError("Field empty")
            result = false
        }
        if(date.isEmpty()){
            eTDate.setError("Field empty")
            result = false
        }

        var text = ""
        if(result){
            text += "Title = $name"
            text += "\nOverview = $desc"
            text += "\nRelease date = $date"
            text += "\nLanguage = "+checkRadioBtn.text.toString()
            text += "\nSuitable for children below 13 = "+!(cBMaster.isChecked)
            if(cBMaster.isChecked){
                text += "\nReason:"
                if(cBViolence.isChecked)
                    text += "\nViolence"
                if(cBLanguage.isChecked)
                    text += "\nLanguage"
            }

            Toast.makeText(this, text, Toast.LENGTH_LONG).show()

            setContentView(R.layout.view_movie)

            // Setting value to view_movie.xml
            tVName.text = name
            tVDesc.text = desc
            tVLang.text = checkRadioBtn.text.toString()
            tVDate.text = date
            if(cBMaster.isChecked)
                tVSuitableFC.text = "No"
            else
                tVSuitableFC.text = "Yes"

        }
    }

    fun btnRateMovie(v: View){
        var name = tVName.text.toString()

        setContentView(R.layout.rate_movie)

        // Setting value to rate_movie.xml
        tVReviewName.text = tVReviewName.text.toString() + name
    }

}
