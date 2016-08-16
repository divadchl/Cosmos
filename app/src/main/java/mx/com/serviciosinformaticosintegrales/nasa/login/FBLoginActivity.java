package mx.com.serviciosinformaticosintegrales.nasa.login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.serviciosinformaticosintegrales.nasa.R;
import mx.com.serviciosinformaticosintegrales.nasa.RecyclerviewActivity;

public class FBLoginActivity extends AppCompatActivity implements FacebookCallback<LoginResult> {

    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1;
    @BindView(R.id.fb_login_button)
    LoginButton loginButton;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fblogin);
        ButterKnife.bind(this);

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, this);
        if(AccessToken.getCurrentAccessToken() != null)//verificar si existe una sesion en facebook
        {
            //Intent intent = new Intent(this, RecyclerviewActivity.class);
            //onActivityResult();
            //startActivity(intent);
            startActivity(new Intent(this, RecyclerviewActivity.class));
            Snackbar.make(findViewById(android.R.id.content), "Login2", Snackbar.LENGTH_SHORT).show();
        }
        else
        {
            Snackbar.make(findViewById(android.R.id.content), "Login3", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        Snackbar.make(findViewById(android.R.id.content), "Login", Snackbar.LENGTH_SHORT).show();
        startActivity(new Intent(this, RecyclerviewActivity.class));
    }

    @Override
    public void onCancel() {
        Snackbar.make(findViewById(android.R.id.content), "Cancel Login", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onError(FacebookException error) {
        Snackbar.make(findViewById(android.R.id.content), error.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
