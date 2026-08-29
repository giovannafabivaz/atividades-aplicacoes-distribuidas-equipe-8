import { useState } from 'react'
import {
  View,
  Text,
  TouchableOpacity,
  StyleSheet,
  ScrollView,
} from 'react-native'
import CampoTexto from '../components/CampoTexto'
import ListaDeFavoritos from '../components/ListaDeFavoritos'
import { Favorito } from '../components/ItemFavorito'

export default function TelaDeFavoritos() {
  const [item, setItem] = useState<string>('')
  const [categoria, setCategoria] = useState<string>('')
  const [favoritos, setFavoritos] = useState<Favorito[]>([])

  const adicionarFavorito = () => {
    if (item.trim() && categoria.trim()) {
      setFavoritos([
        ...favoritos,
        {
          item: item.trim(),
          categoria: categoria.trim(),
        },
      ])

      setItem('')
      setCategoria('')
    }
  }

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.titulo}>Lista de Favoritos</Text>

      <Text style={styles.contador}>
        Itens cadastrados: {favoritos.length}
      </Text>

      <CampoTexto
        value={item}
        onChange={setItem}
        placeholder="Digite o item"
      />

      <CampoTexto
        value={categoria}
        onChange={setCategoria}
        placeholder="Digite a categoria"
      />

      <TouchableOpacity
        style={styles.botao}
        onPress={adicionarFavorito}
      >
        <Text style={styles.textoBotao}>Adicionar favorito</Text>
      </TouchableOpacity>

      <ListaDeFavoritos favoritos={favoritos} />
    </ScrollView>
  )
}

const styles = StyleSheet.create({
  container: {
    flexGrow: 1,
    padding: 20,
    paddingTop: 60,
    backgroundColor: '#000000',
  },
  titulo: {
    color: '#ffffff',
    fontSize: 28,
    fontWeight: 'bold',
    marginBottom: 10,
  },
  contador: {
    color: '#aaaaaa',
    fontSize: 16,
    marginBottom: 24,
  },
  botao: {
    backgroundColor: '#2563eb',
    padding: 15,
    borderRadius: 8,
    alignItems: 'center',
    marginBottom: 10,
  },
  textoBotao: {
    color: '#ffffff',
    fontWeight: 'bold',
    fontSize: 16,
  },
})
