package com.xt.retouch.business.templatetob.fragment.jump;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import com.bytedance.common.profilesdk.ProfileManager;
import com.bytedance.helios.statichook.api.ExtraInfo;
import com.bytedance.helios.statichook.api.HeliosApiHook;
import com.lemon.lvoverseas.R;
import com.lm.components.utils.AppMarketUtils;
import com.retouch.layermanager.api.layer.ILayerManager;
import com.retouch.layermanager.api.layer.Layer;
import com.retouch.layermanager.api.layer.LayerType;
import com.vega.config.CCHypicConfigHelper;
import com.vega.core.utils.FunctionsKt;
import com.vega.draft.monitor.DraftMonitorLancet;
import com.vega.draft.monitor.MonitorExtKt;
import com.vega.infrastructure.base.ModuleCommon;
import com.vega.libfiles.files.hook.FileAssist;
import com.vega.libfiles.files.hook.FileHook;
import com.vega.log.BLog;
import com.vega.performance.PerformanceManagerHelper;
import com.vega.util.ToastUtilKt;
import com.xt.retouch.apiservice.ApiService;
import com.xt.retouch.applauncher.api.AppContext;
import com.xt.retouch.baseui.dialog.CcConfrimDialog;
import com.xt.retouch.business.api.report.IBusinessPicReporter;
import com.xt.retouch.business.piceditor.PicEditIconReport;
import com.xt.retouch.business.templatetob.fragment.jump.JumpRetouchAbility;
import com.xt.retouch.config.api.IConfigManager;
import com.xt.retouch.config.api.model.HypicEntryConfig;
import com.xt.retouch.config.api.model.JsonConfig;
import com.xt.retouch.draft.api.IDraftLogic;
import com.xt.retouch.draftbox.api.ImageDraftBoxManager;
import com.xt.retouch.edit.base.util.GifGuideManager;
import com.xt.retouch.edit.base.util.RetouchTemplateReportUtils;
import com.xt.retouch.lib.log.XTLog;
import com.xt.retouch.scenes.api.draft.IDraftScenesModel;
import com.xt.retouch.util.KvSettingProvider;
import com.xt.retouch.util.ResourceUtil;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* loaded from: classes36.dex */
public final class JumpRetouchEditImpl implements IJumpRetouchEdit {

    /* renamed from: a, reason: collision with root package name */
    public IDraftScenesModel f140949a;
    public AppContext b;

    /* renamed from: c, reason: collision with root package name */
    public IDraftLogic f140950c;

    /* renamed from: d, reason: collision with root package name */
    public ILayerManager f140951d;
    public ImageDraftBoxManager e;
    public IConfigManager f;

    /* renamed from: g, reason: collision with root package name */
    public GifGuideManager f140952g;
    public final IBusinessPicReporter h;
    public JumpRetouchCallback i;
    public final Lazy j;

    /* loaded from: classes34.dex */
    public static final class Companion {
    }

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f140953a;

        static {
            int[] iArr = new int[JumpRetouchAbility.JumpType.values().length];
            try {
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            f140953a = iArr;
        }
    }

    static {
        new Companion();
    }

    public JumpRetouchEditImpl() {
        ApiService.f139261a.getClass();
        this.h = (IBusinessPicReporter) ApiService.a(IBusinessPicReporter.class);
        this.j = LazyKt__LazyJVMKt.lazy(new Function0<HypicEntryConfig>() { // from class: com.xt.retouch.business.templatetob.fragment.jump.JumpRetouchEditImpl$hypicEntryConfig$2
            {
                super(0);
            }

            /* JADX DEBUG: Multi-variable search result rejected for r0v6, resolved type: T */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function0
            public final HypicEntryConfig invoke() {
                HypicEntryConfig.Companion companion = HypicEntryConfig.f141420a;
                IConfigManager iConfigManager = this.e.f;
                if (iConfigManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("configManager");
                    iConfigManager = null;
                }
                JsonConfig jsonConfig = (JsonConfig) iConfigManager.getHypicEntryConfig().getValue();
                companion.getClass();
                return HypicEntryConfig.Companion.a(jsonConfig);
            }
        });
    }

