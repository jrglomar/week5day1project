package com.academy.springboot.service;

import com.academy.springboot.exception.RecordNotFoundException;
import com.academy.springboot.model.Voucher;
import com.academy.springboot.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public Page<Voucher> findAllVoucher(Pageable pageable) {
        return voucherRepository.findAll(pageable);
    }

    @Override
    public Voucher findVoucherById(Long empId) throws RecordNotFoundException {
        return voucherRepository
                .findById(empId)
                .orElseThrow(() -> new RecordNotFoundException("Voucher not found!!!"));
    }

    @Override
    public Voucher saveVoucher(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    @Override
    public Voucher updateVoucher(Voucher emp) throws RecordNotFoundException {
        Voucher voucher = voucherRepository
                .findById(emp.getNumber())
                .orElseThrow(() -> new RecordNotFoundException("Voucher not found!!!"));
        voucher.setAmount(emp.getAmount());
        voucher.setType(emp.getType());
        return voucherRepository.save(voucher);
    }

    @Override
    public void deleteVoucher(Long empId) throws RecordNotFoundException {
        Voucher voucher = voucherRepository
                .findById(empId)
                .orElseThrow(() -> new RecordNotFoundException("Voucher not found!!!"));
        voucherRepository.delete(voucher);


    }
}
