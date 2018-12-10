package echedeygarcias.gmail.com.login.login.view;

public interface IViewLogin {

    void showProgress();
    void hideProgress();
    void showErrorLogin();
    void cleanForm();

    boolean isValidEmail();
    boolean isValidPassword();

    void toMenu();
    void showPassSent();
    void showErrorPassSent();
    void toRegister();
}
