package by.karpovich.springMvc.repository;

import by.karpovich.springMvc.model.Singer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.DriverManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;


class SingerRepositoryTest {


//    private SingerRepository singerRepository;
//
//    static String connectionUrl;
//
//    public static final PostgreSQLContainer<?> container =
//            new PostgreSQLContainer<>("postgres:16")
//
//                    .withInitScript("db-migration.SQL");
//
//    @BeforeEach
//    void setUp() {
//
//    }
//
//    @BeforeAll
//    static void beforeAll() {
//        container.start();
//
//        String[] url = container.getJdbcUrl().split("\\?");
//        connectionUrl = url[0];
//    }
//
//    @AfterAll
//    static void afterAll() {
//        container.stop();
//    }
//
//    @Test
//    void findAll() {
//        List<Singer> all = singerRepository.findAll();
//
//        assertEquals(3, all.size());
//    }
}