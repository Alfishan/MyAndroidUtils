#if(${PACKAGE_NAME}&&${PACKAGE_NAME}!="")package ${PACKAGE_NAME};#end
#parse("File Header.java")

@PerActivity
public interface ${Name}MvpPresenter<V extends ${Name}MvpView, I extends ${Name}MvpInteractor> extends MvpPresenter<V, I> {

