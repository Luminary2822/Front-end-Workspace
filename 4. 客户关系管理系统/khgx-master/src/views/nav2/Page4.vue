<!--<template>-->
  <!--<div>-->
    <!--<h1>vuex 测试</h1>-->
    <!--Clicked: {{ getCount }} times-->
    <!--<button @click="increment">+</button>-->
    <!--<button @click="decrement">-</button>-->
  <!--</div>-->
<!--</template>-->

<!--<script>-->
  <!--import { mapGetters } from 'vuex'-->
  <!--import { mapActions } from 'vuex'-->

  <!--export default {-->
    <!--computed: {-->
  	<!--// 使用对象展开运算符将 getters 混入 computed 对象中-->
    <!--...mapGetters([-->
      <!--'getCount'-->
      <!--// ...-->
    <!--])-->
    <!--},-->
    <!--methods: {-->
    <!--...mapActions([-->
      <!--'increment', // 映射 this.increment() 为 this.$store.dispatch('increment')-->
      <!--'decrement'-->
    <!--])-->
      <!--//...mapActions({-->
      <!--//  add: 'increment' // 映射 this.add() 为 this.$store.dispatch('increment')-->
      <!--//})-->
    <!--}-->
  <!--}-->

<!--</script>-->
<template>
    <el-form ref="form" :model="form" label-width="80px"  style="margin:20px;width:60%;min-width:600px;">
        <div>
            <el-form-item label="客户ID"  style="width:50%;float: left;">
                <el-input v-model="form.clientId" @blur.prevent="getclientName"></el-input>
            </el-form-item>
            <el-form-item label="客户名称"  style="width:50%;float: left;">
                <el-input v-model="form.clientName" id="clientName"></el-input>
            </el-form-item>
        </div>
        <div>
            <el-form-item label="产品ID"  style="width:50%;float: left;">
                <el-input v-model="form.productId" @blur.prevent="getproductName"></el-input>
            </el-form-item>
            <el-form-item label="产品名称"  style="width:50%;float: left;">
                <el-input v-model="form.productName" id="productName" ></el-input>
            </el-form-item>
        </div>
        <el-form-item label="产品数量"  style="width:30%">
            <el-input v-model="form.productAmount"></el-input>
        </el-form-item>
        <el-form-item label="产品单价"  style="width:30%">
            <el-input v-model="form.univalent"></el-input>
        </el-form-item>
        <el-form-item label="产品总价"  style="width:30%">
            <el-input v-model="form.totalPrice"></el-input>
        </el-form-item>
        <el-form-item label="销售渠道">
            <el-select v-model="form.salesPath" placeholder="请选择销售渠道">
                <el-option label="上海" value="shanghai"></el-option>
                <el-option label="杭州" value="hangzhou"></el-option>
            </el-select>
        </el-form-item>
    <el-form-item label="销售时间">
      <el-col :span="11">
        <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
      </el-col>
      <el-col class="line" :span="2">-</el-col>
      <el-col :span="11">
        <el-time-picker type="fixed-time" placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
      </el-col>
    </el-form-item>
    <el-form-item label="即时配送">
      <el-switch on-text="" off-text="" v-model="form.delivery"></el-switch>
    </el-form-item>
    <el-form-item label="支付方式">
      <el-checkbox-group v-model="form.payType">
        <el-checkbox label="支付宝" name="zhifubao"></el-checkbox>
        <el-checkbox label="微信" name="weixin"></el-checkbox>
        <el-checkbox label="现金" name="xianjin"></el-checkbox>
      </el-checkbox-group>
    </el-form-item>
    <el-form-item label="订单状态">
      <el-radio-group v-model="form.resource">
        <el-radio label="已购买"></el-radio>
        <el-radio label="以付款"></el-radio>
        <el-radio label="已送达"></el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="订单备注">
      <el-input type="textarea" v-model="form.desc"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="submit" v-on:click="onSubmit">立即创建</el-button>
      <el-button @click.native.prevent>取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
    export default {
        data() {
            return {
                form: {
                    clientId: '',
                    salesPath: '',
                    productId: '',
                    productAmount: '',
                    univalent: '',
                    totalPrice: '',
                    date1: '',
                    date2: '',
                    delivery: false,
                    payType: [],
                    resource: '',
                    desc: ''
                }
            }
        },
        methods: {
            onSubmit() {
                console.log('submit!');
                alert("订单创建成功！");
                this.$router.push({path: '/user'});	//模拟跳转到相应列表
            },
            /*客户ID输入框失去焦点函数:设置对应客户名称*/
            getclientName:function () {
                if(this.form.clientId==="001")
                {
                    document.getElementById("clientName").innerText="莫良言";
                }
                else if(this.form.clientId==="002")
                {
                    document.getElementById("clientName").innerText="王宇涵";
                }
                else if(this.form.clientId==="003")
                {
                    document.getElementById("clientName").innerText="刘一果";
                }
            },
            /*产品ID输入框失去焦点函数:设置对应产品ID产品名称*/
            getproductName:function () {
                if(this.form.productId==="2018001")
                {
                    document.getElementById("productName").innerText="产品名称001";
                }
                else if(this.form.productId==="2018002")
                {
                    document.getElementById("productName").innerText="产品名称002";
                }
                else if(this.form.productId==="2018003")
                {
                    document.getElementById("productName").innerText="产品名称003";
                }
            }

        }
    }

</script>