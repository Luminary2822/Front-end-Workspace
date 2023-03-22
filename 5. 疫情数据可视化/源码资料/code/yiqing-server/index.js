const express = require('express')
const app = express()
//资源共享
const cors = require('cors')
app.use(cors())

//导入本地数据
const chinaData = require('./data/chinaData')
const worldData = require('./data/worldData')
const quanqiu = require('./data/quanqiu')
const travelCity = require('./data/travelcity')

//接口1：全国疫情接口数据
app.get('/api/94/219',(req,res)=>{
    res.send(chinaData)
})

//接口2：世界接口数据
app.get('/api/94/220',(req,res)=>{
    res.send(worldData)
})

//接口3：全球接口数据
app.get('/api/94/222',(req,res)=>{
    res.send(quanqiu)
})

//接口4：出行城市 travelCity
app.get('/travelCity',(req,res)=>{
    res.send(travelCity)
})


app.listen(8888,()=>{
    console.log(8888);
})