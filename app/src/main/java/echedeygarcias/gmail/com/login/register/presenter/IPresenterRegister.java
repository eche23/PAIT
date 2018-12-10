package echedeygarcias.gmail.com.login.register.presenter;

import echedeygarcias.gmail.com.login.register.events.RegisterEvent;

public interface IPresenterRegister {

    void register(String username, String password);

    void onCreate();
    void onStop();
    void onEventLoginThread(RegisterEvent event);
}
