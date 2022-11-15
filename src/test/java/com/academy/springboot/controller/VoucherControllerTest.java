package com.academy.springboot.controller;

import com.academy.springboot.repository.VoucherRepository;
import com.academy.springboot.service.VoucherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class VoucherControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VoucherService voucherService;
    @MockBean
    private VoucherRepository voucherRepository;
//
//    @Test
//    public void
}
