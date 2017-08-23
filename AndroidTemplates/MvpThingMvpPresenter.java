#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

/**
 * Created by Alfishan@gmail.com on 24/3/17.
 */
@PerActivity
public interface ${Name}MvpPresenter<V extends ${Name}MvpView> extends MvpPresenter<V> {

 void Init();

}
