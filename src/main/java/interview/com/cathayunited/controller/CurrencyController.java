package interview.com.cathayunited.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import interview.com.cathayunited.entity.Currency;
import interview.com.cathayunited.repository.CurrencyRepository;
import interview.com.cathayunited.request.CRUDCurrencyRequest;
import interview.com.cathayunited.response.BaseResponse;
import interview.com.cathayunited.response.CurrencyResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/currency")
public class CurrencyController {

	@Autowired
	private CurrencyRepository currencyRepository;

	@Operation(summary = "API create currency", description = "API create currency")
	@PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<CurrencyResponse>> createCurrency(
			@io.swagger.v3.oas.annotations.parameters.RequestBody @Valid @RequestBody CRUDCurrencyRequest request)
			throws Exception {
		BaseResponse<CurrencyResponse> response = new BaseResponse<>();
		Optional<Currency> currencyFindByCode = this.currencyRepository.findOneByCode(request.getCode());
		if (!currencyFindByCode.isEmpty()) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Currency code is exist!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		Currency currency = Currency.builder().name(request.getName()).code(request.getCode())
				.rate_float(request.getRateFloat()).description(request.getDescription()).build();

		this.currencyRepository.save(currency);
		CurrencyResponse resposneData = new CurrencyResponse(currency);
		response.setData(resposneData);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API udpate currency", description = "API update currency")
	@PutMapping(value = "/{id}/update", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<CurrencyResponse>> updateCurrency(@PathVariable("id") int id,
			@io.swagger.v3.oas.annotations.parameters.RequestBody @Valid @RequestBody CRUDCurrencyRequest request)
			throws Exception {
		BaseResponse<CurrencyResponse> response = new BaseResponse<>();
		Optional<Currency> currency = this.currencyRepository.findById(id);
		if (currency.isEmpty()) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Currency not exist!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		Optional<Currency> currencyFindByCode = this.currencyRepository.findOneByCode(request.getCode());
		if (!currencyFindByCode.isEmpty() && currencyFindByCode.get().getId() != id) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Currency code is exist!");
			return new ResponseEntity<>(response, HttpStatus.OK);

		}
		currency.get().setName(request.getName());
		currency.get().setCode(request.getCode());
		currency.get().setRate_float(request.getRateFloat());
		currency.get().setDescription(request.getDescription());
		this.currencyRepository.save(currency.get());
		CurrencyResponse resposneData = new CurrencyResponse(currency.get());
		response.setData(resposneData);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API delete currency", description = "API delete currency")
	@DeleteMapping(value = "/{id}/delete", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<CurrencyResponse>> deleteCurrency(@PathVariable("id") int id) throws Exception {
		BaseResponse<CurrencyResponse> response = new BaseResponse<>();
		Optional<Currency> currency = this.currencyRepository.findById(id);
		if (currency.isEmpty()) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Currency not exist!");
			return new ResponseEntity<>(response, HttpStatus.OK);

		}
		this.currencyRepository.delete(currency.get());
		CurrencyResponse resposneData = new CurrencyResponse(currency.get());
		response.setData(resposneData);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API query list currency sort by currency code.", description = "API query list currency sort by currency code.")
	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<CurrencyResponse>>> queryCurrency() throws Exception {
		BaseResponse<List<CurrencyResponse>> response = new BaseResponse<>();
		Sort sortByCode = Sort.by("code").ascending();

		List<Currency> currency = this.currencyRepository.findAll(sortByCode);

		List<CurrencyResponse> resposneData = new CurrencyResponse().mapToListResponse(currency);
		response.setData(resposneData);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
