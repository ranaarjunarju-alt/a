package com.vega.audio.tone.viewmodel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.lemon.librespool.model.gen.EffectByIdParams;
import com.lemon.lv.data.ToneType;
import com.ss.android.ugc.effectmanager.effect.model.Effect;
import com.ss.android.ugc.effectmanager.effect.model.EffectCategoryModel;
import com.ss.android.ugc.effectmanager.effect.model.template.EffectTemplate;
import com.vega.audio.tone.clonetone.api.CloneToneRepository;
import com.vega.container.session.core.ISession;
import com.vega.core.context.SPIService;
import com.vega.edit.base.dock.locate.EffectLocatorOwner;
import com.vega.edit.base.dock.locate.EffectLocatorRepository;
import com.vega.edit.base.model.repository.IStickerCacheRepository;
import com.vega.edit.base.model.repository.KeyframeCacheRepository;
import com.vega.edit.base.model.repository.KeyframeCacheRepository$segmentState$1;
import com.vega.edit.base.model.repository.SegmentState;
import com.vega.edit.base.utils.DigitalHumanUtils;
import com.vega.edit.base.viewmodel.abilityImpl.SearchItemInteractionDelegate;
import com.vega.edit.base.viewmodel.effect.IEffectItemViewModel;
import com.vega.effectplatform.artist.Constants;
import com.vega.libeffect.repository.AllEffectsRepository;
import com.vega.libeffect.repository.CategoriesRepository;
import com.vega.libeffectapi.settings.IEffectSettings;
import com.vega.libmaterialpanel.ability.ItemStateManager;
import com.vega.log.BLog;
import com.vega.middlebridge.swig.Segment;
import com.vega.middlebridge.swig.SegmentText;
import com.vega.ve.utils.DraftExpandKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.inject.Provider;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* loaded from: classes29.dex */
public final class AudioToneSelectViewModel extends ToneSelectViewModel implements EffectLocatorOwner, SearchItemInteractionDelegate {
    public final IStickerCacheRepository n1;
    public final EffectLocatorRepository o1;
    public final KeyframeCacheRepository$segmentState$1 p1;
    public String q1;
    public final Lazy r1;
    public final List<ToneType> s1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioToneSelectViewModel(AllEffectsRepository allEffectsRepository, CategoriesRepository categoriesRepository, Provider<IEffectItemViewModel> provider, IStickerCacheRepository iStickerCacheRepository, ISession iSession, CloneToneRepository cloneToneRepository, EffectLocatorRepository effectLocatorRepository) {
        SegmentText segmentText;
        super(allEffectsRepository, categoriesRepository, provider, iSession, cloneToneRepository);
        String strV = "";
        Intrinsics.checkNotNullParameter(allEffectsRepository, "");
        Intrinsics.checkNotNullParameter(categoriesRepository, "");
        Intrinsics.checkNotNullParameter(provider, "");
        Intrinsics.checkNotNullParameter(iStickerCacheRepository, "");
        Intrinsics.checkNotNullParameter(iSession, "");
        Intrinsics.checkNotNullParameter(cloneToneRepository, "");
        Intrinsics.checkNotNullParameter(effectLocatorRepository, "");
        this.n1 = iStickerCacheRepository;
        this.o1 = effectLocatorRepository;
        KeyframeCacheRepository$segmentState$1 keyframeCacheRepository$segmentState$1 = iStickerCacheRepository.f87853d;
        this.p1 = keyframeCacheRepository$segmentState$1;
        SegmentState value = keyframeCacheRepository$segmentState$1.getValue();
        Segment segment = value != null ? value.f87871c : null;
        if ((segment instanceof SegmentText) && (segmentText = (SegmentText) segment) != null) {
            strV = DraftExpandKt.v(segmentText);
        }
        this.q1 = strV;
        this.r1 = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.audio.tone.viewmodel.AudioToneSelectViewModel$enableRemoteSami$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(((IEffectSettings) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IEffectSettings.class), null)).v());
            }
        });
        new MutableLiveData();
        this.s1 = CollectionsKt__CollectionsKt.emptyList();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void o8(final com.vega.audio.tone.viewmodel.AudioToneSelectViewModel r43, final com.vega.edit.digitalhuman.digital.loopy.node.DigitalHumanProgressDialogController r44, java.util.List r45, com.vega.textaihuman.model.DigitalHumanPresenterModel r46, com.vega.aigcapi.materialgenerate.TextToSpeechReportDigitalHumanEntrance r47, kotlin.jvm.functions.Function0 r48, final kotlin.jvm.functions.Function1 r49) {
        /*
            r1 = r43
            r1.getClass()
            java.lang.String r9 = ""
            r2 = r44
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r9)
            r10 = r45
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r9)
            r8 = r46
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r9)
            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r1.z
            java.lang.Object r12 = r0.getValue()
            java.lang.String r12 = (java.lang.String) r12
            androidx.lifecycle.MutableLiveData<com.lemon.lv.data.ToneType> r0 = r1.F
            java.lang.Object r5 = r0.getValue()
            com.lemon.lv.data.ToneType r5 = (com.lemon.lv.data.ToneType) r5
            java.util.HashMap<java.lang.String, com.lemon.lv.data.Emotion> r0 = r1.X0
            java.lang.Object r3 = r0.get(r12)
            com.lemon.lv.data.Emotion r3 = (com.lemon.lv.data.Emotion) r3
            r4 = 0
            if (r3 != 0) goto L37
            if (r5 == 0) goto L22b
            com.lemon.lv.data.Emotion r3 = r5.getDefaultEmotion()
        L37:
            com.vega.edit.base.tone.EmotionOption$Companion r0 = com.vega.edit.base.tone.EmotionOption.h
            r0.getClass()
            com.vega.edit.base.tone.EmotionOption r31 = com.vega.edit.base.tone.EmotionOption.Companion.a(r3)
            r6 = 0
            r7 = r49
            if (r12 == 0) goto L51
            int r0 = r12.length()
            if (r0 != 0) goto L68
        L4b:
            int r0 = r12.length()
            if (r0 != 0) goto L62
        L51:
            r1 = -1000(0xfffffffffffffc18, float:NaN)
        L53:
            com.vega.audio.tone.TextToAudioManager$TextToAudioInfoResult r0 = new com.vega.audio.tone.TextToAudioManager$TextToAudioInfoResult
            r0.<init>(r1, r4, r4)
            r7.invoke(r0)
            com.vega.audio.tone.TextToAudioManager r1 = com.vega.audio.tone.TextToAudioManager.f73896a
            r0 = -4
            com.vega.audio.tone.TextToAudioManager.c(r1, r0)
        L61:
            return
        L62:
            if (r5 != 0) goto L65
            goto L51
        L65:
            r1 = -1300(0xfffffffffffffaec, float:NaN)
            goto L53
        L68:
            if (r5 == 0) goto L70
            boolean r0 = r10.isEmpty()
            if (r0 == 0) goto L73
        L70:
            if (r12 == 0) goto L51
            goto L4b
        L73:
            com.vega.infrastructure.base.ModuleCommon r0 = com.vega.infrastructure.base.ModuleCommon.INSTANCE
            android.app.Application r0 = r0.getApplication()
            boolean r0 = com.bytedance.apm.util.NetUtils.b(r0)
            if (r0 != 0) goto L95
            com.vega.audio.tone.TextToAudioManager$TextToAudioInfoResult r1 = new com.vega.audio.tone.TextToAudioManager$TextToAudioInfoResult
            r0 = -1200(0xfffffffffffffb50, float:NaN)
            r1.<init>(r0, r4, r4)
            r7.invoke(r1)
            com.vega.audio.tone.viewmodel.AudioToneSelectViewModel$saveAudioAll$1 r0 = new kotlin.jvm.functions.Function0<kotlin.Unit>() { // from class: com.vega.audio.tone.viewmodel.AudioToneSelectViewModel$saveAudioAll$1
                static {
                    /*
                        com.vega.audio.tone.viewmodel.AudioToneSelectViewModel$saveAudioAll$1 r0 = new com.vega.audio.tone.viewmodel.AudioToneSelectViewModel$saveAudioAll$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.vega.audio.tone.viewmodel.AudioToneSelectViewModel$saveAudioAll$1) com.vega.audio.tone.viewmodel.AudioToneSelectViewModel$saveAudioAll$1.e com.vega.audio.tone.viewmodel.AudioToneSelectViewModel$saveAudioAll$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.AudioToneSelectViewModel$saveAudioAll$1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 0
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.AudioToneSelectViewModel$saveAudioAll$1.<init>():void");
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final kotlin.Unit invoke() {
                    /*
                        r6 = this;
                        r0 = 2131897356(0x7f122c0c, float:1.94296E38)
                        r1 = 0
                        r5 = 252(0xfc, float:3.53E-43)
                        r2 = r1
                        r3 = r1
                        r4 = r1
                        com.vega.util.ToastUtilKt.d(r0, r1, r2, r3, r4, r5)
                        kotlin.Unit r0 = kotlin.Unit.INSTANCE
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.AudioToneSelectViewModel$saveAudioAll$1.invoke():java.lang.Object");
                }
            }
            com.vega.infrastructure.extensions.ThreadUtilKt.f(r0)
            com.vega.audio.tone.TextToAudioManager r1 = com.vega.audio.tone.TextToAudioManager.f73896a
            r0 = -5
            com.vega.audio.tone.TextToAudioManager.c(r1, r0)
            goto L61
        L95:
            com.vega.audio.tone.viewmodel.AudioToneSelectViewModel$saveAudioAll$2 r0 = new com.vega.audio.tone.viewmodel.AudioToneSelectViewModel$saveAudioAll$2
            r0.<init>()
            r2 = 0
            com.vega.infrastructure.extensions.ThreadUtilKt.e(r2, r0)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r10 = r10.iterator()
        La8:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto Ld4
            java.lang.Object r2 = r10.next()
            java.util.List r2 = (java.util.List) r2
            boolean r0 = r2.isEmpty()
            r0 = r0 ^ 1
            if (r0 == 0) goto La8
            java.util.Iterator r2 = r2.iterator()
        Lc0:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto La8
            java.lang.Object r0 = r2.next()
            com.vega.middlebridge.swig.Segment r0 = (com.vega.middlebridge.swig.Segment) r0
            java.lang.String r0 = com.vega.edit.base.action.DraftExKt.e(r0)
            r3.add(r0)
            goto Lc0
        Ld4:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            androidx.lifecycle.MutableLiveData<com.lemon.lv.data.ToneType> r2 = r1.F
            java.lang.Object r11 = r2.getValue()
            com.lemon.lv.data.ToneType r11 = (com.lemon.lv.data.ToneType) r11
            if (r11 == 0) goto L12e
            java.lang.String r10 = "tone_id"
            java.lang.String r2 = r11.getId()
            r0.put(r10, r2)
            java.lang.String r10 = "tone_category_id"
            java.lang.String r2 = r11.getCategoryID()
            r0.put(r10, r2)
            java.lang.String r10 = "tone_category"
            java.lang.String r2 = r11.getCategoryKey()
            r0.put(r10, r2)
            boolean r2 = r11.isVip()
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            java.lang.String r10 = com.vega.core.ext.ExtentionKt.getReportStr(r2)
            java.lang.String r2 = "is_vip"
            r0.put(r2, r10)
            boolean r2 = r11.isAICloneTone()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r2)
            java.lang.String r2 = "is_cloned"
            r0.put(r2, r10)
            java.lang.String r10 = "tone_second_category"
            java.lang.String r2 = r11.getSecondCategoryKey()
            r0.put(r10, r2)
            java.lang.String r10 = "resource_id"
            java.lang.String r2 = r11.getResourceId()
            r0.put(r10, r2)
        L12e:
            java.lang.String r10 = r8.i()
            if (r10 != 0) goto L135
            r10 = r9
        L135:
            java.lang.String r2 = "ai_avatar_id"
            r0.put(r2, r10)
            java.lang.String r2 = r8.j()
            if (r2 != 0) goto L228
        L140:
            java.lang.String r2 = "ai_avatar_name"
            r0.put(r2, r9)
            com.vega.core.context.SPIService r9 = com.vega.core.context.SPIService.INSTANCE
            java.lang.Class<com.lemon.lv.config.ClientSetting> r2 = com.lemon.lv.config.ClientSetting.class
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
            java.lang.Object r2 = r9.getImpl(r2, r4)
            com.lemon.lv.config.BaseClientSetting r2 = (com.lemon.lv.config.BaseClientSetting) r2
            com.lemon.lv.config.DigitalHumanLocalRenderConfig r2 = r2.getDigitalHumanLocalRenderConfig()
            boolean r2 = r2.b()
            if (r2 != 0) goto L208
        L15d:
            java.lang.String r8 = "server"
        L15f:
            java.lang.String r2 = "render_server"
            r0.put(r2, r8)
            int r8 = r1.e0
            r2 = 30
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r15 = com.vega.audio.tone.viewmodel.ToneSelectViewModel.i8(r1, r8, r6, r4, r2)
            com.vega.edit.base.tone.TextInfo$NoSegTextList r11 = new com.vega.edit.base.tone.TextInfo$NoSegTextList
            r11.<init>(r3)
            com.vega.audio.tone.util.TextToSpeechReportInfo r14 = new com.vega.audio.tone.util.TextToSpeechReportInfo
            r10 = 0
            java.lang.String r2 = r11.getText()
            int r17 = r2.length()
            r20 = 0
            if (r47 == 0) goto L204
            java.lang.String r24 = r47.getInfo()
        L184:
            r26 = 738(0x2e2, float:1.034E-42)
            r16 = r4
            r18 = r6
            r19 = r6
            r22 = r4
            r23 = r4
            r25 = r4
            r27 = r4
            r14.<init>(r15, r16, r17, r18, r19, r20, r22, r23, r24, r25, r26, r27)
            java.lang.String r13 = r5.getPlatform()
            com.vega.edit.base.tone.TTSBusinessType r16 = com.vega.edit.base.tone.TTSBusinessType.f88857c
            int r2 = r1.e0
            java.lang.String r17 = r1.j8(r2)
            java.lang.String r24 = r14.toJson()
            java.lang.String r25 = r5.getAuditionText()
            boolean r3 = r5.isAICloneTone()
            boolean r37 = r5.isV3ModelTone()
            java.lang.String r27 = r5.getToneModelType()
            java.lang.String r28 = r5.getResourceId()
            java.lang.String r32 = r5.getMockToneInfo()
            com.vega.edit.base.tone.TextToSpeechIntent r9 = new com.vega.edit.base.tone.TextToSpeechIntent
            java.lang.String r14 = "VideoDigitalToneSelectViewModel"
            java.lang.String r18 = ""
            r19 = 0
            r20 = 24000(0x5dc0, float:3.3631E-41)
            r23 = 0
            com.vega.audio.tone.viewmodel.AudioToneSelectViewModel$saveAudioAll$intent$1 r2 = new com.vega.audio.tone.viewmodel.AudioToneSelectViewModel$saveAudioAll$intent$1
            r2.<init>()
            java.lang.Boolean r36 = java.lang.Boolean.valueOf(r3)
            r38 = 1
            r46 = -946787807(0xffffffffc7912a21, float:-74324.26)
            r47 = 23
            r44 = r48
            r15 = r10
            r21 = r10
            r22 = r0
            r26 = r23
            r29 = r2
            r30 = r10
            r33 = r10
            r34 = r23
            r35 = r10
            r39 = r23
            r40 = r23
            r41 = r10
            r42 = r10
            r43 = r10
            r45 = r23
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47)
            com.vega.audio.tone.tts.TextToSpeechTaskManager r0 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a
            r0.c(r9)
            goto L61
        L204:
            r24 = r4
            goto L184
        L208:
            com.vega.textaihuman.model.DigitalHumanFigureEffect r2 = r8.b
            com.vega.textaihuman.model.DigitalHumanLocalRenderData r2 = r2.getLocalRenderData()
            boolean r2 = r2.a()
            if (r2 == 0) goto L15d
            com.vega.textaihuman.model.DigitalHumanFigureEffect r2 = r8.b
            com.vega.textaihuman.model.DigitalHumanLocalRenderData r2 = r2.getLocalRenderData()
            java.lang.String r2 = r2.b()
            boolean r2 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r2)
            if (r2 == 0) goto L15d
            java.lang.String r8 = "local"
            goto L15f
        L228:
            r9 = r2
            goto L140
        L22b:
            r3 = r4
            goto L37
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.AudioToneSelectViewModel.o8(com.vega.audio.tone.viewmodel.AudioToneSelectViewModel, com.vega.edit.digitalhuman.digital.loopy.node.DigitalHumanProgressDialogController, java.util.List, com.vega.textaihuman.model.DigitalHumanPresenterModel, com.vega.aigcapi.materialgenerate.TextToSpeechReportDigitalHumanEntrance, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1):void");
    }

    @Override // com.vega.edit.base.dock.locate.EffectLocatorOwner
    public final int D2(List<EffectCategoryModel> list) {
        return EffectLocatorOwner.DefaultImpls.a(this, list);
    }

    @Override // com.vega.edit.base.view.BaseTabViewModel
    public final LiveData<SegmentState> R2() {
        return this.p1;
    }

    @Override // com.vega.audio.tone.viewmodel.ToneSelectViewModel
    public final void X7(Segment segment) {
        KeyframeCacheRepository.d(this.n1, segment, 2);
    }

    @Override // com.vega.edit.base.dock.locate.EffectLocatorOwner
    public final EffectLocatorRepository e6() {
        return this.o1;
    }

    public final void n8(String str, Function1<? super Effect, Unit> function1) {
        List arrayList;
        Intrinsics.checkNotNullParameter(str, "");
        if (str.length() == 0) {
            BLog.e("ToneSelectViewModel", "voiceResourceId is empty, voiceResourceId: ".concat(str));
            function1.invoke(null);
            return;
        }
        DigitalHumanUtils.f88933a.getClass();
        List<Effect> value = DigitalHumanUtils.e.getValue();
        if (value == null || (arrayList = CollectionsKt___CollectionsKt.toMutableList((Collection) value)) == null) {
            arrayList = new ArrayList();
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Intrinsics.areEqual(((EffectTemplate) next).getResourceId(), str)) {
                if (next != 0) {
                    function1.invoke(next);
                    return;
                }
            }
        }
        BuildersKt__Builders_commonKt.launch$default(this, null, null, new AudioToneSelectViewModel$getToneTypeByResourceId$1(CollectionsKt__CollectionsKt.arrayListOf(new EffectByIdParams(str, 11, 1, "tone")), function1, arrayList, null), 3, null);
    }

    @Override // com.vega.audio.tone.viewmodel.ToneSelectViewModel
    public final String w7() {
        return this.q1;
    }

    @Override // com.vega.edit.base.dock.locate.EffectLocatorOwner
    public final boolean x3(Constants.EffectType effectType) {
        return EffectLocatorOwner.DefaultImpls.b(this, effectType);
    }

    @Override // com.vega.edit.base.viewmodel.abilityImpl.SearchItemInteractionDelegate
    public final void y2(Effect effect, Context context, ItemStateManager itemStateManager) {
        Intrinsics.checkNotNullParameter(effect, "");
        Intrinsics.checkNotNullParameter(context, "");
        Intrinsics.checkNotNullParameter(itemStateManager, "");
    }

    @Override // com.vega.edit.base.viewmodel.abilityImpl.SearchItemInteractionDelegate
    public final void y5(Effect effect, Context context, ItemStateManager itemStateManager) {
        Intrinsics.checkNotNullParameter(effect, "");
        Intrinsics.checkNotNullParameter(context, "");
        Intrinsics.checkNotNullParameter(itemStateManager, "");
    }
}