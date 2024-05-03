package interview.com.cathayunited.schedule;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import interview.com.cathayunited.common.utils.Utils;
import interview.com.cathayunited.entity.Currency;
import interview.com.cathayunited.repository.CurrencyRepository;
import interview.com.cathayunited.response.CoindeskResponse;

@Component
public class SynchronizationExchangeRate {

	@Autowired
	private CurrencyRepository currencyRepository;

	private final String COINDESK_API_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";

	@LogExecutionTime
	@Scheduled(fixedDelay = 10000)
	public void synchronizationExchangeRateTask() throws Exception {

		try {
			RestTemplate restTemplate = new RestTemplate();
			CoindeskResponse coindeskResponse = restTemplate.getForObject(COINDESK_API_URL, CoindeskResponse.class);
			Optional<Currency> currencyUsd = currencyRepository
					.findOneByCode(coindeskResponse.getBpi().getUsd().getCode());

			if (currencyUsd.isPresent()) {
				currencyUsd.get().setRate_float(coindeskResponse.getBpi().getUsd().getRate_float());
				currencyUsd.get().setRate(coindeskResponse.getBpi().getUsd().getRate());
				currencyUsd.get().setSymbol(coindeskResponse.getBpi().getUsd().getSymbol());
				currencyRepository.save(currencyUsd.get());
			} else {
				Currency currencyUsdCreate = Currency.builder().name("USD").code("USD")
						.rate_float(coindeskResponse.getBpi().getUsd().getRate_float())
						.rate(coindeskResponse.getBpi().getUsd().getRate())
						.symbol(coindeskResponse.getBpi().getUsd().getSymbol()).description("United States Dollar")
						.build();
				this.currencyRepository.save(currencyUsdCreate);
			}

			Optional<Currency> currencyEur = currencyRepository
					.findOneByCode(coindeskResponse.getBpi().getEur().getCode());
			if (currencyEur.isPresent()) {
				currencyEur.get().setRate_float(coindeskResponse.getBpi().getEur().getRate_float());
				currencyEur.get().setRate(coindeskResponse.getBpi().getEur().getRate());
				currencyEur.get().setSymbol(coindeskResponse.getBpi().getEur().getSymbol());
				currencyRepository.save(currencyEur.get());
			} else {
				Currency currencyEurCreate = Currency.builder().name("EUR").code("EUR")
						.rate(coindeskResponse.getBpi().getUsd().getRate())
						.symbol(coindeskResponse.getBpi().getUsd().getSymbol())
						.rate_float(coindeskResponse.getBpi().getEur().getRate_float()).description("Euro").build();
				this.currencyRepository.save(currencyEurCreate);
			}

			Optional<Currency> currencyGbd = currencyRepository
					.findOneByCode(coindeskResponse.getBpi().getGbd().getCode());
			if (currencyGbd.isPresent()) {
				currencyGbd.get().setRate_float(coindeskResponse.getBpi().getGbd().getRate_float());
				currencyGbd.get().setRate(coindeskResponse.getBpi().getGbd().getRate());
				currencyGbd.get().setSymbol(coindeskResponse.getBpi().getGbd().getSymbol());
				currencyRepository.save(currencyGbd.get());
			} else {
				Currency currencyGbdCreate = Currency.builder().name("GBP").code("GBP")
						.rate_float(coindeskResponse.getBpi().getGbd().getRate_float())
						.rate(coindeskResponse.getBpi().getUsd().getRate())
						.symbol(coindeskResponse.getBpi().getUsd().getSymbol()).description("British Pound Sterling")
						.build();
				this.currencyRepository.save(currencyGbdCreate);
			}

			System.out.println(
					String.format("%s: SynchronizationExchangeRate SUCCESS", Utils.getDatetimeFormatVN(new Date())));
		} catch (Exception e) {
			System.out.println(String.format("%s: SynchronizationExchangeRate ERROR: %s",
					Utils.getDatetimeFormatVN(new Date()), e.getLocalizedMessage()));
		}

	}
}