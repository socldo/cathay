package interview.com.cathayunited.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import interview.com.cathayunited.common.utils.Utils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoindeskResponse {

	@JsonProperty("time")
	private Time time;

	@JsonProperty("disclaimer")
	private String disclaimer;

	@JsonProperty("chartName")
	private String chartName;

	@JsonProperty("bpi")
	private Bpi bpi;

	public Bpi getBpi() {
		return bpi;
	}

	public void setBpi(Bpi bpi) {
		this.bpi = bpi;
	}
	

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}


	public static class Time {

		@JsonProperty("updated")
		private String updated;

		@JsonProperty("updatedISO")
		private String updatedISO;

		@JsonProperty("updateduk")
		private String updateduk;

		public String getUpdated() {
			return Utils.getDatetimeFormatVN(updatedISO);
		}

		public void setUpdated(String updated) {
			this.updated = updated;
		}

		public String getUpdatedISO() {
			return updatedISO;
		}

		public void setUpdatedISO(String updatedISO) {
			this.updatedISO = updatedISO;
		}

		public String getUpdateduk() {
			return updateduk;
		}

		public void setUpdateduk(String updateduk) {
			this.updateduk = updateduk;
		}

	}

	public static class Bpi {

		@JsonProperty("USD")
		private Currency usd;

		@JsonProperty("GBP")
		private Currency gbd;

		@JsonProperty("EUR")
		private Currency eur;

		public Currency getUsd() {
			return usd;
		}

		public void setUsd(Currency usd) {
			this.usd = usd;
		}

		public Currency getGbd() {
			return gbd;
		}

		public void setGbd(Currency gbd) {
			this.gbd = gbd;
		}

		public Currency getEur() {
			return eur;
		}

		public void setEur(Currency eur) {
			this.eur = eur;
		}

	}

	public static class Currency {
		private String code;
		private String symbol;
		private String rate;
		private String description;
		private float rate_float;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getRate() {
			return rate;
		}

		public void setRate(String rate) {
			this.rate = rate;
		}

		public String getSymbol() {
			return symbol;
		}

		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public float getRate_float() {
			return rate_float;
		}

		public void setRate_float(float rate_float) {
			this.rate_float = rate_float;
		}

	}
}