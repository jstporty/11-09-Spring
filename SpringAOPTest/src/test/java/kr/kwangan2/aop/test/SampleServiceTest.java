package kr.kwangan2.aop.test;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.kwangan2.aop.service.SampleService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class SampleServiceTest {

	@Setter(onMethod_ = @Autowired)
	private SampleService service;

	@Test
	public void testClass() {
		log.info("aaaaaaaaaaa"+service);
		log.info("aaaaaaaaaaa"+service.getClass().getName());
	}

	@Test
	public void testAdd() throws Exception{
		log.info(service.doAdd("123", "456"));
	}
	@Test
	public void testException() throws Exception{
		log.info(service.doAdd("123", "한글이라고!!!"));
	}
	
	
	
}
