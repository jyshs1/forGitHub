
str1 = input("")
str2 = str1.replace("-", "")

sum1 = 0
n = 10
for ch in str2:
    num1 = int(ch)
    temp = num1 * n
    sum1 += temp
    n-=1
#print(sum1)

m = 11 - (sum1 % 11)
if (m == 11):
    m = 0
if(m != 10):
    str1 = "ISBN:{}-{}".format(str1,m)
else:
    str1 = "ISBN:{}-{}".format(str1,"X")
print(str1)


'''
for i in range(9, 0,-1):
    print(i)
'''
