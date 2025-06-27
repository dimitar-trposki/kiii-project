package mk.ukim.finki.emc.bookeshop.jobs;

import mk.ukim.finki.emc.bookeshop.service.application.BookApplicationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final BookApplicationService bookApplicationService;

    public ScheduledTasks(BookApplicationService bookApplicationService) {
        this.bookApplicationService = bookApplicationService;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void refreshView() {
        this.bookApplicationService.refreshMaterializedView();
    }
}
