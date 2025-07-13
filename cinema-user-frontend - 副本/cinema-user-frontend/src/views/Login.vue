<template>
  <div id="background">
    <div id="logo">SSE Cinema</div>
    <div id="login">
      <el-card class="box-card">
        <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules">
          <div id="form">
            <div class="left-section">
              <img src="../assets/loginpic.png" >
            </div>
            <div class="form-content">
              <div class="welcome-text">welcome</div>
              <el-input v-model="loginForm.userName" placeholder="请输入用户名"></el-input>
              <el-input placeholder="请输入密码" v-model="loginForm.password" show-password></el-input>
              <el-button class="login-btn" @click="login">登录</el-button>
              <el-button  class="register-btn"@click="register">注册</el-button>
              <div class="lottery-buttons">
                <el-button class="lottery-btn movie" @click="startLottery">开始抽奖</el-button>
              </div>
            </div>
          </div>
        </el-form>
      </el-card>
    </div>
    <div id="clock">
      <div id="time">{{ time }}</div>
      <div id="date">{{ date }}</div>
    </div>
  </div>
</template>

<script>
export default{
  name: "Login",
  data() {
    return {
      loginForm: {
        userName: "",
        password: "",
      },
      loginFormRules: {
        userName: [
          { required: true, message: "请输入用户名称", trigger: "blur" },
          { min: 2, max: 20, message: "用户名称长度在2到20个字符之间", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 16, message: "登录密码长度在6到16个字符之间", trigger: "blur" },
        ],
      },
      time: "",
      date: "",
      sessionId: 0,
    };
  },
  mounted() {
    this.$nextTick(() => {
      setInterval(this.update_clock, 1000);
    });
  },
  methods: {
    update_clock() {
      const d = new Date();
      const zeroPad = (num) => (num < 10? "0" + num : num);
      this.time = `${zeroPad(d.getHours())}:${zeroPad(d.getMinutes())}`;
      this.date = `${d.getFullYear()}/${zeroPad(d.getMonth() + 1)}/${zeroPad(d.getDate())}`;
    },
    success(params) {
      this.login();
    },
    login() {
      this.$refs.loginFormRef.validate(async (valid) => {
        if (!valid) return;
        axios.defaults.headers.post["Content-Type"] = "application/json";
        const { data: res } = await axios.post("sysUser/login", JSON.stringify(this.loginForm));
        if (res.code!== 200) return this.$message.error(res.msg);
        this.$message.success({ message: "登录成功", duration: 1000 });
        console.log(res.data);
        window.sessionStorage.setItem("token", res.data.token);
        res.data.sysUser.sysRole = null;
        window.sessionStorage.setItem("loginUser", JSON.stringify(res.data.sysUser));

        // 登录成功后显示抽奖对话框
        const h = this.$createElement;
        const _this = this;
        // 定义抽奖处理函数
        const handleLottery = (type) => {
          // 关闭抽奖对话框
          document.querySelector('.el-message-box__close').click();

          switch(type) {
            case 'movie':
              // 直接抽电影
              _this.drawMovie(1);
              break;
            case 'again':
              // 显示提示后再次显示抽奖对话框
              _this.$message.info("恭喜获得再抽一次的机会！");
              setTimeout(() => {
                // 重新显示抽奖对话框
                showLotteryDialog();
              }, 1000);
              break;
            case 'thanks':
              // 显示感谢信
              _this.$message.info("谢谢参与，欢迎下次再来！");
              setTimeout(() => {
                _this.$router.push("/welcome");
              }, 1000);
              break;
          }
        };

        // 定义显示抽奖对话框的函数
        const showLotteryDialog = () => {
          // 创建按钮类型数组并随机打乱顺序
          const buttonTypes = ['movie', 'again', 'thanks'];
          for (let i = buttonTypes.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [buttonTypes[i], buttonTypes[j]] = [buttonTypes[j], buttonTypes[i]];
          }

          this.$alert(
            '<div style="text-align:center">' +
            '<h3 style="color:#4CAF50;margin-bottom:20px">欢迎参与抽奖活动</h3>' +
            '<p style="color:#666;margin-bottom:15px">每位用户只有一次抽奖机会哦！</p>' +
            '<div class="lottery-dialog-buttons">' +
            buttonTypes.map(type =>
              `<button class="lottery-btn" onclick="window.handleLotteryClick('${type}')"></button>`
            ).join('') +
            '</div>' +
            '</div>',
            '抽奖活动',
            {
              dangerouslyUseHTMLString: true,
              showConfirmButton: false,
              showCancelButton: true,
              cancelButtonText: '暂不参与',
              beforeClose: (action, instance, done) => {
                if (action === 'cancel') {
                  if (_this.sessionId !== 0 && _this.sessionId !== "0" && _this.sessionId !== null) {
                    _this.$router.push("/chooseSeat/" + _this.sessionId);
                  } else {
                    _this.$router.push("/welcome");
                  }
                }
                done();
              }
            }
          );
        };

        // 添加全局处理函数
        window.handleLotteryClick = handleLottery;

        // 显示抽奖对话框
        showLotteryDialog();
      });
    },
    register() {
      this.$router.push("/register");
    },
    startLottery() {
      // 检查登录状态
      const token = window.sessionStorage.getItem("token");
      if (!token) {
        return this.$message.error("请先登录后再参与抽奖");
      }

      // 随机生成抽奖结果
      const random = Math.random();
      let type;
      if (random < 0.4) {  // 40%概率抽中电影
        type = 1;
      } else if (random < 0.7) {  // 30%概率再抽一次
        type = 2;
      } else {  // 30%概率谢谢参与
        type = 3;
      }

      this.drawMovie(type);
    },
    async drawMovie(type) {
      try {
        const loginUser = JSON.parse(window.sessionStorage.getItem("loginUser"));
        if (!loginUser || !loginUser.userId) {
          return this.$message.error("用户信息获取失败，请重新登录");
        }

        const token = window.sessionStorage.getItem("token");
        if (!token) {
          return this.$message.error("登录已过期，请重新登录");
        }

        const { data: res } = await axios.post(
          `lottery/drawMovie?userId=${loginUser.userId}&type=${type}`,
          null,
          {
            headers: {
              'Authorization': token
            }
          }
        );

        if (res.code !== 200) {
          if (type === 3) {
            this.$message.info("谢谢参与，下次再来！");
          } else if (type === 2) {
            this.$message.info("恭喜获得再抽一次的机会！");
            setTimeout(() => {
              showLotteryDialog();
            }, 1000);
          } else {
            if (res.msg.includes("暂无")) {
              this.$message.error("抱歉，暂无可参与抽奖的场次");
              setTimeout(() => {
                this.$router.push("/welcome");
              }, 1500);
            } else {
              this.$message.error(res.msg || "抽奖失败，请稍后重试");
            }
          }
          return;
        }

        if (!res.data || !res.data.billId) {
          return this.$message.error("订单创建失败");
        }

        this.$message.success({
          message: `恭喜抽中电影：${res.data.blindBoxContents}`,
          duration: 2000
        });

        setTimeout(() => {
          this.$router.push("/welcome");
        }, 1000);
      } catch (error) {
        console.error("抽奖异常:", error);
        this.$message.error("抽奖失败，请稍后重试");
      }
    }
  },
  created() {
    this.sessionId = window.sessionStorage.getItem("sessionId");
    console.log("this sessionId is : " + this.sessionId);
    window.sessionStorage.setItem("sessionId", 0);
  },
};
</script>

