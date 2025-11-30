package com.swe.miniproject;

public class CourseRequirement {
    String courseName;
    String programmeType;
    String minBM;
    String minEnglish;
    String minSejarah;
    String minMath;
    String minPrinsipPerakaunan;
    String minSainsKomp;
    String minKimia;


    public CourseRequirement(String courseName, String programmeType, String minBM, String minEnglish, String minSejarah, String minMath, String minPrinsipPerakaunan, String minSainsKomp, String minKimia) {
        this.courseName = courseName;
        this.programmeType = programmeType;
        this.minBM = minBM;
        this.minEnglish = minEnglish;
        this.minSejarah = minSejarah;
        this.minMath = minMath;
        this.minPrinsipPerakaunan = minPrinsipPerakaunan;
        this.minSainsKomp = minSainsKomp;
        this.minKimia = minKimia;
    }
}