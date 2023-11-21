
# 定义有理数类
class Rational:
    # 构造方法，初始化分子和分母
    def __init__(self, numerator, denominator):
        # 如果分母为0，抛出异常
        if denominator == 0:
            raise ValueError("Denominator cannot be zero")
        # 如果分子为0，分母设为1
        if numerator == 0:
            self.numerator = 0
            self.denominator = 1
        else:
            # 确保分数的符号在分子上
            if denominator < 0:
                numerator = -numerator
                denominator = -denominator
            # 约分，使用辗转相除法求最大公约数
            gcd = self.gcd(abs(numerator), abs(denominator))
            self.numerator = numerator // gcd
            self.denominator = denominator // gcd

    # 定义计算最大公约数的静态方法
    @staticmethod
    def gcd(a, b):
        # 如果b为0，返回a
        if b == 0:
            return a
        # 否则，递归调用
        return Rational.gcd(b, a % b)

    # 定义计算有理数加法的方法
    def add(self, other):
        # 通分，求分子和分母的乘积
        numerator = self.numerator * other.denominator + self.denominator * other.numerator
        denominator = self.denominator * other.denominator
        # 返回一个新的有理数对象
        return Rational(numerator, denominator)

    # 定义打印有理数的方法
    def __str__(self):
        # 如果分母为1，只输出分子
        if self.denominator == 1:
            return str(self.numerator)
        # 否则，输出分子和分母
        return "{}/{}".format(self.numerator, self.denominator)

# 输入N
N = eval(input())
# 输入N个有理数，用空格分隔
rationals = input().split()
# 初始化一个和为0的有理数
sum = Rational(0, 1)
# 遍历每个有理数
for r in rationals:
    # 用/分隔分子和分母
    numerator, denominator = map(int, r.split("/"))
    # 创建一个有理数对象
    rational = Rational(numerator, denominator)
    # 累加到和中
    sum = sum.add(rational)
# 计算平均值，用N作为分母
average = Rational(sum.numerator, sum.denominator * N)
# 输出平均值
print(average)
