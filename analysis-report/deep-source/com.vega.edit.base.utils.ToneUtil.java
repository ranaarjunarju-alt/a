package com.vega.edit.base.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.bytedance.common.profilesdk.ProfileManager;
import com.bytedance.speech.speechengine.SpeechEngineDefines;
import com.lemon.lv.clipmonetize.wrapper.ResourceType;
import com.lemon.lv.config.SamiToneEmotionClientConfig;
import com.lemon.lv.config.SamiToneEmotionClientConfigSetting;
import com.lemon.lv.config.ToneEmotionClientConfig;
import com.lemon.lv.config.ToneEmotionClientConfigSetting;
import com.lemon.lv.data.ToneType;
import com.lemon.lv.data.ToneTypeTagInfo;
import com.lemon.lvoverseas.R;
import com.lm.components.logservice.alog.BLog;
import com.ss.android.ugc.effectmanager.effect.model.Effect;
import com.ss.android.ugc.effectmanager.effect.model.EffectCategoryModel;
import com.utils.BusinessFilterUtil;
import com.vega.config.ConfigSettingsKt;
import com.vega.container.session.core.ISession;
import com.vega.core.ext.ExtentionKt;
import com.vega.core.utils.RankReportType;
import com.vega.core.utils.RankReporter;
import com.vega.edit.base.model.repository.DownloadableItemStateKt;
import com.vega.edit.base.report.Reporter;
import com.vega.edit.base.utils.RecommendCapabilityViewInfo;
import com.vega.edit.base.widget.MarqueeTextView;
import com.vega.effectplatform.artist.data.EffectExtendKt;
import com.vega.effectplatform.loki.EffectExKt;
import com.vega.infrastructure.util.SizeUtil;
import com.vega.middlebridge.swig.MaterialAudio;
import com.vega.middlebridge.swig.Segment;
import com.vega.middlebridge.swig.SegmentAudio;
import com.vega.report.ReportManagerWrapper;
import com.vega.subscriptionapi.report.BusinessReportEvents;
import com.vega.ve.utils.DraftExpandKt;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.Result;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.json.JSONObject;

/* loaded from: classes31.dex */
public final class ToneUtil {

