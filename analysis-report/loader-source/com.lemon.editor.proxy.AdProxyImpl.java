package com.lemon.editor.proxy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.lemon.export.IExportApi;
import com.lemon.export.util.ExportDialogManager2;
import com.lemon.export.util.PriorityDialogManager;
import com.lemon.lv.config.PushMessageProxy;
import com.lemon.lv.config.TemplateExportInfoProxy;
import com.lemon.lv.editor.proxy.IAdProxy;
import com.lemon.lv.libpush.Message;
import com.lemon.lv.libpush.PushNotifyComponent;
import com.lemon.vega.ug.api.IDeepLinkService;
import com.vega.core.context.SPIService;
import com.vega.feedx.bean.IFeedxMain;
import com.vega.feelgoodapi.FeelGoodEvent;
import com.vega.feelgoodapi.FeelGoodService;
import com.vega.main.IMainService;
import com.vega.main.config.FlavorMainConfig;
import com.vega.performance.BadParcelableExceptionOpt;
import com.vega.ui.accomponent.AcComponent;
import com.vega.ui.accomponent.AcComponentActivity;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes.dex */
public final class AdProxyImpl implements IAdProxy {
    public static Intent INVOKEVIRTUAL_com_lemon_editor_proxy_AdProxyImpl_com_vega_launcher_lancet_BadParcelableLancet_getInttent(Activity activity) {
        Context context;
        Intent intent = activity.getIntent();
        if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
            intent.setExtrasClassLoader(context.getClassLoader());
        }
        return intent;
    }

    @Override // com.lemon.lv.editor.proxy.IAdProxy
    public final int a() {
        return ((IExportApi) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IExportApi.class), null)).a();
    }

    @Override // com.lemon.lv.editor.proxy.IAdProxy
    public final AcComponent b(AcComponentActivity acComponentActivity, Intent intent) {
        Intrinsics.checkNotNullParameter(acComponentActivity, "");
        return ((IDeepLinkService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IDeepLinkService.class), null)).b(acComponentActivity, intent);
    }

    @Override // com.lemon.lv.editor.proxy.IAdProxy
    public final String c() {
        return ((IFeedxMain) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IFeedxMain.class), null)).c();
    }

    @Override // com.lemon.lv.editor.proxy.IAdProxy
    public final String d() {
        return ((IFeedxMain) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IFeedxMain.class), null)).d();
    }

    @Override // com.lemon.lv.editor.proxy.IAdProxy
    public final String e() {
        return ((IExportApi) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IExportApi.class), null)).e();
    }

    @Override // com.lemon.lv.editor.proxy.IAdProxy
    public final String f() {
        return ((IMainService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IMainService.class), null)).f();
    }

    @Override // com.lemon.lv.editor.proxy.IAdProxy
    public final String g() {
        return ((IMainService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IMainService.class), null)).g();
    }

    @Override // com.lemon.lv.editor.proxy.IAdProxy
    public final void h(Activity activity, Function0<Unit> function0, Function1<? super Boolean, Unit> function1) {
        Intrinsics.checkNotNullParameter(activity, "");
        Intrinsics.checkNotNullParameter(function0, "");
        Intrinsics.checkNotNullParameter(function1, "");
        ((FeelGoodService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(FeelGoodService.class), null)).f(activity, FeelGoodEvent.i);
    }

    @Override // com.lemon.lv.editor.proxy.IAdProxy
    public final TemplateExportInfoProxy i() {
        return new TemplateExportInfoProxy(((IExportApi) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IExportApi.class), null)).i());
    }

    @Override // com.lemon.lv.editor.proxy.IAdProxy
    public final PushMessageProxy j(Intent intent) {
        PushNotifyComponent.f59513g.getClass();
        Message messageA = PushNotifyComponent.Companion.a(intent);
        if (messageA != null) {
            return new PushMessageProxy(messageA.f59506a, messageA.b, messageA.f59507c, messageA.f59508d, messageA.e);
        }
        return null;
    }

    @Override // com.lemon.lv.editor.proxy.IAdProxy
    public final boolean k(Activity activity) {
        Intent intentINVOKEVIRTUAL_com_lemon_editor_proxy_AdProxyImpl_com_vega_launcher_lancet_BadParcelableLancet_getInttent;
        Uri data;
        if (o(activity)) {
            return false;
        }
        return Intrinsics.areEqual((activity == null || (intentINVOKEVIRTUAL_com_lemon_editor_proxy_AdProxyImpl_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_lemon_editor_proxy_AdProxyImpl_com_vega_launcher_lancet_BadParcelableLancet_getInttent(activity)) == null || (data = intentINVOKEVIRTUAL_com_lemon_editor_proxy_AdProxyImpl_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getData()) == null) ? null : data.getScheme(), "capcut");
    }

    @Override // com.lemon.lv.editor.proxy.IAdProxy
    public final boolean l(int i) {
        ExportDialogManager2.f58759c.getClass();
        return PriorityDialogManager.b(i);
    }

    @Override // com.lemon.lv.editor.proxy.IAdProxy
    public final Object m(Function2<? super CoroutineScope, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super Integer> continuation) {
        return ExportDialogManager2.f58759c.c("DIALOG_AD", continuation, function2);
    }

    @Override // com.lemon.lv.editor.proxy.IAdProxy
    public final boolean n() {
        return ((FlavorMainConfig) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(FlavorMainConfig.class), null)).getDynamicDiscover().c();
    }

    @Override // com.lemon.lv.editor.proxy.IAdProxy
    public final boolean o(Activity activity) {
        return Intrinsics.areEqual(activity != null ? activity.getClass().getName() : null, g());
    }
}