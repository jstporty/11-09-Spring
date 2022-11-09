package kr.kwangan2.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kwangan2.rest.domain.Person;
import kr.kwangan2.rest.domain.Ticket;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/test")
@Log4j
public class RestTestController {

	@RequestMapping(value = "/plainText", produces = "text/plain; charset=UTF-8")
	public String getText() {

		log.info("MINE TYPE:" + MediaType.TEXT_PLAIN_VALUE);

		return "안녕 REST____in_____peace_____TAKEOFF....~";
	}

	@RequestMapping(value = "/object", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public Person getObject() {
		return new Person("홍길동", 30);
	}

	@RequestMapping("/getList")
	public List<Person> getList() {

		return IntStream.range(1, 10).mapToObj(i -> new Person("이름" + i, i)).collect(Collectors.toList());
	}

	@RequestMapping("/getMap")
	public Map<String, Person> getMapp() {
		Map<String, Person> map = new HashMap<String, Person>();

		map.put("Takeoff...", new Person("takeoff", 30));
		map.put("Offset...", new Person("Offset", 30));
		map.put("Quavo...", new Person("Quavo", 30));
		return map;
	}

	@RequestMapping(value = "/responseEntity", params = { "height", "weight" })
	public ResponseEntity<Person> responseEntity(double height, double weight) {
		Person person = new Person("홍길동", 30);
		ResponseEntity<Person> result = null;
		if (height > 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(person);
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(person);
		}
		return result;
	}

	@RequestMapping(value = "/getPath/{cat}/{pid}")
	public String[] getPath(
			@PathVariable("cat") String cat, 
			@PathVariable("pid") String pid
			){
		return new String[] { "category:" + cat, "productid:" + pid };
	}
	
	
	@PostMapping("/requestBody")
	public Ticket requestBody(@RequestBody Ticket ticket) {
		
		log.info("convert...........ticket"+ ticket);
		
		return ticket;
	}
}
// class










