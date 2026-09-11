package com.vega.launcher.network.interceptors;

import android.os.HandlerThread;
import com.bytedance.bpea.transmit.delegate.BPEAHandler;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.bytedance.ttnet.http.RequestContext;
import com.ss.android.ugc.bytex.pthread.base.PThreadExecutorsUtils;
import com.ss.android.ugc.bytex.thread_rename.base.DefaultThreadFactory;
import com.vega.core.context.SPIService;
import com.vega.core.net.NetworkManagerWrapper;
import com.vega.log.BLog;
import com.vega.performance.PerformanceManagerHelper;
import com.vega.templator.settings.TemplatorConfigProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import kotlin.Lazy;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsJVMKt;

/* loaded from: classes20.dex */
public final class TimeOutSettingInterceptor implements Interceptor {
    public static final int s;
    public static final int t;

    /* renamed from: c, reason: collision with root package name */
    public String f107418c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f107419d;
    public boolean e;
    public String f;

    /* renamed from: g, reason: collision with root package name */
    public String[] f107420g;
    public boolean h;
    public String i;
    public String[] j;
    public boolean k;
    public List<PreconnectConfig> l;
    public long m;
    public final ArrayList<String> o;
    public final ArrayList<String> p;
    public final ArrayList<String> q;
    public final Lazy r;
    public final HashMap<String, Integer> b = new HashMap<>();
    public final Map<String, Long> n = new LinkedHashMap();

    /* renamed from: a, reason: collision with root package name */
    public final HashMap<String, Integer[]> f107417a = new HashMap<>();

    /* loaded from: classes.dex */
    public static final class Companion {
    }

    /* loaded from: classes18.dex */
    public static final class DomainKeepAliveHelper {
        public static volatile HandlerThread b;

        /* renamed from: c, reason: collision with root package name */
        public static volatile BPEAHandler f107422c;

        /* renamed from: a, reason: collision with root package name */
        public static final DomainKeepAliveHelper f107421a = new DomainKeepAliveHelper();

        /* renamed from: d, reason: collision with root package name */
        public static final Map<String, Runnable> f107423d = new LinkedHashMap();

        public static final void b(long j, final String str, final String str2) {
            if (str.length() == 0) {
                return;
            }
            f107421a.a();
            BPEAHandler bPEAHandler = f107422c;
            if (bPEAHandler == null) {
                return;
            }
            Map<String, Runnable> map = f107423d;
            synchronized (map) {
                Runnable runnableRemove = map.remove(str);
                if (runnableRemove != null) {
                    try {
                        bPEAHandler.removeCallbacks(runnableRemove);
                    } catch (Throwable unused) {
                    }
                }
            }
            Runnable runnable = new Runnable() { // from class: X.0lu
                @Override // java.lang.Runnable
                public final void run() {
                    String str3 = "https://" + str + '/' + str2;
                    try {
                        NetworkManagerWrapper.f79356a.getClass();
                        NetworkManagerWrapper.g(str3, null, false);
                    } catch (Exception e) {
                        BLog.w("DomainKeepAliveHelper", "keepalive speed failed, url=" + str3 + ", err=" + e.getMessage());
                    }
                }
            };
            Map<String, Runnable> map2 = f107423d;
            synchronized (map2) {
                Runnable runnable2 = (Runnable) ((LinkedHashMap) map2).get(str);
                if (runnable2 != null) {
                    bPEAHandler.removeCallbacks(runnable2);
                }
                map2.put(str, runnable);
            }
            try {
                bPEAHandler.postDelayed(runnable, j);
            } catch (Throwable unused2) {
            }
        }

        public final synchronized void a() {
            if (b == null || f107422c == null) {
                HandlerThread handlerThread = new HandlerThread("DomainKeepAliveThread", 10);
                handlerThread.start();
                b = handlerThread;
                f107422c = new BPEAHandler(handlerThread.getLooper());
                BLog.i("DomainKeepAliveHelper", "keepalive thread started");
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class DropUrlException extends Exception {
        public DropUrlException() {
            super("drop special url");
        }
    }

    /* loaded from: classes37.dex */
    public static final class PreconnectConfig {

        /* renamed from: a, reason: collision with root package name */
        public final String f107424a;
        public final List<String> b;

        public PreconnectConfig(String str, List<String> list) {
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(list, "");
            this.f107424a = str;
            this.b = list;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PreconnectConfig)) {
                return false;
            }
            PreconnectConfig preconnectConfig = (PreconnectConfig) obj;
            return Intrinsics.areEqual(this.f107424a, preconnectConfig.f107424a) && Intrinsics.areEqual(this.b, preconnectConfig.b);
        }

        public final int hashCode() {
            return (this.f107424a.hashCode() * 31) + this.b.hashCode();
        }

        public final String toString() {
            return "PreconnectConfig(triggerKeyword=" + this.f107424a + ", hosts=" + this.b + ')';
        }
    }

