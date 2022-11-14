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

@RestController
@RequestMapping("/vouchers")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;
    @GetMapping("")
    public ResponseEntity<Page<Voucher>> getAllVoucher(Pageable pageable){
        Page<Voucher> vouchers = voucherService.findAllVoucher(pageable);
    return new ResponseEntity<>(vouchers, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public Voucher getVoucherById(@PathVariable("id") Long empId) throws RecordNotFoundException {
        return voucherService.findVoucherById(empId);
    }
    @PostMapping("")
    public Voucher saveVoucher(@RequestBody Voucher voucher) {
        System.out.println("save employee");
        return voucherService.saveVoucher(voucher);
    }
    @PutMapping("")
    public Voucher updateVoucher(@RequestBody Voucher voucher) throws RecordNotFoundException {
        return voucherService.updateVoucher(voucher);
    }
    @DeleteMapping("/{empId}")
    public void deleteVoucher(@PathVariable Long empId) throws RecordNotFoundException {
        voucherService.deleteVoucher(empId);
    }
}
