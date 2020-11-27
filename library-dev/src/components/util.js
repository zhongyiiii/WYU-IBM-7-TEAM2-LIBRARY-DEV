
import Vue from 'vue';
var v = new Vue();
// param为传入参数
//参数1：who,（标题）
//参数2：content，(展示内容)
//参数3：tips，（点击事件后的提示）
// function alert(who, content,timeout,tips){
//     const h = v.$createElement;
//     v.$msgbox({
//           title: who,
//           message: h('p', null, [
//             h('span', null, content),
//             // h('i', { style: 'color: teal' }, 'VNode')
//           ]),
//           showCancelButton: true,
//           confirmButtonText: '确定',
//           cancelButtonText: '取消',
//           beforeClose: (action, instance, done) => {
//             if (action === 'confirm') {
//               instance.confirmButtonLoading = true;
//               instance.confirmButtonText = '执行中...';
//               setTimeout(() => {
//                 done();//关闭弹窗
//                 setTimeout(() => {
//                   instance.confirmButtonLoading = false;
//                 }, 300);
//               }, timeout);
//             } else {
//               done();
//             }
//           }
//         })
//         .then(() => {
//           // console.log(action)
//           if(tips == null){
//             return false;
//           }
//           v.$message({
//             type: 'info',
//             message: tips
//           });
//         })
//         .catch(()=>{
//           // console.log("error:"+tips)
//         });
// }

function confirm(who, content,timeout){
  return new Promise((resolve,reject)=>{
    const h = v.$createElement;
    v.$msgbox({
          title: who,
          message: h('p', null, [
            h('span', null, content),
            // h('i', { style: 'color: teal' }, 'VNode')
          ]),
          showCancelButton: true,
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          beforeClose: (action, instance, done) => {
            if (action === 'confirm') {
              instance.confirmButtonLoading = true;
              instance.confirmButtonText = '执行中...';
              setTimeout(() => {
                done();//关闭弹窗
                setTimeout(() => {
                  instance.confirmButtonLoading = false;
                }, 300);
              }, timeout);
            } else {
              done();
            }
          }
          
        }).then(() => {
      
          resolve(true)
        }).catch(() => {
          reject(false)
        })
  })
}

function alertSuccess(who,content,timeout){
  v.$notify({
    title: who,
    message: content,
    type: 'success',
    duration:timeout
  });
}
function alertWarning(who,content,timeout){
  v.$notify({
    title: who,
    message: content,
    type: 'warning',
    duration:timeout
  });
}
function alertFail(who,content,timeout){
  v.$notify.error({
    title: who,
    message:content,
    duration:timeout
  });
}

function alertInfo(who,content,timeout){
  v.$notify.info({
    title: who,
    message:content,
    duration:timeout
  });
}
export default {
    // Vue.js的插件应当有一个公开方法 install。这个方法的第一个参数是 Vue 构造器，第二个参数是一个可选的选项对象。
    install: function (Vue) {
      Vue.prototype.alertSuccess = (who, content,timeout) => alertSuccess(who, content,timeout);
      Vue.prototype.alertFail = (who, content,timeout) => alertFail(who, content,timeout);
      Vue.prototype.alertInfo = (who, content,timeout) => alertInfo(who, content,timeout);
      Vue.prototype.alertWarning = (who, content,timeout) => alertWarning(who, content,timeout);
      Vue.prototype.confirm = (who, content,timeout) => confirm(who, content, timeout);
    }
  }