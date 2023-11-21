def Func(str):
    str.strip()
    temp = str[:-1]
    retNum = float(temp)
    if (str[-1] == 'F'or str[-1] == 'f'):
        retNum = int((retNum-32) / 1.8)
        return  "{}C".format(retNum)
    elif (str[-1] == 'C'or str[-1] == 'c'):
        retNum =  int((retNum*1.8) + 32)
        return "{}F".format(retNum)
    else:
        return None

temperature =  input("What is the temperature?")
temperatureTrans = Func(temperature)
print("The converted temperature is",temperatureTrans)
