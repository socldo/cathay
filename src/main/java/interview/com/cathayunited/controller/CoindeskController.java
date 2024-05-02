package interview.com.cathayunited.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import interview.com.cathayunited.response.BaseResponse;
import interview.com.cathayunited.response.CoindeskFormatResponse;
import interview.com.cathayunited.response.CoindeskResponse;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
public class CoindeskController {
	private final String COINDESK_API_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";

	private final MessageSource messageSource = null;

	@Operation(summary = "Call coindesk API, analyze its downstream content and data conversion, and\n"
			+ "implement the new API.", description = "coindesk API: https://api.coindesk.com/v1/bpi/currentprice.json")
	@GetMapping("/current-price")
	public ResponseEntity<BaseResponse<Object>> getCurrentPriceUSD(
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		BaseResponse<Object> response = new BaseResponse<>();
		RestTemplate restTemplate = new RestTemplate();
		CoindeskResponse coindeskResponse = restTemplate.getForObject(COINDESK_API_URL, CoindeskResponse.class);
		/**
		 * Handle return exchange value Example: Returns Currency-related information
		 * (currency, name of currency, and exchange rate). Update time (time format
		 * example: 1990/01/01 00:00:00).
		 */
		List<CoindeskFormatResponse> coindeskFormatResponseList = new ArrayList<>();
		CoindeskFormatResponse coindeskFormatResponseUSD = new CoindeskFormatResponse(
				coindeskResponse.getTime().getUpdated(), coindeskResponse.getBpi().getUsd().getDescription(),
				coindeskResponse.getBpi().getUsd().getRate_float(), messageSource.getMessage("USD", null, locale));
		coindeskFormatResponseList.add(coindeskFormatResponseUSD);

		CoindeskFormatResponse coindeskFormatResponseGBP = new CoindeskFormatResponse(
				coindeskResponse.getTime().getUpdated(), coindeskResponse.getBpi().getGbd().getDescription(),
				coindeskResponse.getBpi().getGbd().getRate_float(), messageSource.getMessage("GBP", null, locale));
		coindeskFormatResponseList.add(coindeskFormatResponseGBP);

		CoindeskFormatResponse coindeskFormatResponseEUR = new CoindeskFormatResponse(
				coindeskResponse.getTime().getUpdated(), coindeskResponse.getBpi().getEur().getDescription(),
				coindeskResponse.getBpi().getEur().getRate_float(), messageSource.getMessage("EUR", null, locale));
		coindeskFormatResponseList.add(coindeskFormatResponseEUR);
		response.setData(coindeskFormatResponseList);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
