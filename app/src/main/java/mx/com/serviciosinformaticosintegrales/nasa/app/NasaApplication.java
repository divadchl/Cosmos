package mx.com.serviciosinformaticosintegrales.nasa.app;


import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.drawee.backends.pipeline.Fresco;

public class NasaApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        Fresco.initialize(this);
        FacebookSdk.sdkInitialize(this);
       // AppEventsLogger.activateApp(this);
    }
}
