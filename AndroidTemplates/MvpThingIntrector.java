#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")


import javax.inject.Inject;

/**
 * Created by janisharali on 20/07/17.
 */

public class ${Name}Interactor extends BaseInteractor
        implements ${Name}MvpInteractor {

    private Context mContext;

    @Inject
    public ${Name}Interactor(@ApplicationContext Context context,
                            PreferencesHelper preferencesHelper,
                            ApiHelper apiHelper) {

        super(preferencesHelper, apiHelper);
        mContext = context;


    }

}
