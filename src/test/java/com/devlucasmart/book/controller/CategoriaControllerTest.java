package com.devlucasmart.book.controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:/scripts/books.sql"})
@Sql(scripts = {"classpath:/scripts/categorias.sql"})
public class CategoriaControllerTest {
}
