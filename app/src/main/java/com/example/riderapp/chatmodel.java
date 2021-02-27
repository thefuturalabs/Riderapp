package com.example.riderapp;

public class chatmodel {
    private String Chat_id,Login_id,Massage,Replay,Send_date,Replay_date;

    public chatmodel(String chat_id, String login_id, String massage, String replay, String send_date, String replay_date) {
        Chat_id = chat_id;
        Login_id = login_id;
        Massage = massage;
        Replay = replay;
        Send_date = send_date;
        Replay_date = replay_date;
    }

    public String getChat_id() {
        return Chat_id;
    }

    public void setChat_id(String chat_id) {
        Chat_id = chat_id;
    }

    public String getLogin_id() {
        return Login_id;
    }

    public void setLogin_id(String login_id) {
        Login_id = login_id;
    }

    public String getMassage() {
        return Massage;
    }

    public void setMassage(String massage) {
        Massage = massage;
    }

    public String getReplay() {
        return Replay;
    }

    public void setReplay(String replay) {
        Replay = replay;
    }

    public String getSend_date() {
        return Send_date;
    }

    public void setSend_date(String send_date) {
        Send_date = send_date;
    }

    public String getReplay_date() {
        return Replay_date;
    }

    public void setReplay_date(String replay_date) {
        Replay_date = replay_date;
    }
}
