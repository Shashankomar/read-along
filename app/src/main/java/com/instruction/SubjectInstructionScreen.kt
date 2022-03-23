package com.instruction

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.GradeStatus
import com.HomeLearningProgressObject
import com.StartLearningEventData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.readalong.databinding.MpcLayoutSubjectInstructionBinding
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
open class SubjectInstructionScreen : AppCompatActivity(){

    private var subject: String = "Hindi"
    private var grade: Int = 1
    private var grade1Sample = 1
    private var grade2Sample = 0
    private var grade3Sample = 0
    private var grade4Sample = 0
    private var grade5Sample = 0
    private var hindiGrade1List = ArrayList<Int>()
    private var mathGrade1List = ArrayList<Int>()
    private var hindiGrade2List = ArrayList<Int>()
    private var mathGrade2List = ArrayList<Int>()
    private var hindiGrade3List = ArrayList<Int>()
    private var mathGrade3List = ArrayList<Int>()
    private var hindiGrade4List = ArrayList<Int>()
    private var mathGrade4List = ArrayList<Int>()
    private var hindiGrade5List = ArrayList<Int>()
    private var mathGrade5List = ArrayList<Int>()
    private var strDate: String = "0000-00-00 00:00:00"
    private var deviceID: String = "0"
    private lateinit var eHRMSID: String
    private var isUserTeacher: Boolean = false
    private val LAUNCH_GOOGLE_BOLO = 1

