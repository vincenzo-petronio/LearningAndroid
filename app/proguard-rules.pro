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


-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
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


-dontwarn org.xmlpull.v1.**