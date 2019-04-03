package com.source.mmt.neighbourhood.model;

public class SurveyAnswer {
    private int aptId;
    private int qId;
    private String answer;
    private String usrId;

    public int getAptId() {
        return aptId;
    }

    public void setAptId(int aptId) {
        this.aptId = aptId;
    }

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }
}
