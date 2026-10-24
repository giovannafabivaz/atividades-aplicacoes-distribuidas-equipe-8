package com.empresa.model;

import java.time.LocalDate;

public record Compra(Long id, Long clienteId, Long produtoId, Integer quantidade, LocalDate dataCompra) {
}
