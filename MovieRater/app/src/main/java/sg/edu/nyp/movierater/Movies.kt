package sg.edu.nyp.movierater

class Movies(var Title: String,
             var Overview: String,
             var Language: String,
             var Date: String,
             var NotSuitable: Boolean,
             var SViolance: Boolean,
             var SLang: Boolean) {
    var StarNum: Float = 0.toFloat()
    var Review: String = ""

    fun updateData(name: String,
               desc: String,
               lang: String,
               date: String,
               suitable: Boolean,
               sViolence: Boolean,
               sLang: Boolean){
        Title = name
        Overview = desc
        Language = lang
        Date = date
        NotSuitable = suitable
        SViolance = sViolence
        SLang = sLang
    }

    fun updateReviewData(starNum: Float, review: String){
        StarNum = starNum
        Review = review
    }
}