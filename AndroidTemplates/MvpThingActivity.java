#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")


public class ${Name}Activity extends BaseMvpActivity implements ${Name}MvpView {


    @Inject
    ${Name}MvpPresenter<${Name}MvpView> mPresenter;

 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.${LayouTName});
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);
        
    }

    @Override
    public void InitView() {

    }

 public static Intent getStartIntent(Context context) {
        return new Intent(context, ${Name}Activity.class);
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
