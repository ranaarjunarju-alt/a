package com.vega.feedx.message.ui;

import android.content.Intent;
import android.os.SystemClock;
import com.bytedance.helios.statichook.api.ExtraInfo;
import com.bytedance.helios.statichook.api.HeliosApiHook;
import com.vega.feedx.base.ui.BaseContentFragment;
import com.vega.feedx.message.OnMessageClickListener;
import com.vega.report.ReportManagerWrapper;
import java.util.HashMap;

/* loaded from: classes24.dex */
public abstract class BaseMessageFragment extends BaseContentFragment implements OnMessageClickListener {
    public long E;

    /* loaded from: classes7.dex */
    public static final class Companion {
    }

    /* loaded from: classes21.dex */
    public /* synthetic */ class WhenMappings {
        static {
            OnMessageClickListener.PageType.values();
        }
    }

    static {
        new Companion();
    }

    public static void G4(BaseMessageFragment baseMessageFragment, Intent intent) {
        HeliosApiHook heliosApiHook = new HeliosApiHook();
        Object[] objArr = {intent, 10000};
        ExtraInfo extraInfo = new ExtraInfo(false, "(Landroid/content/Intent;I)V", "dzBzEhQ/WMuSU1IkQQrKaw68bpiUydwU6SKMSiwCPT9tT5O/BEwjyS7pkx+3");
        if (heliosApiHook.preInvoke(11087, "com/vega/feedx/message/ui/BaseMessageFragment", "startActivityForResult", baseMessageFragment, objArr, "void", extraInfo).isIntercept()) {
            heliosApiHook.postInvoke(null, 11087, "com/vega/feedx/message/ui/BaseMessageFragment", "startActivityForResult", baseMessageFragment, objArr, extraInfo, false);
        } else {
            baseMessageFragment.startActivityForResult(intent, 10000);
            heliosApiHook.postInvoke(null, 11087, "com/vega/feedx/message/ui/BaseMessageFragment", "startActivityForResult", baseMessageFragment, objArr, extraInfo, true);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01f1  */
    @Override // com.vega.feedx.message.OnMessageClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g1(com.vega.feedx.message.OnMessageClickListener.PageType r16, java.util.Map<java.lang.String, ? extends java.lang.Object> r17, com.vega.feedx.message.MessageData r18) throws org.json.JSONException {
        /*
            r15 = this;
            java.lang.String r0 = ""
            r2 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            com.vega.infrastructure.util.FastDoubleClickUtil r1 = com.vega.infrastructure.util.FastDoubleClickUtil.f106628a
            boolean r1 = com.vega.infrastructure.util.FastDoubleClickUtil.b(r1)
            if (r1 == 0) goto L10
            return
        L10:
            int r12 = r2.ordinal()
            r5 = 0
            java.lang.String r8 = "single_msg"
            java.lang.String r10 = "user"
            r13 = -1
            java.lang.String r9 = "enter_from"
            java.lang.String r11 = "id"
            java.lang.String r6 = "template_id"
            java.lang.String r4 = "page_enter_from"
            r3 = 10000(0x2710, float:1.4013E-41)
            r7 = r17
            if (r12 == 0) goto L2e8
            r1 = 1
            if (r12 == r1) goto L29f
            r8 = 3
            r2 = 2
            if (r12 == r2) goto L14d
            java.lang.String r6 = "android.intent.action.VIEW"
            if (r12 == r8) goto L74
            r0 = 4
            if (r12 == r0) goto L38
        L37:
            return
        L38:
            if (r7 == 0) goto L37
            java.lang.String r0 = "deeplink"
            java.lang.Object r0 = r7.get(r0)
            if (r0 == 0) goto L37
            java.lang.String r0 = r0.toString()
            if (r0 == 0) goto L37
            android.net.Uri r0 = android.net.Uri.parse(r0)
            android.net.Uri$Builder r1 = r0.buildUpon()
            java.lang.String r0 = "msg_list"
            android.net.Uri$Builder r0 = r1.appendQueryParameter(r9, r0)
            android.net.Uri r0 = r0.build()
            android.content.Intent r1 = new android.content.Intent
            r1.<init>(r6, r0)
            java.lang.Object r0 = r7.get(r4)
            if (r0 == 0) goto L6b
            java.lang.String r0 = r0.toString()
            if (r0 != 0) goto L6d
        L6b:
            java.lang.String r0 = "other"
        L6d:
            r1.putExtra(r4, r0)
            G4(r15, r1)
            goto L37
        L74:
            java.lang.String r9 = "web_url"
            if (r7 == 0) goto L84
            java.lang.Object r2 = r7.get(r9)
            if (r2 == 0) goto L84
            java.lang.String r4 = r2.toString()
            if (r4 != 0) goto L85
        L84:
            r4 = r0
        L85:
            android.net.Uri r2 = android.net.Uri.parse(r4)
            if (r2 == 0) goto Lf0
            java.lang.String r8 = r2.getScheme()
            if (r8 == 0) goto Lf0
            java.lang.String r2 = "http"
            boolean r2 = X.C93472yG.S(r8, r2)
            if (r2 != r1) goto Lf0
            androidx.fragment.app.FragmentActivity r2 = r15.getActivity()
            java.lang.String r1 = "//main/web"
            com.bytedance.router.SmartRoute r5 = com.bytedance.router.SmartRouter.buildRoute(r2, r1)
            r5.withParam(r9, r4)
            java.lang.String r2 = "web_color_src"
            r1 = 2131100012(0x7f06016c, float:1.7812393E38)
            r5.withParam(r2, r1)
            java.lang.String r2 = "source"
            if (r7 == 0) goto Lbe
            java.lang.Object r1 = r7.get(r2)
            if (r1 == 0) goto Lbe
            java.lang.String r1 = r1.toString()
            if (r1 != 0) goto Lbf
        Lbe:
            r1 = r0
        Lbf:
            r5.withParam(r2, r1)
            java.lang.String r2 = "activity_id"
            if (r7 == 0) goto Ld2
            java.lang.Object r1 = r7.get(r2)
            if (r1 == 0) goto Ld2
            java.lang.String r1 = r1.toString()
            if (r1 != 0) goto Ld3
        Ld2:
            r1 = r0
        Ld3:
            r5.withParam(r2, r1)
            java.lang.String r2 = "activity_name"
            if (r7 == 0) goto Le6
            java.lang.Object r1 = r7.get(r2)
            if (r1 == 0) goto Le6
            java.lang.String r1 = r1.toString()
            if (r1 != 0) goto Lee
        Le6:
            r5.withParam(r2, r0)
            r5.open(r3)
            goto L37
        Lee:
            r0 = r1
            goto Le6
        Lf0:
            java.lang.String r0 = "//feedback/"
            boolean r0 = X.C93472yG.S(r4, r0)
            if (r0 == 0) goto L107
            androidx.fragment.app.FragmentActivity r1 = r15.getActivity()
            java.lang.String r0 = "//feedback/myfeedback"
            com.bytedance.router.SmartRoute r0 = com.bytedance.router.SmartRouter.buildRoute(r1, r0)
            r0.open(r3)
            goto L37
        L107:
            java.lang.String r0 = "capcut"
            boolean r0 = X.C93472yG.S(r4, r0)
            if (r0 == 0) goto L144
            if (r7 == 0) goto L12a
            java.lang.String r0 = "page_param"
            java.lang.Object r0 = r7.get(r0)
            if (r0 == 0) goto L12a
            java.lang.String r3 = r0.toString()
            if (r3 == 0) goto L12a
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            java.lang.String r0 = "extra"
            org.json.JSONObject r5 = r2.put(r0, r3)
        L12a:
            android.content.Intent r2 = new android.content.Intent
            android.net.Uri r0 = android.net.Uri.parse(r4)
            r2.<init>(r6, r0)
            java.lang.String r0 = "deep_link_from_inner"
            r2.putExtra(r0, r1)
            if (r5 == 0) goto L13f
            java.lang.String r0 = "lynx_data"
            com.vega.core.ext.IntentExKt.e(r2, r0, r5)
        L13f:
            G4(r15, r2)
            goto L37
        L144:
            java.lang.String r1 = "MessageAppManager"
            java.lang.String r0 = "illegal url"
            com.lm.components.logservice.alog.BLog.e(r1, r0)
            goto L37
        L14d:
            androidx.fragment.app.FragmentActivity r9 = r15.getActivity()
            java.lang.String r4 = "//message/detail"
            com.bytedance.router.SmartRoute r12 = com.bytedance.router.SmartRouter.buildRoute(r9, r4)
            java.lang.String r9 = "message_type"
            if (r7 == 0) goto L167
            java.lang.Object r4 = r7.get(r9)
            if (r4 == 0) goto L167
            java.lang.String r10 = r4.toString()
            if (r10 != 0) goto L168
        L167:
            r10 = r0
        L168:
            java.lang.String r4 = "key_message_type_sign"
            r12.withParam(r4, r10)
            if (r7 == 0) goto L17b
            java.lang.Object r4 = r7.get(r11)
            if (r4 == 0) goto L17b
            java.lang.String r10 = r4.toString()
            if (r10 != 0) goto L17c
        L17b:
            r10 = r0
        L17c:
            java.lang.String r4 = "key_message_msg_id"
            r12.withParam(r4, r10)
            if (r7 == 0) goto L191
            java.lang.String r4 = "ref_id"
            java.lang.Object r4 = r7.get(r4)
            if (r4 == 0) goto L191
            java.lang.String r10 = r4.toString()
            if (r10 != 0) goto L192
        L191:
            r10 = r0
        L192:
            java.lang.String r4 = "key_message_ref_id"
            r12.withParam(r4, r10)
            java.lang.String r11 = "sub_type"
            if (r7 == 0) goto L1a7
            java.lang.Object r4 = r7.get(r11)
            if (r4 == 0) goto L1a7
            java.lang.String r10 = r4.toString()
            if (r10 != 0) goto L1a8
        L1a7:
            r10 = r0
        L1a8:
            java.lang.String r4 = "key_message_sub_type"
            r12.withParam(r4, r10)
            r12.open(r3)
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            if (r7 == 0) goto L1f8
            java.lang.Object r4 = r7.get(r11)
            if (r4 == 0) goto L1c3
            java.lang.String r4 = r4.toString()
            if (r4 != 0) goto L1c4
        L1c3:
            r4 = r0
        L1c4:
            java.lang.Object r7 = r7.get(r9)
            if (r7 == 0) goto L1d0
            java.lang.String r7 = r7.toString()
            if (r7 != 0) goto L1d1
        L1d0:
            r7 = r0
        L1d1:
            int r0 = r7.length()
            if (r0 <= 0) goto L1f8
            int r0 = r4.length()
            if (r0 <= 0) goto L1f8
            java.lang.String r0 = "1"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r0)
            if (r0 == 0) goto L1f8
            com.vega.feedx.message.LikeType$Companion r0 = com.vega.feedx.message.LikeType.b
            r0.getClass()
            int r0 = r4.hashCode()
            switch(r0) {
                case 51: goto L275;
                case 52: goto L283;
                case 53: goto L291;
                default: goto L1f1;
            }
        L1f1:
            java.lang.String r4 = "like"
        L1f3:
            java.lang.String r0 = "msg_type"
            r3.put(r0, r4)
        L1f8:
            if (r18 == 0) goto L264
            com.vega.feedx.message.MessageType$Companion r4 = com.vega.feedx.message.MessageType.b
            com.vega.feedx.message.Message r0 = r18.getLike()
            com.vega.feedx.main.bean.FeedItem r0 = r0.getTemplate()
            int r0 = r0.get_itemType()
            r4.getClass()
            if (r0 == r2) goto L210
            if (r0 == r8) goto L273
            r1 = 0
        L210:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r0 = "video_type_id"
            r3.put(r0, r1)
            com.vega.feedx.message.Message r0 = r18.getLike()
            com.vega.feedx.message.LikeType r2 = r0.getSubType()
            com.vega.feedx.message.LikeType r0 = com.vega.feedx.message.LikeType.i
            java.lang.String r1 = "source_template_id"
            if (r2 != r0) goto L26d
            com.vega.feedx.message.Message r0 = r18.getLike()
            com.vega.feedx.main.bean.FeedItem r0 = r0.getTemplate()
            com.vega.feedx.main.bean.FeedItem r0 = r0.getFromTemplate()
            if (r0 == 0) goto L239
            java.lang.Long r5 = r0.getId()
        L239:
            java.lang.String r0 = java.lang.String.valueOf(r5)
            r3.put(r1, r0)
        L240:
            com.vega.feedx.message.Message r0 = r18.getLike()
            com.vega.feedx.main.bean.FeedItem r0 = r0.getTemplate()
            boolean r0 = com.vega.feedx.message.MessageViewHolderKt.a(r0)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            java.lang.String r0 = "is_own"
            r3.put(r0, r1)
            com.vega.feedx.message.Message r0 = r18.getLike()
            com.vega.feedx.main.bean.FeedItem r0 = r0.getTemplate()
            java.lang.Long r0 = r0.getId()
            r3.put(r6, r0)
        L264:
            com.vega.report.ReportManagerWrapper r1 = com.vega.report.ReportManagerWrapper.INSTANCE
            java.lang.String r0 = "click_msg_list_detail"
            r1.onEvent(r0, r3)
            goto L37
        L26d:
            java.lang.String r0 = "none"
            r3.put(r1, r0)
            goto L240
        L273:
            r1 = 2
            goto L210
        L275:
            java.lang.String r0 = "3"
            boolean r0 = r4.equals(r0)
            if (r0 != 0) goto L27f
            goto L1f1
        L27f:
            java.lang.String r4 = "favorite"
            goto L1f3
        L283:
            java.lang.String r0 = "4"
            boolean r0 = r4.equals(r0)
            if (r0 != 0) goto L28d
            goto L1f1
        L28d:
            java.lang.String r4 = "use"
            goto L1f3
        L291:
            java.lang.String r0 = "5"
            boolean r0 = r4.equals(r0)
            if (r0 != 0) goto L29b
            goto L1f1
        L29b:
            java.lang.String r4 = "remake_publish"
            goto L1f3
        L29f:
            androidx.fragment.app.FragmentActivity r1 = r15.getActivity()
            java.lang.String r0 = "//template/detail"
            com.bytedance.router.SmartRoute r2 = com.bytedance.router.SmartRouter.buildRoute(r1, r0)
            if (r7 == 0) goto L2b1
            java.lang.Object r0 = r7.get(r11)
            if (r0 != 0) goto L2b5
        L2b1:
            java.lang.Long r0 = java.lang.Long.valueOf(r13)
        L2b5:
            java.lang.String r0 = r0.toString()
            r2.withParam(r6, r0)
            r2.withParam(r9, r10)
            if (r7 == 0) goto L2cd
            java.lang.Object r0 = r7.get(r4)
            if (r0 == 0) goto L2cd
            java.lang.String r0 = r0.toString()
            if (r0 != 0) goto L2e6
        L2cd:
            r2.withParam(r4, r8)
            java.lang.String r1 = "category_id"
            if (r7 == 0) goto L2de
            java.lang.Object r0 = r7.get(r1)
            if (r0 == 0) goto L2de
            java.lang.String r5 = r0.toString()
        L2de:
            r2.withParam(r1, r5)
            r2.open(r3)
            goto L37
        L2e6:
            r8 = r0
            goto L2cd
        L2e8:
            androidx.fragment.app.FragmentActivity r1 = r15.getActivity()
            java.lang.String r0 = "//user/homepage"
            com.bytedance.router.SmartRoute r2 = com.bytedance.router.SmartRouter.buildRoute(r1, r0)
            if (r7 == 0) goto L2fa
            java.lang.Object r0 = r7.get(r11)
            if (r0 != 0) goto L2fe
        L2fa:
            java.lang.Long r0 = java.lang.Long.valueOf(r13)
        L2fe:
            java.lang.String r1 = r0.toString()
            java.lang.String r0 = "user_id"
            r2.withParam(r0, r1)
            r2.withParam(r9, r10)
            if (r7 == 0) goto L318
            java.lang.Object r0 = r7.get(r4)
            if (r0 == 0) goto L318
            java.lang.String r0 = r0.toString()
            if (r0 != 0) goto L342
        L318:
            r2.withParam(r4, r8)
            if (r7 == 0) goto L327
            java.lang.Object r0 = r7.get(r6)
            if (r0 == 0) goto L327
            java.lang.String r5 = r0.toString()
        L327:
            r2.withParam(r6, r5)
            r2.open(r3)
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            java.lang.String r1 = "stage"
            java.lang.String r0 = "click"
            r2.put(r1, r0)
            com.vega.report.ReportManagerWrapper r1 = com.vega.report.ReportManagerWrapper.INSTANCE
            java.lang.String r0 = "cc_inbox_jump_user_list"
            r1.onEvent(r0, r2)
            goto L37
        L342:
            r8 = r0
            goto L318
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.feedx.message.ui.BaseMessageFragment.g1(com.vega.feedx.message.OnMessageClickListener$PageType, java.util.Map, com.vega.feedx.message.MessageData):void");
    }

    @Override // com.vega.ui.BaseFragment2
    public final void h4() {
        super.h4();
        this.E = SystemClock.uptimeMillis();
        HashMap<String, Object> map = new HashMap<>();
        map.put("Stage", "mount");
        ReportManagerWrapper.INSTANCE.onEvent("cc_inbox_page_event", map);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onStop() {
        super.onStop();
        if (this.E == 0) {
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis() - this.E;
        this.E = 0L;
        HashMap<String, Object> map = new HashMap<>();
        map.put("stage", "leave");
        map.put("time", Long.valueOf(jUptimeMillis));
        ReportManagerWrapper.INSTANCE.onEvent("cc_inbox_page_event", map);
    }
}