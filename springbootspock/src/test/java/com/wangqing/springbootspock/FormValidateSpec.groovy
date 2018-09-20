package com.wangqing.springbootspock

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post


@SpringBootTest(classes = SpringbootspockApplication.class)
class FormValidateSpec extends  Specification{

    @Autowired
    private WebApplicationContext context;
    MockMvc mockMvc

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    def '添加学生'() {
        when: '调用addStudent'
        def resultActions = mockMvc.perform(post('/student/save')).andDo(MockMvcResultHandlers.print())
        then:

        resultActions.andReturn().response.getContentAsString().contains("不能")
    }

    def '添加学生&用表单POST方式提交'() {
        when: '调用addStudent'
        def resultActions = mockMvc.perform(post('/student/save2')).andDo(MockMvcResultHandlers.print())
        then:

        resultActions.andReturn().response.getContentAsString().contains("不能")
    }


    def '添加学生&用JSON POST方式提交'() {
        when: '调用addStudent'
        def resultActions = mockMvc.perform(post('/student/save3').contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content("{}")).andDo(MockMvcResultHandlers.print())
        then:

        resultActions.andReturn().response.getContentAsString().contains("不能")
    }

}
