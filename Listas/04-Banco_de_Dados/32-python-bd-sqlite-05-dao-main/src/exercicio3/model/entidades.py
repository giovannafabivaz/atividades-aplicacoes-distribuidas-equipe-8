from dataclasses import dataclass
from typing import Optional

@dataclass
class Cliente:
    id_cliente: Optional[int] = None
    nome: str = ""

@dataclass
class Destino:
    id_destino: Optional[int] = None
    nome_destino: str = ""
    pais: str = ""

@dataclass
class Reserva:
    id_reserva: Optional[int] = None
    id_cliente: int = 0
    id_destino: int = 0
    data_viagem: str = ""
    valor: float = 0.0