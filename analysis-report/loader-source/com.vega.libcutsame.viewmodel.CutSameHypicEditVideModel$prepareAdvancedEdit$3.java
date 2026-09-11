package com.vega.libcutsame.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import com.bytedance.security.android.aopcheck.PolarisFileWrapper;
import com.vega.core.ext.FileProviderUtilsKt;
import com.vega.cutsameedit.biz.edit.music.DialogShowHelper;
import com.vega.infrastructure.base.ModuleCommon;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(c = "com.vega.libcutsame.viewmodel.CutSameHypicEditVideModel$prepareAdvancedEdit$3", f = "CutSameHypicEditVideModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
public final class CutSameHypicEditVideModel$prepareAdvancedEdit$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ int q;
    public final /* synthetic */ CutSameHypicEditVideModel r;
    public final /* synthetic */ String s;
    public final /* synthetic */ String t;
    public final /* synthetic */ Activity u;
    public final /* synthetic */ Function1<Intent, Unit> v;

    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: kotlin.jvm.functions.Function1<? super android.content.Intent, kotlin.Unit> */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CutSameHypicEditVideModel$prepareAdvancedEdit$3(int i, CutSameHypicEditVideModel cutSameHypicEditVideModel, String str, String str2, Activity activity, Function1<? super Intent, Unit> function1, Continuation<? super CutSameHypicEditVideModel$prepareAdvancedEdit$3> continuation) {
        super(2, continuation);
        this.q = i;
        this.r = cutSameHypicEditVideModel;
        this.s = str;
        this.t = str2;
        this.u = activity;
        this.v = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CutSameHypicEditVideModel$prepareAdvancedEdit$3(this.q, this.r, this.s, this.t, this.u, this.v, continuation);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        if (this.q >= 11000) {
            CutSameHypicEditVideModel cutSameHypicEditVideModel = this.r;
            ModuleCommon.INSTANCE.getApplication();
            String str = this.s;
            cutSameHypicEditVideModel.getClass();
            final String string = FileProviderUtilsKt.b(new PolarisFileWrapper(str)).toString();
            Intrinsics.checkNotNullExpressionValue(string, "");
            final String string2 = Uri.parse(this.t).buildUpon().appendQueryParameter("picture_path", string).appendQueryParameter("app_name", "CapCut").appendQueryParameter("entry", "capcut").appendQueryParameter("source", "videocut_same").appendQueryParameter("add_activation", "false").build().toString();
            Intrinsics.checkNotNullExpressionValue(string2, "");
            DialogShowHelper.f80676a.getClass();
            if (((SharedPreferences) DialogShowHelper.b.getValue()).getBoolean("key_cut_same_goto_hypic", true)) {
                final CutSameHypicEditVideModel cutSameHypicEditVideModel2 = this.r;
                final Activity activity = this.u;
                final Function1<Intent, Unit> function1 = this.v;
                cutSameHypicEditVideModel2.n6(0, 1, activity, new Function0<Unit>() { // from class: com.vega.libcutsame.viewmodel.CutSameHypicEditVideModel$prepareAdvancedEdit$3.1
                    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: kotlin.jvm.functions.Function1<? super android.content.Intent, kotlin.Unit> */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function0
                    public final Unit invoke() {
                        CutSameHypicEditVideModel cutSameHypicEditVideModel3 = cutSameHypicEditVideModel2;
                        Activity activity2 = activity;
                        Pair pair = TuplesKt.to(string2, string);
                        Function1<Intent, Unit> function12 = function1;
                        cutSameHypicEditVideModel3.getClass();
                        CutSameHypicEditVideModel.k6(activity2, pair, function12);
                        cutSameHypicEditVideModel2.getClass();
                        CutSameHypicEditVideModel.m6(1, "launch_retouch");
                        DialogShowHelper.f80676a.getClass();
                        ((SharedPreferences) DialogShowHelper.b.getValue()).edit().putBoolean("key_cut_same_goto_hypic", false).apply();
                        return Unit.INSTANCE;
                    }
                });
            } else {
                CutSameHypicEditVideModel cutSameHypicEditVideModel3 = this.r;
                Activity activity2 = this.u;
                Pair pair = TuplesKt.to(string2, string);
                Function1<Intent, Unit> function12 = this.v;
                cutSameHypicEditVideModel3.getClass();
                CutSameHypicEditVideModel.k6(activity2, pair, function12);
            }
        } else {
            final CutSameHypicEditVideModel cutSameHypicEditVideModel4 = this.r;
            final Activity activity3 = this.u;
            cutSameHypicEditVideModel4.n6(1, 2, activity3, new Function0<Unit>() { // from class: com.vega.libcutsame.viewmodel.CutSameHypicEditVideModel$prepareAdvancedEdit$3.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    cutSameHypicEditVideModel4.getClass();
                    CutSameHypicEditVideModel.m6(2, "update_retouch");
                    CutSameHypicEditVideModel cutSameHypicEditVideModel5 = cutSameHypicEditVideModel4;
                    Activity activity4 = activity3;
                    Pair pair2 = TuplesKt.to("https://hypic.onelink.me/yzyp/cusqdj20?deep_link_value=retouchoversea://picture_edit/edit", null);
                    cutSameHypicEditVideModel5.getClass();
                    CutSameHypicEditVideModel.k6(activity4, pair2, null);
                    return Unit.INSTANCE;
                }
            });
        }
        return Unit.INSTANCE;
    }
}