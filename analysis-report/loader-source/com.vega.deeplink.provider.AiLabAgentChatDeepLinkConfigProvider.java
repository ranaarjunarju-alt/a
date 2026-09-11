package com.vega.deeplink.provider;

import android.net.Uri;
import com.vega.deeplinkapi.DeepLinkConfigProvider;
import com.vega.deeplinkapi.DeepLinkDispatchSession;
import com.vega.deeplinkapi.DeepLinkUtilsKt;
import com.vega.videoagentapi.unifyagent.settings.AiLabAgentLoginFreeTrialSettings;
import com.vega.videoagentapi.unifyagent.settings.UnifiedAgentEntranceHelper;
import java.util.List;
import javax.inject.Named;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

@Named("//ailab_agent/chat")
/* loaded from: classes22.dex */
public final class AiLabAgentChatDeepLinkConfigProvider implements DeepLinkConfigProvider {
    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    public final List<String> a(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "");
        return CollectionsKt__CollectionsKt.emptyList();
    }

    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    public final Object b(DeepLinkDispatchSession deepLinkDispatchSession, Continuation<? super Uri> continuation) {
        UnifiedAgentEntranceHelper.f135606a.getClass();
        if (!UnifiedAgentEntranceHelper.b()) {
            Uri.Builder builderPath = new Uri.Builder().scheme("capcut").path("//unify_agent/chat");
            Intrinsics.checkNotNull(builderPath);
            DeepLinkUtilsKt.a(builderPath, deepLinkDispatchSession.b.f82398a, CollectionsKt__CollectionsKt.emptyList());
            String string = builderPath.toString();
            Intrinsics.checkNotNullExpressionValue(string, "");
            return Uri.parse(string);
        }
        String queryParameter = deepLinkDispatchSession.b.f82398a.getQueryParameter("conversation_id");
        if (queryParameter == null || queryParameter.length() <= 0) {
            return null;
        }
        Uri.Builder builderPath2 = new Uri.Builder().scheme("capcut").path("//main/tabbar");
        Intrinsics.checkNotNull(builderPath2);
        builderPath2.appendQueryParameter("index", "11");
        builderPath2.appendQueryParameter("anchor_key", "ai_story");
        builderPath2.appendQueryParameter("anchor_feature", "ai_lab");
        builderPath2.appendQueryParameter("ai_lab_chat_schema", deepLinkDispatchSession.b.f82398a.toString());
        String string2 = builderPath2.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "");
        return Uri.parse(string2);
    }

    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    public final boolean c(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "");
        return true;
    }

    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    public final void d(DeepLinkDispatchSession deepLinkDispatchSession) {
        Intrinsics.checkNotNullParameter(deepLinkDispatchSession, "");
    }

    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    public final boolean e(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "");
        AiLabAgentLoginFreeTrialSettings.Companion.getClass();
        return !AiLabAgentLoginFreeTrialSettings.Companion.a();
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:356:0x0611 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:373:0x065d */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:424:0x0611 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:425:0x065d */
    /* JADX DEBUG: Multi-variable search result rejected for r11v5, resolved type: java.util.ArrayList */
    /* JADX DEBUG: Multi-variable search result rejected for r11v6, resolved type: java.util.List */
    /* JADX DEBUG: Multi-variable search result rejected for r11v7, resolved type: java.util.List */
    /* JADX DEBUG: Multi-variable search result rejected for r11v8, resolved type: java.util.List */
    /* JADX DEBUG: Multi-variable search result rejected for r12v0, resolved type: java.util.ArrayList */
    /* JADX DEBUG: Multi-variable search result rejected for r12v1, resolved type: java.util.List */
    /* JADX DEBUG: Multi-variable search result rejected for r12v2, resolved type: java.util.List */
    /* JADX DEBUG: Multi-variable search result rejected for r12v3, resolved type: java.util.List */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0492  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0495  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0073 A[ADDED_TO_REGION, REMOVE] */
    /* JADX WARN: Removed duplicated region for block: B:302:0x04e0  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x04ef A[EDGE_INSN: B:422:0x04ef->B:305:0x04ef BREAK  A[LOOP:1: B:108:0x01e0->B:112:0x01fa], EDGE_INSN: B:423:0x04ef->B:305:0x04ef BREAK  A[LOOP:2: B:350:0x05ea->B:354:0x0604]] */
    /* JADX WARN: Removed duplicated region for block: B:307:0x04f2  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x05c7  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008b A[EDGE_INSN: B:37:0x008b->B:38:0x008c BREAK  A[LOOP:0: B:140:0x0263->B:156:0x02a3]] */
    /* JADX WARN: Removed duplicated region for block: B:408:0x06eb  */
    /* JADX WARN: Type inference failed for: r11v9, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r12v4, types: [java.util.ArrayList] */
    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object f(com.vega.deeplinkapi.DeepLinkDispatchSession r24, kotlin.coroutines.Continuation<? super com.vega.deeplinkapi.DeepLinkDispatchResult> r25) throws java.lang.Throwable {
        /*
            r23 = this;
            r3 = r25
            r4 = r24
            boolean r0 = r3 instanceof com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$1
            if (r0 == 0) goto L4e0
            r7 = r3
            com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$1 r7 = (com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$1) r7
            int r2 = r7.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L4e0
            int r2 = r2 - r1
            r7.t = r2
        L16:
            java.lang.Object r1 = r7.r
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r7.t
            r6 = 1
            if (r0 == 0) goto L4ae
            if (r0 != r6) goto L703
            com.vega.deeplinkapi.DeepLinkDispatchSession r4 = r7.q
            kotlin.ResultKt.throwOnFailure(r1)
        L28:
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.net.Uri r0 = r0.f82398a
            java.lang.String r3 = "open_input_box"
            java.lang.String r2 = r0.getQueryParameter(r3)
            r1 = 0
            if (r2 != 0) goto L4a1
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r0 = r0.f82399c
            r0.putBoolean(r3, r1)
        L3c:
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.net.Uri r0 = r0.f82398a
            java.lang.String r2 = "pull_album"
            java.lang.String r1 = r0.getQueryParameter(r2)
            if (r1 == 0) goto L5a
            int r0 = r1.length()
            if (r0 <= 0) goto L49e
            r0 = 1
        L4f:
            if (r0 == 0) goto L49b
        L51:
            if (r1 == 0) goto L5a
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r0 = r0.f82399c
            r0.putString(r2, r1)
        L5a:
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.net.Uri r0 = r0.f82398a
            java.lang.String r7 = "launch_message"
            java.lang.String r0 = r0.getQueryParameter(r7)
            if (r0 == 0) goto L6c
            int r0 = r0.length()
            if (r0 != 0) goto L498
        L6c:
            r0 = 1
        L6d:
            java.lang.String r3 = ""
            java.lang.String r1 = "launch_message_type"
            if (r0 != 0) goto L2c0
        L73:
            r0 = 0
        L74:
            com.vega.deeplinkapi.DeepLinkDispatchData r2 = r4.b
            android.net.Uri r5 = r2.f82398a
            java.lang.String r2 = "skills"
            java.lang.String r8 = r5.getQueryParameter(r2)
            if (r8 == 0) goto L8b
            int r5 = r8.length()
            if (r5 <= 0) goto L2bd
            r5 = 1
        L87:
            if (r5 == 0) goto L2ba
        L89:
            if (r8 != 0) goto L22f
        L8b:
            r5 = 0
        L8c:
            com.vega.deeplinkapi.DeepLinkDispatchData r8 = r4.b
            android.net.Uri r9 = r8.f82398a
            java.lang.String r8 = "send_text"
            java.lang.String r8 = r9.getQueryParameter(r8)
            if (r8 == 0) goto L9e
            int r9 = r8.length()
            if (r9 != 0) goto L22c
        L9e:
            r9 = 1
        L9f:
            if (r9 != 0) goto L229
            com.vega.deeplinkapi.DeepLinkDispatchData r9 = r4.b
            android.net.Uri r9 = r9.f82398a
            java.lang.String r9 = r9.getQueryParameter(r7)
            if (r9 == 0) goto Lb1
            int r9 = r9.length()
            if (r9 != 0) goto L226
        Lb1:
            r9 = 1
        Lb2:
            if (r9 == 0) goto L229
            if (r0 != 0) goto L229
            r15 = 1
        Lb7:
            if (r15 == 0) goto Lee
            if (r5 != 0) goto L222
            java.util.List r16 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()
        Lbf:
            java.lang.String r17 = ""
            r18 = 0
            r20 = 0
            com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$skillPrefix$1 r21 = new kotlin.jvm.functions.Function1<com.vega.feedx.unifyagent.Skill, java.lang.CharSequence>() { // from class: com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$skillPrefix$1
                static {
                    /*
                        com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$skillPrefix$1 r0 = new com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$skillPrefix$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$skillPrefix$1) com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$skillPrefix$1.e com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$skillPrefix$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$skillPrefix$1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$skillPrefix$1.<init>():void");
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final java.lang.CharSequence invoke(com.vega.feedx.unifyagent.Skill r2) {
                    /*
                        r1 = this;
                        com.vega.feedx.unifyagent.Skill r2 = (com.vega.feedx.unifyagent.Skill) r2
                        java.lang.String r0 = ""
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
                        java.lang.String r0 = com.vega.feedx.unifyagent.SkillKt.a(r2)
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$skillPrefix$1.invoke(java.lang.Object):java.lang.Object");
                }
            }
            r22 = 30
            r19 = r18
            java.lang.String r10 = kotlin.collections.CollectionsKt.j(r16, r17, r18, r19, r20, r21, r22)
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r9 = r0.f82399c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r10)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            r9.putString(r7, r0)
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r7 = r0.f82399c
            java.lang.String r0 = "launch_message_type_normal_text"
            r7.putString(r1, r0)
        Lee:
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.net.Uri r0 = r0.f82398a
            java.lang.String r10 = "model"
            java.lang.String r11 = r0.getQueryParameter(r10)
            java.lang.String r7 = "input_show_default_select_model_first_time"
            java.lang.String r8 = "deeplink_target_model_id"
            java.lang.String r1 = "chat_protocol_enter_from"
            if (r11 == 0) goto L137
            int r0 = r11.length()
            if (r0 <= 0) goto L21f
            r0 = 1
        L107:
            if (r0 == 0) goto L21c
        L109:
            if (r11 == 0) goto L137
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r0 = r0.f82399c
            r0.putString(r8, r11)
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r0 = r0.f82399c
            r0.putBoolean(r7, r6)
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.net.Uri r0 = r0.f82398a
            java.lang.String r0 = r0.getQueryParameter(r1)
            if (r0 == 0) goto L129
            int r0 = r0.length()
            if (r0 != 0) goto L219
        L129:
            r0 = 1
        L12a:
            if (r0 == 0) goto L137
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r9 = r0.f82399c
            java.lang.String r0 = com.vega.videoagentapi.unifyagent.UnifyAgentConstantKt.a(r11)
            r9.putString(r1, r0)
        L137:
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.net.Uri r0 = r0.f82398a
            java.lang.String r0 = r0.getQueryParameter(r10)
            if (r0 == 0) goto L147
            int r0 = r0.length()
            if (r0 != 0) goto L216
        L147:
            r0 = 1
        L148:
            if (r0 == 0) goto L16d
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.net.Uri r0 = r0.f82398a
            java.lang.String r1 = r0.getQueryParameter(r1)
            if (r1 == 0) goto L16d
            int r0 = r1.length()
            if (r0 <= 0) goto L213
            r0 = 1
        L15b:
            if (r0 == 0) goto L210
        L15d:
            if (r1 == 0) goto L16d
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r0 = r0.f82399c
            r0.putString(r8, r1)
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r0 = r0.f82399c
            r0.putBoolean(r7, r6)
        L16d:
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.net.Uri r0 = r0.f82398a
            java.lang.String r7 = "mode"
            java.lang.String r1 = r0.getQueryParameter(r7)
            if (r1 == 0) goto L198
            int r0 = r1.length()
            if (r0 <= 0) goto L20d
            r0 = 1
        L180:
            if (r0 == 0) goto L20a
        L182:
            if (r1 == 0) goto L198
            com.vega.videoagentapi.common.input.InputModalType$Companion r0 = com.vega.videoagentapi.common.input.InputModalType.b
            r0.getClass()
            com.vega.videoagentapi.common.input.InputModalType r6 = com.vega.videoagentapi.common.input.InputModalType.Companion.a(r1)
            if (r6 == 0) goto L198
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r1 = r0.f82399c
            java.lang.String r0 = r6.f135539a
            r1.putString(r7, r0)
        L198:
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.net.Uri r0 = r0.f82398a
            java.lang.String r10 = "launch_prompt_strategy"
            java.lang.String r0 = r0.getQueryParameter(r10)
            if (r0 == 0) goto L1aa
            int r0 = r0.length()
            if (r0 != 0) goto L208
        L1aa:
            r0 = 1
        L1ab:
            if (r0 == 0) goto L4f9
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.net.Uri r1 = r0.f82398a
            java.lang.String r0 = "sug_strategy"
            java.lang.String r7 = r1.getQueryParameter(r0)
            if (r7 == 0) goto L4ef
            java.lang.String r13 = ","
            java.lang.String[] r6 = new java.lang.String[]{r13}
            r9 = 6
            r0 = 0
            java.util.List r6 = X.C93472yG.M(r7, r6, r0, r9)
            if (r6 == 0) goto L4ef
            boolean r0 = r6.isEmpty()
            r0 = r0 ^ 1
            if (r0 == 0) goto L206
        L1cf:
            if (r6 == 0) goto L4ef
            java.util.ArrayList r7 = new java.util.ArrayList
            r8 = 10
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r6, r8)
            r7.<init>(r0)
            java.util.Iterator r6 = r6.iterator()
        L1e0:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L4e9
            java.lang.Object r0 = r6.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.CharSequence r0 = kotlin.text.StringsKt__StringsKt.trim(r0)
            java.lang.String r0 = r0.toString()
            java.lang.Integer r0 = kotlin.text.StringsKt__StringNumberConversionsKt.toIntOrNull(r0)
            if (r0 == 0) goto L4ef
            int r0 = r0.intValue()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r7.add(r0)
            goto L1e0
        L206:
            r6 = 0
            goto L1cf
        L208:
            r0 = 0
            goto L1ab
        L20a:
            r1 = 0
            goto L182
        L20d:
            r0 = 0
            goto L180
        L210:
            r1 = 0
            goto L15d
        L213:
            r0 = 0
            goto L15b
        L216:
            r0 = 0
            goto L148
        L219:
            r0 = 0
            goto L12a
        L21c:
            r11 = 0
            goto L109
        L21f:
            r0 = 0
            goto L107
        L222:
            r16 = r5
            goto Lbf
        L226:
            r9 = 0
            goto Lb2
        L229:
            r15 = 0
            goto Lb7
        L22c:
            r9 = 0
            goto L9f
        L22f:
            org.json.JSONArray r14 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L238
            r14.<init>(r8)     // Catch: java.lang.Throwable -> L238
            kotlin.Result.m17090constructorimpl(r14)     // Catch: java.lang.Throwable -> L238
            goto L240
        L238:
            r5 = move-exception
            java.lang.Object r14 = kotlin.ResultKt.createFailure(r5)
            kotlin.Result.m17090constructorimpl(r14)
        L240:
            boolean r5 = kotlin.Result.m17096isFailureimpl(r14)
            if (r5 == 0) goto L247
            r14 = 0
        L247:
            org.json.JSONArray r14 = (org.json.JSONArray) r14
            if (r14 != 0) goto L24d
            goto L8b
        L24d:
            int r5 = r14.length()
            if (r5 != 0) goto L255
            goto L8b
        L255:
            java.util.ArrayList r5 = new java.util.ArrayList
            int r8 = r14.length()
            r5.<init>(r8)
            int r13 = r14.length()
            r12 = 0
        L263:
            if (r12 >= r13) goto L8c
            org.json.JSONObject r9 = r14.optJSONObject(r12)
            if (r9 != 0) goto L26d
            goto L8b
        L26d:
            java.lang.String r8 = "skill_name"
            java.lang.String r11 = r9.optString(r8)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)
            int r8 = r11.length()
            if (r8 <= 0) goto L2b8
            r8 = 1
        L27d:
            if (r8 == 0) goto L2b6
        L27f:
            java.lang.String r8 = "skill_value"
            java.lang.String r10 = r9.optString(r8)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            int r8 = r10.length()
            if (r8 <= 0) goto L2b4
            r8 = 1
        L28f:
            if (r8 == 0) goto L2b2
        L291:
            java.lang.String r8 = "icon_url"
            java.lang.String r9 = r9.optString(r8)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            int r8 = r9.length()
            if (r8 <= 0) goto L2b0
            r8 = 1
        L2a1:
            if (r8 == 0) goto L2ae
        L2a3:
            com.vega.feedx.unifyagent.Skill r8 = new com.vega.feedx.unifyagent.Skill
            r8.<init>(r11, r10, r9)
            r5.add(r8)
            int r12 = r12 + 1
            goto L263
        L2ae:
            r9 = 0
            goto L2a3
        L2b0:
            r8 = 0
            goto L2a1
        L2b2:
            r10 = 0
            goto L291
        L2b4:
            r8 = 0
            goto L28f
        L2b6:
            r11 = 0
            goto L27f
        L2b8:
            r8 = 0
            goto L27d
        L2ba:
            r8 = 0
            goto L89
        L2bd:
            r5 = 0
            goto L87
        L2c0:
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.net.Uri r2 = r0.f82398a
            java.lang.String r0 = "template"
            java.lang.String r5 = r2.getQueryParameter(r0)
            if (r5 == 0) goto L2d7
            int r0 = r5.length()
            if (r0 <= 0) goto L301
            r0 = 1
        L2d3:
            if (r0 == 0) goto L2ff
        L2d5:
            if (r5 != 0) goto L2dc
        L2d7:
            r9 = 0
        L2d8:
            if (r9 != 0) goto L303
            goto L73
        L2dc:
            com.google.gson.Gson r2 = new com.google.gson.Gson     // Catch: java.lang.Throwable -> L2ed
            r2.<init>()     // Catch: java.lang.Throwable -> L2ed
            java.lang.Class<com.vega.feedx.unifyagent.Template> r0 = com.vega.feedx.unifyagent.Template.class
            java.lang.Object r9 = r2.fromJson(r5, r0)     // Catch: java.lang.Throwable -> L2ed
            com.vega.feedx.unifyagent.Template r9 = (com.vega.feedx.unifyagent.Template) r9     // Catch: java.lang.Throwable -> L2ed
            kotlin.Result.m17090constructorimpl(r9)     // Catch: java.lang.Throwable -> L2ed
            goto L2f5
        L2ed:
            r0 = move-exception
            java.lang.Object r9 = kotlin.ResultKt.createFailure(r0)
            kotlin.Result.m17090constructorimpl(r9)
        L2f5:
            boolean r0 = kotlin.Result.m17096isFailureimpl(r9)
            if (r0 == 0) goto L2fc
            r9 = 0
        L2fc:
            com.vega.feedx.unifyagent.Template r9 = (com.vega.feedx.unifyagent.Template) r9
            goto L2d8
        L2ff:
            r5 = 0
            goto L2d5
        L301:
            r0 = 0
            goto L2d3
        L303:
            java.lang.String r11 = r9.g()
            if (r11 == 0) goto L314
            int r0 = r11.length()
            if (r0 <= 0) goto L495
            r0 = 1
        L310:
            if (r0 == 0) goto L492
        L312:
            if (r11 != 0) goto L317
        L314:
            r0 = 0
            goto L74
        L317:
            java.lang.String r8 = r9.h()
            java.lang.String r0 = "trends"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r0)
            java.lang.String r5 = "send_launch_message_on_history"
            java.lang.String r10 = "key_cover_url"
            java.lang.String r2 = "template_id"
            if (r0 == 0) goto L3fc
            org.json.JSONObject r8 = new org.json.JSONObject
            r8.<init>()
            r8.put(r2, r11)
            java.lang.String r2 = r9.a()
            if (r2 == 0) goto L345
            int r0 = r2.length()
            if (r0 <= 0) goto L3f9
            r0 = 1
        L33e:
            if (r0 == 0) goto L3f6
        L340:
            if (r2 == 0) goto L345
            r8.put(r10, r2)
        L345:
            java.lang.String r2 = r9.i()
            if (r2 == 0) goto L35b
            int r0 = r2.length()
            if (r0 <= 0) goto L3f3
            r0 = 1
        L352:
            if (r0 == 0) goto L3f0
        L354:
            if (r2 == 0) goto L35b
            java.lang.String r0 = "KEY_TEMPLATE_SHORT_TITLE"
            r8.put(r0, r2)
        L35b:
            java.lang.String r2 = r9.j()
            if (r2 == 0) goto L371
            int r0 = r2.length()
            if (r0 <= 0) goto L3ed
            r0 = 1
        L368:
            if (r0 == 0) goto L3ea
        L36a:
            if (r2 == 0) goto L371
            java.lang.String r0 = "ai_trends_template_user_input"
            r8.put(r0, r2)
        L371:
            java.lang.String r2 = r9.f()
            if (r2 == 0) goto L387
            int r0 = r2.length()
            if (r0 <= 0) goto L3e8
            r0 = 1
        L37e:
            if (r0 == 0) goto L3e6
        L380:
            if (r2 == 0) goto L387
            java.lang.String r0 = "ai_trends_template_content"
            r8.put(r0, r2)
        L387:
            java.lang.String r2 = r8.toString()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r0 = r0.f82399c
            r0.putString(r7, r2)
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r2 = r0.f82399c
            java.lang.String r0 = "launch_message_type_trends_template"
            r2.putString(r1, r0)
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r0 = r0.f82399c
            r0.putBoolean(r5, r6)
            java.lang.Integer r0 = r9.k()
            if (r0 == 0) goto L3b8
            int r5 = r0.intValue()
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r2 = r0.f82399c
            java.lang.String r0 = "video_type_id"
            r2.putInt(r0, r5)
        L3b8:
            java.lang.Integer r2 = r9.b()
            if (r2 == 0) goto L3df
            int r0 = r2.intValue()
            if (r0 <= 0) goto L3e4
            r0 = 1
        L3c5:
            if (r0 == 0) goto L3e2
        L3c7:
            if (r2 == 0) goto L3df
            int r5 = r2.intValue()
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r2 = r0.f82399c
            java.lang.String r0 = "gallery_image_limit_count"
            r2.putInt(r0, r5)
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r2 = r0.f82399c
            java.lang.String r0 = "KEY_ENABLE_FULL_SCREEN_ALBUM_TEMPLATE"
            r2.putBoolean(r0, r6)
        L3df:
            r0 = 1
            goto L74
        L3e2:
            r2 = 0
            goto L3c7
        L3e4:
            r0 = 0
            goto L3c5
        L3e6:
            r2 = 0
            goto L380
        L3e8:
            r0 = 0
            goto L37e
        L3ea:
            r2 = 0
            goto L36a
        L3ed:
            r0 = 0
            goto L368
        L3f0:
            r2 = 0
            goto L354
        L3f3:
            r0 = 0
            goto L352
        L3f6:
            r2 = 0
            goto L340
        L3f9:
            r0 = 0
            goto L33e
        L3fc:
            java.lang.String r0 = "avm"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r0)
            if (r0 == 0) goto L314
            org.json.JSONObject r8 = new org.json.JSONObject
            r8.<init>()
            r8.put(r2, r11)
            java.lang.String r2 = r9.a()
            if (r2 == 0) goto L420
            int r0 = r2.length()
            if (r0 <= 0) goto L490
            r0 = 1
        L419:
            if (r0 == 0) goto L48e
        L41b:
            if (r2 == 0) goto L420
            r8.put(r10, r2)
        L420:
            java.lang.String r2 = r9.c()
            if (r2 == 0) goto L436
            int r0 = r2.length()
            if (r0 <= 0) goto L48c
            r0 = 1
        L42d:
            if (r0 == 0) goto L48a
        L42f:
            if (r2 == 0) goto L436
            java.lang.String r0 = "one_word_topic"
            r8.put(r0, r2)
        L436:
            java.lang.String r2 = r9.e()
            if (r2 == 0) goto L44c
            int r0 = r2.length()
            if (r0 <= 0) goto L488
            r0 = 1
        L443:
            if (r0 == 0) goto L486
        L445:
            if (r2 == 0) goto L44c
            java.lang.String r0 = "reverse_prompt"
            r8.put(r0, r2)
        L44c:
            java.lang.String r2 = r9.d()
            if (r2 == 0) goto L462
            int r0 = r2.length()
            if (r0 <= 0) goto L484
            r0 = 1
        L459:
            if (r0 == 0) goto L482
        L45b:
            if (r2 == 0) goto L462
            java.lang.String r0 = "reference_image"
            r8.put(r0, r2)
        L462:
            java.lang.String r2 = r8.toString()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r0 = r0.f82399c
            r0.putString(r7, r2)
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r2 = r0.f82399c
            java.lang.String r0 = "launch_message_type_avm_template"
            r2.putString(r1, r0)
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r0 = r0.f82399c
            r0.putBoolean(r5, r6)
            goto L3df
        L482:
            r2 = 0
            goto L45b
        L484:
            r0 = 0
            goto L459
        L486:
            r2 = 0
            goto L445
        L488:
            r0 = 0
            goto L443
        L48a:
            r2 = 0
            goto L42f
        L48c:
            r0 = 0
            goto L42d
        L48e:
            r2 = 0
            goto L41b
        L490:
            r0 = 0
            goto L419
        L492:
            r11 = 0
            goto L312
        L495:
            r0 = 0
            goto L310
        L498:
            r0 = 0
            goto L6d
        L49b:
            r1 = 0
            goto L51
        L49e:
            r0 = 0
            goto L4f
        L4a1:
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r1 = r0.f82399c
            boolean r0 = java.lang.Boolean.parseBoolean(r2)
            r1.putBoolean(r3, r0)
            goto L3c
        L4ae:
            kotlin.ResultKt.throwOnFailure(r1)
            r7.q = r4
            r7.t = r6
            kotlinx.coroutines.CancellableContinuationImpl r3 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r7)
            r3.<init>(r0, r6)
            r3.initCancellability()
            com.vega.videoagentapi.common.utils.AgentAuth r2 = com.vega.videoagentapi.common.utils.AgentAuth.f135540a
            androidx.appcompat.app.AppCompatActivity r1 = r4.f82403a
            com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$2$1 r0 = new com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$2$1
            r0.<init>()
            r2.getClass()
            com.vega.videoagentapi.common.utils.AgentAuth.a(r1, r0)
            java.lang.Object r1 = r3.getResult()
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r1 != r0) goto L4dd
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r7)
        L4dd:
            if (r1 != r5) goto L28
            return r5
        L4e0:
            com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$1 r7 = new com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$1
            r0 = r23
            r7.<init>(r0, r3)
            goto L16
        L4e9:
            boolean r0 = r7.isEmpty()
            if (r0 == 0) goto L5ca
        L4ef:
            r1 = 0
        L4f0:
            if (r1 == 0) goto L4f9
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r0 = r0.f82399c
            r0.putString(r10, r1)
        L4f9:
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.net.Uri r0 = r0.f82398a
            java.lang.String r7 = "album_type"
            java.lang.String r6 = r0.getQueryParameter(r7)
            if (r6 == 0) goto L5c7
            int r0 = r6.length()
            if (r0 <= 0) goto L5c3
            r1 = 1
            r0 = 1
        L50d:
            if (r0 != r1) goto L5c7
            r0 = 1
        L510:
            if (r0 == 0) goto L519
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r0 = r0.f82399c
            r0.putString(r7, r6)
        L519:
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.net.Uri r0 = r0.f82398a
            java.lang.String r7 = "is_sug_enabled"
            java.lang.String r6 = r0.getQueryParameter(r7)
            if (r6 == 0) goto L538
            int r0 = r6.length()
            if (r0 <= 0) goto L5c0
            r0 = 1
        L52c:
            if (r0 == 0) goto L5bd
        L52e:
            if (r6 == 0) goto L538
            int r1 = r6.hashCode()
            r0 = 48
            if (r1 == r0) goto L5ab
        L538:
            com.vega.videoagentapi.unifyagent.LaunchCloudMaterial$Companion r7 = com.vega.videoagentapi.unifyagent.LaunchCloudMaterial.Companion
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.net.Uri r1 = r0.f82398a
            java.lang.String r0 = "cloud_materials"
            java.lang.String r6 = r1.getQueryParameter(r0)
            com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$14 r1 = new kotlin.jvm.functions.Function1<java.lang.String, java.lang.Boolean>() { // from class: com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$14
                static {
                    /*
                        com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$14 r0 = new com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$14
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$14) com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$14.e com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$14
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$14.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$14.<init>():void");
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Boolean invoke(java.lang.String r4) {
                    /*
                        r3 = this;
                        java.lang.String r4 = (java.lang.String) r4
                        java.lang.String r0 = ""
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                        android.net.Uri r0 = android.net.Uri.parse(r4)
                        java.lang.String r2 = r0.getScheme()
                        java.lang.String r1 = "https"
                        r0 = 1
                        boolean r0 = kotlin.text.StringsKt__StringsJVMKt.equals(r2, r1, r0)
                        java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider$onIntercept$14.invoke(java.lang.Object):java.lang.Object");
                }
            }
            r0 = 4
            java.util.List r1 = com.vega.videoagentapi.unifyagent.LaunchCloudMaterial.Companion.a(r7, r6, r1, r0)
            if (r1 == 0) goto L6d0
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r6 = r0.f82399c
            org.json.JSONArray r8 = new org.json.JSONArray
            r8.<init>()
            java.util.Iterator r10 = r1.iterator()
        L55a:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L6c4
            java.lang.Object r9 = r10.next()
            com.vega.videoagentapi.unifyagent.LaunchCloudMaterial r9 = (com.vega.videoagentapi.unifyagent.LaunchCloudMaterial) r9
            org.json.JSONObject r7 = new org.json.JSONObject
            r7.<init>()
            java.lang.String r1 = r9.getType()
            java.lang.String r0 = "type"
            r7.put(r0, r1)
            java.lang.String r1 = "id"
            java.lang.String r0 = r9.getId()
            r7.put(r1, r0)
            java.lang.String r1 = "url"
            java.lang.String r0 = r9.getUrl()
            r7.put(r1, r0)
            java.lang.String r1 = "coverUrl"
            java.lang.String r0 = r9.getCoverUrl()
            r7.put(r1, r0)
            java.lang.String r0 = r9.getFileName()
            int r0 = r0.length()
            if (r0 <= 0) goto L5a9
            r0 = 1
        L59a:
            if (r0 == 0) goto L5a5
            java.lang.String r1 = "fileName"
            java.lang.String r0 = r9.getFileName()
            r7.put(r1, r0)
        L5a5:
            r8.put(r7)
            goto L55a
        L5a9:
            r0 = 0
            goto L59a
        L5ab:
            java.lang.String r0 = "0"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L538
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r1 = r0.f82399c
            r0 = 0
            r1.putBoolean(r7, r0)
            goto L538
        L5bd:
            r6 = 0
            goto L52e
        L5c0:
            r0 = 0
            goto L52c
        L5c3:
            r1 = 1
            r0 = 0
            goto L50d
        L5c7:
            r0 = 0
            goto L510
        L5ca:
            java.lang.String r0 = "sug_count"
            java.lang.String r11 = r1.getQueryParameter(r0)
            if (r11 == 0) goto L610
            java.lang.String[] r6 = new java.lang.String[]{r13}
            r0 = 0
            java.util.List r6 = X.C93472yG.M(r11, r6, r0, r9)
            if (r6 == 0) goto L610
            java.util.ArrayList r12 = new java.util.ArrayList
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r6, r8)
            r12.<init>(r0)
            java.util.Iterator r6 = r6.iterator()
        L5ea:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L611
            java.lang.Object r0 = r6.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.CharSequence r0 = kotlin.text.StringsKt__StringsKt.trim(r0)
            java.lang.String r0 = r0.toString()
            java.lang.Integer r0 = kotlin.text.StringsKt__StringNumberConversionsKt.toIntOrNull(r0)
            if (r0 == 0) goto L4ef
            int r0 = r0.intValue()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r12.add(r0)
            goto L5ea
        L610:
            r12 = 0
        L611:
            if (r12 != 0) goto L617
            java.util.List r12 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()
        L617:
            java.lang.String r0 = "sug_scene"
            java.lang.String r6 = r1.getQueryParameter(r0)
            if (r6 == 0) goto L65c
            java.lang.String[] r1 = new java.lang.String[]{r13}
            r0 = 0
            java.util.List r1 = X.C93472yG.M(r6, r1, r0, r9)
            if (r1 == 0) goto L65c
            java.util.ArrayList r11 = new java.util.ArrayList
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r1, r8)
            r11.<init>(r0)
            java.util.Iterator r6 = r1.iterator()
        L637:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L65d
            java.lang.Object r0 = r6.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.CharSequence r0 = kotlin.text.StringsKt__StringsKt.trim(r0)
            java.lang.String r1 = r0.toString()
            int r0 = r1.length()
            if (r0 <= 0) goto L65a
            r0 = 1
        L652:
            if (r0 == 0) goto L658
        L654:
            r11.add(r1)
            goto L637
        L658:
            r1 = 0
            goto L654
        L65a:
            r0 = 0
            goto L652
        L65c:
            r11 = 0
        L65d:
            if (r11 != 0) goto L663
            java.util.List r11 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()
        L663:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.Iterator r14 = r7.iterator()
            r13 = 0
        L66d:
            boolean r0 = r14.hasNext()
            if (r0 == 0) goto L6b0
            java.lang.Object r0 = r14.next()
            int r8 = r13 + 1
            if (r13 >= 0) goto L67e
            kotlin.collections.CollectionsKt__CollectionsKt.throwIndexOverflow()
        L67e:
            java.lang.Number r0 = (java.lang.Number) r0
            int r1 = r0.intValue()
            java.lang.Object r0 = kotlin.collections.CollectionsKt___CollectionsKt.getOrNull(r12, r13)
            java.lang.Integer r0 = (java.lang.Integer) r0
            if (r0 == 0) goto L6ae
            int r0 = r0.intValue()
        L690:
            if (r0 > 0) goto L69a
            r7 = 0
        L693:
            if (r7 == 0) goto L698
            r9.add(r7)
        L698:
            r13 = r8
            goto L66d
        L69a:
            com.vega.feedx.unifyagent.PromptStrategy r7 = new com.vega.feedx.unifyagent.PromptStrategy
            java.lang.Integer r6 = java.lang.Integer.valueOf(r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            java.lang.Object r0 = kotlin.collections.CollectionsKt___CollectionsKt.getOrNull(r11, r13)
            java.lang.String r0 = (java.lang.String) r0
            r7.<init>(r6, r1, r0)
            goto L693
        L6ae:
            r0 = 6
            goto L690
        L6b0:
            boolean r0 = r9.isEmpty()
            r0 = r0 ^ 1
            if (r0 == 0) goto L6bc
        L6b8:
            if (r9 != 0) goto L6be
            goto L4ef
        L6bc:
            r9 = 0
            goto L6b8
        L6be:
            java.lang.String r1 = com.vega.feedx.unifyagent.UnifyAgentHomepageSettingsKt.a(r9)
            goto L4f0
        L6c4:
            java.lang.String r1 = r8.toString()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            java.lang.String r0 = "key_launch_cloud_materials"
            r6.putString(r0, r1)
        L6d0:
            if (r15 == 0) goto L6eb
            if (r5 == 0) goto L6da
            boolean r0 = r5.isEmpty()
            if (r0 == 0) goto L6e9
        L6da:
            r0 = 1
        L6db:
            if (r0 != 0) goto L6eb
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r1 = r0.f82399c
            java.lang.String r0 = "[]"
            r1.putString(r2, r0)
        L6e6:
            com.vega.deeplinkapi.Continue r0 = com.vega.deeplinkapi.Continue.f82397a
            return r0
        L6e9:
            r0 = 0
            goto L6db
        L6eb:
            if (r15 != 0) goto L6e6
            if (r5 == 0) goto L6e6
            com.google.gson.Gson r0 = new com.google.gson.Gson
            r0.<init>()
            java.lang.String r1 = r0.toJson(r5)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r4.b
            android.os.Bundle r0 = r0.f82399c
            r0.putString(r2, r1)
            goto L6e6
        L703:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.deeplink.provider.AiLabAgentChatDeepLinkConfigProvider.f(com.vega.deeplinkapi.DeepLinkDispatchSession, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    public final String g(DeepLinkDispatchSession deepLinkDispatchSession) {
        Intrinsics.checkNotNullParameter(deepLinkDispatchSession, "");
        return "capcut://main";
    }
}