    /* renamed from: a, reason: collision with root package name */
    public static final ToneUtil f89173a = new ToneUtil();
    public static final Map<String, Set<String>> b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    public static final Lazy f89174c = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.edit.base.utils.ToneUtil$enableMicrosoftToneEmotion$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(((ToneEmotionClientConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(ToneEmotionClientConfigSetting.class))).a());
        }
    });

    /* renamed from: d, reason: collision with root package name */
    public static final Lazy f89175d = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.edit.base.utils.ToneUtil$enableSamiToneEmotion$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(((SamiToneEmotionClientConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(SamiToneEmotionClientConfigSetting.class))).a());
        }
    });

    public static boolean a(ISession iSession, Segment... segmentArr) {
        SegmentAudio segmentAudio;
        MaterialAudio materialAudioU;
        Intrinsics.checkNotNullParameter(iSession, "");
        Intrinsics.checkNotNullParameter(segmentArr, "");
        for (Segment segment : segmentArr) {
            if (segment != null) {
                f89173a.getClass();
                List listF = f(segment);
                if (listF != null) {
                    Iterator it = listF.iterator();
                    while (it.hasNext()) {
                        Segment segmentI = iSession.i((String) it.next());
                        if ((segmentI instanceof SegmentAudio) && (segmentAudio = (SegmentAudio) segmentI) != null && (materialAudioU = segmentAudio.u()) != null && materialAudioU.n()) {
                            return true;
                        }
                    }
                } else {
                    continue;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x02e7 A[Catch: Exception -> 0x033b, TryCatch #1 {Exception -> 0x033b, blocks: (B:47:0x01db, B:50:0x01e4, B:52:0x01f8, B:54:0x01ff, B:56:0x0214, B:57:0x021d, B:64:0x0277, B:66:0x02ad, B:70:0x02bc, B:72:0x02ca, B:76:0x02d7, B:80:0x02e7, B:82:0x02ee, B:84:0x02f6, B:86:0x02fd, B:88:0x032a, B:91:0x0331, B:92:0x0334, B:93:0x0337, B:63:0x0262), top: B:104:0x01db }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x02fd A[Catch: Exception -> 0x033b, TryCatch #1 {Exception -> 0x033b, blocks: (B:47:0x01db, B:50:0x01e4, B:52:0x01f8, B:54:0x01ff, B:56:0x0214, B:57:0x021d, B:64:0x0277, B:66:0x02ad, B:70:0x02bc, B:72:0x02ca, B:76:0x02d7, B:80:0x02e7, B:82:0x02ee, B:84:0x02f6, B:86:0x02fd, B:88:0x032a, B:91:0x0331, B:92:0x0334, B:93:0x0337, B:63:0x0262), top: B:104:0x01db }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.lemon.lv.data.ToneType b(com.ss.android.ugc.effectmanager.effect.model.Effect r56, com.ss.android.ugc.effectmanager.effect.model.EffectCategoryModel r57, java.util.Map r58, java.util.Map r59) throws org.json.JSONException {
        /*
            java.lang.String r4 = "sami"
            java.lang.String r3 = "ToneUtil"
            java.lang.String r1 = ""
            r7 = r56
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r1)
            java.lang.String r17 = com.vega.effectplatform.loki.EffectExKt.p(r7)
            java.lang.String r18 = com.vega.effectplatform.loki.EffectExKt.q(r7)
            java.lang.String r19 = com.vega.effectplatform.loki.EffectExKt.r(r7)
            java.lang.String r2 = r7.getExtra()
            java.lang.String r0 = "second_category_id_ex"
            java.lang.String r20 = com.vega.effectplatform.loki.EffectExKt.Q(r2, r0)
            java.lang.String r2 = r7.getExtra()
            java.lang.String r0 = "second_category_key_ex"
            java.lang.String r21 = com.vega.effectplatform.loki.EffectExKt.Q(r2, r0)
            java.lang.String r2 = r7.getExtra()
            java.lang.String r0 = "second_category_name_ex"
            java.lang.String r22 = com.vega.effectplatform.loki.EffectExKt.Q(r2, r0)
            java.lang.String r23 = r7.getResourceId()
            java.lang.String r24 = r7.getEffectId()
            java.lang.String r25 = r7.getId()
            java.lang.String r27 = r7.getName()
            java.lang.String r26 = r7.getPanel()
            boolean r28 = com.vega.effectplatform.loki.EffectExKt.h0(r7)
            java.lang.String r29 = com.vega.effectplatform.loki.EffectExKt.R(r7)
            java.util.List r30 = r7.getTags()
            boolean r31 = com.vega.effectplatform.artist.data.EffectExtendKt.n(r7)
            java.lang.String r2 = r7.getExtra()
            java.lang.String r0 = "is_aiavatar"
            boolean r32 = com.vega.effectplatform.artist.data.EffectExtendKt.I(r2, r0)
            java.lang.String r34 = com.vega.effectplatform.artist.data.EffectExtendKt.z(r7)
            boolean r37 = com.vega.effectplatform.artist.data.EffectExtendKt.U(r7)
            boolean r39 = com.vega.effectplatform.artist.data.EffectExtendKt.M(r7)
            java.lang.String r43 = r7.getExtra()
            if (r43 != 0) goto L77
            r43 = r1
        L77:
            java.lang.String r2 = r7.getExtra()
            java.lang.String r0 = "is_vop"
            boolean r44 = com.vega.effectplatform.artist.data.EffectExtendKt.I(r2, r0)
            com.lemon.lv.data.ToneType r10 = new com.lemon.lv.data.ToneType
            r11 = 0
            r2 = 0
            r0 = r10
            r33 = 0
            r35 = 0
            r54 = 1967128639(0x7540003f, float:2.4339013E32)
            r55 = 1022(0x3fe, float:1.432E-42)
            r12 = r11
            r13 = r11
            r14 = r11
            r15 = r11
            r16 = r11
            r38 = r11
            r40 = r33
            r41 = r11
            r42 = r11
            r45 = r11
            r46 = r11
            r47 = r11
            r48 = r11
            r49 = r11
            r50 = r33
            r51 = r11
            r52 = r11
            r53 = r33
            r56 = r11
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56)
            java.lang.String r5 = com.vega.edit.base.model.repository.DownloadableItemStateKt.b(r7)
            r0.setIconUrl(r5)
            java.lang.String r6 = r7.getExtra()
            java.lang.String r5 = "tone_avatar_url"
            java.lang.String r5 = com.vega.effectplatform.loki.EffectExKt.Q(r6, r5)
            r0.setAvatarUrl(r5)
            java.lang.String r6 = r7.getExtra()
            java.lang.String r5 = "mock_tone_info"
            java.lang.String r5 = com.vega.effectplatform.loki.EffectExKt.Q(r6, r5)
            r0.setMockToneInfo(r5)
            java.util.List r5 = com.vega.effectplatform.loki.EffectExKt.N(r7)
            r13 = 0
            r6 = r58
            if (r6 == 0) goto L129
            java.util.Iterator r10 = r5.iterator()
        Le2:
            boolean r5 = r10.hasNext()
            if (r5 == 0) goto L121
            java.lang.Object r9 = r10.next()
            com.lemon.librespool.model.gen.TagInfo r9 = (com.lemon.librespool.model.gen.TagInfo) r9
            java.lang.String r5 = r9.getId()
            java.lang.Object r8 = r6.get(r5)
            java.lang.String r8 = (java.lang.String) r8
            if (r8 == 0) goto Le2
            java.lang.String r2 = "en"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r2)
            if (r2 == 0) goto L107
            java.lang.String r2 = r9.getId()
            goto Le2
        L107:
            java.lang.String r5 = r9.getId()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r1)
            java.util.Locale r2 = java.util.Locale.getDefault()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)
            java.lang.String r2 = r8.toUpperCase(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)
            kotlin.Pair r11 = kotlin.TuplesKt.to(r5, r2)
            goto L129
        L121:
            if (r2 == 0) goto L129
            java.lang.String r5 = "EN"
            kotlin.Pair r11 = kotlin.TuplesKt.to(r2, r5)
        L129:
            r0.setLanguageTag(r11)
            java.util.List r2 = com.vega.effectplatform.loki.EffectExKt.N(r7)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r10 = r2.iterator()
        L139:
            boolean r2 = r10.hasNext()
            r8 = 1
            r11 = r59
            if (r2 == 0) goto L166
            java.lang.Object r9 = r10.next()
            r8 = r9
            com.lemon.librespool.model.gen.TagInfo r8 = (com.lemon.librespool.model.gen.TagInfo) r8
            if (r6 == 0) goto L155
            java.lang.String r2 = r8.getId()
            boolean r2 = r6.containsKey(r2)
            if (r2 != 0) goto L139
        L155:
            if (r11 == 0) goto L139
            java.lang.String r2 = r8.getId()
            boolean r2 = r11.containsKey(r2)
            if (r2 != 0) goto L162
            goto L139
        L162:
            r5.add(r9)
            goto L139
        L166:
            java.util.ArrayList r10 = new java.util.ArrayList
            r2 = 10
            int r2 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r5, r2)
            r10.<init>(r2)
            java.util.Iterator r9 = r5.iterator()
        L175:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto L19d
            java.lang.Object r2 = r9.next()
            com.lemon.librespool.model.gen.TagInfo r2 = (com.lemon.librespool.model.gen.TagInfo) r2
            com.lemon.lv.data.ToneTypeTagInfo r6 = new com.lemon.lv.data.ToneTypeTagInfo
            java.lang.String r5 = r2.getId()
            if (r11 == 0) goto L195
            java.lang.String r2 = r2.getId()
            java.lang.Object r2 = r11.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L196
        L195:
            r2 = r1
        L196:
            r6.<init>(r5, r2)
            r10.add(r6)
            goto L175
        L19d:
            r0.setToneStyleTagList(r10)
            java.lang.String r2 = r7.getName()
            r0.setToneName(r2)
            boolean r2 = com.vega.effectplatform.artist.data.EffectExtendKt.R(r7)
            r0.setRecentUsed(r2)
            java.lang.String r2 = r0.getCategoryID()
            int r2 = r2.length()
            if (r2 != 0) goto L1c8
            if (r57 == 0) goto L1c8
            java.lang.String r2 = com.vega.effectplatform.artist.data.EffectExtendKt.k(r57)
            r0.setCategoryID(r2)
            java.lang.String r2 = r57.getKey()
            r0.setCategoryKey(r2)
        L1c8:
            java.lang.String r2 = r0.getCategoryName()
            int r2 = r2.length()
            if (r2 != 0) goto L1db
            if (r57 == 0) goto L1db
            java.lang.String r2 = r57.getName()
            r0.setCategoryName(r2)
        L1db:
            org.json.JSONObject r10 = new org.json.JSONObject     // Catch: java.lang.Exception -> L33b
            java.lang.String r2 = r7.getExtra()     // Catch: java.lang.Exception -> L33b
            if (r2 != 0) goto L1e4
            r2 = r1
        L1e4:
            r10.<init>(r2)     // Catch: java.lang.Exception -> L33b
            java.lang.String r2 = "original_audio"
            java.lang.String r2 = r10.optString(r2, r1)     // Catch: java.lang.Exception -> L33b
            r0.setOriginalAudioVid(r2)     // Catch: java.lang.Exception -> L33b
            java.lang.String r2 = "test_audio_list"
            org.json.JSONArray r11 = r10.optJSONArray(r2)     // Catch: java.lang.Exception -> L33b
            if (r11 == 0) goto L220
            int r9 = r11.length()     // Catch: java.lang.Exception -> L33b
            r6 = 0
        L1fd:
            if (r6 >= r9) goto L220
            org.json.JSONObject r12 = r11.getJSONObject(r6)     // Catch: java.lang.Exception -> L33b
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r1)     // Catch: java.lang.Exception -> L33b
            java.lang.String r2 = "lang"
            java.lang.String r5 = r12.optString(r2)     // Catch: java.lang.Exception -> L33b
            java.lang.String r2 = "zh"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r2)     // Catch: java.lang.Exception -> L33b
            if (r2 == 0) goto L21d
            java.lang.String r2 = "audio_vid"
            java.lang.String r2 = r12.optString(r2)     // Catch: java.lang.Exception -> L33b
            r0.setAuditionAudioZhVid(r2)     // Catch: java.lang.Exception -> L33b
        L21d:
            int r6 = r6 + 1
            goto L1fd
        L220:
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch: java.lang.Exception -> L261
            java.lang.String r2 = "tonetype"
            java.lang.String r2 = r10.optString(r2)     // Catch: java.lang.Exception -> L261
            r9.<init>(r2)     // Catch: java.lang.Exception -> L261
            java.lang.String r5 = "rate"
            java.lang.String r2 = "24000"
            java.lang.String r2 = r9.optString(r5, r2)     // Catch: java.lang.Exception -> L25e
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)     // Catch: java.lang.Exception -> L25e
            r0.setRate(r2)     // Catch: java.lang.Exception -> L25e
            java.lang.String r2 = "tts_voice"
            java.lang.String r2 = r9.optString(r2, r1)     // Catch: java.lang.Exception -> L25e
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)     // Catch: java.lang.Exception -> L25e
            r0.setTtsVoice(r2)     // Catch: java.lang.Exception -> L25e
            java.lang.String r2 = "voice_type"
            java.lang.String r2 = r9.optString(r2, r1)     // Catch: java.lang.Exception -> L25e
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)     // Catch: java.lang.Exception -> L25e
            r0.setVoiceType(r2)     // Catch: java.lang.Exception -> L25e
            java.lang.String r2 = "platform"
            java.lang.String r2 = r9.optString(r2, r4)     // Catch: java.lang.Exception -> L25e
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)     // Catch: java.lang.Exception -> L25e
            r0.setPlatform(r2)     // Catch: java.lang.Exception -> L25e
            goto L277
        L25e:
            r6 = move-exception
            r13 = r9
            goto L262
        L261:
            r6 = move-exception
        L262:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L33b
            r5.<init>()     // Catch: java.lang.Exception -> L33b
            java.lang.String r2 = "effect2ToneType parse tonetype "
            r5.append(r2)     // Catch: java.lang.Exception -> L33b
            r5.append(r6)     // Catch: java.lang.Exception -> L33b
            java.lang.String r2 = r5.toString()     // Catch: java.lang.Exception -> L33b
            com.lm.components.logservice.alog.BLog.e(r3, r2)     // Catch: java.lang.Exception -> L33b
            r9 = r13
        L277:
            r0.setAiAvatar(r8)     // Catch: java.lang.Exception -> L33b
            java.lang.String r2 = "reading_speed"
            r5 = 4608533498688228557(0x3ff4cccccccccccd, double:1.3)
            double r5 = r10.optDouble(r2, r5)     // Catch: java.lang.Exception -> L33b
            r0.setReadingSpeed(r5)     // Catch: java.lang.Exception -> L33b
            java.lang.String r2 = "default_audition_text"
            java.lang.String r2 = r10.optString(r2, r1)     // Catch: java.lang.Exception -> L33b
            r0.setDefaultAuditionText(r2)     // Catch: java.lang.Exception -> L33b
            java.lang.String r5 = "tone_model_type"
            java.lang.String r2 = "normal_tts"
            java.lang.String r2 = r10.optString(r5, r2)     // Catch: java.lang.Exception -> L33b
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)     // Catch: java.lang.Exception -> L33b
            r0.setToneModelType(r2)     // Catch: java.lang.Exception -> L33b
            kotlin.Lazy r2 = com.vega.edit.base.utils.ToneUtil.f89174c     // Catch: java.lang.Exception -> L33b
            java.lang.Object r2 = r2.getValue()     // Catch: java.lang.Exception -> L33b
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch: java.lang.Exception -> L33b
            boolean r2 = r2.booleanValue()     // Catch: java.lang.Exception -> L33b
            if (r2 == 0) goto L2bb
            java.lang.String r5 = r0.getPlatform()     // Catch: java.lang.Exception -> L33b
            java.lang.String r2 = "microsoft"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r2)     // Catch: java.lang.Exception -> L33b
            if (r2 == 0) goto L2bb
            r6 = 1
            goto L2bc
        L2bb:
            r6 = 0
        L2bc:
            kotlin.Lazy r2 = com.vega.edit.base.utils.ToneUtil.f89175d     // Catch: java.lang.Exception -> L33b
            java.lang.Object r2 = r2.getValue()     // Catch: java.lang.Exception -> L33b
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch: java.lang.Exception -> L33b
            boolean r2 = r2.booleanValue()     // Catch: java.lang.Exception -> L33b
            if (r2 == 0) goto L2d6
            java.lang.String r2 = r0.getPlatform()     // Catch: java.lang.Exception -> L33b
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r4)     // Catch: java.lang.Exception -> L33b
            if (r2 == 0) goto L2d6
            r5 = 1
            goto L2d7
        L2d6:
            r5 = 0
        L2d7:
            java.lang.String r4 = r0.getPlatform()     // Catch: java.lang.Exception -> L33b
            java.lang.String r2 = "moyin"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r2)     // Catch: java.lang.Exception -> L33b
            if (r6 != 0) goto L2e7
            if (r5 != 0) goto L2e7
            if (r2 == 0) goto L34d
        L2e7:
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch: java.lang.Exception -> L33b
            r11.<init>()     // Catch: java.lang.Exception -> L33b
            if (r9 == 0) goto L337
            java.lang.String r2 = "emotion"
            org.json.JSONArray r10 = r9.optJSONArray(r2)     // Catch: java.lang.Exception -> L33b
            if (r10 == 0) goto L337
            int r9 = r10.length()     // Catch: java.lang.Exception -> L33b
            r6 = 0
        L2fb:
            if (r6 >= r9) goto L337
            org.json.JSONObject r2 = r10.getJSONObject(r6)     // Catch: java.lang.Exception -> L33b
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)     // Catch: java.lang.Exception -> L33b
            com.google.gson.Gson r5 = com.vega.core.ext.ExtentionKt.getGson()     // Catch: java.lang.Exception -> L33b
            java.lang.String r4 = r2.toString()     // Catch: java.lang.Exception -> L33b
            java.lang.Class<com.lemon.lv.data.Emotion> r2 = com.lemon.lv.data.Emotion.class
            java.lang.Object r5 = r5.fromJson(r4, r2)     // Catch: java.lang.Exception -> L33b
            com.lemon.lv.data.Emotion r5 = (com.lemon.lv.data.Emotion) r5     // Catch: java.lang.Exception -> L33b
            java.lang.Class<com.lemon.lv.config.ToneEmotionConfigSetting> r2 = com.lemon.lv.config.ToneEmotionConfigSetting.class
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)     // Catch: java.lang.Exception -> L33b
            com.vega.config.IConfig r4 = com.vega.config.ConfigSettingsKt.a(r2)     // Catch: java.lang.Exception -> L33b
            com.lemon.lv.config.ToneEmotionConfig r4 = (com.lemon.lv.config.ToneEmotionConfig) r4     // Catch: java.lang.Exception -> L33b
            java.lang.String r2 = r5.getNameKey()     // Catch: java.lang.Exception -> L33b
            java.lang.String r2 = r4.b(r2)     // Catch: java.lang.Exception -> L33b
            if (r2 == 0) goto L334
            int r2 = r2.length()     // Catch: java.lang.Exception -> L33b
            if (r2 != 0) goto L331
            goto L334
        L331:
            r11.add(r5)     // Catch: java.lang.Exception -> L33b
        L334:
            int r6 = r6 + 1
            goto L2fb
        L337:
            r0.setEmotionList(r11)     // Catch: java.lang.Exception -> L33b
            goto L34d
        L33b:
            r4 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r1 = "effect2ToneType "
            r2.<init>(r1)
            r2.append(r4)
            java.lang.String r1 = r2.toString()
            com.lm.components.logservice.alog.BLog.e(r3, r1)
        L34d:
            java.lang.String r2 = r7.getExtra()
            java.lang.String r1 = "author_name"
            java.lang.String r1 = com.vega.effectplatform.artist.data.EffectExtendKt.K(r2, r1)
            r0.setAuthorName(r1)
            java.lang.String r1 = r0.getVoiceType()
            int r1 = r1.length()
            if (r1 != 0) goto L376
            int r2 = r7.getEffect_type()
            r1 = 100
            if (r2 != r1) goto L376
            java.lang.String r1 = r7.getResource_id()
            r0.setVoiceType(r1)
            r0.setSingCloneTone(r8)
        L376:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.base.utils.ToneUtil.b(com.ss.android.ugc.effectmanager.effect.model.Effect, com.ss.android.ugc.effectmanager.effect.model.EffectCategoryModel, java.util.Map, java.util.Map):com.lemon.lv.data.ToneType");
    }

    public static /* synthetic */ ToneType c(ToneUtil toneUtil, Effect effect, EffectCategoryModel effectCategoryModel, int i) {
        if ((i & 2) != 0) {
            effectCategoryModel = null;
        }
        toneUtil.getClass();
        return b(effect, effectCategoryModel, null, null);
    }

    public static ToneType d(Effect effect, EffectCategoryModel effectCategoryModel) {
        Intrinsics.checkNotNullParameter(effect, "");
        ToneType toneType = new ToneType(null, null, null, null, null, null, EffectExKt.p(effect), EffectExKt.q(effect), EffectExKt.r(effect), null, null, null, effect.getResourceId(), effect.getEffectId(), effect.getId(), effect.getPanel(), effect.getName(), EffectExKt.h0(effect), EffectExKt.R(effect), effect.getTags(), EffectExtendKt.n(effect), EffectExtendKt.I(effect.getExtra(), "is_aiavatar"), 0, EffectExtendKt.z(effect), 0.0d, EffectExtendKt.U(effect), null, false, false, null, null, null, false, null, null, null, null, null, false, null, null, false, -46133697, 1023, null);
        toneType.setIconUrl(DownloadableItemStateKt.b(effect));
        toneType.setToneName(effect.getName());
        if (toneType.getCategoryID().length() == 0 && effectCategoryModel != null) {
            toneType.setCategoryID(EffectExtendKt.k(effectCategoryModel));
            toneType.setCategoryKey(effectCategoryModel.getKey());
        }
        if (toneType.getCategoryName().length() == 0 && effectCategoryModel != null) {
            toneType.setCategoryName(effectCategoryModel.getName());
        }
        try {
            String extra = effect.getExtra();
            if (extra == null) {
                extra = "";
            }
            JSONObject jSONObject = new JSONObject(extra);
            JSONObject jSONObject2 = new JSONObject(jSONObject.getString("tonetype"));
            String strOptString = jSONObject2.optString("rate", "24000");
            Intrinsics.checkNotNullExpressionValue(strOptString, "");
            toneType.setRate(strOptString);
            String strOptString2 = jSONObject2.optString("tts_voice", "");
            Intrinsics.checkNotNullExpressionValue(strOptString2, "");
            toneType.setTtsVoice(strOptString2);
            String strOptString3 = jSONObject2.optString("voice_type", "");
            Intrinsics.checkNotNullExpressionValue(strOptString3, "");
            toneType.setVoiceType(strOptString3);
            toneType.setAiAvatar(true);
            toneType.setReadingSpeed(jSONObject.optDouble("reading_speed", 1.3d));
            String strOptString4 = jSONObject2.optString("platform", "sami");
            Intrinsics.checkNotNullExpressionValue(strOptString4, "");
            toneType.setPlatform(strOptString4);
        } catch (Exception e) {
            BLog.e("ToneUtil", "effect2ToneType " + e);
        }
        if (toneType.getAuthorName().length() == 0) {
            toneType.setAuthorName(EffectExtendKt.K(effect.getExtra(), "author_name"));
        }
        toneType.setVoiceType(EffectExtendKt.B(effect));
        return toneType;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v2, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r6v3, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r6v8, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r9v0, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r9v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r9v3, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0246  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.HashMap e(com.lemon.lv.data.ToneType r14, java.lang.String r15, com.ss.android.ugc.effectmanager.effect.model.EffectCategoryModel r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.util.HashMap r20) throws java.io.IOException {
        /*
            r6 = r17
            java.lang.String r1 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r1)
            r3 = r18
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r1)
            r2 = r19
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.String r5 = "smart_edit"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r15, r5)
            if (r4 == 0) goto L25
            r6 = r5
        L25:
            java.lang.String r4 = "page_from"
            r0.put(r4, r6)
            r11 = 0
            if (r16 == 0) goto L255
            java.lang.String r5 = r16.getKey()
        L31:
            java.lang.String r4 = "clone"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r4)
            if (r4 == 0) goto L249
            java.lang.String r5 = "custom_voice"
        L3b:
            java.lang.String r4 = "category"
            r0.put(r4, r5)
        L40:
            java.lang.String r5 = r14.getCategoryKey()
            java.lang.String r4 = "tone_category"
            r0.put(r4, r5)
            java.lang.String r5 = "tone_category_id"
            java.lang.String r4 = r14.getCategoryID()
            r0.put(r5, r4)
            java.lang.String r5 = "tone"
            java.lang.String r4 = r14.getToneName()
            r0.put(r5, r4)
            java.lang.String r5 = "tone_id"
            java.lang.String r4 = r14.getResourceId()
            r0.put(r5, r4)
            java.lang.String r5 = "tone_picture"
            java.lang.String r4 = r14.getAvatarUrl()
            r0.put(r5, r4)
            kotlin.Pair r4 = r14.getLanguageTag()
            if (r4 == 0) goto L79
            java.lang.Object r5 = r4.getFirst()
            if (r5 != 0) goto L7a
        L79:
            r5 = r1
        L7a:
            java.lang.String r4 = "tone_language_id"
            r0.put(r4, r5)
            kotlin.Pair r4 = r14.getLanguageTag()
            if (r4 == 0) goto L8b
            java.lang.Object r5 = r4.getSecond()
            if (r5 != 0) goto L8c
        L8b:
            r5 = r1
        L8c:
            java.lang.String r4 = "tone_language"
            r0.put(r4, r5)
            java.util.List r8 = r14.getToneStyleTagList()
            if (r8 == 0) goto Lbb
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = ","
            com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$1 r12 = new kotlin.jvm.functions.Function1<com.lemon.lv.data.ToneTypeTagInfo, java.lang.CharSequence>() { // from class: com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$1
                static {
                    /*
                        com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$1 r0 = new com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$1) com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$1.e com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$1.<init>():void");
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final java.lang.CharSequence invoke(com.lemon.lv.data.ToneTypeTagInfo r3) {
                    /*
                        r2 = this;
                        com.lemon.lv.data.ToneTypeTagInfo r3 = (com.lemon.lv.data.ToneTypeTagInfo) r3
                        java.lang.String r1 = ""
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r1)
                        java.lang.String r0 = r3.getName()
                        if (r0 == 0) goto Le
                        r1 = r0
                    Le:
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$1.invoke(java.lang.Object):java.lang.Object");
                }
            }
            r13 = 60
            kotlin.collections.CollectionsKt.i(r8, r9, r10, r11, r12, r13)
            java.lang.String r4 = "tone_style_tag"
            r0.put(r4, r9)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = ","
            com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$2 r12 = new kotlin.jvm.functions.Function1<com.lemon.lv.data.ToneTypeTagInfo, java.lang.CharSequence>() { // from class: com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$2
                static {
                    /*
                        com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$2 r0 = new com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$2
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$2) com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$2.e com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$2
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$2.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$2.<init>():void");
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final java.lang.CharSequence invoke(com.lemon.lv.data.ToneTypeTagInfo r3) {
                    /*
                        r2 = this;
                        com.lemon.lv.data.ToneTypeTagInfo r3 = (com.lemon.lv.data.ToneTypeTagInfo) r3
                        java.lang.String r1 = ""
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r1)
                        java.lang.String r0 = r3.getId()
                        if (r0 == 0) goto Le
                        r1 = r0
                    Le:
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.base.utils.ToneUtil$getClickChangeToneTrackingInfo$2$2.invoke(java.lang.Object):java.lang.Object");
                }
            }
            kotlin.collections.CollectionsKt.i(r8, r9, r10, r11, r12, r13)
            java.lang.String r4 = "tone_style_tag_id"
            r0.put(r4, r9)
        Lbb:
            java.lang.String r5 = "platform"
            java.lang.String r4 = r14.getPlatform()
            r0.put(r5, r4)
            boolean r4 = r14.isVip()
            java.lang.String r5 = "1"
            java.lang.String r10 = "0"
            if (r4 == 0) goto L246
            r6 = r5
        Lcf:
            java.lang.String r4 = "is_vip"
            r0.put(r4, r6)
            java.lang.String r6 = "right_status"
            java.lang.String r4 = r14.getVipStatus()
            r0.put(r6, r4)
            java.lang.String r8 = "enter_from"
            r0.put(r8, r15)
            java.util.List r6 = r14.getTags()
            r7 = 0
            if (r6 == 0) goto L243
            java.lang.String r4 = "cc4b"
            boolean r4 = r6.contains(r4)
        Lef:
            r6 = 1
            if (r4 != 0) goto Lf8
            boolean r4 = r14.isCommercial()
            if (r4 == 0) goto L240
        Lf8:
            r9 = r5
        Lf9:
            java.lang.String r4 = "is_commercial"
            r0.put(r4, r9)
            com.utils.BusinessFilterUtil r4 = com.utils.BusinessFilterUtil.f65451a
            java.lang.String r9 = r4.e()
            java.lang.String r4 = "is_business_filter"
            r0.put(r4, r9)
            java.lang.String r4 = "independent_avatar"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r15, r4)
            if (r4 != 0) goto L119
            java.lang.String r4 = "independent_entrance"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r15, r4)
            if (r4 == 0) goto L23d
        L119:
            java.lang.String r4 = "ai_avatar_edit"
        L11b:
            r0.put(r8, r4)
            java.lang.String r8 = "material_request_id"
            java.lang.String r4 = r14.getRequestId()
            r0.put(r8, r4)
            boolean r4 = r14.isAICloneTone()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)
            java.lang.String r4 = "is_cloned"
            r0.put(r4, r8)
            java.lang.String r8 = "resource_id"
            java.lang.String r4 = r14.getResourceId()
            r0.put(r8, r4)
            java.lang.String r8 = "tone_second_category"
            java.lang.String r4 = r14.getSecondCategoryKey()
            r0.put(r8, r4)
            java.lang.String r4 = "edit_type"
            r0.put(r4, r3)
            com.vega.core.utils.RankReporter r8 = com.vega.core.utils.RankReporter.f79662a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            com.vega.core.utils.RankReportType r3 = com.vega.core.utils.RankReportType.o
            r4.append(r3)
            r3 = 45
            r4.append(r3)
            java.lang.String r3 = r14.getCategoryID()
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            java.lang.String r3 = r14.getEffectId()
            r8.getClass()
            int r3 = com.vega.core.utils.RankReporter.e(r4, r3)
            java.lang.String r4 = java.lang.String.valueOf(r3)
            java.lang.String r3 = "rank"
            r0.put(r3, r4)
            boolean r3 = r14.isVop()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            java.lang.String r3 = "is_ugc"
            r0.put(r3, r4)
            com.vega.edit.base.utils.RecommendCapabilityViewInfo$Companion r4 = com.vega.edit.base.utils.RecommendCapabilityViewInfo.o
            com.vega.edit.base.utils.EditReportManager r3 = com.vega.edit.base.utils.EditReportManager.f88945a
            r3.getClass()
            com.vega.edit.base.utils.RecommendCapabilityViewInfo r3 = com.vega.edit.base.utils.EditReportManager.i1
            r4.getClass()
            java.util.Map r3 = com.vega.edit.base.utils.RecommendCapabilityViewInfo.Companion.d(r3)
            r0.putAll(r3)
            java.lang.String r4 = "right_id"
            java.lang.String r3 = r14.getResourceId()
            r0.put(r4, r3)
            java.lang.String r4 = "right_main_type"
            java.lang.String r3 = "material"
            r0.put(r4, r3)
            java.lang.String r4 = "right_type"
            java.lang.String r3 = "text_to_audio"
            r0.put(r4, r3)
            java.lang.String r4 = "right_category_id"
            java.lang.String r3 = r14.getCategoryID()
            r0.put(r4, r3)
            boolean r3 = r14.isVip()
            if (r3 == 0) goto L23a
            java.lang.String r4 = "vip"
        L1c3:
            java.lang.String r3 = "right_subscribe_type"
            r0.put(r3, r4)
            java.lang.String r4 = "request_id"
            java.lang.String r3 = r14.getRequestId()
            r0.put(r4, r3)
            java.lang.String r3 = "search_id"
            r0.put(r3, r1)
            boolean r1 = com.lemon.lv.data.ToneType.isMultiEmotionTone$default(r14, r7, r6, r11)
            if (r1 == 0) goto L238
        L1dc:
            java.lang.String r1 = "is_style"
            r0.put(r1, r5)
            java.lang.String r1 = "long_text_editor_homepage"
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r15, r1)
            if (r1 != 0) goto L1f1
            java.lang.String r1 = "long_text_editor_tone_detail_page"
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r15, r1)
            if (r1 == 0) goto L1f6
        L1f1:
            java.lang.String r1 = "long_text_editor_page_from"
            r0.put(r1, r2)
        L1f6:
            boolean r1 = r14.isRecentUsed()
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            java.lang.String r2 = com.vega.core.ext.ExtentionKt.getReportStr(r1)
            java.lang.String r1 = "is_recent_use"
            r0.put(r1, r2)
            java.lang.String r1 = r14.getVoiceType()
            java.lang.String r2 = com.vega.core.ext.ExtentionKt.takeIfNotEmpty(r1)
            if (r2 == 0) goto L216
            java.lang.String r1 = "speakerid"
            r0.put(r1, r2)
        L216:
            r1 = r20
            if (r1 == 0) goto L21d
            r0.putAll(r1)
        L21d:
            boolean r1 = r14.isAICloneTone()
            if (r1 == 0) goto L235
            com.lemon.lv.clipmonetize.wrapper.ResourceType r2 = com.lemon.lv.clipmonetize.wrapper.ResourceType.e
        L225:
            com.vega.subscriptionapi.report.BusinessReportEvents r1 = com.vega.subscriptionapi.report.BusinessReportEvents.f131848a
            r3 = 0
            r7 = 30
            r4 = r3
            r5 = r3
            r6 = r3
            java.util.Map r1 = com.vega.subscriptionapi.report.BusinessReportEvents.o(r1, r2, r3, r4, r5, r6, r7)
            r0.putAll(r1)
            return r0
        L235:
            com.lemon.lv.clipmonetize.wrapper.ResourceType r2 = com.lemon.lv.clipmonetize.wrapper.ResourceType.h
            goto L225
        L238:
            r5 = r10
            goto L1dc
        L23a:
            java.lang.String r4 = "free"
            goto L1c3
        L23d:
            r4 = r15
            goto L11b
        L240:
            r9 = r10
            goto Lf9
        L243:
            r4 = 0
            goto Lef
        L246:
            r6 = r10
            goto Lcf
        L249:
            java.lang.String r4 = "all_category"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r4)
            if (r4 == 0) goto L40
            java.lang.String r5 = "text_to_speech"
            goto L3b
        L255:
            r5 = r11
            goto L31
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.base.utils.ToneUtil.e(com.lemon.lv.data.ToneType, java.lang.String, com.ss.android.ugc.effectmanager.effect.model.EffectCategoryModel, java.lang.String, java.lang.String, java.lang.String, java.util.HashMap):java.util.HashMap");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0013 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List f(com.vega.middlebridge.swig.Segment r1) {
        /*
            java.lang.String r0 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            boolean r0 = r1 instanceof com.vega.middlebridge.swig.SegmentText
            if (r0 == 0) goto L14
            com.vega.middlebridge.swig.SegmentText r1 = (com.vega.middlebridge.swig.SegmentText) r1
            if (r1 == 0) goto L27
            com.vega.middlebridge.swig.VectorOfString r0 = com.vega.ve.utils.DraftExpandKt.w(r1)
        L11:
            if (r0 == 0) goto L27
        L13:
            return r0
        L14:
            boolean r0 = r1 instanceof com.vega.middlebridge.swig.SegmentTextTemplate
            if (r0 == 0) goto L27
            com.vega.middlebridge.swig.SegmentTextTemplate r1 = (com.vega.middlebridge.swig.SegmentTextTemplate) r1
            if (r1 == 0) goto L27
            com.vega.middlebridge.swig.MaterialTextTemplate r0 = r1.p()
            if (r0 == 0) goto L27
            com.vega.middlebridge.swig.VectorOfString r0 = r0.s()
            goto L11
        L27:
            java.util.List r0 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()
            goto L13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.base.utils.ToneUtil.f(com.vega.middlebridge.swig.Segment):java.util.List");
    }

    public static Map g(ToneUtil toneUtil, Segment segment) {
        toneUtil.getClass();
        Intrinsics.checkNotNullParameter(segment, "");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        boolean zI = DraftExpandKt.I(segment);
        int i = 1;
        boolean z = !DraftExpandKt.G(segment).isEmpty();
        boolean zH = DraftExpandKt.H(segment);
        linkedHashMap.put("is_vc_sound", Integer.valueOf((z || zI) ? 1 : 0));
        if (!zH && !zI) {
            i = 0;
        }
        linkedHashMap.put("is_cloned", Integer.valueOf(i));
        if (z) {
            linkedHashMap.put("vc_type", zI ? "sing_vc" : "speak_vc");
            if (zI) {
                linkedHashMap.put("vc_clone_type", "sing_vc");
            } else if (zH) {
                linkedHashMap.put("vc_clone_type", "speak_vc");
            }
        }
        return linkedHashMap;
    }

    public static String h(Effect effect) {
        Object objCreateFailure;
        Intrinsics.checkNotNullParameter(effect, "");
        if (effect.getEffect_type() == 100) {
            return effect.getResource_id();
        }
        try {
            String extra = effect.getExtra();
            if (extra == null) {
                extra = "";
            }
            objCreateFailure = new JSONObject(new JSONObject(extra).optString("tonetype")).optString("voice_type", "");
            Result.m17090constructorimpl(objCreateFailure);
        } catch (Throwable th) {
            objCreateFailure = kotlin.ResultKt.createFailure(th);
            Result.m17090constructorimpl(objCreateFailure);
        }
        Object obj = Result.m17096isFailureimpl(objCreateFailure) ? "" : objCreateFailure;
        Intrinsics.checkNotNull(obj);
        return (String) obj;
    }

    public static void i(MarqueeTextView marqueeTextView, View view) {
        Intrinsics.checkNotNullParameter(marqueeTextView, "");
        Intrinsics.checkNotNullParameter(view, "");
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(view.getResources(), R.drawable.qdz);
        Intrinsics.checkNotNullExpressionValue(bitmapDecodeResource, "");
        SizeUtil.f106663a.getClass();
        Bitmap bitmapDecodeResource2 = BitmapFactory.decodeResource(view.getResources(), R.drawable.qda);
        Intrinsics.checkNotNullExpressionValue(bitmapDecodeResource2, "");
        marqueeTextView.setContent(CollectionsKt__CollectionsKt.listOf((Object[]) new MarqueeTextView.IDrawableElement[]{new MarqueeTextView.Image(bitmapDecodeResource), new MarqueeTextView.Space(SizeUtil.b(3.5f)), new MarqueeTextView.Text(""), new MarqueeTextView.Space(SizeUtil.b(3.5f)), new MarqueeTextView.Image(bitmapDecodeResource2)}));
        marqueeTextView.setImageTopSpace(SizeUtil.b(2.0f));
        marqueeTextView.setTextSize(SizeUtil.b(10.0f));
        marqueeTextView.setTextTopSpace(SizeUtil.b(1.5f));
        marqueeTextView.setSpeed(SizeUtil.b(0.5f));
    }

    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean j(com.vega.middlebridge.swig.Segment r4) {
        /*
            java.lang.String r2 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r2)
            boolean r0 = r4 instanceof com.vega.middlebridge.swig.SegmentText
            r3 = 0
            r1 = 1
            if (r0 == 0) goto L1b
            com.vega.middlebridge.swig.SegmentText r4 = (com.vega.middlebridge.swig.SegmentText) r4
            com.vega.middlebridge.swig.VectorOfString r0 = com.vega.ve.utils.DraftExpandKt.w(r4)
            boolean r0 = r0.isEmpty()
            r0 = r0 ^ 1
            if (r0 != r1) goto L1a
        L19:
            r3 = 1
        L1a:
            return r3
        L1b:
            boolean r0 = r4 instanceof com.vega.middlebridge.swig.SegmentTextTemplate
            if (r0 == 0) goto L1a
            com.vega.middlebridge.swig.SegmentTextTemplate r4 = (com.vega.middlebridge.swig.SegmentTextTemplate) r4
            com.vega.middlebridge.swig.MaterialTextTemplate r0 = r4.p()
            com.vega.middlebridge.swig.VectorOfTextBindEffectInfo r1 = r0.q()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L46
        L32:
            com.vega.middlebridge.swig.MaterialTextTemplate r0 = r4.p()
            com.vega.middlebridge.swig.VectorOfString r0 = r0.s()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            boolean r0 = r0.isEmpty()
            r0 = r0 ^ 1
            if (r0 == 0) goto L1a
            goto L19
        L46:
            java.util.Iterator r1 = r1.iterator()
        L4a:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L32
            java.lang.Object r0 = r1.next()
            com.vega.middlebridge.swig.TextBindEffectInfo r0 = (com.vega.middlebridge.swig.TextBindEffectInfo) r0
            com.vega.middlebridge.swig.MaterialText r0 = r0.i()
            com.vega.middlebridge.swig.VectorOfString r0 = r0.f0()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            boolean r0 = r0.isEmpty()
            r0 = r0 ^ 1
            if (r0 == 0) goto L4a
            goto L19
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.base.utils.ToneUtil.j(com.vega.middlebridge.swig.Segment):boolean");
    }

    public static void k(String str, String str2, String str3, String str4) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        ReportManagerWrapper reportManagerWrapper = ReportManagerWrapper.INSTANCE;
        Map<String, String> mapMutableMapOf = MapsKt__MapsKt.mutableMapOf(TuplesKt.to("vc_clone_type", str), TuplesKt.to("status", str2));
        if (str3 != null && str3.length() != 0) {
            mapMutableMapOf.put("error_code", str3);
        }
        if (str4 != null && str4.length() != 0) {
            mapMutableMapOf.put("error_message", str4);
        }
        reportManagerWrapper.onEvent("audio_vc_change_tone_prelisten_status", mapMutableMapOf);
    }

    public static void l(int i, String str, Integer num, Integer num2) {
        Intrinsics.checkNotNullParameter(str, "");
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", Integer.valueOf(i));
        map.put("tone", str);
        if (num != null) {
            map.put("time", Integer.valueOf(num.intValue()));
        }
        if (num2 != null) {
            map.put("cache_status", Integer.valueOf(num2.intValue()));
        }
        ReportManagerWrapper.INSTANCE.onEvent("audition_status", map);
    }

    public static void m(EffectCategoryModel effectCategoryModel, String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        if (str.length() == 0) {
            str = "none";
        }
        if (str2.length() == 0) {
            str2 = "none";
        }
        n("click", effectCategoryModel, str, str2, z);
    }

    public static void n(String str, EffectCategoryModel effectCategoryModel, String str2, String str3, boolean z) {
        String name;
        ReportManagerWrapper reportManagerWrapper = ReportManagerWrapper.INSTANCE;
        Pair[] pairArr = new Pair[3];
        pairArr[0] = TuplesKt.to("page_from", "text_to_speech");
        pairArr[1] = TuplesKt.to("action_type", str);
        if (effectCategoryModel == null || (name = effectCategoryModel.getName()) == null) {
            name = "";
        }
        pairArr[2] = TuplesKt.to("scene", name);
        Map<String, String> mapMutableMapOf = MapsKt__MapsKt.mutableMapOf(pairArr);
        if (Intrinsics.areEqual(str, "click")) {
            mapMutableMapOf.put("click_group", str2);
            mapMutableMapOf.put("click_filter", str3);
            mapMutableMapOf.put("click_selected", z ? ProfileManager.VERSION : "0");
        }
        reportManagerWrapper.onEvent("change_tone_filter_detail", mapMutableMapOf);
    }

    public static void p(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        HashMap<String, Object> map = new HashMap<>();
        map.put("action", str);
        map.put("page_from", "text_to_speech");
        map.put("tone", str2);
        ReportManagerWrapper.INSTANCE.onEvent("click_audition", map);
    }

    public static void q(ToneType toneType, String str, EffectCategoryModel effectCategoryModel, String str2, String str3, String str4, HashMap map) {
        Intrinsics.checkNotNullParameter(toneType, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        ReportManagerWrapper.INSTANCE.onEvent("click_change_tone", e(toneType, str, effectCategoryModel, str2, str3, str4, map));
    }

    public static void r(EffectCategoryModel effectCategoryModel) {
        Intrinsics.checkNotNullParameter(effectCategoryModel, "");
        String key = effectCategoryModel.getKey();
        ReportManagerWrapper.INSTANCE.onEvent("click_change_tone_option", MapsKt__MapsJVMKt.mapOf(TuplesKt.to("category", Intrinsics.areEqual(key, "clone") ? "custom_voice" : Intrinsics.areEqual(key, "all_category") ? "text_to_speech" : "Unknown")));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r10v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r10v2, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r10v5, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r10v6, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    public static void s(ToneUtil toneUtil, ToneType toneType, String str, String str2, EffectCategoryModel effectCategoryModel, String str3, String str4, int i, boolean z, int i2, String str5, boolean z2, int i3) throws IOException {
        Object first;
        Object second;
        Object obj = str5;
        Object obj2 = str3;
        Object obj3 = str2;
        if ((i3 & 4) != 0) {
            obj3 = "";
        }
        if ((i3 & 8) != 0) {
            effectCategoryModel = null;
        }
        if ((i3 & 16) != 0) {
            obj2 = "";
        }
        if ((i3 & 64) != 0) {
            i = -1;
        }
        if ((i3 & NotificationCompat.FLAG_HIGH_PRIORITY) != 0) {
            z = false;
        }
        int i4 = (i3 & 256) == 0 ? i2 : -1;
        if ((i3 & 512) != 0) {
            obj = "";
        }
        if ((i3 & SpeechEngineDefines.TTS_WORK_MODE_BOTH) != 0) {
            z2 = false;
        }
        toneUtil.getClass();
        Intrinsics.checkNotNullParameter(toneType, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(obj3, "");
        Intrinsics.checkNotNullParameter(obj2, "");
        Intrinsics.checkNotNullParameter(str4, "");
        Intrinsics.checkNotNullParameter(obj, "");
        if (!Intrinsics.areEqual((Object) null, Boolean.TRUE)) {
            Set set = (Set) ((HashMap) b).get(ExtentionKt.isNotNullOrEmpty(toneType.getSecondCategoryName()) ? toneType.getSecondCategoryName() : toneType.getCategoryName());
            if (set != null && set.contains(str)) {
                return;
            }
        }
        HashMap map = (HashMap) b;
        Set hashSet = (Set) map.get(toneType.getCategoryName());
        if (hashSet == null) {
            hashSet = new HashSet();
        }
        hashSet.add(str);
        map.put(toneType.getCategoryName(), hashSet);
        HashMap<String, Object> map2 = new HashMap<>();
        String key = effectCategoryModel != null ? effectCategoryModel.getKey() : null;
        map2.put("category", Intrinsics.areEqual(key, "clone") ? "custom_voice" : Intrinsics.areEqual(key, "all_category") ? "text_to_speech" : "");
        map2.put("tone_category", toneType.getCategoryKey());
        map2.put("tone_category_id", toneType.getCategoryID());
        map2.put("tone", toneType.getToneName());
        map2.put("tone_id", toneType.getResourceId());
        map2.put("tone_picture", toneType.getAvatarUrl());
        Pair<String, String> languageTag = toneType.getLanguageTag();
        if (languageTag == null || (first = languageTag.getFirst()) == null) {
            first = "";
        }
        map2.put("tone_language_id", first);
        Pair<String, String> languageTag2 = toneType.getLanguageTag();
        if (languageTag2 == null || (second = languageTag2.getSecond()) == null) {
            second = "";
        }
        map2.put("tone_language", second);
        List<ToneTypeTagInfo> toneStyleTagList = toneType.getToneStyleTagList();
        if (toneStyleTagList != null) {
            StringBuilder sb = new StringBuilder();
            CollectionsKt.i(toneStyleTagList, sb, ",", null, new Function1<ToneTypeTagInfo, CharSequence>() { // from class: com.vega.edit.base.utils.ToneUtil$reportShowChangeTone$2$1
                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final CharSequence invoke(ToneTypeTagInfo toneTypeTagInfo) {
                    ToneTypeTagInfo toneTypeTagInfo2 = toneTypeTagInfo;
                    Intrinsics.checkNotNullParameter(toneTypeTagInfo2, "");
                    String name = toneTypeTagInfo2.getName();
                    return name != null ? name : "";
                }
            }, 60);
            map2.put("tone_style_tag", sb);
            StringBuilder sb2 = new StringBuilder();
            CollectionsKt.i(toneStyleTagList, sb2, ",", null, new Function1<ToneTypeTagInfo, CharSequence>() { // from class: com.vega.edit.base.utils.ToneUtil$reportShowChangeTone$2$2
                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final CharSequence invoke(ToneTypeTagInfo toneTypeTagInfo) {
                    ToneTypeTagInfo toneTypeTagInfo2 = toneTypeTagInfo;
                    Intrinsics.checkNotNullParameter(toneTypeTagInfo2, "");
                    String id = toneTypeTagInfo2.getId();
                    return id != null ? id : "";
                }
            }, 60);
            map2.put("tone_style_tag_id", sb2);
        }
        map2.put("platform", toneType.getPlatform());
        boolean zIsVip = toneType.isVip();
        Object obj4 = ProfileManager.VERSION;
        map2.put("is_vip", zIsVip ? ProfileManager.VERSION : "0");
        map2.put("right_status", toneType.getVipStatus());
        List<String> tags = toneType.getTags();
        map2.put("is_commercial", ((tags == null || !tags.contains("cc4b")) && !toneType.isCommercial()) ? "0" : ProfileManager.VERSION);
        map2.put("is_business_filter", BusinessFilterUtil.f65451a.e());
        map2.put("enter_from", (Intrinsics.areEqual(obj3, "independent_avatar") || Intrinsics.areEqual(obj3, "independent_entrance")) ? "ai_avatar_edit" : obj3);
        if (Intrinsics.areEqual(obj3, "smart_edit")) {
            obj2 = "smart_edit";
        }
        map2.put("page_from", obj2);
        map2.put("divide_by", z ? "audio_module" : "tts");
        map2.put("material_request_id", toneType.getRequestId());
        RankReporter rankReporter = RankReporter.f79662a;
        String str6 = RankReportType.o + '-' + toneType.getCategoryID();
        String effectId = toneType.getEffectId();
        rankReporter.getClass();
        map2.put("rank", String.valueOf(RankReporter.e(str6, effectId)));
        map2.put("edit_type", str4);
        map2.put("tone_second_category", toneType.getSecondCategoryKey());
        map2.put("is_cloned", Integer.valueOf(toneType.isAICloneTone() ? 1 : 0));
        Object objTakeIfNotEmpty = ExtentionKt.takeIfNotEmpty(toneType.getVoiceType());
        if (objTakeIfNotEmpty != null) {
            map2.put("speakerid", objTakeIfNotEmpty);
        }
        map2.put("is_ugc", Integer.valueOf(toneType.isVop() ? 1 : 0));
        if (i >= 0) {
            map2.put("clone_tone_cnt", Integer.valueOf(i));
        }
        if (i4 >= 0) {
            map2.put("ugc_tone_cnt", Integer.valueOf(i4));
        }
        if (Intrinsics.areEqual(obj3, "long_text_editor_homepage") || Intrinsics.areEqual(obj3, "long_text_editor_tone_detail_page")) {
            map2.put("long_text_editor_page_from", obj);
        }
        if (!ToneType.isMultiEmotionTone$default(toneType, 0, 1, null)) {
            obj4 = "0";
        }
        map2.put("is_style", obj4);
        map2.put("is_recent_use", ExtentionKt.getReportStr(Boolean.valueOf(toneType.isRecentUsed())));
        HashMap map3 = new HashMap();
        map3.put("right_id", toneType.getResourceId());
        map3.put("right_main_type", "material");
        map3.put("right_type", "text_to_audio");
        map3.put("right_panel_type", "text_to_audio");
        map3.put("right_category_id", toneType.getCategoryID());
        map3.put("right_subscribe_type", toneType.isVip() ? "vip" : "free");
        map3.put("request_id", toneType.getRequestId());
        map3.put("right_request_id", toneType.getRequestId());
        map3.put("search_id", "");
        map2.putAll(map3);
        map2.putAll(BusinessReportEvents.o(BusinessReportEvents.f131848a, toneType.isAICloneTone() ? ResourceType.e : ResourceType.h, null, null, null, null, 30));
        ReportManagerWrapper.INSTANCE.onEvent("show_change_tone", map2);
        if (!toneType.isVip() || z2) {
            return;
        }
        RecommendCapabilityViewInfo.Companion companion = RecommendCapabilityViewInfo.o;
        EditReportManager.f88945a.getClass();
        RecommendCapabilityViewInfo recommendCapabilityViewInfo = EditReportManager.i1;
        companion.getClass();
        map3.putAll(RecommendCapabilityViewInfo.Companion.d(recommendCapabilityViewInfo));
        Reporter.c(Reporter.f88372a, "show", "change_tone", toneType.getEffectId(), toneType.getName(), null, map3, null, null, 464);
    }

    public static void t(Effect effect, ToneType toneType, String str, String str2, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(effect, "");
        Intrinsics.checkNotNullParameter(toneType, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        Intrinsics.checkNotNullParameter(str5, "");
        HashMap map = new HashMap();
        map.put("right_id", effect.getResourceId());
        map.put("right_main_type", "material");
        map.put("right_type", "audio_effect");
        map.put("right_category_id", EffectExKt.p(effect));
        map.put("right_subscribe_type", toneType.isVip() ? "vip" : "free");
        map.put("request_id", EffectExtendKt.z(effect));
        map.put("search_id", "");
        ReportManagerWrapper reportManagerWrapper = ReportManagerWrapper.INSTANCE;
        Pair[] pairArr = new Pair[17];
        pairArr[0] = TuplesKt.to("is_vc_sound", 1);
        pairArr[1] = TuplesKt.to("tone", effect.getName());
        pairArr[2] = TuplesKt.to("is_vip", EffectExKt.h0(effect) ? ProfileManager.VERSION : "0");
        pairArr[3] = TuplesKt.to("right_status", EffectExKt.R(effect));
        pairArr[4] = TuplesKt.to("rank", effect.toString());
        pairArr[5] = TuplesKt.to("tone_category", toneType.getCategoryKey());
        pairArr[6] = TuplesKt.to("tone_category_id", toneType.getCategoryID());
        pairArr[7] = TuplesKt.to("tone_id", effect.getResourceId());
        pairArr[8] = TuplesKt.to("resource_id", effect.getResourceId());
        pairArr[9] = TuplesKt.to("material_request_id", EffectExtendKt.z(effect));
        pairArr[10] = TuplesKt.to("type", str);
        pairArr[11] = TuplesKt.to("is_cloned", 1);
        pairArr[12] = TuplesKt.to("tone_second_category", "clone");
        pairArr[13] = TuplesKt.to("vc_type", str3);
        pairArr[14] = TuplesKt.to("vc_clone_type", str4);
        pairArr[15] = TuplesKt.to("is_recent_use", ExtentionKt.getReportStr(Boolean.valueOf(EffectExtendKt.R(effect))));
        pairArr[16] = TuplesKt.to("edit_type", str5);
        HashMap<String, Object> mapHashMapOf = MapsKt__MapsKt.hashMapOf(pairArr);
        mapHashMapOf.put("enter_from", str2);
        mapHashMapOf.putAll(map);
        mapHashMapOf.putAll(BusinessReportEvents.o(BusinessReportEvents.f131848a, ResourceType.h, null, null, null, null, 30));
        reportManagerWrapper.onEvent("show_change_voice_tone", mapHashMapOf);
    }

    public static void u(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        Intrinsics.checkNotNullParameter(str5, "");
        Intrinsics.checkNotNullParameter(str6, "");
        Intrinsics.checkNotNullParameter(str7, "");
        Intrinsics.checkNotNullParameter(str8, "");
        Intrinsics.checkNotNullParameter(str9, "");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action_type", str);
        linkedHashMap.put("tone_category", str2);
        linkedHashMap.put("tone_category_id", str3);
        linkedHashMap.put("tone", str4);
        linkedHashMap.put("tone_id", str5);
        linkedHashMap.put("is_vip", str6);
        linkedHashMap.put("enter_from", str7);
        linkedHashMap.put("style_id", str8);
        linkedHashMap.put("tone_second_category", str9);
        ReportManagerWrapper.INSTANCE.onEvent("tone_detail_change", (Map<String, String>) linkedHashMap);
    }

    public static /* synthetic */ void v(ToneUtil toneUtil, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        toneUtil.getClass();
        u(str, str2, str3, str4, str5, str6, str7, "", str8);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    public static Effect w(ToneType toneType) {
        Intrinsics.checkNotNullParameter(toneType, "");
        Effect effect = new Effect(null, 1, 0 == true ? 1 : 0);
        effect.setId(toneType.getId());
        effect.setEffectId(toneType.getEffectId());
        effect.setEffect_id(toneType.getEffectId());
        EffectExKt.s0(effect, toneType.getCategoryID());
        toneType.getCategoryID();
        EffectExKt.s0(effect, toneType.getCategoryID());
        toneType.getCategoryName();
        EffectExKt.u0(effect, toneType.getCategoryName());
        toneType.getResourceId();
        effect.setResourceId(toneType.getResourceId());
        effect.getIconUrl().setUrlList(CollectionsKt__CollectionsJVMKt.listOf(toneType.getIconUrl()));
        effect.setName(toneType.getName());
        effect.setPanel(toneType.getPanel());
        EffectExKt.K0(effect, toneType.isVip());
        effect.setExtra(toneType.getExtra());
        Intrinsics.checkNotNullParameter(toneType.getVipStatus(), "");
        return effect;
    }
}