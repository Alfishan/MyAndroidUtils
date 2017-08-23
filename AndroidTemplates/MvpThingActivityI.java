#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")


public class ${Name}Activity extends BaseActivity implements ${Name}MvpView {

    @Inject
    ${Name}MvpPresenter<${Name}MvpView, ${Name}MvpInteractor> mPresenter;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, ${Name}Activity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.${LayouTName});
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(${Name}Activity.this);
    }

    @Override
    public void initView() {

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }



}
