package interview.com.cathayunited.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import interview.com.cathayunited.common.utils.Utils;
import interview.com.cathayunited.entity.Currency;

public class CurrencyResponse {

	private Integer id;

	@JsonProperty("code")
	private String code;

	@JsonProperty("name")
	private String name;

	@JsonProperty("rate")
	private String rate;

	@JsonProperty("symbol")
	private String symbol;

	@JsonProperty("rate_float")
	private float rateFloat;

	@JsonProperty("description")
	private String description;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("updated_at")
	private String updatedAt;

	public CurrencyResponse() {

	}

	public CurrencyResponse(Currency currency) {
		this.id = currency.getId();
		this.code = currency.getCode();
		this.name = currency.getName();
		this.rateFloat = currency.getRate_float();
		this.description = currency.getDescription();
		this.rate = currency.getRate();
		this.symbol = currency.getSymbol();
		this.createdAt = Utils.getDateFormatVN(currency.getCreatedAt());
		this.updatedAt = Utils.getDateFormatVN(currency.getUpdatedAt());
	}

	public List<CurrencyResponse> mapToListResponse(List<Currency> baseEntities) {
		return baseEntities.stream().map(x -> new CurrencyResponse(x)).collect(Collectors.toList());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getRateFloat() {
		return rateFloat;
	}

	public void setRateFloat(float rateFloat) {
		this.rateFloat = rateFloat;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

}
