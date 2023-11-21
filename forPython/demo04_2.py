# 打开grade.txt文件，读取所有分数
with open("grade.txt", "r") as f:
    grades = [int(line) for line in f.readlines()]

# 计算最高分，最低分和平均分
max_grade = max(grades)
min_grade = min(grades)
avg_grade = sum(grades) / len(grades)

# 打开result.txt文件，写入结果
with open("result.txt", "w") as f:
    f.write(str(max_grade) + "\n")
    f.write(str(min_grade) + "\n")
    f.write("{:.1f}\n".format(avg_grade))
