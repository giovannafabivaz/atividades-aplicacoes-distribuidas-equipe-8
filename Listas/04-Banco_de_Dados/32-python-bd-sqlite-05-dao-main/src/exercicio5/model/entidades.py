from dataclasses import dataclass
from typing import Optional

@dataclass
class Medicamento:
    id_medicamento: Optional[int] = None
    nome: str = ""
    preco: float = 0.0

@dataclass
class Fornecedor:
    id_fornecedor: Optional[int] = None
    nome: str = ""
    cidade: str = ""

@dataclass
class Compra:
    id_compra: Optional[int] = None
    id_medicamento: int = 0
    id_fornecedor: int = 0
    quantidade: int = 0
    data_compra: str = ""