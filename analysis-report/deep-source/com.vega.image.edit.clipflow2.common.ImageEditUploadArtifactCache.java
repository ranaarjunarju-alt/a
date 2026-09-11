package com.vega.image.edit.clipflow2.common;

import com.bytedance.security.android.aopcheck.PolarisFileWrapper;
import com.google.gson.annotations.SerializedName;
import com.vega.core.utils.MD5Utils;
import com.vega.edit.base.network.CommonNetworkUploadCache;
import com.vega.log.BLog;
import com.vega.upload.UploadFunc;
import com.vega.util.JsonUtil;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* loaded from: classes16.dex */
public final class ImageEditUploadArtifactCache {

    /* renamed from: a, reason: collision with root package name */
    public static final ImageEditUploadArtifactCache f104779a = new ImageEditUploadArtifactCache();
    public static final UploadFunc b = UploadFunc.V;

    /* loaded from: classes29.dex */
    public static final class CachedArtifactRecord {

        @SerializedName("height")
        public final int height;

        @SerializedName("prepared_path")
        public final String preparedPath;

        @SerializedName("producer")
        public final String producer;

        @SerializedName("tos_key")
        public final String tosKey;

        @SerializedName("width")
        public final int width;

        /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.lang.Object[] */
        /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.lang.Object[] */
        /* JADX DEBUG: Multi-variable search result rejected for r1v3, resolved type: java.lang.Object[] */
        /* JADX WARN: Illegal instructions before constructor call */
        /* JADX WARN: Multi-variable type inference failed */
        public CachedArtifactRecord() {
            int i = 0;
            this(null, i, i, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 31, 0 == true ? 1 : 0);
        }

        public CachedArtifactRecord(String str, int i, int i2, String str2, String str3) {
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(str2, "");
            Intrinsics.checkNotNullParameter(str3, "");
            this.preparedPath = str;
            this.width = i;
            this.height = i2;
            this.tosKey = str2;
            this.producer = str3;
        }

        /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x001f: CONSTRUCTOR 
          (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0003: ARITH (r12v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? ("") : (r7v0 java.lang.String))
          (wrap:int:?: TERNARY null = ((wrap:int:0x000a: ARITH (r12v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? (0 int) : (r8v0 int))
          (wrap:int:?: TERNARY null = ((wrap:int:0x0010: ARITH (r12v0 int) & (4 int) A[WRAPPED]) == (0 int)) ? (r9v0 int) : (0 int))
          (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0014: ARITH (r12v0 int) & (8 int) A[WRAPPED]) == (0 int)) ? (r10v0 java.lang.String) : (""))
          (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0018: ARITH (r12v0 int) & (16 int) A[WRAPPED]) != (0 int)) ? ("BUSINESS_TASK") : (r11v0 java.lang.String))
         A[MD:(java.lang.String, int, int, java.lang.String, java.lang.String):void (m)] call: com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache.CachedArtifactRecord.<init>(java.lang.String, int, int, java.lang.String, java.lang.String):void type: THIS */
        public /* synthetic */ CachedArtifactRecord(String str, int i, int i2, String str2, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? 0 : i, (i3 & 4) == 0 ? i2 : 0, (i3 & 8) == 0 ? str2 : "", (i3 & 16) != 0 ? "BUSINESS_TASK" : str3);
        }

