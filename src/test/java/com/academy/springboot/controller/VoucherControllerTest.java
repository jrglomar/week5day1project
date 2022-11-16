package com.academy.springboot.controller;

import com.academy.springboot.enums.Types;
import com.academy.springboot.model.Voucher;
import com.academy.springboot.service.VoucherService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class VoucherControllerTest {
    Voucher aj, raven;
    @MockBean
    private VoucherService voucherService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        aj = new Voucher(1L,232,BigDecimal.valueOf(30000)
                ,"You have won 30000!!!",Types.CASH);
        raven = new Voucher(2L,546456,BigDecimal.valueOf(50000)
                ,"You have won 50000!!!",Types.CHECK);
    }

    @Test
    void saveVoucher() throws Exception {
        //Arrange
        when(voucherService.saveVoucher(any(Voucher.class)))
                .thenReturn(raven);

        //Act
        mockMvc.perform(post("/vouchers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(raven)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", CoreMatchers.is(raven.getNumber())))
                .andExpect(jsonPath("$.amount", CoreMatchers.is(raven.getAmount().intValue())))
                .andExpect(jsonPath("$.type", CoreMatchers.is(raven.getType().name())))
                .andExpect(jsonPath("$.description", CoreMatchers.is(raven.getDescription())));

    }

    @Test
    void getAllVoucher() throws Exception {
        //Arrange
        Pageable pageable = PageRequest.of(0, 20);
        when(voucherService.findAllVoucher(pageable))
                .thenReturn(new PageImpl<>(List.of(aj, raven)));

        //Act
        mockMvc.perform(get("/vouchers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", CoreMatchers.is(List.of(aj, raven).size())))
                .andExpect(jsonPath("$.content.[0].number", CoreMatchers.is(aj.getNumber())))
                .andExpect(jsonPath("$.content.[1].number", CoreMatchers.is(raven.getNumber())));
    }

    @Test
    void getVoucherById() throws Exception {
        //Arrange
        when(voucherService.findVoucherById(anyLong())).thenReturn(aj);

        //Act
        mockMvc.perform(get("/vouchers/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", CoreMatchers.is(aj.getNumber())));
    }

    @Test
    void updateVoucher() throws Exception {
        //Arrange
        raven.setNumber(3232);
        when(voucherService.updateVoucher(any(Voucher.class),
                anyLong())).thenReturn(raven);

        //Act
        mockMvc.perform(put("/vouchers/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(raven)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", CoreMatchers.is(raven.getNumber())));
    }

    @Test
    void deleteVoucher() throws Exception {
        //Arrange
        doNothing().when(voucherService).deleteVoucher(raven.getId());

        //Act
        mockMvc.perform(delete("/vouchers/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
