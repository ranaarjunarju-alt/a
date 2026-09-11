package com.bytedance.crash.dart;

import com.bytedance.crash.CrashType;
import com.bytedance.crash.IUploadCallback;
import com.bytedance.crash.entity.CrashBody;
import com.bytedance.crash.monitor.AppMonitor;
import com.bytedance.crash.monitor.MonitorManager;
import com.bytedance.crash.runtime.DefaultWorkThread;
import com.bytedance.crash.upload.CrashUploader;
import com.bytedance.crash.upload.UploaderUrl;
import java.util.Map;

/* loaded from: classes38.dex */
public class DartCrash {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f38616a;

    /* loaded from: classes4.dex */
    public static class UploadProcess implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final long f38617a;
        public final String b;

        /* renamed from: c, reason: collision with root package name */
        public final Map<? extends String, ? extends String> f38618c;

        /* renamed from: d, reason: collision with root package name */
        public final Map<String, String> f38619d;
        public final IUploadCallback e;
        public final String f;

        public UploadProcess(String str, long j, String str2, Map<? extends String, ? extends String> map, Map<String, String> map2, IUploadCallback iUploadCallback) {
            this.f38617a = j;
            this.b = str2;
            this.f38618c = map;
            this.f38619d = map2;
            this.e = iUploadCallback;
            this.f = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppMonitor appMonitor = MonitorManager.f38762a;
            if (appMonitor == null) {
                return;
            }
            try {
                CrashBody crashBodyA = DartSummary.a(appMonitor, this.f, this.f38617a, this.b, this.f38618c, this.f38619d);
                CrashUploader.h(UploaderUrl.f(CrashType.DART, crashBodyA.b.f38670a), crashBodyA.f38668a);
            } catch (Throwable unused) {
            }
            IUploadCallback iUploadCallback = this.e;
            if (iUploadCallback != null) {
                try {
                    iUploadCallback.a();
                } catch (Throwable unused2) {
                }
            }
        }
    }

    public static void a(String str, Map<? extends String, ? extends String> map, Map<String, String> map2, IUploadCallback iUploadCallback) {
        if (f38616a) {
            return;
        }
        DefaultWorkThread.post(new UploadProcess(Thread.currentThread().getName(), System.currentTimeMillis(), str, map, map2, iUploadCallback));
    }
}