    public static boolean INVOKEVIRTUAL_com_xt_retouch_business_templatetob_fragment_jump_JumpRetouchEditImpl_com_vega_draft_monitor_DraftMonitorLancet_delete(File file) {
        try {
            if (MonitorExtKt.e && (file instanceof File)) {
                String absolutePath = file.getAbsolutePath();
                MonitorExtKt.c(file);
                MonitorExtKt.b("DraftMonitorLancet#delete", absolutePath);
                if (!DraftMonitorLancet.a(absolutePath, "delete")) {
                    return false;
                }
            }
        } catch (Throwable unused) {
        }
        return INVOKEVIRTUAL_com_xt_retouch_business_templatetob_fragment_jump_JumpRetouchEditImpl_com_vega_libfiles_files_hook_FileHook_delete(file);
    }

    public static boolean INVOKEVIRTUAL_com_xt_retouch_business_templatetob_fragment_jump_JumpRetouchEditImpl_com_vega_libfiles_files_hook_FileHook_delete(File file) {
        FileAssist fileAssist = FileAssist.INSTANCE;
        if (!fileAssist.isEnable()) {
            return file.delete();
        }
        if (PerformanceManagerHelper.blogEnable) {
            BLog.i("FileHook", "hook_delete");
        }
        if (!(file instanceof File)) {
            return false;
        }
        fileAssist.awaitInspect(file);
        if (FileHook.resolvePath(file)) {
            return file.delete();
        }
        return false;
    }

    public static boolean INVOKEVIRTUAL_com_xt_retouch_business_templatetob_fragment_jump_JumpRetouchEditImpl_com_vega_libfiles_files_hook_FileHook_renameTo(File file, File file2) {
        if (FileAssist.INSTANCE.isEnable()) {
            if (PerformanceManagerHelper.blogEnable) {
                BLog.i("FileHook", "hook renameTo");
            }
            if (file instanceof File) {
                if (PerformanceManagerHelper.blogEnable) {
                    BLog.i("FileHook", "from: " + file.getAbsolutePath() + " renameTo: " + file2.getAbsolutePath());
                }
                if (FileHook.isInMonitoredAppDir(file.getAbsolutePath())) {
                    FileHook.collectStack(file, true, true);
                }
            }
        }
        return file.renameTo(file2);
    }

    public static void c(CcConfrimDialog ccConfrimDialog) {
        if (new HeliosApiHook().preInvoke(300000, "com/xt/retouch/baseui/dialog/CcConfrimDialog", "show", ccConfrimDialog, new Object[0], "void", new ExtraInfo(false, "()V", "dzBzEhouEdjZQVg0RhrKZM8X+sFhGnkJHwe2GJDurvyABWG52/UL6/YdjiVJyQ5r9xqgQmyxeAuWF7LP73/mXEy2Y3WA")).isIntercept()) {
            return;
        }
        ccConfrimDialog.show();
    }

    public static void f() {
        CCHypicConfigHelper.f78907a.getClass();
        if (((Boolean) CCHypicConfigHelper.f78910g.getValue()).booleanValue()) {
            ToastUtilKt.e(FunctionsKt.b(R.string.o0n), 0, 0, 0, 0, false, null, false, 510);
            return;
        }
        AppMarketUtils appMarketUtils = AppMarketUtils.f61171a;
        Application application = ModuleCommon.INSTANCE.getApplication();
        appMarketUtils.getClass();
        AppMarketUtils.b(application, "https://hypic.onelink.me/yzyp/cusqdj20?deep_link_value=retouchoversea://picture_edit/edit");
    }

