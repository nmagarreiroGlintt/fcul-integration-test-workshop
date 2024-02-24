package com.glinttnext.fcul.workshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

@ActiveProfiles(profiles = {"test"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.Random.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class IntegrationParentTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    protected MockHttpServletResponse mockMvcPerformPost(final String url, final Object request)
            throws Exception {
        return this.mockMvcPerformPostWithHeaders(url, request, null);
    }

    protected MockHttpServletResponse mockMvcPerformPostWithHeaders(final String url, final Object request,
                                                                    HttpHeaders headers) throws Exception {
        MvcResult mvcResult = null;

        headers = this.addingDefaultHeadersIfNecessary(headers);

        mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(url).headers(headers)
                        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
                .andReturn();

        return mvcResult.getResponse();
    }

    private HttpHeaders addingDefaultHeadersIfNecessary(HttpHeaders headers) {
        if (headers == null) {
            headers = new HttpHeaders();
        }
        return headers;
    }

    protected MockHttpServletResponse mockMvcPerformGet(final String url) throws Exception {
        return this.mockMvcPerformGet(url, null);
    }

    protected MockHttpServletResponse mockMvcPerformGet(final String url, HttpHeaders headers) throws Exception {
        headers = this.addingDefaultHeadersIfNecessary(headers);

        final MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(url)
                .headers(headers).contentType(MediaType.APPLICATION_JSON)).andReturn();
        final MockHttpServletResponse response = mvcResult.getResponse();
        return response;
    }

    public <T> T getResponseObjectFromResponse(final MockHttpServletResponse response,
                                               final TypeReference<T> returnTypeReference)
            throws JsonProcessingException, UnsupportedEncodingException {
        final T responseBody = this.mapper
                .readValue(response.getContentAsString(Charset.forName("UTF-8")), returnTypeReference);
        return responseBody;
    }
}
