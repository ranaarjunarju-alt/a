package com.vega.core.ext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.text.TextUtils;
import com.bytedance.services.apm.api.EnsureManager;
import com.vega.config.ILynxSchemaInjectSettingApi;
import com.vega.core.context.ContextExtKt;
import com.vega.core.context.SPIService;
import com.vega.infrastructure.extensions.ICancelable$Companion$build$1;
import com.vega.infrastructure.extensions.ThreadUtilKt;
import com.vega.infrastructure.util.NetworkUtils;
import com.vega.log.BLog;
import com.vega.performance.BadParcelableExceptionOpt;
import com.vega.report.AppLogManagerWrapper;
import java.io.File;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsKt;
import org.json.JSONObject;

/* loaded from: classes24.dex */
public final class FunctionKt {
    public static Intent INVOKEVIRTUAL_com_vega_core_ext_FunctionKt_com_vega_launcher_lancet_BadParcelableLancet_getInttent(Activity activity) {
        Context context;
        Intent intent = activity.getIntent();
        if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
            intent.setExtrasClassLoader(context.getClassLoader());
        }
        return intent;
    }

    public static final void a(Function1<? super Long, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "");
        function1.invoke(Long.valueOf(System.currentTimeMillis()));
    }

    public static final void b(final Function1<? super String, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "");
        String strC = ContextExtKt.device().c();
        if (TextUtils.isEmpty(strC)) {
            AppLogManagerWrapper.INSTANCE.addDeviceInfoUpdateListener(new Function2<String, String, Unit>() { // from class: com.vega.core.ext.FunctionKt$dependsOnDid$1
                /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function2
                public final Unit invoke(String str, String str2) {
                    Intrinsics.checkNotNullParameter(str, "");
                    Intrinsics.checkNotNullParameter(str2, "");
                    function1.invoke(str);
                    return Unit.INSTANCE;
                }
            });
        } else {
            function1.invoke(strC);
        }
    }

    public static final String c(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        if (StringsKt__StringsKt.endsWith$default(str, File.separatorChar, false, 2, (Object) null)) {
            str = str.substring(0, str.length() - 1);
            Intrinsics.checkNotNullExpressionValue(str, "");
        }
        int iLastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default(str, File.separatorChar, 0, false, 6, (Object) null);
        if (iLastIndexOf$default <= 0) {
            return str;
        }
        String strSubstring = str.substring(iLastIndexOf$default + 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "");
        return strSubstring;
    }

    public static final void d(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        BLog.e(str, str2);
        EnsureManager.ensureNotReachHere(str2);
    }

    public static final void e(long j, final Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "");
        ThreadUtilKt.b(j, new Function0<Unit>() { // from class: com.vega.core.ext.FunctionKt$post2MainIdleHandler$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                function0.invoke();
                return Unit.INSTANCE;
            }
        });
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: android.os.MessageQueue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.os.MessageQueue$IdleHandler, com.vega.core.ext.FunctionKt$postOnMainIdleHandler$idleHandler$1] */
    public static void f(final Function0 function0) {
        Intrinsics.checkNotNullParameter(function0, "");
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        final ?? r1 = new MessageQueue.IdleHandler() { // from class: com.vega.core.ext.FunctionKt$postOnMainIdleHandler$idleHandler$1
            @Override // android.os.MessageQueue.IdleHandler
            public final boolean queueIdle() {
                Ref$BooleanRef ref$BooleanRef2 = ref$BooleanRef;
                if (ref$BooleanRef2.element) {
                    return false;
                }
                ref$BooleanRef2.element = true;
                try {
                    function0.invoke();
                    return false;
                } catch (Exception e) {
                    BLog.e("DaggerKoin", "postOnMainIdleHandler crash", e);
                    return false;
                }
            }
        };
        Looper.myQueue().addIdleHandler(r1);
        ThreadUtilKt.b(3000L, new Function0<Unit>() { // from class: com.vega.core.ext.FunctionKt$postOnMainIdleHandler$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                Ref$BooleanRef ref$BooleanRef2 = ref$BooleanRef;
                if (!ref$BooleanRef2.element) {
                    ref$BooleanRef2.element = true;
                    try {
                        function0.invoke();
                    } catch (Exception e) {
                        BLog.e("DaggerKoin", "postOnMainIdleHandler crash", e);
                    }
                    Looper.myQueue().removeIdleHandler(r1);
                }
                return Unit.INSTANCE;
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x006f -> B:12:0x002b). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object g(kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super kotlin.coroutines.Continuation<? super java.lang.Boolean>, ? extends java.lang.Object> r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            boolean r0 = r11 instanceof com.vega.core.ext.FunctionKt$retreatForever$1
            if (r0 == 0) goto L7f
            r7 = r11
            com.vega.core.ext.FunctionKt$retreatForever$1 r7 = (com.vega.core.ext.FunctionKt$retreatForever$1) r7
            int r2 = r7.u
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L7f
            int r2 = r2 - r1
            r7.u = r2
        L12:
            java.lang.Object r0 = r7.t
            java.lang.Object r8 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.u
            r6 = 2
            r5 = 1
            if (r1 == 0) goto L72
            if (r1 == r5) goto L3e
            if (r1 != r6) goto L85
            int r4 = r7.s
            kotlin.random.Random r9 = r7.r
            kotlin.jvm.functions.Function2 r10 = r7.q
            kotlin.ResultKt.throwOnFailure(r0)
        L2b:
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)
            r7.q = r10
            r7.r = r9
            r7.s = r4
            r7.u = r5
            java.lang.Object r0 = r10.invoke(r0, r7)
            if (r0 != r8) goto L47
            return r8
        L3e:
            int r4 = r7.s
            kotlin.random.Random r9 = r7.r
            kotlin.jvm.functions.Function2 r10 = r7.q
            kotlin.ResultKt.throwOnFailure(r0)
        L47:
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L8d
            int r4 = r4 + 1
            r2 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r0 = (double) r4
            double r2 = java.lang.Math.pow(r2, r0)
            long r0 = (long) r2
            r2 = 1
            long r0 = r0 - r2
            long r0 = r9.nextLong(r0)
            r2 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 * r2
            r7.q = r10
            r7.r = r9
            r7.s = r4
            r7.u = r6
            java.lang.Object r0 = kotlinx.coroutines.DelayKt.delay(r0, r7)
            if (r0 != r8) goto L2b
            return r8
        L72:
            kotlin.ResultKt.throwOnFailure(r0)
            long r0 = java.lang.System.currentTimeMillis()
            kotlin.random.Random r9 = kotlin.random.RandomKt.Random(r0)
            r4 = 0
            goto L2b
        L7f:
            com.vega.core.ext.FunctionKt$retreatForever$1 r7 = new com.vega.core.ext.FunctionKt$retreatForever$1
            r7.<init>(r11)
            goto L12
        L85:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        L8d:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.core.ext.FunctionKt.g(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void h(android.app.Activity r9, android.os.Bundle r10) {
        /*
            java.lang.String r7 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r7)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r7)
            android.content.Intent r1 = INVOKEVIRTUAL_com_vega_core_ext_FunctionKt_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r9)     // Catch: java.lang.Throwable -> Lc7
            if (r1 == 0) goto L16
            java.lang.String r0 = "enter_from"
            java.lang.String r2 = r1.getStringExtra(r0)     // Catch: java.lang.Throwable -> Lc7
            if (r2 != 0) goto L17
        L16:
            r2 = r7
        L17:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch: java.lang.Throwable -> Lc7
            com.vega.tracing.FeedSceneMonitor r1 = com.vega.tracing.FeedSceneMonitor.f132749a     // Catch: java.lang.Throwable -> Lc7
            java.lang.Class r0 = r9.getClass()     // Catch: java.lang.Throwable -> Lc7
            java.lang.String r0 = r0.getSimpleName()     // Catch: java.lang.Throwable -> Lc7
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r7)     // Catch: java.lang.Throwable -> Lc7
            r1.getClass()     // Catch: java.lang.Throwable -> Lc7
            com.vega.tracing.FeedSceneMonitor.a(r0, r2)     // Catch: java.lang.Throwable -> Lc7
            com.vega.core.utils.AppActivityRecorder r0 = com.vega.core.utils.AppActivityRecorder.f79544a     // Catch: java.lang.Throwable -> Lc7
            android.app.Activity r1 = r0.e()     // Catch: java.lang.Throwable -> Lc7
            r6 = 1
            r8 = 0
            if (r1 != 0) goto L39
            r5 = 1
            goto L3a
        L39:
            r5 = 0
        L3a:
            java.util.List r0 = r0.b()     // Catch: java.lang.Throwable -> Lc7
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch: java.lang.Throwable -> Lc7
            int r4 = r0.size()     // Catch: java.lang.Throwable -> Lc7
            r3 = 0
            if (r5 != 0) goto L57
            com.vega.setting.FeedAcBackConfig$Companion r2 = com.vega.setting.FeedAcBackConfig.f130059a     // Catch: java.lang.Throwable -> Lc7
            if (r1 == 0) goto L55
            java.lang.Class r0 = r1.getClass()     // Catch: java.lang.Throwable -> Lc7
            java.lang.String r1 = r0.getSimpleName()     // Catch: java.lang.Throwable -> Lc7
            if (r1 != 0) goto L59
        L55:
            r1 = r7
            goto L5c
        L57:
            r6 = r5
            goto L70
        L59:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Throwable -> Lc7
        L5c:
            java.lang.Class r0 = r9.getClass()     // Catch: java.lang.Throwable -> Lc7
            java.lang.String r0 = r0.getSimpleName()     // Catch: java.lang.Throwable -> Lc7
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r7)     // Catch: java.lang.Throwable -> Lc7
            r2.getClass()     // Catch: java.lang.Throwable -> Lc7
            boolean r0 = com.vega.setting.FeedAcBackConfig.Companion.a(r4, r1, r0)     // Catch: java.lang.Throwable -> Lc7
            if (r0 == 0) goto L57
        L70:
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_core_ext_FunctionKt_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r9)     // Catch: java.lang.Throwable -> Lc7
            if (r0 == 0) goto L82
            android.net.Uri r1 = r0.getData()     // Catch: java.lang.Throwable -> Lc7
            if (r1 == 0) goto L82
            java.lang.String r0 = "stay_last_page"
            java.lang.String r3 = r1.getQueryParameter(r0)     // Catch: java.lang.Throwable -> Lc7
        L82:
            java.lang.String r0 = "0"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r0)     // Catch: java.lang.Throwable -> Lc7
            if (r0 != 0) goto Lc1
            if (r6 == 0) goto Lc1
            java.lang.Class r1 = r9.getClass()     // Catch: java.lang.Throwable -> Lc7
            java.lang.Class<com.vega.core.annotation.ExitForbiddenActivity> r0 = com.vega.core.annotation.ExitForbiddenActivity.class
            java.lang.annotation.Annotation r7 = r1.getAnnotation(r0)     // Catch: java.lang.Throwable -> Lc7
            com.vega.core.annotation.ExitForbiddenActivity r7 = (com.vega.core.annotation.ExitForbiddenActivity) r7     // Catch: java.lang.Throwable -> Lc7
            if (r7 == 0) goto Lc1
            java.lang.String r0 = r7.path()     // Catch: java.lang.Throwable -> Lc7
            com.bytedance.router.SmartRoute r6 = com.bytedance.router.SmartRouter.buildRoute(r9, r0)     // Catch: java.lang.Throwable -> Lc7
            java.lang.String[] r5 = r7.keys()     // Catch: java.lang.Throwable -> Lc7
            int r4 = r5.length     // Catch: java.lang.Throwable -> Lc7
            r3 = 0
        La8:
            if (r8 >= r4) goto Lbb
            r2 = r5[r8]     // Catch: java.lang.Throwable -> Lc7
            int r1 = r3 + 1
            java.lang.String[] r0 = r7.values()     // Catch: java.lang.Throwable -> Lc7
            r0 = r0[r3]     // Catch: java.lang.Throwable -> Lc7
            r6.withParam(r2, r0)     // Catch: java.lang.Throwable -> Lc7
            int r8 = r8 + 1
            r3 = r1
            goto La8
        Lbb:
            r6.withParam(r10)     // Catch: java.lang.Throwable -> Lc7
            r6.open()     // Catch: java.lang.Throwable -> Lc7
        Lc1:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> Lc7
            kotlin.Result.m17090constructorimpl(r0)     // Catch: java.lang.Throwable -> Lc7
            goto Lcf
        Lc7:
            r0 = move-exception
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            kotlin.Result.m17090constructorimpl(r0)
        Lcf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.core.ext.FunctionKt.h(android.app.Activity, android.os.Bundle):void");
    }

    public static final boolean j(Context context, String str, boolean z, JSONObject jSONObject, Intent intent, Bundle bundle) {
        Bundle bundle2;
        Bundle extras;
        Intrinsics.checkNotNullParameter(context, "");
        Intrinsics.checkNotNullParameter(str, "");
        if (jSONObject == null && intent == null && bundle == null) {
            bundle2 = null;
        } else {
            bundle2 = new Bundle();
            if (jSONObject != null && bundle2 != null) {
                IntentExKt.f(bundle2, "lynx_data", jSONObject);
            }
            if (intent != null && (extras = intent.getExtras()) != null && bundle2 != null) {
                bundle2.putAll(extras);
            }
            if (bundle != null && bundle2 != null) {
                bundle2.putAll(bundle);
            }
        }
        String strA = ((ILynxSchemaInjectSettingApi) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(ILynxSchemaInjectSettingApi.class), null)).a(str);
        if (strA != null && strA.length() != 0) {
            str = strA;
        }
        return l(context, str, z, bundle2);
    }

    public static /* synthetic */ boolean k(Context context, String str, boolean z, JSONObject jSONObject, Intent intent, Bundle bundle, int i) {
        Intent intent2 = intent;
        JSONObject jSONObject2 = jSONObject;
        boolean z2 = z;
        if ((i & 4) != 0) {
            z2 = false;
        }
        if ((i & 8) != 0) {
            jSONObject2 = null;
        }
        if ((i & 16) != 0) {
            intent2 = null;
        }
        return j(context, str, z2, jSONObject2, intent2, (i & 32) == 0 ? bundle : null);
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x014a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean l(android.content.Context r11, java.lang.String r12, boolean r13, android.os.Bundle r14) {
        /*
            java.lang.String r0 = ""
            r9 = r11
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            r10 = r12
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "schema: "
            r1.<init>(r0)
            r1.append(r10)
            java.lang.String r0 = ", force: "
            r1.append(r0)
            r13 = r13
            r1.append(r13)
            java.lang.String r0 = ", extra: "
            r1.append(r0)
            r12 = r14
            r1.append(r12)
            java.lang.String r0 = r1.toString()
            java.lang.String r6 = "tryOpenSchema"
            com.vega.log.BLog.i(r6, r0)
            java.lang.String r0 = "http"
            boolean r0 = X.C93472yG.S(r10, r0)     // Catch: java.lang.Throwable -> L135
            r8 = 0
            r7 = 0
            java.lang.String r2 = "android.intent.action.VIEW"
            r5 = 268435456(0x10000000, float:2.5243549E-29)
            r1 = 1
            if (r0 == 0) goto La8
            android.net.Uri r4 = android.net.Uri.parse(r10)     // Catch: java.lang.Throwable -> L135
            java.lang.String r0 = "https://"
            boolean r0 = X.C93472yG.S(r10, r0)     // Catch: java.lang.Throwable -> L135
            if (r0 == 0) goto L90
            java.lang.String r3 = r4.getHost()     // Catch: java.lang.Throwable -> L135
            if (r3 == 0) goto L90
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch: java.lang.Throwable -> L135
            java.lang.String r0 = "onelink.me"
            boolean r0 = kotlin.text.StringsKt__StringsKt.contains(r3, r0, r1)     // Catch: java.lang.Throwable -> L135
            if (r0 != r1) goto L90
            android.content.Intent r3 = new android.content.Intent     // Catch: java.lang.Throwable -> L76
            r3.<init>(r2, r4)     // Catch: java.lang.Throwable -> L76
            java.lang.String r0 = "android.intent.category.DEFAULT"
            r3.addCategory(r0)     // Catch: java.lang.Throwable -> L76
            java.lang.String r0 = "android.intent.category.BROWSABLE"
            r3.addCategory(r0)     // Catch: java.lang.Throwable -> L76
            r3.addFlags(r5)     // Catch: java.lang.Throwable -> L76
            androidx.core.content.ContextCompat.Api16Impl.INVOKEVIRTUAL_androidx_core_content_ContextCompat$Api16Impl_com_vega_core_deeplink_DeeplinkIntentLancet_startActivity(r9, r3, r8)     // Catch: java.lang.Throwable -> L76
            java.lang.Boolean r3 = java.lang.Boolean.TRUE     // Catch: java.lang.Throwable -> L76
            kotlin.Result.m17090constructorimpl(r3)     // Catch: java.lang.Throwable -> L76
            goto L7e
        L76:
            r0 = move-exception
            java.lang.Object r3 = kotlin.ResultKt.createFailure(r0)     // Catch: java.lang.Throwable -> L135
            kotlin.Result.m17090constructorimpl(r3)     // Catch: java.lang.Throwable -> L135
        L7e:
            java.lang.Boolean r2 = java.lang.Boolean.FALSE     // Catch: java.lang.Throwable -> L135
            boolean r0 = kotlin.Result.m17096isFailureimpl(r3)     // Catch: java.lang.Throwable -> L135
            if (r0 == 0) goto L87
            r3 = r2
        L87:
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch: java.lang.Throwable -> L135
            boolean r0 = r3.booleanValue()     // Catch: java.lang.Throwable -> L135
            if (r0 == 0) goto L90
            return r1
        L90:
            java.lang.String r0 = "//main/web"
            com.bytedance.router.SmartRoute r1 = com.bytedance.router.SmartRouter.buildRoute(r9, r0)     // Catch: java.lang.Throwable -> L135
            java.lang.String r0 = "web_url"
            r1.withParam(r0, r10)     // Catch: java.lang.Throwable -> L135
            r1.addFlags(r5)     // Catch: java.lang.Throwable -> L135
            if (r12 == 0) goto La3
            r1.withParam(r12)     // Catch: java.lang.Throwable -> L135
        La3:
            r1.open()     // Catch: java.lang.Throwable -> L135
            goto L12c
        La8:
            java.lang.String r0 = "market"
            boolean r0 = X.C93472yG.S(r10, r0)     // Catch: java.lang.Throwable -> L135
            java.lang.String r4 = "1"
            java.lang.String r3 = "allow_deep_link_flag"
            if (r0 == 0) goto Ldb
            android.content.Intent r6 = new android.content.Intent     // Catch: java.lang.Throwable -> L135
            android.net.Uri r0 = android.net.Uri.parse(r10)     // Catch: java.lang.Throwable -> L135
            r6.<init>(r2, r0)     // Catch: java.lang.Throwable -> L135
            java.lang.String r0 = "com.android.vending"
            r6.setPackage(r0)     // Catch: java.lang.Throwable -> L135
            if (r12 == 0) goto Lc7
            r6.putExtras(r12)     // Catch: java.lang.Throwable -> L135
        Lc7:
            java.lang.String r1 = "pns.sandbox.dataflow_id"
            r0 = 1258291205(0x4b000005, float:8388613.0)
            r6.putExtra(r1, r0)     // Catch: java.lang.Throwable -> L135
            r6.addFlags(r5)     // Catch: java.lang.Throwable -> L135
            if (r13 == 0) goto Ld7
            r6.putExtra(r3, r4)     // Catch: java.lang.Throwable -> L135
        Ld7:
            androidx.core.content.ContextCompat.Api16Impl.INVOKEVIRTUAL_androidx_core_content_ContextCompat$Api16Impl_com_vega_core_deeplink_DeeplinkIntentLancet_startActivity(r9, r6, r8)     // Catch: java.lang.Throwable -> L135
            goto L12c
        Ldb:
            java.lang.String r0 = "capcut"
            boolean r0 = X.C93472yG.S(r10, r0)     // Catch: java.lang.Throwable -> L135
            if (r0 == 0) goto L12d
            com.vega.core.context.SPIService r7 = com.vega.core.context.SPIService.INSTANCE     // Catch: java.lang.Throwable -> L135
            java.lang.Class<com.vega.core.api.IDeepLinkDispatchService> r0 = com.vega.core.api.IDeepLinkDispatchService.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)     // Catch: java.lang.Throwable -> L135
            java.lang.Object r8 = r7.getImpl(r0, r8)     // Catch: java.lang.Throwable -> L135
            com.vega.core.api.IDeepLinkDispatchService r8 = (com.vega.core.api.IDeepLinkDispatchService) r8     // Catch: java.lang.Throwable -> L135
            com.vega.core.api.DeepLinkDispatchScene r11 = com.vega.core.api.DeepLinkDispatchScene.e     // Catch: java.lang.Throwable -> L135
            boolean r0 = r8.e(r9, r10, r11, r12, r13)     // Catch: java.lang.Throwable -> L135
            if (r0 == 0) goto Lff
            java.lang.String r0 = "tryDispatchDirectly success, schema"
            com.vega.log.BLog.i(r6, r0)     // Catch: java.lang.Throwable -> L135
            return r1
        Lff:
            android.content.Intent r7 = new android.content.Intent     // Catch: java.lang.Throwable -> L135
            android.net.Uri r0 = android.net.Uri.parse(r10)     // Catch: java.lang.Throwable -> L135
            r7.<init>(r2, r0)     // Catch: java.lang.Throwable -> L135
            java.lang.String r0 = "deep_link_from_inner"
            r7.putExtra(r0, r1)     // Catch: java.lang.Throwable -> L135
            java.lang.String r2 = "open_time"
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L135
            r7.putExtra(r2, r0)     // Catch: java.lang.Throwable -> L135
            java.lang.String r0 = "scene"
            r7.putExtra(r0, r6)     // Catch: java.lang.Throwable -> L135
            if (r12 == 0) goto L120
            r7.putExtras(r12)     // Catch: java.lang.Throwable -> L135
        L120:
            r7.addFlags(r5)     // Catch: java.lang.Throwable -> L135
            if (r13 == 0) goto L128
            r7.putExtra(r3, r4)     // Catch: java.lang.Throwable -> L135
        L128:
            r0 = 0
            androidx.core.content.ContextCompat.Api16Impl.INVOKEVIRTUAL_androidx_core_content_ContextCompat$Api16Impl_com_vega_core_deeplink_DeeplinkIntentLancet_startActivity(r9, r7, r0)     // Catch: java.lang.Throwable -> L135
        L12c:
            r7 = 1
        L12d:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r7)     // Catch: java.lang.Throwable -> L135
            kotlin.Result.m17090constructorimpl(r1)     // Catch: java.lang.Throwable -> L135
            goto L13d
        L135:
            r0 = move-exception
            java.lang.Object r1 = kotlin.ResultKt.createFailure(r0)
            kotlin.Result.m17090constructorimpl(r1)
        L13d:
            java.lang.Throwable r0 = kotlin.Result.m17093exceptionOrNullimpl(r1)
            if (r0 != 0) goto L14a
        L143:
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r0 = r1.booleanValue()
            return r0
        L14a:
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            goto L143
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.core.ext.FunctionKt.l(android.content.Context, java.lang.String, boolean, android.os.Bundle):boolean");
    }

    public static final ICancelable$Companion$build$1 m(final Function0 function0) {
        Intrinsics.checkNotNullParameter(function0, "");
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        NetworkUtils.f106649a.getClass();
        boolean zD = NetworkUtils.d();
        ref$BooleanRef.element = zD;
        if (zD) {
            function0.invoke();
        }
        return NetworkUtils.a(new Function1<NetworkUtils.NetworkType, Unit>() { // from class: com.vega.core.ext.FunctionKt$whenConnectToNetwork$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(NetworkUtils.NetworkType networkType) {
                Intrinsics.checkNotNullParameter(networkType, "");
                NetworkUtils.f106649a.getClass();
                if (!NetworkUtils.d()) {
                    ref$BooleanRef.element = false;
                } else if (!ref$BooleanRef.element) {
                    function0.invoke();
                }
                return Unit.INSTANCE;
            }
        });
    }

    public static final ICancelable$Companion$build$1 n(final Function0 function0) {
        Intrinsics.checkNotNullParameter(function0, "");
        NetworkUtils.f106649a.getClass();
        if (!NetworkUtils.d()) {
            function0.invoke();
        }
        return NetworkUtils.a(new Function1<NetworkUtils.NetworkType, Unit>() { // from class: com.vega.core.ext.FunctionKt$whenNotConnectToNetwork$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(NetworkUtils.NetworkType networkType) {
                Intrinsics.checkNotNullParameter(networkType, "");
                NetworkUtils.f106649a.getClass();
                if (!NetworkUtils.d()) {
                    function0.invoke();
                }
                return Unit.INSTANCE;
            }
        });
    }
}