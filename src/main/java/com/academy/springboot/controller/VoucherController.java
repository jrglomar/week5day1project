package com.academy.springboot.controller;

import com.academy.springboot.exception.RecordNotFoundException;
import com.academy.springboot.model.Voucher;
import com.academy.springboot.service.VoucherService;
import org.apache.coyote.Response;
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
    public ResponseEntity<Voucher> findById(@PathVariable Long id) throws RecordNotFoundException {
        return new ResponseEntity<>(voucherService.findVoucherById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Voucher> save(@RequestBody Voucher voucher) {
        return new ResponseEntity<>(voucherService.saveVoucher(voucher), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voucher> update(@RequestBody Voucher voucher, @PathVariable Long id) throws RecordNotFoundException {
        return new ResponseEntity<>(voucherService.updateVoucher(voucher, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws RecordNotFoundException {
        voucherService.deleteVoucher(id);
        return new ResponseEntity<>("Voucher deleted successfully!", HttpStatus.OK);
    }

}