<style scoped>
#background {
  background-image: linear-gradient(90deg, #93aec1, #9dbdba);
  background-size: 400%;
  animation: myanimation 8s infinite;
  position: fixed;
  height: 100%;
  width: 100%;
  top: 0;
  left: 0;
}
@keyframes myanimation {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
#logo {
  color: aliceblue;
  font-size: 30px;
  font-weight: 800;
  text-transform: uppercase;
  position: absolute;
  top: 15%;
  left: 15%;
}
#login {
  position: absolute;
  top: 25%;
  left: 30%;
}
#buttons {
  display: flex;
  justify-content: space-between;
}
.el-card {
  width: 600px;
  height: 420px;
  background-color: rgb(255,255,255);
  border-radius: 20px;
}
#clock {
  color: aliceblue;
  position: absolute;
  top: 70%;
  left: 20%;
  transform: translateX(-50%);
  text-align: center;
}
#time {
  font-size: 100px;
}
#date {
  font-size: 35px;
}
#form {
  padding-top: 25px;
  padding-left: 15px;
  padding-right: 15px;
  display: flex;
  flex-direction: row;
  gap: 20px;
  align-items: center;
}
.form-content {
  padding-left: 25px;
  padding-right: 25px;
  display: flex;
  flex-direction: column;
  align-items: center; /* 让内部元素在水平方向居中对齐 */
  gap: 20px; /* 适当增大元素间的垂直间距 */
}
.left-section {
  margin-right: 20px;
}
.left-section img {
  width: 200px;
  height: auto;
}
.login-btn,
.register-btn {
  width: 250px;
  border-radius: 20px;
  text-align: center;
  transition: transform 0.3s ease-in-out;
  padding: 10px;
  margin-top: 20px;
}
.login-btn {
  background-color: #ec6a52;
  color: white;
}
.login-btn:hover {
  transform: scale(1.05);
}
.register-btn {
  background-color: #f3b7ad;
  color: white;
}
.register-btn:hover {
  transform: scale(1.05);
}
.welcome-text {
  font-weight: bold;
  color: black;
  font-size: 30px;

  text-transform: uppercase;
}
/* 对输入框也统一设置一些样式，让其宽度等更协调 */
.el-input {
  width: 256px;
}
.lottery-buttons {
  display: flex;
  gap: 10px;
  margin-top: 10px;
}
.lottery-btn {
  width: 200px;
  border-radius: 20px;
  color: white;
  text-align: center;
  transition: transform 0.3s ease-in-out;
  padding: 10px;
}

.lottery-btn:hover {
  transform: scale(1.05);
}
.lottery-btn.movie {
  width: 200px;
  background-color: #4CAF50;
}
.lottery-dialog-buttons {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
  gap: 15px;
  padding: 20px;
}

.lottery-dialog-buttons .lottery-btn {
  flex: 1;
  width: 120px;
  height: 120px;
  border-radius: 50%;
  color: white;
  text-align: center;
  transition: all 0.3s ease-in-out;
  padding: 10px;
  border: none;
  cursor: pointer;
  background-color: #4CAF50;
  box-shadow: 0 4px 8px rgba(76, 175, 80, 0.3);
  position: relative;
  overflow: hidden;
}

.lottery-dialog-buttons .lottery-btn::after {
  content: '?';
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 48px;
  color: rgba(255, 255, 255, 0.8);
  font-weight: bold;
}

.lottery-dialog-buttons .lottery-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(76, 175, 80, 0.4);
  background-color: #45a049;
}

.lottery-dialog-buttons .lottery-btn:active {
  transform: translateY(1px);
  box-shadow: 0 1px 2px rgba(76, 175, 80, 0.3);
  background-color: #3d8b40;
}
</style>
