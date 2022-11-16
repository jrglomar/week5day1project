package com.academy.springboot.repository;

import com.academy.springboot.enums.Types;
import com.academy.springboot.model.Voucher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class RepositoryTest {
    @Autowired
    VoucherRepository voucherRepository;

    @Test
    public void findAll() {
        //Arrange
        Voucher voucher1 = new Voucher(1L, 123, 35, "This is voucher number 1", Types.CASH);
        Voucher voucher2 = new Voucher(2L, 456, 35.5, "This is voucher number 2", Types.CHECK);
        Voucher voucher3 = new Voucher(3L, 789, 45, "This is voucher number 3", Types.CASH);
        List<Voucher> voucherList = List.of(voucher1, voucher2, voucher3);
        //Act
        voucherRepository.saveAll(voucherList);
        //Assert
        assertThat(voucherRepository.findAll()).containsAll(voucherList);

    }
    @Test
    public void findById() {
        //Arrange
        Voucher voucher1 = new Voucher(1L, 123, 35, "This is voucher number 1", Types.CASH);
        Voucher expectedVoucher=voucherRepository.save(voucher1);
        //Act
        Optional<Voucher> actualVoucher=voucherRepository.findById(expectedVoucher.getId());
        //Assert
        assertTrue(actualVoucher.isPresent());
        assertEquals(voucher1,expectedVoucher);

    }
    @Test
    public void save() {
        //Arrange
        Voucher voucher1 = new Voucher(1L, 123, 35, "This is voucher number 1", Types.CASH);
        //Act
        Voucher expectedVoucher=voucherRepository.save(voucher1);
        //Assert
        assertEquals(voucher1,expectedVoucher);
    }

    @Test
    public void delete() {
        Voucher voucher1 = new Voucher(1L, 123, 35, "This is voucher number 1", Types.CASH);

        Voucher expectedVoucher = voucherRepository.save(voucher1);
        boolean isExistBeforeDelete = voucherRepository.findById(1L).isPresent();

        //Act
        voucherRepository.delete(voucher1);

        //Assert
        assertTrue(isExistBeforeDelete);
        assertTrue(voucherRepository.findById(1L).isEmpty());


    }

}
