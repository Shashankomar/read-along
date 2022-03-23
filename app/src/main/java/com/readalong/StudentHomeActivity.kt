package com.readalong

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.instruction.SubjectInstructionScreen

class StudentHomeActivity : AppCompatActivity() {

    private val profile: String = "Student"
    private val previousSelectedSubject: String = "Hindi"
    private val previousSelectedGrade: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prernalakshya_layout_studenthome)

        val takeAssessmentButton = findViewById<Button>(R.id.takeAssessmentButton)
        takeAssessmentButton.setOnClickListener(
            { view: android.view.View? ->
                if (previousSelectedGrade < 1 || (previousSelectedSubject == "")) {
                    Toast.makeText(
                        this,
                        "कृपया छात्र का अभ्यास शुरू करने के लिए पहले छात्र की कक्षा एवं विषय का चयन करें।",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
//        studentHomePresenter.getMvpInteractor().getPreferenceHelper()
//            .updateStudentGrade(previousSelectedGrade)
//        Grove.d("Launching Instructions screens for students for grade $previousSelectedGrade with subjects: $previousSelectedSubject")
                    if (!appInstalledOrNot(getPackageManager(), "com.google.android.apps.seekh")
                    ) {
                        val intent = Intent(this, StudentGoogleReadAlongDisclaimer::class.java)
                        intent.putExtra("grade", previousSelectedGrade)
                        intent.putExtra("selectedSubject", previousSelectedSubject)
                        intent.putExtra(
                            "profile",
                            profile
                        )
                        startActivity(intent)
                    } else {
                        val intent = Intent(this, SubjectInstructionScreen::class.java)
                        intent.putExtra("grade", previousSelectedGrade)
                        intent.putExtra(
                            "profile",
                            profile
                        )
                        intent.putExtra("selectedSubject", previousSelectedSubject)
                        startActivity(intent)
                    }
                }
            }
        )

    }


    fun appInstalledOrNot(pm: PackageManager, uri: String?): Boolean {
        return try {
            pm.getPackageInfo(uri!!, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}