package it.localhost.app.mobile.learningandroid.helper.login;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.support.annotation.NonNull;

import bolts.Task;
import bolts.TaskCompletionSource;
import it.localhost.app.mobile.learningandroid.data.model.GenericAuthResult;

/**
 * @author vincenzo.petronio on 23/03/2018.
 */

public class GoogleFirebaseStrategy implements ILoginStrategy {

    @Override
    public Task<GenericAuthResult> login(String username, String psw) throws Exception {
        // raw generics type
        TaskCompletionSource<GenericAuthResult> completionSource = new TaskCompletionSource<>();
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(username, psw)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            completionSource.setResult(GenericAuthResult.newGenericAuthResult(task.getResult()));
                        } else {
                            completionSource.setError(task.getException());
                        }
                    }
                });
        return completionSource.getTask();
    }
}
