package com.bytedance.apm.config;

import android.text.TextUtils;
import com.bytedance.apm.ApmContext;
import com.bytedance.apm.abs.IEnhancedInterceptor;
import com.bytedance.apm.constant.ReportUrl;
import com.bytedance.apm.core.IDynamicParams;
import com.bytedance.apm.core.IQueryParams;
import com.bytedance.apm.impl.DefaultTTNetImpl;
import com.bytedance.apm.listener.IApmLogListener;
import com.bytedance.apm.listener.IApmStartListener;
import com.bytedance.apm.listener.IBlockListener;
import com.bytedance.apm.listener.IMemoryReachTopListener;
import com.bytedance.apm.listener.INtpTimeService;
import com.bytedance.apm.listener.IStorageCheckListener;
import com.bytedance.apm.listener.ITrafficCallback;
import com.bytedance.apm.logging.ApmAlogHelper;
import com.bytedance.apm.logging.IApmAlog;
import com.bytedance.apm6.foundation.UnSampleListener;
import com.bytedance.apm6.monitor.MonitorableInterceptor;
import com.bytedance.frameworks.encryptor.EncryptorUtil;
import com.bytedance.services.apm.api.IEncrypt;
import com.bytedance.services.apm.api.IHttpService;
import com.bytedance.services.apm.api.IRequestTagHeaderProvider;
import com.bytedance.services.apm.api.IWidget;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes37.dex */
public class ApmStartConfig {
    public final String mAlogFilesDir;
    public final IApmLogListener mApmLogListener;
    public final IApmStartListener mApmStartListener;
    public final IBlockListener mBlockListener;
    public final long mBlockThresholdMs;
    public final ITrafficCallback mCallback;
    public List<String> mDefaultLogReportUrls;
    public final long mDelayNetRequestSeconds;
    public final IDynamicParams mDynamicParams;
    public final boolean mEnableBatteryLocalRecord;
    public final boolean mEnableBlockOnlySampled;
    public final boolean mEnableMultiProcessRequestSetting;
    public final boolean mEnableTemperatureLocalRecord;
    public final boolean mEnableTrafficDetect;
    public final IEncrypt mEncryptor;
    public List<String> mExceptionLogReportUrls;
    public final ExecutorService mExecutor;
    public final boolean mForceUpdateSlardarSetting;
    public final JSONObject mHeader;
    public final IHttpService mHttpService;
    public final boolean mIgnoreInvalidStack;
    public final IEnhancedInterceptor mInterceptor;
    public IMemoryReachTopListener mMemoryReachTopListener;
    public final boolean mNetMonitorWithDisconnected;
    public final INtpTimeService mNtpTimeService;
    public final IQueryParams mQueryParams;
    public List<String> mSlardarConfigUrls;
    public final IStorageCheckListener mStorageCheckListener;
    public List<String> mTraceReportUrls;
    public final Set<IWidget> mWidgets;
    public final boolean mWithBatteryDetect;
    public final boolean mWithBlockDetect;
    public final boolean mWithExceptionTrafficDetect;
    public final boolean mWithSeriousBlockDetect;
    public final boolean mWithTemperatureDetect;
    public final boolean mWithWebViewTrafficDetect;
    public final IRequestTagHeaderProvider provider;

    /* loaded from: classes22.dex */
    public static final class Builder {
        public String alogFilesDir;
        public IApmAlog apmAlogInstance;
        public IApmLogListener apmLogListener;
        public IApmStartListener apmStartListener;
        public boolean batteryDetect;
        public boolean batteryLocalRecord;
        public boolean blockDetect;
        public boolean blockDetectOnlySampled;
        public IBlockListener blockListener;
        public long blockThresholdMs;
        public long delayNetRequestSeconds;
        public IDynamicParams dynamicParams;
        public boolean enableMultiProcessRequestSetting;
        public boolean enableTrafficDetect;
        public IEncrypt encryptor;
        public List<String> exceptionLogReportUrls;
        public boolean exceptionTrafficDetect;
        public ExecutorService executor;
        public boolean forceUpdateSlardarSetting;
        public IHttpService httpService;
        public boolean ignoreInvalidStack;
        public IMemoryReachTopListener memoryReachTopListener;
        public IEnhancedInterceptor monitorableInterceptor;
        public boolean netMonitorWithDisconnected;
        public List<String> normalLogReportUrls;
        public INtpTimeService nptTimeService;
        public JSONObject paramsHeader;
        public IRequestTagHeaderProvider provider;
        public IQueryParams queryParams;
        public boolean seriousBlockDetect;
        public List<String> slardarConfigUrls;
        public IStorageCheckListener storageCheckListener;
        public boolean temperatureDetect;
        public boolean temperatureLocalRecord;
        public List<String> traceReportUrls;
        public ITrafficCallback trafficCallback;
        public UnSampleListener unSampleListener;
        public Set<IWidget> widgets;
        public boolean withWebViewTrafficDetect;

