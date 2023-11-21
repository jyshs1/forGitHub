months = "JanFebMarAprMayJunJulAugSepOctNovDec"
index = eval(input("Enter a month number (1-12):"))
pos = (index-1)*3
monthAbbrev = months[pos:pos+3]
strr = "The month abbreviation is {}.".format(monthAbbrev)
print(strr)
