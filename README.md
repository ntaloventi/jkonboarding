# jkonboarding
[![](https://jitpack.io/v/ntaloventi/jkonboarding.svg)](https://jitpack.io/#ntaloventi/jkonboarding)
![Java CI with Gradle](https://github.com/ntaloventi/jkonboarding/workflows/Java%20CI%20with%20Gradle/badge.svg)

OnBoarding Made Simple


>Gradle
```
dependencies {
   api 'com.github.ntaloventi:jkonboarding:0.1.0'
}
```

>Example
```
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.recyclerview.widget.RecyclerView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/recyclerView"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```

```
ArrayList<Integer> bgColors = getColors();
JkOnBoarding jkOnBoarding = new JkOnBoarding(MainActivity.this, items, recyclerView, bgColors);
jkOnBoarding.injectView();
```

