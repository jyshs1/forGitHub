def isVowelWorld(word):
    vowels = ['a', 'e', 'i', 'o', 'u']
    dicCounts = {}
    word = word.lower()
    for ch in word:
        for c in vowels:
            if (ch == c):
                dicCounts[ch] = dicCounts.get(ch,0) + 1
     
    if (len(dicCounts) == 5):
        return True
    else:
        return False


word  =  input()
if  isVowelWorld(word):
        print(True)
else:
        print(False)
