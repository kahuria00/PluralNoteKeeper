<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.alc40phase1" >

    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme" >
        <activity
            android:name=".MyProfile"
            android:label="@string/title_activity_my_profile"
            android:parentActivityName=".MainActivity"
            android:theme="@style/AppTheme.NoActionBar" >
            <meta-data
                android:name="android.support.PARENT_ACTIVITY"
                android:value="com.example.alc40phase1.MainActivity" />
        </activity>
        <activity
            android:name=".AboutALC"
            android:label="@string/title_activity_about_alc"
            android:parentActivityName=".MainActivity"
            android:theme="@style/AppTheme.NoActionBar" >
            <meta-data
                android:name="android.support.PARENT_ACTIVITY"
                android:value="com.example.alc40phase1.MainActivity" />
        </activity>
        <activity
            android:name=".MainActivity"
            android:label="@string/app_name"
            android:theme="@style/AppTheme.NoActionBar" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                