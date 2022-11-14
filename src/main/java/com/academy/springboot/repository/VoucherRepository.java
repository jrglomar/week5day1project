package com.academy.springboot.repository;

import com.academy.springboot.model.Voucher;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends PagingAndSortingRepository<Voucher, Integer>{

}
