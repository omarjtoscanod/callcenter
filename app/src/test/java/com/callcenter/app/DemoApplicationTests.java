package com.callcenter.app;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import com.callcenter.app.bean.Empleado;
import com.callcenter.app.bean.Llamada;
import com.callcenter.app.service.AtencionLlamadaService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	AtencionLlamadaService atencionLlamadaService;

	@Test
	public void AtencionLlamadaServiceTest() throws Exception {

		List<Llamada> llamadasList = new ArrayList<>();
		Random aleatorio = new Random(System.currentTimeMillis());
		Llamada llamada = null;

		List<Empleado> empleadoList = new ArrayList<>();
		Empleado empleado = null;
		for (int i = 0; i < 10; i++) {
			llamada = new Llamada();
			llamada.setNombre("llamada" + (i + 1));
			int intAletorio = (int) (aleatorio.nextDouble() * 6 + 5);
			llamada.setDuracionLlamada(intAletorio);
			llamadasList.add(llamada);

			empleado = new Empleado();
			empleado.setId(i + 1);
			if (i < 5) {
				empleado.setCargo(Constants.CARGO_OPERADOR);
			} else if (i > 4 && i < 9) {
				empleado.setCargo(Constants.CARGO_SUPERVISOR);
			} else {
				empleado.setCargo(Constants.CARGO_DIRECTOR);
			}
			empleado.setDisponible(true);
			empleadoList.add(empleado);

		}

		// Se cargan las llamadas
		CompletableFuture<Empleado> empleado1 = atencionLlamadaService.dispatchCall(llamadasList.get(0),
				empleadoList.get(0));
		CompletableFuture<Empleado> empleado2 = atencionLlamadaService.dispatchCall(llamadasList.get(1),
				empleadoList.get(1));
		CompletableFuture<Empleado> empleado3 = atencionLlamadaService.dispatchCall(llamadasList.get(2),
				empleadoList.get(2));
		CompletableFuture<Empleado> empleado4 = atencionLlamadaService.dispatchCall(llamadasList.get(3),
				empleadoList.get(3));
		CompletableFuture<Empleado> empleado5 = atencionLlamadaService.dispatchCall(llamadasList.get(4),
				empleadoList.get(4));
		CompletableFuture<Empleado> empleado6 = atencionLlamadaService.dispatchCall(llamadasList.get(5),
				empleadoList.get(5));
		CompletableFuture<Empleado> empleado7 = atencionLlamadaService.dispatchCall(llamadasList.get(6),
				empleadoList.get(6));
		CompletableFuture<Empleado> empleado8 = atencionLlamadaService.dispatchCall(llamadasList.get(7),
				empleadoList.get(7));
		CompletableFuture<Empleado> empleado9 = atencionLlamadaService.dispatchCall(llamadasList.get(8),
				empleadoList.get(8));
		CompletableFuture<Empleado> empleado10 = atencionLlamadaService.dispatchCall(llamadasList.get(9),
				empleadoList.get(9));

		assertEquals(empleado1.get(), empleadoList.get(0));
		assertEquals(empleado2.get(), empleadoList.get(1));
		assertEquals(empleado3.get(), empleadoList.get(2));
		assertEquals(empleado4.get(), empleadoList.get(3));
		assertEquals(empleado5.get(), empleadoList.get(4));
		assertEquals(empleado6.get(), empleadoList.get(5));
		assertEquals(empleado7.get(), empleadoList.get(6));
		assertEquals(empleado8.get(), empleadoList.get(7));
		assertEquals(empleado9.get(), empleadoList.get(8));
		assertEquals(empleado10.get(), empleadoList.get(9));

	}

}
