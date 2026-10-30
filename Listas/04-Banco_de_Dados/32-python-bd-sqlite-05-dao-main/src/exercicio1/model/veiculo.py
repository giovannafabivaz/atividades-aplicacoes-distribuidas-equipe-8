from dataclasses import dataclass
from typing import Optional

@dataclass
class Veiculo:
    id: Optional[int] = None
    marca: str = ""
    modelo: str = ""
    chassi: str = ""
    placa: str = ""
    cor: str = ""