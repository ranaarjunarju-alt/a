package com.vega.image.edit.clipflow2.common;

import androidx.core.app.NotificationCompat;
import androidx.core.view.MotionEventCompat;
import com.bytedance.apm.launch.evil.LaunchEvilMethodManager;
import com.google.gson.annotations.SerializedName;
import com.vega.clipflow.ClipflowAsyncNode;
import com.vega.clipflow.ITaskContext;
import com.vega.clipflow.ITaskContextNode;
import com.vega.clipflow.TaskContextAbility;
import java.util.Map;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes9.dex */
public final class ImageEditResolveUploadArtifactNode extends ClipflowAsyncNode<Input, ImageEditUploadResult> implements ITaskContextNode {
    public final ImageEditUploadFailureMode s;
    public final ImageEditArtifactProducer t;
    public final String u;
    public ITaskContext v;

    /* loaded from: classes20.dex */
    public static final class Companion {
    }

    /* loaded from: classes15.dex */
    public static final class Input {

        @SerializedName("auth_timeout_ms")
        public final long authTimeoutMs;

        @SerializedName("cache_time_h")
        public final int cacheTimeH;

        @SerializedName("height")
        public final int height;

        @SerializedName("known_artifact")
        public final ImageEditUploadResult knownArtifact;

        @SerializedName("prepared_path")
        public final String preparedPath;

        @SerializedName("report_params")
        public final Map<String, String> reportParams;

        @SerializedName("upload_timeout_ms")
        public final long uploadTimeoutMs;

        @SerializedName("width")
        public final int width;

        /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.lang.Object[] */
        /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.lang.Object[] */
        /* JADX DEBUG: Multi-variable search result rejected for r1v3, resolved type: java.lang.Object[] */
        /* JADX WARN: Illegal instructions before constructor call */
        /* JADX WARN: Multi-variable type inference failed */
        public Input() {
            int i = 0;
            long j = 0;
            this(null, i, i, 0 == true ? 1 : 0, i, j, j, 0 == true ? 1 : 0, MotionEventCompat.ACTION_MASK, 0 == true ? 1 : 0);
        }

        public Input(String str, int i, int i2, ImageEditUploadResult imageEditUploadResult, int i3, long j, long j2, Map<String, String> map) {
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(map, "");
            this.preparedPath = str;
            this.width = i;
            this.height = i2;
            this.knownArtifact = imageEditUploadResult;
            this.cacheTimeH = i3;
            this.uploadTimeoutMs = j;
            this.authTimeoutMs = j2;
            this.reportParams = map;
        }

