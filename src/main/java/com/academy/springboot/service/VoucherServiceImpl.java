package com.academy.springboot.service;

import com.academy.springboot.exception.RecordNotFoundException;
import com.academy.springboot.model.Voucher;
import com.academy.springboot.repository.VoucherRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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
    public Voucher findVoucherById(Long id) throws RecordNotFoundException {
        return voucherRepository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    @Override
    public Voucher saveVoucher(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    @Override
    public Voucher updateVoucher(Voucher voucher, Long id) throws RecordNotFoundException {
        Voucher voucherFound = voucherRepository.findById(id).orElseThrow(RecordNotFoundException::new);
        modelMapper.getConfiguration().setSkipNullEnabled(false);
        modelMapper.map(voucher, voucherFound);
        voucherFound.setId(id);
        return voucherFound;
    }
    @Override
    public void deleteVoucher(Long id) throws RecordNotFoundException {
        voucherRepository.delete(voucherRepository.findById(id).orElseThrow(RecordNotFoundException::new));
    }
}

//    public void delete(Long id) {
//        return voucherRepository.findById(id).map(Voucher-> {
//            voucherRepository.delete(Voucher);}}