package com.lemon.lv.data;

import androidx.core.app.NotificationCompat;
import com.bytedance.speech.speechengine.SpeechEngineDefines;
import com.service.audio.CloneToneLanguageConfig;
import com.xt.retouch.abtest.bean.BusinessPhotoTemplateOptEntity;
import java.io.Serializable;
import java.util.List;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* loaded from: classes31.dex */
public final class ToneType implements Serializable {
    public static final Companion Companion = new Companion();
    public String auditionAudioZhVid;
    public String authorName;
    public String avatarUrl;
    public String categoryID;
    public String categoryKey;
    public String categoryName;
    public String defaultAuditionText;
    public String effectId;
    public List<Emotion> emotionList;
    public boolean enableWordTimeInfo;
    public String extra;
    public String iconUrl;
    public String id;
    public boolean isAICloneTone;
    public boolean isAiAvatar;
    public boolean isCommercial;
    public boolean isRecentUsed;
    public boolean isSingCloneTone;
    public boolean isUgc;
    public boolean isVip;
    public boolean isVop;
    public Pair<String, String> languageTag;
    public String mockToneInfo;
    public String name;
    public String originalAudioVid;
    public String panel;
    public String platform;
    public String rate;
    public double readingSpeed;
    public String requestId;
    public final String resourceId;
    public String secondCategoryID;
    public String secondCategoryKey;
    public String secondCategoryName;
    public List<String> tags;
    public String toneModelType;
    public String toneName;
    public List<ToneTypeTagInfo> toneStyleTagList;
    public String ttsVoice;
    public String vipStatus;
    public int voiceGender;
    public String voiceType;

    /* loaded from: classes18.dex */
    public static final class Companion {
    }

