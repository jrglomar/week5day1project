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

    @GetMapping
    public ResponseEntity<Page<Voucher>> getVoucher(Pageable pageable){
        Page<Voucher> vouchers = voucherService.findAllVoucher(pageable);
        return new ResponseEntity<>(vouchers, HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public Voucher getVoucherById(@PathVariable("id")Long id) throws RecordNotFoundException{
        return voucherService.findVoucherById(id);
    }
    @PostMapping
    public Voucher saveVoucher(@RequestBody Voucher voucher)throws RecordNotFoundException{
        return voucherService.saveVoucher(voucher);
    }
    @PutMapping("/{voucherId}")
    public Voucher updatedVoucher(@RequestBody Voucher voucher,@PathVariable Long id)throws RecordNotFoundException{
      return voucherService.updatedVoucher(voucher,id);
    }
    @DeleteMapping
    public void deleteVoucher(@RequestParam Long Id) throws RecordNotFoundException{
        voucherService.deleteVoucher(Id);
    }
}
