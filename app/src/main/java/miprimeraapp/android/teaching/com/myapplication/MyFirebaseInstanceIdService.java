package miprimeraapp.android.teaching.com.myapplication;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();


        Log.d("InstanceIdService", "EP! Token refreshed!");

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("InstanceIdService", "Refreshed token: " + refreshedToken);
    }
}