        public Builder() {
            this.enableTrafficDetect = true;
            this.slardarConfigUrls = ReportUrl.f37457a;
            this.normalLogReportUrls = ReportUrl.f37458c;
            this.exceptionLogReportUrls = ReportUrl.f;
            this.traceReportUrls = ReportUrl.f37459d;
            this.paramsHeader = new JSONObject();
            this.widgets = new HashSet();
            this.delayNetRequestSeconds = 0L;
            this.blockThresholdMs = 2500L;
            this.encryptor = new IEncrypt() { // from class: com.bytedance.apm.config.ApmStartConfig.Builder.1
                @Override // com.bytedance.services.apm.api.IEncrypt
                public final byte[] encrypt(byte[] bArr) {
                    return EncryptorUtil.encrypt(bArr, bArr.length);
                }
            };
            this.blockDetect = false;
            this.temperatureDetect = false;
            this.exceptionTrafficDetect = false;
        }

        public Builder(ApmStartConfig apmStartConfig) {
            this.enableTrafficDetect = true;
            this.slardarConfigUrls = apmStartConfig.mSlardarConfigUrls;
            this.normalLogReportUrls = apmStartConfig.mDefaultLogReportUrls;
            this.exceptionLogReportUrls = apmStartConfig.mExceptionLogReportUrls;
            this.traceReportUrls = apmStartConfig.mTraceReportUrls;
            this.blockDetect = apmStartConfig.mWithBlockDetect;
            this.ignoreInvalidStack = apmStartConfig.mIgnoreInvalidStack;
            this.blockDetectOnlySampled = apmStartConfig.mEnableBlockOnlySampled;
            this.seriousBlockDetect = apmStartConfig.mWithSeriousBlockDetect;
            this.blockThresholdMs = apmStartConfig.mBlockThresholdMs;
            this.temperatureDetect = apmStartConfig.mWithTemperatureDetect;
            this.withWebViewTrafficDetect = apmStartConfig.mWithWebViewTrafficDetect;
            this.batteryDetect = apmStartConfig.mWithBatteryDetect;
            this.batteryLocalRecord = apmStartConfig.mEnableBatteryLocalRecord;
            this.paramsHeader = apmStartConfig.mHeader;
            this.dynamicParams = apmStartConfig.mDynamicParams;
            this.widgets = apmStartConfig.mWidgets;
            this.httpService = apmStartConfig.mHttpService;
            this.apmLogListener = apmStartConfig.getApmLogListener();
            this.memoryReachTopListener = apmStartConfig.mMemoryReachTopListener;
            this.encryptor = apmStartConfig.mEncryptor;
            this.netMonitorWithDisconnected = apmStartConfig.mNetMonitorWithDisconnected;
            this.nptTimeService = apmStartConfig.mNtpTimeService;
            this.queryParams = apmStartConfig.mQueryParams;
            this.blockListener = apmStartConfig.mBlockListener;
            this.monitorableInterceptor = apmStartConfig.mInterceptor;
        }

        public Builder aid(int i) throws JSONException {
            param("aid", i);
            return this;
        }

        public Builder apmLogListener(IApmLogListener iApmLogListener) {
            this.apmLogListener = iApmLogListener;
            return this;
        }

        public Builder apmStartListener(IApmStartListener iApmStartListener) {
            this.apmStartListener = iApmStartListener;
            return this;
        }

