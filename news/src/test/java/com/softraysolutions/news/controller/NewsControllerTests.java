package com.softraysolutions.news.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softraysolutions.news.model.News;
import com.softraysolutions.news.model.User;
import com.softraysolutions.news.model.enumeration.Enumerations;
import com.softraysolutions.news.service.NewsService;
import com.softraysolutions.news.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers =NewsController.class)
@ActiveProfiles("test")
public class NewsControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private NewsService newsService;

    private List<News> newsList;

    private User admin;

    @Autowired
    private ObjectMapper objectMapper;

    private final String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbiJ9.D6SOrGJViTl6Shf3igEQwRtebu3c6PwCSRO0tczu0GkEsat5236HH9mYY0QSoueHXszE1pOsK6VySzA-VMyewA";

    @BeforeEach
    void setUp() {
        this.admin = new User("admin@mail.com", "test", Enumerations.UserType.SuperAdmin);
        this.newsList = new ArrayList<News>();
        for(int i = 1; i <= 10; i++) {
            this.newsList.add(new News("Title" + i, "Description" + i,  new Date(), this.admin, this.admin.getUsername()));
            this.newsList.get(i - 1).setId(i);
        }
    }

    @Test
    void shouldReturnAllPublicNews() throws Exception {
        given(newsService.searchPublic("all")).willReturn(this.newsList);
        mockMvc
                .perform(get("/api/news/public/all"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(this.newsList.size())));
    }

    @Test
    void shouldHaveNoPublicAccessForAllAdminNews() throws Exception {
        mockMvc
                .perform(get("/api/news/admin/all"))
                .andExpect(status().is(403));
    }

    @WithMockUser
    @Test
    void shouldReturnAllAdminNews() throws Exception {
        given(newsService.searchAdmin(any(), any())).willReturn(this.newsList);
        given(userService.loadUserByUsername(any())).willReturn(this.admin);
        mockMvc
                .perform(get("/api/news/admin/all").header("Authorization", token))
                .andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(this.newsList.size())));
    }

    @Test
    void shouldHaveNoPublicAccessForCreateNews() throws Exception {
        mockMvc
                .perform(post("/api/news"))
                .andExpect(status().is(403));
    }

    @WithMockUser
    @Test
    void shouldCreateNews() throws Exception {
        given(newsService.save(any())).willReturn(this.newsList.get(0));
        given(userService.loadUserByUsername(any())).willReturn(this.admin);
        mockMvc
                .perform(post("/api/news")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Title1\",\"description\":\"Description1\"}"))
                .andExpect(status().isOk());
    }
}
