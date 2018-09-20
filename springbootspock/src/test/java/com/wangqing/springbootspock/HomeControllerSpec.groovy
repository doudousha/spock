package com.wangqing.springbootspock

import com.wangqing.springbootspock.controller.HomeController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.http.ResponseEntity.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post


@SpringBootTest(classes = SpringbootspockApplication.class)

class HomeControllerSpec extends Specification {

    @Autowired
    private WebApplicationContext context;
    MockMvc mockMvc

    def setup(){
       // mockMvc = standaloneSetup(new HomeController()).build()
        mockMvc =  MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    def '访问首页'() {
        when: '调用addStudent'
        def resultActions = mockMvc.perform(get('/home'))  .andDo(MockMvcResultHandlers.print())
        print "输出status:"+resultActions.andReturn().response.getStatus()
        print "输出:"+resultActions.andReturn().response.getContentAsString();
        then:
        resultActions != null
        resultActions.andReturn().response.getContentAsString() =="home"
    }


    def '添加学生'() {
        when: '调用addStudent'
        def resultActions = mockMvc.perform(post('/student'))  .andDo(MockMvcResultHandlers.print())
        then:

        resultActions.andReturn().response.getContentAsString().contains("不能")
    }
}
