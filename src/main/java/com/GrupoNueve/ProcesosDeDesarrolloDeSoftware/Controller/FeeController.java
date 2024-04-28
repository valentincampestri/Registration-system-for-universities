package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Controller;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IFeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fee")
public class FeeController {
    IFeeService feeService;

    public FeeController(IFeeService feeService) {
        this.feeService = feeService;
    }

    @GetMapping("/{studentCode}")
    public ResponseEntity<?> getFeesByStudent(@PathVariable String studentCode) {
        return ResponseEntity.ok(feeService.getFeesByStudent(studentCode));
    }

    @PostMapping("/pay/{studentCode}")
    public ResponseEntity<?> pay (@PathVariable String studentCode, @RequestParam String paymentMethod) {
        return ResponseEntity.ok(feeService.pay(studentCode, paymentMethod));
    }
    
}
