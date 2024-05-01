package interview.com.cathayunited.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class CRUDCurrencyRequest {
	@Schema(description = "Name can not be null!")
	@NotEmpty(message = "Name can not be null!")
	@JsonProperty("name")
	private String name;

	@Schema(description = "Code can not be null!")
	@NotEmpty(message = "Code can not be null!")
	@JsonProperty("code")
	private String code;

	@Schema(description = "Rate_float must be >=0!")
	@Min(value = 0, message = ("Rate_float must be >=0!"))
	@JsonProperty("rate_float")
	private float rateFloat;

	@Schema(description = "Code can not be null!")
	@NotEmpty(message = "Code can not be null!")
	@JsonProperty("description")
	private String description;

	public CRUDCurrencyRequest(@NotEmpty(message = "Name can not be null!") String name,
			@NotEmpty(message = "Code can not be null!") String code,
			@Min(value = 0, message = "Rate_float must be >=0!") float rateFloat,
			@NotEmpty(message = "Code can not be null!") String description) {
		super();
		this.name = name;
		this.code = code;
		this.rateFloat = rateFloat;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

}
