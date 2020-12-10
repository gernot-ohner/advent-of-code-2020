require 'set'

file = File.open('../resources/inputs/day10.txt')
data = file.readlines.map(&:to_i).to_set
file.close

current_joltage = 0
one_jolt_diffs = 0
three_jolt_diffs = 0

while current_joltage < data.max
  puts "Current joltage: #{current_joltage}"
  if data.include?(current_joltage + 1)
    one_jolt_diffs += 1
    current_joltage += 1
  elsif data.include?(current_joltage + 2)
    current_joltage += 2
  elsif data.include?(current_joltage + 3)
    three_jolt_diffs += 1
    current_joltage += 3
  end
end

puts "the result is #{one_jolt_diffs * (three_jolt_diffs + 1)}"