    private lateinit var binding: MpcLayoutSubjectInstructionBinding
    private lateinit var media: MediaPlayer
    private lateinit var sharedPreferences: SharedPreferences

//    @Inject
//    lateinit var subjectInstructionsPresenter: SubjectInstructionsPresenter<SubjectInstructionsMvpView, SubjectInstructionsMvpInteractor>


    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val pre= SubjectInstructionsPresenter<SubjectInstructionsMvpView, SubjectInstructionsMvpInteractor>()
        binding = MpcLayoutSubjectInstructionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        activityComponent.inject(this)
//        subjectInstructionsPresenter.onAttach(this)
        subject = intent.getStringExtra("selectedSubject") as String
        grade = intent.getIntExtra("grade", 0)
        Log.d("-->>","Subject selected is " + subject)
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
//        sharedPreferences.edit().remove("studentAssessmentFlowStatus").apply()
        if (subject == "Hindi") {
            binding.subjectInstructionText.text = "आपको अभी कुछ शब्द अथवा कहानियां दिखेंगी\nउनको पढ़ने की कोशिश करें।"
        } else {
            binding.subjectInstructionText.text = "आपको गणित के कुछ प्रश्न दिखेंगे\nआपको दिए गए संख्याएं को हिंदी में पढ़ना है।"
        }
        /*binding.instructionGoToHome.setOnClickListener {
            EventBus.getDefault().postSticky(BackEvent())
            finish()
        }*/
        binding.subjectInstructionStudentGoToSubject.setOnClickListener {
            onGoToSubjectClicked()
        }
//        deviceID = PreferenceManager.getDefaultSharedPreferences(this).getString("device", "")!!
//        isUserTeacher = PreferenceManager.getDefaultSharedPreferences(this).getString("profile", "") == "Teacher"
        eHRMSID = "-"
        val sdfDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss") //dd/MM/yyyy
        val now = Date()
        strDate = sdfDate.format(now)
//        media = MediaPlayer.create(this, R.raw.subject_selection)
//        updateFormConfigSettings()
//        initialiseObserver()
    }

    private fun initialiseObserver() {
//        subjectInstructionsPresenter.checkForFormUpdates(subject, this, UtilityFunctions.isNetworkConnected(activityContext))
    }

    private fun moveToQuestions() {
//        getRandomTestSample(subject)
//        val currentSample = when (grade) {
//            1 -> grade1Sample
//            2 -> grade2Sample
//            3 -> grade3Sample
//            4 -> grade4Sample
//            else -> grade5Sample
//        }
        val currentSample = grade1Sample
        Log.d("-->>","Rendering Instructions screen for student of grade : " +
                grade + " and practise of subject - " + subject)
        val studentHomeLearningProgressObject = HomeLearningProgressObject(grade,
                1, subject, grade1Sample,
                grade2Sample, grade3Sample, grade4Sample, grade5Sample, currentSample, 1,
                GradeStatus(), GradeStatus(), GradeStatus(), GradeStatus(), GradeStatus())
        Log.d("-->>","Subject Instruction Screen object is $studentHomeLearningProgressObject")
//        sharedPreferences.edit().putString("studentAssessmentFlowStatus", UtilityFunctions.toJson(studentHomeLearningProgressObject)).apply()

        val intent = Intent()
        intent.action = "com.google.android.apps.seekh.READBOOK"
        intent.putExtra("assessment_mode", true)
        val subjectCode: String = "h"
//        subjectCode = if (event.getSubject().equals("Hindi")) "h" else "m"
        val homeLearningProgressObject: HomeLearningProgressObject =
            studentHomeLearningProgressObject
//        studentHomePresenter.getMvpInteractor().getPreferenceHelper()
//            .updateStudentAssessmentFlowStatus(homeLearningProgressObject)
        intent.putExtra("intent_open_book_id",
            "g1" + subjectCode + "_s" + studentHomeLearningProgressObject
                .getGrade1StartSample() + "_v1"
        )
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        startActivityForResult(intent, LAUNCH_GOOGLE_BOLO)
        val sdfDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss") //dd/MM/yyyy

        val now = Date()
        val strDate = sdfDate.format(now)
        Log.d("-->>", "OnActivityResult() called from Subject Instruction screen")

        finish()
    }

    private fun onGoToSubjectClicked() {
        val startLearningEventData = StartLearningEventData(subject, grade, isUserTeacher, eHRMSID, deviceID, strDate)
      /*  if (UtilityFunctions.isNetworkConnected(this)) {
//            subjectInstructionsPresenter.sendData(AppConstants.JWTToken, fetchList(startLearningEventData), activityContext);
        } else {
            updateList(fetchList((startLearningEventData)))
            moveToQuestions()
        }*/

//        updateList(fetchList((startLearningEventData)))
        moveToQuestions()

    }

    private fun updateList(homeLearningProgressObject: ArrayList<StartLearningEventData>) {
        val json = Gson().toJson(homeLearningProgressObject)
        sharedPreferences.edit().putString("ASSESSMENT_EVENT_START_LIST", json).apply()
    }

    private fun fetchList(homeLearningProgressObject: StartLearningEventData): ArrayList<StartLearningEventData> {
        val serializedObject = sharedPreferences.getString("ASSESSMENT_EVENT_START_LIST", null)
        if (serializedObject != null) {
            val gson = Gson()
            val type: Type = object : TypeToken<List<StartLearningEventData>?>() {}.type
            var arrayItems = gson.fromJson<List<StartLearningEventData>>(serializedObject, type)
            return if (arrayItems.isEmpty()) {
                arrayItems = ArrayList()
                arrayItems.add(homeLearningProgressObject)
                arrayItems
            } else {
                val list = ArrayList<StartLearningEventData>()
                list.addAll(arrayItems)
                list.add(homeLearningProgressObject)
                list
            }
        } else {
            val arrayItems = ArrayList<StartLearningEventData>()
            arrayItems.add(homeLearningProgressObject)
            return arrayItems
        }
    }

    private fun updateFormConfigSettings() {
        hindiGrade1List.clear()
        hindiGrade2List.clear()
        hindiGrade3List.clear()
        hindiGrade4List.clear()
        hindiGrade5List.clear()
        mathGrade1List.clear()
        mathGrade2List.clear()
        mathGrade3List.clear()
        mathGrade4List.clear()
        mathGrade5List.clear()
        val json = PreferenceManager.getDefaultSharedPreferences(this).getString("formAssessmentsConfigText", "")
        Log.d("-->>","JSON FOR CONFIG TEXT IS >>> " + json)
//        val assessmentRangeConfigArrayList = UtilityFunctions.getJsonObject(json)
        /*for (assessmentRangeConfig in assessmentRangeConfigArrayList) {
            when (assessmentRangeConfig.grade) {
                1 -> {
                    addToGradeList(assessmentRangeConfig.hindiStartIndex, assessmentRangeConfig.hindiEndIndex, hindiGrade1List)
                    addToGradeList(assessmentRangeConfig.mathsStartIndex, assessmentRangeConfig.mathsEndIndex, mathGrade1List)
                }
                2 -> {
                    addToGradeList(assessmentRangeConfig.hindiStartIndex, assessmentRangeConfig.hindiEndIndex, hindiGrade2List)
                    addToGradeList(assessmentRangeConfig.mathsStartIndex, assessmentRangeConfig.mathsEndIndex, mathGrade2List)
                }
                3 -> {
                    addToGradeList(assessmentRangeConfig.hindiStartIndex, assessmentRangeConfig.hindiEndIndex, hindiGrade3List)
                    addToGradeList(assessmentRangeConfig.mathsStartIndex, assessmentRangeConfig.mathsEndIndex, mathGrade3List)
                }
                4 -> {
                    addToGradeList(assessmentRangeConfig.hindiStartIndex, assessmentRangeConfig.hindiEndIndex, hindiGrade4List)
                    addToGradeList(assessmentRangeConfig.mathsStartIndex, assessmentRangeConfig.mathsEndIndex, mathGrade4List)
                }
                else -> {
                    addToGradeList(assessmentRangeConfig.hindiStartIndex, assessmentRangeConfig.hindiEndIndex, hindiGrade5List)
                    addToGradeList(assessmentRangeConfig.mathsStartIndex, assessmentRangeConfig.mathsEndIndex, mathGrade5List)
                }
            }
        }*/

    }

    private fun addToGradeList(startIndex: Int, endIndex: Int, gradeList: ArrayList<Int>) {
        for (i in startIndex..endIndex) {
            gradeList.add(i)
        }
    }


    private fun getRandomTestSample(subject: String) {
        var hindiSampleValue: Int
        var mathSampleValue: Int
        var size: Int
        if (subject == "Hindi") {
            size = hindiGrade1List.size - 5
            val subList1 = hindiGrade1List.subList(0, size)
            hindiSampleValue = getRandomSample(subList1)
            if (sharedPreferences.getInt("Grade1HindiTestSample", 0) == hindiSampleValue) {
                val length = subList1.size
                if (hindiSampleValue == subList1[length - 1]) {
                    hindiSampleValue = subList1[0]
                } else {
                    hindiSampleValue += 1
                }
            }
            sharedPreferences.edit().putInt("Grade1HindiTestSample", hindiSampleValue).apply()
            grade1Sample = hindiSampleValue

            size = hindiGrade2List.size - 4
            val subList2 = hindiGrade2List.subList(0, size)
            hindiSampleValue = getRandomSample(subList2)
            if (sharedPreferences.getInt("Grade2HindiTestSample", 0) == hindiSampleValue) {
                val length = subList2.size
                if (hindiSampleValue == subList2[length - 1]) {
                    hindiSampleValue = subList2[0]
                } else {
                    hindiSampleValue += 1
                }
            }
            sharedPreferences.edit().putInt("Grade2HindiTestSample", hindiSampleValue).apply()
            grade2Sample = hindiSampleValue

            size = hindiGrade3List.size - 4
            val subList3 = hindiGrade4List.subList(0, size)
            hindiSampleValue = getRandomSample(subList3)
            if (sharedPreferences.getInt("Grade3HindiTestSample", 0) == hindiSampleValue) {
                val length = subList3.size
                if (hindiSampleValue == subList3[length - 1]) {
                    hindiSampleValue = subList3[0]
                } else {
                    hindiSampleValue += 1
                }
            }
            sharedPreferences.edit().putInt("Grade3HindiTestSample", hindiSampleValue).apply()
            grade3Sample = hindiSampleValue

            size = hindiGrade4List.size - 4
            val subList4 = hindiGrade4List.subList(0, size)
            hindiSampleValue = getRandomSample(subList4)
            if (sharedPreferences.getInt("Grade4HindiTestSample", 0) == hindiSampleValue) {
                val length = subList4.size
                if (hindiSampleValue == subList4[length - 1]) {
                    hindiSampleValue = subList4[0]
                } else {
                    hindiSampleValue += 1
                }
            }
            sharedPreferences.edit().putInt("Grade4HindiTestSample", hindiSampleValue).apply()
            grade4Sample = hindiSampleValue

            size = hindiGrade5List.size - 4
            val subList5 = hindiGrade5List.subList(0, size)
            hindiSampleValue = getRandomSample(subList5)
            if (sharedPreferences.getInt("Grade5HindiTestSample", 0) == hindiSampleValue) {
                val length = subList5.size
                if (hindiSampleValue == subList5[length - 1]) {
                    hindiSampleValue = subList5[0]
                } else {
                    hindiSampleValue += 1
                }
            }
            sharedPreferences.edit().putInt("Grade5HindiTestSample", hindiSampleValue).apply()
            grade5Sample = hindiSampleValue

        } else {
            size = mathGrade1List.size - 5
            val subList1 = mathGrade1List.subList(0, size)
            mathSampleValue = getRandomSample(subList1)
            if (sharedPreferences.getInt("Grade1MathTestSample", 0) == mathSampleValue) {
                val length = subList1.size
                if (mathSampleValue == subList1[length - 1]) {
                    mathSampleValue = subList1[0]
                } else {
                    mathSampleValue += 1
                }
            }
            sharedPreferences.edit().putInt("Grade1MathTestSample", mathSampleValue).apply()
            grade1Sample = mathSampleValue

            size = mathGrade2List.size - 4
            val subList2 = mathGrade2List.subList(0, size)
            mathSampleValue = getRandomSample(subList2)
            if (sharedPreferences.getInt("Grade2MathTestSample", 0) == (mathSampleValue)) {
                val length = subList2.size
                if (mathSampleValue == subList2[length - 1]) {
                    mathSampleValue = subList2[0]
                } else {
                    mathSampleValue += 1
                }
            }
            sharedPreferences.edit().putInt("Grade2MathTestSample", mathSampleValue).apply()
            grade2Sample = mathSampleValue

            size = mathGrade3List.size - 4
            val subList3 = mathGrade3List.subList(0, size)
            mathSampleValue = getRandomSample(subList3)
            if (sharedPreferences.getInt("Grade3MathTestSample", 0) == mathSampleValue) {
                val length = subList3.size
                if (mathSampleValue == subList3[length - 1]) {
                    mathSampleValue = subList3[0]
                } else {
                    mathSampleValue += 1
                }
            }
            sharedPreferences.edit().putInt("Grade3MathTestSample", mathSampleValue).apply()
            grade3Sample = mathSampleValue

            size = mathGrade4List.size - 4
            val subList4 = mathGrade4List.subList(0, size)
            mathSampleValue = getRandomSample(subList4)
            if (sharedPreferences.getInt("Grade4MathTestSample", 0) == mathSampleValue) {
                val length = subList4.size
                if (mathSampleValue == subList4[length - 1]) {
                    mathSampleValue = subList4[0]
                } else {
                    mathSampleValue += 1
                }
            }
            sharedPreferences.edit().putInt("Grade4MathTestSample", mathSampleValue).apply()
            grade4Sample = mathSampleValue

            size = mathGrade5List.size - 4
            val subList5 = mathGrade5List.subList(0, size)
            mathSampleValue = getRandomSample(subList5)
            if (sharedPreferences.getInt("Grade5MathTestSample", 0) == mathSampleValue) {
                val length = subList5.size
                if (mathSampleValue == subList5[length - 1]) {
                    mathSampleValue = subList5[0]
                } else {
                    mathSampleValue += 1
                }
            }
            sharedPreferences.edit().putInt("Grade5MathTestSample", mathSampleValue).apply();
            grade5Sample = mathSampleValue

        }

    }
    open fun getRandomSample(list: List<Int>): Int {
        val rand = Random()
        return list[rand.nextInt(list.size)]
    }

    fun successReceived() {
        updateList(ArrayList())
        moveToQuestions()
    }

    fun failure(startLearningEventData: ArrayList<StartLearningEventData>) {
        updateList(startLearningEventData)
        moveToQuestions()
    }
