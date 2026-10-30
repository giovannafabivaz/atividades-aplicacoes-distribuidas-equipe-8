from .dao.padaria_dao import ClienteDAO, ProdutoDAO, PedidoDAO, ItemPedidoDAO
from .model.entidades import Cliente, Produto, Pedido, ItemPedido

def main() -> None:
    # 1. Inicializar DAOs e criar tabelas
    cliente_dao = ClienteDAO()
    produto_dao = ProdutoDAO()
    pedido_dao = PedidoDAO()
    item_pedido_dao = ItemPedidoDAO()

    cliente_dao.create_table()
    produto_dao.create_table()
    pedido_dao.create_table()
    item_pedido_dao.create_table()

    # 2. Inserir Cliente
    cliente = Cliente(nome="Maria Joaquina")
    cliente = cliente_dao.create(cliente)
    print(f"Cliente registado: {cliente}")

    # 3. Inserir Produto
    produto = Produto(nome="Pão Alentejano", preco=2.50)
    produto = produto_dao.create(produto)
    print(f"Produto registado: {produto}")

    # 4. Registar Pedido para o Cliente
    pedido = Pedido(id_cliente=cliente.id_cliente, data_pedido="2026-10-30", total=5.00)
    pedido = pedido_dao.create(pedido)
    print(f"Pedido registado: {pedido}")

    # 5. Adicionar Item ao Pedido
    item = ItemPedido(id_pedido=pedido.id_pedido, id_produto=produto.id_produto, quantidade=2)
    item_pedido_dao.create(item)
    print(f"Item registado: {item}")

if __name__ == "__main__":
    main()