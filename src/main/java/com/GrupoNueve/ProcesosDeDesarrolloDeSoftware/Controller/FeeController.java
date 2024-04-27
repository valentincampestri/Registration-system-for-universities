package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Controller;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IFeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fee")
public class FeeController {
    IFeeService feeService;

    public FeeController(IFeeService feeService) {
        this.feeService = feeService;
    }
}
