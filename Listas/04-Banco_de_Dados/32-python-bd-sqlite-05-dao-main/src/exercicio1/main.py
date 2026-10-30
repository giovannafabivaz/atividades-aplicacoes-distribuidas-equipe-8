from .model.veiculo import Veiculo
from .dao.veiculo_dao import VeiculoDAO

def main() -> None:
    dao = VeiculoDAO()
    dao.create_table()

    while True:
        print("\n1. Cadastrar Veículo\n2. Consultar Veículos\n3. Atualizar Veículo\n4. Excluir Veículo\n5. Sair")
        opcao = input("Escolha uma opção: ")

        if opcao == '1':
            while True:
                v = Veiculo(
                    marca=input("Marca: "),
                    modelo=input("Modelo: "),
                    chassi=input("Chassi: "),
                    placa=input("Placa: "),
                    cor=input("Cor: ")
                )
                dao.create(v)
                if input("Cadastrar outro? (s/n): ").lower() != 's':
                    break
        elif opcao == '2':
            for v in dao.read_all():
                print(v)
        elif opcao == '3':
            id_v = int(input("ID do veículo para atualizar: "))
            v = Veiculo(id=id_v, marca=input("Nova Marca: "), modelo=input("Novo Modelo: "), chassi=input("Novo Chassi: "), placa=input("Nova Placa: "), cor=input("Nova Cor: "))
            dao.update(v)
        elif opcao == '4':
            id_v = int(input("ID do veículo para excluir: "))
            dao.delete(id_v)
        elif opcao == '5':
            break

if __name__ == "__main__":
    main()