        /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x003c: CONSTRUCTOR 
          (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x000d: ARITH (r23v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? ("") : (r13v0 java.lang.String))
          (wrap:int:?: TERNARY null = ((wrap:int:0x0013: ARITH (r23v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? (0 int) : (r14v0 int))
          (wrap:int:?: TERNARY null = ((wrap:int:0x0019: ARITH (r23v0 int) & (4 int) A[WRAPPED]) != (0 int)) ? (0 int) : (r15v0 int))
          (wrap:com.vega.image.edit.clipflow2.common.ImageEditUploadResult:?: TERNARY null = ((wrap:int:0x001e: ARITH (r23v0 int) & (8 int) A[WRAPPED]) != (0 int)) ? (null com.vega.image.edit.clipflow2.common.ImageEditUploadResult) : (r16v0 com.vega.image.edit.clipflow2.common.ImageEditUploadResult))
          (wrap:int:?: TERNARY null = ((wrap:int:0x0023: ARITH (r23v0 int) & (16 int) A[WRAPPED]) == (0 int)) ? (r17v0 int) : (0 int))
          (wrap:long:?: TERNARY null = ((wrap:int:0x0027: ARITH (r23v0 int) & (32 int) A[WRAPPED]) != (0 int)) ? (0 long) : (r18v0 long))
          (wrap:long:?: TERNARY null = ((wrap:int:0x002d: ARITH (r23v0 int) & (64 int) A[WRAPPED]) != (0 int)) ? (wrap:long:0x0031: SGET  A[WRAPPED] com.bytedance.apm.launch.evil.LaunchEvilMethodManager.MAX_INTERVAL long) : (r20v0 long))
          (wrap:java.util.Map:?: TERNARY null = ((wrap:int:0x0033: ARITH (r23v0 int) & (wrap:??:SGET  A[WRAPPED] androidx.core.app.NotificationCompat.FLAG_HIGH_PRIORITY int) A[WRAPPED]) != (0 int)) ? (wrap:java.util.Map:0x0037: INVOKE  STATIC call: kotlin.collections.MapsKt__MapsKt.emptyMap():java.util.Map A[MD:<K, V>:():java.util.Map<K, V> (m), WRAPPED]) : (r22v0 java.util.Map))
         A[MD:(java.lang.String, int, int, com.vega.image.edit.clipflow2.common.ImageEditUploadResult, int, long, long, java.util.Map<java.lang.String, java.lang.String>):void (m)] call: com.vega.image.edit.clipflow2.common.ImageEditResolveUploadArtifactNode.Input.<init>(java.lang.String, int, int, com.vega.image.edit.clipflow2.common.ImageEditUploadResult, int, long, long, java.util.Map):void type: THIS */
        public /* synthetic */ Input(String str, int i, int i2, ImageEditUploadResult imageEditUploadResult, int i3, long j, long j2, Map map, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this((i4 & 1) != 0 ? "" : str, (i4 & 2) != 0 ? 0 : i, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? null : imageEditUploadResult, (i4 & 16) == 0 ? i3 : 0, (i4 & 32) != 0 ? 0L : j, (i4 & 64) != 0 ? LaunchEvilMethodManager.MAX_INTERVAL : j2, (i4 & NotificationCompat.FLAG_HIGH_PRIORITY) != 0 ? MapsKt__MapsKt.emptyMap() : map);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.vega.image.edit.clipflow2.common.ImageEditResolveUploadArtifactNode$Input */
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Input copy$default(Input input, String str, int i, int i2, ImageEditUploadResult imageEditUploadResult, int i3, long j, long j2, Map map, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                str = input.preparedPath;
            }
            if ((i4 & 2) != 0) {
                i = input.width;
            }
            if ((i4 & 4) != 0) {
                i2 = input.height;
            }
            if ((i4 & 8) != 0) {
                imageEditUploadResult = input.knownArtifact;
            }
            if ((i4 & 16) != 0) {
                i3 = input.cacheTimeH;
            }
            if ((i4 & 32) != 0) {
                j = input.uploadTimeoutMs;
            }
            if ((i4 & 64) != 0) {
                j2 = input.authTimeoutMs;
            }
            if ((i4 & NotificationCompat.FLAG_HIGH_PRIORITY) != 0) {
                map = input.reportParams;
            }
            return input.copy(str, i, i2, imageEditUploadResult, i3, j, j2, map);
        }

        public final Input copy(String str, int i, int i2, ImageEditUploadResult imageEditUploadResult, int i3, long j, long j2, Map<String, String> map) {
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(map, "");
            return new Input(str, i, i2, imageEditUploadResult, i3, j, j2, map);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Input)) {
                return false;
            }
            Input input = (Input) obj;
            return Intrinsics.areEqual(this.preparedPath, input.preparedPath) && this.width == input.width && this.height == input.height && Intrinsics.areEqual(this.knownArtifact, input.knownArtifact) && this.cacheTimeH == input.cacheTimeH && this.uploadTimeoutMs == input.uploadTimeoutMs && this.authTimeoutMs == input.authTimeoutMs && Intrinsics.areEqual(this.reportParams, input.reportParams);
        }

        public final long getAuthTimeoutMs() {
            return this.authTimeoutMs;
        }

        public final int getCacheTimeH() {
            return this.cacheTimeH;
        }

        public final int getHeight() {
            return this.height;
        }

        public final ImageEditUploadResult getKnownArtifact() {
            return this.knownArtifact;
        }

        public final String getPreparedPath() {
            return this.preparedPath;
        }

        public final Map<String, String> getReportParams() {
            return this.reportParams;
        }

        public final long getUploadTimeoutMs() {
            return this.uploadTimeoutMs;
        }

        public final int getWidth() {
            return this.width;
        }

        public int hashCode() {
            int iHashCode = ((((this.preparedPath.hashCode() * 31) + this.width) * 31) + this.height) * 31;
            ImageEditUploadResult imageEditUploadResult = this.knownArtifact;
            int iHashCode2 = (((iHashCode + (imageEditUploadResult == null ? 0 : imageEditUploadResult.hashCode())) * 31) + this.cacheTimeH) * 31;
            long j = this.uploadTimeoutMs;
            int i = (iHashCode2 + ((int) (j ^ (j >>> 32)))) * 31;
            long j2 = this.authTimeoutMs;
            return ((i + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.reportParams.hashCode();
        }

        public String toString() {
            return "Input(preparedPath=" + this.preparedPath + ", width=" + this.width + ", height=" + this.height + ", knownArtifact=" + this.knownArtifact + ", cacheTimeH=" + this.cacheTimeH + ", uploadTimeoutMs=" + this.uploadTimeoutMs + ", authTimeoutMs=" + this.authTimeoutMs + ", reportParams=" + this.reportParams + ')';
        }
    }

    static {
        new Companion();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImageEditResolveUploadArtifactNode(String str, ImageEditUploadFailureMode imageEditUploadFailureMode, ImageEditArtifactProducer imageEditArtifactProducer) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(imageEditUploadFailureMode, "");
        Intrinsics.checkNotNullParameter(imageEditArtifactProducer, "");
        this.s = imageEditUploadFailureMode;
        this.t = imageEditArtifactProducer;
        this.u = "ImageEditResolveUploadArtifactNode";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void J(java.lang.String r8) {
        /*
            r7 = this;
            boolean r0 = kotlin.text.StringsKt__StringsKt.isBlank(r8)
            r6 = 1
            r0 = r0 ^ 1
            r4 = 0
            if (r0 == 0) goto L54
            java.lang.String r0 = "NETWORK"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r0)
            if (r0 != 0) goto L54
            r5 = 1
        L13:
            int r0 = r8.hashCode()
            switch(r0) {
                case -1985466892: goto L2a;
                case -1770733785: goto L36;
                case 1071839779: goto L3f;
                case 2074624396: goto L48;
                default: goto L1a;
            }
        L1a:
            java.lang.String r1 = "miss"
        L1c:
            java.lang.String r0 = "hit_cache"
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r1)
            java.util.Map r0 = kotlin.collections.MapsKt__MapsJVMKt.mapOf(r0)
            r7.h(r0)
            goto L56
        L2a:
            java.lang.String r0 = "PRELOAD_IN_FLIGHT"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L33
            goto L1a
        L33:
            java.lang.String r1 = "memory"
            goto L1c
        L36:
            java.lang.String r0 = "DOWNLOADED"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L51
            goto L1a
        L3f:
            java.lang.String r0 = "BUSINESS_CACHE"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L51
            goto L1a
        L48:
            java.lang.String r0 = "PRELOAD_CACHE"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L51
            goto L1a
        L51:
            java.lang.String r1 = "disk"
            goto L1c
        L54:
            r5 = 0
            goto L13
        L56:
            com.vega.clipflow.ITaskContext r3 = r7.v     // Catch: java.lang.Throwable -> L81
            if (r3 == 0) goto L7a
        L5a:
            r0 = 2
            kotlin.Pair[] r2 = new kotlin.Pair[r0]     // Catch: java.lang.Throwable -> L81
            java.lang.String r1 = "algorithm_cache_hit"
            java.lang.String r0 = java.lang.String.valueOf(r5)     // Catch: java.lang.Throwable -> L81
            kotlin.Pair r0 = kotlin.TuplesKt.to(r1, r0)     // Catch: java.lang.Throwable -> L81
            r2[r4] = r0     // Catch: java.lang.Throwable -> L81
            java.lang.String r0 = "algorithm_cache_source"
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r8)     // Catch: java.lang.Throwable -> L81
            r2[r6] = r0     // Catch: java.lang.Throwable -> L81
            r3.c(r2, r4)     // Catch: java.lang.Throwable -> L81
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L81
            kotlin.Result.m17090constructorimpl(r0)     // Catch: java.lang.Throwable -> L81
            goto L89
        L7a:
            java.lang.String r0 = "taskContext"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)     // Catch: java.lang.Throwable -> L81
            r3 = 0
            goto L5a
        L81:
            r0 = move-exception
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            kotlin.Result.m17090constructorimpl(r0)
        L89:
            java.lang.Throwable r2 = kotlin.Result.m17093exceptionOrNullimpl(r0)
            if (r2 == 0) goto L96
            java.lang.String r1 = "ImageEditPreUpload"
            java.lang.String r0 = "Resolve: update task tracing failed"
            com.vega.log.BLog.e(r1, r0, r2)
        L96:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.image.edit.clipflow2.common.ImageEditResolveUploadArtifactNode.J(java.lang.String):void");
    }

    /* JADX DEBUG: Method merged with bridge method: H(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object; */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00de  */
    @Override // com.vega.clipflow.ClipflowAsyncNode
    /* renamed from: K, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object H(com.vega.image.edit.clipflow2.common.ImageEditResolveUploadArtifactNode.Input r24, kotlin.coroutines.Continuation<? super com.vega.clipflow.NodeResult<com.vega.image.edit.clipflow2.common.ImageEditUploadResult>> r25) throws java.lang.Throwable {
        /*
            r23 = this;
            r4 = r25
            boolean r0 = r4 instanceof com.vega.image.edit.clipflow2.common.ImageEditResolveUploadArtifactNode$runAsync$1
            r1 = r23
            if (r0 == 0) goto Lde
            r13 = r4
            com.vega.image.edit.clipflow2.common.ImageEditResolveUploadArtifactNode$runAsync$1 r13 = (com.vega.image.edit.clipflow2.common.ImageEditResolveUploadArtifactNode$runAsync$1) r13
            int r3 = r13.s
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r3 & r2
            if (r0 == 0) goto Lde
            int r3 = r3 - r2
            r13.s = r3
        L16:
            java.lang.Object r4 = r13.q
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r6 = r13.s
            r5 = 1
            java.lang.String r2 = ", producer="
            java.lang.String r0 = "ImageEditPreUpload"
            if (r6 == 0) goto L3f
            if (r6 != r5) goto L16f
            kotlin.ResultKt.throwOnFailure(r4)
        L2a:
            com.vega.image.edit.clipflow2.common.ImageEditUploadResult r4 = (com.vega.image.edit.clipflow2.common.ImageEditUploadResult) r4
            boolean r3 = r1.k
            if (r3 == 0) goto Le5
            java.lang.String r1 = "Resolve: canceled"
            com.vega.log.BLog.e(r0, r1)
            com.vega.clipflow.NodeResult$Fail r2 = new com.vega.clipflow.NodeResult$Fail
            java.lang.String r1 = "cancel"
            r0 = -1002(0xfffffffffffffc16, float:NaN)
            r2.<init>(r0, r1)
            return r2
        L3f:
            kotlin.ResultKt.throwOnFailure(r4)
            com.vega.image.edit.clipflow2.common.ImageEditUploadResult r5 = r24.getKnownArtifact()
            if (r5 == 0) goto L78
            boolean r4 = r5.getSuccess()
            if (r4 == 0) goto L78
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r3 = "Resolve: use known artifact, source="
            r4.<init>(r3)
            java.lang.String r3 = r5.getCacheSource()
            r4.append(r3)
            r4.append(r2)
            com.vega.image.edit.clipflow2.common.ImageEditArtifactProducer r2 = r1.t
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            com.vega.log.BLog.i(r0, r2)
            java.lang.String r0 = r5.getCacheSource()
            r1.J(r0)
            com.vega.clipflow.NodeResult$Success r0 = new com.vega.clipflow.NodeResult$Success
            r0.<init>(r5)
            return r0
        L78:
            int r4 = r24.getCacheTimeH()
            if (r4 == 0) goto Ld4
            int r11 = r24.getCacheTimeH()
        L82:
            long r7 = r24.getUploadTimeoutMs()
            r5 = 0
            int r4 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r4 <= 0) goto Lca
            long r16 = r24.getUploadTimeoutMs()
        L90:
            com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache r6 = com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache.f104779a
            java.lang.String r7 = r24.getPreparedPath()
            int r8 = r24.getWidth()
            int r9 = r24.getHeight()
            com.vega.image.edit.clipflow2.common.ImageEditArtifactProducer r10 = r1.t
            com.vega.image.edit.clipflow2.common.ImageEditImageUploader$UploadRequest r12 = new com.vega.image.edit.clipflow2.common.ImageEditImageUploader$UploadRequest
            java.lang.String r15 = r24.getPreparedPath()
            long r18 = r24.getAuthTimeoutMs()
            java.util.Map r20 = r24.getReportParams()
            com.vega.image.edit.clipflow2.common.ImageEditResolveUploadArtifactNode$runAsync$result$1 r5 = new com.vega.image.edit.clipflow2.common.ImageEditResolveUploadArtifactNode$runAsync$result$1
            r5.<init>()
            com.vega.image.edit.clipflow2.common.ImageEditResolveUploadArtifactNode$runAsync$result$2 r4 = new com.vega.image.edit.clipflow2.common.ImageEditResolveUploadArtifactNode$runAsync$result$2
            r4.<init>()
            r14 = r12
            r21 = r5
            r22 = r4
            r14.<init>(r15, r16, r18, r20, r21, r22)
            r4 = 1
            r13.s = r4
            java.lang.Object r4 = r6.e(r7, r8, r9, r10, r11, r12, r13)
            if (r4 != r3) goto L2a
            return r3
        Lca:
            com.vega.libeffect.settings.ImageEditorPreUploadConfig$Companion r4 = com.vega.libeffect.settings.ImageEditorPreUploadConfig.f109933a
            r4.getClass()
            long r16 = com.vega.libeffect.settings.ImageEditorPreUploadConfig.Companion.d()
            goto L90
        Ld4:
            com.vega.libeffect.settings.ImageEditorPreUploadConfig$Companion r4 = com.vega.libeffect.settings.ImageEditorPreUploadConfig.f109933a
            r4.getClass()
            int r11 = com.vega.libeffect.settings.ImageEditorPreUploadConfig.Companion.a()
            goto L82
        Lde:
            com.vega.image.edit.clipflow2.common.ImageEditResolveUploadArtifactNode$runAsync$1 r13 = new com.vega.image.edit.clipflow2.common.ImageEditResolveUploadArtifactNode$runAsync$1
            r13.<init>(r1, r4)
            goto L16
        Le5:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r3 = "Resolve: done, success="
            r5.<init>(r3)
            boolean r3 = r4.getSuccess()
            r5.append(r3)
            java.lang.String r3 = ", source="
            r5.append(r3)
            java.lang.String r3 = r4.getCacheSource()
            r5.append(r3)
            r5.append(r2)
            com.vega.image.edit.clipflow2.common.ImageEditArtifactProducer r2 = r1.t
            r5.append(r2)
            java.lang.String r2 = ", mode="
            r5.append(r2)
            com.vega.image.edit.clipflow2.common.ImageEditUploadFailureMode r2 = r1.s
            r5.append(r2)
            java.lang.String r2 = ", code="
            r5.append(r2)
            int r2 = r4.getErrorCode()
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            com.vega.log.BLog.i(r0, r2)
            boolean r0 = r4.getSuccess()
            if (r0 == 0) goto L15f
            java.lang.String r0 = r4.getCacheSource()
            r1.J(r0)
        L131:
            boolean r0 = r4.getSuccess()
            if (r0 == 0) goto L13d
            com.vega.clipflow.NodeResult$Success r0 = new com.vega.clipflow.NodeResult$Success
            r0.<init>(r4)
        L13c:
            return r0
        L13d:
            com.vega.image.edit.clipflow2.common.ImageEditUploadFailureMode r1 = r1.s
            com.vega.image.edit.clipflow2.common.ImageEditUploadFailureMode r0 = com.vega.image.edit.clipflow2.common.ImageEditUploadFailureMode.f104780a
            if (r1 != r0) goto L159
            java.lang.String r2 = r4.getErrorMessage()
            boolean r0 = kotlin.text.StringsKt__StringsKt.isBlank(r2)
            if (r0 == 0) goto L14f
            java.lang.String r2 = "upload failed"
        L14f:
            int r1 = r4.getErrorCode()
            com.vega.clipflow.NodeResult$Fail r0 = new com.vega.clipflow.NodeResult$Fail
            r0.<init>(r1, r2)
            goto L13c
        L159:
            com.vega.clipflow.NodeResult$Success r0 = new com.vega.clipflow.NodeResult$Success
            r0.<init>(r4)
            goto L13c
        L15f:
            java.lang.String r2 = "hit_cache"
            java.lang.String r0 = "miss"
            kotlin.Pair r0 = kotlin.TuplesKt.to(r2, r0)
            java.util.Map r0 = kotlin.collections.MapsKt__MapsJVMKt.mapOf(r0)
            r1.h(r0)
            goto L131
        L16f:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.image.edit.clipflow2.common.ImageEditResolveUploadArtifactNode.H(com.vega.image.edit.clipflow2.common.ImageEditResolveUploadArtifactNode$Input, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.vega.clipflow.ITaskContextNode
    public final void f(TaskContextAbility taskContextAbility) {
        Intrinsics.checkNotNullParameter(taskContextAbility, "");
        this.v = taskContextAbility;
    }

    @Override // com.vega.clipflow.ClipflowNode
    public final String l() {
        return this.u;
    }
}