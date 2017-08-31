
# Temp solution for [F.A.B Hides but Doesn't Show](https://stackoverflow.com/questions/42414531/f-a-b-hides-but-doesnt-show?answertab=active#tab-top)


###  Copy  [ScrollAwareFABBehavior](ScrollAwareFABBehavior.java) to your project


### Use it to your fab

```xml
 <android.support.design.widget.FloatingActionButton
            android:layout_width="wrap_content"
            app:fabSize="normal"
            app:borderWidth="8dp"
            android:id="@+id/add_expense"
            app:rippleColor="@color/black_magic"
            android:src="@mipmap/ic_launcher_round"
            app:layout_behavior="com.example.myapp.ScrollAwareFABBehavior"
            android:layout_gravity="bottom|end"
            android:layout_marginEnd="@dimen/fab_margin"
            android:layout_marginBottom="@dimen/fab_margin"
            android:layout_height="wrap_content"
            tools:ignore="UnusedAttribute"/>
```
