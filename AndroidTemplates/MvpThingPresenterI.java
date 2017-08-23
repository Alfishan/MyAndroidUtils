#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

import javax.inject.Inject;


public class ${Name}Presenter<V extends ${Name}MvpView, I extends ${Name}MvpInteractor> extends BasePresenter<V, I> implements ${Name}MvpPresenter<V, I> {

    @Inject
    public ${Name}Presenter(EventBus eventBus,
                           CompositeDisposable compositeDisposable,
                           I mvpInteractor,
                           SchedulerProvider schedulerProvider,
                           NetworkUtil networkUtil) {
        super(eventBus,compositeDisposable,mvpInteractor, schedulerProvider, networkUtil);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }
}
