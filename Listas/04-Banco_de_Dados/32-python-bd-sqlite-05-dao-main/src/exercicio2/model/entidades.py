from dataclasses import dataclass
from typing import Optional

@dataclass
class Cliente:
    id_cliente: Optional[int] = None
    nome: str = ""

@dataclass
class Produto:
    id_produto: Optional[int] = None
    nome: str = ""
    preco: float = 0.0

@dataclass
class Pedido:
    id_pedido: Optional[int] = None
    id_cliente: int = 0
    data_pedido: str = ""
    total: float = 0.0

@dataclass
class ItemPedido:
    id_pedido: int = 0
    id_produto: int = 0
    quantidade: int = 0
    