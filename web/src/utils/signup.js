import axios from 'axios'

export async function isExistUsername(str) {
  let data = 0;
  await axios({
    method: 'get',
    url: "/signup/validate?username="+str
  }).then(resp=>{
    data = resp.data;
  })
  return data;
}

export function entSignup (username,password){
  return axios({
    method: 'post',
    url: "/signup/ent?username="+username+"&password="+password,
  });
}
