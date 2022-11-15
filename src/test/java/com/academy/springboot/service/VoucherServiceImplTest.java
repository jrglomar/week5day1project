package com.academy.springboot.service;

import com.academy.springboot.configuration.ModelMapperConfig;
import com.academy.springboot.exception.RecordNotFoundException;
import com.academy.springboot.model.Voucher;
import com.academy.springboot.repository.VoucherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
class VoucherServiceImplTest {

    @Mock
    VoucherRepository voucherRepository;
    @InjectMocks
    VoucherServiceImpl voucherServiceImpl;

    @Mock
    private ModelMapperConfig modelMapperConfig;
    


    @Test
    public void findAllVoucher(){
        Voucher voucher1 = new Voucher(1L, 25.5, "Cash", "This is voucher number 1");
        Voucher voucher2 = new Voucher(2L, 35, "Check", "This is voucher number 2");
        Voucher voucher3 = new Voucher(3L, 45.5, "Cash", "This is voucher number 3");
        Pageable pageable = PageRequest.of(0,3);
        Page<Voucher> expectedListOfVoucher = new PageImpl<>(List.of(voucher1, voucher2, voucher3));

        Mockito.when(voucherRepository.findAll(Mockito.any(Pageable.class))).thenReturn(expectedListOfVoucher);
        Page<Voucher> actualListOfVoucher = voucherServiceImpl.findAllVoucher(pageable);
        
        Mockito.verify(voucherRepository).findAll(pageable);
        assertEquals(expectedListOfVoucher, actualListOfVoucher);
    }
    
    

    @Test
    public void findVoucherById() throws RecordNotFoundException {
        Voucher expectedVoucher = new Voucher(1L, 25.5, "Cash", "This is voucher number 1");
        
        Mockito.when(voucherRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(expectedVoucher));
        Voucher actualVoucher = voucherServiceImpl.findVoucherById(1L);
        
        Mockito.verify(voucherRepository).findById(1L);
        assertEquals(expectedVoucher, actualVoucher);
    }

    @Test
    public void saveVoucher(){
        Voucher expectedVoucher = new Voucher(1L, 25.5, "Cash", "This is voucher number 1");

        Mockito.when(voucherRepository.save(Mockito.any(Voucher.class))).thenReturn(expectedVoucher);
        Voucher actualVoucher = voucherServiceImpl.saveVoucher(expectedVoucher);
        
        Mockito.verify(voucherRepository).save(expectedVoucher);
        assertEquals(expectedVoucher, actualVoucher);
    }

    @Test
    public void updateVoucher() throws RecordNotFoundException {
        Voucher expectedVoucher = new Voucher(1L, 45.5, "Check", "This is voucher number 1");
        Voucher updatedVoucher = new Voucher(1L, 45.5, "Cash", "This is updater version of voucher number 1");

        
        Mockito.when(voucherRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(expectedVoucher));
        Mockito.when(modelMapperConfig
                .modelMapper()
                .map(Mockito.any(), Mockito.any()))
                .thenReturn(updatedVoucher);
        Voucher actualVoucher = voucherServiceImpl.updateVoucher(expectedVoucher, 1L);
        
        Mockito.verify(voucherRepository).save(expectedVoucher);
        assertEquals(expectedVoucher, actualVoucher);
    }

    @Test
    public void deleteVoucher() throws RecordNotFoundException {
        Voucher expectedVoucher = new Voucher(1L, 45.5, "Check", "This is voucher number 1");

        Mockito.when(voucherRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(expectedVoucher));
        voucherServiceImpl.deleteVoucher(1L);

        Mockito.verify(voucherRepository).findById(1L);
        Mockito.verify(voucherRepository).delete(expectedVoucher);
    }
}