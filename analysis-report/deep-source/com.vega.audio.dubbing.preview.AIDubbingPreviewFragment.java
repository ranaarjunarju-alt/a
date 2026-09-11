package com.vega.audio.dubbing.preview;

import X.C113073om;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.util.Consumer;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.bytedance.helios.statichook.api.ExtraInfo;
import com.bytedance.helios.statichook.api.HeliosApiHook;
import com.lemon.lv.editor.proxy.IAIDubbingPreviewDigtalHumanController;
import com.lemon.lvoverseas.R;
import com.vega.audio.dubbing.AIDubbingUtil;
import com.vega.audio.dubbing.core.view.AIDubbingEditTextView;
import com.vega.audio.dubbing.model.AIDubbingPageState;
import com.vega.audio.dubbing.model.AIDubbingViewModel;
import com.vega.audio.dubbing.page.IAIDubbingPage;
import com.vega.audio.dubbing.preview.AIDubbingPreviewFragment;
import com.vega.audio.dubbing.statistics.AIDubbingReporter;
import com.vega.audio.dubbing.view.AIDubbingChangeVoicePanel;
import com.vega.audio.tone.viewmodel.AudioToneSelectViewModel;
import com.vega.commoneditor.ICommonEditorReport;
import com.vega.config.ConfigSettingsKt;
import com.vega.core.context.SPIService;
import com.vega.core.ext.LiveDataExtKt;
import com.vega.core.utils.FunctionsKt;
import com.vega.core.utils.PadUtil;
import com.vega.edit.base.event.CommonEditorFinishEvent;
import com.vega.gallery.utils.AiWriterReportData;
import com.vega.infrastructure.extensions.ThreadUtilKt;
import com.vega.infrastructure.koin.GetViewModelKt;
import com.vega.infrastructure.koin.KoinScopeFragment;
import com.vega.infrastructure.koin.ScopeExKt;
import com.vega.infrastructure.util.KeyboardUtils;
import com.vega.libeffectapi.settings.AIDubbingParamConfig;
import com.vega.libeffectapi.settings.AIDubbingParamConfigSetting;
import com.vega.log.BLog;
import com.vega.ui.dialog.BaseDialog;
import com.vega.ui.dialog.ConfirmCancelDialog;
import com.vega.ui.dialog.ConfirmCancelDialogWith16Radius;
import com.vega.ui.widget.TextInputSampleView;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Regex;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.core.qualifier.Qualifier;

/* loaded from: classes12.dex */
public final class AIDubbingPreviewFragment extends KoinScopeFragment implements IAIDubbingPage {
    public static final /* synthetic */ int y = 0;

    /* renamed from: c, reason: collision with root package name */
    public final Lazy f73017c;

    /* renamed from: d, reason: collision with root package name */
    public final Lazy f73018d;
    public ImageView e;
    public TextView f;

    /* renamed from: g, reason: collision with root package name */
    public View f73019g;
    public AIDubbingEditTextView h;
    public TextInputSampleView i;
    public View j;
    public View k;
    public View l;
    public LinearLayout m;
    public LinearLayout n;
    public LinearLayout o;
    public LinearLayout p;
    public LinearLayout q;
    public IAIDubbingPreviewDigtalHumanController r;
    public AIDubbingPreviewExportController s;
    public AIDubbingPreviewToneController t;
    public AIDubbingTextFunctionController u;
    public AIDubbingPreviewHeightController v;
    public final C113073om w;
    public ConfirmCancelDialogWith16Radius x;

    /* loaded from: classes18.dex */
    public static final class Companion {
    }

