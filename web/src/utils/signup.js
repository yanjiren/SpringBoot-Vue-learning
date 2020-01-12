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
