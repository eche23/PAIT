package echedeygarcias.gmail.com.login.register.presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import echedeygarcias.gmail.com.login.register.events.RegisterEvent;
import echedeygarcias.gmail.com.login.register.model.IModelRegister;
import echedeygarcias.gmail.com.login.register.model.ModelRegister;
import echedeygarcias.gmail.com.login.register.view.IViewRegister;
import echedeygarcias.gmail.com.login.register.view.RegisterActivity;

public class PresenterRegister implements IPresenterRegister{

    private IViewRegister view;
    private IModelRegister model;
    private EventBus eventBus;

    public PresenterRegister(IViewRegister activity) {
        this.view = activity;
        this.model = new ModelRegister();
        this.eventBus = EventBus.getDefault();
    }


    @Override
    public void onCreate(){
        eventBus.register(this);
    }

    @Override
    public void onStop() {
        eventBus.unregister(this);
    }

    @Override
    public void register(String username, String password) {
        model.register(username, password);
    }


    @Subscribe
    @Override
    public void onEventLoginThread(RegisterEvent event) {
        view.hideProgress();
        switch (event.getEventType()){
            case RegisterEvent.ON_REGISTER_SUCESS:
                view.toMenu();
                break;
            case  RegisterEvent.ON_REGISTER_ERROR:
                view.showError();
                view.clearForm();
                break;
        }
    }
}