    static {
        new Companion();
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [X.3om] */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$special$$inlined$activityFactoryViewModel$1] */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$special$$inlined$activityFactoryViewModel$3] */
    public AIDubbingPreviewFragment() {
        final ?? r1 = new Function0<FragmentActivity>() { // from class: com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$special$$inlined$activityFactoryViewModel$1
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final FragmentActivity invoke() {
                FragmentActivity fragmentActivityRequireActivity = this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "");
                return fragmentActivityRequireActivity;
            }
        };
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        this.f73017c = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<AIDubbingViewModel>() { // from class: com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$special$$inlined$activityFactoryViewModel$2
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.audio.dubbing.model.AIDubbingViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final AIDubbingViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r1;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(AIDubbingViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r12 = new Function0<FragmentActivity>() { // from class: com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$special$$inlined$activityFactoryViewModel$3
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final FragmentActivity invoke() {
                FragmentActivity fragmentActivityRequireActivity = this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "");
                return fragmentActivityRequireActivity;
            }
        };
        this.f73018d = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<AudioToneSelectViewModel>() { // from class: com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$special$$inlined$activityFactoryViewModel$4
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.audio.tone.viewmodel.AudioToneSelectViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final AudioToneSelectViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r12;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(AudioToneSelectViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        this.w = new Consumer() { // from class: X.3om
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                final AIDubbingPreviewFragment aIDubbingPreviewFragment = this.f17162a;
                PadUtil.f79639a.getClass();
                if (PadUtil.o()) {
                    KeyboardUtils keyboardUtils = KeyboardUtils.f106639a;
                    View view = aIDubbingPreviewFragment.getView();
                    if (view == null) {
                        return;
                    }
                    keyboardUtils.getClass();
                    KeyboardUtils.b(view);
                    ThreadUtilKt.e(150L, new Function0<Unit>() { // from class: com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$orientationListener$1$1
                        {
                            super(0);
                        }

                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function0
                        public final Unit invoke() {
                            AIDubbingPreviewHeightController aIDubbingPreviewHeightController = aIDubbingPreviewFragment.v;
                            if (aIDubbingPreviewHeightController != null) {
                                aIDubbingPreviewHeightController.a();
                            }
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:84:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:95:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void Y3(final android.text.SpannableString r12, final boolean r13, final boolean r14, final boolean r15) {
        /*
            r11 = this;
            r5 = 1
            r3 = 0
            if (r12 == 0) goto La
            int r0 = r12.length()
            if (r0 != 0) goto Lc4
        La:
            r1 = 1
        Lb:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r0 = "checkFunctionValidate -> containEmptyNode="
            r2.<init>(r0)
            r7 = r13
            r2.append(r7)
            java.lang.String r0 = ", containEmptyText="
            r2.append(r0)
            r2.append(r1)
            java.lang.String r0 = ", selectRecordInfo="
            r2.append(r0)
            r8 = r15
            r2.append(r8)
            java.lang.String r2 = r2.toString()
            java.lang.String r0 = "AIDubbingPreviewFragment"
            com.vega.log.BLog.i(r0, r2)
            android.widget.LinearLayout r0 = r11.n
            r4 = 1053609165(0x3ecccccd, float:0.4)
            r2 = 1065353216(0x3f800000, float:1.0)
            r9 = r14
            if (r0 == 0) goto L46
            if (r7 != 0) goto L40
            if (r9 != 0) goto L40
            if (r8 == 0) goto Lbd
        L40:
            r0.setAlpha(r4)
            r0.setEnabled(r3)
        L46:
            android.widget.LinearLayout r0 = r11.m
            if (r0 == 0) goto L56
            if (r7 != 0) goto L50
            if (r9 != 0) goto L50
            if (r8 == 0) goto Lb6
        L50:
            r0.setAlpha(r4)
            r0.setEnabled(r3)
        L56:
            android.widget.LinearLayout r0 = r11.o
            if (r0 == 0) goto L66
            if (r7 != 0) goto L60
            if (r9 != 0) goto L60
            if (r8 == 0) goto Laf
        L60:
            r0.setAlpha(r4)
            r0.setEnabled(r3)
        L66:
            android.view.View r0 = r11.l
            if (r0 == 0) goto L74
            if (r7 != 0) goto L6e
            if (r8 == 0) goto La8
        L6e:
            r0.setAlpha(r4)
            r0.setEnabled(r3)
        L74:
            android.widget.LinearLayout r10 = r11.q
            r5 = 10
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r10 == 0) goto Lc7
            com.vega.audio.dubbing.model.AIDubbingViewModel r0 = r11.c4()
            com.vega.audio.dubbing.core.AIDubbingDraftManager r0 = r0.f
            java.util.ArrayList r2 = r0.r(r3, r2)
            java.util.ArrayList r4 = new java.util.ArrayList
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r2, r5)
            r4.<init>(r0)
            java.util.Iterator r2 = r2.iterator()
        L94:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto Lcb
            java.lang.Object r0 = r2.next()
            kotlin.Pair r0 = (kotlin.Pair) r0
            java.lang.Object r0 = r0.getFirst()
            r4.add(r0)
            goto L94
        La8:
            r0.setAlpha(r2)
            r0.setEnabled(r5)
            goto L74
        Laf:
            r0.setAlpha(r2)
            r0.setEnabled(r5)
            goto L66
        Lb6:
            r0.setAlpha(r2)
            r0.setEnabled(r5)
            goto L56
        Lbd:
            r0.setAlpha(r2)
            r0.setEnabled(r5)
            goto L46
        Lc4:
            r1 = 0
            goto Lb
        Lc7:
            r2 = 2147483647(0x7fffffff, float:NaN)
            goto Ldb
        Lcb:
            com.vega.audio.dubbing.AIDubbingUtil r0 = com.vega.audio.dubbing.AIDubbingUtil.f72854a
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$checkFunctionValidate$5$1 r6 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$checkFunctionValidate$5$1
            r2 = 2147483647(0x7fffffff, float:NaN)
            r6.<init>()
            r0.getClass()
            com.vega.audio.dubbing.AIDubbingUtil.y(r4, r6)
        Ldb:
            android.widget.LinearLayout r10 = r11.p
            if (r10 == 0) goto L117
            com.vega.audio.dubbing.model.AIDubbingViewModel r0 = r11.c4()
            com.vega.audio.dubbing.core.AIDubbingDraftManager r0 = r0.f
            java.util.ArrayList r2 = r0.r(r3, r2)
            java.util.ArrayList r4 = new java.util.ArrayList
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r2, r5)
            r4.<init>(r0)
            java.util.Iterator r2 = r2.iterator()
        Lf6:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L10a
            java.lang.Object r0 = r2.next()
            kotlin.Pair r0 = (kotlin.Pair) r0
            java.lang.Object r0 = r0.getFirst()
            r4.add(r0)
            goto Lf6
        L10a:
            com.vega.audio.dubbing.AIDubbingUtil r0 = com.vega.audio.dubbing.AIDubbingUtil.f72854a
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$checkFunctionValidate$6$1 r6 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$checkFunctionValidate$6$1
            r6.<init>()
            r0.getClass()
            com.vega.audio.dubbing.AIDubbingUtil.x(r4, r6)
        L117:
            android.view.View r2 = r11.j
            r0 = 0
            if (r2 == 0) goto L12f
            if (r7 != 0) goto L120
            if (r8 == 0) goto L1bd
        L120:
            com.vega.infrastructure.extensions.ViewExtKt.e(r2)
            android.view.View r2 = r11.j
            if (r2 == 0) goto L12f
            X.0CC r0 = new X.0CC
            r0.<init>()
            r2.setOnClickListener(r0)
        L12f:
            android.view.View r2 = r11.getView()
            if (r2 == 0) goto L14f
            r0 = 2131296858(0x7f09025a, float:1.8211645E38)
            android.view.View r2 = r2.findViewById(r0)
            if (r2 == 0) goto L14f
            if (r12 == 0) goto L146
            int r0 = r12.length()
            if (r0 != 0) goto L1b3
        L146:
            r0 = 1053609165(0x3ecccccd, float:0.4)
            r2.setAlpha(r0)
            r2.setEnabled(r3)
        L14f:
            com.vega.audio.dubbing.preview.AIDubbingTextFunctionController r2 = r11.u
            if (r2 == 0) goto L160
            if (r12 == 0) goto L15b
            int r0 = r12.length()
            if (r0 != 0) goto L1ad
        L15b:
            android.view.ViewGroup r0 = r2.f73050a
            com.vega.infrastructure.extensions.ViewExtKt.e(r0)
        L160:
            com.vega.audio.dubbing.preview.AIDubbingPreviewHeightController r4 = r11.v
            if (r4 == 0) goto L176
            if (r12 == 0) goto L16c
            int r0 = r12.length()
            if (r0 != 0) goto L176
        L16c:
            com.vega.infrastructure.util.KeyboardUtils r2 = com.vega.infrastructure.util.KeyboardUtils.f106639a
            com.vega.audio.dubbing.core.view.AIDubbingEditTextView r0 = r4.f73030d
            r2.getClass()
            com.vega.infrastructure.util.KeyboardUtils.b(r0)
        L176:
            android.widget.TextView r0 = r11.f
            if (r0 == 0) goto L1a9
            if (r1 == 0) goto L19c
            r4 = 1053609165(0x3ecccccd, float:0.4)
            r0.setAlpha(r4)
            r0.setEnabled(r3)
        L185:
            r2 = 1065353216(0x3f800000, float:1.0)
            r1 = 1
        L188:
            android.view.View r0 = r11.f73019g
            if (r0 == 0) goto L194
            if (r7 == 0) goto L195
            r0.setAlpha(r4)
            r0.setEnabled(r3)
        L194:
            return
        L195:
            r0.setAlpha(r2)
            r0.setEnabled(r1)
            goto L194
        L19c:
            r4 = 1053609165(0x3ecccccd, float:0.4)
            r2 = 1065353216(0x3f800000, float:1.0)
            r0.setAlpha(r2)
            r1 = 1
            r0.setEnabled(r1)
            goto L188
        L1a9:
            r4 = 1053609165(0x3ecccccd, float:0.4)
            goto L185
        L1ad:
            android.view.ViewGroup r0 = r2.f73050a
            com.vega.infrastructure.extensions.ViewExtKt.b(r0)
            goto L160
        L1b3:
            r0 = 1065353216(0x3f800000, float:1.0)
            r2.setAlpha(r0)
            r0 = 1
            r2.setEnabled(r0)
            goto L14f
        L1bd:
            com.vega.infrastructure.extensions.ViewExtKt.b(r2)
            r2.setOnClickListener(r0)
            goto L12f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.dubbing.preview.AIDubbingPreviewFragment.Y3(android.text.SpannableString, boolean, boolean, boolean):void");
    }

    public final void Z3() {
        AIDubbingEditTextView aIDubbingEditTextView = this.h;
        if (aIDubbingEditTextView != null) {
            aIDubbingEditTextView.setSelection(0);
        }
        AIDubbingEditTextView aIDubbingEditTextView2 = this.h;
        if (aIDubbingEditTextView2 != null) {
            aIDubbingEditTextView2.clearFocus();
        }
    }

    public final AudioToneSelectViewModel a4() {
        return (AudioToneSelectViewModel) this.f73018d.getValue();
    }

    public final AIDubbingViewModel c4() {
        return (AIDubbingViewModel) this.f73017c.getValue();
    }

    public final boolean d4() {
        return c4().p.getValue() == AIDubbingPageState.f72924a;
    }

    public final void e4(String str) {
        if (d4()) {
            AIDubbingUtil.f72854a.getClass();
            Intrinsics.checkNotNullParameter(str, "");
            String strC = ((AIDubbingParamConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AIDubbingParamConfigSetting.class))).c();
            if (strC != null && strC.length() != 0) {
                str = new Regex(strC).replace(str, "");
            }
            c4().L = true;
            c4().G.g(str);
        }
    }

    @Override // com.vega.audio.dubbing.page.IAIDubbingPage
    public final boolean onBackPressed() {
        if (!d4()) {
            BLog.i("AIDubbingPreviewFragment", "onBackPressed -> !isInPreview()");
            return false;
        }
        if (c4().R) {
            KeyboardUtils keyboardUtils = KeyboardUtils.f106639a;
            View view = getView();
            if (view == null) {
                return false;
            }
            keyboardUtils.getClass();
            KeyboardUtils.b(view);
            BLog.i("AIDubbingPreviewFragment", "onBackPressed -> isKeyboardVisible");
            return true;
        }
        AIDubbingPreviewToneController aIDubbingPreviewToneController = this.t;
        if (aIDubbingPreviewToneController != null) {
            FrameLayout frameLayout = aIDubbingPreviewToneController.f;
            View view2 = aIDubbingPreviewToneController.f73036J;
            if (view2 != null) {
                boolean z = frameLayout.indexOfChild(view2) != -1;
                View view3 = aIDubbingPreviewToneController.f73036J;
                boolean z2 = view3 != null && view3.getVisibility() == 0;
                if (z && z2) {
                    LiveDataExtKt.o(aIDubbingPreviewToneController.d().I, Boolean.FALSE);
                } else {
                    BaseDialog baseDialog = aIDubbingPreviewToneController.D;
                    if (baseDialog == null || !baseDialog.isShowing()) {
                        AIDubbingChangeVoicePanel aIDubbingChangeVoicePanel = aIDubbingPreviewToneController.h;
                        if (aIDubbingChangeVoicePanel != null) {
                            aIDubbingChangeVoicePanel.dismiss();
                            aIDubbingPreviewToneController.h = null;
                        }
                    } else {
                        aIDubbingPreviewToneController.h();
                    }
                }
                BLog.i("AIDubbingPreviewFragment", "onBackPressed -> controllerOnBackpressed");
                return true;
            }
        }
        AIDubbingUtil aIDubbingUtil = AIDubbingUtil.f72854a;
        AIDubbingViewModel aIDubbingViewModelC4 = c4();
        aIDubbingUtil.getClass();
        if (AIDubbingUtil.i(aIDubbingViewModelC4)) {
            String strU6 = c4().u6();
            Intrinsics.checkNotNullParameter(strU6, "");
            AIDubbingUtil.f72855c = strU6;
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
            return false;
        }
        if (c4().w6().length() <= 0) {
            FragmentActivity activity2 = getActivity();
            if (activity2 != null) {
                activity2.finish();
            }
            return false;
        }
        if (this.x == null) {
            FragmentActivity activity3 = getActivity();
            if (activity3 == null) {
                return false;
            }
            ConfirmCancelDialogWith16Radius confirmCancelDialogWith16Radius = new ConfirmCancelDialogWith16Radius(activity3, new Function0<Unit>() { // from class: com.vega.audio.dubbing.preview.AIDubbingPreviewFragment.onBackPressed.1
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    AIDubbingPreviewExportController aIDubbingPreviewExportController = AIDubbingPreviewFragment.this.s;
                    if (aIDubbingPreviewExportController != null) {
                        aIDubbingPreviewExportController.c(false);
                    }
                    AIDubbingReporter.f73057a.getClass();
                    AIDubbingReporter.c("confirm");
                    return Unit.INSTANCE;
                }
            }, new Function0<Unit>() { // from class: com.vega.audio.dubbing.preview.AIDubbingPreviewFragment.onBackPressed.2
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    FragmentActivity activity4 = AIDubbingPreviewFragment.this.getActivity();
                    if (activity4 != null) {
                        activity4.finish();
                    }
                    AIDubbingReporter.f73057a.getClass();
                    AIDubbingReporter.c("cancel");
                    return Unit.INSTANCE;
                }
            }, 8);
            confirmCancelDialogWith16Radius.s(FunctionsKt.b(R.string.uu));
            ConfirmCancelDialog.o(confirmCancelDialogWith16Radius, FunctionsKt.b(R.string.uw), null, null, 6);
            confirmCancelDialogWith16Radius.m(FunctionsKt.b(R.string.uv));
            confirmCancelDialogWith16Radius.k(FunctionsKt.b(R.string.ti));
            confirmCancelDialogWith16Radius.s = false;
            confirmCancelDialogWith16Radius.t(true);
            confirmCancelDialogWith16Radius.setCancelable(false);
            confirmCancelDialogWith16Radius.setOnShowListener(new DialogInterface.OnShowListener() { // from class: X.3on
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    AIDubbingReporter.f73057a.getClass();
                    AIDubbingReporter.c("show");
                }
            });
            this.x = confirmCancelDialogWith16Radius;
        }
        ConfirmCancelDialogWith16Radius confirmCancelDialogWith16Radius2 = this.x;
        if (confirmCancelDialogWith16Radius2 != null && !new HeliosApiHook().preInvoke(300000, "com/vega/ui/dialog/ConfirmCancelDialogWith16Radius", "show", confirmCancelDialogWith16Radius2, new Object[0], "void", new ExtraInfo(false, "()V", "dzBzEhQ/WMuSVEIlTB3KYsBm51CnhB00WPfr21uMDA2lbiHa/xc4hLoFYCqglQCTuQD5n3k5wA==")).isIntercept()) {
            confirmCancelDialogWith16Radius2.show();
        }
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onCommonEditFinish(CommonEditorFinishEvent commonEditorFinishEvent) {
        Intrinsics.checkNotNullParameter(commonEditorFinishEvent, "");
        ICommonEditorReport iCommonEditorReport = (ICommonEditorReport) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(ICommonEditorReport.class), null);
        AIDubbingUtil aIDubbingUtil = AIDubbingUtil.f72854a;
        AiWriterReportData aiWriterReportData = new AiWriterReportData(iCommonEditorReport.p(), iCommonEditorReport.e(), iCommonEditorReport.i(), iCommonEditorReport.o(), iCommonEditorReport.k(), iCommonEditorReport.l());
        aIDubbingUtil.getClass();
        AIDubbingUtil.f72856d = aiWriterReportData;
        e4(commonEditorFinishEvent.f87588a);
        c4().f72937c = true;
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "");
        View viewInflate = layoutInflater.inflate(R.layout._2v_res_0x7f0c0493, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "");
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override // com.vega.audio.dubbing.page.IAIDubbingPage
    public final void onHide() {
        AIDubbingEditTextView aIDubbingEditTextView = this.h;
        if (aIDubbingEditTextView != null) {
            aIDubbingEditTextView.clearFocus();
        }
        KeyboardUtils keyboardUtils = KeyboardUtils.f106639a;
        View view = getView();
        if (view == null) {
            return;
        }
        keyboardUtils.getClass();
        KeyboardUtils.b(view);
    }

    @Override // com.vega.audio.dubbing.page.IAIDubbingPage
    public final void onShow() {
        String value = a4().z.getValue();
        MutableLiveData<String> mutableLiveData = a4().z;
        if (value == null) {
            value = "";
        }
        LiveDataExtKt.o(mutableLiveData, value);
        AIDubbingEditTextView aIDubbingEditTextView = this.h;
        if (aIDubbingEditTextView != null) {
            aIDubbingEditTextView.clearFocus();
        }
        KeyboardUtils keyboardUtils = KeyboardUtils.f106639a;
        View view = getView();
        if (view == null) {
            return;
        }
        keyboardUtils.getClass();
        KeyboardUtils.b(view);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onStart() {
        super.onStart();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.addOnConfigurationChangedListener(this.w);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onStop() {
        super.onStop();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.removeOnConfigurationChangedListener(this.w);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x05ce  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x05ba  */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onViewCreated(android.view.View r20, android.os.Bundle r21) {
        /*
            r19 = this;
            java.lang.String r2 = ""
            r6 = r20
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r2)
            r0 = 2131296857(0x7f090259, float:1.8211643E38)
            android.view.View r0 = r6.findViewById(r0)
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            r12 = r19
            r12.e = r0
            r0 = 2131296898(0x7f090282, float:1.8211726E38)
            android.view.View r0 = r6.findViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            r12.f = r0
            r0 = 2131296866(0x7f090262, float:1.821166E38)
            android.view.View r0 = r6.findViewById(r0)
            r12.f73019g = r0
            r0 = 2131296896(0x7f090280, float:1.8211722E38)
            android.view.View r0 = r6.findViewById(r0)
            com.vega.audio.dubbing.core.view.AIDubbingEditTextView r0 = (com.vega.audio.dubbing.core.view.AIDubbingEditTextView) r0
            r12.h = r0
            r0 = 2131296890(0x7f09027a, float:1.821171E38)
            android.view.View r0 = r6.findViewById(r0)
            com.vega.ui.widget.TextInputSampleView r0 = (com.vega.ui.widget.TextInputSampleView) r0
            r12.i = r0
            r0 = 2131296871(0x7f090267, float:1.821167E38)
            android.view.View r0 = r6.findViewById(r0)
            r12.j = r0
            r0 = 2131296867(0x7f090263, float:1.8211663E38)
            android.view.View r0 = r6.findViewById(r0)
            r12.k = r0
            r8 = 2131296874(0x7f09026a, float:1.8211677E38)
            android.view.View r0 = r6.findViewById(r8)
            r12.l = r0
            r0 = 2131296869(0x7f090265, float:1.8211667E38)
            android.view.View r0 = r6.findViewById(r0)
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
            r12.n = r0
            r0 = 2131296884(0x7f090274, float:1.8211697E38)
            android.view.View r0 = r6.findViewById(r0)
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
            r12.o = r0
            r0 = 2131296882(0x7f090272, float:1.8211693E38)
            android.view.View r0 = r6.findViewById(r0)
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
            r12.m = r0
            r0 = 2131296872(0x7f090268, float:1.8211673E38)
            android.view.View r0 = r6.findViewById(r0)
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
            r12.p = r0
            r0 = 2131296880(0x7f090270, float:1.821169E38)
            android.view.View r0 = r6.findViewById(r0)
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
            r12.q = r0
            com.vega.audio.dubbing.preview.AIDubbingTextFunctionController r3 = new com.vega.audio.dubbing.preview.AIDubbingTextFunctionController
            r0 = 2131296892(0x7f09027c, float:1.8211714E38)
            android.view.View r1 = r6.findViewById(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            r7 = 2131296885(0x7f090275, float:1.82117E38)
            android.view.View r0 = r6.findViewById(r7)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            r3.<init>(r1, r0, r12)
            r12.u = r3
            com.vega.core.context.SPIService r4 = com.vega.core.context.SPIService.INSTANCE
            java.lang.Class<com.lemon.lv.editor.proxy.IAIDubbingDigtalHuman> r0 = com.lemon.lv.editor.proxy.IAIDubbingDigtalHuman.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            r3 = 0
            java.lang.Object r5 = r4.getImpl(r0, r3)
            com.lemon.lv.editor.proxy.IAIDubbingDigtalHuman r5 = (com.lemon.lv.editor.proxy.IAIDubbingDigtalHuman) r5
            r0 = 2131296862(0x7f09025e, float:1.8211653E38)
            android.view.View r1 = r6.findViewById(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            android.view.View r0 = r6.findViewById(r7)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            com.vega.edit.dubbing.AIDubbingDigtalHumanController r0 = r5.a(r1, r0, r12)
            r12.r = r0
            com.vega.audio.dubbing.model.AIDubbingViewModel r1 = r12.c4()
            com.lemon.lv.editor.proxy.IAIDubbingPreviewDigtalHumanController r0 = r12.r
            r1.W = r0
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController r11 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController
            com.lemon.lv.editor.proxy.IAIDubbingPreviewDigtalHumanController r13 = r12.r
            android.view.View r14 = r6.findViewById(r8)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r2)
            android.view.ViewGroup r14 = (android.view.ViewGroup) r14
            r0 = 2131296902(0x7f090286, float:1.8211734E38)
            android.view.View r15 = r6.findViewById(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r2)
            r0 = 2131296901(0x7f090285, float:1.8211732E38)
            android.view.View r5 = r6.findViewById(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r2)
            r0 = 2131296899(0x7f090283, float:1.8211728E38)
            android.view.View r1 = r6.findViewById(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            android.widget.FrameLayout r1 = (android.widget.FrameLayout) r1
            android.view.View r0 = r6.findViewById(r7)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            r17 = r1
            r18 = r0
            r16 = r5
            r11.<init>(r12, r13, r14, r15, r16, r17, r18)
            r12.t = r11
            com.vega.audio.dubbing.preview.AIDubbingPreviewExportController r5 = new com.vega.audio.dubbing.preview.AIDubbingPreviewExportController
            com.vega.audio.dubbing.model.AIDubbingViewModel r1 = r12.c4()
            com.lemon.lv.editor.proxy.IAIDubbingPreviewDigtalHumanController r0 = r12.r
            r5.<init>(r1, r12, r0)
            r12.s = r5
            com.vega.audio.dubbing.preview.AIDubbingPreviewHeightController r5 = new com.vega.audio.dubbing.preview.AIDubbingPreviewHeightController
            r0 = 2131296861(0x7f09025d, float:1.821165E38)
            android.view.View r1 = r6.findViewById(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r0 = 2131296893(0x7f09027d, float:1.8211716E38)
            android.view.View r0 = r6.findViewById(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            com.vega.audio.dubbing.view.AIDubbingEditorSuggestTool r0 = (com.vega.audio.dubbing.view.AIDubbingEditorSuggestTool) r0
            r5.<init>(r1, r0, r12)
            r12.v = r5
            org.greenrobot.eventbus.EventBus r0 = org.greenrobot.eventbus.EventBus.getDefault()
            r0.register(r12)
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController r6 = r12.t
            r0 = 600(0x258, double:2.964E-321)
            r5 = 1
            if (r6 == 0) goto L294
            com.vega.audio.tone.viewmodel.AudioToneSelectViewModel r8 = r6.d()
            com.vega.util.MaterialPanelLoadReporter r7 = new com.vega.util.MaterialPanelLoadReporter
            r7.<init>()
            r8.getClass()
            r8.f = r7
            com.vega.audio.tone.viewmodel.AudioToneSelectViewModel r7 = r6.d()
            com.vega.util.MaterialPanelLoadReporter r11 = r7.f
            com.vega.edit.base.utils.EditReportManager r7 = com.vega.edit.base.utils.EditReportManager.f88945a
            r7.getClass()
            java.lang.String r10 = com.vega.edit.base.utils.EditReportManager.f88947d
            r9 = 4
            java.lang.String r8 = "tone"
            java.lang.String r7 = "tts_component"
            com.vega.util.MaterialPanelLoadReporter.g(r11, r8, r7, r10, r9)
            com.vega.edit.base.utils.RecyclerViewExposeUtil r13 = r6.q
            androidx.recyclerview.widget.RecyclerView r14 = r6.n
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initEvent$1 r15 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initEvent$1
            r15.<init>()
            r16 = 0
            r9 = 0
            r18 = 28
            r17 = r9
            com.vega.edit.base.utils.RecyclerViewExposeUtil.e(r13, r14, r15, r16, r17, r18)
            android.view.ViewGroup r8 = r6.f73038c
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initEvent$2 r7 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initEvent$2
            r7.<init>()
            com.vega.ui.util.ViewUtilsKt.d(r8, r7)
            android.view.View r8 = r6.s
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initEvent$3 r7 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initEvent$3
            r7.<init>()
            com.vega.ui.util.ViewUtilsKt.d(r8, r7)
            androidx.recyclerview.widget.RecyclerView r10 = r6.n
            com.vega.ui.SmoothLinearLayoutManager r8 = new com.vega.ui.SmoothLinearLayoutManager
            android.content.Context r7 = r10.getContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r2)
            r8.<init>(r7)
            r10.setLayoutManager(r8)
            com.vega.ui.HeaderFooterAdapterWrapper r8 = new com.vega.ui.HeaderFooterAdapterWrapper
            kotlin.Lazy r7 = r6.I
            java.lang.Object r7 = r7.getValue()
            androidx.recyclerview.widget.RecyclerView$Adapter r7 = (androidx.recyclerview.widget.RecyclerView.Adapter) r7
            r8.<init>(r7)
            r10.setAdapter(r8)
            androidx.recyclerview.widget.RecyclerView r8 = r6.n
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initEvent$5 r7 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initEvent$5
            r7.<init>()
            r8.addItemDecoration(r7)
            com.vega.audio.tone.viewmodel.AudioToneSelectViewModel r7 = r6.d()
            androidx.lifecycle.LiveData r11 = r7.c7()
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment r10 = r6.f73037a
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initEvent$6 r8 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initEvent$6
            r8.<init>()
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$sam$androidx_lifecycle_Observer$0 r7 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$sam$androidx_lifecycle_Observer$0
            r7.<init>(r8)
            r11.observe(r10, r7)
            com.vega.audio.dubbing.model.AIDubbingViewModel r7 = r6.e()
            com.vega.audio.dubbing.model.AIDubbingEnterParameter r7 = r7.f72938d
            if (r7 == 0) goto L675
            java.lang.String r8 = r7.e
        L1ea:
            java.util.ArrayList<com.ss.android.ugc.effectmanager.effect.model.Effect> r7 = r6.B
            r7.clear()
            if (r8 == 0) goto L1f7
            int r7 = r8.length()
            if (r7 != 0) goto L65c
        L1f7:
            com.vega.audio.tone.viewmodel.AudioToneSelectViewModel r10 = r6.d()
            com.vega.effectplatform.loki.EffectPanel r8 = com.vega.effectplatform.loki.EffectPanel.B
            r7 = 6
            com.vega.edit.base.view.BaseTabViewModel.E6(r10, r8, r9, r9, r7)
        L201:
            android.view.View r8 = r6.e
            r7 = 1065353216(0x3f800000, float:1.0)
            r8.setAlpha(r7)
            android.view.View r7 = r6.f73039d
            com.vega.infrastructure.extensions.ViewExtKt.c(r7)
            android.view.View r7 = r6.f73039d
            r7.setOnClickListener(r3)
            com.vega.audio.tone.viewmodel.AudioToneSelectViewModel r8 = r6.d()
            r7 = 11
            r8.e0 = r7
            android.view.View r8 = r6.k
            if (r8 == 0) goto L226
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initEvent$8 r7 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initEvent$8
            r7.<init>()
            com.vega.ui.util.ViewUtilsKt.c(r8, r0, r7)
        L226:
            android.widget.TextView r9 = r6.j
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r7 = 2131896260(0x7f1227c4, float:1.9427376E38)
            java.lang.String r7 = com.vega.core.utils.FunctionsKt.b(r7)
            r8.append(r7)
            r7 = 2131896262(0x7f1227c6, float:1.942738E38)
            java.lang.String r7 = com.vega.core.utils.FunctionsKt.b(r7)
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            r9.setText(r7)
            android.view.View r8 = r6.i
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initEvent$9 r7 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initEvent$9
            r7.<init>()
            com.vega.ui.util.ViewUtilsKt.d(r8, r7)
            com.vega.audio.dubbing.AIDubbingUtil r8 = com.vega.audio.dubbing.AIDubbingUtil.f72854a
            com.vega.audio.dubbing.model.AIDubbingViewModel r7 = r6.e()
            r8.getClass()
            boolean r7 = com.vega.audio.dubbing.AIDubbingUtil.i(r7)
            if (r7 == 0) goto L632
            android.view.View r7 = r6.i
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r2)
            com.vega.infrastructure.extensions.ViewExtKt.e(r7)
            com.vega.theme.VegaCheckBox r7 = r6.u
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r2)
            com.vega.infrastructure.extensions.ViewExtKt.e(r7)
            com.vega.theme.VegaCheckBox r7 = r6.u
            r7.setChecked(r5)
            com.vega.theme.VegaCheckBox r8 = r6.u
            X.3ok r7 = new X.3ok
            r7.<init>()
            r8.setOnCheckedChangeListener(r7)
        L280:
            com.vega.core.privacy.fbv.geoblock.GeoBlockManager r7 = com.vega.core.privacy.fbv.geoblock.GeoBlockManager.f79427a
            r7.getClass()
            java.lang.String r7 = "bpea-fbv_voice_editor_audio_ai_voiceover_voice"
            com.vega.core.privacy.fbv.geoblock.GeoBlockManager$GeoBlockResult r7 = com.vega.core.privacy.fbv.geoblock.GeoBlockManager.a(r7)
            boolean r7 = r7.b
            if (r7 == 0) goto L294
            android.view.View r6 = r6.e
            com.vega.infrastructure.extensions.ViewExtKt.b(r6)
        L294:
            com.vega.audio.dubbing.preview.AIDubbingTextFunctionController r8 = r12.u
            if (r8 == 0) goto L2dc
            java.lang.Class<com.vega.commonedit.config.AIWriterIntegratesDSConfigSetting> r6 = com.vega.commonedit.config.AIWriterIntegratesDSConfigSetting.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            com.vega.config.IConfig r7 = com.vega.config.ConfigSettingsKt.a(r6)
            com.vega.commonedit.config.AIWriterIntegratesDSConfig r7 = (com.vega.commonedit.config.AIWriterIntegratesDSConfig) r7
            java.lang.String r6 = "ai_dubbing"
            boolean r7 = r7.b(r6)
            java.lang.Class<com.lemon.lv.config.ClientSetting> r6 = com.lemon.lv.config.ClientSetting.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            java.lang.Object r6 = r4.getImpl(r6, r3)
            com.lemon.lv.config.BaseClientSetting r6 = (com.lemon.lv.config.BaseClientSetting) r6
            com.lemon.lv.config.AIWriterConfig r6 = r6.y()
            boolean r6 = r6.b()
            if (r7 == 0) goto L610
            android.view.View r6 = r8.e
            com.vega.infrastructure.extensions.ViewExtKt.e(r6)
            android.widget.ImageView r6 = r8.f
            com.vega.infrastructure.extensions.ViewExtKt.e(r6)
            android.widget.ImageView r7 = r8.f
            r6 = 2131237620(0x7f081af4, float:1.8091496E38)
            r7.setImageResource(r6)
        L2d2:
            android.view.View r7 = r8.e
            com.vega.audio.dubbing.preview.AIDubbingTextFunctionController$initEvent$1 r6 = new com.vega.audio.dubbing.preview.AIDubbingTextFunctionController$initEvent$1
            r6.<init>()
            com.vega.ui.util.ViewUtilsKt.d(r7, r6)
        L2dc:
            com.vega.audio.dubbing.preview.AIDubbingPreviewExportController r10 = r12.s
            if (r10 == 0) goto L306
            com.vega.audio.dubbing.model.AIDubbingViewModel r6 = r10.f73003a
            androidx.lifecycle.MutableLiveData r9 = r6.D
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment r8 = r10.b
            com.vega.audio.dubbing.preview.AIDubbingPreviewExportController$initEvent$1 r7 = new com.vega.audio.dubbing.preview.AIDubbingPreviewExportController$initEvent$1
            r7.<init>()
            com.vega.audio.dubbing.preview.AIDubbingPreviewExportController$sam$androidx_lifecycle_Observer$0 r6 = new com.vega.audio.dubbing.preview.AIDubbingPreviewExportController$sam$androidx_lifecycle_Observer$0
            r6.<init>(r7)
            r9.observe(r8, r6)
            com.vega.audio.dubbing.model.AIDubbingViewModel r6 = r10.f73003a
            androidx.lifecycle.MutableLiveData r9 = r6.c0
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment r8 = r10.b
            com.vega.audio.dubbing.preview.AIDubbingPreviewExportController$initEvent$2 r7 = new com.vega.audio.dubbing.preview.AIDubbingPreviewExportController$initEvent$2
            r7.<init>()
            com.vega.audio.dubbing.preview.AIDubbingPreviewExportController$sam$androidx_lifecycle_Observer$0 r6 = new com.vega.audio.dubbing.preview.AIDubbingPreviewExportController$sam$androidx_lifecycle_Observer$0
            r6.<init>(r7)
            r9.observe(r8, r6)
        L306:
            com.lemon.lv.editor.proxy.IAIDubbingPreviewDigtalHumanController r6 = r12.r
            if (r6 == 0) goto L30d
            r6.b()
        L30d:
            android.widget.TextView r7 = r12.f
            if (r7 == 0) goto L319
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$1 r6 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$1
            r6.<init>()
            com.vega.ui.util.ViewUtilsKt.c(r7, r0, r6)
        L319:
            android.view.View r7 = r12.f73019g
            if (r7 == 0) goto L325
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$2 r6 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$2
            r6.<init>()
            com.vega.ui.util.ViewUtilsKt.c(r7, r0, r6)
        L325:
            android.widget.ImageView r8 = r12.e
            if (r8 == 0) goto L34b
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$3$1 r6 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$3$1
            r6.<init>()
            com.vega.ui.util.ViewUtilsKt.c(r8, r0, r6)
            com.vega.audio.dubbing.AIDubbingUtil r7 = com.vega.audio.dubbing.AIDubbingUtil.f72854a
            com.vega.audio.dubbing.model.AIDubbingViewModel r6 = r12.c4()
            r7.getClass()
            boolean r6 = com.vega.audio.dubbing.AIDubbingUtil.i(r6)
            if (r6 == 0) goto L603
            com.vega.ui.util.IconDiffUtil r6 = com.vega.ui.util.IconDiffUtil.f134344a
            r6.getClass()
            r6 = 2131241439(0x7f0829df, float:1.8099241E38)
            r8.setImageResource(r6)
        L34b:
            android.widget.LinearLayout r7 = r12.n
            if (r7 == 0) goto L357
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$4 r6 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$4
            r6.<init>()
            com.vega.ui.util.ViewUtilsKt.c(r7, r0, r6)
        L357:
            android.widget.LinearLayout r7 = r12.m
            if (r7 == 0) goto L363
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$5 r6 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$5
            r6.<init>()
            com.vega.ui.util.ViewUtilsKt.c(r7, r0, r6)
        L363:
            android.widget.LinearLayout r7 = r12.o
            if (r7 == 0) goto L36f
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$6 r6 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$6
            r6.<init>()
            com.vega.ui.util.ViewUtilsKt.c(r7, r0, r6)
        L36f:
            android.widget.LinearLayout r7 = r12.p
            if (r7 == 0) goto L37b
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$7 r6 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$7
            r6.<init>()
            com.vega.ui.util.ViewUtilsKt.c(r7, r0, r6)
        L37b:
            android.widget.LinearLayout r7 = r12.q
            if (r7 == 0) goto L387
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$8 r6 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$8
            r6.<init>()
            com.vega.ui.util.ViewUtilsKt.c(r7, r0, r6)
        L387:
            com.vega.audio.dubbing.core.view.AIDubbingEditTextView r6 = r12.h
            if (r6 == 0) goto L399
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$9 r1 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$9
            r1.<init>()
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$10 r0 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$10
            r0.<init>()
            r6.f72910c = r1
            r6.f72911d = r0
        L399:
            com.vega.audio.dubbing.core.view.AIDubbingEditTextView r6 = r12.h
            if (r6 == 0) goto L3af
            android.view.ViewParent r0 = r6.getParent()
            android.widget.ScrollView r1 = com.vega.audio.dubbing.core.view.AIDubbingEditTextView.m(r0)
            if (r1 == 0) goto L3af
            X.3SI r0 = new X.3SI
            r0.<init>()
            r6.setOnTouchListener(r0)
        L3af:
            com.vega.audio.dubbing.AIDubbingUtil r1 = com.vega.audio.dubbing.AIDubbingUtil.f72854a
            com.vega.audio.dubbing.model.AIDubbingViewModel r0 = r12.c4()
            r1.getClass()
            boolean r0 = com.vega.audio.dubbing.AIDubbingUtil.i(r0)
            if (r0 == 0) goto L5ea
            android.view.View r0 = r12.f73019g
            if (r0 == 0) goto L3c5
            com.vega.infrastructure.extensions.ViewExtKt.c(r0)
        L3c5:
            android.widget.TextView r1 = r12.f
            if (r1 != 0) goto L5de
        L3c9:
            com.vega.ui.widget.TextInputSampleView r6 = r12.i
            if (r6 == 0) goto L5b1
            com.vega.audio.dubbing.core.view.AIDubbingEditTextView r1 = r12.h
            if (r1 != 0) goto L5a9
        L3d1:
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController r7 = r12.t
            if (r7 == 0) goto L48d
            com.vega.audio.dubbing.model.AIDubbingViewModel r0 = r7.e()
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r8 = r0.T
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment r0 = r7.f73037a
            androidx.lifecycle.LifecycleOwner r6 = r0.getViewLifecycleOwner()
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initObserver$1 r1 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initObserver$1
            r1.<init>()
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$sam$androidx_lifecycle_Observer$0 r0 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$sam$androidx_lifecycle_Observer$0
            r0.<init>(r1)
            r8.observe(r6, r0)
            com.vega.audio.tone.viewmodel.AudioToneSelectViewModel r0 = r7.d()
            androidx.lifecycle.LiveData<java.lang.Boolean> r8 = r0.I
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment r0 = r7.f73037a
            androidx.lifecycle.LifecycleOwner r6 = r0.getViewLifecycleOwner()
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initObserver$2 r1 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initObserver$2
            r1.<init>()
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$sam$androidx_lifecycle_Observer$0 r0 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$sam$androidx_lifecycle_Observer$0
            r0.<init>(r1)
            r8.observe(r6, r0)
            com.vega.audio.dubbing.preview.AIDubbingToneSpeedAdjustPanelHelper r1 = r7.c()
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initObserver$3 r0 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initObserver$3
            r0.<init>()
            r1.o = r0
            com.vega.audio.dubbing.model.AIDubbingViewModel r0 = r7.e()
            androidx.lifecycle.MutableLiveData r8 = r0.v
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment r0 = r7.f73037a
            androidx.lifecycle.LifecycleOwner r6 = r0.getViewLifecycleOwner()
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initObserver$4 r1 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initObserver$4
            r1.<init>()
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$sam$androidx_lifecycle_Observer$0 r0 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$sam$androidx_lifecycle_Observer$0
            r0.<init>(r1)
            r8.observe(r6, r0)
            com.vega.audio.dubbing.model.AIDubbingViewModel r0 = r7.e()
            com.vega.core.viewmodel.LiveEvent<kotlin.Pair<com.vega.audio.dubbing.model.AIDubbingPageState, com.vega.audio.dubbing.model.AIDubbingReadingBundle>> r6 = r0.A
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment r0 = r7.f73037a
            androidx.lifecycle.LifecycleOwner r1 = r0.getViewLifecycleOwner()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initObserver$5 r0 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initObserver$5
            r0.<init>()
            r6.observe(r1, r0)
            com.vega.audio.dubbing.model.AIDubbingViewModel r0 = r7.e()
            com.vega.core.viewmodel.LiveEvent<com.lemon.lv.data.ToneType> r8 = r0.f72935J
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment r0 = r7.f73037a
            androidx.lifecycle.LifecycleOwner r6 = r0.getViewLifecycleOwner()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r2)
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initObserver$6 r1 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initObserver$6
            r1.<init>()
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$sam$androidx_lifecycle_Observer$0 r0 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$sam$androidx_lifecycle_Observer$0
            r0.<init>(r1)
            r8.observe(r6, r0)
            com.vega.audio.dubbing.model.AIDubbingViewModel r0 = r7.e()
            androidx.lifecycle.MutableLiveData r6 = r0.a0
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment r0 = r7.f73037a
            androidx.lifecycle.LifecycleOwner r2 = r0.getViewLifecycleOwner()
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initObserver$7 r1 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initObserver$7
            r1.<init>()
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$sam$androidx_lifecycle_Observer$0 r0 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$sam$androidx_lifecycle_Observer$0
            r0.<init>(r1)
            r6.observe(r2, r0)
            com.vega.audio.dubbing.model.AIDubbingViewModel r2 = r7.e()
            com.vega.audio.dubbing.model.AIDubbingViewModel r0 = r7.e()
            com.vega.audio.dubbing.model.AIDubbingEnterParameter r0 = r0.f72938d
            if (r0 == 0) goto L5a6
            java.lang.String r1 = r0.f72920d
        L485:
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initObserver$8 r0 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initObserver$8
            r0.<init>()
            r2.D6(r1, r0)
        L48d:
            com.vega.audio.dubbing.model.AIDubbingViewModel r0 = r12.c4()
            com.vega.core.utils.CombinedLiveData r6 = r0.i
            androidx.lifecycle.LifecycleOwner r2 = r12.getViewLifecycleOwner()
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initObserver$1 r1 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initObserver$1
            r1.<init>()
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$sam$androidx_lifecycle_Observer$0 r0 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$sam$androidx_lifecycle_Observer$0
            r0.<init>(r1)
            r6.observe(r2, r0)
            com.vega.audio.dubbing.model.AIDubbingViewModel r0 = r12.c4()
            androidx.lifecycle.MutableLiveData r6 = r0.Y
            androidx.lifecycle.LifecycleOwner r2 = r12.getViewLifecycleOwner()
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initObserver$2 r1 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initObserver$2
            r1.<init>()
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$sam$androidx_lifecycle_Observer$0 r0 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$sam$androidx_lifecycle_Observer$0
            r0.<init>(r1)
            r6.observe(r2, r0)
            com.vega.audio.dubbing.model.AIDubbingViewModel r0 = r12.c4()
            androidx.lifecycle.MutableLiveData<com.lemon.lv.data.ToneType> r6 = r0.S
            androidx.lifecycle.LifecycleOwner r2 = r12.getViewLifecycleOwner()
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initObserver$3 r1 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initObserver$3
            r1.<init>()
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$sam$androidx_lifecycle_Observer$0 r0 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$sam$androidx_lifecycle_Observer$0
            r0.<init>(r1)
            r6.observe(r2, r0)
            com.vega.audio.dubbing.model.AIDubbingViewModel r0 = r12.c4()
            androidx.lifecycle.MutableLiveData r6 = r0.a0
            androidx.lifecycle.LifecycleOwner r2 = r12.getViewLifecycleOwner()
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initObserver$4 r1 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initObserver$4
            r1.<init>()
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$sam$androidx_lifecycle_Observer$0 r0 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$sam$androidx_lifecycle_Observer$0
            r0.<init>(r1)
            r6.observe(r2, r0)
            com.vega.audio.dubbing.model.AIDubbingViewModel r0 = r12.c4()
            com.vega.core.utils.CombinedLiveData r6 = r0.I
            androidx.lifecycle.LifecycleOwner r2 = r12.getViewLifecycleOwner()
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initObserver$5 r1 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initObserver$5
            r1.<init>()
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$sam$androidx_lifecycle_Observer$0 r0 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$sam$androidx_lifecycle_Observer$0
            r0.<init>(r1)
            r6.observe(r2, r0)
            com.vega.audio.dubbing.preview.AIDubbingPreviewHeightController r2 = r12.v
            if (r2 == 0) goto L5a5
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            r6.add(r0)
            r0 = 2
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6.add(r0)
            r0 = 3
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6.add(r0)
            java.lang.Class<com.lemon.lv.config.ClientSetting> r0 = com.lemon.lv.config.ClientSetting.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            java.lang.Object r0 = r4.getImpl(r0, r3)
            com.lemon.lv.config.BaseClientSetting r0 = (com.lemon.lv.config.BaseClientSetting) r0
            com.lemon.lv.config.CommonEditorConfig r0 = r0.getCommonEditorConfig()
            java.util.List r1 = r0.getTranslateDisableScenes()
            java.lang.String r0 = "13"
            boolean r0 = r1.contains(r0)
            if (r0 != 0) goto L544
            r0 = 5
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6.add(r0)
        L544:
            com.vega.audio.dubbing.view.AIDubbingEditorSuggestTool r0 = r2.b
            r0.setSelectedToolFunc(r6)
            com.vega.audio.dubbing.view.AIDubbingEditorSuggestTool r1 = r2.b
            r0 = 2131298603(0x7f09092b, float:1.8215184E38)
            android.view.View r1 = r1.findViewById(r0)
            if (r1 == 0) goto L55c
            X.3mG r0 = new X.3mG
            r0.<init>()
            r1.setOnClickListener(r0)
        L55c:
            com.vega.audio.dubbing.view.AIDubbingEditorSuggestTool r4 = r2.b
            com.vega.audio.dubbing.preview.AIDubbingPreviewHeightController$initSmartTool$2 r3 = new com.vega.audio.dubbing.preview.AIDubbingPreviewHeightController$initSmartTool$2
            r3.<init>()
            r4.getClass()
            android.view.View r1 = r4.f73071a
            X.1eR r0 = new X.1eR
            r0.<init>()
            r1.setOnClickListener(r0)
            android.view.View r1 = r4.b
            X.1eS r0 = new X.1eS
            r0.<init>()
            r1.setOnClickListener(r0)
            android.view.View r1 = r4.f73072c
            X.1eT r0 = new X.1eT
            r0.<init>()
            r1.setOnClickListener(r0)
            android.view.View r1 = r4.f73073d
            X.1eU r0 = new X.1eU
            r0.<init>()
            r1.setOnClickListener(r0)
            android.view.View r1 = r4.e
            X.1eV r0 = new X.1eV
            r0.<init>()
            r1.setOnClickListener(r0)
            android.view.View r1 = r4.f
            X.1eW r0 = new X.1eW
            r0.<init>()
            r1.setOnClickListener(r0)
            r2.a()
        L5a5:
            return
        L5a6:
            r1 = r3
            goto L485
        L5a9:
            com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$11 r0 = new com.vega.audio.dubbing.preview.AIDubbingPreviewFragment$initEvent$11
            r0.<init>()
            r6.i(r1, r0)
        L5b1:
            r12.c4()
            boolean r0 = com.vega.audio.dubbing.AIDubbingUtil.u()
            if (r0 != 0) goto L5ce
            android.widget.LinearLayout r0 = r12.p
            if (r0 == 0) goto L5c1
            com.vega.infrastructure.extensions.ViewExtKt.b(r0)
        L5c1:
            r12.p = r3
            android.widget.LinearLayout r0 = r12.q
            if (r0 == 0) goto L5ca
            com.vega.infrastructure.extensions.ViewExtKt.b(r0)
        L5ca:
            r12.q = r3
            goto L3d1
        L5ce:
            android.widget.LinearLayout r0 = r12.p
            if (r0 == 0) goto L5d5
            com.vega.infrastructure.extensions.ViewExtKt.e(r0)
        L5d5:
            android.widget.LinearLayout r0 = r12.q
            if (r0 == 0) goto L3d1
            com.vega.infrastructure.extensions.ViewExtKt.e(r0)
            goto L3d1
        L5de:
            r0 = 2131886477(0x7f12018d, float:1.9407534E38)
            java.lang.String r0 = com.vega.core.utils.FunctionsKt.b(r0)
            r1.setText(r0)
            goto L3c9
        L5ea:
            android.view.View r0 = r12.f73019g
            if (r0 == 0) goto L5f1
            com.vega.infrastructure.extensions.ViewExtKt.e(r0)
        L5f1:
            android.widget.TextView r1 = r12.f
            if (r1 != 0) goto L5f7
            goto L3c9
        L5f7:
            r0 = 2131886499(0x7f1201a3, float:1.9407579E38)
            java.lang.String r0 = com.vega.core.utils.FunctionsKt.b(r0)
            r1.setText(r0)
            goto L3c9
        L603:
            com.vega.ui.util.IconDiffUtil r6 = com.vega.ui.util.IconDiffUtil.f134344a
            r6.getClass()
            r6 = 2131236808(0x7f0817c8, float:1.8089849E38)
            r8.setImageResource(r6)
            goto L34b
        L610:
            if (r6 == 0) goto L626
            android.view.View r6 = r8.e
            com.vega.infrastructure.extensions.ViewExtKt.e(r6)
            android.widget.ImageView r6 = r8.f
            com.vega.infrastructure.extensions.ViewExtKt.e(r6)
            android.widget.ImageView r7 = r8.f
            r6 = 2131231699(0x7f0803d3, float:1.8079486E38)
            r7.setImageResource(r6)
            goto L2d2
        L626:
            android.view.View r6 = r8.e
            com.vega.infrastructure.extensions.ViewExtKt.b(r6)
            android.widget.ImageView r6 = r8.f
            com.vega.infrastructure.extensions.ViewExtKt.b(r6)
            goto L2d2
        L632:
            android.view.View r7 = r6.i
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r2)
            com.vega.infrastructure.extensions.ViewExtKt.b(r7)
            android.view.View r7 = r6.t
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r2)
            com.vega.infrastructure.extensions.ViewExtKt.e(r7)
            com.vega.theme.VegaCheckBox r7 = r6.u
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r2)
            com.vega.infrastructure.extensions.ViewExtKt.b(r7)
            android.view.View r7 = r6.m
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r2)
            com.vega.infrastructure.extensions.ViewExtKt.e(r7)
            android.view.View r7 = r6.s
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r2)
            com.vega.infrastructure.extensions.ViewExtKt.e(r7)
            goto L280
        L65c:
            com.vega.audio.dubbing.model.AIDubbingViewModel r13 = r6.e()
            kotlinx.coroutines.CoroutineDispatcher r14 = kotlinx.coroutines.Dispatchers.getIO()
            com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initEvent$7 r7 = new com.vega.audio.dubbing.preview.AIDubbingPreviewToneController$initEvent$7
            r7.<init>(r8, r6, r3)
            r17 = 2
            r15 = r3
            r16 = r7
            r18 = r3
            kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r13, r14, r15, r16, r17, r18)
            goto L201
        L675:
            r8 = r3
            goto L1ea
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.dubbing.preview.AIDubbingPreviewFragment.onViewCreated(android.view.View, android.os.Bundle):void");
    }
}