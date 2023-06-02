#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_mct_NativeStore_stringFromJNI(JNIEnv *env, jobject thiz) {
    std::string hello ="https://rmapi.mct.com.sg/";
  //  std::string hello ="https://mctremit.com/";
    return env->NewStringUTF(hello.c_str());
}
