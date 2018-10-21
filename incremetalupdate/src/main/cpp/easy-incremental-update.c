#include <jni.h>

extern int bspatch_main(int argc, char *argv[]);

JNIEXPORT jboolean JNICALL
Java_jni_intremetalupdate_EasyIncrementalUpdate_patch(JNIEnv *env, jclass type,
                                                      jstring oldFilePath_,
                                                      jstring newFilePath_,
                                                      jstring patchPath_) {

    const char *ch[4] = {0};
    ch[0] = "bspatch";
    ch[1] = (*env)->GetStringUTFChars(env, oldFilePath_, 0);
    ch[2] = (*env)->GetStringUTFChars(env, newFilePath_, 0);
    ch[3] = (*env)->GetStringUTFChars(env, patchPath_, 0);

    int ret = bspatch_main(4, ch);

    (*env)->ReleaseStringUTFChars(env, oldFilePath_, ch[1]);
    (*env)->ReleaseStringUTFChars(env, newFilePath_, ch[2]);
    (*env)->ReleaseStringUTFChars(env, patchPath_, ch[3]);

    return !ret;
}