    public ToneType(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, boolean z, String str18, List<String> list, boolean z2, boolean z3, int i, String str19, double d2, boolean z4, String str20, boolean z5, boolean z6, String str21, String str22, String str23, boolean z7, List<Emotion> list2, String str24, String str25, Pair<String, String> pair, List<ToneTypeTagInfo> list3, boolean z8, String str26, String str27, boolean z9) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        Intrinsics.checkNotNullParameter(str5, "");
        Intrinsics.checkNotNullParameter(str6, "");
        Intrinsics.checkNotNullParameter(str7, "");
        Intrinsics.checkNotNullParameter(str8, "");
        Intrinsics.checkNotNullParameter(str9, "");
        Intrinsics.checkNotNullParameter(str10, "");
        Intrinsics.checkNotNullParameter(str11, "");
        Intrinsics.checkNotNullParameter(str12, "");
        Intrinsics.checkNotNullParameter(str13, "");
        Intrinsics.checkNotNullParameter(str14, "");
        Intrinsics.checkNotNullParameter(str15, "");
        Intrinsics.checkNotNullParameter(str16, "");
        Intrinsics.checkNotNullParameter(str17, "");
        Intrinsics.checkNotNullParameter(str18, "");
        Intrinsics.checkNotNullParameter(str19, "");
        Intrinsics.checkNotNullParameter(str20, "");
        Intrinsics.checkNotNullParameter(str22, "");
        Intrinsics.checkNotNullParameter(str23, "");
        Intrinsics.checkNotNullParameter(str24, "");
        this.iconUrl = str;
        this.authorName = str2;
        this.voiceType = str3;
        this.rate = str4;
        this.ttsVoice = str5;
        this.toneName = str6;
        this.categoryID = str7;
        this.categoryKey = str8;
        this.categoryName = str9;
        this.secondCategoryID = str10;
        this.secondCategoryKey = str11;
        this.secondCategoryName = str12;
        this.resourceId = str13;
        this.effectId = str14;
        this.id = str15;
        this.panel = str16;
        this.name = str17;
        this.isVip = z;
        this.vipStatus = str18;
        this.tags = list;
        this.isCommercial = z2;
        this.isAiAvatar = z3;
        this.voiceGender = i;
        this.requestId = str19;
        this.readingSpeed = d2;
        this.isUgc = z4;
        this.platform = str20;
        this.isAICloneTone = z5;
        this.enableWordTimeInfo = z6;
        this.defaultAuditionText = str21;
        this.toneModelType = str22;
        this.extra = str23;
        this.isVop = z7;
        this.emotionList = list2;
        this.avatarUrl = str24;
        this.mockToneInfo = str25;
        this.languageTag = pair;
        this.toneStyleTagList = list3;
        this.isSingCloneTone = z8;
        this.originalAudioVid = str26;
        this.auditionAudioZhVid = str27;
        this.isRecentUsed = z9;
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x015e: CONSTRUCTOR 
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0050: ARITH (r89v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? ("") : (r46v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0058: ARITH (r89v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? ("") : (r47v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x005e: ARITH (r89v0 int) & (4 int) A[WRAPPED]) != (0 int)) ? ("") : (r48v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0064: ARITH (r89v0 int) & (8 int) A[WRAPPED]) != (0 int)) ? ("") : (r49v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x006a: ARITH (r89v0 int) & (16 int) A[WRAPPED]) != (0 int)) ? ("") : (r50v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0070: ARITH (r89v0 int) & (32 int) A[WRAPPED]) != (0 int)) ? ("") : (r51v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0076: ARITH (r89v0 int) & (64 int) A[WRAPPED]) != (0 int)) ? ("") : (r52v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x007c: ARITH (r89v0 int) & (wrap:??:SGET  A[WRAPPED] androidx.core.app.NotificationCompat.FLAG_HIGH_PRIORITY int) A[WRAPPED]) != (0 int)) ? ("") : (r53v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0082: ARITH (r89v0 int) & (256 int) A[WRAPPED]) != (0 int)) ? ("") : (r54v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0088: ARITH (r89v0 int) & (512 int) A[WRAPPED]) != (0 int)) ? ("") : (r55v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x008e: ARITH (r89v0 int) & (1024 int) A[WRAPPED]) != (0 int)) ? ("") : (r56v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0094: ARITH (r89v0 int) & (wrap:??:SGET  A[WRAPPED] com.bytedance.speech.speechengine.SpeechEngineDefines.ASR_WORK_MODE_OFFLINE int) A[WRAPPED]) != (0 int)) ? ("") : (r57v0 java.lang.String))
      (r58v0 java.lang.String)
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x009a: ARITH (r89v0 int) & (8192 int) A[WRAPPED]) != (0 int)) ? ("") : (r59v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x00a0: ARITH (r89v0 int) & (16384 int) A[WRAPPED]) != (0 int)) ? ("") : (r60v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x00a9: ARITH (32768 int) & (r89v0 int) A[WRAPPED]) != (0 int)) ? ("") : (r61v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x00b0: ARITH (65536 int) & (r89v0 int) A[WRAPPED]) != (0 int)) ? ("") : (r62v0 java.lang.String))
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x00b7: ARITH (131072 int) & (r89v0 int) A[WRAPPED]) != (0 int)) ? false : (r63v0 boolean))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x00be: ARITH (262144 int) & (r89v0 int) A[WRAPPED]) != (0 int)) ? ("free") : (r64v0 java.lang.String))
      (wrap:java.util.List:?: TERNARY null = ((wrap:int:0x00c5: ARITH (524288 int) & (r89v0 int) A[WRAPPED]) != (0 int)) ? (null java.util.List) : (r65v0 java.util.List))
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x00ce: ARITH (1048576 int) & (r89v0 int) A[WRAPPED]) != (0 int)) ? false : (r66v0 boolean))
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x00d5: ARITH (2097152 int) & (r89v0 int) A[WRAPPED]) != (0 int)) ? false : (r67v0 boolean))
      (wrap:int:?: TERNARY null = ((wrap:int:0x00dc: ARITH (4194304 int) & (r89v0 int) A[WRAPPED]) != (0 int)) ? (2 int) : (r68v0 int))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x00e3: ARITH (8388608 int) & (r89v0 int) A[WRAPPED]) != (0 int)) ? ("") : (r69v0 java.lang.String))
      (wrap:double:?: TERNARY null = ((wrap:int:0x00ea: ARITH (16777216 int) & (r89v0 int) A[WRAPPED]) != (0 int)) ? (1.0d double) : (r70v0 double))
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x00f1: ARITH (33554432 int) & (r89v0 int) A[WRAPPED]) != (0 int)) ? false : (r72v0 boolean))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x00f8: ARITH (67108864 int) & (r89v0 int) A[WRAPPED]) != (0 int)) ? ("sami") : (r73v0 java.lang.String))
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x00ff: ARITH (134217728 int) & (r89v0 int) A[WRAPPED]) != (0 int)) ? false : (r74v0 boolean))
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x0106: ARITH (268435456 int) & (r89v0 int) A[WRAPPED]) != (0 int)) ? false : (r75v0 boolean))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x010d: ARITH (536870912 int) & (r89v0 int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r76v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0114: ARITH (1073741824 int) & (r89v0 int) A[WRAPPED]) != (0 int)) ? ("normal_tts") : (r77v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x011b: ARITH (r89v0 int) & (Integer.MIN_VALUE int) A[WRAPPED]) != (0 int)) ? ("") : (r78v0 java.lang.String))
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x0122: ARITH (r90v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? false : (r79v0 boolean))
      (wrap:java.util.List:?: TERNARY null = ((wrap:int:0x0128: ARITH (r90v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? (null java.util.List) : (r80v0 java.util.List))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x012e: ARITH (r90v0 int) & (4 int) A[WRAPPED]) == (0 int)) ? (r81v0 java.lang.String) : (""))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0132: ARITH (r90v0 int) & (8 int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r82v0 java.lang.String))
      (wrap:kotlin.Pair:?: TERNARY null = ((wrap:int:0x0138: ARITH (r90v0 int) & (16 int) A[WRAPPED]) != (0 int)) ? (null kotlin.Pair) : (r83v0 kotlin.Pair))
      (wrap:java.util.List:?: TERNARY null = ((wrap:int:0x013e: ARITH (r90v0 int) & (32 int) A[WRAPPED]) != (0 int)) ? (null java.util.List) : (r84v0 java.util.List))
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x0144: ARITH (r90v0 int) & (64 int) A[WRAPPED]) != (0 int)) ? false : (r85v0 boolean))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x014a: ARITH (r90v0 int) & (wrap:??:SGET  A[WRAPPED] androidx.core.app.NotificationCompat.FLAG_HIGH_PRIORITY int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r86v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0150: ARITH (r90v0 int) & (256 int) A[WRAPPED]) == (0 int)) ? (r87v0 java.lang.String) : (null java.lang.String))
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x0154: ARITH (r90v0 int) & (512 int) A[WRAPPED]) != (0 int)) ? false : (r88v0 boolean))
     A[MD:(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String, java.util.List<java.lang.String>, boolean, boolean, int, java.lang.String, double, boolean, java.lang.String, boolean, boolean, java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List<com.lemon.lv.data.Emotion>, java.lang.String, java.lang.String, kotlin.Pair<java.lang.String, java.lang.String>, java.util.List<com.lemon.lv.data.ToneTypeTagInfo>, boolean, java.lang.String, java.lang.String, boolean):void (m)] call: com.lemon.lv.data.ToneType.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String, java.util.List, boolean, boolean, int, java.lang.String, double, boolean, java.lang.String, boolean, boolean, java.lang.String, java.lang.String, java.lang.String, boolean, java.util.List, java.lang.String, java.lang.String, kotlin.Pair, java.util.List, boolean, java.lang.String, java.lang.String, boolean):void type: THIS */
    public /* synthetic */ ToneType(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, boolean z, String str18, List list, boolean z2, boolean z3, int i, String str19, double d2, boolean z4, String str20, boolean z5, boolean z6, String str21, String str22, String str23, boolean z7, List list2, String str24, String str25, Pair pair, List list3, boolean z8, String str26, String str27, boolean z9, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) != 0 ? "" : str4, (i2 & 16) != 0 ? "" : str5, (i2 & 32) != 0 ? "" : str6, (i2 & 64) != 0 ? "" : str7, (i2 & NotificationCompat.FLAG_HIGH_PRIORITY) != 0 ? "" : str8, (i2 & 256) != 0 ? "" : str9, (i2 & 512) != 0 ? "" : str10, (i2 & 1024) != 0 ? "" : str11, (i2 & SpeechEngineDefines.ASR_WORK_MODE_OFFLINE) != 0 ? "" : str12, str13, (i2 & 8192) != 0 ? "" : str14, (i2 & 16384) != 0 ? "" : str15, (32768 & i2) != 0 ? "" : str16, (65536 & i2) != 0 ? "" : str17, (131072 & i2) != 0 ? false : z, (262144 & i2) != 0 ? "free" : str18, (524288 & i2) != 0 ? null : list, (1048576 & i2) != 0 ? false : z2, (2097152 & i2) != 0 ? false : z3, (4194304 & i2) != 0 ? 2 : i, (8388608 & i2) != 0 ? "" : str19, (16777216 & i2) != 0 ? 1.0d : d2, (33554432 & i2) != 0 ? false : z4, (67108864 & i2) != 0 ? "sami" : str20, (134217728 & i2) != 0 ? false : z5, (268435456 & i2) != 0 ? false : z6, (536870912 & i2) != 0 ? null : str21, (1073741824 & i2) != 0 ? "normal_tts" : str22, (i2 & Integer.MIN_VALUE) != 0 ? "" : str23, (i3 & 1) != 0 ? false : z7, (i3 & 2) != 0 ? null : list2, (i3 & 4) == 0 ? str24 : "", (i3 & 8) != 0 ? null : str25, (i3 & 16) != 0 ? null : pair, (i3 & 32) != 0 ? null : list3, (i3 & 64) != 0 ? false : z8, (i3 & NotificationCompat.FLAG_HIGH_PRIORITY) != 0 ? null : str26, (i3 & 256) == 0 ? str27 : null, (i3 & 512) != 0 ? false : z9);
    }

    public static /* synthetic */ boolean isMultiEmotionTone$default(ToneType toneType, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return toneType.isMultiEmotionTone(i);
    }

    public final boolean canShowStyle() {
        return Intrinsics.areEqual(this.platform, "microsoft") || Intrinsics.areEqual(this.platform, "sami") || Intrinsics.areEqual(this.platform, "moyin");
    }

    public final String getAuditionAudioZhVid() {
        return this.auditionAudioZhVid;
    }

    public final String getAuditionText() {
        String str = this.defaultAuditionText;
        if (!this.isAICloneTone) {
            return (str == null || str.length() == 0) ? ToneTypeKt.a() : str;
        }
        CloneToneLanguageConfig cloneToneLanguageConfig = CloneToneLanguageConfig.f64851a;
        String cloneToneLanguage = getCloneToneLanguage();
        cloneToneLanguageConfig.getClass();
        return CloneToneLanguageConfig.c(cloneToneLanguage);
    }

    public final String getAuthorName() {
        return this.authorName;
    }

    public final String getAvatarUrl() {
        return this.avatarUrl;
    }

    public final String getCategoryID() {
        return this.categoryID;
    }

    public final String getCategoryKey() {
        return this.categoryKey;
    }

    public final String getCategoryName() {
        return this.categoryName;
    }

    public final String getCloneToneLanguage() {
        Object objCreateFailure;
        try {
            String str = this.extra;
            if (str == null) {
                str = "";
            }
            objCreateFailure = new JSONObject(str).optString("language");
            Result.m17090constructorimpl(objCreateFailure);
        } catch (Throwable th) {
            objCreateFailure = ResultKt.createFailure(th);
            Result.m17090constructorimpl(objCreateFailure);
        }
        return (String) (Result.m17096isFailureimpl(objCreateFailure) ? "" : objCreateFailure);
    }

    public final String getDefaultAuditionText() {
        return this.defaultAuditionText;
    }

    public final Emotion getDefaultEmotion() {
        List<Emotion> list;
        if (!canShowStyle() || (list = this.emotionList) == null) {
            return null;
        }
        return (Emotion) CollectionsKt___CollectionsKt.firstOrNull((List) list);
    }

    public final String getEffectId() {
        return this.effectId;
    }

    public final List<Emotion> getEmotionList() {
        return this.emotionList;
    }

    public final boolean getEnableWordTimeInfo() {
        return this.enableWordTimeInfo;
    }

    public final String getExtra() {
        return this.extra;
    }

    public final String getIconUrl() {
        return this.iconUrl;
    }

    public final String getId() {
        return this.id;
    }

    public final Pair<String, String> getLanguageTag() {
        return this.languageTag;
    }

    public final String getMockToneInfo() {
        return this.mockToneInfo;
    }

    public final String getName() {
        return this.name;
    }

    public final String getOriginalAudioVid() {
        return this.originalAudioVid;
    }

    public final String getPanel() {
        return this.panel;
    }

    public final String getPlatform() {
        return this.platform;
    }

    public final String getRate() {
        return this.rate;
    }

    public final double getReadingSpeed() {
        return this.readingSpeed;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final String getResourceId() {
        return this.resourceId;
    }

    public final String getSecondCategoryID() {
        return this.secondCategoryID;
    }

    public final String getSecondCategoryKey() {
        return this.secondCategoryKey;
    }

    public final String getSecondCategoryName() {
        return this.secondCategoryName;
    }

    public final List<String> getTags() {
        return this.tags;
    }

    public final String getToneModelType() {
        return this.toneModelType;
    }

    public final String getToneName() {
        return this.toneName;
    }

    public final List<ToneTypeTagInfo> getToneStyleTagList() {
        return this.toneStyleTagList;
    }

    public final String getTtsVoice() {
        return this.ttsVoice;
    }

    public final String getVipStatus() {
        return this.vipStatus;
    }

    public final int getVoiceGender() {
        return this.voiceGender;
    }

    public final String getVoiceType() {
        return this.voiceType;
    }

    public final boolean isAICloneTone() {
        return this.isAICloneTone;
    }

    public final boolean isAiAvatar() {
        return this.isAiAvatar;
    }

    public final boolean isCommercial() {
        return this.isCommercial;
    }

    public final boolean isEmptyTone() {
        return this.effectId.length() == 0 && this.resourceId.length() == 0 && this.toneName.length() == 0;
    }

    public final boolean isMultiEmotionTone(int i) {
        List<Emotion> list;
        if (i == 4 || i == 6) {
            return false;
        }
        return !(i == 8 && Intrinsics.areEqual(this.platform, "microsoft")) && canShowStyle() && (list = this.emotionList) != null && list.size() > 1;
    }

    public final boolean isRecentUsed() {
        return this.isRecentUsed;
    }

    public final boolean isSingCloneTone() {
        return this.isSingCloneTone;
    }

    public final boolean isUgc() {
        return this.isUgc;
    }

    public final boolean isV3ModelTone() {
        Object objCreateFailure;
        try {
            objCreateFailure = Boolean.valueOf(Intrinsics.areEqual(new JSONObject(new JSONObject(this.extra).optString("tonetype")).optString("version", ""), BusinessPhotoTemplateOptEntity.V3));
            Result.m17090constructorimpl(objCreateFailure);
        } catch (Throwable th) {
            objCreateFailure = ResultKt.createFailure(th);
            Result.m17090constructorimpl(objCreateFailure);
        }
        Boolean bool = Boolean.FALSE;
        if (Result.m17096isFailureimpl(objCreateFailure)) {
            objCreateFailure = bool;
        }
        return ((Boolean) objCreateFailure).booleanValue();
    }

    public final boolean isVip() {
        return this.isVip;
    }

    public final boolean isVop() {
        return this.isVop;
    }

    public final void setAICloneTone(boolean z) {
        this.isAICloneTone = z;
    }

    public final void setAiAvatar(boolean z) {
        this.isAiAvatar = z;
    }

    public final void setAuditionAudioZhVid(String str) {
        this.auditionAudioZhVid = str;
    }

    public final void setAuthorName(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.authorName = str;
    }

    public final void setAvatarUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.avatarUrl = str;
    }

    public final void setCategoryID(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.categoryID = str;
    }

    public final void setCategoryKey(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.categoryKey = str;
    }

    public final void setCategoryName(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.categoryName = str;
    }

    public final void setCommercial(boolean z) {
        this.isCommercial = z;
    }

    public final void setDefaultAuditionText(String str) {
        this.defaultAuditionText = str;
    }

    public final void setEffectId(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.effectId = str;
    }

    public final void setEmotionList(List<Emotion> list) {
        this.emotionList = list;
    }

    public final void setEnableWordTimeInfo(boolean z) {
        this.enableWordTimeInfo = z;
    }

    public final void setExtra(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.extra = str;
    }

    public final void setIconUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.iconUrl = str;
    }

    public final void setId(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.id = str;
    }

    public final void setLanguageTag(Pair<String, String> pair) {
        this.languageTag = pair;
    }

    public final void setMockToneInfo(String str) {
        this.mockToneInfo = str;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.name = str;
    }

    public final void setOriginalAudioVid(String str) {
        this.originalAudioVid = str;
    }

    public final void setPanel(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.panel = str;
    }

    public final void setPlatform(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.platform = str;
    }

    public final void setRate(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.rate = str;
    }

    public final void setReadingSpeed(double d2) {
        this.readingSpeed = d2;
    }

    public final void setRecentUsed(boolean z) {
        this.isRecentUsed = z;
    }

    public final void setRequestId(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.requestId = str;
    }

    public final void setSecondCategoryID(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.secondCategoryID = str;
    }

    public final void setSecondCategoryKey(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.secondCategoryKey = str;
    }

    public final void setSecondCategoryName(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.secondCategoryName = str;
    }

    public final void setSingCloneTone(boolean z) {
        this.isSingCloneTone = z;
    }

    public final void setTags(List<String> list) {
        this.tags = list;
    }

    public final void setToneModelType(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.toneModelType = str;
    }

    public final void setToneName(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.toneName = str;
    }

    public final void setToneStyleTagList(List<ToneTypeTagInfo> list) {
        this.toneStyleTagList = list;
    }

    public final void setTtsVoice(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.ttsVoice = str;
    }

    public final void setUgc(boolean z) {
        this.isUgc = z;
    }

    public final void setVip(boolean z) {
        this.isVip = z;
    }

    public final void setVipStatus(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.vipStatus = str;
    }

    public final void setVoiceGender(int i) {
        this.voiceGender = i;
    }

    public final void setVoiceType(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.voiceType = str;
    }

    public final void setVop(boolean z) {
        this.isVop = z;
    }

    public String toString() {
        return "toneType{name:" + this.name + " ,voiceType:" + this.voiceType + " ,resourceId:" + this.resourceId + ", platform:" + this.platform + '}';
    }
}