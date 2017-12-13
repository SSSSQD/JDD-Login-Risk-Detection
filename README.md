## 逻辑说明
1. 建立一个子模型, 本段交易定义为这段交易前后2次交易时间不超过20min, 如果最近2h内登录过不同设备或IP, 就加入数据集
2. 建立一个主模型, 本段交易定义为这段交易前后2次交易时间不超过20min, 本次登录定义为最近一次登录结果不为31的登录, 就加入数据集
3. 制定白样本过滤策略, 对于前两步生成的结果集, 如果某段交易的那次登录城市, 3天前以及3天后, 用户都登录过, 从结果集中去除

## 代码说明
### 1. Data
- [x] 对所有数据新增一列`row_name`, 要求全局唯一
### 2. Feature
- [x] 导入Feature内的Intellij工程
- [x] 运行`com.jd.login.v14.Feature14A.java`, `com.jd.login.v14.Feature14C.java`, 生成训练测试特征数据
- [ ] 注意运行其他package内`.java`, 生成前置`map`文件
- [ ] 运行`com.jd.login.WhiteList.java`, 打印结果`b.csv`
### 3. Jupyter Notebook
- [x] 运行`part1.ipynb`, 生成子模型结果集`a.csv`
- [x] 运行`part2.ipynb`, 生成主模型结果集`c.csv`
- [ ] 合并`a.csv`和`c.csv`, 去重复, 去除`b.csv`中的`row_name`, 生成最后结果集`d.csv`
