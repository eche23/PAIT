package echedeygarcias.gmail.com.login.register.model;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;



import echedeygarcias.gmail.com.login.register.events.RegisterEvent;
import echedeygarcias.gmail.com.login.user.User;

public class RepositoryRegister implements IRepositoryRegister{

    private FirebaseAuth mAuth;

    public RepositoryRegister() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void register(final String username, final String password) {
        try {
            mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        postEvent(RegisterEvent.ON_REGISTER_SUCESS);
                        login(username, password);
                    } else {
                        postEvent(RegisterEvent.ON_REGISTER_ERROR);
                    }
                }
            });
        } catch (Exception e){
            postEvent(RegisterEvent.ON_REGISTER_ERROR);
        }
    }


    private void postEvent(int eventType){
        RegisterEvent registerEvent = new RegisterEvent();
        registerEvent.setEventType(eventType);
        EventBus.getDefault().post(registerEvent);
    }

    public void login(final String username, String password) {
        try {
            mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        postEvent(RegisterEvent.ON_REGISTER_SUCESS);
                        registerUserDB(username);
                    } else {
                        postEvent(RegisterEvent.ON_REGISTER_ERROR);
                    }
                }
            });
        }catch (Exception e){
            postEvent(RegisterEvent.ON_REGISTER_ERROR);
        }
    }

    private void registerUserDB(String username){
        final DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("usuarios");
        String key = userRef.push().getKey();
        User user = new User();
        user.setId(key);
        user.setUsername(username);
        userRef.child(key).setValue(username);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postEvent(RegisterEvent.ON_REGISTER_SUCESS);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                postEvent(RegisterEvent.ON_REGISTER_ERROR);
            }
        });


    }
}
