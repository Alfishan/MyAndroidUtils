package alfishan.customexpandablefab;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import alfishan.customexpandablefab.utils.FontUtil;

/**
 * Created by root on 13/9/17.
 */

public class MyApp extends Application {

    CalligraphyConfig mCalligraphyConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        mCalligraphyConfig=new CalligraphyConfig.Builder().setDefaultFontPath(FontUtil.FONT_Montserrat_REGULAR).setFontAttrId(R.attr.fontPath).build();
        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }
}
