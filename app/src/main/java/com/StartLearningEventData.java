package com;

public class StartLearningEventData {
    private String deviceID;
    private String subject;

    public String getDeviceID() {
        return deviceID;
    }

    public String getSubject() {
        return subject;
    }

    public int getGrade() {
        return grade;
    }

    public boolean isUserTeacher() {
        return isUserTeacher;
    }

    public String getErhms_code() {
        return erhmsCode;
    }

    public String getStrDate() {
        return strDate;
    }

    private int grade;
    private boolean isUserTeacher;
    private String erhmsCode;
    private String strDate;


    public StartLearningEventData(String subject, int grade, boolean isUserTeacher,
                                  String erhmsCode, String deviceID, String strDate) {
        this.deviceID = deviceID;
        this.subject = subject;
        this.grade = grade;
        this.isUserTeacher=isUserTeacher;
        this.erhmsCode = erhmsCode;
        this.strDate = strDate;
    }
}