        public Builder appVersion(String str) throws JSONException {
            param("app_version", str);
            return this;
        }

        public Builder batteryDetect(boolean z) {
            this.batteryDetect = z;
            return this;
        }

        public Builder batteryLocalRecord(boolean z) {
            this.batteryLocalRecord = z;
            return this;
        }

        public Builder blockDetect(boolean z) {
            this.blockDetect = z;
            return this;
        }

        public Builder blockDetectOnlySampled(boolean z) {
            this.blockDetectOnlySampled = z;
            return this;
        }

        public Builder blockThresholdMs(long j) {
            this.blockThresholdMs = j;
            return this;
        }

        public ApmStartConfig build() {
            if (TextUtils.isEmpty(this.paramsHeader.optString("aid"))) {
                throw new IllegalArgumentException("aid must not be empty");
            }
            if (this.dynamicParams == null) {
                throw new NullPointerException("dynamicParams must not be null");
            }
            TextUtils.isEmpty(this.paramsHeader.optString("app_version"));
            TextUtils.isEmpty(this.paramsHeader.optString("update_version_code"));
            TextUtils.isEmpty(this.paramsHeader.optString("device_id"));
            TextUtils.isEmpty(this.paramsHeader.optString("release_build"));
            return new ApmStartConfig(this);
        }

        public Builder channel(String str) throws JSONException {
            param("channel", str);
            return this;
        }

        public Builder configFetchUrl(List<String> list) {
            this.slardarConfigUrls = list;
            return this;
        }

        public Builder defaultReportUrls(List<String> list) {
            this.normalLogReportUrls = list;
            return this;
        }

        public Builder delayReport(long j) {
            this.delayNetRequestSeconds = Math.min(j, 30L);
            return this;
        }

        public Builder deviceId(String str) throws JSONException {
            param("device_id", str);
            return this;
        }

        public Builder dynamicParams(IDynamicParams iDynamicParams) {
            this.dynamicParams = iDynamicParams;
            return this;
        }

        public Builder enableMultiProcessRequestSetting(boolean z) {
            this.enableMultiProcessRequestSetting = z;
            return this;
        }

        public Builder enableNetMonitorWithDisconnected(boolean z) {
            this.netMonitorWithDisconnected = z;
            return this;
        }

        public Builder enableTrafficDetect(boolean z) {
            this.enableTrafficDetect = z;
            return this;
        }

        public Builder exceptionLogDefaultReportUrls(List<String> list) {
            this.exceptionLogReportUrls = list;
            return this;
        }

        public Builder exceptionTrafficDetect(boolean z) {
            this.exceptionTrafficDetect = z;
            return this;
        }

        public Builder forceUpdateSlardarSetting(boolean z) {
            this.forceUpdateSlardarSetting = z;
            return this;
        }

        public Builder ignoreInvalidStack(boolean z) {
            this.ignoreInvalidStack = z;
            return this;
        }

        public Builder injectExecutor(ExecutorService executorService) {
            this.executor = executorService;
            return this;
        }

        public Builder memoryReachTop(IMemoryReachTopListener iMemoryReachTopListener) {
            this.memoryReachTopListener = iMemoryReachTopListener;
            return this;
        }

        public Builder param(String str, int i) throws JSONException {
            try {
                this.paramsHeader.put(str, i);
            } catch (JSONException unused) {
            }
            return this;
        }

        public Builder param(String str, long j) throws JSONException {
            try {
                this.paramsHeader.put(str, j);
            } catch (JSONException unused) {
            }
            return this;
        }

        public Builder param(String str, String str2) throws JSONException {
            try {
                this.paramsHeader.put(str, str2);
            } catch (JSONException unused) {
            }
            return this;
        }

        public Builder params(JSONObject jSONObject) throws JSONException {
            Iterator<String> itKeys;
            try {
                JSONObject jSONObject2 = this.paramsHeader;
                if (jSONObject2 != null && jSONObject != null && (itKeys = jSONObject.keys()) != null) {
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        if (!jSONObject.isNull(next)) {
                            jSONObject2.put(next, jSONObject.opt(next));
                        }
                    }
                }
            } catch (JSONException unused) {
            }
            return this;
        }

