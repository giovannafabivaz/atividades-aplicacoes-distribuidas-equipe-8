import { TextInput, StyleSheet } from 'react-native'

interface CampoTextoProps {
  value: string
  onChange: (value: string) => void
  placeholder: string
}

export default function CampoTexto({
  value,
  onChange,
  placeholder,
}: CampoTextoProps) {
  return (
    <TextInput
      style={styles.input}
      value={value}
      onChangeText={onChange}
      placeholder={placeholder}
      placeholderTextColor="#777777"
    />
  )
}

const styles = StyleSheet.create({
  input: {
    width: '100%',
    borderWidth: 1,
    borderColor: '#333333',
    backgroundColor: '#111111',
    color: '#ffffff',
    padding: 14,
    borderRadius: 8,
    marginBottom: 12,
    fontSize: 16,
  },
})
