package com.callcenter.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import com.callcenter.app.bean.Empleado;
import com.callcenter.app.bean.Llamada;
import com.callcenter.app.service.AtencionLlamadaService;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class DemoApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	@Autowired
	private AtencionLlamadaService atencionLlamadaService;

	@Bean("threadPoolTaskExecutor")
	public TaskExecutor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(500);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setThreadNamePrefix("AtencionLlamada-");
		executor.initialize();
		return executor;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args).close();
	}

	@Override
	public void run(String... args) throws Exception {

		long start = System.currentTimeMillis();

		// Crear lista de 10 llamadas y empleados
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

		// LLamada adicional
		Llamada llamada11 = new Llamada();
		llamada11.setNombre("llamada11");
		int intAletorio = (int) (aleatorio.nextDouble() * 6 + 5);
		llamada11.setDuracionLlamada(intAletorio);

		// Se asigna la llamada al empleado que este disponible
		boolean llamadaAtendida = false;
		CompletableFuture<Empleado> empleado11 = null;
		while (!llamadaAtendida) {
			if (empleado1.isDone()) {
				empleado1.get().setDisponible(true);
				empleado11 = atencionLlamadaService.dispatchCall(llamada11, empleado1.get());
				llamadaAtendida = true;
			} else if (empleado2.isDone()) {
				empleado2.get().setDisponible(true);
				empleado11 = atencionLlamadaService.dispatchCall(llamada11, empleado2.get());
				llamadaAtendida = true;
			} else if (empleado3.isDone()) {
				empleado3.get().setDisponible(true);
				empleado11 = atencionLlamadaService.dispatchCall(llamada11, empleado3.get());
				llamadaAtendida = true;
			} else if (empleado4.isDone()) {
				empleado4.get().setDisponible(true);
				empleado11 = atencionLlamadaService.dispatchCall(llamada11, empleado4.get());
				llamadaAtendida = true;
			} else if (empleado5.isDone()) {
				empleado5.get().setDisponible(true);
				empleado11 = atencionLlamadaService.dispatchCall(llamada11, empleado5.get());
				llamadaAtendida = true;
			} else {
				System.out.println("La llamada " + llamada.getNombre() + " se encuenta en espera");
			}

		}

		logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
		logger.info("--> " + empleado1.get());
		logger.info("--> " + empleado2.get());
		logger.info("--> " + empleado3.get());
		logger.info("--> " + empleado4.get());
		logger.info("--> " + empleado5.get());
		logger.info("--> " + empleado6.get());
		logger.info("--> " + empleado7.get());
		logger.info("--> " + empleado8.get());
		logger.info("--> " + empleado9.get());
		logger.info("--> " + empleado10.get());
		logger.info("--> " + empleado11.get());

	}

}
