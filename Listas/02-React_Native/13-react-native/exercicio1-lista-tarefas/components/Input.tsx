import { TextInput, StyleSheet } from 'react-native'

interface InputProps {
  value: string
  onChange: (value: string) => void
  placeholder?: string
}

export default function Input({
  value,
  onChange,
  placeholder,
}: InputProps) {
  return (
    <TextInput
      style={styles.input}
      value={value}
      onChangeText={onChange}
      placeholder={placeholder}
      placeholderTextColor="#9ca3af"
    />
  )
}

const styles = StyleSheet.create({
  input: {
    borderWidth: 1,
    borderColor: '#4b5563',
    backgroundColor: '#1f2937',
    color: '#f9fafb',
    padding: 10,
    borderRadius: 5,
    marginBottom: 10,
  },
})
