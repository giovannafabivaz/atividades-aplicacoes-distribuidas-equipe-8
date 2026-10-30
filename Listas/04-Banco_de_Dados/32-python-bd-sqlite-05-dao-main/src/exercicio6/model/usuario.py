from dataclasses import dataclass
from typing import Optional

@dataclass
class Usuario:
    id: Optional[int] = None
    nome: str = ""
    nascimento: Optional[str] = None