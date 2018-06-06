# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Program Files (x86)\Android\android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Folder con il risultato: /build/outputs/mapping/release/

# Stampa nel file indicato le configurazioni a valle del merge e parsing
-printconfiguration proguard-merged-config.txt


-keepattributes Signature,RuntimeVisibleAnnotations,AnnotationDefault
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes EnclosingMethod, InnerClasses
-keepattributes SourceFile,LineNumberTable
-keepattributes Exceptions


-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase


-keep class com.thoughtworks.xstream.**{*;}
-dontwarn com.thoughtworks.xstream.**


-dontwarn org.htmlcleaner.JDomSerializer
-dontwarn org.htmlcleaner.HtmlCleanerForAnt


-dontwarn com.google.errorprone.annotations.*


-dontwarn org.jdom2.xpath.jaxen.**
-dontwarn org.jdom2.input.StAXEventBuilder
-dontwarn org.jdom2.input.StAXStreamBuilder
-dontwarn org.jdom2.output.StAXEventOutputter
-dontwarn org.jdom2.output.StAXStreamOutputter
-dontwarn org.jdom2.output.support.**


-dontwarn com.facebook.fbui.**
-dontwarn com.facebook.litho.**
-dontwarn android.text.StaticLayout
-dontwarn android.view.DisplayList
-dontwarn android.view.RenderNode
-dontwarn android.view.DisplayListCanvas
-dontwarn android.view.HardwareCanvas
-dontwarn com.facebook.litho.TransitionManager$*
-dontwarn com.facebook.litho.widget.RecyclerBinder$*
-keep class com.facebook.yoga.** {*;}


-dontwarn org.xmlpull.v1.**
-keep class org.xmlpull.v1.** { *; }


-dontwarn android.support.**
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }
-keep class android.support.v7.widget.RoundRectDrawable { *; }
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }
-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}
-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}
-keepclassmembers class android.support.v7.preference.PreferenceGroupAdapter {
    private ** mPreferenceLayouts;
}
-keepclassmembers class android.support.v7.preference.PreferenceGroupAdapter$PreferenceLayout {
    private int resId;
    private int widgetResId;
}
-keepclassmembers class android.support.graphics.drawable.VectorDrawableCompat$* {
   void set*(***);
   *** get*();
}
-keep class android.support.v8.renderscript.** { *; }


-keeppackagenames org.jsoup.nodes


-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# for DexGuard only
# -keepresourcexmlelements manifest/application/meta-data@value=GlideModule


-keep class it.localhost.app.mobile.learningandroid.data.model.** { *; }


-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer


-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}