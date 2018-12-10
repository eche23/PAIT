package echedeygarcias.gmail.com.login.login.presenter;

import echedeygarcias.gmail.com.login.login.events.LoginEvents;

public interface IPresenterLogin {

    void onCreate();
    void onDestroy();
    void toLogin(String email, String password);
    void forgotPassword(String email);
    void onEventLoginThread(LoginEvents events);
}