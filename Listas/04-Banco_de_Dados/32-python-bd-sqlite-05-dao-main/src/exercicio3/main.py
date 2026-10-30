from .dao.agencia_dao import ClienteDAO, DestinoDAO, ReservaDAO
from .model.entidades import Cliente, Destino, Reserva

def main() -> None:
    # 1. Inicializar DAOs e criar tabelas
    cliente_dao = ClienteDAO()
    destino_dao = DestinoDAO()
    reserva_dao = ReservaDAO()

    cliente_dao.create_table()
    destino_dao.create_table()
    reserva_dao.create_table()

    # 2. Inserir Cliente
    cliente = Cliente(nome="Carlos Mendes")
    cliente = cliente_dao.create(cliente)
    print(f"Cliente registado: {cliente}")

    # 3. Inserir Destino
    destino = Destino(nome_destino="Lisboa", pais="Portugal")
    destino = destino_dao.create(destino)
    print(f"Destino registado: {destino}")

    # 4. Registar Reserva
    reserva = Reserva(id_cliente=cliente.id_cliente, id_destino=destino.id_destino, data_viagem="2027-05-15", valor=1200.00)
    reserva = reserva_dao.create(reserva)
    print(f"Reserva registada: {reserva}")

if __name__ == "__main__":
    main()