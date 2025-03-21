package com.examen.prueba;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import com.examen.prueba.model.document.Telefono;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import com.examen.prueba.repository.TelefonoRepository;
import com.examen.prueba.service.TelefonoService;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ExamenPruebaApplicationTests {

	@Mock
	private TelefonoRepository repository;
	@InjectMocks
	private TelefonoService service;

	@Test
	void getTelefonoByImei() throws Exception {
		//given
		Telefono tel1 = new Telefono("123abc56789",
				"Samsung S23 Ultra",
				"Samsung",
				"Galaxy S23 Ultra",
				"S23 Ultra",
				new Date(),
				1234567890L,
				4422648297L,
				"jesuspc_905@hotmail.com",
				true);
		Telefono telefono = Telefono.builder()
				.id("123abc56789")
				.nombre("Samsung S23 Ultra")
				.marca("Samsung")
				.modelo("Galaxy S23 Ultra")
				.nombreCorto("S23 Ultra")
				.fechaCreacion(new Date())
				.imei(1234567890L)
				.numeroCelular(4422648297L)
				.emailSoporte("jesuspc_905@hotmail.com")
				.isIOS(true)
				.build();
		//When
		given(repository.findByImei(1111L))
				.willReturn(Optional.of(telefono));
		var  tel = service.getTelefonoByImei(1111L);
		//Then

		assertThat(tel).isNotNull();
	}

	@Test
	void getTelefonoPorId() throws Exception {
		//given
		Telefono tel1 = new Telefono("123abc56789",
				"Samsung S23 Ultra",
				"Samsung",
				"Galaxy S23 Ultra",
				"S23 Ultra",
				new Date(),
				1234567890L,
				4422648297L,
				"jesuspc_905@hotmail.com",
				true);
		Telefono telefono = Telefono.builder()
				.id("123abc56789")
				.nombre("Samsung S23 Ultra")
				.marca("Samsung")
				.modelo("Galaxy S23 Ultra")
				.nombreCorto("S23 Ultra")
				.fechaCreacion(new Date())
				.imei(1234567890L)
				.numeroCelular(4422648297L)
				.emailSoporte("jesuspc_905@hotmail.com")
				.isIOS(true)
				.build();
		//When
		given(repository.findById("123abc"))
				.willReturn(Optional.of(telefono));
		var  tel = service.getTelefonoPorId("123abc");
		//Then

		assertThat(tel).isNotNull();
	}

	@Test
	void getTodos() throws Exception {
		//given
		Pageable pagina = PageRequest.of(1, 3);
		Telefono tel1 = new Telefono("123abc56789",
				"Samsung S23 Ultra",
				"Samsung",
				"Galaxy S23 Ultra",
				"S23 Ultra",
				new Date(),
				1234567890L,
				4422648297L,
				"jesuspc_905@hotmail.com",
				true);
		Telefono telefono = Telefono.builder()
				.id("123abc56789")
				.nombre("Samsung S23 Ultra")
				.marca("Samsung")
				.modelo("Galaxy S23 Ultra")
				.nombreCorto("S23 Ultra")
				.fechaCreacion(new Date())
				.imei(1234567890L)
				.numeroCelular(4422648297L)
				.emailSoporte("jesuspc_905@hotmail.com")
				.isIOS(true)
				.build();
		//When
		given(repository.findAll())
				.willReturn(List.of(telefono, tel1));
		var telList = service.getAll(pagina);
		//Then

		assertThat(telList).isNotNull();
		assertThat(telList.getTotalPages()).isGreaterThan(0);
	}

	@Test
	void contextLoads() {
	}

}
