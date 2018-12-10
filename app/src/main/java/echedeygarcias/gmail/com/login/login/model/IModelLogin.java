package echedeygarcias.gmail.com.login.login.model;


public interface IModelLogin {

    void  doLogin(String email, String password);
    void forgotPassword(String email);
}