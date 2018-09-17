# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontnote android.net.http.*
-dontnote org.apache.commons.codec.**
-dontnote org.apache.http.**

-keep class com.paynimo.android.payment.** { *; }
-dontwarn com.paynimo.android.payment.**

-keepclassmembernames class * {
    java.lang.Class class$(java.lang.String);
    java.lang.Class class$(java.lang.String, boolean);
}

-keep class retrofit.** {
    <fields>;
    <methods>;
}
-dontwarn retrofit.**

# Gson specific classes
-keep class sun.misc.Unsafe {
    <fields>;
    <methods>;
}

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.** {
    <fields>;
    <methods>;
}

# #---------------End: proguard configuration for Gson  ----------
-keep class de.greenrobot.event.** {
    <fields>;
    <methods>;
}

-keep class com.squareup.** {
    <fields>;
    <methods>;
}

-keep class android.support.v4.** {
    <fields>;
    <methods>;
}

-keep class android.support.v7.** {
    <fields>;
    <methods>;
}

-keep class android.service.media.MediaBrowserService {
    <fields>;
    <methods>;
}
-dontwarn android.service.media.MediaBrowserService

-keep class org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement {
     <fields>;
     <methods>;
}
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

-keep interface android.service.media.IMediaBrowserServiceCallbacks {
    <fields>;
    <methods>;
}

-keep class io.card.payment.** {
    <fields>;
    <methods>;
}

-keep class okio.** {
    <fields>;
    <methods>;
}

-keep class java.nio.file.** {
    <fields>;
    <methods>;
}
-dontwarn java.nio.file.**