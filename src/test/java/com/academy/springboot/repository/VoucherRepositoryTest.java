package com.academy.springboot.repository;

import com.academy.springboot.model.Voucher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class VoucherRepositoryTest {

    @Autowired
    private VoucherRepository voucherRepository;

    @Test
    @DisplayName("Given voucher from voucherServiceImpl with the setup above " +
            "WHEN findAllVoucher is executed  " +
            "THEN result should return all")

    public void testFindAll() {
        //Arrange
        Voucher JohnMicheal = new Voucher(1L,133,2445d,"check","Your voucher receive is 12909");
        Voucher JohnRaven = new Voucher(2L,19,209d,"cash","Your voucher is used to purchase items");
        Iterable<Voucher> voucherList = voucherRepository.saveAll(List.of(JohnMicheal, JohnRaven));
        //Act
        Iterable<Voucher> result =voucherRepository.findAll();
        //Assert
        assertThat(result).containsAll(voucherList);
    }

    @Test
    @DisplayName("Given voucher from voucherServiceImpl with the setup above " +
            "WHEN findAllVoucher is executed  " +
            "THEN result should return all")

    public void testFindById() {
        //Arrange
        Voucher JohnMicheal = new Voucher(1L,133,2445d,"check","Your voucher receive is 12909");
        Voucher JohnRaven = new Voucher(2L,19,209d,"cash","Your voucher is used to purchase items");


        //Act


        //Assert
        assertThat(voucherRepository.findAll());
    }



}
