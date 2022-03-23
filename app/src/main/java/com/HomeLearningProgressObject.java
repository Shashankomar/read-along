package com;

public class HomeLearningProgressObject {
    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    private boolean isComplete;
    //device Id
    private int actualGrade;
    private int currentAssessmentGrade;
    
    private int currentSample;
    private int currentAttempt;

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    private String deviceID;
    private String subject;
    private int grade1StartSample;
    private int grade2StartSample;
    private int grade3StartSample;
    private int grade4StartSample;
    private int grade5StartSample;

    private GradeStatus grade1;
    private GradeStatus grade3;
    private GradeStatus grade2;
    private GradeStatus grade4;
    private GradeStatus grade5;

    private String pushTime;
    private String startTime;
    private int totalScore;

    public String getPushTime() {
        return pushTime;
    }

    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }

    public boolean isUserTeacher() {
        return isUserTeacher;
    }

    public void setUserTeacher(boolean userTeacher) {
        isUserTeacher = userTeacher;
    }

    public String getHrmsCode() {
        return hrmsCode;
    }

    public void setHrmsCode(String hrmsCode) {
        this.hrmsCode = hrmsCode;
    }

    private boolean isUserTeacher;
    private String hrmsCode;
    //isCompleted
    public HomeLearningProgressObject(int actualGrade, int currentAssessmentGrade, String subject, 
                                      int grade1StartSample, int grade2StartSample, int grade3StartSample, int grade4StartSample, int grade5StartSample,
                                      int currentSample, int currentAttempt, GradeStatus grade1, GradeStatus grade2, GradeStatus grade3,GradeStatus grade4,
                                      GradeStatus grade5) {
        this.actualGrade = actualGrade;
        this.currentAssessmentGrade = currentAssessmentGrade;
        this.subject = subject;
        this.grade1StartSample = grade1StartSample;
        this.grade2StartSample = grade2StartSample;
        this.grade3StartSample = grade3StartSample;
        this.grade4StartSample = grade4StartSample;
        this.grade5StartSample = grade5StartSample;
        this.currentSample = currentSample;
        this.currentAttempt = currentAttempt;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
        this.grade4 = grade4;
        this.grade5 = grade5;
        this.isComplete = true;
    }

    public int getActualGrade() {
        return actualGrade;
    }

    public int getCurrentAssessmentGrade() {
        return currentAssessmentGrade;
    }

    public String getSubject() {
        return subject;
    }

    public int getGrade1StartSample() {
        return grade1StartSample;
    }

    public int getGrade2StartSample() {
        return grade2StartSample;
    }

    public int getGrade3StartSample() {
        return grade3StartSample;
    }

    public int getGrade4StartSample() {
        return grade4StartSample;
    }

    public int getGrade5StartSample() {
        return grade5StartSample;
    }

    public void setActualGrade(int actualGrade) {
        this.actualGrade = actualGrade;
    }

    public void setCurrentAssessmentGrade(int currentGrade) {
        this.currentAssessmentGrade = currentGrade;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setGrade1StartSample(int grade1StartSample) {
        this.grade1StartSample = grade1StartSample;
    }

    public void setGrade2StartSample(int grade2StartSample) {
        this.grade2StartSample = grade2StartSample;
    }

    public void setGrade3StartSample(int grade3StartSample) {
        this.grade3StartSample = grade3StartSample;
    }

    public void setGrade4StartSample(int grade4StartSample) {
        this.grade4StartSample = grade4StartSample;
    }

    public void setGrade5StartSample(int grade5StartSample) {
        this.grade5StartSample = grade5StartSample;
    }

    public int getCurrentSample() {
        return currentSample;
    }

    public void setCurrentSample(int currentSample) {
        this.currentSample = currentSample;
    }


    @Override
    public Object clone() {
        try {
            return (HomeLearningProgressObject) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
         }
    }

    public HomeLearningProgressObject copy(HomeLearningProgressObject copy) {
        return new HomeLearningProgressObject(copy.actualGrade, copy.currentAssessmentGrade,
                copy.subject, copy.grade1StartSample, copy.grade2StartSample, copy.grade3StartSample,
                copy.grade4StartSample, copy.grade5StartSample, copy.currentSample, copy.currentAttempt,
                copy.grade1, copy.grade2, copy.grade3, copy.grade4, copy.grade5);
    }

    public GradeStatus getGrade5() {
        return grade5;
    }

    public void setGrade5(GradeStatus grade5) {
        this.grade5 = grade5;
    }

    public GradeStatus getGrade1() {
        return grade1;
    }

    public void setGrade1(GradeStatus grade1) {
        this.grade1 = grade1;
    }

    public GradeStatus getGrade3() {
        return grade3;
    }

    public void setGrade3(GradeStatus grade3) {
        this.grade3 = grade3;
    }

    public GradeStatus getGrade2() {
        return grade2;
    }

    public void setGrade2(GradeStatus grade2) {
        this.grade2 = grade2;
    }

    public GradeStatus getGrade4() {
        return grade4;
    }

    public void setGrade4(GradeStatus grade4) {
        this.grade4 = grade4;
    }

    public int getCurrentAttempt() {
        return currentAttempt;
    }

    public void setCurrentAttempt(int currentAttempt) {
        this.currentAttempt = currentAttempt;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "HomeLearningProgressObject{" +
                "isComplete=" + isComplete +
                ", actualGrade=" + actualGrade +
                ", currentAssessmentGrade=" + currentAssessmentGrade +
                ", currentSample=" + currentSample +
                ", currentAttempt=" + currentAttempt +
                ", deviceID='" + deviceID + '\'' +
                ", subject='" + subject + '\'' +
                ", grade1StartSample=" + grade1StartSample +
                ", grade2StartSample=" + grade2StartSample +
                ", grade3StartSample=" + grade3StartSample +
                ", grade4StartSample=" + grade4StartSample +
                ", grade5StartSample=" + grade5StartSample +
                ", grade1=" + grade1 +
                ", grade3=" + grade3 +
                ", grade2=" + grade2 +
                ", grade4=" + grade4 +
                ", grade5=" + grade5 +
                ", pushTime='" + pushTime + '\'' +
                ", startTime='" + startTime + '\'' +
                ", totalScore=" + totalScore +
                ", isUserTeacher=" + isUserTeacher +
                ", hrmsCode='" + hrmsCode + '\'' +
                '}';
    }
}

