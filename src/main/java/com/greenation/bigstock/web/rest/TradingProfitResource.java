package com.greenation.bigstock.web.rest;

import java.net.URISyntaxException;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.greenation.bigstock.domain.TotalProfit;
import com.greenation.bigstock.service.ProfitService;

@RestController
@RequestMapping("/api")
public class TradingProfitResource {

	private final Logger log = LoggerFactory.getLogger(TradingProfitResource.class);
	
	@Inject
    private ProfitService profitService;
	
	@RequestMapping(value = "/tradingProfits",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TotalProfit> getAllProfit() throws URISyntaxException {
		TotalProfit totalProfit = profitService.getTotalProfits();
        return new ResponseEntity<>(totalProfit, HttpStatus.OK);
    }
	
	
}
