package com.webank.wedatasphere.exchangis.job.launcher.builder;

import com.webank.wedatasphere.exchangis.job.builder.ExchangisJobBuilderContext;
import com.webank.wedatasphere.exchangis.job.builder.api.AbstractExchangisJobBuilder;
import com.webank.wedatasphere.exchangis.job.domain.ExchangisEngineJob;
import com.webank.wedatasphere.exchangis.job.domain.ExchangisJobInfo;
import com.webank.wedatasphere.exchangis.job.launcher.entity.ExchangisLauncherJob;
import com.webank.wedatasphere.exchangis.job.vo.ExchangisJobVO;
import com.webank.wedatasphere.exchangis.job.exception.ExchangisJobException;

/**
 * Launcher job builder
 */

public class LinkisExchangisLauncherJobBuilder extends AbstractExchangisJobBuilder<ExchangisEngineJob, ExchangisLauncherJob> {

    private static final String LAUNCHER_NAME = "Linkis";
    @Override
    public ExchangisLauncherJob buildJob(ExchangisEngineJob inputJob, ExchangisLauncherJob expectOut, ExchangisJobBuilderContext ctx) throws ExchangisJobException {
        ExchangisLauncherJob launcherJob = new ExchangisLauncherJob();
        ExchangisJobInfo exchangisJob = ctx.getOriginalJob();
        launcherJob.setId(inputJob.getId());
        launcherJob.setName(inputJob.getName());
        launcherJob.setCreateUser(inputJob.getCreateUser());
//        launcherJob.setExecuteNode(exchangisJob.getExecuteNode());
//        launcherJob.setProxyUser(exchangisJob.getProxyUser());
        launcherJob.setJobContent(inputJob.getJobContent());
        launcherJob.setRuntimeMap(inputJob.getRuntimeParams());
//        launcherJob.setEngine(inputJob.getEngine());
        launcherJob.setName(inputJob.getName());
        launcherJob.setLaunchName(LAUNCHER_NAME);
        return launcherJob;
    }
}
