package com.ClassExchange.uc.manter_campus;


import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CampusServiceTest {

    @Mock
    private CampusRepository campusRepository;

    @InjectMocks
    private CampusService campusService;

    @Test
    void deveCriarCampus() {
        // Dado
        CampusRequest request = new CampusRequest("Campus A", "CA", "Endereço A");

        // Quando
        CampusResponse response = campusService.createCampus(request);

        // Então
        assertNotNull(response); // Verifica se o resultado não é nulo
        assertEquals("Campus A", response.getName()); // Verifica se o nome está correto
    }

    @Test
    void deveRetornarTodosOsCampuses() {
        // Dado
        Campus campus1 = new Campus("Campus A", "CA", "Endereço A");
        Campus campus2 = new Campus("Campus B", "CB", "Endereço B");
        when(campusRepository.findAll()).thenReturn(List.of(campus1, campus2));

        // Quando
        List<CampusResponse> response = campusService.getAllCampuses();

        // Então
        assertNotNull(response); // Verifica se a lista não é nula
        assertEquals(2, response.size()); // Verifica se retornou dois campus
        assertEquals("Campus A", response.get(0).getName()); // Verifica o nome do primeiro campus
        assertEquals("Campus B", response.get(1).getName()); // Verifica o nome do segundo campus
    }

    @Test
    void deveRetornarCampusPorId() {
        // Dado
        Long campusId = 1L;
        Campus campus = new Campus("Campus A", "CA", "Endereço A");
        when(campusRepository.findById(campusId)).thenReturn(Optional.of(campus));

        // Quando
        CampusResponse response = campusService.getCampusById(campusId).orElseThrow();

        // Então
        assertNotNull(response); // Verifica se o resultado não é nulo
        assertEquals("Campus A", response.getName()); // Verifica o nome do campus
    }

    @Test
    void deveRetornarOptionalVazioQuandoCampusNaoExistir() {
        // Dado
        Long campusId = 1L;
        when(campusRepository.findById(campusId)).thenReturn(Optional.empty());

        // Quando
        Optional<CampusResponse> response = campusService.getCampusById(campusId);

        // Então
        assertTrue(response.isEmpty()); // Verifica se o resultado é vazio
    }

    @Test
    void deveAtualizarCampus() {
        // Dado
        Long campusId = 1L;
        Campus campusExistente = new Campus("Campus A", "CA", "Endereço A");
        CampusRequest updateRequest = new CampusRequest("Campus Atualizado", "CA", "Endereço Atualizado");
        when(campusRepository.findById(campusId)).thenReturn(Optional.of(campusExistente));
        when(campusRepository.save(any(Campus.class))).thenReturn(campusExistente); // Simula a atualização

        // Quando
        Optional<CampusResponse> response = campusService.updateCampus(campusId, updateRequest);

        // Então
        assertTrue(response.isPresent()); // Verifica se o retorno não é vazio
        assertEquals("Campus Atualizado", response.get().getName()); // Verifica o nome atualizado
        assertEquals("Endereço Atualizado", response.get().getEndereco()); // Verifica o endereço atualizado
    }

    @Test
    void deveRetornarOptionalVazioQuandoCampusNaoExistirParaAtualizacao() {
        // Dado
        Long campusId = 1L;
        CampusRequest updateRequest = new CampusRequest("Campus Atualizado", "CA", "Endereço Atualizado");
        when(campusRepository.findById(campusId)).thenReturn(Optional.empty());

        // Quando
        Optional<CampusResponse> response = campusService.updateCampus(campusId, updateRequest);

        // Então
        assertTrue(response.isEmpty()); // Verifica se o retorno é vazio, porque o campus não existe
    }

    @Test
    void deveExcluirCampus() {
        // Dado
        Long campusId = 1L;
        Campus campusExistente = new Campus("Campus A", "CA", "Endereço A");
        when(campusRepository.existsById(campusId)).thenReturn(true); // Simula que o campus existe

        // Quando
        boolean result = campusService.deleteCampus(campusId);

        // Então
        assertTrue(result); // Verifica se o retorno é verdadeiro, indicando que o campus foi excluído
        verify(campusRepository).deleteById(campusId); // Verifica se o método de exclusão foi chamado
    }

    @Test
    void naoDeveExcluirCampusQuandoNaoExistir() {
        // Dado
        Long campusId = 1L;
        when(campusRepository.existsById(campusId)).thenReturn(false); // Simula que o campus não existe

        // Quando
        boolean result = campusService.deleteCampus(campusId);

        // Então
        assertFalse(result); // Verifica se o retorno é falso, indicando que a exclusão não foi realizada
        verify(campusRepository, never()).deleteById(campusId); // Verifica se o método de exclusão nunca foi chamado
    }


}