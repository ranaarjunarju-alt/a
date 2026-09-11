package com.vega.edit.base.c2pa;

import android.content.Context;
import android.util.Base64;
import com.bytedance.common.utility.StringUtils;
import com.bytedance.ttc2pa.DefaultCallStatusErrorHandler;
import com.bytedance.ttc2pa.MemoryOutputStream;
import com.bytedance.ttc2pa.NativeLib;
import com.bytedance.ttc2pa.RustBuffer;
import com.bytedance.ttc2pa.RustCallStatus;
import com.bytedance.ttc2pa.TTC2PA;
import com.bytedance.ttc2pa.UnhandledException;
import com.bytedance.ttc2pa.localfiles.SharedPreferencesUtils;
import com.bytedance.ttc2pa.localfiles.SignCertManager;
import com.bytedance.ttc2pa.utils.C2paThreadFactory;
import com.bytedance.ttc2pa.utils.Logger;
import com.squareup.wire.ProtoAdapter;
import com.ss.android.ugc.bytex.pthread.base.proxy.PThreadScheduledThreadPoolExecutor;
import com.sun.jna.Pointer;
import com.vega.container.session.core.ISession;
import com.vega.core.app.AppContext;
import com.vega.infrastructure.extensions.ThreadUtilKt;
import com.vega.log.BLog;
import com.vega.middlebridge.swig.Draft;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import okio.ByteString;
import org.json.JSONObject;
import tt_c2pa_sdk.proto.FuncParamsX913809d0daa224e7;
import tt_c2pa_sdk.proto.FuncReturnX0bf8b5c665c57f35;

/* loaded from: classes24.dex */
public final class C2PAUtil {

    /* renamed from: a, reason: collision with root package name */
    public static final C2PAUtil f87111a = new C2PAUtil();
    public static volatile boolean b;

    /* loaded from: classes19.dex */
    public static final class SignResult {

        /* renamed from: a, reason: collision with root package name */
        public final String f87112a;
        public final long b;

        /* renamed from: c, reason: collision with root package name */
        public final int f87113c;

        /* renamed from: d, reason: collision with root package name */
        public final String f87114d;

