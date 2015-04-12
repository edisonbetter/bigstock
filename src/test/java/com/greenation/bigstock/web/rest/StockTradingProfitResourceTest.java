package com.greenation.bigstock.web.rest;

import com.greenation.bigstock.Application;
import com.greenation.bigstock.domain.StockTradingProfit;
import com.greenation.bigstock.repository.StockTradingProfitRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.joda.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the StockTradingProfitResource REST controller.
 *
 * @see StockTradingProfitResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class StockTradingProfitResourceTest {


    private static final LocalDate DEFAULT_CREATION_DATE = new LocalDate(0L);
    private static final LocalDate UPDATED_CREATION_DATE = new LocalDate();
    private static final String DEFAULT_CODE = "SAMPLE_TEXT";
    private static final String UPDATED_CODE = "UPDATED_TEXT";
    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";

    private static final BigDecimal DEFAULT_QUANTITY = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_QUANTITY = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_BUY_PRICE = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_BUY_PRICE = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_SELL_PRICE = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_SELL_PRICE = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_BUY_TRANSACTION_FEE = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_BUY_TRANSACTION_FEE = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_SELL_TRANSACTION_FEE = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_SELL_TRANSACTION_FEE = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_BUY_CONSIDERATION = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_BUY_CONSIDERATION = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_SELL_CONSIDERATION = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_SELL_CONSIDERATION = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_PROFIT = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_PROFIT = BigDecimal.ONE;
    private static final String DEFAULT_CURRENCY = "SAMPLE_TEXT";
    private static final String UPDATED_CURRENCY = "UPDATED_TEXT";
    private static final String DEFAULT_MARKET = "SAMPLE_TEXT";
    private static final String UPDATED_MARKET = "UPDATED_TEXT";

    @Inject
    private StockTradingProfitRepository stockTradingProfitRepository;

    private MockMvc restStockTradingProfitMockMvc;

    private StockTradingProfit stockTradingProfit;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        StockTradingProfitResource stockTradingProfitResource = new StockTradingProfitResource();
        ReflectionTestUtils.setField(stockTradingProfitResource, "stockTradingProfitRepository", stockTradingProfitRepository);
        this.restStockTradingProfitMockMvc = MockMvcBuilders.standaloneSetup(stockTradingProfitResource).build();
    }

    @Before
    public void initTest() {
        stockTradingProfit = new StockTradingProfit();
        stockTradingProfit.setCreationDate(DEFAULT_CREATION_DATE);
        stockTradingProfit.setCode(DEFAULT_CODE);
        stockTradingProfit.setName(DEFAULT_NAME);
        stockTradingProfit.setQuantity(DEFAULT_QUANTITY);
        stockTradingProfit.setBuyPrice(DEFAULT_BUY_PRICE);
        stockTradingProfit.setSellPrice(DEFAULT_SELL_PRICE);
        stockTradingProfit.setBuyTransactionFee(DEFAULT_BUY_TRANSACTION_FEE);
        stockTradingProfit.setSellTransactionFee(DEFAULT_SELL_TRANSACTION_FEE);
        stockTradingProfit.setBuyConsideration(DEFAULT_BUY_CONSIDERATION);
        stockTradingProfit.setSellConsideration(DEFAULT_SELL_CONSIDERATION);
        stockTradingProfit.setProfit(DEFAULT_PROFIT);
        stockTradingProfit.setCurrency(DEFAULT_CURRENCY);
        stockTradingProfit.setMarket(DEFAULT_MARKET);
    }

    @Test
    @Transactional
    public void createStockTradingProfit() throws Exception {
        int databaseSizeBeforeCreate = stockTradingProfitRepository.findAll().size();

        // Create the StockTradingProfit
        restStockTradingProfitMockMvc.perform(post("/api/stockTradingProfits")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(stockTradingProfit)))
                .andExpect(status().isCreated());

        // Validate the StockTradingProfit in the database
        List<StockTradingProfit> stockTradingProfits = stockTradingProfitRepository.findAll();
        assertThat(stockTradingProfits).hasSize(databaseSizeBeforeCreate + 1);
        StockTradingProfit testStockTradingProfit = stockTradingProfits.get(stockTradingProfits.size() - 1);
        assertThat(testStockTradingProfit.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testStockTradingProfit.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testStockTradingProfit.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStockTradingProfit.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testStockTradingProfit.getBuyPrice()).isEqualTo(DEFAULT_BUY_PRICE);
        assertThat(testStockTradingProfit.getSellPrice()).isEqualTo(DEFAULT_SELL_PRICE);
        assertThat(testStockTradingProfit.getBuyTransactionFee()).isEqualTo(DEFAULT_BUY_TRANSACTION_FEE);
        assertThat(testStockTradingProfit.getSellTransactionFee()).isEqualTo(DEFAULT_SELL_TRANSACTION_FEE);
        assertThat(testStockTradingProfit.getBuyConsideration()).isEqualTo(DEFAULT_BUY_CONSIDERATION);
        assertThat(testStockTradingProfit.getSellConsideration()).isEqualTo(DEFAULT_SELL_CONSIDERATION);
        assertThat(testStockTradingProfit.getProfit()).isEqualTo(DEFAULT_PROFIT);
        assertThat(testStockTradingProfit.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testStockTradingProfit.getMarket()).isEqualTo(DEFAULT_MARKET);
    }

    @Test
    @Transactional
    public void checkCreationDateIsRequired() throws Exception {
        // Validate the database is empty
        assertThat(stockTradingProfitRepository.findAll()).hasSize(0);
        // set the field null
        stockTradingProfit.setCreationDate(null);

        // Create the StockTradingProfit, which fails.
        restStockTradingProfitMockMvc.perform(post("/api/stockTradingProfits")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(stockTradingProfit)))
                .andExpect(status().isBadRequest());

        // Validate the database is still empty
        List<StockTradingProfit> stockTradingProfits = stockTradingProfitRepository.findAll();
        assertThat(stockTradingProfits).hasSize(0);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        // Validate the database is empty
        assertThat(stockTradingProfitRepository.findAll()).hasSize(0);
        // set the field null
        stockTradingProfit.setCode(null);

        // Create the StockTradingProfit, which fails.
        restStockTradingProfitMockMvc.perform(post("/api/stockTradingProfits")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(stockTradingProfit)))
                .andExpect(status().isBadRequest());

        // Validate the database is still empty
        List<StockTradingProfit> stockTradingProfits = stockTradingProfitRepository.findAll();
        assertThat(stockTradingProfits).hasSize(0);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        // Validate the database is empty
        assertThat(stockTradingProfitRepository.findAll()).hasSize(0);
        // set the field null
        stockTradingProfit.setName(null);

        // Create the StockTradingProfit, which fails.
        restStockTradingProfitMockMvc.perform(post("/api/stockTradingProfits")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(stockTradingProfit)))
                .andExpect(status().isBadRequest());

        // Validate the database is still empty
        List<StockTradingProfit> stockTradingProfits = stockTradingProfitRepository.findAll();
        assertThat(stockTradingProfits).hasSize(0);
    }

    @Test
    @Transactional
    public void checkQuantityIsRequired() throws Exception {
        // Validate the database is empty
        assertThat(stockTradingProfitRepository.findAll()).hasSize(0);
        // set the field null
        stockTradingProfit.setQuantity(null);

        // Create the StockTradingProfit, which fails.
        restStockTradingProfitMockMvc.perform(post("/api/stockTradingProfits")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(stockTradingProfit)))
                .andExpect(status().isBadRequest());

        // Validate the database is still empty
        List<StockTradingProfit> stockTradingProfits = stockTradingProfitRepository.findAll();
        assertThat(stockTradingProfits).hasSize(0);
    }

    @Test
    @Transactional
    public void checkBuyPriceIsRequired() throws Exception {
        // Validate the database is empty
        assertThat(stockTradingProfitRepository.findAll()).hasSize(0);
        // set the field null
        stockTradingProfit.setBuyPrice(null);

        // Create the StockTradingProfit, which fails.
        restStockTradingProfitMockMvc.perform(post("/api/stockTradingProfits")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(stockTradingProfit)))
                .andExpect(status().isBadRequest());

        // Validate the database is still empty
        List<StockTradingProfit> stockTradingProfits = stockTradingProfitRepository.findAll();
        assertThat(stockTradingProfits).hasSize(0);
    }

    @Test
    @Transactional
    public void checkSellPriceIsRequired() throws Exception {
        // Validate the database is empty
        assertThat(stockTradingProfitRepository.findAll()).hasSize(0);
        // set the field null
        stockTradingProfit.setSellPrice(null);

        // Create the StockTradingProfit, which fails.
        restStockTradingProfitMockMvc.perform(post("/api/stockTradingProfits")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(stockTradingProfit)))
                .andExpect(status().isBadRequest());

        // Validate the database is still empty
        List<StockTradingProfit> stockTradingProfits = stockTradingProfitRepository.findAll();
        assertThat(stockTradingProfits).hasSize(0);
    }

    @Test
    @Transactional
    public void checkBuyTransactionFeeIsRequired() throws Exception {
        // Validate the database is empty
        assertThat(stockTradingProfitRepository.findAll()).hasSize(0);
        // set the field null
        stockTradingProfit.setBuyTransactionFee(null);

        // Create the StockTradingProfit, which fails.
        restStockTradingProfitMockMvc.perform(post("/api/stockTradingProfits")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(stockTradingProfit)))
                .andExpect(status().isBadRequest());

        // Validate the database is still empty
        List<StockTradingProfit> stockTradingProfits = stockTradingProfitRepository.findAll();
        assertThat(stockTradingProfits).hasSize(0);
    }

    @Test
    @Transactional
    public void checkSellTransactionFeeIsRequired() throws Exception {
        // Validate the database is empty
        assertThat(stockTradingProfitRepository.findAll()).hasSize(0);
        // set the field null
        stockTradingProfit.setSellTransactionFee(null);

        // Create the StockTradingProfit, which fails.
        restStockTradingProfitMockMvc.perform(post("/api/stockTradingProfits")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(stockTradingProfit)))
                .andExpect(status().isBadRequest());

        // Validate the database is still empty
        List<StockTradingProfit> stockTradingProfits = stockTradingProfitRepository.findAll();
        assertThat(stockTradingProfits).hasSize(0);
    }

    @Test
    @Transactional
    public void checkProfitIsRequired() throws Exception {
        // Validate the database is empty
        assertThat(stockTradingProfitRepository.findAll()).hasSize(0);
        // set the field null
        stockTradingProfit.setProfit(null);

        // Create the StockTradingProfit, which fails.
        restStockTradingProfitMockMvc.perform(post("/api/stockTradingProfits")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(stockTradingProfit)))
                .andExpect(status().isBadRequest());

        // Validate the database is still empty
        List<StockTradingProfit> stockTradingProfits = stockTradingProfitRepository.findAll();
        assertThat(stockTradingProfits).hasSize(0);
    }

    @Test
    @Transactional
    public void checkCurrencyIsRequired() throws Exception {
        // Validate the database is empty
        assertThat(stockTradingProfitRepository.findAll()).hasSize(0);
        // set the field null
        stockTradingProfit.setCurrency(null);

        // Create the StockTradingProfit, which fails.
        restStockTradingProfitMockMvc.perform(post("/api/stockTradingProfits")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(stockTradingProfit)))
                .andExpect(status().isBadRequest());

        // Validate the database is still empty
        List<StockTradingProfit> stockTradingProfits = stockTradingProfitRepository.findAll();
        assertThat(stockTradingProfits).hasSize(0);
    }

    @Test
    @Transactional
    public void checkMarketIsRequired() throws Exception {
        // Validate the database is empty
        assertThat(stockTradingProfitRepository.findAll()).hasSize(0);
        // set the field null
        stockTradingProfit.setMarket(null);

        // Create the StockTradingProfit, which fails.
        restStockTradingProfitMockMvc.perform(post("/api/stockTradingProfits")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(stockTradingProfit)))
                .andExpect(status().isBadRequest());

        // Validate the database is still empty
        List<StockTradingProfit> stockTradingProfits = stockTradingProfitRepository.findAll();
        assertThat(stockTradingProfits).hasSize(0);
    }

    @Test
    @Transactional
    public void getAllStockTradingProfits() throws Exception {
        // Initialize the database
        stockTradingProfitRepository.saveAndFlush(stockTradingProfit);

        // Get all the stockTradingProfits
        restStockTradingProfitMockMvc.perform(get("/api/stockTradingProfits"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(stockTradingProfit.getId().intValue())))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
                .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
                .andExpect(jsonPath("$.[*].buyPrice").value(hasItem(DEFAULT_BUY_PRICE.intValue())))
                .andExpect(jsonPath("$.[*].sellPrice").value(hasItem(DEFAULT_SELL_PRICE.intValue())))
                .andExpect(jsonPath("$.[*].buyTransactionFee").value(hasItem(DEFAULT_BUY_TRANSACTION_FEE.intValue())))
                .andExpect(jsonPath("$.[*].sellTransactionFee").value(hasItem(DEFAULT_SELL_TRANSACTION_FEE.intValue())))
                .andExpect(jsonPath("$.[*].buyConsideration").value(hasItem(DEFAULT_BUY_CONSIDERATION.intValue())))
                .andExpect(jsonPath("$.[*].sellConsideration").value(hasItem(DEFAULT_SELL_CONSIDERATION.intValue())))
                .andExpect(jsonPath("$.[*].profit").value(hasItem(DEFAULT_PROFIT.intValue())))
                .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.toString())))
                .andExpect(jsonPath("$.[*].market").value(hasItem(DEFAULT_MARKET.toString())));
    }

    @Test
    @Transactional
    public void getStockTradingProfit() throws Exception {
        // Initialize the database
        stockTradingProfitRepository.saveAndFlush(stockTradingProfit);

        // Get the stockTradingProfit
        restStockTradingProfitMockMvc.perform(get("/api/stockTradingProfits/{id}", stockTradingProfit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(stockTradingProfit.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.buyPrice").value(DEFAULT_BUY_PRICE.intValue()))
            .andExpect(jsonPath("$.sellPrice").value(DEFAULT_SELL_PRICE.intValue()))
            .andExpect(jsonPath("$.buyTransactionFee").value(DEFAULT_BUY_TRANSACTION_FEE.intValue()))
            .andExpect(jsonPath("$.sellTransactionFee").value(DEFAULT_SELL_TRANSACTION_FEE.intValue()))
            .andExpect(jsonPath("$.buyConsideration").value(DEFAULT_BUY_CONSIDERATION.intValue()))
            .andExpect(jsonPath("$.sellConsideration").value(DEFAULT_SELL_CONSIDERATION.intValue()))
            .andExpect(jsonPath("$.profit").value(DEFAULT_PROFIT.intValue()))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.toString()))
            .andExpect(jsonPath("$.market").value(DEFAULT_MARKET.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStockTradingProfit() throws Exception {
        // Get the stockTradingProfit
        restStockTradingProfitMockMvc.perform(get("/api/stockTradingProfits/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStockTradingProfit() throws Exception {
        // Initialize the database
        stockTradingProfitRepository.saveAndFlush(stockTradingProfit);
		
		int databaseSizeBeforeUpdate = stockTradingProfitRepository.findAll().size();

        // Update the stockTradingProfit
        stockTradingProfit.setCreationDate(UPDATED_CREATION_DATE);
        stockTradingProfit.setCode(UPDATED_CODE);
        stockTradingProfit.setName(UPDATED_NAME);
        stockTradingProfit.setQuantity(UPDATED_QUANTITY);
        stockTradingProfit.setBuyPrice(UPDATED_BUY_PRICE);
        stockTradingProfit.setSellPrice(UPDATED_SELL_PRICE);
        stockTradingProfit.setBuyTransactionFee(UPDATED_BUY_TRANSACTION_FEE);
        stockTradingProfit.setSellTransactionFee(UPDATED_SELL_TRANSACTION_FEE);
        stockTradingProfit.setBuyConsideration(UPDATED_BUY_CONSIDERATION);
        stockTradingProfit.setSellConsideration(UPDATED_SELL_CONSIDERATION);
        stockTradingProfit.setProfit(UPDATED_PROFIT);
        stockTradingProfit.setCurrency(UPDATED_CURRENCY);
        stockTradingProfit.setMarket(UPDATED_MARKET);
        restStockTradingProfitMockMvc.perform(put("/api/stockTradingProfits")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(stockTradingProfit)))
                .andExpect(status().isOk());

        // Validate the StockTradingProfit in the database
        List<StockTradingProfit> stockTradingProfits = stockTradingProfitRepository.findAll();
        assertThat(stockTradingProfits).hasSize(databaseSizeBeforeUpdate);
        StockTradingProfit testStockTradingProfit = stockTradingProfits.get(stockTradingProfits.size() - 1);
        assertThat(testStockTradingProfit.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testStockTradingProfit.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testStockTradingProfit.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStockTradingProfit.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testStockTradingProfit.getBuyPrice()).isEqualTo(UPDATED_BUY_PRICE);
        assertThat(testStockTradingProfit.getSellPrice()).isEqualTo(UPDATED_SELL_PRICE);
        assertThat(testStockTradingProfit.getBuyTransactionFee()).isEqualTo(UPDATED_BUY_TRANSACTION_FEE);
        assertThat(testStockTradingProfit.getSellTransactionFee()).isEqualTo(UPDATED_SELL_TRANSACTION_FEE);
        assertThat(testStockTradingProfit.getBuyConsideration()).isEqualTo(UPDATED_BUY_CONSIDERATION);
        assertThat(testStockTradingProfit.getSellConsideration()).isEqualTo(UPDATED_SELL_CONSIDERATION);
        assertThat(testStockTradingProfit.getProfit()).isEqualTo(UPDATED_PROFIT);
        assertThat(testStockTradingProfit.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testStockTradingProfit.getMarket()).isEqualTo(UPDATED_MARKET);
    }

    @Test
    @Transactional
    public void deleteStockTradingProfit() throws Exception {
        // Initialize the database
        stockTradingProfitRepository.saveAndFlush(stockTradingProfit);
		
		int databaseSizeBeforeDelete = stockTradingProfitRepository.findAll().size();

        // Get the stockTradingProfit
        restStockTradingProfitMockMvc.perform(delete("/api/stockTradingProfits/{id}", stockTradingProfit.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<StockTradingProfit> stockTradingProfits = stockTradingProfitRepository.findAll();
        assertThat(stockTradingProfits).hasSize(databaseSizeBeforeDelete - 1);
    }
}
