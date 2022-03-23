package com.readalong

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.instruction.SubjectInstructionScreen


class StudentGoogleReadAlongDisclaimer :AppCompatActivity(){

    private lateinit var proceed_to_assessment_playstore: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.google_bolo_disclaimer)
        proceed_to_assessment_playstore = findViewById<Button>(R.id.proceed_to_assessment_playstore)
    }

    override fun onResume() {
        super.onResume()
  /*      close_cross_bolo_screen.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(activityContext).edit().remove("studentAssessmentFlowStatus").apply()
            EventBus.getDefault().postSticky(BackEvent())
            finish()
        }
*/
       if(!appInstalledOrNot(packageManager, "com.google.android.apps.seekh")) {
//           reading_student.visibility = View.GONE
          /* lakshaya_title1.text = "आकलन के लिए आपको अपने मोबाइल में 'Google Read Along' Application डाउनलोड करना पड़ेगा Google PlayStore से।\nडाउनलोड करने पे, आकलन शुरू करने से पहले सुनिश्चित करें:\n"
           val sourceString = "1) Google PlayStore से 'Read Along' ऐप डाउनलोड करें। <br/>\n" +
                   "2) 'Read Along' ऐप खुलने पर यदि  <b> 'Audio' और 'Video' permissions </b> मांगे तो 'Allow' पर क्लिक करें। <br/>\n" +
                   "3) 'Read Along' ऐप की <b> भाषा को अंग्रेजी से हिंदी में बदलें। </b> ('Read Along' के पहले पेज पर बाईं ओर बटन दबाने पर यह विकल्प मिलेगा) <br/> \n" +
                   "4) 'Read Along' ऐप पर <b> 'upprerna' पार्टनर कोड </b> डालें। ('Read Along' के पहले पेज पर बाईं ओर बटन दबाने पर यह विकल्प मिलेगा) <br/>"
           if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
               lakshaya_title11.text = Html.fromHtml(sourceString, Html.FROM_HTML_MODE_LEGACY)
           }else{
               lakshaya_title11.text = Html.fromHtml(sourceString)
           }
           bolo_logo.visibility = View.VISIBLE
           Glide.with(activityContext).load(R.drawable.mobile_install)
                   .centerCrop()
                   .into(bolo_logo)
          */ proceed_to_assessment_playstore.text = "PlayStore ले जाएँ"
           proceed_to_assessment_playstore.setOnClickListener {
               val uri: Uri = Uri.parse("market://details?id=com.google.android.apps.seekh")
               val goToMarket = Intent(Intent.ACTION_VIEW, uri)
               // To count with Play market back-stack, After pressing back button,
               // to taken back to our application, we need to add following flags to intent.
               goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or
                       Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                       Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
               try {
                   startActivity(goToMarket)
               } catch (e: ActivityNotFoundException) {
                   startActivity(Intent(Intent.ACTION_VIEW,
                           Uri.parse("http://play.google.com/store/apps/details?id=com.google.android.apps.seekh")))
               }
           }
       }else{
//           lakshaya_title1.text = "भाषा के आकलन के लिए 'Read Along' का इस्तेमाल किया जाएगा। \nआकलन शुरू करने से पहले सुनिश्चित करें:"
//
//           val sourceString = "1) Google PlayStore से 'Read Along' ऐप डाउनलोड करें। <br/>\n" +
//                   "2) 'Read Along' ऐप खुलने पर यदि  <b> 'Audio' और 'Video' permissions </b> मांगे तो 'Allow' पर क्लिक करें। <br/>\n" +
//                   "3) 'Read Along' ऐप की <b> भाषा को अंग्रेजी से हिंदी में बदलें। </b> ('Read Along' के पहले पेज पर बाईं ओर बटन दबाने पर यह विकल्प मिलेगा) <br/> \n" +
//                   "4) 'Read Along' ऐप पर <b> 'upprerna' पार्टनर कोड </b> डालें। ('Read Along' के पहले पेज पर बाईं ओर बटन दबाने पर यह विकल्प मिलेगा) <br/>"
//           if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//               lakshaya_title11.text = Html.fromHtml(sourceString, Html.FROM_HTML_MODE_LEGACY)
//           }else{
//               lakshaya_title11.text = Html.fromHtml(sourceString)
//           }
//           reading_student.visibility = View.VISIBLE
//           bolo_logo.visibility = View.GONE
           proceed_to_assessment_playstore.text = "आकलन शुरू करें"
           proceed_to_assessment_playstore.setOnClickListener {
               val intent = Intent(this, SubjectInstructionScreen::class.java)
               intent.putExtra("grade", intent.getIntExtra("grade", 0))
               intent.putExtra("selectedSubject", intent.getStringExtra("selectedSubject"))
               intent.putExtra("profile", "Student")
               startActivity(intent)
               finish()
           }
       }
        /*go_back_home_summary_bolo.setOnClickListener {
            PreferenceManager.getDefaultSharedPreferences(activityContext).edit().remove("studentAssessmentFlowStatus").apply()
            EventBus.getDefault().postSticky(BackEvent())
            finish()
        }*/
    }

    /*override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            PreferenceManager.getDefaultSharedPreferences(activityContext).edit().remove("studentAssessmentFlowStatus").apply()
            EventBus.getDefault().postSticky(BackEvent())
            finish()
            true
        } else super.onKeyDown(keyCode, event)
    }
*/
    fun appInstalledOrNot(pm: PackageManager, uri: String?): Boolean {
        return try {
            pm.getPackageInfo(uri!!, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}