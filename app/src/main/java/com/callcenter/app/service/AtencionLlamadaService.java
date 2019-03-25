package com.callcenter.app.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.callcenter.app.Constants;
import com.callcenter.app.bean.Empleado;
import com.callcenter.app.bean.Llamada;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AtencionLlamadaService {

    private static final Logger logger = LoggerFactory.getLogger(AtencionLlamadaService.class);

    /**
     * Metodo que se encarga de asignar las llamadas.
     * 
     * @param Llamada llamada entrante
     */
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Empleado> dispatchCall(Llamada llamada, Empleado empleado)
            throws ExecutionException, InterruptedException {

        logger.info("dispatchCall: " + llamada.toString());

        /* Asignacion de la llamada segun la jerarquia */
        if (empleado.getCargo().equals(Constants.CARGO_OPERADOR) && empleado.isDisponible()) {
            tomarLlamada(empleado, llamada);
        } else if (empleado.getCargo().equals(Constants.CARGO_SUPERVISOR) && empleado.isDisponible()) {
            tomarLlamada(empleado, llamada);
        } else if (empleado.getCargo().equals(Constants.CARGO_DIRECTOR) && empleado.isDisponible()) {
            tomarLlamada(empleado, llamada);
        }

        return CompletableFuture.completedFuture(empleado);

    }

    private void tomarLlamada(Empleado empleado, Llamada llamada) {
        empleado.setDisponible(false);
        // Informacion de quien recibe la llamada y tiempo
        System.out.println(
                "El empleado " + empleado.toString() + " ha tomado la llamada " + llamada.toString() + " segundos");
        // simulacion del tiempo
        try {
            Thread.sleep(llamada.getDuracionLlamada() * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

    }

}