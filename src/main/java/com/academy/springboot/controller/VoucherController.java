package com.academy.springboot.controller;

import com.academy.springboot.exception.RecordNotFoundException;
import com.academy.springboot.model.Voucher;
import com.academy.springboot.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vouchers")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @GetMapping
    public ResponseEntity<Page<Voucher>> findAll(Pageable pageable) {
        return new ResponseEntity<>(voucherService.findAllVoucher(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Voucher findById(@PathVariable Long id) throws RecordNotFoundException {
        return voucherService.findVoucherById(id);
    }

    @PostMapping
    public Voucher save(@RequestBody Voucher voucher) {
        return voucherService.saveVoucher(voucher);
    }

    @PutMapping("/{id}")
    public Voucher update(@RequestBody Voucher voucher, @PathVariable Long id) throws RecordNotFoundException {
        return voucherService.updateVoucher(voucher, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws RecordNotFoundException {
        voucherService.deleteVoucher(id);
        return new ResponseEntity<>("Voucher deleted successfully!", HttpStatus.OK);
    }

}
