str1 = input("")
str1=str1.upper()
dicCounts = {}
arr = ['A', 'E', 'I', 'O', 'U']
for ch in str1:
    for c in arr:
        if (ch == c):
            dicCounts[ch]=dicCounts.get(ch,0) + 1

        

# print(dicCounts)
for ch in arr:
    if (dicCounts.get(ch) != None):
        diclen = int(dicCounts.get(ch))
        print("{}:{},{:.2%}".format(ch, diclen, diclen/len(str1)))
    else:
        print("{}:{},{:.2%}".format(ch, 0, 0))
        

