package com.academy.springboot.service;

import com.academy.springboot.exception.RecordNotFoundException;
import com.academy.springboot.model.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VoucherService {
    Page<Voucher> findAllVoucher(Pageable pageable);
    Voucher findVoucherById(Long empId) throws RecordNotFoundException;
    Voucher saveVoucher(Voucher voucher);
    Voucher updateVoucher(Voucher voucher) throws RecordNotFoundException;
    void deleteVoucher(Long empId) throws RecordNotFoundException;
}
