import fetch from 'dva/fetch';
import { notification } from 'antd';
import router from 'umi/router';


const codeMessage = {
  200: '服务器成功返回请求的数据。',
  201: '新建或修改数据成功。',
  202: '一个请求已经进入后台排队（异步任务）。',
  204: '删除数据成功。',
  400: '发出的请求有错误，服务器没有进行新建或修改数据的操作。',
  401: '用户没有权限（令牌、用户名、密码错误）。',
  403: '用户得到授权，但是访问是被禁止的。',
  404: '发出的请求针对的是不存在的记录，服务器没有进行操作。',
  406: '请求的格式不可得。',
  410: '请求的资源被永久删除，且不会再得到的。',
  422: '当创建一个对象时，发生一个验证错误。',
  500: '服务器发生错误，请检查服务器。',
  502: '网关错误。',
  503: '服务不可用，服务器暂时过载或维护。',
  504: '网关超时。',
};

export const ContentType = {
  JSON : "application/json;charset=UTF-8",
  FORM : "application/x-www-form-urlencoded;charset=UTF-8"
};

export const HttpMethod = {
  GET : "GET",
  POST : "POST",
  PUT : "PUT",
  PATCH : "PATCH",
  DELETE : "DELETE"
};

//指定头信息
const getHeaders = (type) => {
  if(type === 'json'){
    return {
      "Content-Type": ContentType.JSON
    }
  }else {
    return {
      "Content-Type": ContentType.FORM
    }
  }

};

export const getRequest = (url,body = null) => {
  if(body !== null){
    url = new URL(url);
    Object.keys(body).forEach(key => url.searchParams.append(key, body[key]));
  }

  const promise = fetch(url,{
    method : HttpMethod.GET,
    headers: getHeaders('from'),
    credentials: "include" //携带cookie信息
  });
  return checkStatus(promise);
};

export const postRequest = (url,body,callback) => {
  fetch(url,{
    method : HttpMethod.POST,
    headers: getHeaders('json'),
    body : JSON.stringify(body)
  }).then((response) => {
    if(response.status === 200){
      return response.json();
    }
    if(response.status ===404){
      router.push('/exception/404');
    }
    if(response.status ===403 ){
      router.push('/exception/403');
    }
    else {
      router.push('/exception/500');
    }
  }).then(rep=>{
    if(rep){
      callback(rep)
    }
  })
};

export const postFromRequest = (url,body,callback) => {
  fetch(url,{
    method : HttpMethod.POST,
    headers: getHeaders('from'),
    body : getFromData(body)
  }).then( response => {
    if(response.status === 200){
      return response;
    }
    if(response.status ===401){
      return response;
    }
    if(response.status ===404){
      router.push('/exception/404');
    }
    if(response.status ===403 ){
      router.push('/exception/403');
    }
    else {
      router.push('/exception/500');
    }
  }).then(rep=>{
    if(rep){
      callback(rep)
    }
  })
};

 const getFromData = (data) => {
  let formData = '';
  for (let item in data) {
    formData += `${encodeURIComponent(item)}=${encodeURIComponent(data[item])}&`
  }
  return formData;
};







