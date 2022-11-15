package com.academy.springboot.service;

import com.academy.springboot.exception.RecordNotFoundException;
import com.academy.springboot.model.Voucher;
import com.academy.springboot.repository.VoucherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    VoucherRepository voucherRepo;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public Page<Voucher> findAllVoucher(Pageable pageable) {
        Page<Voucher> pageVoucher = voucherRepo.findAll(pageable);
        return pageVoucher;
    }

    @Override
    public Voucher findVoucherById(Long id) throws RecordNotFoundException {
        return voucherRepo.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    @Override
    public Voucher saveVoucher(Voucher voucher)  {
        return voucherRepo.save(voucher);
    }

    @Override
    public void deleteVoucher(Long id) {
        Voucher voucher = voucherRepo.findById(id).get();
        voucherRepo.delete(voucher);
    }

    @Override
    public Voucher updatedVoucher(Voucher voucher, Long id) throws RecordNotFoundException {
        Voucher voucher1 = voucherRepo.findById(id).get();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(voucher, voucher1);
        return voucher1;
    }
}