    public final Uri a(JumpRetouchAbility jumpRetouchAbility, String str) {
        if (WhenMappings.f140953a[jumpRetouchAbility.c().b.ordinal()] == 1) {
            Uri uriBuild = Uri.parse(jumpRetouchAbility.c().e ? "retouchoversea://capcut_edit/business" : "retouchoversea://vega_edit").buildUpon().appendQueryParameter("draft_path", str).appendQueryParameter("videocut_source", "videoCutSource").appendQueryParameter("autosave", ProfileManager.VERSION).appendQueryParameter("app_name", jumpRetouchAbility.c().f140940a).appendQueryParameter("entry", "capcut").appendQueryParameter("business_template_id", jumpRetouchAbility.c().f).appendQueryParameter("from_videocut_draft_id", d()).build();
            Intrinsics.checkNotNull(uriBuild);
            return uriBuild;
        }
        Uri uriBuild2 = Uri.parse("retouchoversea://picture_edit" + jumpRetouchAbility.c().h).buildUpon().appendQueryParameter("picture_path", str).appendQueryParameter("videocut_source", jumpRetouchAbility.c().f140943g).appendQueryParameter("autosave", ProfileManager.VERSION).appendQueryParameter("app_name", jumpRetouchAbility.c().f140940a).appendQueryParameter("entry", "capcut").appendQueryParameter("business_template_id", jumpRetouchAbility.c().f).appendQueryParameter("from_videocut_draft_id", d()).build();
        Intrinsics.checkNotNull(uriBuild2);
        return uriBuild2;
    }

