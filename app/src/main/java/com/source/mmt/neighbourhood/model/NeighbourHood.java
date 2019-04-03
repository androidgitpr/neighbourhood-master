package com.source.mmt.neighbourhood.model;

import java.util.ArrayList;
import java.util.HashMap;

public class NeighbourHood {
    private static NeighbourHood NeighbourHood = null;
    private Integer id;
    private String mobile;
    private String pwd;
    private String name;
    private String email;
    private String otp;
    private Boolean isInitDone;
    private ArrayList<ImageGalleryCategoryModel> AcademicImages;
    private ArrayList<ImageGalleryCategoryModel> DailyImages;
    private ArrayList<EventInfo> event;
    private ArrayList<ImageGallerySubcategoryModel> CurrentImages;
    private ArrayList<Contactinfo> contactDetail;
    private ArrayList<Apartment> apartmentsList;
    private ArrayList<SurveyQuestions> surveyQList;
    private int surveyQIndex;
    private Boolean announceLoaded;

    private ArrayList<ServiceInfo> serviceCatList;
    private HashMap<Integer, ArrayList<ServiceInfo>> serviceSubCatMapping;

    private Userinfo usrInfo;
    NeighbourHood() {
        isInitDone = false;
        surveyQIndex = 0;
        event = new ArrayList<EventInfo>();
        AcademicImages = new ArrayList<ImageGalleryCategoryModel>();
        DailyImages = new ArrayList<ImageGalleryCategoryModel>();
        contactDetail = new ArrayList<Contactinfo>();
        surveyQList = new ArrayList<SurveyQuestions>();
        announceLoaded = false;

        serviceCatList = new ArrayList<ServiceInfo>();
        serviceSubCatMapping = new HashMap<Integer, ArrayList<ServiceInfo>>();
        apartmentsList = new ArrayList<Apartment>();

        usrInfo = new Userinfo();
    }

    public static NeighbourHood getInstance()
    {
        if (NeighbourHood == null) {
            NeighbourHood = new NeighbourHood();
        }

        return NeighbourHood;
    }

    public Boolean getInitDone() {
        return isInitDone;
    }

    public void setInitDone(Boolean initDone) {
        isInitDone = initDone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public ArrayList<ImageGalleryCategoryModel> getAcademicImages() {
        return AcademicImages;
    }

    public void setAcademicImages(ArrayList<ImageGalleryCategoryModel> academicImages) {
        AcademicImages = academicImages;
    }

    public ArrayList<ImageGalleryCategoryModel> getDailyImages() {
        return DailyImages;
    }

    public void setDailyImages(ArrayList<ImageGalleryCategoryModel> dailyImages) {
        DailyImages = dailyImages;
    }


    public Boolean getAnnounceLoaded() {
        return announceLoaded;
    }

    public void setAnnounceLoaded(Boolean announceLoaded) {
        this.announceLoaded = announceLoaded;
    }


    public ArrayList<ImageGallerySubcategoryModel> getCurrentImages() {
        return CurrentImages;
    }

    public void setCurrentImages(ArrayList<ImageGallerySubcategoryModel> currentImages) {
        CurrentImages = currentImages;
    }

    public ArrayList<EventInfo> getEvent() {
        return event;
    }

    public void setEvent(ArrayList<EventInfo> event) {
        this.event = event;
    }

    public ArrayList<Contactinfo> getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(ArrayList<Contactinfo> contactDetail) {
        this.contactDetail = contactDetail;
    }

    public ArrayList<SurveyQuestions> getSurveyQList() {
        return surveyQList;
    }

    public void setSurveyQList(ArrayList<SurveyQuestions> surveyQList) {
        this.surveyQList = surveyQList;
    }

    public int getSurveyQIndex() {
        return surveyQIndex;
    }

    public void setSurveyQIndex(int surveyQIndex) {
        this.surveyQIndex = surveyQIndex;
    }

    public ArrayList<ServiceInfo> getServiceCatList() {
        return serviceCatList;
    }

    public void setServiceCatList(ArrayList<ServiceInfo> serviceCatList) {
        this.serviceCatList = serviceCatList;
    }

    public HashMap<Integer, ArrayList<ServiceInfo>> getServiceSubCatMapping() {
        return serviceSubCatMapping;
    }

    public void setServiceSubCatMapping(HashMap<Integer, ArrayList<ServiceInfo>> serviceSubCatMapping) {
        this.serviceSubCatMapping = serviceSubCatMapping;
    }

    public ArrayList<Apartment> getApartmentsList() {
        return apartmentsList;
    }

    public void setApartmentsList(ArrayList<Apartment> apartmentsList) {
        this.apartmentsList = apartmentsList;
    }

    public Userinfo getUsrInfo() {
        return usrInfo;
    }

    public void setUsrInfo(Userinfo usrInfo) {
        this.usrInfo = usrInfo;
    }
}
