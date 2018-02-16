<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layout_behavior="@string/appbar_scrolling_view_behavior"
    tools:context="import com.example.ims.aksisstent02.services.Notenrechner"
    tools:showIn="@layout/activity_notenrechner">


    <ListView
        android:id="@+id/listView"
        android:layout_width="368dp"
        android:layout_height="266dp"
        android:layout_marginBottom="8dp"
        android:layout_marginTop="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/txtDesc" />

    <EditText
        android:id="@+id/txtSubject"
        android:layout_width="77dp"
        android:layout_height="wrap_content"
        android:layout_marginBottom="8dp"
        android:layout_marginLeft="24dp"
        android:layout_marginTop="8dp"
        android:ems="10"
        android:inputType="textPersonName"
        android:text="Fach"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.28" />

    <EditText
        android:id="@+id/txtMark"
        android:layout_width="57dp"
        android:layout_height="wrap_content"
        android:layout_marginBottom="8dp"
        android:layout_marginRight="28dp"
        android:layout_marginTop="8dp"
        android:ems="10"
        android:inputType="numberDecimal"
        android:text="Note"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.28" />

    <EditText
        android:id="@+id/txtDesc"
        android:layout_width="123dp"
        android:layout_height="wrap_content"
        android:layout_marginBottom="8dp"
        android:layout_marginTop="132dp"
        android:ems="10"
        android:inputType="textPersonName"
        android:text="Beschreibung"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/txtMark"
        app:layout_constraintHorizontal_bias="0.238"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintStart_toEndOf="@+id/txtSubject"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.006" />

    <Button
        android:id="@+id/btnAdd"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="8dp"
        android:layout_marginTop="8dp"
        android:background="@android:color/holo_orange_dark"
        android:text="+"
        android:textColor="@android:color/background_light"
        android:textSize="40sp"
        app:layout_constraintBottom_toTopOf="@+id/listView"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.308" />

</android.support.constraint.ConstraintLayout>
