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
public class VoucherServiceImpl implements VoucherService{

    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Page<Voucher> findAllVoucher(Pageable pageable) {
        return voucherRepository.findAll(pageable);
    }

    @Override
    public Voucher findVoucherById(Integer id) throws RecordNotFoundException {
        return voucherRepository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    @Override
    public Voucher saveVoucher(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    @Override
    public Voucher updateVoucher(Voucher voucher, Integer id) throws RecordNotFoundException {
        Voucher voucherFound = voucherRepository.findById(id).orElseThrow(RecordNotFoundException::new);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(voucher, voucherFound);
        return voucherFound;
    }

    @Override
    public void deleteVoucher(Integer id) throws RecordNotFoundException {
        voucherRepository.delete(voucherRepository.findById(id).orElseThrow(RecordNotFoundException::new));
    }
}