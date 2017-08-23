#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")


/**
 * Created by Alfishan@gmail.com on 24/3/17.
 */

public class ${Name}Presenter<V extends ${Name}MvpView> extends BasePresenter<V> implements ${Name}MvpPresenter<V> {


    @Inject
    public ${Name}Presenter(DataManager dataManager, EventBus eventBus, CompositeDisposable compositeDisposable) {
        super(dataManager, eventBus, compositeDisposable);

    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void Init() {
        
    }

}