/*

    fun renderLayoutVisible() {
        instruction_scroller.visibility = View.VISIBLE
        progressbarPredict.visibility = View.GONE
        textloader.visibility = View.GONE
        textloader1.visibility = View.GONE
    }

    fun showDownloadFailureMessage() {
        showSnackbar("आकलन प्रश्न पत्र डाउनलोड करने में त्रुटि हुई |", Snackbar.LENGTH_LONG)
        renderLayoutVisible()
    }

    fun showNoInternetMessage() {
        showSnackbar("आकलन प्रश्न पत्र को एक बार डाउनलोड करने हेतु, आपका इंटरनेट से जुड़ना अनिवार्य है |", Snackbar.LENGTH_LONG)
        renderLayoutVisible()
    }

    fun showFailureDownloadMessage() {
        showSnackbar("आकलन प्रश्न पत्र डाउनलोड करने में त्रुटि हुई |", Snackbar.LENGTH_LONG)
        renderLayoutVisible()
    }

    fun loaderStatus( f:String) {
        textloader1.text = f+"%"
    }
*/


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == LAUNCH_GOOGLE_BOLO) {
            /*val assessmentFlowStatus: HomeLearningProgressObject =
                studentHomePresenter.getMvpInteractor().getPreferenceHelper()
                    .getStudentAssessmentFlowStatus()
            val correctWords = data!!.getIntExtra("correct_words", 0)
            val totals: Int
            val totalTime = data.getLongExtra("total_time", 0)
            val l = totalTime / 1000
            totals = l.toInt()
            if (assessmentFlowStatus.currentAssessmentGrade === 1) {
                val copy1 = assessmentFlowStatus.copy(assessmentFlowStatus)
                if (assessmentFlowStatus.currentAttempt === 1) {
                    val gradeStatus = GradeStatus()
                    gradeStatus.totalTimeTrial1 = totals
                    gradeStatus.wordsCorrectTrial1 = correctWords
                    gradeStatus.scoreTrial1 = 0
                    copy1.grade1 = gradeStatus
                } else if (assessmentFlowStatus.currentAttempt === 2) {
                    val gradeStatus = GradeStatus()
                    gradeStatus.totalTimeTrial1 = assessmentFlowStatus.grade1.totalTimeTrial1
                    gradeStatus.wordsCorrectTrial1 = assessmentFlowStatus.grade1.wordsCorrectTrial1
                    gradeStatus.scoreTrial1 = 0
                    gradeStatus.totalTimeTrial2 = totals
                    gradeStatus.wordsCorrectTrial2 = correctWords
                    gradeStatus.scoreTrial2 = 0
                    copy1.grade1 = gradeStatus
                } else if (assessmentFlowStatus.currentAttempt === 3) {
                    val gradeStatus = GradeStatus()
                    gradeStatus.totalTimeTrial1 = assessmentFlowStatus.grade1.totalTimeTrial1
                    gradeStatus.wordsCorrectTrial1 = assessmentFlowStatus.grade1.wordsCorrectTrial1
                    gradeStatus.scoreTrial1 = 0
                    gradeStatus.totalTimeTrial2 = assessmentFlowStatus.grade1.totalTimeTrial2
                    gradeStatus.wordsCorrectTrial2 = assessmentFlowStatus.grade1.wordsCorrectTrial2
                    gradeStatus.scoreTrial2 = 0
                    gradeStatus.totalTimeTrial3 = totals
                    gradeStatus.wordsCorrectTrial3 = correctWords
                    gradeStatus.scoreTrial3 = 0
                    copy1.grade1 = gradeStatus
                } else if (assessmentFlowStatus.currentAttempt === 4) {
                    val gradeStatus = GradeStatus()
                    gradeStatus.totalTimeTrial1 = assessmentFlowStatus.grade1.totalTimeTrial1
                    gradeStatus.wordsCorrectTrial1 = assessmentFlowStatus.grade1.wordsCorrectTrial1
                    gradeStatus.scoreTrial1 = 0
                    gradeStatus.totalTimeTrial2 = assessmentFlowStatus.grade1.totalTimeTrial2
                    gradeStatus.wordsCorrectTrial2 = assessmentFlowStatus.grade1.wordsCorrectTrial2
                    gradeStatus.scoreTrial2 = 0
                    gradeStatus.totalTimeTrial3 = assessmentFlowStatus.grade1.totalTimeTrial3
                    gradeStatus.wordsCorrectTrial3 = assessmentFlowStatus.grade1.wordsCorrectTrial3
                    gradeStatus.scoreTrial3 = 0
                    gradeStatus.totalTimeTrial4 = totals
                    gradeStatus.wordsCorrectTrial4 = correctWords
                    gradeStatus.scoreTrial4 = 0
                    copy1.grade1 = gradeStatus
                } else {
                    val gradeStatus = GradeStatus()
                    gradeStatus.totalTimeTrial1 = assessmentFlowStatus.grade1.totalTimeTrial1
                    gradeStatus.wordsCorrectTrial1 = assessmentFlowStatus.grade1.wordsCorrectTrial1
                    gradeStatus.scoreTrial1 = 0
                    gradeStatus.totalTimeTrial2 = assessmentFlowStatus.grade1.totalTimeTrial2
                    gradeStatus.wordsCorrectTrial2 = assessmentFlowStatus.grade1.wordsCorrectTrial2
                    gradeStatus.scoreTrial2 = 0
                    gradeStatus.totalTimeTrial3 = assessmentFlowStatus.grade1.totalTimeTrial3
                    gradeStatus.wordsCorrectTrial3 = assessmentFlowStatus.grade1.wordsCorrectTrial3
                    gradeStatus.scoreTrial3 = 0
                    gradeStatus.totalTimeTrial4 = assessmentFlowStatus.grade1.totalTimeTrial4
                    gradeStatus.wordsCorrectTrial4 = assessmentFlowStatus.grade1.wordsCorrectTrial4
                    gradeStatus.scoreTrial4 = 0
                    gradeStatus.totalTimeTrial5 = totals
                    gradeStatus.wordsCorrectTrial5 = correctWords
                    gradeStatus.scoreTrial5 = 0
                    copy1.grade1 = gradeStatus
                }
                studentHomePresenter.getMvpInteractor().getPreferenceHelper()
                    .updateStudentAssessmentFlowStatus(copy1)
            } else if (assessmentFlowStatus.currentAssessmentGrade === 2) {
                val copy1 = assessmentFlowStatus.copy(assessmentFlowStatus)
                if (assessmentFlowStatus.currentAttempt === 1) {
                    val gradeStatus = GradeStatus()
                    gradeStatus.totalTimeTrial1 = totals
                    gradeStatus.wordsCorrectTrial1 = correctWords
                    gradeStatus.scoreTrial1 = 0
                    copy1.grade2 = gradeStatus
                } else if (assessmentFlowStatus.currentAttempt === 2) {
                    val gradeStatus = GradeStatus()
                    gradeStatus.totalTimeTrial1 = assessmentFlowStatus.grade2.totalTimeTrial1
                    gradeStatus.wordsCorrectTrial1 = assessmentFlowStatus.grade2.wordsCorrectTrial1
                    gradeStatus.scoreTrial1 = assessmentFlowStatus.grade2.scoreTrial1
                    gradeStatus.totalTimeTrial2 = totals
                    gradeStatus.wordsCorrectTrial2 = correctWords
                    gradeStatus.scoreTrial2 = 0
                    copy1.grade2 = gradeStatus
                } else if (assessmentFlowStatus.currentAttempt === 3) {
                    val gradeStatus = GradeStatus()
                    gradeStatus.totalTimeTrial1 = assessmentFlowStatus.grade2.totalTimeTrial1
                    gradeStatus.wordsCorrectTrial1 = assessmentFlowStatus.grade2.wordsCorrectTrial1
                    gradeStatus.scoreTrial1 = assessmentFlowStatus.grade2.scoreTrial1
                    gradeStatus.totalTimeTrial2 = assessmentFlowStatus.grade2.totalTimeTrial2
                    gradeStatus.wordsCorrectTrial2 = assessmentFlowStatus.grade2.wordsCorrectTrial2
                    gradeStatus.scoreTrial2 = assessmentFlowStatus.grade2.scoreTrial2
                    gradeStatus.totalTimeTrial3 = totals
                    gradeStatus.wordsCorrectTrial3 = correctWords
                    gradeStatus.scoreTrial3 = 0
                    copy1.grade2 = gradeStatus
                } else {
                    val gradeStatus = GradeStatus()
                    gradeStatus.totalTimeTrial1 = assessmentFlowStatus.grade2.totalTimeTrial1
                    gradeStatus.wordsCorrectTrial1 = assessmentFlowStatus.grade2.wordsCorrectTrial1
                    gradeStatus.scoreTrial1 = assessmentFlowStatus.grade2.scoreTrial1
                    gradeStatus.totalTimeTrial2 = assessmentFlowStatus.grade2.totalTimeTrial2
                    gradeStatus.wordsCorrectTrial2 = assessmentFlowStatus.grade2.wordsCorrectTrial2
                    gradeStatus.scoreTrial2 = assessmentFlowStatus.grade2.scoreTrial2
                    gradeStatus.totalTimeTrial3 = assessmentFlowStatus.grade2.totalTimeTrial3
                    gradeStatus.wordsCorrectTrial3 = assessmentFlowStatus.grade2.wordsCorrectTrial3
                    gradeStatus.scoreTrial3 = assessmentFlowStatus.grade2.scoreTrial3
                    gradeStatus.totalTimeTrial4 = totals
                    gradeStatus.wordsCorrectTrial4 = correctWords
                    gradeStatus.scoreTrial4 = 0
                    copy1.grade2 = gradeStatus
                }
                studentHomePresenter.getMvpInteractor().getPreferenceHelper()
                    .updateStudentAssessmentFlowStatus(copy1)
            } else if (assessmentFlowStatus.currentAssessmentGrade === 3) {
                val copy1 = assessmentFlowStatus.copy(assessmentFlowStatus)
                if (assessmentFlowStatus.currentAttempt === 1) {
                    val gradeStatus = GradeStatus()
                    gradeStatus.totalTimeTrial1 = totals
                    gradeStatus.wordsCorrectTrial1 = correctWords
                    gradeStatus.scoreTrial1 = 0
                    copy1.grade3 = gradeStatus
                } else if (assessmentFlowStatus.currentAttempt === 2) {
                    val gradeStatus = GradeStatus()
                    gradeStatus.totalTimeTrial1 = assessmentFlowStatus.grade3.totalTimeTrial1
                    gradeStatus.wordsCorrectTrial1 = assessmentFlowStatus.grade3.wordsCorrectTrial1
                    gradeStatus.scoreTrial1 = assessmentFlowStatus.grade3.scoreTrial1
                    gradeStatus.totalTimeTrial3 = totals
                    gradeStatus.wordsCorrectTrial3 = correctWords
                    gradeStatus.scoreTrial3 = 0
                    copy1.grade3 = gradeStatus
                } else if (assessmentFlowStatus.currentAttempt === 3) {
                    val gradeStatus = GradeStatus()
                    gradeStatus.totalTimeTrial1 = assessmentFlowStatus.grade3.totalTimeTrial1
                    gradeStatus.wordsCorrectTrial1 = assessmentFlowStatus.grade3.wordsCorrectTrial1
                    gradeStatus.scoreTrial1 = assessmentFlowStatus.grade3.scoreTrial1
                    gradeStatus.totalTimeTrial2 = assessmentFlowStatus.grade3.totalTimeTrial2
                    gradeStatus.wordsCorrectTrial2 = assessmentFlowStatus.grade3.wordsCorrectTrial2
                    gradeStatus.scoreTrial2 = assessmentFlowStatus.grade3.scoreTrial2
                    gradeStatus.totalTimeTrial3 = totals
                    gradeStatus.wordsCorrectTrial3 = correctWords
                    gradeStatus.scoreTrial3 = 0
                    copy1.grade3 = gradeStatus
                } else {
                    val gradeStatus = GradeStatus()
                    gradeStatus.totalTimeTrial1 = assessmentFlowStatus.grade3.totalTimeTrial1
                    gradeStatus.wordsCorrectTrial1 = assessmentFlowStatus.grade3.wordsCorrectTrial1
                    gradeStatus.scoreTrial1 = assessmentFlowStatus.grade3.scoreTrial1
                    gradeStatus.totalTimeTrial2 = assessmentFlowStatus.grade3.totalTimeTrial2
                    gradeStatus.wordsCorrectTrial2 = assessmentFlowStatus.grade3.wordsCorrectTrial2
                    gradeStatus.scoreTrial2 = assessmentFlowStatus.grade3.scoreTrial2
                    gradeStatus.totalTimeTrial3 = assessmentFlowStatus.grade3.totalTimeTrial3
                    gradeStatus.wordsCorrectTrial3 = assessmentFlowStatus.grade3.wordsCorrectTrial3
                    gradeStatus.scoreTrial3 = assessmentFlowStatus.grade3.scoreTrial3
                    gradeStatus.totalTimeTrial4 = totals
                    gradeStatus.wordsCorrectTrial4 = correctWords
                    gradeStatus.scoreTrial4 = 0
                    copy1.grade3 = gradeStatus
                }
                studentHomePresenter.getMvpInteractor().getPreferenceHelper()
                    .updateStudentAssessmentFlowStatus(copy1)
            }
            val intent = Intent(this, ResultProcessorScreen::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivityForResult(intent, 9700)
        } else if (resultCode == RESULT_CANCELED && requestCode == LAUNCH_GOOGLE_BOLO) {
            Grove.e("On Return from Bolo >>> ", "Result code \$resultCode Data \$data")
            AlertDialogUtils.showDialog(
                AlertDialogUtils.createErrorDialog(
                    this,
                    getString(R.string.couldnt_complete_assessment), false
                ), this
            )
        } else if (resultCode == 9707 && requestCode == 9700) {
            resetSelectors()
        } else if (resultCode == 9709 && requestCode == 9700) {
            resetSelectors()
            val intent = Intent()
            intent.action = "com.google.android.apps.seekh.READBOOK"
            intent.putExtra("assessment_mode", true)
            val subjectCode: String
            subjectCode = if (data!!.getStringExtra("subject") == "Hindi") "h" else "m"
            intent.putExtra(
                "intent_open_book_id", "g" + data.getIntExtra("grade", 0) + subjectCode + "_s" +
                        data.getIntExtra("bookSample", 0) + "_v1"
            )
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
            startActivityForResult(intent, LAUNCH_GOOGLE_BOLO)
            Grove.d("OnActivityResult() called from Subject Instruction screen")
        }*/
        }
    }

}