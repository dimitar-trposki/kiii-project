package mk.ukim.finki.emc.bookeshop.events;

import lombok.Getter;
import mk.ukim.finki.emc.bookeshop.model.domain.Author;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class AuthorChangedEvent extends ApplicationEvent {

    private LocalDateTime when;

    public AuthorChangedEvent(Author source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public AuthorChangedEvent(Author source, LocalDateTime when) {
        super(source);
        this.when = when;
    }

}
