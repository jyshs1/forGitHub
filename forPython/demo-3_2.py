
def tans2ISBN13(isbn10):
    str2 = "978"
    str2 += isbn10.replace("-", "")
    n = 1
    suma = 0
    for ch in str2:
        if (n > 12):
            break
        num = int(ch)
        if (n%2 == 0):
            num *= 3
        suma += num
        n += 1
       # print("{}  {}".format(num,suma))
    suma = 10 - suma % 10
    isbn10 = isbn10[:-2]
    if (suma >= 0 and suma <= 9):
        return "987-{}-{}".format(isbn10, suma)
    else:
        return "978-{}-{}".format(isbn10, "0")
        



isbn10  =  input()
print(tans2ISBN13(isbn10))



