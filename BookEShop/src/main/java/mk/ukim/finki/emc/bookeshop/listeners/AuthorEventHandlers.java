package mk.ukim.finki.emc.bookeshop.listeners;

import mk.ukim.finki.emc.bookeshop.events.AuthorChangedEvent;
import mk.ukim.finki.emc.bookeshop.events.AuthorCreatedEvent;
import mk.ukim.finki.emc.bookeshop.events.AuthorDeletedEvent;
import mk.ukim.finki.emc.bookeshop.service.domain.AuthorService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthorEventHandlers {

    private final AuthorService authorService;

    public AuthorEventHandlers(AuthorService authorService) {
        this.authorService = authorService;
    }

    @EventListener
    public void onAuthorCreated(AuthorCreatedEvent authorCreatedEvent) {
        this.authorService.refreshMaterializedView();
    }

    @EventListener
    public void onAuthorChanged(AuthorChangedEvent authorChangedEvent) {
        this.authorService.refreshMaterializedView();
    }

    @EventListener
    public void onAuthorDeleted(AuthorDeletedEvent authorDeletedEvent) {
        this.authorService.refreshMaterializedView();
    }

}
