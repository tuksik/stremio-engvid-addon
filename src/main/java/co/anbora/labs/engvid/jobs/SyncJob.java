package co.anbora.labs.engvid.jobs;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;

import co.anbora.labs.engvid.domain.usecase.UseCaseExecutor;
import co.anbora.labs.engvid.domain.usecase.lesson.SyncLessonsAtStartupUseCase;
import co.anbora.labs.engvid.domain.usecase.lesson.SyncRemoteLessonsUseCase;
import io.quarkus.scheduler.Scheduled;

import java.util.function.Function;

public class SyncJob {

    @Inject
    UseCaseExecutor useCaseExecutor;

    @Inject
    SyncRemoteLessonsUseCase syncRemoteLessonsUseCase;

    @Inject
    SyncLessonsAtStartupUseCase syncLessonsAtStartupUseCase;

    @Scheduled(every = "1440m")
    public void daily() {
        useCaseExecutor.execute(
                syncLessonsAtStartupUseCase,
                new SyncLessonsAtStartupUseCase.Request(),
                Function.identity()
        );
    }

    @Scheduled(cron = "0 0 12 */7 * ?")
    public void weekly() {
        useCaseExecutor.execute(
                syncRemoteLessonsUseCase,
                new SyncRemoteLessonsUseCase.Request(),
                Function.identity()
        );
    }
}