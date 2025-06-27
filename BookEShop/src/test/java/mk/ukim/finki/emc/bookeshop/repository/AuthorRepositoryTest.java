package mk.ukim.finki.emc.bookeshop.repository;

import mk.ukim.finki.emc.bookeshop.model.domain.Author;
import mk.ukim.finki.emc.bookeshop.model.domain.User;
import mk.ukim.finki.emc.bookeshop.model.enumerations.Role;
import mk.ukim.finki.emc.bookeshop.model.projections.AuthorProjection;
import mk.ukim.finki.emc.bookeshop.model.projections.UserProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testProjectUsernameAndNameAndSurname() {
        List<AuthorProjection> authorProjectionList = this.authorRepository.getAuthorsByNameAndSurname();
        Assertions.assertEquals("Harper", authorProjectionList.get(0).getName());
        Assertions.assertEquals("Lee", authorProjectionList.get(0).getSurname());
    }

}