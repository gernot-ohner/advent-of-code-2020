require 'set'

file = File.open("../resources/inputs/day8_modified.txt")
data = file.readlines
file.close

i = 0
acc = 0
already_visited_instructions = Set.new

def compute(s, i, acc)
  operation, argument = s.split(' ')
  puts "i: #{i}, acc: #{acc}, operation: #{operation}, argument: #{argument}"
  case operation
  when 'nop'
    [i + 1, acc]
  when 'acc'
    [i + 1, acc + argument.to_i]
  when 'jmp'
    [i + argument.to_i, acc]
  end
end

loop do
  if already_visited_instructions.include? i
    puts "loop detected!"
    break acc
  end
  if i == data.size
    puts 'made it to the end!'
    break acc
  end

  already_visited_instructions.add i
  i, acc = compute(data[i], i, acc)
end

puts acc

