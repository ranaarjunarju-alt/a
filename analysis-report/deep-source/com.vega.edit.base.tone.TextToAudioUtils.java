package com.vega.edit.base.tone;

import com.lemon.lv.config.BaseClientSetting;
import com.lemon.lv.config.ClientSetting;
import com.ss.android.ugc.effectmanager.effect.model.Effect;
import com.vega.aigcapi.materialgenerate.ITextToAudioApi;
import com.vega.aigcapi.materialgenerate.TextToAudioType;
import com.vega.core.context.SPIService;
import com.vega.core.ext.ExtentionKt;
import com.vega.libeffectapi.settings.IEffectSettings;
import com.vega.log.BLog;
import com.vega.middlebridge.swig.DigitalHumanVoiceInfo;
import com.vega.middlebridge.swig.Draft;
import com.vega.middlebridge.swig.LVVEMetaType;
import com.vega.middlebridge.swig.MaterialAudioEffect;
import com.vega.middlebridge.swig.MaterialDigitalHuman;
import com.vega.middlebridge.swig.Segment;
import com.vega.middlebridge.swig.SegmentAdcube;
import com.vega.middlebridge.swig.SegmentAudio;
import com.vega.middlebridge.swig.SegmentVideo;
import com.vega.middlebridge.swig.Track;
import com.vega.middlebridge.swig.VectorOfMaterialAudioEffect;
import com.vega.middlebridge.swig.VectorOfSegment;
import com.vega.middlebridge.swig.VectorOfTrack;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public final class TextToAudioUtils implements ITextToAudioApi {

    /* renamed from: a, reason: collision with root package name */
    public final boolean f88863a;

    /* loaded from: classes9.dex */
    public static final class Companion {
    }

    static {
        new Companion();
    }

    public TextToAudioUtils() {
        SPIService sPIService = SPIService.INSTANCE;
        this.f88863a = ((BaseClientSetting) sPIService.getImpl(Reflection.getOrCreateKotlinClass(ClientSetting.class), null)).getTtsNewFrameworkConfig().a() || ((IEffectSettings) sPIService.getImpl(Reflection.getOrCreateKotlinClass(IEffectSettings.class), null)).v() || ((BaseClientSetting) sPIService.getImpl(Reflection.getOrCreateKotlinClass(ClientSetting.class), null)).getMicrosoftTonePlatformConfig().enableMicrosoftTone() || ((BaseClientSetting) sPIService.getImpl(Reflection.getOrCreateKotlinClass(ClientSetting.class), null)).getElevenLabsTonePlatformConfig().isEnabled();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    public static TextToAudioType e(String str) {
        switch (str.hashCode()) {
            case -94228242:
                if (str.equals("microsoft")) {
                    return TextToAudioType.b;
                }
                break;
            case 3483983:
                if (str.equals("qwen")) {
                    return TextToAudioType.f69160g;
                }
                break;
            case 3522666:
                if (str.equals("sami")) {
                    return TextToAudioType.f69157a;
                }
                break;
            case 104090236:
                if (str.equals("moyin")) {
                    return TextToAudioType.e;
                }
                break;
            case 1451394726:
                if (str.equals("11labs")) {
                    return TextToAudioType.f69159d;
                }
                break;
        }
        return TextToAudioType.f69157a;
    }

    @Override // com.vega.aigcapi.materialgenerate.ITextToAudioApi
    public final ITextToAudioApi.ToneReport a(Draft draft) {
        DigitalHumanVoiceInfo digitalHumanVoiceInfoY;
        MaterialDigitalHuman materialDigitalHumanC;
        DigitalHumanVoiceInfo digitalHumanVoiceInfoY2;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        StringBuilder sb5 = new StringBuilder();
        StringBuilder sb6 = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        if (draft != null) {
            Iterator<Track> it = draft.v().iterator();
            while (it.hasNext()) {
                VectorOfSegment vectorOfSegmentE = it.next().e();
                Intrinsics.checkNotNullExpressionValue(vectorOfSegmentE, "");
                Iterator<Segment> it2 = vectorOfSegmentE.iterator();
                while (it2.hasNext()) {
                    Segment next = it2.next();
                    Intrinsics.checkNotNull(next);
                    arrayList.add(next);
                }
            }
        }
        Iterator it3 = arrayList.iterator();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (it3.hasNext()) {
            Segment segment = (Segment) it3.next();
            if (segment instanceof SegmentAudio) {
                SegmentAudio segmentAudio = (SegmentAudio) segment;
                if (segmentAudio.u().d() == LVVEMetaType.MetaTypeTextToAudio) {
                    i++;
                    if ("microsoft".equals(segmentAudio.u().H())) {
                        i2++;
                    }
                    sb.append(segmentAudio.u().J());
                    sb.append(",");
                    sb2.append(segmentAudio.u().E());
                    sb2.append(",");
                }
            } else if (segment instanceof SegmentVideo) {
                SegmentVideo segmentVideo = (SegmentVideo) segment;
                MaterialDigitalHuman materialDigitalHumanC2 = segmentVideo.C();
                if (materialDigitalHumanC2 != null && (digitalHumanVoiceInfoY = materialDigitalHumanC2.y()) != null && ExtentionKt.isNotNullOrEmpty(digitalHumanVoiceInfoY.n())) {
                    i++;
                    if ("microsoft".equals(digitalHumanVoiceInfoY.m())) {
                        i2++;
                    }
                    sb.append(digitalHumanVoiceInfoY.n());
                    sb.append(",");
                    sb2.append(digitalHumanVoiceInfoY.j());
                    sb2.append(",");
                    sb3.append(digitalHumanVoiceInfoY.i());
                    sb3.append(",");
                    sb4.append(digitalHumanVoiceInfoY.h());
                    sb4.append(",");
                }
                VectorOfMaterialAudioEffect vectorOfMaterialAudioEffectJ0 = segmentVideo.j0();
                Intrinsics.checkNotNullExpressionValue(vectorOfMaterialAudioEffectJ0, "");
                Iterator<MaterialAudioEffect> it4 = vectorOfMaterialAudioEffectJ0.iterator();
                while (it4.hasNext()) {
                    MaterialAudioEffect next2 = it4.next();
                    Boolean boolValueOf = Boolean.valueOf(ExtentionKt.isNotNullOrEmpty(next2.l()));
                    if (boolValueOf.booleanValue()) {
                        boolValueOf.booleanValue();
                        i++;
                        sb.append(next2.i());
                        sb.append(",");
                        sb2.append(next2.l());
                        sb2.append(",");
                        sb3.append(next2.f());
                        sb3.append(",");
                        sb4.append(next2.e());
                        sb4.append(",");
                    }
                }
            } else if (segment instanceof SegmentAdcube) {
                VectorOfTrack vectorOfTrackV = ((SegmentAdcube) segment).o().i().v();
                Intrinsics.checkNotNullExpressionValue(vectorOfTrackV, "");
                Iterator<Track> it5 = vectorOfTrackV.iterator();
                while (it5.hasNext()) {
                    VectorOfSegment vectorOfSegmentE2 = it5.next().e();
                    Intrinsics.checkNotNullExpressionValue(vectorOfSegmentE2, "");
                    Iterator<Segment> it6 = vectorOfSegmentE2.iterator();
                    while (it6.hasNext()) {
                        Segment next3 = it6.next();
                        if ((next3 instanceof SegmentVideo) && (materialDigitalHumanC = ((SegmentVideo) next3).C()) != null && (digitalHumanVoiceInfoY2 = materialDigitalHumanC.y()) != null && ExtentionKt.isNotNullOrEmpty(digitalHumanVoiceInfoY2.n())) {
                            i3++;
                            if ("microsoft".equals(digitalHumanVoiceInfoY2.m())) {
                                i4++;
                            }
                            sb5.append(digitalHumanVoiceInfoY2.n());
                            sb5.append(",");
                            sb6.append(digitalHumanVoiceInfoY2.j());
                            sb6.append(",");
                        }
                        if (next3 instanceof SegmentAudio) {
                            SegmentAudio segmentAudio2 = (SegmentAudio) next3;
                            if (segmentAudio2.u().d() == LVVEMetaType.MetaTypeTextToAudio) {
                                i3++;
                                if ("microsoft".equals(segmentAudio2.u().H())) {
                                    i4++;
                                }
                                sb5.append(segmentAudio2.u().J());
                                sb5.append(",");
                                sb6.append(segmentAudio2.u().E());
                                sb6.append(",");
                            }
                        }
                    }
                }
            }
        }
        if (i <= 0) {
            if (StringsKt__StringsKt.endsWith$default(sb5, ",", false, 2, (Object) null)) {
                sb5.deleteCharAt(sb5.length() - 1);
            }
            if (StringsKt__StringsKt.endsWith$default(sb, ",", false, 2, (Object) null)) {
                sb6.deleteCharAt(sb6.length() - 1);
            }
            String string = sb5.toString();
            Intrinsics.checkNotNullExpressionValue(string, "");
            String string2 = sb6.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "");
            return new ITextToAudioApi.ToneReport(i3, i4, string, string2, 48);
        }
        if (StringsKt__StringsKt.endsWith$default(sb, ",", false, 2, (Object) null)) {
            sb.deleteCharAt(sb.length() - 1);
        }
        if (StringsKt__StringsKt.endsWith$default(sb, ",", false, 2, (Object) null)) {
            sb2.deleteCharAt(sb2.length() - 1);
        }
        String string3 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string3, "");
        String string4 = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string4, "");
        String string5 = sb3.toString();
        Intrinsics.checkNotNullExpressionValue(string5, "");
        String string6 = sb4.toString();
        Intrinsics.checkNotNullExpressionValue(string6, "");
        return new ITextToAudioApi.ToneReport(i, i2, string3, string4, string5, string6);
    }

    @Override // com.vega.aigcapi.materialgenerate.ITextToAudioApi
    public final List<Effect> b(List<? extends Effect> list, List<? extends TextToAudioType> list2) {
        Intrinsics.checkNotNullParameter(list, "");
        Intrinsics.checkNotNullParameter(list2, "");
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (list2.contains(d((Effect) obj))) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @Override // com.vega.aigcapi.materialgenerate.ITextToAudioApi
    public final boolean c() {
        return this.f88863a;
    }

    @Override // com.vega.aigcapi.materialgenerate.ITextToAudioApi
    public final TextToAudioType d(Effect effect) {
        Intrinsics.checkNotNullParameter(effect, "");
        TextToAudioType textToAudioTypeE = TextToAudioType.f69157a;
        try {
            String extra = effect.getExtra();
            String strOptString = new JSONObject(extra != null ? extra : "").optString("tonetype");
            if (strOptString == null) {
                return textToAudioTypeE;
            }
            String strOptString2 = new JSONObject(strOptString).optString("platform", "sami");
            BLog.e("TextToAudioUtils", "effectToneType platform " + strOptString2);
            Intrinsics.checkNotNull(strOptString2);
            textToAudioTypeE = e(strOptString2);
            return textToAudioTypeE;
        } catch (JSONException e) {
            BLog.e("TextToAudioUtils", "effect2ToneType " + e);
            return textToAudioTypeE;
        }
    }
}