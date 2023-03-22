<template>
	<el-form ref="form" :model="form" label-width="80px" @submit.prevent="onSubmit" style="margin:20px;width:60%;min-width:600px;">
		<el-form-item label="产品编号" style="width: 50%;">
			<el-input v-model="form.ID" @blur.prevent="getName"></el-input>
		</el-form-item>
		<el-form-item label="产品名称" style="width: 50%;">
			<el-input v-model="form.name" id="name"></el-input>
		</el-form-item>
		<el-form-item label="产品数量" style="width: 50%;">
			<el-input v-model="form.num"></el-input>
		</el-form-item>
		<el-form-item label="销售价格" style="width: 50%;">
			<el-input v-model="form.price"></el-input>
		</el-form-item>
		<el-form-item label="销售渠道" style="width: 50%;">
			<el-select v-model="form.sale_path" placeholder="请选择销售渠道">
				<el-option label="杭州" value="hangzhou"></el-option>
				<el-option label="上海" value="shanghai"></el-option>
				<el-option label="北京" value="beijing"></el-option>
				<el-option label="广州" value="guangzhou"></el-option>
				<el-option label="深圳" value="shenzhen"></el-option>
			</el-select>
		</el-form-item>
		<el-form-item label="销售期限" style="width: 50%;">
			<el-col :span="11">
				<el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
			</el-col>
			<el-col class="line" :span="1" style="margin-left: 5px">-</el-col>
			<el-col :span="11">
				<el-date-picker type="date" placeholder="选择日期" v-model="form.date2" style="width: 100%;"></el-date-picker>
			</el-col>
		</el-form-item>
		<el-form-item label="销售费用" style="width: 50%;">
			<el-input v-model="form.all_price"></el-input>
		</el-form-item>
		<el-form-item>
			<el-button type="primary" v-on:click="onSubmit">立即创建</el-button>
			<el-button @click.native.prevent>取消</el-button>
		</el-form-item>
	</el-form>
</template>

<script>
    export default {
        data() {
            return {
                form: {
                    ID: '',
                    num: '',
                    price: '',
                    sale_path: '',
                    date1: '',
                    date2: '',
                    all_price:''
                }
            }
        },
        methods: {
            onSubmit() {
                console.log('submit!');
                alert("销售计划创建成功！");
                this.$router.push({path: '/user'});	//模拟跳转到相应列表
            },
			/*产品ID输入框失去焦点函数:设置对应产品ID产品名称*/
            getName:function () {
                if(this.form.ID==="2018001")
				{
                    document.getElementById("name").innerText="产品名称001";
				}
				else if(this.form.ID==="2018002")
				{
                    document.getElementById("name").innerText="产品名称002";
				}
                else if(this.form.ID==="2018003")
                {
                    document.getElementById("name").innerText="产品名称003";
                }
            }
		}

    }



</script>