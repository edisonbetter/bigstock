package com.greenation.bigstock.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.greenation.bigstock.domain.FundTradingProfit;
import com.greenation.bigstock.domain.StockTradingProfit;
import com.greenation.bigstock.repository.FundTradingProfitRepository;
import com.greenation.bigstock.security.SecurityUtils;
import com.greenation.bigstock.service.ProfitService;
import com.greenation.bigstock.web.rest.util.PaginationUtil;

/**
 * REST controller for managing FundTradingProfit.
 */
@RestController
@RequestMapping("/api")
public class FundTradingProfitResource {
	private final Logger log = LoggerFactory.getLogger(FundTradingProfitResource.class);

    @Inject
    private FundTradingProfitRepository fundTradingProfitRepository;

    @Inject
    private ProfitService profitService;
    
    /**
     * POST  /fudnTradingProfits -> Create a new fundTradingProfit.
     */
    @RequestMapping(value = "/fundTradingProfits",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@Valid @RequestBody FundTradingProfit fundTradingProfit) throws URISyntaxException {
        log.debug("REST request to save FundTradingProfit : {}", fundTradingProfit);
        if (fundTradingProfit.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new fundTradingProfit cannot already have an ID").build();
        }
        fundTradingProfit.setUser(SecurityUtils.getCurrentLogin());
        fundTradingProfitRepository.save(fundTradingProfit);
        addToTotalProfit(fundTradingProfit);
        return ResponseEntity.created(new URI("/api/fundTradingProfits/" + fundTradingProfit.getId())).build();
    }

    /**
     * PUT  /fundTradingProfits -> Updates an existing fundTradingProfit.
     */
    @RequestMapping(value = "/fundTradingProfits",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@Valid @RequestBody FundTradingProfit fundTradingProfit) throws URISyntaxException {
        log.debug("REST request to update FundTradingProfit : {}", fundTradingProfit);
        if (fundTradingProfit.getId() == null) {
            return create(fundTradingProfit);
        }
        updateTotalProfit(fundTradingProfit);
        fundTradingProfitRepository.save(fundTradingProfit);
        return ResponseEntity.ok().build();
    }

    private void addToTotalProfit(FundTradingProfit fundTradingProfit){
    	profitService.addToTotalProfits("Fund", fundTradingProfit);
    }
    
    private void updateTotalProfit(FundTradingProfit fundTradingProfit){
    	profitService.updateTotalProfits("Fund", fundTradingProfit);
    }

    /**
     * GET  /fundTradingProfits -> get all the fundTradingProfits.
     */
    @RequestMapping(value = "/fundTradingProfits",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<FundTradingProfit>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<FundTradingProfit> page = fundTradingProfitRepository.findAll(PaginationUtil.generatePageRequest(offset, limit, Direction.DESC, "creationDate"));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fundTradingProfits", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /fundTradingProfits/hkd,cny -> get all the fundTradingProfits with currency=HKD or CNY.
     */
    @RequestMapping(value = {"/fundTradingProfits/hkd", "/fundTradingProfits/cny"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<FundTradingProfit>> getAllByCurrency(@RequestParam(value="currency", required=false) String currency, @RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<FundTradingProfit> page = fundTradingProfitRepository.findByCurrency(currency, PaginationUtil.generatePageRequest(offset, limit));
        StringBuffer baseUrl = new StringBuffer("/api/fundTradingProfits/").append(currency.toLowerCase());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, baseUrl.toString(), offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /fundTradingProfits/:id -> get the "id" fundTradingProfit.
     */
    @RequestMapping(value = "/fundTradingProfits/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FundTradingProfit> get(@PathVariable Long id) {
        log.debug("REST request to get FundTradingProfit : {}", id);
        return Optional.ofNullable(fundTradingProfitRepository.findOne(id))
            .map(fundTradingProfit -> new ResponseEntity<>(
                fundTradingProfit,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /fundTradingProfits/:id -> delete the "id" fundTradingProfit.
     */
    @RequestMapping(value = "/fundTradingProfits/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete FundTradingProfit : {}", id);
        fundTradingProfitRepository.delete(id);
    }
}
