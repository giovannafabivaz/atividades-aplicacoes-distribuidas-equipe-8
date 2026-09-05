import { View, Text, StyleSheet } from 'react-native'

export interface Favorito {
  item: string
  categoria: string
}

interface ItemFavoritoProps {
  favorito: Favorito
}

export default function ItemFavorito({ favorito }: ItemFavoritoProps) {
  return (
    <View style={styles.item}>
      <Text style={styles.nome}>{favorito.item}</Text>
      <Text style={styles.categoria}>
        Categoria: {favorito.categoria}
      </Text>
    </View>
  )
}

const styles = StyleSheet.create({
  item: {
    width: '100%',
    padding: 16,
    marginTop: 12,
    borderRadius: 10,
    backgroundColor: '#111111',
    borderWidth: 1,
    borderColor: '#333333',
  },
  nome: {
    color: '#ffffff',
    fontSize: 18,
    fontWeight: 'bold',
  },
  categoria: {
    color: '#aaaaaa',
    marginTop: 6,
    fontSize: 14,
  },
})