        public SignResult(int i, long j, String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(str2, "");
            this.f87112a = str;
            this.b = j;
            this.f87113c = i;
            this.f87114d = str2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:127:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0509  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0182  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List a(com.vega.container.session.core.ISession r9, com.vega.middlebridge.swig.Draft r10) {
        /*
            if (r10 != 0) goto L7
            java.util.List r0 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()
            return r0
        L7:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.List r1 = com.vega.middlebridge.expand.DraftExpandKt.v(r10)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            boolean r0 = r1.isEmpty()
            r6 = 0
            r5 = 1
            if (r0 == 0) goto L1b2
        L1a:
            r2 = 0
        L1b:
            java.util.List r1 = com.vega.middlebridge.expand.DraftExpandKt.v(r10)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L199
        L27:
            java.util.List r1 = com.vega.middlebridge.expand.DraftExpandKt.u(r10)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L182
        L33:
            r8 = 0
        L34:
            if (r2 == 0) goto L3b
            java.lang.String r0 = "aigc_material"
            r4.add(r0)
        L3b:
            java.util.List r1 = com.vega.middlebridge.expand.DraftExpandKt.v(r10)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            boolean r0 = r1.isEmpty()
            java.lang.String r3 = ""
            if (r0 == 0) goto L141
        L49:
            java.util.List r1 = com.vega.middlebridge.expand.DraftExpandKt.y(r10)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L128
        L55:
            java.util.List r1 = com.vega.middlebridge.expand.DraftExpandKt.v(r10)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L10b
        L61:
            java.util.List r1 = com.vega.middlebridge.expand.DraftExpandKt.v(r10)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto Lee
        L6d:
            java.util.List r1 = com.vega.middlebridge.expand.DraftExpandKt.u(r10)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto Ld2
        L79:
            boolean r0 = com.vega.middlebridge.expand.DraftExpandKt.q0(r10)
            if (r0 == 0) goto L84
            java.lang.String r0 = "smart_lip"
            r4.add(r0)
        L84:
            java.util.List r1 = com.vega.middlebridge.expand.DraftExpandKt.y(r10)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto Lb6
        L90:
            com.vega.middlebridge.swig.VectorOfTrack r0 = r10.v()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Iterator r6 = r0.iterator()
        L9d:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L1dd
            java.lang.Object r2 = r6.next()
            r0 = r2
            com.vega.middlebridge.swig.Track r0 = (com.vega.middlebridge.swig.Track) r0
            com.vega.middlebridge.swig.LVVETrackType r1 = r0.f()
            com.vega.middlebridge.swig.LVVETrackType r0 = com.vega.middlebridge.swig.LVVETrackType.TrackTypeVideo
            if (r1 != r0) goto L9d
            r7.add(r2)
            goto L9d
        Lb6:
            java.util.Iterator r1 = r1.iterator()
        Lba:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L90
            java.lang.Object r0 = r1.next()
            com.vega.middlebridge.swig.Segment r0 = (com.vega.middlebridge.swig.Segment) r0
            boolean r0 = com.vega.ve.expand.UnifyTextExpandKt.H(r0)
            if (r0 == 0) goto Lba
            java.lang.String r0 = "ai_emoji"
            r4.add(r0)
            goto L90
        Ld2:
            java.util.Iterator r1 = r1.iterator()
        Ld6:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L79
            java.lang.Object r0 = r1.next()
            com.vega.middlebridge.swig.Segment r0 = (com.vega.middlebridge.swig.Segment) r0
            boolean r0 = com.vega.middlebridge.expand.DraftExpandKt.A0(r0)
            if (r0 == 0) goto Ld6
            java.lang.String r0 = "tone_clone"
            r4.add(r0)
            goto L79
        Lee:
            java.util.Iterator r1 = r1.iterator()
        Lf2:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L6d
            java.lang.Object r0 = r1.next()
            com.vega.middlebridge.swig.SegmentVideo r0 = (com.vega.middlebridge.swig.SegmentVideo) r0
            com.vega.middlebridge.swig.MaterialDigitalHumanModelDressing r0 = r0.D()
            if (r0 == 0) goto Lf2
            java.lang.String r0 = "model_dressing_video"
            r4.add(r0)
            goto L6d
        L10b:
            java.util.Iterator r1 = r1.iterator()
        L10f:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L61
            java.lang.Object r0 = r1.next()
            com.vega.middlebridge.swig.SegmentVideo r0 = (com.vega.middlebridge.swig.SegmentVideo) r0
            com.vega.middlebridge.swig.MaterialDigitalHuman r0 = r0.C()
            if (r0 == 0) goto L10f
            java.lang.String r0 = "digital_human"
            r4.add(r0)
            goto L61
        L128:
            java.util.Iterator r2 = r1.iterator()
        L12c:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L55
            java.lang.Object r0 = r2.next()
            com.vega.middlebridge.swig.Segment r0 = (com.vega.middlebridge.swig.Segment) r0
            com.vega.middlebridge.swig.LVVESegmentSource r1 = r0.i()
            com.vega.middlebridge.swig.LVVESegmentSource r0 = com.vega.middlebridge.swig.LVVESegmentSource.SegmentSourceAIVideoExpand
            if (r1 != r0) goto L12c
            goto L17b
        L141:
            java.util.Iterator r6 = r1.iterator()
        L145:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L49
            java.lang.Object r0 = r6.next()
            com.vega.middlebridge.swig.SegmentVideo r0 = (com.vega.middlebridge.swig.SegmentVideo) r0
            com.vega.middlebridge.swig.VideoAlgorithm r0 = r0.d0()
            com.vega.middlebridge.swig.VectorOfAlgorithm r1 = r0.i()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L163
            goto L145
        L163:
            java.util.Iterator r2 = r1.iterator()
        L167:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L145
            java.lang.Object r0 = r2.next()
            com.vega.middlebridge.swig.Algorithm r0 = (com.vega.middlebridge.swig.Algorithm) r0
            com.vega.middlebridge.swig.LVVELocalAlgorithmType r1 = r0.h()
            com.vega.middlebridge.swig.LVVELocalAlgorithmType r0 = com.vega.middlebridge.swig.LVVELocalAlgorithmType.GenAiVideoExpand
            if (r1 != r0) goto L167
        L17b:
            java.lang.String r0 = "video_ai_extend"
            r4.add(r0)
            goto L55
        L182:
            java.util.Iterator r1 = r1.iterator()
        L186:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L33
            java.lang.Object r0 = r1.next()
            com.vega.middlebridge.swig.Segment r0 = (com.vega.middlebridge.swig.Segment) r0
            boolean r0 = com.vega.ve.utils.DraftExpandKt.H(r0)
            if (r0 == 0) goto L186
            goto L1af
        L199:
            java.util.Iterator r1 = r1.iterator()
        L19d:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L27
            java.lang.Object r0 = r1.next()
            com.vega.middlebridge.swig.Segment r0 = (com.vega.middlebridge.swig.Segment) r0
            boolean r0 = com.vega.ve.utils.DraftExpandKt.H(r0)
            if (r0 == 0) goto L19d
        L1af:
            r8 = 1
            goto L34
        L1b2:
            java.util.Iterator r3 = r1.iterator()
        L1b6:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L1a
            java.lang.Object r0 = r3.next()
            com.vega.middlebridge.swig.SegmentVideo r0 = (com.vega.middlebridge.swig.SegmentVideo) r0
            com.vega.middlebridge.swig.VideoAlgorithm r0 = r0.d0()
            com.vega.middlebridge.swig.AigcGenerate r2 = r0.h()
            if (r2 == 0) goto L1db
            long r0 = r2.f115820d
            java.lang.String r0 = com.vega.middlebridge.swig.AigcGenerateModuleJNI.AigcGenerate_getAigcGenerateId(r0, r2)
        L1d2:
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r0)
            if (r0 == 0) goto L1b6
            r2 = 1
            goto L1b
        L1db:
            r0 = r6
            goto L1d2
        L1dd:
            boolean r0 = r7.isEmpty()
            if (r0 == 0) goto L48a
        L1e3:
            boolean r0 = com.vega.middlebridge.expand.DraftExpandKt.G0(r10)
            if (r0 == 0) goto L1ee
            java.lang.String r0 = "ai_add_action"
            r4.add(r0)
        L1ee:
            boolean r0 = com.vega.middlebridge.expand.DraftExpandKt.E0(r10)
            if (r0 == 0) goto L1f9
            java.lang.String r0 = "ai_change_expression"
            r4.add(r0)
        L1f9:
            java.util.List r1 = com.vega.middlebridge.expand.DraftExpandKt.v(r10)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L46d
        L205:
            if (r8 == 0) goto L20c
            java.lang.String r0 = "ai_vc_clone_tone"
            r4.add(r0)
        L20c:
            java.util.List r1 = com.vega.middlebridge.expand.DraftExpandKt.v(r10)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L430
        L218:
            if (r9 == 0) goto L2a4
            com.vega.middlebridge.lyrasession.LyraSession r1 = r9.b()
            if (r1 == 0) goto L248
            com.vega.middlebridge.swig.GetAIClipperInfoReqStruct r0 = new com.vega.middlebridge.swig.GetAIClipperInfoReqStruct
            r0.<init>()
            com.vega.middlebridge.swig.GetAIClipperInfoRespStruct r0 = com.vega.middlebridge.client.AttachmentClient.b(r1, r0)
            if (r0 == 0) goto L248
            com.vega.middlebridge.swig.AttachmentAiClipperInfo r0 = r0.c()
            if (r0 == 0) goto L248
            boolean r0 = r0.d()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            if (r0 == 0) goto L248
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L246
            java.lang.String r0 = "ai_clipper"
            r4.add(r0)
        L246:
            if (r9 == 0) goto L2a4
        L248:
            com.vega.middlebridge.lyrasession.LyraSession r1 = r9.b()
            if (r1 == 0) goto L276
            com.vega.middlebridge.swig.GetAIClipperInfoReqStruct r0 = new com.vega.middlebridge.swig.GetAIClipperInfoReqStruct
            r0.<init>()
            com.vega.middlebridge.swig.GetAIClipperInfoRespStruct r0 = com.vega.middlebridge.client.AttachmentClient.b(r1, r0)
            if (r0 == 0) goto L276
            com.vega.middlebridge.swig.AttachmentAiClipperInfo r0 = r0.c()
            if (r0 == 0) goto L276
            boolean r0 = r0.e()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            if (r0 == 0) goto L276
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L274
            java.lang.String r0 = "editpilot_ai_clipper"
            r4.add(r0)
        L274:
            if (r9 == 0) goto L2a4
        L276:
            com.vega.middlebridge.lyrasession.LyraSession r1 = r9.b()
            if (r1 == 0) goto L2a4
            com.vega.middlebridge.swig.GetAIClipperInfoReqStruct r0 = new com.vega.middlebridge.swig.GetAIClipperInfoReqStruct
            r0.<init>()
            com.vega.middlebridge.swig.GetAIClipperInfoRespStruct r0 = com.vega.middlebridge.client.AttachmentClient.b(r1, r0)
            if (r0 == 0) goto L2a4
            com.vega.middlebridge.swig.AttachmentAiClipperInfo r2 = r0.c()
            if (r2 == 0) goto L2a4
            long r0 = r2.f115997d
            boolean r0 = com.vega.middlebridge.swig.AttachmentAiClipperInfoModuleJNI.AttachmentAiClipperInfo_getIsUsedAiEditAudio(r0, r2)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            if (r0 == 0) goto L2a4
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L2a4
            java.lang.String r0 = "editpilot_audio"
            r4.add(r0)
        L2a4:
            java.util.List r1 = com.vega.middlebridge.expand.DraftExpandKt.y(r10)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L411
        L2b0:
            boolean r0 = com.vega.middlebridge.expand.DraftExpandKt.p1(r10)
            if (r0 == 0) goto L2bb
            java.lang.String r0 = "smart_ads"
            r4.add(r0)
        L2bb:
            if (r9 == 0) goto L348
            com.vega.edit.base.c2pa.C2PAUtil r0 = com.vega.edit.base.c2pa.C2PAUtil.f87111a
            r0.getClass()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            com.vega.middlebridge.lyrasession.LyraSession r1 = r9.b()
            com.vega.middlebridge.swig.GetGenAiInfoReqStruct r0 = new com.vega.middlebridge.swig.GetGenAiInfoReqStruct
            r0.<init>()
            com.vega.middlebridge.swig.GetGenAiInfoRespStruct r0 = com.vega.middlebridge.client.AttachmentClient.i(r1, r0)
            if (r0 == 0) goto L345
            com.vega.middlebridge.swig.AttachmentGenAi r0 = r0.c()
            com.vega.middlebridge.swig.AttachmentAiFuncConfig r0 = r0.d()
            com.vega.middlebridge.swig.VectorOfAttachmentAiFuncInfo r8 = r0.f()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            java.util.Iterator r6 = r8.iterator()
        L2e9:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L305
            java.lang.Object r2 = r6.next()
            r0 = r2
            com.vega.middlebridge.swig.AttachmentAiFuncInfo r0 = (com.vega.middlebridge.swig.AttachmentAiFuncInfo) r0
            com.vega.middlebridge.swig.LVVEGenAIFuncType r1 = r0.d()
            com.vega.middlebridge.swig.LVVEGenAIFuncType r0 = com.vega.middlebridge.swig.LVVEGenAIFuncType.LVVEGenAIEffect
            if (r1 != r0) goto L2e9
            if (r2 == 0) goto L305
            java.lang.String r0 = "genai_ai_effect"
            r7.add(r0)
        L305:
            java.util.Iterator r6 = r8.iterator()
        L309:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L325
            java.lang.Object r2 = r6.next()
            r0 = r2
            com.vega.middlebridge.swig.AttachmentAiFuncInfo r0 = (com.vega.middlebridge.swig.AttachmentAiFuncInfo) r0
            com.vega.middlebridge.swig.LVVEGenAIFuncType r1 = r0.d()
            com.vega.middlebridge.swig.LVVEGenAIFuncType r0 = com.vega.middlebridge.swig.LVVEGenAIFuncType.LVVEGenAIVideoGeneration
            if (r1 != r0) goto L309
            if (r2 == 0) goto L325
            java.lang.String r0 = "genai_video_generate"
            r7.add(r0)
        L325:
            java.util.Iterator r6 = r8.iterator()
        L329:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L345
            java.lang.Object r2 = r6.next()
            r0 = r2
            com.vega.middlebridge.swig.AttachmentAiFuncInfo r0 = (com.vega.middlebridge.swig.AttachmentAiFuncInfo) r0
            com.vega.middlebridge.swig.LVVEGenAIFuncType r1 = r0.d()
            com.vega.middlebridge.swig.LVVEGenAIFuncType r0 = com.vega.middlebridge.swig.LVVEGenAIFuncType.LVVEGenAIImageGeneration
            if (r1 != r0) goto L329
            if (r2 == 0) goto L345
            java.lang.String r0 = "genai_image_generate"
            r7.add(r0)
        L345:
            r4.addAll(r7)
        L348:
            com.vega.middlebridge.swig.ExtraInfo r0 = r10.m()
            java.lang.String r1 = "ai_story"
            if (r0 == 0) goto L363
            com.vega.middlebridge.swig.TrackInfo r0 = r0.f()
            if (r0 == 0) goto L363
            com.vega.middlebridge.swig.VectorOfString r0 = r0.e()
            boolean r0 = r0.contains(r1)
            if (r0 != r5) goto L363
            r4.add(r1)
        L363:
            java.lang.String r1 = r10.k()
            java.lang.String r0 = "image"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            if (r0 != 0) goto L3c3
        L36f:
            java.util.List r1 = com.vega.middlebridge.expand.DraftExpandKt.v(r10)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L3a1
        L37b:
            com.vega.middlebridge.swig.VectorOfTrack r0 = r10.v()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Iterator r6 = r0.iterator()
        L388:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L4f6
            java.lang.Object r2 = r6.next()
            r0 = r2
            com.vega.middlebridge.swig.Track r0 = (com.vega.middlebridge.swig.Track) r0
            com.vega.middlebridge.swig.LVVETrackType r1 = r0.f()
            com.vega.middlebridge.swig.LVVETrackType r0 = com.vega.middlebridge.swig.LVVETrackType.TrackTypeVideo
            if (r1 != r0) goto L388
            r7.add(r2)
            goto L388
        L3a1:
            java.util.Iterator r2 = r1.iterator()
        L3a5:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L37b
            java.lang.Object r0 = r2.next()
            com.vega.middlebridge.swig.SegmentVideo r0 = (com.vega.middlebridge.swig.SegmentVideo) r0
            com.vega.middlebridge.swig.MaterialVideo r0 = r0.O()
            com.vega.middlebridge.swig.LVVEAIGCType r1 = r0.e()
            com.vega.middlebridge.swig.LVVEAIGCType r0 = com.vega.middlebridge.swig.LVVEAIGCType.AIGCTypeAiCreatorStory
            if (r1 != r0) goto L3a5
            java.lang.String r0 = "ai_trends"
            r4.add(r0)
            goto L37b
        L3c3:
            java.util.List r1 = com.vega.middlebridge.expand.DraftExpandKt.v(r10)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L3d0
            goto L36f
        L3d0:
            java.util.Iterator r6 = r1.iterator()
        L3d4:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L36f
            java.lang.Object r0 = r6.next()
            com.vega.middlebridge.swig.SegmentVideo r0 = (com.vega.middlebridge.swig.SegmentVideo) r0
            com.vega.middlebridge.swig.VideoAlgorithm r0 = r0.d0()
            com.vega.middlebridge.swig.VectorOfGameplay r1 = r0.l()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L3f2
            goto L3d4
        L3f2:
            java.util.Iterator r2 = r1.iterator()
        L3f6:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L3d4
            java.lang.Object r0 = r2.next()
            com.vega.middlebridge.swig.Gameplay r0 = (com.vega.middlebridge.swig.Gameplay) r0
            com.vega.middlebridge.swig.LVVELocalAlgorithmType r1 = r0.i()
            com.vega.middlebridge.swig.LVVELocalAlgorithmType r0 = com.vega.middlebridge.swig.LVVELocalAlgorithmType.ImageEditorAITemplate
            if (r1 != r0) goto L3f6
            java.lang.String r0 = "image_inspiration"
            r4.add(r0)
            goto L36f
        L411:
            java.util.Iterator r2 = r1.iterator()
        L415:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L2b0
            java.lang.Object r0 = r2.next()
            com.vega.middlebridge.swig.Segment r0 = (com.vega.middlebridge.swig.Segment) r0
            com.vega.middlebridge.swig.LVVESegmentSource r1 = r0.i()
            com.vega.middlebridge.swig.LVVESegmentSource r0 = com.vega.middlebridge.swig.LVVESegmentSource.SegmentSourceSmartEditText
            if (r1 != r0) goto L415
            java.lang.String r0 = "editpilot_text"
            r4.add(r0)
            goto L2b0
        L430:
            java.util.Iterator r7 = r1.iterator()
        L434:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L218
            java.lang.Object r0 = r7.next()
            com.vega.middlebridge.swig.SegmentVideo r0 = (com.vega.middlebridge.swig.SegmentVideo) r0
            com.vega.middlebridge.swig.VideoAlgorithm r0 = r0.d0()
            com.vega.middlebridge.swig.VectorOfAlgorithm r0 = r0.i()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            java.util.Iterator r6 = r0.iterator()
        L44f:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L434
            java.lang.Object r2 = r6.next()
            r0 = r2
            com.vega.middlebridge.swig.Algorithm r0 = (com.vega.middlebridge.swig.Algorithm) r0
            com.vega.middlebridge.swig.LVVELocalAlgorithmType r1 = r0.h()
            com.vega.middlebridge.swig.LVVELocalAlgorithmType r0 = com.vega.middlebridge.swig.LVVELocalAlgorithmType.ImageInterpretation
            if (r1 != r0) goto L44f
            if (r2 == 0) goto L434
            java.lang.String r0 = "photo_acting"
            r4.add(r0)
            goto L218
        L46d:
            java.util.Iterator r1 = r1.iterator()
        L471:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L205
            java.lang.Object r0 = r1.next()
            com.vega.middlebridge.swig.SegmentVideo r0 = (com.vega.middlebridge.swig.SegmentVideo) r0
            boolean r0 = com.vega.middlebridge.expand.DraftExpandKt.c1(r0)
            if (r0 == 0) goto L471
            java.lang.String r0 = "ai_repainting"
            r4.add(r0)
            goto L205
        L48a:
            java.util.Iterator r7 = r7.iterator()
        L48e:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L1e3
            java.lang.Object r0 = r7.next()
            com.vega.middlebridge.swig.Track r0 = (com.vega.middlebridge.swig.Track) r0
            com.vega.middlebridge.swig.VectorOfSegment r1 = r0.e()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            java.lang.Class<com.vega.middlebridge.swig.SegmentVideo> r0 = com.vega.middlebridge.swig.SegmentVideo.class
            java.util.List r1 = kotlin.collections.CollectionsKt___CollectionsJvmKt.filterIsInstance(r1, r0)
            boolean r0 = r1 instanceof java.util.Collection
            if (r0 == 0) goto L4b5
            r0 = r1
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L4b5
            goto L48e
        L4b5:
            java.util.Iterator r6 = r1.iterator()
        L4b9:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L48e
            java.lang.Object r0 = r6.next()
            com.vega.middlebridge.swig.SegmentVideo r0 = (com.vega.middlebridge.swig.SegmentVideo) r0
            com.vega.middlebridge.swig.VideoAlgorithm r0 = r0.d0()
            com.vega.middlebridge.swig.VectorOfAlgorithm r1 = r0.i()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L4d7
            goto L4b9
        L4d7:
            java.util.Iterator r2 = r1.iterator()
        L4db:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L4b9
            java.lang.Object r0 = r2.next()
            com.vega.middlebridge.swig.Algorithm r0 = (com.vega.middlebridge.swig.Algorithm) r0
            com.vega.middlebridge.swig.LVVELocalAlgorithmType r1 = r0.h()
            com.vega.middlebridge.swig.LVVELocalAlgorithmType r0 = com.vega.middlebridge.swig.LVVELocalAlgorithmType.AIBackground
            if (r1 != r0) goto L4db
            java.lang.String r0 = "ai_background"
            r4.add(r0)
            goto L1e3
        L4f6:
            boolean r0 = r7.isEmpty()
            if (r0 == 0) goto L526
        L4fc:
            java.util.List r1 = com.vega.middlebridge.expand.DraftExpandKt.v(r10)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L509
        L508:
            return r4
        L509:
            java.util.Iterator r1 = r1.iterator()
        L50d:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L508
            java.lang.Object r0 = r1.next()
            com.vega.middlebridge.swig.SegmentVideo r0 = (com.vega.middlebridge.swig.SegmentVideo) r0
            com.vega.middlebridge.swig.MaterialTransition r0 = r0.c0()
            if (r0 == 0) goto L50d
            boolean r0 = r0.i()
            if (r0 != r5) goto L50d
            goto L58b
        L526:
            java.util.Iterator r7 = r7.iterator()
        L52a:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L4fc
            java.lang.Object r0 = r7.next()
            com.vega.middlebridge.swig.Track r0 = (com.vega.middlebridge.swig.Track) r0
            com.vega.middlebridge.swig.VectorOfSegment r1 = r0.e()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            java.lang.Class<com.vega.middlebridge.swig.SegmentVideo> r0 = com.vega.middlebridge.swig.SegmentVideo.class
            java.util.List r1 = kotlin.collections.CollectionsKt___CollectionsJvmKt.filterIsInstance(r1, r0)
            boolean r0 = r1 instanceof java.util.Collection
            if (r0 == 0) goto L551
            r0 = r1
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L551
            goto L52a
        L551:
            java.util.Iterator r6 = r1.iterator()
        L555:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L52a
            java.lang.Object r0 = r6.next()
            com.vega.middlebridge.swig.SegmentVideo r0 = (com.vega.middlebridge.swig.SegmentVideo) r0
            com.vega.middlebridge.swig.VideoAlgorithm r0 = r0.d0()
            com.vega.middlebridge.swig.VectorOfAlgorithm r1 = r0.i()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L573
            goto L555
        L573:
            java.util.Iterator r2 = r1.iterator()
        L577:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L555
            java.lang.Object r0 = r2.next()
            com.vega.middlebridge.swig.Algorithm r0 = (com.vega.middlebridge.swig.Algorithm) r0
            com.vega.middlebridge.swig.LVVELocalAlgorithmType r1 = r0.h()
            com.vega.middlebridge.swig.LVVELocalAlgorithmType r0 = com.vega.middlebridge.swig.LVVELocalAlgorithmType.AigcGamePlay
            if (r1 != r0) goto L577
        L58b:
            java.lang.String r0 = "ai_effect"
            r4.add(r0)
            goto L508
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.base.c2pa.C2PAUtil.a(com.vega.container.session.core.ISession, com.vega.middlebridge.swig.Draft):java.util.List");
    }

    public static void b(final AppContext appContext) {
        Intrinsics.checkNotNullParameter(appContext, "");
        ThreadUtilKt.e(0L, new Function0<Unit>() { // from class: com.vega.edit.base.c2pa.C2PAUtil$initSDK$1
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                if (!C2PAUtil.b) {
                    BLog.i("C2PAUtil", "initSDK");
                    TTC2PA ttc2pa = TTC2PA.f47103a;
                    C2PADependImpl c2PADependImpl = new C2PADependImpl(appContext);
                    ttc2pa.getClass();
                    if (TTC2PA.b == null) {
                        TTC2PA.b = c2PADependImpl;
                        TTC2PA.b().d();
                        TTC2PA.b().getIoExecutor();
                        PThreadScheduledThreadPoolExecutor pThreadScheduledThreadPoolExecutor = new PThreadScheduledThreadPoolExecutor(1, new C2paThreadFactory());
                        TTC2PA.f47104c = pThreadScheduledThreadPoolExecutor;
                        pThreadScheduledThreadPoolExecutor.submit(new Runnable() { // from class: X.4qk
                            @Override // java.lang.Runnable
                            public final void run() throws IOException, UnhandledException {
                                RustBuffer.ByValue byValueA;
                                byte[] byteArray;
                                String string;
                                SignCertManager.CertState certState;
                                TTC2PA.f47103a.getClass();
                                String str = (String) TTC2PA.a().getSecond();
                                Intrinsics.checkNotNullParameter(str, "");
                                FuncParamsX913809d0daa224e7 funcParamsX913809d0daa224e7 = new FuncParamsX913809d0daa224e7(str, ByteString.EMPTY);
                                int iEncodedSize = FuncParamsX913809d0daa224e7.ADAPTER.encodedSize(funcParamsX913809d0daa224e7);
                                if (iEncodedSize > 0) {
                                    RustBuffer.Companion.getClass();
                                    byValueA = RustBuffer.Companion.a(iEncodedSize);
                                    Pointer pointer = byValueA.data;
                                    Intrinsics.checkNotNull(pointer);
                                    MemoryOutputStream memoryOutputStream = new MemoryOutputStream(pointer);
                                    funcParamsX913809d0daa224e7.encode(memoryOutputStream);
                                    memoryOutputStream.close();
                                } else {
                                    RustBuffer.Companion.getClass();
                                    byValueA = RustBuffer.Companion.a(0);
                                }
                                DefaultCallStatusErrorHandler defaultCallStatusErrorHandler = DefaultCallStatusErrorHandler.f47100a;
                                RustCallStatus rustCallStatus = new RustCallStatus();
                                NativeLib.Companion.getClass();
                                RustBuffer.ByValue byValueMolten_ffi_tt_c2pa_sdk_ttc2pa_set_temp_dir_7e8f = NativeLib.Companion.a().molten_ffi_tt_c2pa_sdk_ttc2pa_set_temp_dir_7e8f(byValueA, rustCallStatus);
                                String str2 = null;
                                if (!rustCallStatus.isSuccess()) {
                                    if (rustCallStatus.isError()) {
                                        throw defaultCallStatusErrorHandler.a(rustCallStatus.error_buf);
                                    }
                                    if (!rustCallStatus.isPanic()) {
                                        throw new UnhandledException("Unknown rust call status: " + rustCallStatus + ".code");
                                    }
                                    RustBuffer.ByValue byValue = rustCallStatus.error_buf;
                                    int i = byValue.len;
                                    if (i <= 0) {
                                        throw new UnhandledException("Rust panic");
                                    }
                                    try {
                                        Pointer pointer2 = byValue.data;
                                        if (pointer2 != null && (byteArray = pointer2.getByteArray(0L, i)) != null) {
                                            str2 = new String(byteArray, Charsets.UTF_8);
                                        }
                                        Intrinsics.checkNotNull(str2);
                                        RustBuffer.Companion companion = RustBuffer.Companion;
                                        RustBuffer.ByValue byValue2 = rustCallStatus.error_buf;
                                        companion.getClass();
                                        RustBuffer.Companion.c(byValue2);
                                        throw new UnhandledException(str2);
                                    } catch (Throwable th) {
                                        RustBuffer.Companion companion2 = RustBuffer.Companion;
                                        RustBuffer.ByValue byValue3 = rustCallStatus.error_buf;
                                        companion2.getClass();
                                        RustBuffer.Companion.c(byValue3);
                                        throw th;
                                    }
                                }
                                RustBuffer.Companion.getClass();
                                byte[] bArrB = RustBuffer.Companion.b(byValueMolten_ffi_tt_c2pa_sdk_ttc2pa_set_temp_dir_7e8f);
                                ProtoAdapter<FuncReturnX0bf8b5c665c57f35> protoAdapter = FuncReturnX0bf8b5c665c57f35.ADAPTER;
                                Intrinsics.checkNotNull(bArrB);
                                Intrinsics.checkNotNullExpressionValue(protoAdapter.decode(bArrB).f151274a, "");
                                SignCertManager signCertManager = SignCertManager.f47113a;
                                signCertManager.getClass();
                                TTC2PA.b().d();
                                SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.f47112a;
                                Context context = TTC2PA.b().getContext();
                                sharedPreferencesUtils.getClass();
                                Intrinsics.checkNotNullParameter(context, "");
                                try {
                                    string = SharedPreferencesUtils.INVOKEVIRTUAL_com_bytedance_ttc2pa_localfiles_SharedPreferencesUtils_com_vega_launcher_lancet_SharedPreferencesLancet_getSharedPreferences(context, "ttc2pa_main_process_config", 0).getString("ttc2pa_config", "");
                                } catch (Throwable th2) {
                                    th2.printStackTrace();
                                    string = null;
                                }
                                boolean z = string == null || string.length() == 0;
                                if (z) {
                                    certState = new SignCertManager.CertState(-5L, "Local cert info empty", null);
                                } else {
                                    try {
                                        JSONObject jSONObject = new JSONObject(string);
                                        String strOptString = jSONObject.optString("cert_chain");
                                        if (StringUtils.isEmpty(strOptString)) {
                                            certState = new SignCertManager.CertState(-3L, "Local cert_chain is empty", null);
                                        } else {
                                            String strOptString2 = jSONObject.optString("cert_fingerprint");
                                            if (StringUtils.isEmpty(strOptString2)) {
                                                certState = new SignCertManager.CertState(-4L, "Local cert_fingerprint is empty", null);
                                            } else {
                                                Intrinsics.checkNotNullExpressionValue(strOptString, "");
                                                Charset charset = Charsets.UTF_8;
                                                byte[] bytes = strOptString.getBytes(charset);
                                                Intrinsics.checkNotNullExpressionValue(bytes, "");
                                                byte[] bArrDecode = Base64.decode(bytes, 0);
                                                Intrinsics.checkNotNullExpressionValue(bArrDecode, "");
                                                String str3 = new String(bArrDecode, charset);
                                                Pair pairD = SignCertManager.d(str3);
                                                if (((Boolean) pairD.getFirst()).booleanValue()) {
                                                    Intrinsics.checkNotNullExpressionValue(strOptString2, "");
                                                    certState = new SignCertManager.CertState(0L, "", new SignCertManager.CertInfo(strOptString2, str3));
                                                } else {
                                                    certState = new SignCertManager.CertState(-14L, "Verify local cert failed. cert_len:" + str3.length() + " fp:" + strOptString2 + ", e:" + ((String) pairD.getSecond()) + '}', null);
                                                }
                                            }
                                        }
                                    } catch (Throwable th3) {
                                        certState = new SignCertManager.CertState(-5L, "Local cert error:" + th3, null);
                                    }
                                }
                                SignCertManager.f = certState;
                                if (SignCertManager.f.f47118c == null) {
                                    Logger.f47121a.getClass();
                                    SignCertManager.a(SignCertManager.f);
                                    signCertManager.c();
                                }
                            }
                        });
                    }
                    C2PAUtil.b = true;
                }
                return Unit.INSTANCE;
            }
        });
    }

    public static boolean c(ISession iSession, Draft draft) {
        if (!b) {
            BLog.i("C2PAUtil", "needSign, not init, return false");
            return false;
        }
        if (!a(iSession, draft).isEmpty()) {
            return true;
        }
        BLog.i("C2PAUtil", "needSign, bizList is empty, return false");
        return false;
    }

    public static SignResult e(String str, List list) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(list, "");
        BLog.i("C2PAUtil", "signFileSyncWithTimeout, filePath: " + str + ", bizList:" + list);
        return (SignResult) BuildersKt__BuildersKt.runBlocking$default(null, new C2PAUtil$signFileSync$1(str, list, null), 1, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0262  */
    /* JADX WARN: Type inference failed for: r6v4, types: [com.vega.edit.base.c2pa.C2PAUtil$signFileAsync$1] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object d(java.lang.String r26, java.util.List<java.lang.String> r27, kotlin.coroutines.Continuation<? super com.vega.edit.base.c2pa.C2PAUtil.SignResult> r28) {
        /*
            r25 = this;
            r4 = r28
            r2 = r27
            boolean r0 = r4 instanceof com.vega.edit.base.c2pa.C2PAUtil$signFile$1
            if (r0 == 0) goto L262
            r5 = r4
            com.vega.edit.base.c2pa.C2PAUtil$signFile$1 r5 = (com.vega.edit.base.c2pa.C2PAUtil$signFile$1) r5
            int r3 = r5.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r3 & r1
            if (r0 == 0) goto L262
            int r3 = r3 - r1
            r5.t = r3
        L16:
            java.lang.Object r3 = r5.r
            java.lang.Object r12 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r5.t
            r13 = 4
            r6 = 2
            r17 = 0
            r8 = 1
            if (r0 == 0) goto L90
            if (r0 != r8) goto L26b
            java.lang.Object r2 = r5.q
            java.util.List r2 = (java.util.List) r2
            kotlin.ResultKt.throwOnFailure(r3)
        L2e:
            com.vega.edit.base.c2pa.C2PAUtil$SignResult r3 = (com.vega.edit.base.c2pa.C2PAUtil.SignResult) r3
            com.vega.report.ReportManagerWrapper r4 = com.vega.report.ReportManagerWrapper.INSTANCE
            r0 = 5
            kotlin.Pair[] r5 = new kotlin.Pair[r0]
            int r0 = r3.f87113c
            if (r0 != 0) goto L8d
            java.lang.String r1 = "success"
        L3b:
            java.lang.String r0 = "status"
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r1)
            r5[r17] = r0
            long r0 = r3.b
            java.lang.String r1 = java.lang.String.valueOf(r0)
            java.lang.String r0 = "c2pa_cost"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r0, r1)
            r0 = 1
            r5[r0] = r1
            java.lang.String r14 = ","
            r15 = 0
            r19 = 62
            r16 = r15
            r18 = r15
            r13 = r2
            java.lang.String r1 = kotlin.collections.CollectionsKt.j(r13, r14, r15, r16, r17, r18, r19)
            java.lang.String r0 = "c2pa_list"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r0, r1)
            r0 = 2
            r5[r0] = r1
            int r0 = r3.f87113c
            java.lang.String r1 = java.lang.String.valueOf(r0)
            java.lang.String r0 = "err_code"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r0, r1)
            r0 = 3
            r5[r0] = r1
            java.lang.String r1 = r3.f87114d
            java.lang.String r0 = "err_msg"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r0, r1)
            r0 = 4
            r5[r0] = r1
            java.util.Map r1 = kotlin.collections.MapsKt__MapsKt.mapOf(r5)
            java.lang.String r0 = "c2pa_status"
            r4.onEvent(r0, r1)
            return r3
        L8d:
            java.lang.String r1 = "fail"
            goto L3b
        L90:
            kotlin.ResultKt.throwOnFailure(r3)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "signFileWithTimeout, filePath: "
            r1.<init>(r0)
            r7 = r26
            r1.append(r7)
            java.lang.String r0 = ", bizList: "
            r1.append(r0)
            r1.append(r2)
            java.lang.String r0 = r1.toString()
            java.lang.String r9 = "C2PAUtil"
            com.vega.log.BLog.i(r9, r0)
            r5.q = r2
            r5.t = r8
            kotlinx.coroutines.CancellableContinuationImpl r4 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r5)
            r4.<init>(r0, r8)
            r4.initCancellability()
            java.lang.Class<com.lemon.lv.config.C2PACommonConfigSetting> r0 = com.lemon.lv.config.C2PACommonConfigSetting.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            com.vega.config.IConfig r0 = com.vega.config.ConfigSettingsKt.a(r0)
            com.lemon.lv.config.C2PACommonConfig r0 = (com.lemon.lv.config.C2PACommonConfig) r0
            boolean r3 = r0.writeDirectly
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "signFile, writeDirectly: "
            r1.<init>(r0)
            r1.append(r3)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.i(r9, r0)
            if (r3 == 0) goto L235
            r14 = r7
        Le2:
            com.vega.edit.base.c2pa.C2PAUtil r0 = com.vega.edit.base.c2pa.C2PAUtil.f87111a
            r0.getClass()
            kotlin.Pair[] r11 = new kotlin.Pair[r13]
            java.lang.String r1 = "claim_generator"
            java.lang.String r0 = "capcut"
            kotlin.Pair r0 = kotlin.TuplesKt.to(r1, r0)
            r11[r17] = r0
            java.lang.String r10 = "input_file"
            kotlin.Pair r0 = kotlin.TuplesKt.to(r10, r7)
            r1 = 1
            r11[r1] = r0
            java.lang.String r8 = "output_file"
            kotlin.Pair r0 = kotlin.TuplesKt.to(r8, r14)
            r11[r6] = r0
            java.lang.Object[] r0 = new java.lang.Object[r1]
            r16 = r0
            kotlin.Pair[] r7 = new kotlin.Pair[r6]
            java.lang.String r6 = "label"
            java.lang.String r0 = "c2pa.actions"
            kotlin.Pair r0 = kotlin.TuplesKt.to(r6, r0)
            r7[r17] = r0
            kotlin.Pair[] r15 = new kotlin.Pair[r1]
            java.lang.Object[] r6 = new java.lang.Object[r1]
            kotlin.Pair[] r1 = new kotlin.Pair[r13]
            java.lang.String r13 = "action"
            java.lang.String r0 = "c2pa.created"
            kotlin.Pair r0 = kotlin.TuplesKt.to(r13, r0)
            r1[r17] = r0
            long r13 = java.lang.System.currentTimeMillis()
            java.lang.String r13 = java.lang.String.valueOf(r13)
            java.lang.String r0 = "when"
            kotlin.Pair r13 = kotlin.TuplesKt.to(r0, r13)
            r0 = 1
            r1[r0] = r13
            r0 = 2
            kotlin.Pair[] r13 = new kotlin.Pair[r0]
            java.lang.String r14 = "name"
            java.lang.String r0 = "CapCut App"
            kotlin.Pair r0 = kotlin.TuplesKt.to(r14, r0)
            r13[r17] = r0
            com.vega.core.context.AppProperty r0 = com.vega.core.context.ContextExtKt.app()
            r0.K()
            java.lang.String r14 = "19.6.0"
            java.lang.String r0 = "version"
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r14)
            r14 = 1
            r13[r14] = r0
            org.json.JSONObject r13 = com.vega.core.ext.JSONObjectExKt.e(r13)
            java.lang.String r0 = "softwareAgent"
            kotlin.Pair r13 = kotlin.TuplesKt.to(r0, r13)
            r0 = 2
            r1[r0] = r13
            kotlin.Pair[] r0 = new kotlin.Pair[r14]
            java.lang.String r19 = ","
            r20 = 0
            r24 = 62
            r21 = r20
            r23 = r20
            r18 = r2
            r22 = r17
            java.lang.String r14 = kotlin.collections.CollectionsKt.j(r18, r19, r20, r21, r22, r23, r24)
            java.lang.String r13 = "from"
            kotlin.Pair r13 = kotlin.TuplesKt.to(r13, r14)
            r0[r17] = r13
            org.json.JSONObject r13 = com.vega.core.ext.JSONObjectExKt.e(r0)
            java.lang.String r0 = "parameters"
            kotlin.Pair r13 = kotlin.TuplesKt.to(r0, r13)
            r0 = 3
            r1[r0] = r13
            org.json.JSONObject r0 = com.vega.core.ext.JSONObjectExKt.e(r1)
            r6[r17] = r0
            org.json.JSONArray r1 = com.vega.core.ext.JSONObjectExKt.c(r6)
            java.lang.String r0 = "actions"
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r1)
            r15[r17] = r0
            org.json.JSONObject r1 = com.vega.core.ext.JSONObjectExKt.e(r15)
            java.lang.String r0 = "data"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r0, r1)
            r0 = 1
            r7[r0] = r1
            org.json.JSONObject r0 = com.vega.core.ext.JSONObjectExKt.e(r7)
            r16[r17] = r0
            org.json.JSONArray r1 = com.vega.core.ext.JSONObjectExKt.c(r16)
            java.lang.String r0 = "assertions"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r0, r1)
            r0 = 3
            r11[r0] = r1
            org.json.JSONObject r6 = com.vega.core.ext.JSONObjectExKt.e(r11)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "signFile, manifest: "
            r1.<init>(r0)
            r1.append(r6)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.i(r9, r0)
            com.vega.edit.base.c2pa.C2PAUtil$signFileSuspend$2$1 r0 = new com.vega.edit.base.c2pa.C2PAUtil$signFileSuspend$2$1
            r0.<init>()
            java.lang.String r21 = r6.optString(r10)
            java.lang.String r22 = r6.optString(r8)
            long r19 = android.os.SystemClock.uptimeMillis()
            com.bytedance.ttc2pa.TTC2PA r1 = com.bytedance.ttc2pa.TTC2PA.f47103a
            java.lang.String r7 = r6.toString()
            java.lang.String r8 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
            com.vega.edit.base.c2pa.C2PAUtil$signFileAsync$1 r6 = new com.vega.edit.base.c2pa.C2PAUtil$signFileAsync$1
            r18 = r6
            r23 = r0
            r24 = r3
            r18.<init>()
            r1.getClass()
            com.bytedance.ttc2pa.ITTC2PADepend r0 = com.bytedance.ttc2pa.TTC2PA.b
            if (r0 != 0) goto L215
            r1 = -1
            java.lang.String r0 = "Must call init before use"
            r6.a(r1, r0, r8)
        L205:
            java.lang.Object r3 = r4.getResult()
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r3 != r0) goto L212
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r5)
        L212:
            if (r3 != r12) goto L2e
            return r12
        L215:
            org.json.JSONObject r9 = new org.json.JSONObject
            r9.<init>()
            java.lang.String r8 = "tsa_timeout"
            r0 = 10000(0x2710, double:4.9407E-320)
            r9.put(r8, r0)
            java.util.concurrent.ScheduledExecutorService r1 = com.bytedance.ttc2pa.TTC2PA.f47104c
            if (r1 == 0) goto L22e
        L225:
            X.4qj r0 = new X.4qj
            r0.<init>()
            r1.submit(r0)
            goto L205
        L22e:
            java.lang.String r0 = "mExecutor"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r1 = 0
            goto L225
        L235:
            java.lang.String r1 = "/"
            java.lang.String r0 = X.C93472yG.f0(r7, r1)
            java.lang.String r10 = X.C93472yG.Z(r7, r1)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            java.lang.String r0 = "/signed_"
            r8.append(r0)
            r3 = 0
            long r0 = java.lang.System.currentTimeMillis()
            r8.append(r0)
            r0 = 95
            r8.append(r0)
            r8.append(r10)
            java.lang.String r14 = r8.toString()
            goto Le2
        L262:
            com.vega.edit.base.c2pa.C2PAUtil$signFile$1 r5 = new com.vega.edit.base.c2pa.C2PAUtil$signFile$1
            r0 = r25
            r5.<init>(r0, r4)
            goto L16
        L26b:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.base.c2pa.C2PAUtil.d(java.lang.String, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }
}