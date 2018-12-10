package echedeygarcias.gmail.com.login.register.view;

public interface IViewRegister {

    void onStop();
    void toLogin();
    void toMenu();

    void showProgress();
    void hideProgress();
    void clearForm();
    void showError();

}
