package b05studio.com.order_boss.view.util;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by young on 2017-05-16.
 */

public class FontConfig extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "fonts/NanumBarunpenR.ttf"))
                .addBold(Typekit.createFromAsset(this, "fonts/NanumBarunpenB.ttf"));

    }
}