        public Builder queryParams(IQueryParams iQueryParams) {
            this.queryParams = iQueryParams;
            return this;
        }

        public Builder releaseBuild(String str) throws JSONException {
            param("release_build", str);
            return this;
        }

        public Builder requestTagHeaderProvider(IRequestTagHeaderProvider iRequestTagHeaderProvider) {
            this.provider = iRequestTagHeaderProvider;
            return this;
        }

        public Builder seriousBlockDetect(boolean z) {
            this.seriousBlockDetect = z;
            return this;
        }

        public Builder setAlogFilesDir(String str) {
            this.alogFilesDir = str;
            return this;
        }

        public Builder setAlogInstance(IApmAlog iApmAlog) {
            this.apmAlogInstance = iApmAlog;
            return this;
        }

        public Builder setBlockListener(IBlockListener iBlockListener) {
            this.blockListener = iBlockListener;
            return this;
        }

        public Builder setDiskMBThresholdToday(int i) throws JSONException {
            param("max_size_mb_today", i);
            return this;
        }

        public Builder setEncrypt(IEncrypt iEncrypt) {
            this.encryptor = iEncrypt;
            return this;
        }

        public Builder setMonitorInterceptor(IEnhancedInterceptor iEnhancedInterceptor) {
            this.monitorableInterceptor = iEnhancedInterceptor;
            return this;
        }

        public Builder setNptTimeService(INtpTimeService iNtpTimeService) {
            this.nptTimeService = iNtpTimeService;
            return this;
        }

        public Builder setStorageCheckListener(IStorageCheckListener iStorageCheckListener) {
            this.storageCheckListener = iStorageCheckListener;
            return this;
        }

        public Builder setTrafficCallback(ITrafficCallback iTrafficCallback) {
            this.trafficCallback = iTrafficCallback;
            return this;
        }

        public Builder setUnSampleListener(UnSampleListener unSampleListener) {
            this.unSampleListener = unSampleListener;
            return this;
        }

        public Builder temperatureDetect(boolean z) {
            this.temperatureDetect = z;
            return this;
        }

        public Builder temperatureLocalRecord(boolean z) {
            this.temperatureLocalRecord = z;
            return this;
        }

        public Builder traceReportUrl(List<String> list) {
            this.traceReportUrls = list;
            return this;
        }

        public Builder updateVersionCode(String str) throws JSONException {
            param("update_version_code", str);
            return this;
        }

        public Builder useDefaultTTNetImpl(boolean z) {
            if (z) {
                this.httpService = new DefaultTTNetImpl();
            }
            return this;
        }

        public Builder useHttpService(IHttpService iHttpService) {
            this.httpService = iHttpService;
            return this;
        }

        public Builder webViewTrafficDetect(boolean z) {
            this.withWebViewTrafficDetect = z;
            return this;
        }

