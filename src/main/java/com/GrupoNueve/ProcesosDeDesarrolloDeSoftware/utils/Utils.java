package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Inscription;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IFeeService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Utils {
    IFeeService feeService;

    public Utils(IFeeService feeService) {
        this.feeService = feeService;
    }

    public void addFee(Inscription inscription) {
        feeService.addFee(inscription);
    }
}