    public final boolean b(final AppCompatActivity appCompatActivity, int i, JumpRetouchAbility.JumpType jumpType, String str, final String str2) {
        if (i == -1) {
            RetouchTemplateReportUtils retouchTemplateReportUtils = RetouchTemplateReportUtils.f142320a;
            String strD = d();
            retouchTemplateReportUtils.getClass();
            RetouchTemplateReportUtils.f(0, "show", str2, strD);
            CcConfrimDialog ccConfrimDialog = new CcConfrimDialog(appCompatActivity, new Function0<Unit>() { // from class: com.xt.retouch.business.templatetob.fragment.jump.JumpRetouchEditImpl$checkNeedJumpGoogleShop$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    RetouchTemplateReportUtils retouchTemplateReportUtils2 = RetouchTemplateReportUtils.f142320a;
                    String str3 = str2;
                    String strD2 = this.d();
                    retouchTemplateReportUtils2.getClass();
                    RetouchTemplateReportUtils.f(0, "download_retouch", str3, strD2);
                    RetouchTemplateReportUtils.d("download_retouch", str2, this.d());
                    this.getClass();
                    JumpRetouchEditImpl.f();
                    return Unit.INSTANCE;
                }
            }, new Function0<Unit>() { // from class: com.xt.retouch.business.templatetob.fragment.jump.JumpRetouchEditImpl$checkNeedJumpGoogleShop$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    RetouchTemplateReportUtils retouchTemplateReportUtils2 = RetouchTemplateReportUtils.f142320a;
                    String str3 = str2;
                    String strD2 = this.d();
                    retouchTemplateReportUtils2.getClass();
                    RetouchTemplateReportUtils.f(0, "cancel", str3, strD2);
                    return Unit.INSTANCE;
                }
            }, null, null, null, null, 8184);
            j(ccConfrimDialog, jumpType, str);
            c(ccConfrimDialog);
        } else {
            if (i > 15000) {
                return false;
            }
            RetouchTemplateReportUtils retouchTemplateReportUtils2 = RetouchTemplateReportUtils.f142320a;
            String strD2 = d();
            retouchTemplateReportUtils2.getClass();
            RetouchTemplateReportUtils.f(2, "show", str2, strD2);
            CcConfrimDialog ccConfrimDialog2 = new CcConfrimDialog(appCompatActivity, new Function0<Unit>() { // from class: com.xt.retouch.business.templatetob.fragment.jump.JumpRetouchEditImpl$checkNeedJumpGoogleShop$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    RetouchTemplateReportUtils retouchTemplateReportUtils3 = RetouchTemplateReportUtils.f142320a;
                    String str3 = str2;
                    String strD3 = this.d();
                    retouchTemplateReportUtils3.getClass();
                    RetouchTemplateReportUtils.f(2, "update_retouch", str3, strD3);
                    RetouchTemplateReportUtils.d("update_retouch", str2, this.d());
                    this.getClass();
                    JumpRetouchEditImpl.f();
                    return Unit.INSTANCE;
                }
            }, new Function0<Unit>() { // from class: com.xt.retouch.business.templatetob.fragment.jump.JumpRetouchEditImpl$checkNeedJumpGoogleShop$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    RetouchTemplateReportUtils retouchTemplateReportUtils3 = RetouchTemplateReportUtils.f142320a;
                    String str3 = str2;
                    String strD3 = this.d();
                    retouchTemplateReportUtils3.getClass();
                    RetouchTemplateReportUtils.f(2, "cancel", str3, strD3);
                    return Unit.INSTANCE;
                }
            }, ResourceUtil.g(ResourceUtil.f150132a, R.string.tnc), ResourceUtil.f(R.string.sh4, null), ResourceUtil.f(R.string.slx, null), null, 7960);
            j(ccConfrimDialog2, jumpType, str);
            c(ccConfrimDialog2);
        }
        return true;
    }

    public final String d() {
        return e().U1();
    }

    public final IDraftScenesModel e() {
        IDraftScenesModel iDraftScenesModel = this.f140949a;
        if (iDraftScenesModel != null) {
            return iDraftScenesModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("sceneModel");
        return null;
    }

    public final void g(final JumpRetouchAbility jumpRetouchAbility, IDraftScenesModel iDraftScenesModel, final JumpRetouchCallback jumpRetouchCallback, String str) throws Exception {
        PackageInfo packageInfo;
        Intrinsics.checkNotNullParameter(jumpRetouchAbility, "");
        Intrinsics.checkNotNullParameter(iDraftScenesModel, "");
        Intrinsics.checkNotNullParameter(str, "");
        this.f140949a = iDraftScenesModel;
        AppCompatActivity activity = jumpRetouchAbility.getActivity();
        if (activity == null) {
            return;
        }
        this.i = jumpRetouchCallback;
        try {
            packageInfo = activity.getPackageManager().getPackageInfo("com.xt.retouchoversea", 0);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        int i = packageInfo != null ? packageInfo.versionCode : -1;
        XTLog.f144340a.getClass();
        XTLog.e("JumpRetouchEditImpl", "current versionCode: " + i);
        JumpRetouchAbility.JumpType jumpType = jumpRetouchAbility.c().b;
        PicEditIconReport.f140785a.getClass();
        Intrinsics.checkNotNullParameter(jumpType, "");
        JumpRetouchAbility.JumpType jumpType2 = JumpRetouchAbility.JumpType.b;
        final String str2 = jumpType == jumpType2 ? "photo_editor_category" : "ads_pic_template_edit";
        if (jumpType != jumpType2 || !Intrinsics.areEqual(str, "template_project_edit_more")) {
            RetouchTemplateReportUtils.e(RetouchTemplateReportUtils.f142320a, str2, "click", d());
        }
        int iOrdinal = jumpType.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1 && !b(activity, i, jumpType, str, str2)) {
                if (!KvSettingProvider.f150073a.a3()) {
                    i(jumpRetouchAbility);
                    return;
                }
                CcConfrimDialog ccConfrimDialog = new CcConfrimDialog(activity, new Function0<Unit>() { // from class: com.xt.retouch.business.templatetob.fragment.jump.JumpRetouchEditImpl$jumRetouch$4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function0
                    public final Unit invoke() throws Exception {
                        this.e.i(jumpRetouchAbility);
                        RetouchTemplateReportUtils retouchTemplateReportUtils = RetouchTemplateReportUtils.f142320a;
                        String str3 = str2;
                        String strD = this.e.d();
                        retouchTemplateReportUtils.getClass();
                        RetouchTemplateReportUtils.f(1, "click", str3, strD);
                        KvSettingProvider.f150073a.B8();
                        return Unit.INSTANCE;
                    }
                }, new Function0<Unit>() { // from class: com.xt.retouch.business.templatetob.fragment.jump.JumpRetouchEditImpl$jumRetouch$5
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function0
                    public final Unit invoke() {
                        RetouchTemplateReportUtils retouchTemplateReportUtils = RetouchTemplateReportUtils.f142320a;
                        String str3 = str2;
                        String strD = this.d();
                        retouchTemplateReportUtils.getClass();
                        RetouchTemplateReportUtils.f(1, "cancel", str3, strD);
                        return Unit.INSTANCE;
                    }
                }, ResourceUtil.g(ResourceUtil.f150132a, R.string.tkq), ResourceUtil.f(R.string.tu3, null), ResourceUtil.f(R.string.slx, null), null, 7960);
                j(ccConfrimDialog, jumpType, str);
                c(ccConfrimDialog);
                RetouchTemplateReportUtils retouchTemplateReportUtils = RetouchTemplateReportUtils.f142320a;
                String strD = d();
                retouchTemplateReportUtils.getClass();
                RetouchTemplateReportUtils.f(1, "show", str2, strD);
                return;
            }
            return;
        }
        if (!jumpRetouchAbility.c().e || b(activity, i, jumpType, str, str2)) {
            return;
        }
        if (i < 29000) {
            ILayerManager iLayerManager = this.f140951d;
            if (iLayerManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("layerManager");
                iLayerManager = null;
            }
            Iterator<T> it = iLayerManager.I1().iterator();
            while (it.hasNext()) {
                if (((Layer) it.next()).T1() == LayerType.r) {
                    c(new CcConfrimDialog(activity, new Function0<Unit>() { // from class: com.xt.retouch.business.templatetob.fragment.jump.JumpRetouchEditImpl$checkExitImageContainer$1$1
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function0
                        public final /* bridge */ /* synthetic */ Unit invoke() {
                            return Unit.INSTANCE;
                        }
                    }, new Function0<Unit>() { // from class: com.xt.retouch.business.templatetob.fragment.jump.JumpRetouchEditImpl$checkExitImageContainer$1$2
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function0
                        public final /* bridge */ /* synthetic */ Unit invoke() {
                            return Unit.INSTANCE;
                        }
                    }, ResourceUtil.g(ResourceUtil.f150132a, R.string.szd), ResourceUtil.f(R.string.szb, null), null, ResourceUtil.f(R.string.szc, null), 7320));
                    return;
                }
            }
        }
        RetouchTemplateReportUtils.f142320a.getClass();
        HashMap map = new HashMap();
        map.put("click", "more");
        map.putAll(RetouchTemplateReportUtils.a());
        RetouchTemplateReportUtils.b("click_template_edit_option", map);
        if (!KvSettingProvider.f150073a.a3()) {
            if (jumpRetouchCallback != null) {
                jumpRetouchCallback.b();
            }
            h(jumpRetouchAbility, i);
            return;
        }
        RetouchTemplateReportUtils.f(1, "show", str2, d());
        final String str3 = str2;
        final int i2 = i;
        CcConfrimDialog ccConfrimDialog2 = new CcConfrimDialog(activity, new Function0<Unit>() { // from class: com.xt.retouch.business.templatetob.fragment.jump.JumpRetouchEditImpl$jumRetouch$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() throws Exception {
                RetouchTemplateReportUtils retouchTemplateReportUtils2 = RetouchTemplateReportUtils.f142320a;
                String str4 = str3;
                String strD2 = this.d();
                retouchTemplateReportUtils2.getClass();
                RetouchTemplateReportUtils.f(1, "launch_retouch", str4, strD2);
                JumpRetouchCallback jumpRetouchCallback2 = jumpRetouchCallback;
                if (jumpRetouchCallback2 != null) {
                    jumpRetouchCallback2.b();
                }
                this.h(jumpRetouchAbility, i2);
                KvSettingProvider.f150073a.B8();
                return Unit.INSTANCE;
            }
        }, new Function0<Unit>() { // from class: com.xt.retouch.business.templatetob.fragment.jump.JumpRetouchEditImpl$jumRetouch$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                RetouchTemplateReportUtils retouchTemplateReportUtils2 = RetouchTemplateReportUtils.f142320a;
                String str4 = str2;
                String strD2 = this.d();
                retouchTemplateReportUtils2.getClass();
                RetouchTemplateReportUtils.f(1, "cancel", str4, strD2);
                return Unit.INSTANCE;
            }
        }, ResourceUtil.g(ResourceUtil.f150132a, R.string.tkq), ResourceUtil.f(R.string.tu3, null), ResourceUtil.f(R.string.slx, null), null, 7960);
        j(ccConfrimDialog2, jumpType, str);
        c(ccConfrimDialog2);
    }

    public final void h(JumpRetouchAbility jumpRetouchAbility, int i) throws Exception {
        RetouchTemplateReportUtils retouchTemplateReportUtils = RetouchTemplateReportUtils.f142320a;
        String strD = d();
        retouchTemplateReportUtils.getClass();
        RetouchTemplateReportUtils.d("launch_retouch", "videoCutSource", strD);
        String str = jumpRetouchAbility.c().f140941c;
        if (str.length() == 0) {
            throw new Exception("jump with draft need draft path");
        }
        AppCompatActivity activity = jumpRetouchAbility.getActivity();
        if (activity == null) {
            return;
        }
        jumpRetouchAbility.e(true);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new JumpRetouchEditImpl$jumpWithDraft$1(this, str, activity, i, jumpRetouchAbility, null), 2, null);
    }

    public final void i(JumpRetouchAbility jumpRetouchAbility) throws Exception {
        RetouchTemplateReportUtils retouchTemplateReportUtils = RetouchTemplateReportUtils.f142320a;
        String strD = d();
        retouchTemplateReportUtils.getClass();
        RetouchTemplateReportUtils.d("launch_retouch", "photo_editor_category", strD);
        String str = jumpRetouchAbility.c().f140942d;
        if (str.length() == 0) {
            throw new Exception("jump with draft need draft path");
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new JumpRetouchEditImpl$jumpWithPicture$1(this, str, jumpRetouchAbility, null), 2, null);
    }

    public final void j(CcConfrimDialog ccConfrimDialog, JumpRetouchAbility.JumpType jumpType, String str) {
        HypicEntryConfig hypicEntryConfig;
        String str2;
        if (jumpType != JumpRetouchAbility.JumpType.b || !Intrinsics.areEqual(str, "pic_edit") || (hypicEntryConfig = (HypicEntryConfig) this.j.getValue()) == null || !hypicEntryConfig.d()) {
            ccConfrimDialog.r = true;
            ccConfrimDialog.o = false;
            return;
        }
        GifGuideManager gifGuideManager = this.f140952g;
        if (gifGuideManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("gifGuideManager");
            gifGuideManager = null;
        }
        GifGuideManager.GifGuideConfig gifGuideConfigB = GifGuideManager.b(gifGuideManager, GifGuideManager.GifGuideType.b);
        String strG = ResourceUtil.g(ResourceUtil.f150132a, R.string.sx4);
        String strF = ResourceUtil.f(R.string.sx5, null);
        String str3 = gifGuideConfigB != null ? gifGuideConfigB.f142288c : null;
        if (str3 == null) {
            str3 = "";
        }
        if (gifGuideConfigB == null || (str2 = gifGuideConfigB.b) == null) {
            str2 = "";
        }
        Intrinsics.checkNotNullParameter(strG, "");
        Intrinsics.checkNotNullParameter(strF, "");
        ccConfrimDialog.j = strG;
        ccConfrimDialog.m = strF;
        ccConfrimDialog.p = str3;
        ccConfrimDialog.q = str2;
    }
}