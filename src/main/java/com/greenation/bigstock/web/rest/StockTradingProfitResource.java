package com.greenation.bigstock.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.greenation.bigstock.domain.StockTradingProfit;
import com.greenation.bigstock.repository.StockTradingProfitRepository;
import com.greenation.bigstock.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing StockTradingProfit.
 */
@RestController
@RequestMapping("/api")
public class StockTradingProfitResource {

    private final Logger log = LoggerFactory.getLogger(StockTradingProfitResource.class);

    @Inject
    private StockTradingProfitRepository stockTradingProfitRepository;

    /**
     * POST  /stockTradingProfits -> Create a new stockTradingProfit.
     */
    @RequestMapping(value = "/stockTradingProfits",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@Valid @RequestBody StockTradingProfit stockTradingProfit) throws URISyntaxException {
        log.debug("REST request to save StockTradingProfit : {}", stockTradingProfit);
        if (stockTradingProfit.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new stockTradingProfit cannot already have an ID").build();
        }
        stockTradingProfitRepository.save(stockTradingProfit);
        return ResponseEntity.created(new URI("/api/stockTradingProfits/" + stockTradingProfit.getId())).build();
    }

    /**
     * PUT  /stockTradingProfits -> Updates an existing stockTradingProfit.
     */
    @RequestMapping(value = "/stockTradingProfits",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@Valid @RequestBody StockTradingProfit stockTradingProfit) throws URISyntaxException {
        log.debug("REST request to update StockTradingProfit : {}", stockTradingProfit);
        if (stockTradingProfit.getId() == null) {
            return create(stockTradingProfit);
        }
        stockTradingProfitRepository.save(stockTradingProfit);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /stockTradingProfits -> get all the stockTradingProfits.
     */
    @RequestMapping(value = "/stockTradingProfits",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<StockTradingProfit>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<StockTradingProfit> page = stockTradingProfitRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stockTradingProfits", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /stockTradingProfits/:id -> get the "id" stockTradingProfit.
     */
    @RequestMapping(value = "/stockTradingProfits/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<StockTradingProfit> get(@PathVariable Long id) {
        log.debug("REST request to get StockTradingProfit : {}", id);
        return Optional.ofNullable(stockTradingProfitRepository.findOne(id))
            .map(stockTradingProfit -> new ResponseEntity<>(
                stockTradingProfit,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /stockTradingProfits/:id -> delete the "id" stockTradingProfit.
     */
    @RequestMapping(value = "/stockTradingProfits/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete StockTradingProfit : {}", id);
        stockTradingProfitRepository.delete(id);
    }
}
