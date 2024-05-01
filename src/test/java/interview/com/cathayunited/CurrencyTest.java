package interview.com.cathayunited;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import interview.com.cathayunited.entity.Currency;
import interview.com.cathayunited.repository.CurrencyRepository;
import interview.com.cathayunited.request.CRUDCurrencyRequest;
import interview.com.cathayunited.response.CoindeskFormatResponse;
import jakarta.validation.constraints.Min;

@WebMvcTest
public class CurrencyTest {
	@MockBean
	private CurrencyRepository currencyRepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void convertCoinDeskTest() throws Exception {
		// given - precondition or setup
		List<CoindeskFormatResponse> listOfCurrencies = new ArrayList<>();
		listOfCurrencies.add(new CoindeskFormatResponse("01/05/2024 10:54:12", "GBP", (float) 48.2, "GBP"));
		listOfCurrencies.add(new CoindeskFormatResponse("01/05/2024 10:54:12", "USD", (float) 60.35, "USD"));
		listOfCurrencies.add(new CoindeskFormatResponse("01/05/2024 10:54:12", "EUR", (float) 50.66, "EUR"));
		ResultActions response = mockMvc.perform(get("/api/current-price").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(listOfCurrencies)));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(listOfCurrencies.size())));

	}

	@Test
	void testCreateNewCurrency() throws Exception {
		CRUDCurrencyRequest cRUDCurrencyRequest = new CRUDCurrencyRequest("Japan", "JPY",
				(@Min(value = 0, message = "Rate_float must be >=0!") float) 1.56, "Currency of Japan");
		Currency currency = Currency.builder().name(cRUDCurrencyRequest.getName()).code(cRUDCurrencyRequest.getName())
				.rate_float(cRUDCurrencyRequest.getRateFloat()).description(cRUDCurrencyRequest.getDescription())
				.build();

		currencyRepository.save(currency);
		given(currencyRepository.save(currency)).willAnswer((invocation) -> invocation.getArgument(0));
		ResultActions response = mockMvc.perform(post("/api/currency/create").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(currency)));
		// then - verify the result or output using assert statements
		response.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.data.code", is(currency.getCode())))
				.andExpect(jsonPath("$.data.name", is(currency.getName())))
				.andExpect(jsonPath("$.data.description", is(currency.getDescription())))
				.andExpect(jsonPath("$.data.rate_float").value(currency.getRate_float()));
	}

	@Test
	void testUpdateNewCurrency() throws Exception {
		CRUDCurrencyRequest cRUDCurrencyRequest = new CRUDCurrencyRequest("Japan", "JPY",
				(@Min(value = 0, message = "Rate_float must be >=0!") float) 1.56, "Currency of Japan");
		Optional<Currency> currency = this.currencyRepository.findById(1);
		if (currency.isPresent()) {
			Currency currencyData = currency.get();
			currencyData.setCode(cRUDCurrencyRequest.getCode());
			currencyData.setName(cRUDCurrencyRequest.getName());
			currencyData.setDescription(cRUDCurrencyRequest.getDescription());
			currencyData.setRate_float(cRUDCurrencyRequest.getRateFloat());
			currencyRepository.save(currencyData);
			given(currencyRepository.save(currencyData)).willAnswer((invocation) -> invocation.getArgument(0));
			ResultActions response = mockMvc.perform(put("/api/currency/1/update")
					.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(currency)));
			// then - verify the result or output using assert statements
			response.andDo(print()).andExpect(status().isCreated())
					.andExpect(jsonPath("$.code", is(currencyData.getCode())))
					.andExpect(jsonPath("$.name", is(currencyData.getName())))
					.andExpect(jsonPath("$.description", is(currencyData.getDescription())))
					.andExpect(jsonPath("$.rate_float", is(currencyData.getRate_float())));
		}

	}

	@Test
	public void getListCurrencyTest() throws Exception {
		// given - precondition or setup
		List<Currency> listOfCurrencies = new ArrayList<>();
		listOfCurrencies.add(Currency.builder().name("VND").code("VND").rate_float((float) 1.66)
				.description("Viet Nam dong").build());
		listOfCurrencies.add(Currency.builder().name("USD").code("USD").rate_float((float) 20.66)
				.description("United State").build());
		listOfCurrencies.add(Currency.builder().name("GPB").code("GPB").rate_float((float) 20.66)
				.description("British Pound Sterling").build());
		given(currencyRepository.findAll()).willReturn(listOfCurrencies);

		ResultActions response = mockMvc.perform(get("/api/currency"));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(listOfCurrencies.size())));

	}

	@Test
	void testDeleteCurrency() throws Exception {
		Optional<Currency> currency = this.currencyRepository.findById(1);
		if (currency.isPresent()) {
			Currency currencyData = currency.get();
			currencyRepository.delete(currencyData);
			given(currencyRepository.save(currencyData)).willAnswer((invocation) -> invocation.getArgument(0));
			ResultActions response = mockMvc.perform(put("/api/currency/1/update")
					.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(currency)));
			// then - verify the result or output using assert statements
			response.andDo(print()).andExpect(status().isCreated())
					.andExpect(jsonPath("$.code", is(currencyData.getCode())))
					.andExpect(jsonPath("$.name", is(currencyData.getName())))
					.andExpect(jsonPath("$.description", is(currencyData.getDescription())))
					.andExpect(jsonPath("$.rate_float", is(currencyData.getRate_float())));
		}

	}

}