        public Builder widget(IWidget iWidget) {
            if (iWidget == null || (!ApmContext.j() && iWidget.isOnlyMainProcess())) {
                return this;
            }
            this.widgets.add(iWidget);
            return this;
        }
    }

    public ApmStartConfig(Builder builder) {
        this.mHeader = builder.paramsHeader;
        this.mForceUpdateSlardarSetting = builder.forceUpdateSlardarSetting;
        this.mEnableMultiProcessRequestSetting = builder.enableMultiProcessRequestSetting;
        this.mDynamicParams = builder.dynamicParams;
        this.mSlardarConfigUrls = builder.slardarConfigUrls;
        this.mHttpService = builder.httpService;
        this.mEnableTrafficDetect = builder.enableTrafficDetect;
        this.mWithExceptionTrafficDetect = builder.exceptionTrafficDetect;
        this.mWithBlockDetect = builder.blockDetect;
        this.mEnableBlockOnlySampled = builder.blockDetectOnlySampled;
        this.mWithSeriousBlockDetect = builder.seriousBlockDetect;
        this.mIgnoreInvalidStack = builder.ignoreInvalidStack;
        this.mBlockThresholdMs = builder.blockThresholdMs;
        this.mWithBatteryDetect = builder.batteryDetect;
        this.mEnableBatteryLocalRecord = builder.batteryLocalRecord;
        this.mEnableTemperatureLocalRecord = builder.temperatureLocalRecord;
        this.mWidgets = builder.widgets;
        this.mDefaultLogReportUrls = builder.normalLogReportUrls;
        this.mExceptionLogReportUrls = builder.exceptionLogReportUrls;
        this.mTraceReportUrls = builder.traceReportUrls;
        this.mDelayNetRequestSeconds = builder.delayNetRequestSeconds;
        this.mWithTemperatureDetect = builder.temperatureDetect;
        this.mWithWebViewTrafficDetect = builder.withWebViewTrafficDetect;
        this.mApmLogListener = builder.apmLogListener;
        this.mApmStartListener = builder.apmStartListener;
        this.mStorageCheckListener = builder.storageCheckListener;
        this.mExecutor = builder.executor;
        this.mMemoryReachTopListener = builder.memoryReachTopListener;
        this.mEncryptor = builder.encryptor;
        this.mNetMonitorWithDisconnected = builder.netMonitorWithDisconnected;
        this.mAlogFilesDir = builder.alogFilesDir;
        this.mCallback = builder.trafficCallback;
        this.mNtpTimeService = builder.nptTimeService;
        this.mQueryParams = builder.queryParams;
        this.mBlockListener = builder.blockListener;
        this.mInterceptor = builder.monitorableInterceptor;
        this.provider = builder.provider;
        IApmAlog iApmAlog = builder.apmAlogInstance;
        ApmAlogHelper.b = iApmAlog;
        if (iApmAlog != null) {
            ApmAlogHelper.f37563a = true;
        }
        ApmAlogHelper.f37564c = builder.unSampleListener;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(ApmStartConfig apmStartConfig) {
        return new Builder(apmStartConfig);
    }

    public String getAlogFilesDir() {
        return this.mAlogFilesDir;
    }

    public IApmLogListener getApmLogListener() {
        return this.mApmLogListener;
    }

    public IApmStartListener getApmStartListener() {
        return this.mApmStartListener;
    }

    public IBlockListener getBlockListener() {
        return this.mBlockListener;
    }

    public long getBlockThresholdMs() {
        return this.mBlockThresholdMs;
    }

    public List<String> getDefaultLogReportUrls() {
        return this.mDefaultLogReportUrls;
    }

    public long getDelayRequestSeconds() {
        return this.mDelayNetRequestSeconds;
    }

    public long getDeviceId() {
        return this.mHeader.optLong("device_id");
    }

    public IDynamicParams getDynamicParams() {
        return this.mDynamicParams;
    }

    public IEncrypt getEncryptor() {
        return this.mEncryptor;
    }

    public List<String> getExceptionLogReportUrls() {
        return this.mExceptionLogReportUrls;
    }

    public ExecutorService getExecutor() {
        return this.mExecutor;
    }

    public JSONObject getHeader() {
        return this.mHeader;
    }

    public IHttpService getHttpService() {
        return this.mHttpService;
    }

    public MonitorableInterceptor getInterceptor() {
        return this.mInterceptor;
    }

    public IMemoryReachTopListener getMemoryReachTopListener() {
        return this.mMemoryReachTopListener;
    }

    public boolean getNetMonitorWithDisconnected() {
        return this.mNetMonitorWithDisconnected;
    }

    public INtpTimeService getNtpTimeService() {
        return this.mNtpTimeService;
    }

    public IQueryParams getQueryParams() {
        return this.mQueryParams;
    }

    public IRequestTagHeaderProvider getRequestTagHeaderProvider() {
        return this.provider;
    }

    public List<String> getSlardarConfigUrls() {
        return this.mSlardarConfigUrls;
    }

    public IStorageCheckListener getStorageCheckListener() {
        return this.mStorageCheckListener;
    }

    public List<String> getTraceReportUrls() {
        return this.mTraceReportUrls;
    }

    public ITrafficCallback getTrafficCallback() {
        return this.mCallback;
    }

    public Set<IWidget> getWidgets() {
        return this.mWidgets;
    }

    public boolean isBatteryLocalRecordEnable() {
        return this.mEnableBatteryLocalRecord;
    }

    public boolean isEnableBlockOnlySampled() {
        return this.mEnableBlockOnlySampled;
    }

    public boolean isEnableMultiProcessRequestSetting() {
        return this.mEnableMultiProcessRequestSetting;
    }

    public boolean isEnableTrafficDetect() {
        return this.mEnableTrafficDetect;
    }

    public boolean isForceUpdateSlardarSetting() {
        return this.mForceUpdateSlardarSetting;
    }

    public boolean isIgnoreInvalidStack() {
        return this.mIgnoreInvalidStack;
    }

    public boolean isTemperatureLocalRecordEnable() {
        return this.mEnableTemperatureLocalRecord;
    }

    public boolean isWithBatteryDetect() {
        return this.mWithBatteryDetect;
    }

    public boolean isWithBlockDetect() {
        return this.mWithBlockDetect;
    }

    public boolean isWithExceptionTrafficDetect() {
        return this.mWithExceptionTrafficDetect;
    }

    public boolean isWithSeriousBlockDetect() {
        return this.mWithSeriousBlockDetect;
    }

    public boolean isWithTemperatureDetect() {
        return this.mWithTemperatureDetect;
    }

    public boolean isWithWebViewDetect() {
        return this.mWithWebViewTrafficDetect;
    }

    public void setDefaultLogReportUrlsCompat(List<String> list) {
        this.mDefaultLogReportUrls = list;
    }

    public void setExceptionLogReportUrlsCompat(List<String> list) {
        this.mExceptionLogReportUrls = list;
    }

    public void setSlardarConfigUrlsCompat(List<String> list) {
        this.mSlardarConfigUrls = list;
    }

    public String toString() {
        return "ApmStartConfig{mSlardarConfigUrls=" + this.mSlardarConfigUrls + ", mDefaultLogReportUrls=" + this.mDefaultLogReportUrls + ", mExceptionLogReportUrls=" + this.mExceptionLogReportUrls + ", mTraceReportUrls=" + this.mTraceReportUrls + ", mMemoryReachTopListener=" + this.mMemoryReachTopListener + ", mWithExceptionTrafficDetect=" + this.mWithExceptionTrafficDetect + ", mEnableTrafficDetect=" + this.mEnableTrafficDetect + ", mWithWebViewTrafficDetect=" + this.mWithWebViewTrafficDetect + ", mWithBlockDetect=" + this.mWithBlockDetect + ", mEnableBlockOnlySampled=" + this.mEnableBlockOnlySampled + ", mWithSeriousBlockDetect=" + this.mWithSeriousBlockDetect + ", mBlockThresholdMs=" + this.mBlockThresholdMs + ", mWithTemperatureDetect=" + this.mWithTemperatureDetect + ", mWithBatteryDetect=" + this.mWithBatteryDetect + ", mEnableBatteryLocalRecord=" + this.mEnableBatteryLocalRecord + ", mEnableTemperatureLocalRecord=" + this.mEnableTemperatureLocalRecord + ", mForceUpdateSlardarSetting=" + this.mForceUpdateSlardarSetting + ", mEnableMultiProcessRequestSetting=" + this.mEnableMultiProcessRequestSetting + ", mNetMonitorWithDisconnected=" + this.mNetMonitorWithDisconnected + ", mHeader=" + this.mHeader + ", mDynamicParams=" + this.mDynamicParams + ", mQueryParams=" + this.mQueryParams + ", mHttpService=" + this.mHttpService + ", mWidgets=" + this.mWidgets + ", mDelayNetRequestSeconds=" + this.mDelayNetRequestSeconds + ", mApmStartListener=" + this.mApmStartListener + ", mApmLogListener=" + this.mApmLogListener + ", mStorageCheckListener=" + this.mStorageCheckListener + ", mExecutor=" + this.mExecutor + ", mEncryptor=" + this.mEncryptor + ", mAlogFilesDir='" + this.mAlogFilesDir + "', mCallback=" + this.mCallback + ", mNtpTimeService=" + this.mNtpTimeService + '}';
    }
}