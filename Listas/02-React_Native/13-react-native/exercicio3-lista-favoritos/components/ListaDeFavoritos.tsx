import { View } from 'react-native'
import ItemFavorito, { Favorito } from './ItemFavorito'

interface ListaDeFavoritosProps {
  favoritos: Favorito[]
}

export default function ListaDeFavoritos({
  favoritos,
}: ListaDeFavoritosProps) {
  return (
    <View>
      {favoritos.map((favorito, indice) => (
        <ItemFavorito key={indice} favorito={favorito} />
      ))}
    </View>
  )
}
