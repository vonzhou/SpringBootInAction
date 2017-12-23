package com.vonzhou.springbootinaction;

import com.vonzhou.springbootinaction.domain.Book;
import jdk.nashorn.internal.runtime.ECMAException;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by vonzhou on 2017/12/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReadingListApplication.class)
@WebAppConfiguration
public class ReadingListControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getReadingListPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reading/vonzhou")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.view().name("readingList")).
                andExpect(MockMvcResultMatchers.model().attributeExists("books")).
                andExpect(MockMvcResultMatchers.model().attribute("books", Matchers.is(Matchers.empty())));
    }

    @Test
    public void addBooksToReadingList() throws Exception {
        String reader = "vonzhou";
        mockMvc.perform(MockMvcRequestBuilders.post("/reading/" + reader)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "Book title")
                .param("author", "Book Author")
                .param("isbn", "2342134")
                .param("description", "balaaaaaaaaaa"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.header().string("Location", "/reading/" + reader));


        Book expected = new Book();
        expected.setId(1L);
        expected.setReader("vonzhou");
        expected.setTitle("Book title");
        expected.setAuthor("Book Author");
        expected.setIsbn("2342134");
        expected.setDescription("balaaaaaaaaaa");

        mockMvc.perform(MockMvcRequestBuilders.get("/reading/vonzhou")).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.view().name("readingList")).
                andExpect(MockMvcResultMatchers.model().attributeExists("books")).
                andExpect(MockMvcResultMatchers.model().attribute("books", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.model().attribute("books", Matchers.contains(Matchers.samePropertyValuesAs(expected))));
    }

}
