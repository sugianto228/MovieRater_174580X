package sg.edu.nyp.movierater

class Movies(var Title: String,
             var Overview: String,
             var Language: String,
             var Date: String,
             var Suitable: Boolean,
             var SViolance: Boolean,
             var SLang: Boolean) {
    var StarNum = 0
    var Review = ""

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
        Suitable = suitable
        SViolance = sViolence
        SLang = sLang
    }

    fun updateReviewData(starNum: Int, review: String){
        StarNum = starNum
        Review = review
    }
}