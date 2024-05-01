package interview.com.cathayunited.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import interview.com.cathayunited.entity.Currency;

public class CoindeskFormatResponse {
	@JsonProperty("update_time")
	private String updateTime;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("name")
	private String name;

	@JsonProperty("rate")
	private float rate;

	public CoindeskFormatResponse(String updateTime, String name, float rate, String currencyName) {
		super();
		this.updateTime = updateTime;
		this.currency = currencyName;
		this.name = name;
		this.rate = rate;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

}
