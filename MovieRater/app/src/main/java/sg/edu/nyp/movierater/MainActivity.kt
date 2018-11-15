package sg.edu.nyp.movierater

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cBMaster.setOnClickListener {
            if (cBMaster.isChecked == true){
                cBSwitch.visibility = View.VISIBLE
            } else {
                cBSwitch.visibility = View.INVISIBLE
                cBViolence.isChecked = false
                cBLanguage.isChecked = false
            }
        }

    }

    fun btnAddMovie(v: View){
        var name = eTName.text.toString()
        var desc = eTDesc.text.toString()
        var date = eTDate.text.toString()
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
            text += "Title = "+name
            text += "\nOverview = "+desc
            text += "\nRelease date = "+date
            text += "\nLanguage = "
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
        }
    }

    fun btnRateMovie(v: View){
        setContentView(R.layout.rate_movie)
    }

}