        public static /* synthetic */ CachedArtifactRecord copy$default(CachedArtifactRecord cachedArtifactRecord, String str, int i, int i2, String str2, String str3, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = cachedArtifactRecord.preparedPath;
            }
            if ((i3 & 2) != 0) {
                i = cachedArtifactRecord.width;
            }
            if ((i3 & 4) != 0) {
                i2 = cachedArtifactRecord.height;
            }
            if ((i3 & 8) != 0) {
                str2 = cachedArtifactRecord.tosKey;
            }
            if ((i3 & 16) != 0) {
                str3 = cachedArtifactRecord.producer;
            }
            return cachedArtifactRecord.copy(str, i, i2, str2, str3);
        }

        public final CachedArtifactRecord copy(String str, int i, int i2, String str2, String str3) {
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(str2, "");
            Intrinsics.checkNotNullParameter(str3, "");
            return new CachedArtifactRecord(str, i, i2, str2, str3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CachedArtifactRecord)) {
                return false;
            }
            CachedArtifactRecord cachedArtifactRecord = (CachedArtifactRecord) obj;
            return Intrinsics.areEqual(this.preparedPath, cachedArtifactRecord.preparedPath) && this.width == cachedArtifactRecord.width && this.height == cachedArtifactRecord.height && Intrinsics.areEqual(this.tosKey, cachedArtifactRecord.tosKey) && Intrinsics.areEqual(this.producer, cachedArtifactRecord.producer);
        }

        public final int getHeight() {
            return this.height;
        }

        public final String getPreparedPath() {
            return this.preparedPath;
        }

        public final String getProducer() {
            return this.producer;
        }

        public final String getTosKey() {
            return this.tosKey;
        }

        public final int getWidth() {
            return this.width;
        }

        public int hashCode() {
            return (((((((this.preparedPath.hashCode() * 31) + this.width) * 31) + this.height) * 31) + this.tosKey.hashCode()) * 31) + this.producer.hashCode();
        }

        public final ImageEditArtifactProducer producerEnum() {
            Object objCreateFailure;
            try {
                objCreateFailure = ImageEditArtifactProducer.valueOf(this.producer);
                Result.m17090constructorimpl(objCreateFailure);
            } catch (Throwable th) {
                objCreateFailure = ResultKt.createFailure(th);
                Result.m17090constructorimpl(objCreateFailure);
            }
            ImageEditArtifactProducer imageEditArtifactProducer = ImageEditArtifactProducer.b;
            if (Result.m17096isFailureimpl(objCreateFailure)) {
                objCreateFailure = imageEditArtifactProducer;
            }
            return (ImageEditArtifactProducer) objCreateFailure;
        }

        public String toString() {
            return "CachedArtifactRecord(preparedPath=" + this.preparedPath + ", width=" + this.width + ", height=" + this.height + ", tosKey=" + this.tosKey + ", producer=" + this.producer + ')';
        }
    }

    /* loaded from: classes33.dex */
    public static final class DownloadedBindingRecord {

        @SerializedName("file_size")
        public final long fileSize;

        @SerializedName("height")
        public final int height;

        @SerializedName("last_modified")
        public final long lastModified;

        @SerializedName("local_path")
        public final String localPath;

        @SerializedName("tos_key")
        public final String tosKey;

        @SerializedName("width")
        public final int width;

        /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.lang.Object[] */
        /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.lang.Object[] */
        /* JADX WARN: Illegal instructions before constructor call */
        /* JADX WARN: Multi-variable type inference failed */
        public DownloadedBindingRecord() {
            long j = 0;
            int i = 0;
            this(null, j, j, 0 == true ? 1 : 0, i, i, 63, 0 == true ? 1 : 0);
        }

        public DownloadedBindingRecord(String str, long j, long j2, String str2, int i, int i2) {
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(str2, "");
            this.localPath = str;
            this.fileSize = j;
            this.lastModified = j2;
            this.tosKey = str2;
            this.width = i;
            this.height = i2;
        }

        /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0026: CONSTRUCTOR 
          (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0004: ARITH (r18v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? ("") : (r10v0 java.lang.String))
          (wrap:long:?: TERNARY null = ((wrap:int:0x000b: ARITH (r18v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? (0 long) : (r11v0 long))
          (wrap:long:?: TERNARY null = ((wrap:int:0x0013: ARITH (r18v0 int) & (4 int) A[WRAPPED]) == (0 int)) ? (r13v0 long) : (0 long))
          (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0017: ARITH (r18v0 int) & (8 int) A[WRAPPED]) == (0 int)) ? (r15v0 java.lang.String) : (""))
          (wrap:int:?: TERNARY null = ((wrap:int:0x001b: ARITH (r18v0 int) & (16 int) A[WRAPPED]) != (0 int)) ? (0 int) : (r16v0 int))
          (wrap:int:?: TERNARY null = ((wrap:int:0x0021: ARITH (r18v0 int) & (32 int) A[WRAPPED]) == (0 int)) ? (r17v0 int) : (0 int))
         A[MD:(java.lang.String, long, long, java.lang.String, int, int):void (m)] call: com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache.DownloadedBindingRecord.<init>(java.lang.String, long, long, java.lang.String, int, int):void type: THIS */
        public /* synthetic */ DownloadedBindingRecord(String str, long j, long j2, String str2, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? 0L : j, (i3 & 4) == 0 ? j2 : 0L, (i3 & 8) == 0 ? str2 : "", (i3 & 16) != 0 ? 0 : i, (i3 & 32) == 0 ? i2 : 0);
        }

        public static /* synthetic */ DownloadedBindingRecord copy$default(DownloadedBindingRecord downloadedBindingRecord, String str, long j, long j2, String str2, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = downloadedBindingRecord.localPath;
            }
            if ((i3 & 2) != 0) {
                j = downloadedBindingRecord.fileSize;
            }
            if ((i3 & 4) != 0) {
                j2 = downloadedBindingRecord.lastModified;
            }
            if ((i3 & 8) != 0) {
                str2 = downloadedBindingRecord.tosKey;
            }
            if ((i3 & 16) != 0) {
                i = downloadedBindingRecord.width;
            }
            if ((i3 & 32) != 0) {
                i2 = downloadedBindingRecord.height;
            }
            return downloadedBindingRecord.copy(str, j, j2, str2, i, i2);
        }

        public final DownloadedBindingRecord copy(String str, long j, long j2, String str2, int i, int i2) {
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(str2, "");
            return new DownloadedBindingRecord(str, j, j2, str2, i, i2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DownloadedBindingRecord)) {
                return false;
            }
            DownloadedBindingRecord downloadedBindingRecord = (DownloadedBindingRecord) obj;
            return Intrinsics.areEqual(this.localPath, downloadedBindingRecord.localPath) && this.fileSize == downloadedBindingRecord.fileSize && this.lastModified == downloadedBindingRecord.lastModified && Intrinsics.areEqual(this.tosKey, downloadedBindingRecord.tosKey) && this.width == downloadedBindingRecord.width && this.height == downloadedBindingRecord.height;
        }

        public final long getFileSize() {
            return this.fileSize;
        }

        public final int getHeight() {
            return this.height;
        }

        public final long getLastModified() {
            return this.lastModified;
        }

        public final String getLocalPath() {
            return this.localPath;
        }

        public final String getTosKey() {
            return this.tosKey;
        }

        public final int getWidth() {
            return this.width;
        }

        public int hashCode() {
            int iHashCode = this.localPath.hashCode() * 31;
            long j = this.fileSize;
            int i = (iHashCode + ((int) (j ^ (j >>> 32)))) * 31;
            long j2 = this.lastModified;
            return ((((((i + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.tosKey.hashCode()) * 31) + this.width) * 31) + this.height;
        }

        public String toString() {
            return "DownloadedBindingRecord(localPath=" + this.localPath + ", fileSize=" + this.fileSize + ", lastModified=" + this.lastModified + ", tosKey=" + this.tosKey + ", width=" + this.width + ", height=" + this.height + ')';
        }
    }

    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        static {
            ImageEditArtifactProducer.values();
        }
    }

    public static String a(String str) {
        MD5Utils.f79623a.getClass();
        String strB = MD5Utils.b(str);
        if (strB == null) {
            strB = String.valueOf(str.hashCode());
        }
        return "ie_dl_bind_" + strB;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:41:0x000d */
    /* JADX DEBUG: Multi-variable search result rejected for r7v1, resolved type: com.vega.image.edit.clipflow2.common.ImageEditUploadResult */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.String, kotlin.jvm.internal.DefaultConstructorMarker] */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.vega.image.edit.clipflow2.common.ImageEditUploadResult] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    public static ImageEditUploadResult b(String str) {
        Object objCreateFailure;
        Intrinsics.checkNotNullParameter(str, "");
        ImageEditUploadResult imageEditUploadResult = 0;
        imageEditUploadResult = 0;
        imageEditUploadResult = 0;
        if (StringsKt__StringsKt.isBlank(str)) {
            return null;
        }
        try {
            PolarisFileWrapper polarisFileWrapper = new PolarisFileWrapper(str);
            if (polarisFileWrapper.exists() && polarisFileWrapper.isFile()) {
                try {
                    objCreateFailure = polarisFileWrapper.getCanonicalPath();
                    Result.m17090constructorimpl(objCreateFailure);
                } catch (Throwable th) {
                    objCreateFailure = ResultKt.createFailure(th);
                    Result.m17090constructorimpl(objCreateFailure);
                }
                String absolutePath = polarisFileWrapper.getAbsolutePath();
                if (Result.m17096isFailureimpl(objCreateFailure)) {
                    objCreateFailure = absolutePath;
                }
                String str2 = (String) objCreateFailure;
                Intrinsics.checkNotNull(str2);
                String strA = a(str2);
                NodeCacheStore.f104787a.getClass();
                String strC = NodeCacheStore.c(strA);
                if (strC == null) {
                    return null;
                }
                JsonUtil.f135118a.getClass();
                DownloadedBindingRecord downloadedBindingRecord = (DownloadedBindingRecord) JsonUtil.b.fromJson(strC, DownloadedBindingRecord.class);
                if (downloadedBindingRecord == null || StringsKt__StringsKt.isBlank(downloadedBindingRecord.getTosKey()) || !Intrinsics.areEqual(downloadedBindingRecord.getLocalPath(), str2) || downloadedBindingRecord.getFileSize() != polarisFileWrapper.length() || downloadedBindingRecord.getLastModified() != polarisFileWrapper.lastModified()) {
                    return null;
                }
                imageEditUploadResult = new ImageEditUploadResult(downloadedBindingRecord.getTosKey(), downloadedBindingRecord.getWidth(), downloadedBindingRecord.getHeight(), "DOWNLOADED", 0, imageEditUploadResult, 48, imageEditUploadResult);
                return imageEditUploadResult;
            }
            return null;
        } catch (Throwable th2) {
            BLog.e("ImageEditPreUpload", "Cache: findDownloaded error", th2);
            return imageEditUploadResult;
        }
    }

    public static String c(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        CommonNetworkUploadCache commonNetworkUploadCache = CommonNetworkUploadCache.f88050a;
        UploadFunc uploadFunc = b;
        String str2 = uploadFunc.f134907a;
        String str3 = uploadFunc.f134908c;
        commonNetworkUploadCache.getClass();
        return "ie_upload_art_" + CommonNetworkUploadCache.a(str, str2, str3);
    }

    public static void d(ImageEditUploadArtifactCache imageEditUploadArtifactCache, String str, String str2, int i) {
        Object objCreateFailure;
        imageEditUploadArtifactCache.getClass();
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        if (StringsKt__StringsKt.isBlank(str) || StringsKt__StringsKt.isBlank(str2) || i == 0) {
            return;
        }
        try {
            PolarisFileWrapper polarisFileWrapper = new PolarisFileWrapper(str);
            if (!polarisFileWrapper.exists() || !polarisFileWrapper.isFile()) {
                BLog.i("ImageEditPreUpload", "Cache: registerDownloaded skip, file missing");
                return;
            }
            try {
                objCreateFailure = polarisFileWrapper.getCanonicalPath();
                Result.m17090constructorimpl(objCreateFailure);
            } catch (Throwable th) {
                objCreateFailure = ResultKt.createFailure(th);
                Result.m17090constructorimpl(objCreateFailure);
            }
            String absolutePath = polarisFileWrapper.getAbsolutePath();
            if (Result.m17096isFailureimpl(objCreateFailure)) {
                objCreateFailure = absolutePath;
            }
            String str3 = (String) objCreateFailure;
            Intrinsics.checkNotNull(str3);
            String strA = a(str3);
            DownloadedBindingRecord downloadedBindingRecord = new DownloadedBindingRecord(str3, polarisFileWrapper.length(), polarisFileWrapper.lastModified(), str2, 0, 0);
            JsonUtil.f135118a.getClass();
            String json = JsonUtil.b.toJson(downloadedBindingRecord);
            NodeCacheStore nodeCacheStore = NodeCacheStore.f104787a;
            Intrinsics.checkNotNull(json);
            long j = i == -1 ? -1L : i * 60 * 60 * 1000;
            nodeCacheStore.getClass();
            NodeCacheStore.f(j, strA, json);
            BLog.i("ImageEditPreUpload", "Cache: registerDownloaded ok, size=" + downloadedBindingRecord.getFileSize());
        } catch (Throwable th2) {
            BLog.e("ImageEditPreUpload", "Cache: registerDownloaded error", th2);
        }
    }

    public static ImageEditArtifactSource f(ImageEditArtifactProducer imageEditArtifactProducer, boolean z) {
        if (z) {
            return imageEditArtifactProducer == ImageEditArtifactProducer.f104759a ? ImageEditArtifactSource.b : ImageEditArtifactSource.f104762c;
        }
        int iOrdinal = imageEditArtifactProducer.ordinal();
        if (iOrdinal == 0) {
            return ImageEditArtifactSource.f104761a;
        }
        if (iOrdinal == 1) {
            return ImageEditArtifactSource.f104762c;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r13v13, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r18v7, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r6v14, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x024b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0183 A[Catch: all -> 0x0186, TRY_LEAVE, TryCatch #6 {all -> 0x0186, blocks: (B:73:0x017f, B:75:0x0183, B:70:0x0169), top: B:143:0x0169 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x018c A[PHI: r0 r3 r4 r6 r9 r10
      0x018c: PHI (r0v15 int) = (r0v16 int), (r0v21 int) binds: [B:77:0x0187, B:74:0x0181] A[DONT_GENERATE, DONT_INLINE]
      0x018c: PHI (r3v6 java.lang.String) = (r3v7 java.lang.String), (r3v11 java.lang.String) binds: [B:77:0x0187, B:74:0x0181] A[DONT_GENERATE, DONT_INLINE]
      0x018c: PHI (r4v4 com.vega.image.edit.clipflow2.common.ImageEditArtifactProducer) = 
      (r4v5 com.vega.image.edit.clipflow2.common.ImageEditArtifactProducer)
      (r4v9 com.vega.image.edit.clipflow2.common.ImageEditArtifactProducer)
     binds: [B:77:0x0187, B:74:0x0181] A[DONT_GENERATE, DONT_INLINE]
      0x018c: PHI (r6v6 int) = (r6v7 int), (r6v12 int) binds: [B:77:0x0187, B:74:0x0181] A[DONT_GENERATE, DONT_INLINE]
      0x018c: PHI (r9v1 com.vega.image.edit.clipflow2.common.ImageEditImageUploader$UploadRequest) = 
      (r9v2 com.vega.image.edit.clipflow2.common.ImageEditImageUploader$UploadRequest)
      (r9v6 com.vega.image.edit.clipflow2.common.ImageEditImageUploader$UploadRequest)
     binds: [B:77:0x0187, B:74:0x0181] A[DONT_GENERATE, DONT_INLINE]
      0x018c: PHI (r10v4 int) = (r10v5 int), (r10v9 int) binds: [B:77:0x0187, B:74:0x0181] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0202 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0206 A[PHI: r11
      0x0206: PHI (r11v29 java.lang.Object) = (r11v0 java.lang.Object), (r11v32 java.lang.Object) binds: [B:92:0x0203, B:90:0x0200] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object e(java.lang.String r23, int r24, int r25, com.vega.image.edit.clipflow2.common.ImageEditArtifactProducer r26, int r27, com.vega.image.edit.clipflow2.common.ImageEditImageUploader.UploadRequest r28, kotlin.coroutines.Continuation<? super com.vega.image.edit.clipflow2.common.ImageEditUploadResult> r29) throws java.lang.Throwable {
        /*
            r22 = this;
            r3 = r23
            r4 = r26
            r9 = r28
            r0 = r24
            r6 = r25
            r10 = r27
            java.lang.String r13 = "Cache: hit disk, source="
            r7 = r29
            boolean r1 = r7 instanceof com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache$resolvePreparedArtifact$1
            r21 = r22
            if (r1 == 0) goto L3a
            r8 = r7
            com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache$resolvePreparedArtifact$1 r8 = (com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache$resolvePreparedArtifact$1) r8
            int r5 = r8.z
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r5 & r2
            if (r1 == 0) goto L3a
            int r5 = r5 - r2
            r8.z = r5
        L24:
            java.lang.Object r11 = r8.x
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r8.z
            r1 = 1
            java.lang.String r5 = "ImageEditPreUpload"
            switch(r2) {
                case 0: goto L5a;
                case 1: goto L74;
                case 2: goto Lb9;
                case 3: goto L30e;
                case 4: goto L2ec;
                case 5: goto L42;
                case 6: goto L203;
                case 7: goto L230;
                default: goto L32;
            }
        L32:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        L3a:
            com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache$resolvePreparedArtifact$1 r8 = new com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache$resolvePreparedArtifact$1
            r1 = r21
            r8.<init>(r1, r7)
            goto L24
        L42:
            int r10 = r8.v
            int r6 = r8.u
            int r0 = r8.t
            java.lang.Object r9 = r8.s
            com.vega.image.edit.clipflow2.common.ImageEditImageUploader$UploadRequest r9 = (com.vega.image.edit.clipflow2.common.ImageEditImageUploader.UploadRequest) r9
            com.vega.image.edit.clipflow2.common.ImageEditArtifactProducer r4 = r8.r
            java.lang.Object r3 = r8.q
            java.lang.String r3 = (java.lang.String) r3
            kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L57
            goto L17f
        L57:
            r2 = move-exception
            goto L187
        L5a:
            kotlin.ResultKt.throwOnFailure(r11)
            if (r10 != 0) goto L78
            r8.z = r1
            r11 = r21
            r12 = r3
            r13 = r0
            r14 = r6
            r15 = r4
            r16 = r10
            r17 = r9
            r18 = r8
            java.lang.Object r11 = r11.g(r12, r13, r14, r15, r16, r17, r18)
            if (r11 != r7) goto L77
            return r7
        L74:
            kotlin.ResultKt.throwOnFailure(r11)
        L77:
            return r11
        L78:
            java.lang.String r12 = c(r3)     // Catch: java.lang.Throwable -> L80
            kotlin.Result.m17090constructorimpl(r12)     // Catch: java.lang.Throwable -> L80
            goto L88
        L80:
            r1 = move-exception
            java.lang.Object r12 = kotlin.ResultKt.createFailure(r1)
            kotlin.Result.m17090constructorimpl(r12)
        L88:
            boolean r1 = kotlin.Result.m17096isFailureimpl(r12)
            if (r1 == 0) goto L8f
            r12 = 0
        L8f:
            java.lang.String r12 = (java.lang.String) r12
            if (r12 == 0) goto L99
            int r1 = r12.length()
            if (r1 != 0) goto Lb7
        L99:
            r1 = 1
        L9a:
            if (r1 == 0) goto Lbd
            java.lang.String r1 = "Cache: resolve cache key failed, fallback to network"
            com.vega.log.BLog.e(r5, r1)
            r1 = 2
            r8.z = r1
            r11 = r21
            r12 = r3
            r13 = r0
            r14 = r6
            r15 = r4
            r16 = r10
            r17 = r9
            r18 = r8
            java.lang.Object r11 = r11.g(r12, r13, r14, r15, r16, r17, r18)
            if (r11 != r7) goto Lbc
            return r7
        Lb7:
            r1 = 0
            goto L9a
        Lb9:
            kotlin.ResultKt.throwOnFailure(r11)
        Lbc:
            return r11
        Lbd:
            r1 = -1
            if (r10 != r1) goto Lc3
            r1 = -1
            goto Lcb
        Lc3:
            int r1 = r10 * 60
            int r1 = r1 * 60
            long r1 = (long) r1
            r14 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 * r14
        Lcb:
            com.vega.image.edit.clipflow2.common.NodeCacheStore r11 = com.vega.image.edit.clipflow2.common.NodeCacheStore.f104787a     // Catch: java.lang.Throwable -> L2f0
            r11.getClass()     // Catch: java.lang.Throwable -> L2f0
            java.lang.String r15 = com.vega.image.edit.clipflow2.common.NodeCacheStore.c(r12)     // Catch: java.lang.Throwable -> L2f0
            if (r15 != 0) goto Ld7
            goto Le7
        Ld7:
            com.vega.util.JsonUtil r11 = com.vega.util.JsonUtil.f135118a     // Catch: java.lang.Throwable -> L2f0
            r11.getClass()     // Catch: java.lang.Throwable -> L2f0
            com.google.gson.Gson r14 = com.vega.util.JsonUtil.b     // Catch: java.lang.Throwable -> L2f0
            java.lang.Class<com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache$CachedArtifactRecord> r11 = com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache.CachedArtifactRecord.class
            java.lang.Object r11 = r14.fromJson(r15, r11)     // Catch: java.lang.Throwable -> L2f0
            com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache$CachedArtifactRecord r11 = (com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache.CachedArtifactRecord) r11     // Catch: java.lang.Throwable -> L2f0
            goto Le8
        Le7:
            r11 = 0
        Le8:
            if (r11 == 0) goto L142
            java.lang.String r14 = r11.getTosKey()     // Catch: java.lang.Throwable -> L2f2
            boolean r14 = kotlin.text.StringsKt__StringsKt.isBlank(r14)     // Catch: java.lang.Throwable -> L2f2
            r14 = r14 ^ 1
            if (r14 == 0) goto L142
            com.vega.image.edit.clipflow2.common.ImageEditArtifactProducer r14 = r11.producerEnum()     // Catch: java.lang.Throwable -> L2f2
            r1 = 0
            com.vega.image.edit.clipflow2.common.ImageEditArtifactSource r1 = f(r14, r1)     // Catch: java.lang.Throwable -> L2f2
            java.lang.String r1 = r1.name()     // Catch: java.lang.Throwable -> L2f2
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2f2
            r12.<init>(r13)     // Catch: java.lang.Throwable -> L2f2
            r12.append(r1)     // Catch: java.lang.Throwable -> L2f2
            java.lang.String r2 = ", producer="
            r12.append(r2)     // Catch: java.lang.Throwable -> L2f2
            r12.append(r14)     // Catch: java.lang.Throwable -> L2f2
            java.lang.String r2 = r12.toString()     // Catch: java.lang.Throwable -> L2f2
            com.vega.log.BLog.i(r5, r2)     // Catch: java.lang.Throwable -> L13f
            com.vega.image.edit.clipflow2.common.ImageEditUploadResult r12 = new com.vega.image.edit.clipflow2.common.ImageEditUploadResult     // Catch: java.lang.Throwable -> L13f
            java.lang.String r13 = r11.getTosKey()     // Catch: java.lang.Throwable -> L13f
            if (r0 <= 0) goto L123
            goto L128
        L123:
            int r14 = r11.getWidth()     // Catch: java.lang.Throwable -> L13f
            goto L129
        L128:
            r14 = r0
        L129:
            if (r6 <= 0) goto L12d
            r15 = r6
            goto L131
        L12d:
            int r15 = r11.getHeight()     // Catch: java.lang.Throwable -> L13f
        L131:
            r17 = 0
            r18 = 0
            r19 = 48
            r20 = r18
            r16 = r1
            r12.<init>(r13, r14, r15, r16, r17, r18, r19, r20)     // Catch: java.lang.Throwable -> L13f
            return r12
        L13f:
            r2 = move-exception
            goto L2f3
        L142:
            kotlin.Pair r13 = com.vega.image.edit.clipflow2.common.NodeCacheStore.d(r12)     // Catch: java.lang.Throwable -> L2d0
            java.lang.Object r11 = r13.getFirst()
            kotlinx.coroutines.Deferred r11 = (kotlinx.coroutines.Deferred) r11
            java.lang.Object r13 = r13.getSecond()
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 != 0) goto L207
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r1 = "Cache: await in-flight, producer="
            r2.<init>(r1)
            r2.append(r4)
            java.lang.String r1 = r2.toString()
            com.vega.log.BLog.i(r5, r1)
            r8.q = r3     // Catch: java.lang.Throwable -> L186
            r8.r = r4     // Catch: java.lang.Throwable -> L186
            r8.s = r9     // Catch: java.lang.Throwable -> L186
            r8.t = r0     // Catch: java.lang.Throwable -> L186
            r8.u = r6     // Catch: java.lang.Throwable -> L186
            r8.v = r10     // Catch: java.lang.Throwable -> L186
            r1 = 5
            r8.z = r1     // Catch: java.lang.Throwable -> L186
            java.lang.Object r11 = r11.await(r8)     // Catch: java.lang.Throwable -> L186
            if (r11 != r7) goto L17f
            return r7
        L17f:
            boolean r1 = r11 instanceof com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache.CachedArtifactRecord     // Catch: java.lang.Throwable -> L186
            if (r1 == 0) goto L18c
            com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache$CachedArtifactRecord r11 = (com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache.CachedArtifactRecord) r11     // Catch: java.lang.Throwable -> L186
            goto L18d
        L186:
            r2 = move-exception
        L187:
            java.lang.String r1 = "Cache: await in-flight error"
            com.vega.log.BLog.e(r5, r1, r2)
        L18c:
            r11 = 0
        L18d:
            if (r11 == 0) goto L1e1
            java.lang.String r1 = r11.getTosKey()
            boolean r1 = kotlin.text.StringsKt__StringsKt.isBlank(r1)
            r2 = 1
            r1 = r1 ^ 1
            if (r1 == 0) goto L1e1
            com.vega.image.edit.clipflow2.common.ImageEditArtifactProducer r4 = r11.producerEnum()
            com.vega.image.edit.clipflow2.common.ImageEditArtifactSource r1 = f(r4, r2)
            java.lang.String r3 = r1.name()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r1 = "Cache: hit in-flight, source="
            r2.<init>(r1)
            r2.append(r3)
            java.lang.String r1 = ", ownerProducer="
            r2.append(r1)
            r2.append(r4)
            java.lang.String r1 = r2.toString()
            com.vega.log.BLog.i(r5, r1)
            com.vega.image.edit.clipflow2.common.ImageEditUploadResult r7 = new com.vega.image.edit.clipflow2.common.ImageEditUploadResult
            java.lang.String r8 = r11.getTosKey()
            if (r0 <= 0) goto L1dc
        L1c9:
            if (r6 <= 0) goto L1d7
        L1cb:
            r12 = 0
            r13 = 0
            r14 = 48
            r15 = r13
            r9 = r0
            r10 = r6
            r11 = r3
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15)
            return r7
        L1d7:
            int r6 = r11.getHeight()
            goto L1cb
        L1dc:
            int r0 = r11.getWidth()
            goto L1c9
        L1e1:
            java.lang.String r1 = "Cache: in-flight miss, fallback to network"
            com.vega.log.BLog.i(r5, r1)
            r1 = 0
            r8.q = r1
            r8.r = r1
            r8.s = r1
            r1 = 6
            r8.z = r1
            r11 = r21
            r12 = r3
            r13 = r0
            r14 = r6
            r15 = r4
            r16 = r10
            r17 = r9
            r18 = r8
            java.lang.Object r11 = r11.g(r12, r13, r14, r15, r16, r17, r18)
            if (r11 != r7) goto L206
            return r7
        L203:
            kotlin.ResultKt.throwOnFailure(r11)
        L206:
            return r11
        L207:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r10 = "Cache: miss, upload network, producer="
            r11.<init>(r10)
            r11.append(r4)
            java.lang.String r10 = r11.toString()
            com.vega.log.BLog.i(r5, r10)
            com.vega.image.edit.clipflow2.common.ImageEditImageUploader r11 = com.vega.image.edit.clipflow2.common.ImageEditImageUploader.f104771a
            r8.q = r3
            r8.r = r4
            r8.s = r12
            r8.t = r0
            r8.u = r6
            r8.w = r1
            r10 = 7
            r8.z = r10
            java.lang.Object r11 = r11.b(r9, r8)
            if (r11 != r7) goto L243
            return r7
        L230:
            long r1 = r8.w
            int r6 = r8.u
            int r0 = r8.t
            java.lang.Object r12 = r8.s
            java.lang.String r12 = (java.lang.String) r12
            com.vega.image.edit.clipflow2.common.ImageEditArtifactProducer r4 = r8.r
            java.lang.Object r3 = r8.q
            java.lang.String r3 = (java.lang.String) r3
            kotlin.ResultKt.throwOnFailure(r11)
        L243:
            com.vega.image.edit.clipflow2.common.ImageEditUploadResult r11 = (com.vega.image.edit.clipflow2.common.ImageEditUploadResult) r11
            boolean r7 = r11.getSuccess()
            if (r7 != 0) goto L25b
            com.vega.image.edit.clipflow2.common.NodeCacheStore r0 = com.vega.image.edit.clipflow2.common.NodeCacheStore.f104787a     // Catch: java.lang.Throwable -> L254
            r0.getClass()     // Catch: java.lang.Throwable -> L254
            com.vega.image.edit.clipflow2.common.NodeCacheStore.e(r12)     // Catch: java.lang.Throwable -> L254
            goto L25a
        L254:
            r1 = move-exception
            java.lang.String r0 = "Cache: remove in-flight error"
            com.vega.log.BLog.e(r5, r0, r1)
        L25a:
            return r11
        L25b:
            com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache$CachedArtifactRecord r13 = new com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache$CachedArtifactRecord
            if (r0 <= 0) goto L276
        L25f:
            if (r6 <= 0) goto L271
        L261:
            java.lang.String r17 = r11.getTosKey()
            java.lang.String r18 = r4.name()
            r14 = r3
            r15 = r0
            r16 = r6
            r13.<init>(r14, r15, r16, r17, r18)
            goto L27b
        L271:
            int r6 = r11.getHeight()
            goto L261
        L276:
            int r0 = r11.getWidth()
            goto L25f
        L27b:
            com.vega.util.JsonUtil r0 = com.vega.util.JsonUtil.f135118a     // Catch: java.lang.Throwable -> L298
            r0.getClass()     // Catch: java.lang.Throwable -> L298
            com.google.gson.Gson r0 = com.vega.util.JsonUtil.b     // Catch: java.lang.Throwable -> L298
            java.lang.String r3 = r0.toJson(r13)     // Catch: java.lang.Throwable -> L298
            com.vega.image.edit.clipflow2.common.NodeCacheStore r0 = com.vega.image.edit.clipflow2.common.NodeCacheStore.f104787a     // Catch: java.lang.Throwable -> L298
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch: java.lang.Throwable -> L298
            r0.getClass()     // Catch: java.lang.Throwable -> L298
            com.vega.image.edit.clipflow2.common.NodeCacheStore.f(r1, r12, r3)     // Catch: java.lang.Throwable -> L298
            r0.getClass()     // Catch: java.lang.Throwable -> L298
            com.vega.image.edit.clipflow2.common.NodeCacheStore.a(r1, r13, r12)     // Catch: java.lang.Throwable -> L298
            goto L2a6
        L298:
            r1 = move-exception
            java.lang.String r0 = "Cache: write disk error"
            com.vega.log.BLog.e(r5, r0, r1)
            com.vega.image.edit.clipflow2.common.NodeCacheStore r0 = com.vega.image.edit.clipflow2.common.NodeCacheStore.f104787a     // Catch: java.lang.Throwable -> L2a6
            r0.getClass()     // Catch: java.lang.Throwable -> L2a6
            com.vega.image.edit.clipflow2.common.NodeCacheStore.e(r12)     // Catch: java.lang.Throwable -> L2a6
        L2a6:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "Cache: network upload ok, source=NETWORK, producer="
            r1.<init>(r0)
            r1.append(r4)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.i(r5, r0)
            com.vega.image.edit.clipflow2.common.ImageEditUploadResult r0 = new com.vega.image.edit.clipflow2.common.ImageEditUploadResult
            java.lang.String r1 = r13.getTosKey()
            int r2 = r13.getWidth()
            int r3 = r13.getHeight()
            java.lang.String r4 = "NETWORK"
            r5 = 0
            r6 = 0
            r7 = 48
            r8 = r6
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return r0
        L2d0:
            r2 = move-exception
            java.lang.String r1 = "Cache: register in-flight error"
            com.vega.log.BLog.e(r5, r1, r2)
            r1 = 4
            r8.z = r1
            r11 = r21
            r12 = r3
            r13 = r0
            r14 = r6
            r15 = r4
            r16 = r10
            r17 = r9
            r18 = r8
            java.lang.Object r11 = r11.g(r12, r13, r14, r15, r16, r17, r18)
            if (r11 != r7) goto L2ef
            return r7
        L2ec:
            kotlin.ResultKt.throwOnFailure(r11)
        L2ef:
            return r11
        L2f0:
            r2 = move-exception
            goto L2f3
        L2f2:
            r2 = move-exception
        L2f3:
            java.lang.String r1 = "Cache: read disk error"
            com.vega.log.BLog.e(r5, r1, r2)
            r1 = 3
            r8.z = r1
            r11 = r21
            r12 = r3
            r13 = r0
            r14 = r6
            r15 = r4
            r16 = r10
            r17 = r9
            r18 = r8
            java.lang.Object r11 = r11.g(r12, r13, r14, r15, r16, r17, r18)
            if (r11 != r7) goto L311
            return r7
        L30e:
            kotlin.ResultKt.throwOnFailure(r11)
        L311:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache.e(java.lang.String, int, int, com.vega.image.edit.clipflow2.common.ImageEditArtifactProducer, int, com.vega.image.edit.clipflow2.common.ImageEditImageUploader$UploadRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r13v2, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object g(java.lang.String r17, int r18, int r19, com.vega.image.edit.clipflow2.common.ImageEditArtifactProducer r20, int r21, com.vega.image.edit.clipflow2.common.ImageEditImageUploader.UploadRequest r22, kotlin.coroutines.Continuation<? super com.vega.image.edit.clipflow2.common.ImageEditUploadResult> r23) throws java.lang.Throwable {
        /*
            r16 = this;
            r2 = r17
            r3 = r20
            r9 = r18
            r6 = r23
            r10 = r19
            r1 = r21
            boolean r0 = r6 instanceof com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache$uploadAndMaybeStore$1
            if (r0 == 0) goto L5d
            r7 = r6
            com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache$uploadAndMaybeStore$1 r7 = (com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache$uploadAndMaybeStore$1) r7
            int r5 = r7.x
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r5 & r4
            if (r0 == 0) goto L5d
            int r5 = r5 - r4
            r7.x = r5
        L1e:
            java.lang.Object r6 = r7.v
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r7.x
            r4 = 1
            if (r0 == 0) goto L43
            if (r0 != r4) goto Lea
            int r1 = r7.u
            int r10 = r7.t
            int r9 = r7.s
            com.vega.image.edit.clipflow2.common.ImageEditArtifactProducer r3 = r7.r
            java.lang.Object r2 = r7.q
            java.lang.String r2 = (java.lang.String) r2
            kotlin.ResultKt.throwOnFailure(r6)
        L3a:
            com.vega.image.edit.clipflow2.common.ImageEditUploadResult r6 = (com.vega.image.edit.clipflow2.common.ImageEditUploadResult) r6
            boolean r0 = r6.getSuccess()
            if (r0 != 0) goto L65
            return r6
        L43:
            kotlin.ResultKt.throwOnFailure(r6)
            com.vega.image.edit.clipflow2.common.ImageEditImageUploader r0 = com.vega.image.edit.clipflow2.common.ImageEditImageUploader.f104771a
            r7.q = r2
            r7.r = r3
            r7.s = r9
            r7.t = r10
            r7.u = r1
            r7.x = r4
            r4 = r22
            java.lang.Object r6 = r0.b(r4, r7)
            if (r6 != r5) goto L3a
            return r5
        L5d:
            com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache$uploadAndMaybeStore$1 r7 = new com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache$uploadAndMaybeStore$1
            r0 = r16
            r7.<init>(r0, r6)
            goto L1e
        L65:
            com.vega.image.edit.clipflow2.common.ImageEditUploadResult r7 = new com.vega.image.edit.clipflow2.common.ImageEditUploadResult
            java.lang.String r8 = r6.getTosKey()
            if (r9 <= 0) goto L81
        L6d:
            if (r10 <= 0) goto L7c
        L6f:
            java.lang.String r11 = "NETWORK"
            r12 = 0
            r13 = 0
            r14 = 48
            r15 = r13
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15)
            if (r1 == 0) goto Le9
            goto L86
        L7c:
            int r10 = r6.getHeight()
            goto L6f
        L81:
            int r9 = r6.getWidth()
            goto L6d
        L86:
            java.lang.String r4 = c(r2)     // Catch: java.lang.Throwable -> Le1
            r0 = -1
            if (r1 != r0) goto L90
            r0 = -1
            goto L98
        L90:
            int r0 = r1 * 60
            int r0 = r0 * 60
            long r0 = (long) r0     // Catch: java.lang.Throwable -> Le1
            r5 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 * r5
        L98:
            com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache$CachedArtifactRecord r8 = new com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache$CachedArtifactRecord     // Catch: java.lang.Throwable -> Le1
            int r10 = r7.getWidth()     // Catch: java.lang.Throwable -> Le1
            int r11 = r7.getHeight()     // Catch: java.lang.Throwable -> Le1
            java.lang.String r12 = r7.getTosKey()     // Catch: java.lang.Throwable -> Le1
            java.lang.String r13 = r3.name()     // Catch: java.lang.Throwable -> Le1
            r9 = r2
            r8.<init>(r9, r10, r11, r12, r13)     // Catch: java.lang.Throwable -> Le1
            com.vega.util.JsonUtil r2 = com.vega.util.JsonUtil.f135118a     // Catch: java.lang.Throwable -> Le1
            r2.getClass()     // Catch: java.lang.Throwable -> Le1
            com.google.gson.Gson r2 = com.vega.util.JsonUtil.b     // Catch: java.lang.Throwable -> Le1
            java.lang.String r3 = r2.toJson(r8)     // Catch: java.lang.Throwable -> Le1
            com.vega.image.edit.clipflow2.common.NodeCacheStore r2 = com.vega.image.edit.clipflow2.common.NodeCacheStore.f104787a     // Catch: java.lang.Throwable -> Le1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch: java.lang.Throwable -> Le1
            r2.getClass()     // Catch: java.lang.Throwable -> Le1
            com.vega.image.edit.clipflow2.common.NodeCacheStore.f(r0, r4, r3)     // Catch: java.lang.Throwable -> Le1
            r2.getClass()     // Catch: java.lang.Throwable -> Le1
            kotlin.Pair r3 = com.vega.image.edit.clipflow2.common.NodeCacheStore.d(r4)     // Catch: java.lang.Throwable -> Le1
            java.lang.Object r2 = r3.getFirst()     // Catch: java.lang.Throwable -> Le1
            kotlinx.coroutines.CompletableDeferred r2 = (kotlinx.coroutines.CompletableDeferred) r2     // Catch: java.lang.Throwable -> Le1
            java.lang.Object r2 = r3.getSecond()     // Catch: java.lang.Throwable -> Le1
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch: java.lang.Throwable -> Le1
            boolean r2 = r2.booleanValue()     // Catch: java.lang.Throwable -> Le1
            if (r2 == 0) goto Le9
            com.vega.image.edit.clipflow2.common.NodeCacheStore.a(r0, r8, r4)     // Catch: java.lang.Throwable -> Le1
            goto Le9
        Le1:
            r2 = move-exception
            java.lang.String r1 = "ImageEditPreUpload"
            java.lang.String r0 = "Cache: best-effort store error"
            com.vega.log.BLog.e(r1, r0, r2)
        Le9:
            return r7
        Lea:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.image.edit.clipflow2.common.ImageEditUploadArtifactCache.g(java.lang.String, int, int, com.vega.image.edit.clipflow2.common.ImageEditArtifactProducer, int, com.vega.image.edit.clipflow2.common.ImageEditImageUploader$UploadRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }
}