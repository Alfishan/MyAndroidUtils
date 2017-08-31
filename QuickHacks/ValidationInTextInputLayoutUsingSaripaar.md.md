
# Validation using Saripaar  

Validate and showing error in TextInputLayout 

#### you can checkout [Saripaar git here](https://github.com/ragunathjawahar/android-saripaar)

### Gradle script

```gradle

compile 'com.github.ragunathjawahar:android-saripaar:android-saripaar-2.0.3'

```

### 1 Create TextInputLayoutAdapter 
```java
public class TextInputLayoutAdapter implements ViewDataAdapter<TextInputLayout, String> {

    @Override
    public String getData(final TextInputLayout til) {
        return getText(til);
    }

    private String getText(TextInputLayout til) {
        return til.getEditText().getText().toString();
    }
}
```

### 2 implementation 

```java
public class YourActivity extends AppCompatActivity implements Validator.ValidationListener  { 

 @NotEmpty
 @BindView(R.id.til_username)
 TextInputLayout mTilUsername; 

 private Validator mValidator;
 
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
         initValidator();
    }

    private void initValidator() {
        mValidator=new Validator(this);
        mValidator.setValidationListener(this);
        mValidator.registerAdapter(TextInputLayout.class, new TextInputLayoutAdapter());
        mValidator.setViewValidatedAction(view -> {
            if (view instanceof TextInputLayout) {
                ((TextInputLayout) view).setError("");
                ((TextInputLayout) view).setErrorEnabled(false);

            }
        });
    }
    
    
     @Override
    public void onValidationSucceeded() {
        

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        errors.get(0).getView().requestFocus();
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof TextInputLayout) {
                ((TextInputLayout) view).setError(message);
                ((TextInputLayout) view).setErrorEnabled(true);

            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
```