    static {
        new Companion();
        s = 1;
        t = 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:132:0x0372 A[Catch: Exception -> 0x0434, TryCatch #1 {Exception -> 0x0434, blocks: (B:16:0x006c, B:18:0x0086, B:19:0x0089, B:22:0x0092, B:24:0x0096, B:26:0x00a7, B:27:0x00af, B:29:0x00b5, B:33:0x00c7, B:34:0x00d1, B:32:0x00c2, B:35:0x00db, B:37:0x00df, B:39:0x00e3, B:41:0x00f4, B:42:0x00fc, B:44:0x0102, B:48:0x0114, B:49:0x011e, B:47:0x010f, B:50:0x0128, B:54:0x013f, B:55:0x0159, B:57:0x015f, B:58:0x0171, B:59:0x017a, B:61:0x0180, B:63:0x018d, B:64:0x0191, B:65:0x0195, B:67:0x019b, B:68:0x01b8, B:70:0x01be, B:71:0x01d0, B:72:0x01d9, B:74:0x01df, B:76:0x01ec, B:77:0x01f0, B:79:0x01f6, B:80:0x0239, B:81:0x023b, B:85:0x0249, B:86:0x025e, B:88:0x0264, B:89:0x0276, B:90:0x027f, B:92:0x0285, B:94:0x0292, B:95:0x0296, B:96:0x029a, B:98:0x02a0, B:99:0x02bb, B:101:0x02c1, B:102:0x02d3, B:104:0x02d9, B:106:0x02eb, B:110:0x02f4, B:112:0x02fa, B:113:0x031d, B:116:0x0323, B:118:0x0327, B:120:0x032d, B:122:0x033c, B:123:0x0344, B:125:0x034a, B:129:0x035c, B:130:0x0366, B:132:0x0372, B:134:0x0383, B:135:0x038b, B:137:0x0391, B:141:0x03a3, B:142:0x03ad, B:140:0x039e, B:128:0x0357, B:13:0x005e, B:15:0x006a, B:143:0x042c, B:144:0x0433, B:12:0x0057, B:4:0x002b, B:6:0x0034, B:7:0x0039, B:9:0x004b, B:10:0x0052), top: B:149:0x002b, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public TimeOutSettingInterceptor() throws java.lang.NumberFormatException {
        /*
            r15 = this;
            r15.<init>()
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r15.b = r0
            java.lang.String r0 = "ies/speed/"
            r15.f107418c = r0
            java.util.List r0 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()
            r15.l = r0
            r0 = 60000(0xea60, double:2.9644E-319)
            r15.m = r0
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            r15.n = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r15.f107417a = r0
            java.lang.String r7 = ""
            java.lang.Class<com.vega.templator.settings.HttpTimeoutConfig> r3 = com.vega.templator.settings.HttpTimeoutConfig.class
            com.vega.core.settings.obtain.SettingsObtainer r0 = com.vega.core.settings.obtain.SettingsObtainer.f79519a     // Catch: java.lang.Throwable -> L56
            r0.getClass()     // Catch: java.lang.Throwable -> L56
            boolean r0 = com.vega.core.settings.obtain.SettingsObtainer.f79521d     // Catch: java.lang.Throwable -> L56
            if (r0 == 0) goto L39
            java.lang.Object r8 = com.vega.core.settings.obtain.SettingsObtainer.b(r3)     // Catch: java.lang.Throwable -> L56
            goto L6c
        L39:
            com.vega.kv.KvStorage r2 = com.vega.core.settings.obtain.SettingsObtainer.c()     // Catch: java.lang.Throwable -> L56
            java.lang.String r1 = r3.getName()     // Catch: java.lang.Throwable -> L56
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r7)     // Catch: java.lang.Throwable -> L56
            r0 = 0
            java.lang.String r1 = r2.i(r1, r0)     // Catch: java.lang.Throwable -> L56
            if (r1 == 0) goto L52
            java.lang.Class<com.vega.templator.settings.HttpTimeoutConfig> r0 = com.vega.templator.settings.HttpTimeoutConfig.class
            java.lang.Object r8 = com.vega.core.ext.ExtentionKt.toObject(r1, r0)     // Catch: java.lang.Throwable -> L56
            goto L6c
        L52:
            kotlin.Result.m17090constructorimpl(r0)     // Catch: java.lang.Throwable -> L56
            goto L5e
        L56:
            r0 = move-exception
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)     // Catch: java.lang.Exception -> L434
            kotlin.Result.m17090constructorimpl(r0)     // Catch: java.lang.Exception -> L434
        L5e:
            java.lang.Object r0 = r3.newInstance()     // Catch: java.lang.Exception -> L434
            com.bytedance.news.common.settings.api.annotation.IDefaultValueProvider r0 = (com.bytedance.news.common.settings.api.annotation.IDefaultValueProvider) r0     // Catch: java.lang.Exception -> L434
            java.lang.Object r8 = r0.create()     // Catch: java.lang.Exception -> L434
            if (r8 == 0) goto L42c
            com.vega.templator.settings.HttpTimeoutConfig r8 = (com.vega.templator.settings.HttpTimeoutConfig) r8     // Catch: java.lang.Exception -> L434
        L6c:
            com.vega.templator.settings.HttpTimeoutConfig r8 = (com.vega.templator.settings.HttpTimeoutConfig) r8     // Catch: java.lang.Exception -> L434
            boolean r0 = r8.timeout_opt_by_domain     // Catch: java.lang.Exception -> L434
            r15.f107419d = r0     // Catch: java.lang.Exception -> L434
            boolean r0 = r8.checkSpecialUrl     // Catch: java.lang.Exception -> L434
            r15.e = r0     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = r8.checkSpecialUrlList     // Catch: java.lang.Exception -> L434
            r15.f = r0     // Catch: java.lang.Exception -> L434
            boolean r0 = r8.dropSpecialUrl     // Catch: java.lang.Exception -> L434
            r15.h = r0     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = r8.dropSpecialUrlList     // Catch: java.lang.Exception -> L434
            r15.i = r0     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = r8.forbiddenReportKeyword     // Catch: java.lang.Exception -> L434
            if (r0 == 0) goto L89
            com.lm.components.network.init.NetworkMonitorHelper.d(r0)     // Catch: java.lang.Exception -> L434
        L89:
            boolean r0 = r15.e     // Catch: java.lang.Exception -> L434
            r6 = 1
            r10 = 6
            r5 = 0
            java.lang.String r2 = ","
            if (r0 == 0) goto Ldb
            java.lang.String r1 = r15.f     // Catch: java.lang.Exception -> L434
            if (r1 == 0) goto Ldb
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> L434
            java.lang.String[] r0 = new java.lang.String[]{r2}     // Catch: java.lang.Exception -> L434
            java.util.List r3 = X.C93472yG.M(r1, r0, r5, r10)     // Catch: java.lang.Exception -> L434
            boolean r0 = r3.isEmpty()     // Catch: java.lang.Exception -> L434
            if (r0 != 0) goto Lc2
            int r0 = r3.size()     // Catch: java.lang.Exception -> L434
            java.util.ListIterator r1 = r3.listIterator(r0)     // Catch: java.lang.Exception -> L434
        Laf:
            boolean r0 = r1.hasPrevious()     // Catch: java.lang.Exception -> L434
            if (r0 == 0) goto Lc2
            java.lang.Object r0 = r1.previous()     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> L434
            int r0 = r0.length()     // Catch: java.lang.Exception -> L434
            if (r0 != 0) goto Lc7
            goto Laf
        Lc2:
            java.util.List r1 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()     // Catch: java.lang.Exception -> L434
            goto Ld1
        Lc7:
            int r0 = r1.nextIndex()     // Catch: java.lang.Exception -> L434
            int r0 = r0 + 1
            java.util.List r1 = kotlin.collections.CollectionsKt___CollectionsKt.take(r3, r0)     // Catch: java.lang.Exception -> L434
        Ld1:
            java.lang.String[] r0 = new java.lang.String[r5]     // Catch: java.lang.Exception -> L434
            java.lang.Object[] r0 = r1.toArray(r0)     // Catch: java.lang.Exception -> L434
            java.lang.String[] r0 = (java.lang.String[]) r0     // Catch: java.lang.Exception -> L434
            r15.f107420g = r0     // Catch: java.lang.Exception -> L434
        Ldb:
            boolean r0 = r15.h     // Catch: java.lang.Exception -> L434
            if (r0 == 0) goto L128
            java.lang.String r1 = r15.i     // Catch: java.lang.Exception -> L434
            if (r1 == 0) goto L128
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> L434
            java.lang.String[] r0 = new java.lang.String[]{r2}     // Catch: java.lang.Exception -> L434
            java.util.List r3 = X.C93472yG.M(r1, r0, r5, r10)     // Catch: java.lang.Exception -> L434
            boolean r0 = r3.isEmpty()     // Catch: java.lang.Exception -> L434
            if (r0 != 0) goto L10f
            int r0 = r3.size()     // Catch: java.lang.Exception -> L434
            java.util.ListIterator r1 = r3.listIterator(r0)     // Catch: java.lang.Exception -> L434
        Lfc:
            boolean r0 = r1.hasPrevious()     // Catch: java.lang.Exception -> L434
            if (r0 == 0) goto L10f
            java.lang.Object r0 = r1.previous()     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> L434
            int r0 = r0.length()     // Catch: java.lang.Exception -> L434
            if (r0 != 0) goto L114
            goto Lfc
        L10f:
            java.util.List r1 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()     // Catch: java.lang.Exception -> L434
            goto L11e
        L114:
            int r0 = r1.nextIndex()     // Catch: java.lang.Exception -> L434
            int r0 = r0 + 1
            java.util.List r1 = kotlin.collections.CollectionsKt___CollectionsKt.take(r3, r0)     // Catch: java.lang.Exception -> L434
        L11e:
            java.lang.String[] r0 = new java.lang.String[r5]     // Catch: java.lang.Exception -> L434
            java.lang.Object[] r0 = r1.toArray(r0)     // Catch: java.lang.Exception -> L434
            java.lang.String[] r0 = (java.lang.String[]) r0     // Catch: java.lang.Exception -> L434
            r15.j = r0     // Catch: java.lang.Exception -> L434
        L128:
            boolean r9 = r8.forcePreconnectOpt     // Catch: java.lang.Exception -> L434
            r15.k = r9     // Catch: java.lang.Exception -> L434
            java.lang.String r12 = r8.forcePreconnectConfig     // Catch: java.lang.Exception -> L434
            int r0 = r8.forcePreconnectInterval     // Catch: java.lang.Exception -> L434
            long r0 = (long) r0     // Catch: java.lang.Exception -> L434
            r3 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 * r3
            r15.m = r0     // Catch: java.lang.Exception -> L434
            java.lang.String r4 = "TimeOutSettingInterceptor"
            r3 = 2
            r11 = 10
            if (r9 == 0) goto L23b
            if (r12 == 0) goto L23b
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch: java.lang.Exception -> L434
            r9.<init>()     // Catch: java.lang.Exception -> L434
            java.lang.String[] r0 = new java.lang.String[]{r2}     // Catch: java.lang.Exception -> L434
            java.util.List r12 = X.C93472yG.M(r12, r0, r5, r10)     // Catch: java.lang.Exception -> L434
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Exception -> L434
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r12, r11)     // Catch: java.lang.Exception -> L434
            r1.<init>(r0)     // Catch: java.lang.Exception -> L434
            java.util.Iterator r12 = r12.iterator()     // Catch: java.lang.Exception -> L434
        L159:
            boolean r0 = r12.hasNext()     // Catch: java.lang.Exception -> L434
            if (r0 == 0) goto L171
            java.lang.Object r0 = r12.next()     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> L434
            java.lang.CharSequence r0 = kotlin.text.StringsKt__StringsKt.trim(r0)     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L434
            r1.add(r0)     // Catch: java.lang.Exception -> L434
            goto L159
        L171:
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch: java.lang.Exception -> L434
            r12.<init>()     // Catch: java.lang.Exception -> L434
            java.util.Iterator r13 = r1.iterator()     // Catch: java.lang.Exception -> L434
        L17a:
            boolean r0 = r13.hasNext()     // Catch: java.lang.Exception -> L434
            if (r0 == 0) goto L191
            java.lang.Object r1 = r13.next()     // Catch: java.lang.Exception -> L434
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> L434
            int r0 = r0.length()     // Catch: java.lang.Exception -> L434
            if (r0 <= 0) goto L17a
            r12.add(r1)     // Catch: java.lang.Exception -> L434
            goto L17a
        L191:
            java.util.Iterator r14 = r12.iterator()     // Catch: java.lang.Exception -> L434
        L195:
            boolean r0 = r14.hasNext()     // Catch: java.lang.Exception -> L434
            if (r0 == 0) goto L239
            java.lang.Object r1 = r14.next()     // Catch: java.lang.Exception -> L434
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = "|"
            java.lang.String[] r0 = new java.lang.String[]{r0}     // Catch: java.lang.Exception -> L434
            java.util.List r1 = X.C93472yG.M(r1, r0, r5, r10)     // Catch: java.lang.Exception -> L434
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch: java.lang.Exception -> L434
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r1, r11)     // Catch: java.lang.Exception -> L434
            r12.<init>(r0)     // Catch: java.lang.Exception -> L434
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Exception -> L434
        L1b8:
            boolean r0 = r1.hasNext()     // Catch: java.lang.Exception -> L434
            if (r0 == 0) goto L1d0
            java.lang.Object r0 = r1.next()     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> L434
            java.lang.CharSequence r0 = kotlin.text.StringsKt__StringsKt.trim(r0)     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L434
            r12.add(r0)     // Catch: java.lang.Exception -> L434
            goto L1b8
        L1d0:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Exception -> L434
            r1.<init>()     // Catch: java.lang.Exception -> L434
            java.util.Iterator r13 = r12.iterator()     // Catch: java.lang.Exception -> L434
        L1d9:
            boolean r0 = r13.hasNext()     // Catch: java.lang.Exception -> L434
            if (r0 == 0) goto L1f0
            java.lang.Object r12 = r13.next()     // Catch: java.lang.Exception -> L434
            r0 = r12
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> L434
            int r0 = r0.length()     // Catch: java.lang.Exception -> L434
            if (r0 <= 0) goto L1d9
            r1.add(r12)     // Catch: java.lang.Exception -> L434
            goto L1d9
        L1f0:
            int r0 = r1.size()     // Catch: java.lang.Exception -> L434
            if (r0 < r3) goto L195
            java.lang.Object r13 = r1.get(r5)     // Catch: java.lang.Exception -> L434
            java.lang.String r13 = (java.lang.String) r13     // Catch: java.lang.Exception -> L434
            int r0 = r1.size()     // Catch: java.lang.Exception -> L434
            java.util.List r1 = r1.subList(r6, r0)     // Catch: java.lang.Exception -> L434
            com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$PreconnectConfig r0 = new com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$PreconnectConfig     // Catch: java.lang.Exception -> L434
            r0.<init>(r13, r1)     // Catch: java.lang.Exception -> L434
            r9.add(r0)     // Catch: java.lang.Exception -> L434
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L434
            r12.<init>()     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = "forcePreconnect: triggerKeyword="
            r12.append(r0)     // Catch: java.lang.Exception -> L434
            r12.append(r13)     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = ", hosts="
            r12.append(r0)     // Catch: java.lang.Exception -> L434
            r12.append(r1)     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = ", interval="
            r12.append(r0)     // Catch: java.lang.Exception -> L434
            long r0 = r15.m     // Catch: java.lang.Exception -> L434
            r12.append(r0)     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = "ms"
            r12.append(r0)     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = r12.toString()     // Catch: java.lang.Exception -> L434
            com.vega.log.BLog.i(r4, r0)     // Catch: java.lang.Exception -> L434
            goto L195
        L239:
            r15.l = r9     // Catch: java.lang.Exception -> L434
        L23b:
            java.lang.String r1 = r8.domainKeepAliveConfig     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = r8.domainKeepAlivePath     // Catch: java.lang.Exception -> L434
            r15.f107418c = r0     // Catch: java.lang.Exception -> L434
            boolean r0 = r8.forceKeepAliveOpt     // Catch: java.lang.Exception -> L434
            java.lang.String r9 = ":"
            if (r0 == 0) goto L31d
            if (r1 == 0) goto L31d
            java.lang.String[] r0 = new java.lang.String[]{r2}     // Catch: java.lang.Exception -> L434
            java.util.List r12 = X.C93472yG.M(r1, r0, r5, r10)     // Catch: java.lang.Exception -> L434
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Exception -> L434
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r12, r11)     // Catch: java.lang.Exception -> L434
            r1.<init>(r0)     // Catch: java.lang.Exception -> L434
            java.util.Iterator r12 = r12.iterator()     // Catch: java.lang.Exception -> L434
        L25e:
            boolean r0 = r12.hasNext()     // Catch: java.lang.Exception -> L434
            if (r0 == 0) goto L276
            java.lang.Object r0 = r12.next()     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> L434
            java.lang.CharSequence r0 = kotlin.text.StringsKt__StringsKt.trim(r0)     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L434
            r1.add(r0)     // Catch: java.lang.Exception -> L434
            goto L25e
        L276:
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch: java.lang.Exception -> L434
            r12.<init>()     // Catch: java.lang.Exception -> L434
            java.util.Iterator r13 = r1.iterator()     // Catch: java.lang.Exception -> L434
        L27f:
            boolean r0 = r13.hasNext()     // Catch: java.lang.Exception -> L434
            if (r0 == 0) goto L296
            java.lang.Object r1 = r13.next()     // Catch: java.lang.Exception -> L434
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> L434
            int r0 = r0.length()     // Catch: java.lang.Exception -> L434
            if (r0 <= 0) goto L27f
            r12.add(r1)     // Catch: java.lang.Exception -> L434
            goto L27f
        L296:
            java.util.Iterator r14 = r12.iterator()     // Catch: java.lang.Exception -> L434
        L29a:
            boolean r0 = r14.hasNext()     // Catch: java.lang.Exception -> L434
            if (r0 == 0) goto L31d
            java.lang.Object r1 = r14.next()     // Catch: java.lang.Exception -> L434
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Exception -> L434
            java.lang.String[] r0 = new java.lang.String[]{r9}     // Catch: java.lang.Exception -> L434
            java.util.List r12 = X.C93472yG.M(r1, r0, r5, r10)     // Catch: java.lang.Exception -> L434
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Exception -> L434
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r12, r11)     // Catch: java.lang.Exception -> L434
            r1.<init>(r0)     // Catch: java.lang.Exception -> L434
            java.util.Iterator r12 = r12.iterator()     // Catch: java.lang.Exception -> L434
        L2bb:
            boolean r0 = r12.hasNext()     // Catch: java.lang.Exception -> L434
            if (r0 == 0) goto L2d3
            java.lang.Object r0 = r12.next()     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> L434
            java.lang.CharSequence r0 = kotlin.text.StringsKt__StringsKt.trim(r0)     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L434
            r1.add(r0)     // Catch: java.lang.Exception -> L434
            goto L2bb
        L2d3:
            int r0 = r1.size()     // Catch: java.lang.Exception -> L434
            if (r0 < r3) goto L29a
            java.lang.Object r13 = r1.get(r5)     // Catch: java.lang.Exception -> L434
            java.lang.String r13 = (java.lang.String) r13     // Catch: java.lang.Exception -> L434
            java.lang.Object r0 = r1.get(r6)     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> L434
            java.lang.Integer r12 = kotlin.text.StringsKt__StringNumberConversionsKt.toIntOrNull(r0)     // Catch: java.lang.Exception -> L434
            if (r13 == 0) goto L29a
            int r0 = r13.length()     // Catch: java.lang.Exception -> L434
            if (r0 != 0) goto L2f2
            goto L29a
        L2f2:
            if (r12 == 0) goto L29a
            int r0 = r12.intValue()     // Catch: java.lang.Exception -> L434
            if (r0 <= 0) goto L29a
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r15.b     // Catch: java.lang.Exception -> L434
            r0.put(r13, r12)     // Catch: java.lang.Exception -> L434
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L434
            r1.<init>()     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = "keepalive Config="
            r1.append(r0)     // Catch: java.lang.Exception -> L434
            r1.append(r13)     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = ", delayMs="
            r1.append(r0)     // Catch: java.lang.Exception -> L434
            r1.append(r12)     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Exception -> L434
            com.vega.log.BLog.i(r4, r0)     // Catch: java.lang.Exception -> L434
            goto L29a
        L31d:
            boolean r0 = r15.f107419d     // Catch: java.lang.Exception -> L434
            if (r0 != 0) goto L323
            goto L434
        L323:
            java.lang.String r1 = r8.domainTimeoutConfig     // Catch: java.lang.Exception -> L434
            if (r1 == 0) goto L434
            int r0 = r1.length()     // Catch: java.lang.Exception -> L434
            if (r0 <= 0) goto L434
            kotlin.text.Regex r0 = new kotlin.text.Regex     // Catch: java.lang.Exception -> L434
            r0.<init>(r2)     // Catch: java.lang.Exception -> L434
            java.util.List r2 = r0.split(r1, r5)     // Catch: java.lang.Exception -> L434
            boolean r0 = r2.isEmpty()     // Catch: java.lang.Exception -> L434
            if (r0 != 0) goto L357
            int r0 = r2.size()     // Catch: java.lang.Exception -> L434
            java.util.ListIterator r1 = r2.listIterator(r0)     // Catch: java.lang.Exception -> L434
        L344:
            boolean r0 = r1.hasPrevious()     // Catch: java.lang.Exception -> L434
            if (r0 == 0) goto L357
            java.lang.Object r0 = r1.previous()     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> L434
            int r0 = r0.length()     // Catch: java.lang.Exception -> L434
            if (r0 != 0) goto L35c
            goto L344
        L357:
            java.util.List r1 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()     // Catch: java.lang.Exception -> L434
            goto L366
        L35c:
            int r0 = r1.nextIndex()     // Catch: java.lang.Exception -> L434
            int r0 = r0 + 1
            java.util.List r1 = kotlin.collections.CollectionsKt___CollectionsKt.take(r2, r0)     // Catch: java.lang.Exception -> L434
        L366:
            java.lang.String[] r0 = new java.lang.String[r5]     // Catch: java.lang.Exception -> L434
            java.lang.Object[] r8 = r1.toArray(r0)     // Catch: java.lang.Exception -> L434
            java.lang.String[] r8 = (java.lang.String[]) r8     // Catch: java.lang.Exception -> L434
            int r2 = r8.length     // Catch: java.lang.Exception -> L434
            r1 = 0
        L370:
            if (r1 >= r2) goto L434
            r10 = r8[r1]     // Catch: java.lang.Exception -> L434
            kotlin.text.Regex r0 = new kotlin.text.Regex     // Catch: java.lang.Exception -> L434
            r0.<init>(r9)     // Catch: java.lang.Exception -> L434
            java.util.List r10 = r0.split(r10, r5)     // Catch: java.lang.Exception -> L434
            boolean r0 = r10.isEmpty()     // Catch: java.lang.Exception -> L434
            if (r0 != 0) goto L39e
            int r0 = r10.size()     // Catch: java.lang.Exception -> L434
            java.util.ListIterator r11 = r10.listIterator(r0)     // Catch: java.lang.Exception -> L434
        L38b:
            boolean r0 = r11.hasPrevious()     // Catch: java.lang.Exception -> L434
            if (r0 == 0) goto L39e
            java.lang.Object r0 = r11.previous()     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> L434
            int r0 = r0.length()     // Catch: java.lang.Exception -> L434
            if (r0 != 0) goto L3a3
            goto L38b
        L39e:
            java.util.List r10 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()     // Catch: java.lang.Exception -> L434
            goto L3ad
        L3a3:
            int r0 = r11.nextIndex()     // Catch: java.lang.Exception -> L434
            int r0 = r0 + 1
            java.util.List r10 = kotlin.collections.CollectionsKt___CollectionsKt.take(r10, r0)     // Catch: java.lang.Exception -> L434
        L3ad:
            java.lang.String[] r0 = new java.lang.String[r5]     // Catch: java.lang.Exception -> L434
            java.lang.Object[] r12 = r10.toArray(r0)     // Catch: java.lang.Exception -> L434
            java.lang.String[] r12 = (java.lang.String[]) r12     // Catch: java.lang.Exception -> L434
            r0 = 3
            java.lang.Integer[] r11 = new java.lang.Integer[r0]     // Catch: java.lang.Exception -> L434
            r0 = r12[r6]     // Catch: java.lang.Exception -> L434
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Exception -> L434
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r7)     // Catch: java.lang.Exception -> L434
            r11[r5] = r0     // Catch: java.lang.Exception -> L434
            int r14 = com.vega.launcher.network.interceptors.TimeOutSettingInterceptor.s     // Catch: java.lang.Exception -> L434
            int r0 = r14 + 1
            r0 = r12[r0]     // Catch: java.lang.Exception -> L434
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Exception -> L434
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r7)     // Catch: java.lang.Exception -> L434
            r11[r6] = r0     // Catch: java.lang.Exception -> L434
            int r13 = com.vega.launcher.network.interceptors.TimeOutSettingInterceptor.t     // Catch: java.lang.Exception -> L434
            int r0 = r13 + 1
            r0 = r12[r0]     // Catch: java.lang.Exception -> L434
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Exception -> L434
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r7)     // Catch: java.lang.Exception -> L434
            r11[r3] = r0     // Catch: java.lang.Exception -> L434
            java.util.HashMap<java.lang.String, java.lang.Integer[]> r10 = r15.f107417a     // Catch: java.lang.Exception -> L434
            r0 = r12[r5]     // Catch: java.lang.Exception -> L434
            r10.put(r0, r11)     // Catch: java.lang.Exception -> L434
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L434
            r10.<init>()     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = "add Config="
            r10.append(r0)     // Catch: java.lang.Exception -> L434
            r0 = r12[r5]     // Catch: java.lang.Exception -> L434
            r10.append(r0)     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = ",protect="
            r10.append(r0)     // Catch: java.lang.Exception -> L434
            r0 = r11[r5]     // Catch: java.lang.Exception -> L434
            int r0 = r0.intValue()     // Catch: java.lang.Exception -> L434
            r10.append(r0)     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = ",conn="
            r10.append(r0)     // Catch: java.lang.Exception -> L434
            r0 = r11[r14]     // Catch: java.lang.Exception -> L434
            int r0 = r0.intValue()     // Catch: java.lang.Exception -> L434
            r10.append(r0)     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = ",read="
            r10.append(r0)     // Catch: java.lang.Exception -> L434
            r0 = r11[r13]     // Catch: java.lang.Exception -> L434
            int r0 = r0.intValue()     // Catch: java.lang.Exception -> L434
            r10.append(r0)     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = r10.toString()     // Catch: java.lang.Exception -> L434
            com.vega.log.BLog.i(r4, r0)     // Catch: java.lang.Exception -> L434
            int r1 = r1 + 1
            goto L370
        L42c:
            java.lang.NullPointerException r1 = new java.lang.NullPointerException     // Catch: java.lang.Exception -> L434
            java.lang.String r0 = "null cannot be cast to non-null type com.vega.templator.settings.HttpTimeoutConfig"
            r1.<init>(r0)     // Catch: java.lang.Exception -> L434
            throw r1     // Catch: java.lang.Exception -> L434
        L434:
            java.lang.String r0 = "gecko-sg.capcutapi.com"
            java.lang.String r1 = "p16-capcut-sg.ibyteimg.com"
            java.lang.String r2 = "p16-ulike-sg.ibyteimg.com"
            java.lang.String r3 = "lf16-effectcdn.byteeffecttos-g.com"
            java.lang.String r4 = "p19-capcut-sg.ibyteimg.com"
            java.lang.String r5 = "sf16-passport-va.ibytedtos.com"
            java.lang.String r6 = "editor-api.capcutapi.com"
            java.lang.String r7 = "feed-api.capcutapi.com"
            java.lang.String r8 = "lf19-effectcdn.byteeffecttos-g.com"
            java.lang.String r9 = "p19-capcut-va.ibyteimg.com"
            java.lang.String r10 = "p16-capcut-va.ibyteimg.com"
            java.lang.String r11 = "lf16-beecdn.ibytedtos.com"
            java.lang.String r12 = "p16-sg-default.akamaized.net"
            java.lang.String r13 = "sf16-passport-sg.ibytedtos.com"
            java.lang.String[] r0 = new java.lang.String[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13}
            java.util.ArrayList r0 = kotlin.collections.CollectionsKt__CollectionsKt.arrayListOf(r0)
            r15.o = r0
            java.lang.String r0 = "passport-api.capcutapi.com"
            java.lang.String[] r0 = new java.lang.String[]{r0}
            java.util.ArrayList r0 = kotlin.collections.CollectionsKt__CollectionsKt.arrayListOf(r0)
            r15.p = r0
            java.lang.String r0 = "api-resource.capcutapi.com"
            java.lang.String[] r0 = new java.lang.String[]{r0}
            java.util.ArrayList r0 = kotlin.collections.CollectionsKt__CollectionsKt.arrayListOf(r0)
            r15.q = r0
            com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$passportRequestContext$2 r0 = new kotlin.jvm.functions.Function0<com.bytedance.ttnet.http.RequestContext>() { // from class: com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$passportRequestContext$2
                static {
                    /*
                        com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$passportRequestContext$2 r0 = new com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$passportRequestContext$2
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$passportRequestContext$2) com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$passportRequestContext$2.e com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$passportRequestContext$2
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$passportRequestContext$2.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 0
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$passportRequestContext$2.<init>():void");
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final com.bytedance.ttnet.http.RequestContext invoke() {
                    /*
                        r7 = this;
                        java.lang.Class<com.lemon.config.LoginOptimizeABConfigSetting> r0 = com.lemon.config.LoginOptimizeABConfigSetting.class
                        kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
                        com.vega.config.IConfig r1 = com.vega.config.ConfigSettingsKt.a(r0)
                        com.lemon.config.LoginOptimizeABConfig r1 = (com.lemon.config.LoginOptimizeABConfig) r1
                        java.lang.String r0 = ""
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
                        boolean r0 = r1.b()
                        if (r0 == 0) goto L43
                        int r0 = r1.c()
                        java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                        if (r0 == 0) goto L43
                        int r0 = r0.intValue()
                        long r4 = (long) r0
                    L26:
                        com.bytedance.ttnet.http.RequestContext r6 = new com.bytedance.ttnet.http.RequestContext
                        r6.<init>()
                        r0 = 1000(0x3e8, float:1.401E-42)
                        long r2 = (long) r0
                        long r0 = r4 * r2
                        r6.timeout_connect = r0
                        r6.timeout_read = r0
                        r6.timeout_write = r0
                        r6.socket_connect_timeout = r0
                        r6.socket_read_timeout = r0
                        r6.socket_write_timeout = r0
                        r0 = 2
                        long r0 = (long) r0
                        long r4 = r4 * r0
                        long r4 = r4 * r2
                        r6.protect_timeout = r4
                        return r6
                    L43:
                        r4 = 30
                        goto L26
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$passportRequestContext$2.invoke():java.lang.Object");
                }
            }
            kotlin.Lazy r0 = kotlin.LazyKt__LazyJVMKt.lazy(r0)
            r15.r = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.launcher.network.interceptors.TimeOutSettingInterceptor.<init>():void");
    }

    public static RequestContext a() {
        TemplatorConfigProvider templatorConfigProvider = (TemplatorConfigProvider) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(TemplatorConfigProvider.class), null);
        long j = templatorConfigProvider.getHttpTimeoutConfig().timeout;
        long j2 = templatorConfigProvider.getHttpTimeoutConfig().protected_timeout;
        if (j <= 0 || j2 <= 0) {
            return null;
        }
        if (PerformanceManagerHelper.blogEnable) {
            BLog.i("TimeOutSettingInterceptor", "reset timeout: " + j + "; " + j2);
        }
        RequestContext requestContext = new RequestContext();
        requestContext.timeout_connect = j;
        requestContext.timeout_read = j;
        requestContext.timeout_write = j;
        requestContext.socket_connect_timeout = j;
        requestContext.socket_read_timeout = j;
        requestContext.socket_write_timeout = j;
        requestContext.protect_timeout = j2;
        return requestContext;
    }

    public final Integer[] b(String str) {
        if (str != null && this.f107417a.size() > 0) {
            for (String str2 : this.f107417a.keySet()) {
                Intrinsics.checkNotNull(str2);
                if (StringsKt__StringsJVMKt.endsWith$default(str, str2, false, 2, null)) {
                    return this.f107417a.get(str2);
                }
            }
        }
        return new Integer[]{-1, -1, -1};
    }

    public final void c(String str, List<String> list) {
        if (list.isEmpty()) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        Long l = (Long) ((LinkedHashMap) this.n).get(str);
        if (jCurrentTimeMillis - (l != null ? l.longValue() : 0L) < this.m) {
            return;
        }
        this.n.put(str, Long.valueOf(jCurrentTimeMillis));
        ExecutorService executorServiceNewFixedThreadPool = PThreadExecutorsUtils.newFixedThreadPool(2, new DefaultThreadFactory("TimeOutSettingInterceptor"));
        try {
            for (final String str2 : list) {
                executorServiceNewFixedThreadPool.execute(new Runnable() { // from class: X.0lv
                    @Override // java.lang.Runnable
                    public final void run() {
                        String str3 = str2;
                        try {
                            String str4 = "https://" + str3 + '/';
                            NetworkManagerWrapper.f79356a.getClass();
                            NetworkManagerWrapper.g(str4, null, false);
                            BLog.i("TimeOutSettingInterceptor", "force preconnect success: ".concat(str4));
                        } catch (Exception e) {
                            BLog.w("TimeOutSettingInterceptor", "force preconnect failed: " + str3 + ", err=" + e.getMessage());
                        }
                    }
                });
            }
        } finally {
            executorServiceNewFixedThreadPool.shutdown();
        }
    }

    /* JADX DEBUG: Finally have unexpected throw blocks count: 2, expect 1 */
    /* JADX WARN: Removed duplicated region for block: B:139:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0130  */
    @Override // com.bytedance.retrofit2.intercept.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.bytedance.retrofit2.SsResponse<?> intercept(com.bytedance.retrofit2.intercept.Interceptor.Chain r18) throws com.vega.launcher.network.interceptors.TimeOutSettingInterceptor.DropUrlException {
        /*
            r17 = this;
            java.lang.String r13 = "\n"
            java.lang.String r5 = "drop special url: "
            java.lang.String r3 = "TimeOutSettingInterceptor"
            java.lang.String r8 = ""
            r11 = r18
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r8)
            com.bytedance.retrofit2.client.Request r7 = r11.request()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
            r4 = r17
            java.lang.String[] r12 = r4.f107420g     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            if (r12 == 0) goto L89
            kotlin.jvm.internal.Intrinsics.checkNotNull(r12)     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            int r10 = r12.length     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            r9 = 0
        L1f:
            if (r9 >= r10) goto L89
            r1 = r12[r9]     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            java.lang.String r0 = r7.getUrl()     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r8)     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            boolean r0 = X.C93472yG.o(r0, r1)     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            if (r0 == 0) goto L86
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            java.lang.StackTraceElement[] r14 = r0.getStackTrace()     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            java.lang.String r0 = "get special req stack to debug issue!"
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            r1.<init>()     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            java.lang.String r0 = "check_special_path="
            r1.append(r0)     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            java.lang.String r0 = r7.getPath()     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            r1.append(r0)     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            r6.append(r0)     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            r6.append(r13)     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14)     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            int r2 = r14.length     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            r1 = 0
        L5f:
            if (r1 >= r2) goto L7f
            r16 = r14[r1]     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            java.lang.String r15 = r16.toString()     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r8)     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            java.lang.String r0 = "RealInterceptorChain.proceed"
            boolean r0 = X.C93472yG.o(r15, r0)     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            if (r0 != 0) goto L7c
            java.lang.String r0 = r16.toString()     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            r6.append(r0)     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            r6.append(r13)     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
        L7c:
            int r1 = r1 + 1
            goto L5f
        L7f:
            java.lang.String r0 = r6.toString()     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            com.bytedance.services.apm.api.EnsureManager.ensureNotReachHere(r0)     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
        L86:
            int r9 = r9 + 1
            goto L1f
        L89:
            java.lang.String[] r9 = r4.j     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            if (r9 == 0) goto La8
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            int r6 = r9.length     // Catch: java.lang.Throwable -> L11a java.lang.Exception -> L11c
            r2 = 0
            r10 = 0
        L93:
            if (r2 >= r6) goto La9
            r1 = r9[r2]     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11e
            java.lang.String r0 = r7.getUrl()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11e
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r8)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11e
            boolean r0 = X.C93472yG.o(r0, r1)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11e
            if (r0 == 0) goto La5
            r10 = 1
        La5:
            int r2 = r2 + 1
            goto L93
        La8:
            r10 = 0
        La9:
            boolean r0 = r4.k     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11e
            if (r0 == 0) goto Le1
            java.util.List<com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$PreconnectConfig> r0 = r4.l     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11e
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11e
            r9 = 1
            r0 = r0 ^ 1
            if (r0 == 0) goto Le2
            java.util.List<com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$PreconnectConfig> r0 = r4.l     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11f
            java.util.Iterator r6 = r0.iterator()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11f
        Lbe:
            boolean r0 = r6.hasNext()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11f
            if (r0 == 0) goto Le2
            java.lang.Object r2 = r6.next()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11f
            com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$PreconnectConfig r2 = (com.vega.launcher.network.interceptors.TimeOutSettingInterceptor.PreconnectConfig) r2     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11f
            java.lang.String r1 = r7.getUrl()     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11f
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r8)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11f
            java.lang.String r0 = r2.f107424a     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11f
            boolean r0 = X.C93472yG.o(r1, r0)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11f
            if (r0 == 0) goto Lbe
            java.lang.String r1 = r2.f107424a     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11f
            java.util.List<java.lang.String> r0 = r2.b     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11f
            r4.c(r1, r0)     // Catch: java.lang.Throwable -> Lfe java.lang.Exception -> L11f
            goto Lbe
        Le1:
            r9 = 1
        Le2:
            if (r10 != 0) goto Le5
            goto L121
        Le5:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r5)
            java.lang.String r0 = r7.getUrl()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.e(r3, r0)
            com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$DropUrlException r0 = new com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$DropUrlException
            r0.<init>()
            throw r0
        Lfe:
            r0 = move-exception
            if (r10 == 0) goto L11b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r5)
            java.lang.String r0 = r7.getUrl()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.e(r3, r0)
            com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$DropUrlException r0 = new com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$DropUrlException
            r0.<init>()
            throw r0
        L11a:
            r0 = move-exception
        L11b:
            throw r0
        L11c:
            r9 = 1
            goto L121
        L11e:
            r9 = 1
        L11f:
            if (r10 != 0) goto L34d
        L121:
            java.lang.String r1 = r7.getUrl()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r8)
            java.lang.String r0 = "dont_reset_timeout"
            boolean r0 = X.C93472yG.o(r1, r0)
            if (r0 != 0) goto L13b
            com.lm.components.network.extra.NetworkRuntimeConfig r0 = com.lm.components.network.extra.NetworkRuntimeConfig.f60924a
            r0.getClass()
            boolean r0 = com.lm.components.network.extra.NetworkRuntimeConfig.a(r7)
            if (r0 == 0) goto L143
        L13b:
            com.bytedance.retrofit2.SsResponse r0 = r11.proceed(r7)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r8)
            return r0
        L143:
            boolean r0 = com.vega.performance.PerformanceManagerHelper.blogEnable
            if (r0 == 0) goto L15c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "url: "
            r1.<init>(r0)
            java.lang.String r0 = r7.getUrl()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.i(r3, r0)
        L15c:
            java.lang.String r1 = r7.getUrl()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r8)
            java.lang.String r0 = "reset_timeout=1"
            boolean r0 = X.C93472yG.o(r1, r0)
            if (r0 == 0) goto L179
            com.bytedance.ttnet.http.RequestContext r0 = a()
            if (r0 == 0) goto L179
            com.bytedance.ttnet.http.RequestContext r0 = a()
            r7.setExtraInfo(r0)
            goto L17a
        L179:
            r9 = 0
        L17a:
            java.lang.String r5 = r7.getHost()     // Catch: java.lang.Exception -> L1a6
            java.lang.String r2 = r4.f107418c     // Catch: java.lang.Exception -> L1a6
            if (r5 == 0) goto L1bc
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r4.b     // Catch: java.lang.Exception -> L1a6
            java.lang.Object r1 = r0.get(r5)     // Catch: java.lang.Exception -> L1a6
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch: java.lang.Exception -> L1a6
            if (r2 == 0) goto L1bc
            if (r1 == 0) goto L1bc
            int r0 = r1.intValue()     // Catch: java.lang.Exception -> L1a6
            if (r0 <= 0) goto L1bc
            com.vega.core.utils.AppActivityRecorder r0 = com.vega.core.utils.AppActivityRecorder.f79544a     // Catch: java.lang.Exception -> L1a6
            r0.getClass()     // Catch: java.lang.Exception -> L1a6
            boolean r0 = com.vega.core.utils.AppActivityRecorder.i     // Catch: java.lang.Exception -> L1a6
            if (r0 != 0) goto L1bc
            int r0 = r1.intValue()     // Catch: java.lang.Exception -> L1a6
            long r0 = (long) r0     // Catch: java.lang.Exception -> L1a6
            com.vega.launcher.network.interceptors.TimeOutSettingInterceptor.DomainKeepAliveHelper.b(r0, r5, r2)     // Catch: java.lang.Exception -> L1a6
            goto L1bc
        L1a6:
            r2 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "keepalive schedule failed: "
            r1.<init>(r0)
            java.lang.String r0 = r2.getMessage()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.w(r3, r0)
        L1bc:
            if (r9 != 0) goto L345
            boolean r0 = r4.f107419d
            if (r0 == 0) goto L266
            java.lang.String r0 = r7.getHost()     // Catch: java.lang.Exception -> L1f5
            java.lang.Integer[] r1 = r4.b(r0)     // Catch: java.lang.Exception -> L1f5
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> L1f5
            r0 = 0
            r0 = r1[r0]     // Catch: java.lang.Exception -> L1f5
            int r3 = r0.intValue()     // Catch: java.lang.Exception -> L1f5
            int r0 = com.vega.launcher.network.interceptors.TimeOutSettingInterceptor.s     // Catch: java.lang.Exception -> L1f5
            r0 = r1[r0]     // Catch: java.lang.Exception -> L1f5
            int r2 = r0.intValue()     // Catch: java.lang.Exception -> L1f5
            int r0 = com.vega.launcher.network.interceptors.TimeOutSettingInterceptor.t     // Catch: java.lang.Exception -> L1f5
            r0 = r1[r0]     // Catch: java.lang.Exception -> L1f5
            int r1 = r0.intValue()     // Catch: java.lang.Exception -> L1f5
            long r9 = (long) r3     // Catch: java.lang.Exception -> L1f5
            r13 = 0
            int r0 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r0 <= 0) goto L1f5
            long r4 = (long) r2     // Catch: java.lang.Exception -> L1f5
            int r0 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r0 <= 0) goto L1f5
            long r2 = (long) r1     // Catch: java.lang.Exception -> L1f5
            int r0 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
            if (r0 > 0) goto L1fd
        L1f5:
            com.bytedance.retrofit2.SsResponse r0 = r11.proceed(r7)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r8)
            return r0
        L1fd:
            java.lang.Object r6 = r7.getExtraInfo()     // Catch: java.lang.Exception -> L1f5
            if (r6 != 0) goto L212
            com.bytedance.ttnet.http.RequestContext r0 = new com.bytedance.ttnet.http.RequestContext     // Catch: java.lang.Exception -> L1f5
            r0.<init>()     // Catch: java.lang.Exception -> L1f5
            r0.protect_timeout = r9     // Catch: java.lang.Exception -> L1f5
            r0.socket_connect_timeout = r4     // Catch: java.lang.Exception -> L1f5
            r0.socket_read_timeout = r2     // Catch: java.lang.Exception -> L1f5
            r7.setExtraInfo(r0)     // Catch: java.lang.Exception -> L1f5
            goto L1f5
        L212:
            boolean r0 = r6 instanceof com.bytedance.ttnet.http.RequestContext     // Catch: java.lang.Exception -> L1f5
            if (r0 == 0) goto L1f5
            r0 = r6
            com.bytedance.ttnet.http.RequestContext r0 = (com.bytedance.ttnet.http.RequestContext) r0     // Catch: java.lang.Exception -> L1f5
            long r0 = r0.protect_timeout     // Catch: java.lang.Exception -> L1f5
            int r12 = (r0 > r13 ? 1 : (r0 == r13 ? 0 : -1))
            if (r12 > 0) goto L1f5
            r0 = r6
            com.bytedance.ttnet.http.RequestContext r0 = (com.bytedance.ttnet.http.RequestContext) r0     // Catch: java.lang.Exception -> L1f5
            long r0 = r0.socket_connect_timeout     // Catch: java.lang.Exception -> L1f5
            int r12 = (r0 > r13 ? 1 : (r0 == r13 ? 0 : -1))
            if (r12 > 0) goto L1f5
            r0 = r6
            com.bytedance.ttnet.http.RequestContext r0 = (com.bytedance.ttnet.http.RequestContext) r0     // Catch: java.lang.Exception -> L1f5
            long r0 = r0.socket_read_timeout     // Catch: java.lang.Exception -> L1f5
            int r12 = (r0 > r13 ? 1 : (r0 == r13 ? 0 : -1))
            if (r12 > 0) goto L1f5
            r0 = r6
            com.bytedance.ttnet.http.RequestContext r0 = (com.bytedance.ttnet.http.RequestContext) r0     // Catch: java.lang.Exception -> L1f5
            long r0 = r0.socket_write_timeout     // Catch: java.lang.Exception -> L1f5
            int r12 = (r0 > r13 ? 1 : (r0 == r13 ? 0 : -1))
            if (r12 <= 0) goto L23b
            goto L1f5
        L23b:
            r0 = r6
            com.bytedance.ttnet.http.RequestContext r0 = (com.bytedance.ttnet.http.RequestContext) r0     // Catch: java.lang.Exception -> L1f5
            long r0 = r0.timeout_read     // Catch: java.lang.Exception -> L1f5
            int r12 = (r0 > r13 ? 1 : (r0 == r13 ? 0 : -1))
            if (r12 > 0) goto L1f5
            r0 = r6
            com.bytedance.ttnet.http.RequestContext r0 = (com.bytedance.ttnet.http.RequestContext) r0     // Catch: java.lang.Exception -> L1f5
            long r0 = r0.timeout_connect     // Catch: java.lang.Exception -> L1f5
            int r12 = (r0 > r13 ? 1 : (r0 == r13 ? 0 : -1))
            if (r12 > 0) goto L1f5
            r0 = r6
            com.bytedance.ttnet.http.RequestContext r0 = (com.bytedance.ttnet.http.RequestContext) r0     // Catch: java.lang.Exception -> L1f5
            long r0 = r0.timeout_write     // Catch: java.lang.Exception -> L1f5
            int r12 = (r0 > r13 ? 1 : (r0 == r13 ? 0 : -1))
            if (r12 <= 0) goto L257
            goto L1f5
        L257:
            r0 = r6
            com.bytedance.ttnet.http.RequestContext r0 = (com.bytedance.ttnet.http.RequestContext) r0     // Catch: java.lang.Exception -> L1f5
            r0.protect_timeout = r9     // Catch: java.lang.Exception -> L1f5
            r0 = r6
            com.bytedance.ttnet.http.RequestContext r0 = (com.bytedance.ttnet.http.RequestContext) r0     // Catch: java.lang.Exception -> L1f5
            r0.socket_connect_timeout = r4     // Catch: java.lang.Exception -> L1f5
            com.bytedance.ttnet.http.RequestContext r6 = (com.bytedance.ttnet.http.RequestContext) r6     // Catch: java.lang.Exception -> L1f5
            r6.socket_read_timeout = r2     // Catch: java.lang.Exception -> L1f5
            goto L1f5
        L266:
            java.util.ArrayList<java.lang.String> r0 = r4.o
            java.util.Iterator r5 = r0.iterator()
        L26c:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L2b6
            java.lang.Object r2 = r5.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r0 = r7.getUrl()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r8)
            boolean r0 = X.C93472yG.o(r0, r2)
            if (r0 == 0) goto L26c
            boolean r0 = com.vega.performance.PerformanceManagerHelper.blogEnable
            if (r0 == 0) goto L29a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "increase http timeout: "
            r1.<init>(r0)
            r1.append(r2)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.i(r3, r0)
        L29a:
            com.bytedance.ttnet.http.RequestContext r2 = new com.bytedance.ttnet.http.RequestContext
            r2.<init>()
            r0 = 30000(0x7530, double:1.4822E-319)
            r2.timeout_connect = r0
            r2.timeout_read = r0
            r2.timeout_write = r0
            r2.socket_connect_timeout = r0
            r2.socket_read_timeout = r0
            r2.socket_write_timeout = r0
            r0 = 60000(0xea60, double:2.9644E-319)
            r2.protect_timeout = r0
            r7.setExtraInfo(r2)
            goto L26c
        L2b6:
            java.util.ArrayList<java.lang.String> r0 = r4.q
            java.util.Iterator r5 = r0.iterator()
        L2bc:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L307
            java.lang.Object r2 = r5.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r0 = r7.getUrl()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r8)
            boolean r0 = X.C93472yG.o(r0, r2)
            if (r0 == 0) goto L2bc
            boolean r0 = com.vega.performance.PerformanceManagerHelper.blogEnable
            if (r0 == 0) goto L2ea
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "tuchong increase http timeout: "
            r1.<init>(r0)
            r1.append(r2)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.i(r3, r0)
        L2ea:
            com.bytedance.ttnet.http.RequestContext r2 = new com.bytedance.ttnet.http.RequestContext
            r2.<init>()
            r0 = 90000(0x15f90, double:4.4466E-319)
            r2.timeout_connect = r0
            r2.timeout_read = r0
            r2.timeout_write = r0
            r2.socket_connect_timeout = r0
            r2.socket_read_timeout = r0
            r2.socket_write_timeout = r0
            r0 = 180000(0x2bf20, double:8.8932E-319)
            r2.protect_timeout = r0
            r7.setExtraInfo(r2)
            goto L2bc
        L307:
            java.util.ArrayList<java.lang.String> r0 = r4.p
            java.util.Iterator r5 = r0.iterator()
        L30d:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L345
            java.lang.Object r2 = r5.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r0 = r7.getUrl()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r8)
            boolean r0 = X.C93472yG.o(r0, r2)
            if (r0 == 0) goto L30d
            boolean r0 = com.vega.performance.PerformanceManagerHelper.blogEnable
            if (r0 == 0) goto L33b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "passport set http timeout: "
            r1.<init>(r0)
            r1.append(r2)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.i(r3, r0)
        L33b:
            kotlin.Lazy r0 = r4.r
            java.lang.Object r0 = r0.getValue()
            r7.setExtraInfo(r0)
            goto L30d
        L345:
            com.bytedance.retrofit2.SsResponse r0 = r11.proceed(r7)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r8)
            return r0
        L34d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r5)
            java.lang.String r0 = r7.getUrl()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.e(r3, r0)
            com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$DropUrlException r0 = new com.vega.launcher.network.interceptors.TimeOutSettingInterceptor$DropUrlException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.launcher.network.interceptors.TimeOutSettingInterceptor.intercept(com.bytedance.retrofit2.intercept.Interceptor$Chain):com.bytedance.retrofit2.SsResponse");
    }
}