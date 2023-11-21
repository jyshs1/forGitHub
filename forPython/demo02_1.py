def mean(numbers):
    return sum(numbers) / len(numbers)

def median(numbers):
    numbers.sort()
    length = len(numbers)
    if length % 2 == 0:
        return (numbers[length // 2 - 1] + numbers[length // 2]) / 2
    else:
        return numbers[length // 2]

data = []
while True:
    try:
        number = float(input("Enter a number (<Enter> to quit):"))
        data.append(number)
    except ValueError:
        break

mean_value = mean(data)
median_value = median(data)
print("The mean is {:.6f}".format(mean_value))
print("The median is {:.6f}".format(median_value))
