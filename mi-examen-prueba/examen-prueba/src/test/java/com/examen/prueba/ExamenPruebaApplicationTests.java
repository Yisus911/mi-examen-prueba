package com.examen.prueba;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.examen.prueba.data.DataProvider;
import com.examen.prueba.model.document.Telefono;
import com.examen.prueba.model.request.TelefonoRequest;
import com.examen.prueba.model.response.TelefonoResponse;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import com.examen.prueba.repository.TelefonoRepository;
import com.examen.prueba.service.TelefonoService;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestPropertySource(locations="classpath:bootstrap.properties")
class ExamenPruebaApplicationTests {

	@Mock
	private TelefonoRepository repository;
	@InjectMocks
	private TelefonoService service;

	@Test
	void testFindAll() throws Exception {
		//Given
		Pageable pagina = PageRequest.of(1, 2);
		//When
		when(repository.findAll()).thenReturn(DataProvider.getListaTelefonos());
		List<TelefonoResponse> telefonos = repository.findAll().stream().map(TelefonoResponse::mapeo).toList();

		//Then
		assertNotNull(telefonos);
		assertFalse(telefonos.isEmpty());
		assertTrue(telefonos.size() > 0);
	}

	@Test
	void testGetTelefonoPorId() throws Exception {
		//Given
		String id = "Abc123defghi456";

		//When
		when(repository.findById(id)).thenReturn(Optional.of(DataProvider.getTelefono()));
		TelefonoResponse telResp = service.getTelefonoPorId(id);

		//Then
		assertNotNull(telResp);
        assertEquals("123abc56789", telResp.id());
		verify(repository).findById(anyString());
	}

	@Test
	void testGetTelefonoByImei() throws Exception {
		//Given
		Long id = 1234567890L;

		//When
		when(repository.findByImei(anyLong())).thenReturn(Optional.of(DataProvider.getTelefono()));
		//Telefono telResp = repository.findByImei(id).orElseThrow(() -> new Exception("Teléfono no encontrado!"));
		TelefonoResponse telResp = service.getTelefonoByImei(id);

		//Then
		assertNotNull(telResp);
		assertEquals(1234567890L, telResp.imei());
	}

	@Test
	void testGuardar(){
		//Given
		TelefonoRequest tel = DataProvider.crearNuevoTelefono();
		//When
		Telefono telefono = this.repository.save(TelefonoRequest.mapeo(tel));
		//Then
		verify(repository).save(any());
	}

	@Test
	void testEliminar() throws Exception {
		//Given
		String id = "123456789";
		//When
		this.repository.deleteById(id);
		//Telefono telefono = this.repository.save(TelefonoRequest.mapeo(tel));
		//Then
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(repository).deleteById(anyString());
		verify(repository).deleteById(captor.capture());
		assertEquals("123456789", captor.getValue());
	}



}
