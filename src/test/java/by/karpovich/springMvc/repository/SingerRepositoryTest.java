package by.karpovich.springMvc.repository;

import by.karpovich.springMvc.config.PersistenceJPAConfig;
import by.karpovich.springMvc.config.WebMvcConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceJPAConfig.class, WebMvcConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Testcontainers
class SingerRepositoryTest {

    @Autowired
    private SingerRepository singerRepository;
//    @Autowired
//    private ApplicationContext applicationContext;

    //    @ClassRule
    public static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>("postgres:16")
                    .withUsername("postgres")
                    .withDatabaseName("postgres")
                    .withPassword("postgres")
                    .withInitScript("db-migration.SQL");

    @AfterAll
    static void afterAll() {
        container.stop();
    }

    @BeforeAll
    static void beforeAll() {
        container.start();
    }

    @Test
    void findAll() {
//        List<Singer> all = singerRepository.findAll();
//
//        assertEquals(3, all.size());
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersistenceJPAConfig.class);
//        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
//        System.out.println(context.getBeanDefinitionNames());
    }
}