package com.lemon.editexport.report;

import com.lemon.editexport.base.IExportTimeReporter;
import com.vega.container.session.core.ISession;
import com.vega.core.ext.ExtentionKt;
import com.vega.middlebridge.expand.DraftExpandKt;
import com.vega.middlebridge.swig.Draft;
import com.vega.middlebridge.swig.SegmentAudio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes12.dex */
public final class ElevenLabsReporter implements IExportTimeReporter {

    /* renamed from: a, reason: collision with root package name */
    public final ISession f58583a;

    public ElevenLabsReporter(ISession iSession) {
        Intrinsics.checkNotNullParameter(iSession, "");
        this.f58583a = iSession;
    }

    @Override // com.lemon.editexport.base.IExportTimeReporter
    public final Object b(Draft draft, HashMap<String, Object> map, Continuation<? super Unit> continuation) {
        ArrayList arrayList = new ArrayList();
        if (draft != null) {
            Iterator it = ((ArrayList) DraftExpandKt.u(draft)).iterator();
            while (it.hasNext()) {
                SegmentAudio segmentAudio = (SegmentAudio) it.next();
                if (ExtentionKt.isNotNullOrEmpty(segmentAudio.u().o()) && !Intrinsics.areEqual(segmentAudio.u().o(), segmentAudio.u().I())) {
                    String strO = segmentAudio.u().o();
                    Intrinsics.checkNotNullExpressionValue(strO, "");
                    arrayList.add(strO);
                }
            }
        }
        if (!arrayList.isEmpty()) {
            map.put("imitation_tone_id", CollectionsKt___CollectionsKt.joinToString$default(arrayList, null, null, null, 0, null, null, 63, null));
        }
        return Unit.INSTANCE;
    }
}