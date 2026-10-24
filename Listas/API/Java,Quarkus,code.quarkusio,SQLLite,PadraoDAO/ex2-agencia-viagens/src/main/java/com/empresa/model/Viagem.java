package com.empresa.model;

import java.time.LocalDate;

public record Viagem(Long id, Long clienteId, Long destinoId, LocalDate dataVisita) {
}
