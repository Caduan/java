package com.example.practice16;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Practice16ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetRequest() throws Exception {
		// Тестируем, что GET запрос на "/example/data" возвращает статус 200
		mockMvc.perform(get("/example/data"))
				.andExpect(status().isOk());
	}
}
