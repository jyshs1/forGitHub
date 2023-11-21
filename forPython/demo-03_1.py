from math import *
def calu(a1, a2, d1, d2):
    a = a2 - a1
    #a %= 180
    d = d2 - d1
    #d %= 90
    d1Pi = radians(d1)
    d2Pi = radians(d2)
    aPi = radians(a)
    #print("{} {}".format(a,aPi))
    dPi = radians(d)
    temp = sin(dPi/2)*sin(dPi/2) + cos(d1Pi)*cos(d2Pi)*sin(aPi/2)*sin(aPi/2)
    arcSqrt = 2*asin(sqrt(temp))
    return arcSqrt

star1  =  eval(input())
star2  =  eval(input())

ret = calu(star1[0], star2[0], star1[1], star2[1])
print("{:.4f}".format(ret))
