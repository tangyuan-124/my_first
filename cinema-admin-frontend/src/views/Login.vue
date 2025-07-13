<!--
 * 登录页面
 *
 * @Author: ShanZhu
 * @Date: 2023-12-16
-->
<template>
  <div class="login_container">
    <div class="login_box">
      <div class="title_box">
        <p style="color: #e75c09"><i class="iconfont icon-login-title" style="font-size: 36px;"> </i>登录电影院管理系统</p>
      </div>
      <!-- 登录表单区域 -->
      <el-form class="login_form" :model="loginForm" :rules="loginFormRules" ref="loginFormRef">
        <!-- 用户名 -->
        <el-form-item prop="userName">
          <el-input v-model="loginForm.userName" placeholder="请输入用户名" clearable
                    prefix-icon="iconfont icon-user"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input v-model="loginForm.password" type="password" placeholder="请输入密码" show-password
                    prefix-icon="iconfont icon-lock"></el-input>
        </el-form-item>
        <!-- 按扭区域 -->
        <el-form-item class="btns">
          <el-button style="font-size: 22px;" size="small" type="success" @click="login">
            <i class="iconfont icon-sure-button" style="font-size: 22px;"></i>
             登录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      //登录表单数据对象
      loginForm: {
        userName: '',
        password: ''
      },
      //表单验证规则
      loginFormRules: {
        //验证用户名
        userName: [
          { required: true, message: "请输入用户名称", trigger: "blur"},
          { min:2, max: 20, message: "长度在2到20个字符之间", trigger: "blur"}
        ],
        //验证密码
        password: [
          { required: true, message: "请输入密码", trigger: "blur"},
          { min:6, max: 16, message: "长度在6到16个字符之间", trigger: "blur"}
        ]
      }
    }
  },
  methods:{
    success(params) {
      this.login()
    },
    login() {
      this.$refs.loginFormRef.validate(async valid => {
        if(!valid) return;
        axios.defaults.headers.post['Content-Type'] = 'application/json'
        const { data: res} = await axios.post('sysUser/login', JSON.stringify(this.loginForm));
        if(res.code !== 200) return this.$message.error(res.msg);
        //控制登录权限
        // 判断用户是否有权限登录（检查角色）
        const userRole = res.data.sysUser.sysRole;
        if (userRole === null || userRole.roleId !== 1) {
          // 如果角色为 null 或 roleId 不等于 1（非管理员）
          this.$message.error("抱歉，您没有权限登录，请联系管理员获取权限");
          return;
        }
        this.$message.success("登录成功")
        //保存token
        window.sessionStorage.setItem("token", res.data.token)
        window.sessionStorage.setItem("loginUser", JSON.stringify({sysUser : res.data.sysUser, cinemaId : res.data.cinemaId, cinemaName : res.data.cinemaName}));
        window.sessionStorage.setItem("btnPermission", res.data.sysUser.sysRole.roleId === 1 ? "admin" : "normal")
        //window.sessionStorage.setItem("btnPermission", res.data.sysUser.sysRole.roleId === 1 ? "admin" : "admin")
        //导航跳转到首页
        await this.$router.push('/welcome');
      })
    }
  }
}
</script>

<style scoped>
.login_container{
  height: 100%;
  background: url("../assets/login-background.jpg");
  background-size: cover;
}

.login_box{
  opacity:0.9;
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 8px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  border: 1px solid #ffffff;
}

.avatar_box{
  height: 130px;
  width: 130px;
  border: 1px solid #eee;
  border-radius: 50%;
  padding: 10px;
  box-shadow: 0 0 10px #ddd;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
}

.avatar_box > img{
  width: 100%;
  height: 100%;
  border-radius: 50%;
  background-color: #eee;
}

.title_box{
  text-align: center;
  font-size: 200%;
}

.login_form{
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}

.btns{
  display: flex;
  justify-content: center;
}
</style>
